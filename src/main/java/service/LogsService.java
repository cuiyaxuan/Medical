package service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.LogsMapper;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/02/24.
 */
@Service
@Transactional
public class LogsService {
    @Autowired
    private LogsMapper logsMapper;


    public int add (Logs logs){
        return  logsMapper.insert(logs);
    }

    public PageInfo get (Integer page){
        int pageNo = page;
        int pageSize = 10;
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Logs> list = logsMapper.getAllGood();
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }

    public List<Logs> getAll (){
        List<Logs> list = logsMapper.getAllGood();
        return  list;
    }
}
