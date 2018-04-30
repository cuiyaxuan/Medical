package repository.doctor;

import entity.MRemind;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * The interface Remind mapper.
 *
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月30日 07:41
 */
@Repository
public interface RemindMapper extends BaseMapper<MRemind> {
    /**
     * List work mind list.
     *
     * @param userId the user id
     * @return the list
     */
    @Select("SELECT * FROM m_remind WHERE userid=#{userId}")
    List<Map<String,Object>> listWorkMind(String userId);

}
