package controller.nurse;

import Util.AjaxResponse;
import entity.MNurseDocumentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.nurse.NurseService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月16日 08:45
 **/
@Controller
@RequestMapping("nurse")
public class NurseController {
    @Autowired
    NurseService nurseService;

    /**
     * 查询所有护理文书
     * @return
     */
    @ResponseBody
    @RequestMapping("listAllDocument")
    public AjaxResponse listAllDocument() {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            List<Map<String, Object>> mapList = nurseService.listAllDocument();
            ajaxResponse.setSuccessMessage("查询所有护理文书成功！", mapList);
        } catch (Exception e) {
            ajaxResponse.setSuccessMessage("查询所有护理文书成功！", e);
            e.printStackTrace();
        }
        return ajaxResponse;
    }
    /**
     * 根据id查询护理文书
     * @return
     */
    @ResponseBody
    @RequestMapping("getDocumentById")
    public AjaxResponse getDocumentById(int id) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            Map<String, Object> map = nurseService.getDocumentById(id);
            ajaxResponse.setSuccessMessage("根据id查询护理文书成功！", map);
        } catch (Exception e) {
            ajaxResponse.setSuccessMessage("根据id查询护理文书成功！", e);
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    /**
     * 添加护理文书
     * @return
     */
    @ResponseBody
    @RequestMapping("add")
    public AjaxResponse add(MNurseDocumentation mNurseDocumentation) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            mNurseDocumentation.setGmtCreate(date);
            int i = nurseService.add(mNurseDocumentation);
            ajaxResponse.setSuccessMessage("根据id查询护理文书成功！", i);
        } catch (Exception e) {
            ajaxResponse.setSuccessMessage("根据id查询护理文书成功！", e);
            e.printStackTrace();
        }
        return ajaxResponse;
    }
    /**
     * 添加护理文书
     * @return
     */
    @ResponseBody
    @RequestMapping("update")
    public AjaxResponse update(MNurseDocumentation mNurseDocumentation) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(new Date());
            mNurseDocumentation.setGmtCreate(date);
            int i = nurseService.update(mNurseDocumentation);
            ajaxResponse.setSuccessMessage("根据id查询护理文书成功！", i);
        } catch (Exception e) {
            ajaxResponse.setSuccessMessage("根据id查询护理文书成功！", e);
            e.printStackTrace();
        }
        return ajaxResponse;
    }
}
