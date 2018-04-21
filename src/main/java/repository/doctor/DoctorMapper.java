package repository.doctor;

import entity.MPatient;
import entity.MUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * The interface Doctor mapper.
 *
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月16日 08:47
 */
@Repository
public interface DoctorMapper extends BaseMapper<MUser> {
    /**
     * List all patient list.
     * 查询所有未出院病人
     *
     * @return the list
     */
    @Select("SELECT * FROM m_patient WHERE precure = 1")
    List<MPatient> listAllPatient();

    /**
     * Gets one  patient.
     * 根据id 查询病人
     * @param id the id
     * @return the one patient
     */
    @Select("SELECT\n" +
            "\t*,\n" +
            "CASE\n" +
            "\tpsex \n" +
            "\tWHEN 1 THEN\n" +
            "\t'男' ELSE '女' \n" +
            "\tEND AS sex\n" +
            "FROM\n" +
            "\t`m_patient` \n" +
            "WHERE\n" +
            "\tprecure =1 AND id=#{id}")
    Map<String,Object> getOnePatientById(int id);
}
