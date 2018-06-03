package service.data;

import Util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.data.DataMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: WangXinYu
 * @describe: TODO
 * @create: 2018年05月02日 08:14
 **/
@Service
public class DataService {
    @Autowired
    DataMapper dataMapper;

    public Map<String, Object> getDefaultState(String id, String year) {
        List<String> defaultState = new ArrayList<String>();
        List<String> passState = new ArrayList<String>();
        List<String> rejectState = new ArrayList<String>();
        String date = "";
        for (int i = 1; i <= 12; i++) {
            if (StringUtils.getWordCount(String.valueOf(i)) < 2) {
                date = year + "-0" + String.valueOf(i);
            } else {
                date = year + "-" + String.valueOf(i);
            }
            int defaultNumber = dataMapper.getDefaultState(id, date);
            int passNumber = dataMapper.getPassState(id, date);
            int rejectNumber = dataMapper.getRejectState(id, date);
            if ("".equals(defaultNumber)) {
                defaultState.add("0");
            } else {
                defaultState.add(String.valueOf(defaultNumber));
            }
            if ("".equals(passNumber)) {
                passState.add("0");
            } else {
                passState.add(String.valueOf(passNumber));
            }
            if ("".equals(rejectNumber)) {
                rejectState.add("0");
            } else {
                rejectState.add(String.valueOf(rejectNumber));
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("defaultState", defaultState);
        map.put("passState", passState);
        map.put("rejectState", rejectState);
        return map;
    }

    /*********************************传染病统计开始*********************************/
    public List<Integer> listAllInfectionData() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) {
            list.add(0);
        }
        List<Map<String, Object>> mapList = dataMapper.listAllInfectionData();
        for (int i = 0; i < mapList.size(); i++) {
            int month = Integer.parseInt(mapList.get(i).get("months").toString());
            list.set(month - 1, Integer.parseInt(mapList.get(i).get("number").toString()));
        }
        return list;
    }

    public List<Integer> listAllInfectionDataByDepartment(String departmentId) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) {
            list.add(0);
        }
        List<Map<String, Object>> mapList = dataMapper.listAllInfectionDataByDepartment(departmentId);
        for (int i = 0; i < mapList.size(); i++) {
            int month = Integer.parseInt(mapList.get(i).get("months").toString());
            list.set(month - 1, Integer.parseInt(mapList.get(i).get("number").toString()));
        }
        return list;
    }

    public List<Map<String, Object>> listAllMonthTop() {
        List<Map<String, Object>> mapList = dataMapper.listAllMonthTop();
        mapList = mapList.subList(0, 5);
        return mapList;
    }

    public List<Map<String, Object>> getMonthTop(String departmentId) {
        List<Map<String, Object>> mapList = dataMapper.getMonthTop(departmentId);
        mapList = mapList.subList(0, 5);
        return mapList;
    }

    public List<Map<String, Object>> listDepartMentTop(){
        List<Map<String, Object>> mapList = dataMapper.listDepartMentTop();
        return mapList;
    };
    /*********************************传染病统计结束*********************************/
    public Map<String, Object> getRecordData(String id, String year) {
        List<String> defaultState = new ArrayList<String>();
        List<String> passState = new ArrayList<String>();
        List<String> rejectState = new ArrayList<String>();
        String date = "";
        for (int i = 1; i <= 12; i++) {
            if (StringUtils.getWordCount(String.valueOf(i)) < 2) {
                date = year + "-0" + String.valueOf(i);
            } else {
                date = year + "-" + String.valueOf(i);
            }
            int defaultNumber = dataMapper.getRecordDefaultData(id, date);
            int passNumber = dataMapper.getRecordPassData(id, date);
            int rejectNumber = dataMapper.getRecordRejectData(id, date);
            if ("".equals(defaultNumber)) {
                defaultState.add("0");
            } else {
                defaultState.add(String.valueOf(defaultNumber));
            }
            if ("".equals(passNumber)) {
                passState.add("0");
            } else {
                passState.add(String.valueOf(passNumber));
            }
            if ("".equals(rejectNumber)) {
                rejectState.add("0");
            } else {
                rejectState.add(String.valueOf(rejectNumber));
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("defaultState", defaultState);
        map.put("passState", passState);
        map.put("rejectState", rejectState);
        return map;
    }

    public Map<String, Object> listRecordData(String year) {
        List<String> defaultState = new ArrayList<String>();
        List<String> passState = new ArrayList<String>();
        List<String> rejectState = new ArrayList<String>();
        String date = "";
        for (int i = 1; i <= 12; i++) {
            if (StringUtils.getWordCount(String.valueOf(i)) < 2) {
                date = year + "-0" + String.valueOf(i);
            } else {
                date = year + "-" + String.valueOf(i);
            }
            int defaultNumber = dataMapper.listRecordDefaultData(date);
            int passNumber = dataMapper.listRecordPassData(date);
            int rejectNumber = dataMapper.listRecordRejectData(date);
            if ("".equals(defaultNumber)) {
                defaultState.add("0");
            } else {
                defaultState.add(String.valueOf(defaultNumber));
            }
            if ("".equals(passNumber)) {
                passState.add("0");
            } else {
                passState.add(String.valueOf(passNumber));
            }
            if ("".equals(rejectNumber)) {
                rejectState.add("0");
            } else {
                rejectState.add(String.valueOf(rejectNumber));
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("defaultState", defaultState);
        map.put("passState", passState);
        map.put("rejectState", rejectState);
        return map;
    }

    /*********************病人统计开始了************************************/
    public List<Integer> listPatientMonthDataBySex(String departmentId, String page, String psex) {
        int max = 0;
        int min = 0;
        switch (Integer.parseInt(page)) {
            case 1:
                max = 45;
                min = 0;
                break;
            case 2:
                max = 59;
                min = 45;
                break;
            case 3:
                max = 74;
                min = 59;
                break;
            case 4:
                max = 89;
                min = 74;
                break;
            case 5:
                max = 200;
                min = 89;
                break;
            default:
                break;
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) {
            list.add(0);
        }
        List<Map<String, Object>> mapList = dataMapper  .listPatientMonthDataBySex(departmentId, max,min, psex);
        for (int i = 0; i < mapList.size(); i++) {
            int month = Integer.parseInt(mapList.get(i).get("name").toString());
            list.set(month - 1, Integer.parseInt(mapList.get(i).get("value").toString()));
        }
        return list;
    }

    public List<Integer> listAllPatientMonthData() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) {
            list.add(0);
        }
        List<Map<String, Object>> mapList = dataMapper.listAllPatientMonthData();
        for (int i = 0; i < mapList.size(); i++) {
            int month = Integer.parseInt(mapList.get(i).get("name").toString());
            list.set(month - 1, Integer.parseInt(mapList.get(i).get("value").toString()));
        }
        return list;
    }
    public List<Map<String,Object>> listPatientMonthTop(){
        List<Map<String, Object>> mapList = dataMapper.listPatientMonthTop();
        return mapList;
    }
    /*********************病人统计结束了************************************/
}
