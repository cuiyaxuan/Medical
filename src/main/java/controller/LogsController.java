package controller;

import Util.AjaxResponse;
import com.github.pagehelper.PageInfo;
import entity.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LogsService;

import java.util.List;

/**
* Created by CodeGenerator on 2018/02/24.
*/
@Controller
@RequestMapping("/logs")
public class LogsController {
    @Autowired
    private LogsService logsService;
    @ResponseBody
    @RequestMapping("/get")
    public AjaxResponse get(Integer page) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            PageInfo<Logs> pageInfo  =  logsService.get(page);
            ajaxResponse.setState("200");
            ajaxResponse.setResult(pageInfo);
            ajaxResponse.setMessage("获取商品类别成功!");
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("获取商品类别失败!");
        }
        return ajaxResponse;

    }

    @ResponseBody
    @RequestMapping("/getAll")
    public AjaxResponse getAll() {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            List<Logs> logsList =  logsService.getAll();
            ajaxResponse.setState("200");
            ajaxResponse.setResult(logsList);
            ajaxResponse.setMessage("获取商品类别成功!");
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("获取商品类别失败!");
        }
        return ajaxResponse;

    }
}
