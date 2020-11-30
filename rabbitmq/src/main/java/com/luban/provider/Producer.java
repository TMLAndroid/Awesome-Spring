package com.luban.provider;

import com.luban.utils.ConnectionUtil;
import com.rabbitmq.client.*;

public class Producer {
    public static void main(String[] args) {
        try {
            sendByExchange("Hello tmlong");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendByExchange(String message) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列 channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
        // 声明exchange
       // channel.exchangeDeclare(ConnectionUtil.EXCHANGE_NAME, "fanout"); //交换机和队列绑定
        //channel.queueBind(ConnectionUtil.QUEUE_NAME, ConnectionUtil.EXCHANGE_NAME, "");

        //交换机和队列绑定
        channel.exchangeDelete("exchangeTest");//先删除 BuiltinExchangeType.FANOUT
        channel.exchangeDeclare("exchangeTest", BuiltinExchangeType.DIRECT);
        channel.queueBind("queue1", "exchangeTest", "info.user");
        channel.queueBind("queue2", "exchangeTest", "error.user");
        for (int i = 0; i < 20000; i++) {
            channel.basicPublish("exchangeTest", "info.user", null,
                    (message+i).getBytes());
            System.out.println("发送的信息为:" + message);
        }

//        channel.basicPublish(ConnectionUtil.EXCHANGE_NAME, "", null,
//                message.getBytes()); System.out.println("发送的信息为:" + message);
                channel.close();
        connection.close();
    }
}
