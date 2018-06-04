package repository.doctor;

import entity.MPatient;
import entity.MUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * The interface Doctor mapper.
 *
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月16日 08:47
 */
@Repository
public interface DoctorMapper extends BaseMapper<MUser> {
    /**
     * List all patient list.
     * 查询所有未出院病人
     *
     * @return the list
     */
    @Select("SELECT * FROM m_patient WHERE precure = 1 and")
    List<MPatient> listAllPatient();

    /**
     * Gets one  patient.
     * 根据id 查询病人
     *
     * @param id the id
     * @return the one patient
     */
    @Select("SELECT\n" +
            "\t*,\n" +
            "CASE\n" +
            "\tpsex \n" +
            "\tWHEN 1 THEN\n" +
            "\t'男' ELSE '女' \n" +
            "\tEND AS sex\n" +
            "FROM\n" +
            "\t`m_patient` \n" +
            "WHERE\n" +
            "\tprecure =1 AND id=#{id}")
    Map<String,Object> getOnePatientById(int id);

    /**
     * List all record list.
     * 查询所有的record 关联查询patient表
     *
     * @param departmentId the department id
     * @return the list
     */
    @Select("SELECT\n" +
            "\t*,\n" +
            "\td.d_name,\n" +
            "CASE\n" +
            "\trstate \n" +
            "\tWHEN 1 THEN\n" +
            "\t'未封存' \n" +
            "\tWHEN 2 THEN\n" +
            "\t'已封存' \n" +
            "\tWHEN 3 THEN\n" +
            "\t'未通过' ELSE '未知'\n" +
            "END AS state,\n" +
            "\tCASE\n" +
            "\t\trpass \n" +
            "\t\tWHEN 0 THEN\n" +
            "\t\t'未审核' \n" +
            "\t\tWHEN 1 THEN\n" +
            "\t\t'已通过' \n" +
            "\t\tWHEN 2 THEN\n" +
            "\t\t'未通过' ELSE '未知'\n" +
            "\tEND AS pass\n" +
            "FROM\n" +
            "\t`m_record` r\n" +
            "\tINNER JOIN m_patient p ON p.id = r.pid\n" +
            "\tLEFT JOIN m_department d ON d.id = r.rdepartment WHERE r.rdepartment=#{departmentId} AND r.rpass!= 1")
    List<Map<String, Object>> listAllRecord(String departmentId);


    /**
     * 根据用户 查询用户的部门
     *
     * @param userName the user name
     * @return the user department
     */
    @Select("SELECT u.departmentid FROM m_login l INNER JOIN m_user u ON l.id=u.loginid WHERE l.username=#{userName}")
    String getUserDepartment(String userName);

    /**
     * Is record exist int.
     *
     * @param pid the pid
     * @return the int
     */
    @Select("SELECT COUNT(*) FROM m_record WHERE pid=#{pid}")
    int isRecordExist(String pid);

    @Delete("DELETE FROM m_remind WHERE id=#{id}")
    int deleteWorkRemind(String id);
}
