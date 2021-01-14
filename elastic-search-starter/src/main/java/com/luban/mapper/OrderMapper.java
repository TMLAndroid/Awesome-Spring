package com.luban.mapper;

import com.luban.bean.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderMapper extends ElasticsearchRepository<Order,String> {
}
