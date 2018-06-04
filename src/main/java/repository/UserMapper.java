package repository;

import entity.MUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Map;

/**
 * The interface User mapper.
 *
 * @author wxy
 */
@Repository
public interface UserMapper extends BaseMapper<MUser> {
    /**
     * Gets user info.
     *
     * @param loginId the login id
     * @return the user info
     */
    @Select("SELECT u.*,d.d_name,d.d_leader FROM `m_user` u LEFT JOIN m_department d on d.id=u.departmentid WHERE loginid = #{loginId}")
    Map<String, Object> getUserInfo(String loginId);

    /**
     * Add login int.
     *
     * @param userName the user name
     * @return the int
     */
    @Insert("INSERT INTO m_login (username,password) VALUES (#{userName}, 123456)")
    int addLogin(String userName);

    /**
     * Gets user by login id.
     *
     * @param userName the user name
     * @return the user by login id
     */
    @Select("SELECT id FROM m_login WHERE username=#{userName}")
    int getLoginIdByUserName(String userName);

    @Select("SELECT u.*,d.d_name,d.d_leader FROM `m_user` u LEFT JOIN m_department d on d.id=u.departmentid WHERE u.id = #{id}")
    Map<String, Object> getUserInfoById(String id);

    @Update("UPDATE m_user SET score=score+1 WHERE loginid=#{loginid} ")
    int addScore(String loginid);
}