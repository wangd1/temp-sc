package com.wang.sc.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: wangdi
 * @Date: 2020/12/5
 */
@Component
public class RabbitMqListener {

    @RabbitListener(queues = "boot_queue")
    public void listenQueue(Message message){
        System.out.println(message);
    }

}
