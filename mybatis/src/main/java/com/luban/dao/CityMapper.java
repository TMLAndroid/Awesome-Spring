package com.luban.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@CacheNamespace//开启二级缓存，存入本地环境 ，（二级缓存有个坑 ，更新了数据，查询的是旧数据，基于命名空间，如果命名空间不一致，会导致查询出旧数据）
public interface CityMapper {

    @Select("select * from city")
    public List<Map<String,Object>> list();
}
