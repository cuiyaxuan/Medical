package controller;

import Util.AjaxResponse;
import Util.CosUtil;
import entity.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
*
 * @author WangXinYu
 * @date 2018/02/24
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/uploadImg")
    public AjaxResponse test(HttpServletRequest request) throws Exception {
        return CosUtil.uploadImgCloudBase64(request);
    }
    @ResponseBody
    @RequestMapping("/getUserInfo")
    public AjaxResponse getUserInfo(HttpServletRequest request) throws Exception {
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
        try {
            Map<String,Object> map=userService.getUserInfo(id);
            if(map.get("d_leader")==map.get("id")) {
                map.put("leader", 1);
            }else {
                map.put("leader", 0);
            }
            ajaxResponse.setSuccessMessage("查询成功！", map);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询失败！", e);
            e.printStackTrace();
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("/updateUserInfo")
    public AjaxResponse updateUserInfo(MUser mUser,HttpServletRequest request) throws Exception {
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        try {
            int i=userService.updateUserInfo(mUser);
            if(i>0) {
                ajaxResponse.setSuccessMessage("修改成功！", i);
            }
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("修改失败！", e);
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    /**
     * 管理员分配账户
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/add")
    public AjaxResponse add(String userName,MUser mUser,HttpServletRequest request) throws Exception {
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        int i = userService.insert(userName, mUser);
        if(i>0) {
            ajaxResponse.setSuccessMessage("分配账号成功，初始密码为123456", i);
        }else {
            ajaxResponse.setErrorMessage("分配账号失败！", i);
        }
        return ajaxResponse;
    }
}
