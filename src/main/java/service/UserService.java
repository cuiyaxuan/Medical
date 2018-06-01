package service;


import entity.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserMapper;

import java.util.Map;


/**
 *
 * @author WangXinYu
 * @date 2018/02/24
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

    public int updateUserInfo(MUser mUser){
        int i = userMapper.updateByPrimaryKeySelective(mUser);
        return i;
    }
    public int insert(String userName,MUser mUser){
        int i = userMapper.addLogin(userName);
        int flag = 0;
        if(i>0) {
            int id = userMapper.getLoginIdByUserName(userName);
            mUser.setLoginid(String.valueOf(id));
            mUser.setScore("0");
            mUser.setRole("1");
            mUser.setRealname(userName);
            userMapper.insert(mUser);
            flag = 1;
        }
        return flag;
    }
    public Map<String,Object> getUserInfoByid(String id){
        Map<String, Object> map = userMapper.getUserInfoById(id);
        return map;
    }

}
