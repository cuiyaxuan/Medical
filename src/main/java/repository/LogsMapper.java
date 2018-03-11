package repository;

import entity.Logs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Repository
@Mapper
public interface LogsMapper  extends BaseMapper<Logs> {
    @Select("select * from logs  ORDER BY id  DESC  ")
    List<Logs> getAllGood();
}