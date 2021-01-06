package com.example.luban.ioc;


import com.example.luban.ioc.config.BeanFactory;
import com.example.luban.ioc.config.MyScope;
import com.example.luban.ioc.config.ProjectConfig;
import com.example.luban.ioc.module.IndexDao;
import com.example.luban.ioc.module.OutSideBean;
import com.example.luban.ioc.service.IndexDaoService;
import com.example.luban.ioc.service.IndexDaoService1;
import com.example.luban.ioc.service.IndexDaoService2;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Main {

    //IOC 看到1.6了
    public static void main(String[] args){

//
//        BeanFactory beanFactory = new BeanFactory("factory.xml");
//        IndexDaoService1 indexDaoService = (IndexDaoService1) beanFactory.getBean("indexDaoService");
//        indexDaoService.test();


//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(); 多个参数
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ProjectConfig.class);

        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) annotationConfigApplicationContext.getBeanFactory();
        beanFactory.registerSingleton("outSideBean",new OutSideBean());// 注入不是容器管理的对象

        Scope scope =new MyScope();
        beanFactory.registerScope("thread",scope);//注入自定义Scope

        OutSideBean outSideBean = (OutSideBean) annotationConfigApplicationContext.getBean("outSideBean");
        outSideBean.print();

        IndexDaoService indexDaoService = annotationConfigApplicationContext.getBean(IndexDaoService.class);
        IndexDaoService indexDaoService1 = annotationConfigApplicationContext.getBean(IndexDaoService.class);
        System.out.println("hashCode:"+indexDaoService.hashCode());
        System.out.println("hashCode1:"+indexDaoService1.hashCode());
        indexDaoService.test();

        //indexDaoService1.test();

//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//        IndexDaoService bean = classPathXmlApplicationContext.getBean(IndexDaoService.class);
//        bean.test();



    }
}
