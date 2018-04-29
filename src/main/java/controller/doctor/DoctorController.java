package controller.doctor;

import Util.AjaxResponse;
import entity.MPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.doctor.DoctorService;

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
}
