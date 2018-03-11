package controller;
import Util.AjaxResponse;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/02/24.
*/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/add")
    @ResponseBody
    public AjaxResponse add(User user) {
        AjaxResponse ajaxResponse =new AjaxResponse();

        try {
            userService.add(user);
        }catch (Exception e){
            ajaxResponse.setMessage("添加用户失败!");
        }
        return ajaxResponse;
    }
}
