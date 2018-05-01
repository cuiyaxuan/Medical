package controller.doctor;

import Util.AjaxResponse;
import entity.MPatient;
import entity.MRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.doctor.DoctorService;
import service.record.RecordService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月16日 08:44
 **/
@Controller
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @Autowired
    RecordService recordService;
    /**
     * 查询所有的病人
     * @return
     */
    @ResponseBody
    @RequestMapping("listAllPatient")
    public AjaxResponse listAllPatient(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<MPatient> mPatients = doctorService.listAllPatient();
            ajaxResponse.setSuccessMessage("查询所有病人成功",mPatients);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询所有病人失败！", e);
        }
        return ajaxResponse;
    }
    /**
     * 根据id查询病人
     * @return
     */
    @ResponseBody
    @RequestMapping("getOnePatientById")
    public AjaxResponse getOnePatientById(int id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Map<String,Object> mPatient = doctorService.getOnePatientById(id);
            ajaxResponse.setSuccessMessage("查询单个病人成功",mPatient);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询单个病人失败！", e);
        }
        return ajaxResponse;
    }

    /**
     * 新增 病人
     * @return
     */
    @ResponseBody
    @RequestMapping("add")
    public AjaxResponse add(MPatient patient){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int i = doctorService.add(patient);
            ajaxResponse.setSuccessMessage("新增病人成功", i);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("新增病人失败！", e);
        }
        return ajaxResponse;
    }
    /**
     * 更新 病人
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public AjaxResponse update(MPatient patient){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int i = doctorService.update(patient);
            ajaxResponse.setSuccessMessage("更新病人成功", i);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("更新病人失败！", e);
        }
        return ajaxResponse;
    }

    /**
     * 删除 病人 update precure
     * @return
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResponse delete(String id){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int i = doctorService.delete(id);
            ajaxResponse.setSuccessMessage("病人出院成功！", i);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("病人出院失败！", e);
        }
        return ajaxResponse;
    }

    /**
     * 医生工作站 显示所有病历 个人科室的所有病例
     * @return
     */
    @RequestMapping("listAllRecord")
    @ResponseBody
    public AjaxResponse listAllRecord(HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        String userName = session.getAttribute("userName").toString();
        try {
            List<Map<String, Object>> mapList = doctorService.listAllRecord(userName);
            ajaxResponse.setSuccessMessage("查询所有病历成功！", mapList);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询所有病历失败！", e);
        }

        return ajaxResponse;
    }

    /**
     * 医生工作站 显示所有病历 个人科室的所有病例
     * @return
     */
    @RequestMapping("getOneRecordByPid")
    @ResponseBody
    public AjaxResponse getOneRecordById(String id,HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Map<String,Object> obj = doctorService.getOneRecordById(id);
            ajaxResponse.setSuccessMessage("查询病历成功！", obj);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询病历失败！", e);
        }

        return ajaxResponse;
    }

    /**
     * 医生工作站 修改病历 个人科室的所有病例
     * @return
     */
    @RequestMapping("updateRecord")
    @ResponseBody
    public AjaxResponse updateRecord(MRecord mRecord,HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            mRecord.setGmtCreate(date);
            int i = doctorService.updateRecord(mRecord);
            ajaxResponse.setSuccessMessage("修改病历成功！", i);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("修改病历失败！", e);
        }
        return ajaxResponse;
    }

    /**
     * 医生工作站 新增病历
     * @return
     */
    @RequestMapping("addRecord")
    @ResponseBody
    public AjaxResponse addRecord(MRecord mRecord,HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int i = doctorService.addRecord(mRecord);
            ajaxResponse.setSuccessMessage("新增病历成功！", i);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("新增病历失败！", e);
        }
        return ajaxResponse;
    }

    /**
     * 查询该用户的所有工作提醒
     * @return
     */
    @RequestMapping("listWorkMind")
    @ResponseBody
    public AjaxResponse listWorkMind(HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            HttpSession session = request.getSession(true);
            String id = session.getAttribute("id").toString();
            List<Map<String, Object>> mapList = doctorService.listWorkMind(id);
            ajaxResponse.setSuccessMessage("查询工作提醒成功！", mapList);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询工作提醒失败！", e);
        }
        return ajaxResponse;
    }

    /**
     * 通过id删除工作提醒
     * @return
     */
    @RequestMapping("deleteWorkMind")
    @ResponseBody
    public AjaxResponse deleteWorkMind(String id,HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            int i = doctorService.deleteRemind(id);
            ajaxResponse.setSuccessMessage("查询工作提醒成功！", i);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询工作提醒失败！", e);
        }
        return ajaxResponse;
    }
}
