package com.wang.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangdi
 * @Date: 2020/11/29
 */
public class WeatherRelease {

    public static void main(String[] args) {

        Map<String,String > area = new HashMap<>();
        area.put("11.a.1.c","aa1");
        area.put("12.b.2.c","aa2");
        area.put("13.c.3.c","aa3");
        area.put("14.d.4.c","aa4");

        area.put("14.c.a.c","aa6");
        area.put("14.e.b.c","aa7");

        try(Connection conn = RabbitMQUtils.getConnection();
            Channel channel = conn.createChannel()){

            for (String s : area.keySet()) {
                channel.basicPublish(RabbitMQConstant.EXCHANGE_WEATHER_TOPIC,s,null,area.get(s).getBytes());
            }
            System.out.println("发送数据成功！");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
