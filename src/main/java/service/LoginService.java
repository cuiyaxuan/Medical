package service;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LoginMapper;

import java.util.List;


/**
 * @ Author : dell on 2018/2/24.
 * Date :  Created in  10:39.   2018/2/24.
 */
@Service
public class LoginService {
    @Autowired
    private  LoginMapper loginMapper;
    public List<User> getAllUser(){
        List<User> list =loginMapper.getAllUser();
        return list;
    }
}
