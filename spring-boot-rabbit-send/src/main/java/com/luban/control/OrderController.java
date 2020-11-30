package com.luban.control;

import com.luban.util.RabbitMessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    RabbitMessageSend rabbitMessageSend;

    @RequestMapping("order")
    public Object order(){
        rabbitMessageSend.testSend();
        return null;
    }
}
