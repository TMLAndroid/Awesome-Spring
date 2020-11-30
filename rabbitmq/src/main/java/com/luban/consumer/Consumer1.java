package com.luban.consumer;

import com.luban.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Date;

public class Consumer1 {

    static int i = 0;
    public static void main(String[] args) {
        try {
            getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getMessage() throws Exception {
        Date date = new Date();
        Connection connection = ConnectionUtil.getConnection();
    final Channel channel = connection.createChannel();
        channel.queueDeclare("queue1",true,false,false,null);
        DefaultConsumer deliverCallback = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                i++;


                if (i%2500 == 0 || i == 20000){  //取一条7.474s 一次取2500 2.140s
                    channel.basicAck(envelope.getDeliveryTag(),true);  //第二个参数，是否批量确认
                    System.out.println(new String(body, "UTF-8"));
                    System.out.println("耗时"+(new Date().getTime()-date.getTime()));
                }
            }

        };
        channel.basicQos(2500);
        channel.basicConsume("queue1", false,deliverCallback);//true 自动消息确认
    }

}
