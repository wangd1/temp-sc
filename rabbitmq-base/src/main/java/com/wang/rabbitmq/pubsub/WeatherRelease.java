package com.wang.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

import java.util.Scanner;

/**
 * @Author: wangdi
 * @Date: 2020/11/29
 */
public class WeatherRelease {

    public static void main(String[] args) {
        try(Connection conn = RabbitMQUtils.getConnection();
            Channel channel = conn.createChannel()){
            String input = new Scanner(System.in).next();

            channel.basicPublish(RabbitMQConstant.EXCHANGE_WEATHER,"",null,input.getBytes());
            System.out.println("发送数据成功！");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
