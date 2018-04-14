package repository.role;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * The interface Role mapper.
 *
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月13日 10:30
 */
@Repository
public interface RoleMapper {
    /**
     * List all user list.
     * 查询所有的用户以及他的所属部门
     *
     * @return the list
     */
    @Select("SELECT u.* ,CASE u.sex WHEN '1' THEN '男' ELSE '女' END as sex," +
            "CASE u.type WHEN '1' THEN '医生' WHEN '2' THEN '护士' ELSE '未知' END as csType ," +
            "CASE u.role WHEN '1' THEN '普通用户' WHEN '2' THEN '管理员' ELSE '未知' END as csRole ," +
            "d.d_name FROM `m_user` u " +
            "LEFT JOIN m_department d on u.departmentid=d.id")
    List<Map<String, Object>> listAllUser();

    /**
     * Update user role byid int.
     *  修改用户权限
     * @param id   the id
     * @param role the role
     * @return the int
     */
    @Update("UPDATE m_user u SET u.role=#{role} WHERE u.id= #{id}")
    int updateUserRoleByid(@Param("id") int id, @Param("role") int role);
}
