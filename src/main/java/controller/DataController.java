package controller;

import Util.AjaxResponse;
import com.github.pagehelper.PageInfo;
import entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DataService;

import java.util.List;


/**
* Created by CodeGenerator on 2018/02/24.
*/
@Controller
@RequestMapping("Data")
public class DataController {
    @Autowired
    DataService dataService;


    @ResponseBody
    @RequestMapping("/add")
    public AjaxResponse add(Data data) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            dataService.add(data);
            ajaxResponse.setState("200");
            ajaxResponse.setResult("OK");
            ajaxResponse.setMessage("添加商品成功!");
            }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("添加商品失败!");
            }
            return ajaxResponse;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResponse delete(Data data) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            dataService.delete(data);
            ajaxResponse.setState("200");
            ajaxResponse.setResult("OK");
            ajaxResponse.setMessage("删除商品成功!");
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("删除商品失败!");
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("/update")
    public AjaxResponse update(Data data) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            dataService.update(data);
            ajaxResponse.setState("200");
            ajaxResponse.setResult("OK");
            ajaxResponse.setMessage("更新商品成功!");
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("更新商品失败!");
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("/list")
    public AjaxResponse list( Integer page) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            PageInfo<Data> pageInfo = dataService.get(page);
            ajaxResponse.setState("200");
            ajaxResponse.setMessage("更新商品成功!");
            ajaxResponse.setResult(pageInfo);
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("更新商品失败!");
        }
        return ajaxResponse;
    }
    @ResponseBody
    @RequestMapping("/listAll")
    public AjaxResponse listAll() {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            List<Data> list = dataService.getAll();
            ajaxResponse.setState("200");
            ajaxResponse.setMessage("查询商品成功!");
            ajaxResponse.setResult(list);
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("查询商品失败!");
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping("/listDataByID")
    public AjaxResponse listDataByID(String id) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            Data data = dataService.getDataByID(id);
            ajaxResponse.setState("200");
            ajaxResponse.setMessage("查询单个商品成功!");
            ajaxResponse.setResult(data);
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("查询单个商品失败!");
        }
        return ajaxResponse;
    }
}
