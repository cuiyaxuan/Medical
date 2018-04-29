package repository.doctor;

import entity.MPatient;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * The interface Patient mapper.
 *
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月29日 10:06
 */
@Repository
public interface PatientMapper extends BaseMapper<MPatient> {
    /**
     * Delete patient int.
     *
     * @param id the id
     * @return the int
     */
    @Update("UPDATE m_patient SET precure = 2 WHERE id = #{id}")
    int deletePatient(String id);
}
