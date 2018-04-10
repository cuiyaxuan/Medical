package repository;

import entity.Login;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author WangXinYu
 * @ Author : dell on 2018/2/24.
 * Date :  Created in  10:39.   2018/2/24.
 */
@Repository
public interface LoginMapper {

    /**
     * Gets all user.
     *
     * @return the all user
     */
    @Select(" select * from  m_login ")
    List<Login> getAllUser();
}
