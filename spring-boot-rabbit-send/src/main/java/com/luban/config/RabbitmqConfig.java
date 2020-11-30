package com.luban.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.luban")
public class RabbitmqConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1",5672);
        connectionFactory.setUsername("tmlong");
        connectionFactory.setPassword("123");
        connectionFactory.setVirtualHost("testhost");
        connectionFactory.setPublisherConfirms(true);//开启消息确认
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
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String msg) {
                System.out.println(ack);
                System.out.println(correlationData.getId());
                System.out.println(msg);
            }
        });
        template.setMandatory(true);//失败回调
        //消息解析器
        template.setMessageConverter(new MessageConverter() {
            @Override
            public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
                return null;
            }

            @Override
            public Object fromMessage(Message message) throws MessageConversionException {
                return null;
            }
        });
        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println(message);
                System.out.println(replyCode);
                System.out.println(replyText);
                System.out.println(exchange);
                System.out.println(routingKey);
            }
        });
        return template;
    }
}
