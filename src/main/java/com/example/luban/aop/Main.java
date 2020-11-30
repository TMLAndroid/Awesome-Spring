package com.example.luban.aop;

import com.example.luban.aop.config.AppConfig;
import com.example.luban.aop.dao.Dao;
import com.example.luban.aop.service.OrderDaoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Dao dao = (Dao) annotationConfigApplicationContext.getBean("IndexDao");
//        dao.query();
        System.out.println(dao.hashCode());
        dao.query("参数");
        Dao dao1 = (Dao) annotationConfigApplicationContext.getBean("IndexDao");
        System.out.println(dao1.hashCode());
        dao1.query("参数1");
//        Dao testDao = (Dao) annotationConfigApplicationContext.getBean("testDao");
//        testDao.query("DeclareParents");

        OrderDaoService orderDaoService = annotationConfigApplicationContext.getBean(OrderDaoService.class);
        orderDaoService.query("B");

    }
}
