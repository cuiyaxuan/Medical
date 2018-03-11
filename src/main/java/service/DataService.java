package service;


import Util.LogsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Data;
import entity.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DataMapper;
import repository.LogsMapper;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/02/24.
 */
@Service
public class DataService {
    @Autowired
    DataMapper dataMapper;
    @Autowired
    LogsMapper logsMapper;

    @Transactional
    public int add(Data data){
        Logs logs = LogsUtil.Logs("添加了商品,商品名称："+data.getTeam());
        logsMapper.insert(logs);
    return  dataMapper.insertData(data.getTeam(),data.getGoodid(),data.getColor(),data.getNo(),data.getPlayerName(),data.getsNumber(),data.getmNumber(),data.getlNumber(),data.getXlNumber(),data.getXxlNumber(),data.getXxxlNumber(),data.getMeno());

    }
    @Transactional
    public  int delete (Data data){
        Logs logs = LogsUtil.Logs("删除了商品,商品名称："+data.getTeam());
        logsMapper.insert(logs);
        return  dataMapper.delete(data);
    }
    @Transactional
    public  int update (Data data){
        Logs logs = LogsUtil.Logs("更新了商品,商品名称："+data.getTeam());
        logsMapper.insert(logs);
        return  dataMapper.updateData(data.getTeam(),data.getGoodid(),data.getColor(),data.getNo(),data.getPlayerName(),data.getsNumber(),data.getmNumber(),data.getlNumber(),data.getXlNumber(),data.getXxlNumber(),data.getXxxlNumber(),data.getMeno(),data.getId());

    }
    @Transactional
    public PageInfo get ( Integer page){
        int pageNo = page;
        int pageSize = 10;
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Data> list = dataMapper.getAllData() ;
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }
    @Transactional
    public List<Data> getAll () {
        List<Data> list = dataMapper.getAllData();
        return list;
    }
    @Transactional
    public Data getDataByID(String id){
        Data data = dataMapper.getDataByID(id);
        return data;
    }
    public Data getOne ( Integer page){
      Data list = dataMapper.getOneData() ;
      return list;
    }
}
