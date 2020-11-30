package com.luban;

import com.luban.manager.UserClazz;
import com.luban.service.IndexService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.ProxyGenerator;

public class Main {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        //System.out.println(annotationConfigApplicationContext.getBean(IndexService.class).list());
//        ProxyGenerator
        System.out.println(annotationConfigApplicationContext.getBean(UserClazz.class).getOrderClazz()+"===========");
    }
}
