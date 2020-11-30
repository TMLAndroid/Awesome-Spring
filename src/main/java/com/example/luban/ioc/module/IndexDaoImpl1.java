package com.example.luban.ioc.module;

import com.example.luban.ioc.MyName;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("c")
@Scope("prototype")
@MyName("tmlong")
public class IndexDaoImpl1 implements IndexDao {


    public void test() {
        System.out.println("IndexDaoImpl1");
    }
}
