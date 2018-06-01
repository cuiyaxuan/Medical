package service.remind;

import entity.MRemind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.remind.RemindMapper;

import static Util.DateUtils.getNowDateString;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年06月02日 00:42
 **/
@Service
public class RemindService {
    @Autowired
    RemindMapper remindMapper;
    public int insert(MRemind mRemind){
        mRemind.setGmtCreate(getNowDateString());
        int i = remindMapper.insert(mRemind);
        return i;
    }
}
