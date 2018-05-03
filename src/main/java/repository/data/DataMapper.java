package repository.data;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * The interface Data mapper.
 *
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年05月02日 08:13
 */
@Repository
public interface DataMapper {
    /**
     * Gets default state.
     * 查询该部门未审核的记录
     *
     * @param id    the id
     * @param month the year
     * @return the default state
     */
    @Select("SELECT COUNT(*) FROM `m_record` WHERE rdepartment=(SELECT id FROM m_department WHERE d_leader=#{userid}) AND date_format(gmt_create,'%Y-%m')=#{month} AND rpass=0")
    int getDefaultState(@Param("userid") String id,@Param("month")String month);

    /**
     * Gets default state.
     * 查询该部门通过的记录
     *
     * @param id    the id
     * @param month the month
     * @return the default state
     */
    @Select("SELECT COUNT(*) FROM `m_record` WHERE rdepartment=(SELECT id FROM m_department WHERE d_leader=#{userid}) AND date_format(gmt_create,'%Y-%m')=#{month} AND rpass=1")
    int getPassState(@Param("userid") String id,@Param("month")String month);

    /**
     * Gets default state.
     * 查询该部门拒绝的记录
     *
     * @param id    the id
     * @param month the month
     * @return the default state
     */
    @Select("SELECT COUNT(*) FROM `m_record` WHERE rdepartment=(SELECT id FROM m_department WHERE d_leader=#{userid}) AND date_format(gmt_create,'%Y-%m')=#{month} AND rpass=2 ")
    int getRejectState(@Param("userid") String id,@Param("month")String month);


    /**
     * List all infection data list.
     * 查询所有科室按月份统计的list
     *
     * @return the list
     */
    @Select("SELECT DATE_FORMAT(r.gmt_create,'%m') months,COUNT(*) as number FROM `m_record` r WHERE r.rinfaction=1 group by months order by number DESC")
    List<Map<String, Object>> listAllInfectionData();

    /**
     * List all infection data list.
     * 查询单个科室按月份统计的list
     *
     * @param id the id
     * @return the list
     */
    @Select("SELECT DATE_FORMAT(r.gmt_create,'%m') months,COUNT(*) as number FROM `m_record` r WHERE r.rdepartment=#{departmentId} AND r.rinfaction=1 group by months")
    List<Map<String, Object>> listAllInfectionDataByDepartment(@Param("departmentId") String id);

    /**
     * List all infection data list.
     * 查询所有科室按月份统计的list
     *
     * @return the list
     */
    @Select("SELECT DATE_FORMAT(r.gmt_create,'%m') name,COUNT(*) as value FROM `m_record` r WHERE r.rinfaction=1 group by name order by value DESC")
    List<Map<String, Object>> listAllMonthTop();

    /**
     * List all infection data list.
     * 查询单个科室按月份统计的list
     *
     * @param id the id
     * @return the list
     */
    @Select("SELECT DATE_FORMAT(r.gmt_create,'%m') name,COUNT(*) as value FROM `m_record` r WHERE r.rdepartment=#{departmentId} AND r.rinfaction=1 group by name")
    List<Map<String, Object>> getMonthTop(@Param("departmentId") String id);

    /**
     * Gets default state.
     * 查询病历统记的未审核记录
     *
     * @param departmentId the department id
     * @param month        the month
     * @return the record default data
     */
    @Select("SELECT COUNT(*) FROM `m_record` WHERE rdepartment=#{departmentId} AND date_format(gmt_create,'%Y-%m')=#{month} AND rpass=0")
    int getRecordDefaultData(@Param("departmentId")String departmentId,@Param("month")String month);

    /**
     * Gets record pass data.
     *
     * @param departmentId the department id
     * @param month        the month
     * @return the record pass data
     */
    @Select("SELECT COUNT(*) FROM `m_record` WHERE rdepartment=#{departmentId} AND date_format(gmt_create,'%Y-%m')=#{month} AND rpass=1")
    int getRecordPassData(@Param("departmentId")String departmentId,@Param("month")String month);

    /**
     * Gets record reject data.
     *
     * @param departmentId the department id
     * @param month        the month
     * @return the record reject data
     */
    @Select("SELECT COUNT(*) FROM `m_record` WHERE rdepartment=#{departmentId} AND date_format(gmt_create,'%Y-%m')=#{month} AND rpass=2")
    int getRecordRejectData(@Param("departmentId")String departmentId,@Param("month")String month);

    /**
     * Gets default state.
     * 查询病历统记的未审核记录
     *
     * @param month the month
     * @return the record default data
     */
    @Select("SELECT COUNT(*) FROM `m_record` WHERE date_format(gmt_create,'%Y-%m')=#{month} AND rpass=0")
    int listRecordDefaultData(@Param("month")String month);

    /**
     * Gets record pass data.
     *
     * @param month the month
     * @return the record pass data
     */
    @Select("SELECT COUNT(*) FROM `m_record` WHERE date_format(gmt_create,'%Y-%m')=#{month} AND rpass=1")
    int listRecordPassData(@Param("month")String month);

    /**
     * Gets record reject data.
     *
     * @param month the month
     * @return the record reject data
     */
    @Select("SELECT COUNT(*) FROM `m_record` WHERE date_format(gmt_create,'%Y-%m')=#{month} AND rpass=2")
    int listRecordRejectData(@Param("month")String month);

    /**
     * List patient month data by sex list.
     * 按性别查询 月份数据
     *
     * @param psex the psex
     * @return the list
     */
    @Select("\tSELECT count(*) as value, DATE_FORMAT(p.padmissiontime,'%m') name FROM m_patient p LEFT JOIN m_record r ON r.pid=p.id\n" +
            "\tWHERE r.rdepartment=#{departmentId} AND p.psex=#{psex} And p.page<=#{maxAge}&&p.page>#{minAge}\n" +
            "\tGROUP BY name")
    List<Map<String, Object>> listPatientMonthDataBySex(@Param("departmentId") String departmentId, @Param("maxAge")int maxAge,@Param("minAge")int minAge,@Param("psex") String psex);

    /**
     * List all patient month data list.
     * 查询全部
     * @return the list
     */
    @Select("SELECT count(*) as value, DATE_FORMAT(p.padmissiontime,'%m') name FROM `m_patient` p GROUP BY name")
    List<Map<String, Object>> listAllPatientMonthData();
}
