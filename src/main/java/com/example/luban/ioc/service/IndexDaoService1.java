package com.example.luban.ioc.service;

import com.example.luban.ioc.MyName;
import com.example.luban.ioc.module.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

public  class IndexDaoService1 {//implements ApplicationContextAware


    public IndexDao c;



    public void test(){

        c.test();


    }


    public void setC(IndexDao c) {
        this.c = c;

    }



}
