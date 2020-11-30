package com.luban.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer1 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        //key 反序列化器 决定哪个分区
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        //value 反序列化器
        properties.setProperty("value.deserializer",StringDeserializer.class.getName());

        //消费者群组
        properties.setProperty("group.id","1111");
        //是否自动提交
        properties.setProperty("enable.auto.commit","false");

        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Arrays.asList("my-topic"));

        //同步提交
        //kafkaConsumer.commitSync();
        //异步提交
        //kafkaConsumer.commitAsync();
        try {
            while (true) {
                ConsumerRecords<String, String> poll = kafkaConsumer.poll(500);
                for (ConsumerRecord<String, String> context : poll) {
                    System.out.println("消息所在分区" + context.partition() +
                            "消息偏移量 " + context.offset() +
                            "key " + context.key() +
                            "value " + context.value());
                    System.out.println("处理消费到西溪逻辑");
                }

                //异步提交偏移量 偶尔的失败不怎么影响
                kafkaConsumer.commitAsync();


            }
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            try {
                kafkaConsumer.commitSync();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                kafkaConsumer.close();
            }
        }
    }
}
