package com.example.luban.ioc.service;

import com.example.luban.ioc.MyName;
import com.example.luban.ioc.module.IndexDao;
import com.example.luban.ioc.module.OutSideBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@DependsOn({"c"})
public  abstract class IndexDaoService   {//implements ApplicationContextAware

    @Autowired
    @Qualifier("c")
    IndexDao c;

    @Autowired
    private ApplicationContext context;
//    private ApplicationContext applicationContext;

//    @Autowired
//    private OutSideBean outSideBean;

    public IndexDaoService(IndexDao a) {
        System.out.println("IndexDaoService Construct");
        this.c = a;

    }
    public void test(){
//        outSideBean.print();
        c.test();
        MyName annotation = c.getClass().getAnnotation(MyName.class);
        String value = annotation.value();
        System.out.println("注解："+value);
        for (int i = 0; i < 3; i++) {
            System.out.println(getCC().hashCode());
        }

    }


    public void setC(IndexDao c) {
        this.c = c;

    }

    @Lookup(value = "c")
    public abstract  IndexDao getC();

    public IndexDao getCC(){
        return (IndexDao)(this.context.getBean("c",IndexDao.class));
    }

//    public void setApplicationContext(
//            ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }

}
