package repository.logs;

import entity.Logs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * The interface Logs mapper.
 */
@Repository
@Mapper
public interface LogsMapper  extends BaseMapper<Logs> {
    /**
     * Gets all logs.
     *
     * @return the all logs
     */
    @Select("select * from m_logs  ORDER BY id  DESC  ")
    List<Logs> getAllLogs();
}