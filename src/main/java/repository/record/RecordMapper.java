package repository.record;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
public interface RecordMapper {
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
    @Select("SELECT *,CASE\n" +
            "\trstate \n" +
            "\tWHEN 1 THEN\n" +
            "\t'未封存' \n" +
            "\tWHEN 2 THEN\n" +
            "\t'已封存' \n" +
            "\t ELSE '未知'\n" +
            "END AS state FROM `m_record` r INNER JOIN m_patient p ON p.id=r.pid LEFT JOIN m_department d on d.id=r.rdepartment ")
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
            "END AS state FROM `m_record` r INNER JOIN m_patient p ON p.id=r.pid LEFT JOIN m_department d on d.id=r.rdepartment WHERE r.rstate = #{state}")
    List<Map<String, Object>> listAllRecordByState(int state);
}
