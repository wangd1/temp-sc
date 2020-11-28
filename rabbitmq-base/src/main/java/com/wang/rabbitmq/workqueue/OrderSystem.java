package com.wang.rabbitmq.workqueue;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

/**
 * @Author: wangdi
 * @Date: 2020/11/28
 */
public class OrderSystem {

    public static void main(String[] args) {
        try(Connection conn = RabbitMQUtils.getConnection();
            Channel channel = conn.createChannel()){
            channel.queueDeclare(RabbitMQConstant.QUEUE_SMS,false,false,false,null);
            for (int i = 0; i < 100; i++) {
                SMS sms = new SMS("乘客"+i+1, "15255096271","您的车票已预定成功");
                String message = new Gson().toJson(sms);
                channel.basicPublish("",RabbitMQConstant.QUEUE_SMS,null,message.getBytes());
            }
            System.out.println("发送数据成功！");
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
