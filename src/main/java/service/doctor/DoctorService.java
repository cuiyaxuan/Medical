package service.doctor;

import entity.MPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.doctor.DoctorMapper;
import repository.doctor.PatientMapper;

import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月16日 08:46
 **/
@Service
public class DoctorService {
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    PatientMapper patientMapper;

    public List<MPatient> listAllPatient(){
        List<MPatient> mPatients = doctorMapper.listAllPatient();
        return mPatients;
    }
    public Map<String,Object> getOnePatientById(int id){
        Map<String,Object> mPatient = doctorMapper.getOnePatientById(id);
        return mPatient;
    }

    /**
     * 新增病人
     * @param mPatient
     * @return
     */
    public int add(MPatient mPatient){
        int i = patientMapper.insertSelective(mPatient);
        return i;
    }

    /**
     * 更新病人
     * @param mPatient
     * @return
     */
    public int update(MPatient mPatient){
        int i = patientMapper.updateByPrimaryKeySelective(mPatient);
        return i;
    }
    /**
     * 删除病人
     * @param
     * @return
     */
    public int delete(String id){
        int i = patientMapper.deletePatient(id);
        return i;
    }

}
