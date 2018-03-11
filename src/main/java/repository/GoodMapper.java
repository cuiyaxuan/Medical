package repository;


import entity.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
/**
 * @author WangXinYu
 */
@Mapper
public interface GoodMapper  extends BaseMapper<Good> {

    @Select("select * from good ")
    List<Good> getAllGood();
}