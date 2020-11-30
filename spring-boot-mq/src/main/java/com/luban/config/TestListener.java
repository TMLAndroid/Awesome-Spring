package com.luban.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestListener {
    @RabbitListener(queues = "testQueue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void get(Message message, Channel channel) throws Exception{

        System.out.println(new String(message.getBody(),"UTF-8"));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        //出现死循环 ，再加一个消费者，轮询所有消费者消费
        //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true));
        System.out.println("消费者1");
    }

    @RabbitListener(queues = "testQueue",containerFactory = "simpleRabbitListenerContainerFactory")
    public void get(String message) throws Exception{

        System.out.println(message);

        System.out.println("消费者2");
    }
}
