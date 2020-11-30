package com.luban.consumer;

import com.luban.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer3 {

    public static void main(String[] args) {
        try {
            getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getMessage() throws Exception {
        Connection connection = ConnectionUtil.getConnection();
    final Channel channel = connection.createChannel();
        channel.queueDeclare("queue3",true,false,false,null);

        DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                channel.basicAck(envelope.getDeliveryTag(),false);  //第二个参数，是否批量确认
                System.out.println(new String(body, "UTF-8"));
            }

        };
        channel.basicConsume("queue3", true,deliverCallback);//true 自动消息确认
    }

}
