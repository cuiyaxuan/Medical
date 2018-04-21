package service.index;

import entity.MUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.index.IndexMapper;

import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月14日 14:46
 **/
@Service
public class IndexService {
    @Autowired
    IndexMapper indexMapper;

    public List<Map<String,Object>> countRecordByState(){
        List<Map<String, Object>> mapList = indexMapper.countRecordByState();
        return mapList;
    }

    public List<Map<String,Object>> listSomeDoctor(){
        List<Map<String,Object>> mUsers = indexMapper.listSomeDoctors();
        return mUsers;
    }
    public List<MUser> listSomeNurse(){
        List<MUser> mUsers = indexMapper.listSomeNurses();
        return mUsers;
    }
}
