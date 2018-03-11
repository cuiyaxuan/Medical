package controller;

import Util.AjaxResponse;
import Util.LogsUtil;
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
 */
@Controller
@RequestMapping("Login")
public class loginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LogsService logsService;
    @ResponseBody
    @RequestMapping("login")
    public AjaxResponse create(User user,HttpServletRequest request) {
      AjaxResponse ajaxResponse = new AjaxResponse();
      HttpSession session = request.getSession(true);
        ajaxResponse.setState("200");
        ajaxResponse.setResult("NO");
      List<User> list =loginService.getAllUser();
        for (User user_ : list
             ) {
            if (user.getName().equals(user_.getName())&&user.getPassword().equals(user_ .getPassword())){
                ajaxResponse.setState("200");
                ajaxResponse.setResult("OK");
                ajaxResponse.setMessage(user_.getRole());
               session.setAttribute("loginUser",user_ );
                Logs logs =new Logs();
                logs.setUsername(user.getName());
                logs.setTime(new Date());
                logs.setDetails("登陆了系统!");
               logsService.add(logs);
                break;
            }
        }
      return  ajaxResponse;
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
