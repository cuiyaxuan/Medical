package service.doctor;

import entity.MPatient;
import entity.MRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.doctor.DoctorMapper;
import repository.doctor.PatientMapper;
import repository.doctor.RemindMapper;
import repository.record.RecordMapper;

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
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    RemindMapper remindMapper;

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

    public List<Map<String, Object>> listAllRecord(String userName){
        String departmentId = doctorMapper.getUserDepartment(userName);
        List<Map<String, Object>> mapList = doctorMapper.listAllRecord(departmentId);
        return mapList;
    }

    public Map<String,Object> getOneRecordById(String pid){
        Map<String,Object> object = recordMapper.getRecordByPid(pid);
        return object;
    }

    /**
     * 修改病历
     */
    public int updateRecord(MRecord mrecord){
        int i = recordMapper.updateByPrimaryKeySelective(mrecord);
        return i;
    }
    /**
     * 新增病历
     */
    public int addRecord(MRecord record){
        int i = recordMapper.insert(record);
        return i;
    }
    /**
     * 查询工作提醒
     */
    public List<Map<String, Object>> listWorkMind(String id){
        List<Map<String, Object>> mapList = remindMapper.listWorkMind(id);
        return mapList;
    }
    /**
     * 删除工作提醒
     */
    public int deleteRemind(String id){
        int i = doctorMapper.deleteWorkRemind(id);
        return i;
    }

    /**
     * 通过pid查询是否存在病历
     */
    public int isRecordExist(String pid){
        int i = doctorMapper.isRecordExist(pid);
        return i;
    }
}
