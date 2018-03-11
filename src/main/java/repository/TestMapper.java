package repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestMapper {

    @Select("select name from user where id=#{id}")
    String selectNameByID(int  id);
}
