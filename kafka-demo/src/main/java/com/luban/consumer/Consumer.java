package com.luban.consumer;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;

public class Consumer {
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
        List<PartitionInfo> list = kafkaConsumer.partitionsFor("my-topic");
        for (PartitionInfo topicPartition : list) {
            System.out.println("第"+topicPartition.partition()+"区");
        }
        List<TopicPartition> topicPartitionList = new ArrayList<>();
        topicPartitionList.add(new TopicPartition("my-topic",0));
        kafkaConsumer.assign(list);//独立消费者，消费指定topic下的分区
        //回调 处理分区再均衡可能导致的偏移量不一致问题
        kafkaConsumer.subscribe(Arrays.asList("my-topic"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                kafkaConsumer.commitSync();
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {

            }
        });

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
