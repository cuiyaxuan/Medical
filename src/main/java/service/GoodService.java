package service;


import Util.LogsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Good;
import entity.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.GoodMapper;
import repository.LogsMapper;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * @author CodeGenerator
 * @date 2018/02/24
 */
@Service
@Transactional
public class GoodService {
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private LogsMapper logsMapper;
    @Transactional
    public  int add (Good good){
        Logs logs = LogsUtil.Logs("添加了商品,商品名称："+good.getName());
        logsMapper.insert(logs);
        return  goodMapper.insert(good);
    }
    @Transactional
    public  int delete (Good good){
        Logs logs = LogsUtil.Logs("删除了商品,商品名称："+good.getName());
        logsMapper.insert(logs);
        return  goodMapper.delete(good);
    }
    @Transactional
    public  int update (Good good){
        Logs logs = LogsUtil.Logs("更新了商品,商品名称："+good.getName()+" 商品ID："+good.getId());
        logsMapper.insert(logs);
        return  goodMapper.updateByPrimaryKeySelective(good);
    }

    public PageInfo get (Integer page){
        int pageNo = page;
        int pageSize = 10;
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Good> list = goodMapper.getAllGood();
        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;

    }

    public List<Good> getAll (){
        List<Good> list = goodMapper.getAllGood();
        return  list;

    }
}
