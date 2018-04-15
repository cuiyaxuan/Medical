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
     *
     * @param nowDateString the now date string
     * @return the list
     */
    @Select("SELECT * FROM m_record WHERE rstate = 2 AND gmt_create = #{nowDateString}")
    List<Map<String, Object>> listTodaySignedRecord(@Param("nowDateString") String nowDateString);

    /**
     * List today not signed record list.
     *
     * @return the list
     */
    @Select("SELECT * FROM m_record WHERE rstate = 1")
    List<Map<String, Object>> listTodayNotSignedRecord();
}
