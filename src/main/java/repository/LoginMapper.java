package repository;

import entity.Login;
import entity.MUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;


/**
 * The interface Login mapper.
 *
 * @author WangXinYu @ Author : dell on 2018/2/24. Date :  Created in  10:39.   2018/2/24.
 */
@Repository
public interface LoginMapper extends BaseMapper<Login> {

    /**
     * Gets all user.
     *
     * @return the all user
     */
    @Select(" select * from  m_login ")
    List<Login> getAllUser();

    /**
     * Gets user by name.
     *
     * @param userName the user name
     * @return the user by name
     */
    @Select(" select * from  m_login where username=#{userName}")
    Login getUserByName(String userName);

    /**
     * Gets user info.
     *
     * @param id the id
     * @return the user info
     */
    @Select(" select * from  m_user where loginid=#{id}")
    MUser getUserInfo(String id);

    /**
     * Gets login pwd.
     *
     * @param id the id
     * @return the login pwd
     */
    @Select("SELECT password FROM m_login WHERE id=#{id}")
    String getLoginPwd(String id);
}
