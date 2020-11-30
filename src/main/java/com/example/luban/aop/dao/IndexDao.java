package com.example.luban.aop.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("IndexDao")
@Scope("prototype")
public class IndexDao implements Dao {
    public void query() {
        System.out.println("query IndexDao");
    }

    public void query(String parmas) {
        System.out.println("query IndexDao "+parmas);
    }
}
