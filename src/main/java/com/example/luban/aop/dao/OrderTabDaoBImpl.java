package com.example.luban.aop.dao;

import org.springframework.stereotype.Component;

@Component("OrderTabDaoBImpl")
public class OrderTabDaoBImpl implements OrderTabDao {
    public void query() {
        System.out.println("query B");
    }
}
