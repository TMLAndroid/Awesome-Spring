package com.example.luban.aop.config;

import com.example.luban.aop.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.example.luban.aop.*")
public class AppConfig {
    @Autowired
    Dao IndexDao;


}
