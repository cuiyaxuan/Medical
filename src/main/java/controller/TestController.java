package controller;

import Util.AjaxResponse;
import Util.Excel;
import entity.Data;
import entity.Logs;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import repository.DataMapper;
import service.DataService;
import service.LogsService;
import service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Test")
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    LogsService logsService;
    @Autowired
    DataService dataService;

    @RequestMapping("/test")
    @ResponseBody
    public  String selectName(int id){
        return testService.selectNameByID(id);
    }
    @RequestMapping("/test111")
    @ResponseBody
    public  String test ( HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        Logs logs = new Logs();
        logs.setDetails("测试一下session");
        logs.setTime(new Date());
        logs.setUsername(user.getName());
        logsService.add(logs);
        return "OK";
    }

    @RequestMapping(value = "/importVilla")
    public void importForeignHouseData(MultipartFile file ) {

        boolean result=false;
        boolean resultMin=false;
        try {
            InputStream is = file.getInputStream();
            List<List<List<String>>> list= Excel.excelToForeignHouseExport(is);
            testService.insertData(list);
            int a =0;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(result&&resultMin){


        }else{

        }
    }

    @RequestMapping("TestPage")
    @ResponseBody
    public AjaxResponse TestPage (){

    AjaxResponse ajaxResponse =new AjaxResponse();
    ajaxResponse.setResult( dataService.get(3));
    return ajaxResponse;
    }
    @RequestMapping("TestWhy")
    @ResponseBody
    public AjaxResponse TestWhy (){

        AjaxResponse ajaxResponse =new AjaxResponse();
        Data data = new Data();
        data.setGoodid(9);
        data.setXxxlNumber(0);
        data.setXxlNumber(0);
        data.setXlNumber(0);
        data.setlNumber(0);
        data.setmNumber(0);
        data.setsNumber(0);
        data.setPlayerName("aaa");
        data.setColor("blue");
        data.setTeam("WE");
        data.setMeno("00");
        data.setNo("TX");
        dataService.add(data);
        return ajaxResponse;
    }

}
