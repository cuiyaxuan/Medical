package repository;

import entity.MUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.Map;

@Repository
public interface UserMapper  extends BaseMapper<MUser> {
    @Select("SELECT u.*,d.d_name,d.d_leader FROM `m_user` u LEFT JOIN m_department d on d.id=u.departmentid WHERE loginid = #{loginId}")
    Map<String, Object> getUserInfo(String loginId);
}