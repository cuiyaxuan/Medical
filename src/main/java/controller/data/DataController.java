package controller.data;

import Util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.data.DataService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年05月02日 08:12
 **/
@Controller
@RequestMapping("data")
public class DataController {
    @Autowired
    DataService dataService;

    @ResponseBody
    @RequestMapping("getDepartmentOneYearData")
    public AjaxResponse getDepartmentOneYearData(String year, HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
        try {
            Map<String,Object> map = dataService.getDefaultState(id, year);
            ajaxResponse.setSuccessMessage("查询数据成功！",map);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询数据失败！", e);
        }
        return ajaxResponse;
    }
    /*******************************传染病统计页面开始**************************************/
    @ResponseBody
    @RequestMapping("listAllInfectionData")
    public AjaxResponse listAllInfectionData(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<Integer> list=dataService.listAllInfectionData();
            ajaxResponse.setSuccessMessage("查询数据成功！",list);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询数据失败！", e);
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("listAllInfectionDataByDepartment")
    public AjaxResponse listAllInfectionDataByDepartment(String departmentId){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<Integer> list=dataService.listAllInfectionDataByDepartment(departmentId);
            ajaxResponse.setSuccessMessage("查询数据成功！",list);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询数据失败！", e);
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("listAllMonthTop")
    public AjaxResponse listAllMonthTop(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String,Object>> list=dataService.listAllMonthTop();
            ajaxResponse.setSuccessMessage("查询数据成功！",list);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询数据失败！", e);
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("getMonthTop")
    public AjaxResponse getMonthTop(String departmentId){
        AjaxResponse ajaxResponse = new AjaxResponse();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Map<String,Object>> list=dataService.getMonthTop(departmentId);
            ajaxResponse.setSuccessMessage("查询数据成功！",list);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询数据失败！", e);
        }
        return ajaxResponse;
    }
    /*******************************传染病统计页面结束**************************************/

    @ResponseBody
    @RequestMapping("getRecordData")
    public AjaxResponse getRecordData(String departmentId,String year, HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Map<String,Object> map = dataService.getRecordData(departmentId, year);
            ajaxResponse.setSuccessMessage("查询数据成功！",map);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询数据失败！", e);
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("listRecordData")
    public AjaxResponse listRecordData(String year, HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Map<String,Object> map = dataService.listRecordData(year);
            ajaxResponse.setSuccessMessage("查询数据成功！",map);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询数据失败！", e);
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("listPatientMonthDataBySex")
    public AjaxResponse listPatientMonthDataBySex(String departmentId,String page,String psex, HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<Integer> list = dataService.listPatientMonthDataBySex(departmentId, page, psex);
            ajaxResponse.setSuccessMessage("查询数据成功！",list);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询数据失败！", e);
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("listAllPatientMonthData")
    public AjaxResponse listAllPatientMonthData(HttpServletRequest request){
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<Integer> list = dataService.listAllPatientMonthData();
            ajaxResponse.setSuccessMessage("查询数据成功！",list);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询数据失败！", e);
        }
        return ajaxResponse;
    }
}
