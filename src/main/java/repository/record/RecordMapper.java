package repository.record;

import entity.MRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * The interface Record mapper.
 *
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月14日 21:58
 */
@Repository
public interface RecordMapper extends BaseMapper<MRecord> {
    /**
     * List today signed record list.
     * 查询今日已封存病历
     *
     * @param nowDateString the now date string
     * @return the list
     */
    @Select("SELECT * FROM m_record WHERE rstate = 2 AND gmt_create = #{nowDateString}")
    List<Map<String, Object>> listTodaySignedRecord(@Param("nowDateString") String nowDateString);

    /**
     * List today not signed record list.
     * 查询今日未封存病历
     *
     * @return the list
     */
    @Select("SELECT * FROM m_record WHERE rstate = 1")
    List<Map<String, Object>> listTodayNotSignedRecord();

    /**
     * List all record list.
     * 查询所有的record 关联查询patient表
     *
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
            "\tLEFT JOIN m_department d ON d.id = r.rdepartment ")
    List<Map<String, Object>> listAllRecord();

    /**
     * List all not signed record list.
     * 查询所有未封存的病历
     *
     * @param state the state
     * @return the list
     */
    @Select("SELECT *,CASE\n" +
            "\trstate \n" +
            "\tWHEN 1 THEN\n" +
            "\t'未封存' \n" +
            "\tWHEN 2 THEN\n" +
            "\t'已封存' \n" +
            " ELSE '未知'\n" +
            "END AS state FROM `m_record` r INNER JOIN m_patient p ON p.id=r.pid LEFT JOIN m_department d on d.id=r.rdepartment WHERE r.rstate = #{state} AND r.rpass!=1")
    List<Map<String, Object>> listAllRecordByState(int state);

    /**
     * 使用id查询单个的病历
     *
     * @param id the id
     * @return the list
     */
    @Select("SELECT *,CASE\n" +
            "\trstate \n" +
            "\tWHEN 1 THEN\n" +
            "\t'未封存' \n" +
            "\tWHEN 2 THEN\n" +
            "\t'已封存' \n" +
            " ELSE '未知'\n" +
            "END AS state FROM `m_record` r INNER JOIN m_patient p ON p.id=r.pid LEFT JOIN m_department d on d.id=r.rdepartment WHERE r.id = #{id}")
    Map<String, Object> getRecordById(int id);

    /**
     * Gets record by pid.
     *
     * @param id the id
     * @return the record by pid
     */
    @Select("SELECT * FROM m_record WHERE pid=#{id}")
    Map<String, Object> getRecordByPid(String id);

    /**
     * Update seal record by id int.
     * 封存病历
     * @param id the id
     * @return the int
     */
    @Update("UPDATE m_record SET rstate = 2 WHERE id = #{id}")
    int updateSealRecordById(int id);
    /**
     * Update seal record by id int.
     * 解封病历
     * @param id the id
     * @return the int
     */
    @Update("UPDATE m_record SET rstate = 1 WHERE id = #{id}")
    int updateDeBlockRecordById(int id);

    /**
     * Update seal record by id int.
     * 通过病历
     * @param id the id
     * @return the int
     */
    @Update("UPDATE m_record SET rpass = 1 WHERE id = #{id}")
    int passRecordById(int id);
    /**
     * Update seal record by id int.
     * 拒绝病历
     * @param id the id
     * @return the int
     */
    @Update("UPDATE m_record SET rpass = 2,rstate=1 WHERE id = #{id}")
    int rejectRecordById(int id);

}
