package service;

import entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DataMapper;
import repository.TestMapper;

import java.util.List;

@Service
public class TestService {
    @Autowired
    TestMapper testMapper;
    @Autowired
    DataMapper dataMapper;
    public  String selectNameByID(int  id){
        return testMapper.selectNameByID(id);
    }
    @Transactional
    public boolean insertData (List<List<List<String>>> list){
        List<List<String>> list1 =list.get(0);

        for (int i =0 ; i<list1.size();i++){
            Data data =new Data();
            data.setTeam(list1.get(i).get(0));
            data.setColor(list1.get(i).get(1));
            data.setNo(list1.get(i).get(2));
            data.setPlayerName(list1.get(i).get(3));
            if (null!=list1.get(i).get(4)&&!"".equals(list1.get(i).get(4))&&!" ".equals(list1.get(i).get(4))&&!"  ".equals(list1.get(i).get(4))) {
                data.setsNumber((int)Float.parseFloat(list1.get(i).get(4).toString()));
            }else {
                data.setsNumber(0);
            }
            if (null!=list1.get(i).get(5)&&!"".equals(list1.get(i).get(5))&&!" ".equals(list1.get(i).get(5))&&!"  ".equals(list1.get(i).get(5))) {
                data.setmNumber((int)Float.parseFloat(list1.get(i).get(5).toString()));
            }else {
                data.setmNumber(0);
            }
            if (null!=list1.get(i).get(6)&&!"".equals(list1.get(i).get(6))&&!" ".equals(list1.get(i).get(6))&&!"  ".equals(list1.get(i).get(6))) {
                data.setlNumber((int)Float.parseFloat(list1.get(i).get(6).toString()));
            }else {
                data.setlNumber(0);
            }
            if (null!=list1.get(i).get(7)&&!"".equals(list1.get(i).get(7))&&!" ".equals(list1.get(i).get(7))&&!"  ".equals(list1.get(i).get(7))) {
                data.setXlNumber((int)Float.parseFloat(list1.get(i).get(7).toString()));
            }else {
                data.setXlNumber(0);
            }
            if (null!=list1.get(i).get(8)&&!"".equals(list1.get(i).get(8))&&!" ".equals(list1.get(i).get(8))&&!"  ".equals(list1.get(i).get(8))) {
                data.setXxlNumber((int)Float.parseFloat(list1.get(i).get(8).toString()));
            }else {
                data.setXxlNumber(0);
            }
            if (null!=list1.get(i).get(9)&&!"".equals(list1.get(i).get(9))&&!" ".equals(list1.get(i).get(9))&&!"  ".equals(list1.get(i).get(9))) {
                data.setXxxlNumber((int)Float.parseFloat(list1.get(i).get(9).toString()));
            }else {
                data.setXxxlNumber(0);
            }



            data.setMeno(list1.get(i).get(10));
            data.setGoodid(Integer.parseInt(list1.get(i).get(11)));
            dataMapper.insert(data);
        }


        return true;
    }
}
