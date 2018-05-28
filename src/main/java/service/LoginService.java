package service;

import entity.Login;
import entity.MUser;
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
    public List<Login> getAllUser(){
        List<Login> list =loginMapper.getAllUser();
        return list;
    }
    public Login getUser(Login login){
        String userName = login.getUsername();
        Login login1 =loginMapper.getUserByName(userName);
        return login1;
    }
    public MUser getUserInfo (String id){
        MUser mUser = loginMapper.getUserInfo(id);
        return mUser;
    }
}
