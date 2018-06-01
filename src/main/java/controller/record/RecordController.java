package controller.record;

import Util.AjaxResponse;
import entity.MRemind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import service.record.RecordService;
import service.remind.RemindService;

import java.util.List;
import java.util.Map;

import static Util.DateUtils.getNowDateString;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月14日 21:57
 **/
@Controller
@RequestMapping("record")
public class RecordController {
    @Autowired
    RecordService recordService;
    @Autowired
    RemindService remindService;
    @Autowired
    UserService userService;

    /**
     * 在主页显示 今日已封存病历
     * @return
     */
    @RequestMapping("listTodaySignedRecord")
    @ResponseBody
    public AjaxResponse listTodaySignedRecord(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<Map<String, Object>> mapList = recordService.listTodaySignedRecord();
            ajaxResponse.setSuccessMessage("查询今日已封存病历成功！", mapList);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询今日已封存病历失败!", e);
        }
        return ajaxResponse;
    }
    /**
     * 在主页显示 今日未封存病历
     * @return
     */
    @RequestMapping("listTodayNotSignedRecord")
    @ResponseBody
    public AjaxResponse listTodayNotSignedRecord(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<Map<String, Object>> mapList = recordService.listTodayNotSignedRecord();
            ajaxResponse.setSuccessMessage("查询今日未封存病历成功！", mapList);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询今日未封存病历失败", e);
        }

        return ajaxResponse;
    }
    /**
     * 病例浏览查询显示所有病历
     * @return
     */
    @RequestMapping("listAllRecord")
    @ResponseBody
    public AjaxResponse listAllRecord(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<Map<String, Object>> mapList = recordService.listAllRecord();
            ajaxResponse.setSuccessMessage("查询所有病历成功！", mapList);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询所有病历失败！", e);
        }

        return ajaxResponse;
    }
    /**
     * 病历封存查询显示所有病历
     * @return
     */
    @RequestMapping("listAllRecordByState")
    @ResponseBody
    public AjaxResponse listAllRecordByState(int state){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<Map<String, Object>> mapList = recordService.listAllRecordByState(state);
            ajaxResponse.setSuccessMessage("查询所有病历成功！", mapList);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询所有病历失败！", e);
        }

        return ajaxResponse;
    }
    /**
     * 病历封存 查询单个病历
     * @return
     */
    @RequestMapping("getRecordById")
    @ResponseBody
    public AjaxResponse getRecordById(int id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Map<String, Object> map = recordService.getRecordById(id);
            ajaxResponse.setSuccessMessage("查询病历成功！", map);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询病历失败！", e);
        }

        return ajaxResponse;
    }

    /**
     * 根据id封存病历
     * @return
     */
    @RequestMapping("sealRecordById")
    @ResponseBody
    public AjaxResponse sealRecordById(int id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int i = recordService.updateSealRecordById(id);
            ajaxResponse.setSuccessMessage("封存病历成功！", i);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("封存病历失败！", e);
        }

        return ajaxResponse;
    }
    /**
     * 根据id解封病历
     * @return
     */
    @RequestMapping("deBlockRecordById")
    @ResponseBody
    public AjaxResponse deBlockRecordById(int id,String userLoginId){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int i = recordService.updateDeBlockRecordById(id);
            if(i>0) {
                Map<String,Object> map=userService.getUserInfo(userLoginId);
                String userId =map.get("id").toString();
                MRemind mRemind = new MRemind();
                mRemind.setMessage("提交的病历被解封，请重新封存！");
                mRemind.setGmtCreate(getNowDateString());
                mRemind.setUserid(Long.parseLong(userId));
                remindService.insert(mRemind);
                ajaxResponse.setSuccessMessage("解封病历成功！", i);
            }

        } catch (Exception e) {
            ajaxResponse.setErrorMessage("解封病历失败！", e);
        }

        return ajaxResponse;
    }
    /**
     * 根据id通过病历
     * @return
     */
    @RequestMapping("passRecordById")
    @ResponseBody
    public AjaxResponse passRecordById(int id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int i = recordService.passRecordById(id);
            ajaxResponse.setSuccessMessage("通过病历成功！", i);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("通过病历失败！", e);
        }

        return ajaxResponse;
    }
    /**
     * 根据id通过病历
     * @return
     */
    @RequestMapping("rejectRecordById")
    @ResponseBody
    public AjaxResponse rejectRecordById(int id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int i = recordService.rejectRecordById(id);
            ajaxResponse.setSuccessMessage("拒绝病历成功！", i);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("拒绝病历失败！", e);
        }

        return ajaxResponse;
    }

}
