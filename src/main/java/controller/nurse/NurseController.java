package controller.nurse;

import Util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.nurse.NurseService;

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
}
