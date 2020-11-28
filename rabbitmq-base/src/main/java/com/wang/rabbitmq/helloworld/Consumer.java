package com.wang.rabbitmq.helloworld;

import com.rabbitmq.client.*;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wangdi
 * @Date: 2020/11/28
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接和通道
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        // 创建队列
        // 队列名称，是否持久化，是否队列私有化，是否自动删除，其他
        channel.queueDeclare(RabbitMQConstant.QUEUE_HELLOWORLD,false,false,false,null);
        // 消费信息，从rabbitmq服务器中拉取数据
        // 队列名，false代表手动确认消费消息，DefaultConsumer的实现类
        channel.basicConsume(RabbitMQConstant.QUEUE_HELLOWORLD,false,new Reciver(channel));
    }
}

/**
 * 自定义消费器
 */
class Reciver extends DefaultConsumer{

    private final Channel channel;
    public Reciver(Channel channel){
        super(channel);
        this.channel=channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body);
        System.out.println("接收到的消息："+message);
        System.out.println();
        System.out.println("消息的tagid："+envelope.getDeliveryTag());
        // false代表只签收当前的消息，true签收所有消息
        channel.basicAck(envelope.getDeliveryTag(),false);
    }
}
