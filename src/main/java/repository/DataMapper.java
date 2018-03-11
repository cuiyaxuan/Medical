package repository;

import entity.Data;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;


@Repository
@Mapper
public interface DataMapper  extends BaseMapper<Data> {
   /* @Select("select  id,team,goodId,color,PLAYER_NAME,No,s_number,m_number,l_number,xl_number,xxl_number,xxxl_number,MENO  from data ")
    List<Data> getAllData();*/
   @Select("select  d.*,g.name  from data as d LEFT JOIN  good g ON g.id= d.goodId ")
   List<Data> getAllData();
    @Select("select * from data where id =2000")
    Data getOneData();

    @Select("select * from data where id = #{id}")
    Data getDataByID(@Param("id") String id);

    @Update("update data set team=#{team},goodId=#{goodid},color=#{color},No=#{no},PLAYERNAME=#{playerName},snumber=#{sNumber},mnumber=#{mNumber},lnumber=#{lNumber},xlnumber=#{xlNumber},xxlnumber=#{xxlNumber},xxxlnumber=#{xxxlNumber},MENO=#{meno} where id=#{id}")
    int updateData(@Param("team")String team,@Param("goodid")Integer goodid,@Param("color")String color,@Param("no")String no,@Param("playerName")String playerName,@Param("sNumber")Integer sNumber,@Param("mNumber")Integer mNumber,@Param("lNumber")Integer lNumber,@Param("xlNumber")Integer xlNumber,@Param("xxlNumber")Integer xxlNumber,@Param("xxxlNumber")Integer xxXlNumber,@Param("meno")String meno,@Param("id")Integer id);
    @Insert("insert into data (team,goodId,color,No,PLAYERNAME,snumber,mnumber,lnumber,xlnumber,xxlnumber,xxxlnumber,MENO) values (#{team},#{goodid},#{color},#{no},#{playerName},#{sNumber},#{mNumber},#{lNumber},#{xlNumber},#{xxlNumber},#{xxxlNumber},#{meno})")
    int insertData(@Param("team")String team,@Param("goodid")Integer goodid,@Param("color")String color,@Param("no")String no,@Param("playerName")String playerName,@Param("sNumber")Integer sNumber,@Param("mNumber")Integer mNumber,@Param("lNumber")Integer lNumber,@Param("xlNumber")Integer xlNumber,@Param("xxlNumber")Integer xxlNumber,@Param("xxxlNumber")Integer xxXlNumber,@Param("meno")String meno);
}