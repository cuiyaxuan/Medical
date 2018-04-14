package service.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.record.RecordMapper;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年04月14日 21:58
 **/
@Service
public class RecordService {
    @Autowired
    RecordMapper recordMapper;
}
