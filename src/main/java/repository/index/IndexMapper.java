package repository.index;

import entity.MUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * The interface Index mapper.
 *
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月14日 14:46
 */
@Repository
public interface IndexMapper{
    /**
     * Count record by state list.
     *
     * @return the list
     */
    @Select("SELECT COUNT(*) AS number, mr.rstate FROM `m_record` mr GROUP BY mr.rstate")
    List<Map<String,Object>> countRecordByState();

    /**
     * List some doctors list.
     *
     * @return the list
     */
    @Select("SELECT * FROM `m_user` WHERE type = 1 LIMIT 8")
    List<MUser> listSomeDoctors();

    /**
     * List some nurses list.
     *
     * @return the list
     */
    @Select("SELECT * FROM `m_user` WHERE type = 2 LIMIT 8")
    List<MUser> listSomeNurses();
}
