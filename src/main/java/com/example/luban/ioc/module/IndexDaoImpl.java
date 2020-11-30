package com.example.luban.ioc.module;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("a")
//@Primary
@Scope()
public class IndexDaoImpl implements IndexDao {


    public void test() {
        System.out.println("IndexDaoImpl");
    }

    public IndexDaoImpl() {
        System.out.println("construct");
    }

    @PostConstruct
    public void init(){
        System.out.println("init");
    }
}
