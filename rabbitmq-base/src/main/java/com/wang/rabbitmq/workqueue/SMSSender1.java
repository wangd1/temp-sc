package com.wang.rabbitmq.workqueue;

import com.rabbitmq.client.*;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @Author: wangdi
 * @Date: 2020/11/28
 */
public class SMSSender1 {
    public static void main(String[] args) throws IOException {
        // 创建连接和通道
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 创建队列
        // 队列名称，是否持久化，是否队列私有化，是否自动删除，其他
        channel.queueDeclare(RabbitMQConstant.QUEUE_SMS,false,false,false,null);
        // 消费信息，从rabbitmq服务器中拉取数据
        // 队列名，false代表手动确认消费消息，DefaultConsumer的实现类
        channel.basicConsume(RabbitMQConstant.QUEUE_SMS,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body);
                System.out.println("SMSSender1发送短信成功！"+message);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
