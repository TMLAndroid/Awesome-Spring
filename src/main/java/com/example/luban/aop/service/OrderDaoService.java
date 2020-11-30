package com.example.luban.aop.service;

import com.example.luban.aop.dao.OrderTabDao;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderDaoService implements ApplicationContextAware {
    ApplicationContext applicationContext;

    @Autowired
    Map<String,OrderTabDao> map;
    public void query(String name){
        if (name.equals("A")){
            OrderTabDao orderTabDaoAImpl = (OrderTabDao) applicationContext.getBean("OrderTabDaoAImpl");
            orderTabDaoAImpl.query();
        }else if(name.equals("B")){
            OrderTabDao orderTabDaoAImpl = (OrderTabDao) applicationContext.getBean("OrderTabDaoBImpl");
            orderTabDaoAImpl.query();
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
