package com.luban.provider;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        //key 序列化器 决定哪个分区
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        //value 序列化器
        properties.setProperty("value.serializer",StringSerializer.class.getName());


        KafkaProducer kafkaProducer = new KafkaProducer(properties);



        //异步
//        kafkaProducer.send(record, new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//
//            }
//        });
        //同步
        for (int i = 0; i < 10; i++) {
            ProducerRecord record = new ProducerRecord("my-topic",null,"hello"+i);
            Future<RecordMetadata> send = kafkaProducer.send(record);
            RecordMetadata recordMetadata = send.get();
            System.out.println("偏移量："+recordMetadata.offset()+" 分区---"+recordMetadata.partition());

            ProducerRecord record1 = new ProducerRecord("my-topic","1","hello"+i);
            Future<RecordMetadata> send1 = kafkaProducer.send(record1);
            RecordMetadata recordMetadata1 = send1.get();
            System.out.println("偏移量："+recordMetadata1.offset()+" 分区---"+recordMetadata1.partition());
        }

        kafkaProducer.flush();
        kafkaProducer.close();



    }
}
