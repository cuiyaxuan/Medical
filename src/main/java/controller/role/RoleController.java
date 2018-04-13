package controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月13日 10:20
 **/
@Controller
@RequestMapping("role")
public class RoleController {

    @RequestMapping("listUserRole")
    @ResponseBody
    public void listUserRole(){

    }
}
