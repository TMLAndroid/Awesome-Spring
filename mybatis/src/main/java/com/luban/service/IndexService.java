package com.luban.service;

import com.luban.dao.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IndexService {

    @Autowired
    CityMapper cityMapper;

    public  List<Map<String,Object>> list(){
//        org.apache.ibatis.logging.LogFactory.useLog4JLogging();
        cityMapper.list();
        cityMapper.list();
        return cityMapper.list();
    }
}
