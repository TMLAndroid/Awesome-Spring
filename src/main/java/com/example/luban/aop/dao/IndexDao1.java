package com.example.luban.aop.dao;

import org.springframework.stereotype.Component;

@Component("IndexDao1")
public class IndexDao1 implements Dao {
    public void query() {
        System.out.println("query IndexDao1");
    }

    public void query(String parmas) {
        System.out.println("query IndexDao1 "+parmas);
    }

}
