package com.wang.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wangdi
 * @Date: 2020/11/28
 */
public class Producer {
    public static void main(String[] args){
        // 创建连接和通道
        try(Connection connection = RabbitMQUtils.getConnection();
            Channel channel = connection.createChannel()){
            // 创建队列
            // 队列名称，是否持久化，是否队列私有化，是否自动删除，其他
            channel.queueDeclare(RabbitMQConstant.QUEUE_HELLOWORLD,false,false,false,null);
            String message = "wangdi哈哈哈1221zzz";
            // 交换机，队列，其他，消息字节数组
            channel.basicPublish("",RabbitMQConstant.QUEUE_HELLOWORLD,null,message.getBytes());
            System.out.println("发送成功======");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
