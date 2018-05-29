package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserMapper;

import java.util.Map;


/**
 * Created by CodeGenerator on 2018/02/24.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Map<String,Object> getUserInfo(String loginId){
        Map<String, Object> map = userMapper.getUserInfo(loginId);
        return map;
    }

}
