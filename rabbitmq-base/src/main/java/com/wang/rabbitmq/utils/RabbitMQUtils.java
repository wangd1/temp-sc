package com.wang.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: wangdi
 * @Date: 2020/11/28
 */
public class RabbitMQUtils {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory();
    static{
        // 设置连接信息
        connectionFactory.setHost("47.105.138.124");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("wangdi");
        connectionFactory.setPassword("wangdi");
        connectionFactory.setVirtualHost("wangdi_1128");
    }

    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = connectionFactory.newConnection();
            return connection;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
