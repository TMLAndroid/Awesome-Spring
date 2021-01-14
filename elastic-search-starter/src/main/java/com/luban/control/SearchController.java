package com.luban.control;

import com.luban.bean.Order;
import com.luban.mapper.OrderMapper;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;

@RestController
public class SearchController {

    @Autowired
    OrderMapper orderMapper;

    @RequestMapping("/query-test")
    public Iterable<Order> query(){
        MatchQueryBuilder queryBuilders = QueryBuilders.matchQuery("buyer","官方旗舰店");
        Pageable pageable = new Pageable() {
            @Override
            public int getNumberOfPages() {
                return 0;
            }

            @Override
            public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
                return null;
            }

            @Override
            public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
                return null;
            }
        };
        Iterable<Order> search = orderMapper.search(queryBuilders);
        return search;
    }
}
