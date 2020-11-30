package com.luban.util;

import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageSend {
    @Autowired
    RabbitTemplate rabbitTemplate;
    public void testSend(){
        CorrelationData correlationData = new CorrelationData("订单ID");//业务id
        rabbitTemplate.convertAndSend("directExchange","direct.key","hello",correlationData);
    }
}
