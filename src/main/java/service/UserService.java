package service;


import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserMapper;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/02/24.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public  void  add (User user){
        userMapper.insert(user);
    }

}
