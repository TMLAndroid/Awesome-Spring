package com.luban.service;

import com.luban.dao.CityMapper;
import com.luban.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {

    @Autowired
    CityMapper cityMapper;

    public  List<Map<String,Object>> list(){
//        org.apache.ibatis.logging.LogFactory.useLog4JLogging();
        Map<String,Object> map =new HashMap<>();
        PageUtil pageUtil = new PageUtil(1,10);
        map.put("page",pageUtil);
        cityMapper.selectByPage(map);

        return cityMapper.selectByPage(map);
    }
}
