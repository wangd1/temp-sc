package com.wang.rabbitmq.pubsub;

import com.rabbitmq.client.*;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.Scanner;

/**
 * @Author: wangdi
 * @Date: 2020/11/30
 */
public class Sina {

    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMQUtils.getConnection();
        Channel channel = conn.createChannel();

        channel.queueDeclare(RabbitMQConstant.QUEUE_SINA,false,false,false,null);
        //绑定交换机和队列
        channel.queueBind(RabbitMQConstant.QUEUE_SINA,RabbitMQConstant.EXCHANGE_WEATHER,"");
        channel.basicQos(1);
        channel.basicConsume(RabbitMQConstant.QUEUE_SINA,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪天气收到气象信息："+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
