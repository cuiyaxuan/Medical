package repository;

import entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * @ Author : dell on 2018/2/24.
 * Date :  Created in  10:39.   2018/2/24.
 */
@Repository
public interface LoginMapper {

    @Select(" select * from  user ")
    List<User> getAllUser();
}
