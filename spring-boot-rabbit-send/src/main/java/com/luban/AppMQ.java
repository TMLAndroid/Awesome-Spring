package com.luban;

import com.luban.config.RabbitmqConfig;
import com.luban.util.RabbitMessageSend;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AppMQ {
    public static void main(String[] args) {
//        SpringApplication.run(AppMQ.class);
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(RabbitmqConfig.class);
        RabbitMessageSend rabbitMessageSend = annotationConfigApplicationContext.getBean(RabbitMessageSend.class);
        rabbitMessageSend.testSend();
        //annotationConfigApplicationContext.close();
    }
}
