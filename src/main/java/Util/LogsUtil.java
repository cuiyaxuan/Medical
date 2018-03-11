package Util;

import entity.Logs;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import service.LogsService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @ Author : dell on 2018/2/26.
 * Date :  Created in  9:34.   2018/2/26.
 */
public class LogsUtil  {
     LogsService logsService;
    public  static Logs Logs (String details){
        Logs logs = new Logs();
        logs.setDetails(details);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        logs.setTime(new Date());
        logs.setUsername(user.getName());
        return logs;
    }
}
