package com.example.luban.aop.dao;

import org.springframework.stereotype.Component;

@Component("OrderTabDaoAImpl")
public class OrderTabDaoAImpl implements OrderTabDao {
    public void query() {
        System.out.println("query A");
    }
}
