package controller.index;

import Util.AjaxResponse;
import entity.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.index.IndexService;

import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月14日 14:42
 **/
@Controller
@RequestMapping("index")
public class IndexController {
    @Autowired
    IndexService indexService;

    /**
     * 查询各种病历的数量
     * @return
     */
    @ResponseBody
    @RequestMapping("listCountRecord")
    public AjaxResponse listCountRecord(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        List<Map<String, Object>> mapList = indexService.countRecordByState();
        try {
            ajaxResponse.setSuccessMessage("查询各类型病历总和成功!",mapList);
        } catch (Exception e) {
            ajaxResponse.setSuccessMessage("查询各类型病历总和失败!"+e,mapList);
        }
        return ajaxResponse;
    }

    /**
     * 查询首页医生
     * @return
     */
    @ResponseBody
    @RequestMapping("listSomeDoctor")
    public AjaxResponse listSomeDoctor(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        List<Map<String,Object>> mUsers = indexService.listSomeDoctor();
        try {
            ajaxResponse.setSuccessMessage("查询首页医生成功!",mUsers);
        } catch (Exception e) {
            ajaxResponse.setSuccessMessage("查询首页护士失败!"+e,mUsers);
        }
        return ajaxResponse;
    }

    /**
     * 查询首页护士
     * @return
     */
    @ResponseBody
    @RequestMapping("listSomeNurses")
    public AjaxResponse listSomeNurses(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        List<MUser> mUsers = indexService.listSomeNurse();
        try {
            ajaxResponse.setSuccessMessage("查询首页护士成功!",mUsers);
        } catch (Exception e) {
            ajaxResponse.setSuccessMessage("查询首页护士失败!"+e,mUsers);
        }
        return ajaxResponse;
    }
}
