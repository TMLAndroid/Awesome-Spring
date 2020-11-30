package com.example.luban.ioc.service;

import com.example.luban.ioc.module.IndexDao;

public  class IndexDaoService2 {//implements ApplicationContextAware


    IndexDao c;

    public IndexDaoService2(IndexDao c) {
        this.c = c;
    }

    public void test(){

        c.test();


    }


    public void setC(IndexDao c) {
        this.c = c;

    }



}
