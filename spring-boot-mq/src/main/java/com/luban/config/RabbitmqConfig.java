package com.luban.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost",5672);
        connectionFactory.setUsername("tmlong");
        connectionFactory.setPassword("123");
        connectionFactory.setVirtualHost("testhost");
        return connectionFactory;
    }


    @Bean
    public DirectExchange defaultExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    public Queue queue(){
        return new Queue("testQueue",true);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(defaultExchange()).with("direct.key");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory());;
        simpleRabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);;
        return simpleRabbitListenerContainerFactory;
    }
}
