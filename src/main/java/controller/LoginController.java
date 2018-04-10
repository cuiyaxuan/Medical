package controller;

import Util.AjaxResponse;
import entity.Login;
import entity.Logs;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LoginService;
import service.LogsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @ Author : dell on 2018/2/24.
 * Date :  Created in  10:38.   2018/2/24.
 * @author WangXinYu
 */
@Controller
@RequestMapping("Login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LogsService logsService;
    @ResponseBody
    @RequestMapping("login")
    public AjaxResponse create(Login login, HttpServletRequest request) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        ajaxResponse.setState("200");
        ajaxResponse.setResult("NO");
        List<Login> list = loginService.getAllUser();
        for (Login login_ : list
                ) {
            if (login.getUsername().equals(login_.getUsername()) && login.getPassword().equals(login_.getPassword())) {
                ajaxResponse.setState("200");
                ajaxResponse.setResult("OK");
                ajaxResponse.setMessage("1");
                session.setAttribute("loginUser", login_);
                Logs logs = new Logs();
                logs.setUsername(login.getUsername());
                logs.setTime(new Date());
                logs.setDetails("登陆了系统!");
                logsService.add(logs);
                break;
            }
        }
        return ajaxResponse;
    }




    @ResponseBody
    @RequestMapping("test")
    public AjaxResponse test(  ) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setResult("OK");
        ajaxResponse.setMessage("me");

        return  ajaxResponse;
    }

    @RequestMapping("/getRole")
    @ResponseBody
    public AjaxResponse getRole(HttpServletRequest request){
        AjaxResponse ajaxResponse=new AjaxResponse();
        HttpSession session = request.getSession(true);
        User user= (User) session.getAttribute("loginUser");
        String role=user.getRole();
        ajaxResponse.setResult(role);
        return ajaxResponse;
    }

}
