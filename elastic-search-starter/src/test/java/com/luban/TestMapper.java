package com.luban;

import com.luban.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestMapper {

    @Autowired
    OrderMapper orderMapper;

    @Test
    void exists(){
        boolean hh = orderMapper.existsById("hh");
    }

}
