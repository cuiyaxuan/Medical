package service.doctor;

import entity.MPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.doctor.DoctorMapper;

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

    public List<MPatient> listAllPatient(){
        List<MPatient> mPatients = doctorMapper.listAllPatient();
        return mPatients;
    }
    public Map<String,Object> getOnePatientById(int id){
        Map<String,Object> mPatient = doctorMapper.getOnePatientById(id);
        return mPatient;
    }


}
