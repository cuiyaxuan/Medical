package controller.role;

import Util.AjaxResponse;
import entity.Logs;
import entity.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import service.logs.LogsService;
import service.role.RoleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月13日 10:20
 **/
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    private LogsService logsService;
    @Autowired
    private UserService userService;
    @RequestMapping("listUserRole")
    @ResponseBody
    public AjaxResponse listUserRole(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        List<Map<String, Object>> mapList = roleService.listAllUserRole();
        ajaxResponse.setResult(mapList);
        ajaxResponse.setMessage("查询成功！");
        return ajaxResponse;
    }
    @RequestMapping("updateUserRole")
    @ResponseBody
    public AjaxResponse updateUserRole(int id ,int role,HttpServletRequest request){
        HttpSession session = request.getSession(true);
        int newRole = 0;
        if(role==1){
            newRole = 2;
        }else if(role==2) {
            newRole = 1;
        }
        AjaxResponse ajaxResponse = new AjaxResponse();
        int i = roleService.updateUserRoleById(id, newRole);
        if(i>0) {
            Logs logs = new Logs();
            logs.setUsername((String) session.getAttribute("userName"));
            logs.setTime(new Date());
            logs.setDetails("更新了权限!");
            logsService.add(logs);
            ajaxResponse.setSuccessMessage("更新成功！", i);
        }else {
            ajaxResponse.setErrorMessage("更新失败！", i);
        }
        return ajaxResponse;
    }
    @RequestMapping("getOneUser")
    @ResponseBody
    public AjaxResponse getOneUser(String id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Map<String, Object> map = roleService.getOneUser(id);
            ajaxResponse.setSuccessMessage("查询成功！",map);
        } catch (Exception e) {
            ajaxResponse.setSuccessMessage("查询失败！",e);
            e.printStackTrace();
        }
        return ajaxResponse;
    }
    @RequestMapping("updateUserDepRole")
    @ResponseBody
    public AjaxResponse updateUserDepRole(String id,String departmentid,String leaderFlag){
        AjaxResponse ajaxResponse = new AjaxResponse();
        MUser mUser = new MUser();
        mUser.setId(id);
        mUser.setDepartmentid(departmentid);
        try {
            userService.updateUserInfo(mUser);
            if(leaderFlag.equals("1")) {
                int i=roleService.updateUserDepRole(departmentid, id);
            }
            ajaxResponse.setSuccessMessage("修改成功！",mUser);
        } catch (Exception e) {
            ajaxResponse.setSuccessMessage("修改失败！",mUser);
            e.printStackTrace();
        }
        return ajaxResponse;
    }

}
