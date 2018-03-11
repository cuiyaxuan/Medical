package repository;

import entity.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
@Mapper
public interface UserMapper  extends BaseMapper<User> {
}