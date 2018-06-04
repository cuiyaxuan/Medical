package controller;

import Util.AjaxResponse;
import Util.CosUtil;
import entity.Login;
import entity.Logs;
import entity.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LoginService;
import service.logs.LogsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author WangXinYu
 * @ Author : dell on 2018/2/24.
 * Date :  Created in  10:38.   2018/2/24.
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
    public AjaxResponse login(Login login, HttpServletRequest request) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        Login returnLogin = loginService.getUser(login);
        if (login.getUsername().equals(returnLogin.getUsername()) && login.getPassword().equals(returnLogin.getPassword())) {
            try {
                ajaxResponse.setSuccessMessage("登录成功!",returnLogin);
            } catch (Exception e) {
                ajaxResponse.setErrorMessage("登录失败!"+e,returnLogin);
            }
            session.setAttribute("loginUser", returnLogin);
            session.setAttribute("userName", login.getUsername());
            session.setAttribute("id", returnLogin.getId());
            Logs logs = new Logs();
            logs.setUsername(login.getUsername());
            logs.setTime(new Date());
            logs.setDetails(login.getUsername()+"登录了系统!");
            logsService.add(logs);
        }
        String s = session.getAttribute("userName").toString();
        return ajaxResponse;
    }


    @ResponseBody
    @RequestMapping("/test")
    public AjaxResponse test(HttpServletRequest request) throws Exception {
        return CosUtil.uploadImgCloudBase64(request);
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public AjaxResponse getRole(HttpServletRequest request) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        String id = session.getAttribute("id").toString();
        MUser mUser=loginService.getUserInfo(id);
        try {
            ajaxResponse.setSuccessMessage("查询用户信息成功!",mUser);
        } catch (Exception e) {
            ajaxResponse.setErrorMessage("查询用户信息成功!"+e,mUser);
        }
        return ajaxResponse;
    }

    /**
     * 验证旧密码是否正确
     * @param pwd
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/getPwd")
    public AjaxResponse getPwd(String pwd,HttpServletRequest request) throws Exception {
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        String id =session.getAttribute("id").toString();
        String dataBasePwd = loginService.getLoginPwd(id);
        if(dataBasePwd.equals(pwd)) {
            ajaxResponse.setSuccessMessage("旧密码输入正确！", pwd);
        }else {
            ajaxResponse.setErrorMessage("旧密码输入错误!请重新输入！", pwd);
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping("/updatePwd")
    public AjaxResponse updatePwd(String pwd,HttpServletRequest request) throws Exception {
        AjaxResponse ajaxResponse = new AjaxResponse();
        HttpSession session = request.getSession(true);
        String id =session.getAttribute("id").toString();
        Login login = new Login();
        login.setId(Long.parseLong(id));
        login.setPassword(pwd);
        int i = loginService.updatePwd(login);
        if(i>0) {
            ajaxResponse.setSuccessMessage("修改密码成功！", pwd);
        }else {
            ajaxResponse.setErrorMessage("修改密码失败！", pwd);
        }
        return ajaxResponse;
    }

}
