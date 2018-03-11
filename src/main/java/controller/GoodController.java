package controller;

import Util.AjaxResponse;
import com.github.pagehelper.PageInfo;
import entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.GoodService;

import java.util.List;

/**
* Created by CodeGenerator on 2018/02/24.
*/
@Controller
@RequestMapping("/good")
public class GoodController {
    @Autowired
    private GoodService goodService;

    @ResponseBody
    @RequestMapping("/add")
    public AjaxResponse add(Good good) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            goodService.add(good);
            ajaxResponse.setState("200");
            ajaxResponse.setResult("OK");
            ajaxResponse.setMessage("添加商品类别成功!");
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("添加商品类别失败!");
        }
        return ajaxResponse;

    }

    @ResponseBody
    @RequestMapping("/delete")
    public AjaxResponse delete(Good good) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            goodService.delete(good);
            ajaxResponse.setState("200");
            ajaxResponse.setResult("OK");
            ajaxResponse.setMessage("删除商品类别成功!");
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("删除商品类别失败!");
        }
        return ajaxResponse;

    }
    @ResponseBody
    @RequestMapping("/update")
    public AjaxResponse update(Good good) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            goodService.update(good);
            ajaxResponse.setState("200");
            ajaxResponse.setResult("OK");
            ajaxResponse.setMessage("更新商品类别成功!");
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("更新商品类别失败!");
        }
        return ajaxResponse;

    }

    @RequestMapping("/get")
    @ResponseBody
    public AjaxResponse get(Integer page) {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            PageInfo<Good> pageInfo  =  goodService.get(page);
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

    @RequestMapping("/getAll")
    @ResponseBody
    public AjaxResponse getAll() {
        AjaxResponse ajaxResponse =new AjaxResponse();
        try {
            List<Good> list= goodService.getAll();
            ajaxResponse.setState("200");
            ajaxResponse.setResult(list);
            ajaxResponse.setMessage("获取商品类别成功!");
        }catch (Exception e){
            ajaxResponse.setState("404");
            ajaxResponse.setResult("error");
            ajaxResponse.setMessage("获取商品类别失败!");
        }
        return ajaxResponse;

    }
}
