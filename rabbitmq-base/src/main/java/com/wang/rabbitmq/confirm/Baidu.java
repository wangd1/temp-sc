package com.wang.rabbitmq.confirm;

import com.rabbitmq.client.*;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @Author: wangdi
 * @Date: 2020/11/30
 */
public class Baidu {

    public static void main(String[] args) throws IOException {
        Connection conn = RabbitMQUtils.getConnection();
        Channel channel = conn.createChannel();
        channel.queueDeclare(RabbitMQConstant.QUEUE_BAIDU,false,false,false,null);

        //绑定交换机和队列
        channel.queueBind(RabbitMQConstant.QUEUE_BAIDU,RabbitMQConstant.EXCHANGE_WEATHER_TOPIC,"14.#");

        channel.basicConsume(RabbitMQConstant.QUEUE_BAIDU,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("百度天气收到气象信息："+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
