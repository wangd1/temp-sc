package com.wang.sc.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangdi
 * @Date: 2020/12/5
 */
@Configuration
public class RabbitMQConfig {

    @Bean("bootExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange("boot_topic_exchange").durable(true).build();
    }

    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable("boot_queue").build();
    }

    @Bean
    public Binding bindQueueExchange(@Qualifier("bootQueue") Queue queue,@Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

}
