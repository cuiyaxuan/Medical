package service.nurse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.nurse.NurseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月16日 08:45
 **/
@Service
public class NurseService {
    @Autowired
    NurseMapper nurseMapper;

    public List<Map<String,Object>> listAllDocument() {
        List<Map<String, Object>> mapList = nurseMapper.listAllDocument();
        return mapList;
    }
    public Map<String,Object> getDocumentById(int id) {
        Map<String, Object> map = nurseMapper.getDocumentById(id);
        return map;
    }
}
