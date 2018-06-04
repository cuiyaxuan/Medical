package service.record;

import Util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserMapper;
import repository.doctor.DoctorMapper;
import repository.record.RecordMapper;

import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月14日 21:58
 **/
@Service
public class RecordService {
    @Autowired
    RecordMapper recordMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DoctorMapper doctorMapper;
    public List<Map<String, Object>> listTodaySignedRecord() {
        String nowDateString = DateUtils.getNowDateString();
        List<Map<String, Object>> mapList = recordMapper.listTodaySignedRecord(nowDateString);
        return mapList;
    }

    public List<Map<String, Object>> listTodayNotSignedRecord() {
        String nowDateString = DateUtils.getNowDateString();
        List<Map<String, Object>> mapList = recordMapper.listTodayNotSignedRecord(nowDateString);
        return mapList;
    }

    public List<Map<String, Object>> listAllRecord() {
        List<Map<String, Object>> mapList = recordMapper.listAllRecord();
        return mapList;
    }

    public List<Map<String, Object>> listAllRecordByState(int state,String userName) {
        String departmentId = doctorMapper.getUserDepartment(userName);
        List<Map<String, Object>> mapList = recordMapper.listAllRecordByState(state,departmentId);
        return mapList;
    }

    public Map<String, Object> getRecordById(int id) {
        Map<String, Object> map = recordMapper.getRecordById(id);
        return map;
    }

    public int updateSealRecordById(int id) {
        int i = recordMapper.updateSealRecordById(id);
        return i;
    }

    public int updateDeBlockRecordById(int id) {
        int i = recordMapper.updateDeBlockRecordById(id);
        return i;
    }

    public int passRecordById(int id,String loginId) {
        int i = recordMapper.passRecordById(id);
        if(i>0) {
            userMapper.addScore(loginId);
            return i;
        }
        return 0;
    }

    public int rejectRecordById(int id) {
        int i = recordMapper.rejectRecordById(id);
        return i;
    }

    public Map<String,Object> downloadRecordWord(int id) {
        Map<String, Object> map = recordMapper.getRecordAndPatientInfo(id);

        return map;
    }
}
