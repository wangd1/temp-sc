package com.wang.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: wangdi
 * @Date: 2020/11/29
 */
public class WeatherRelease {

    public static void main(String[] args) {

        Map<String,String > area = new HashMap<>();
        area.put("11","aa1");
        area.put("12","aa2");
        area.put("13","aa3");
        area.put("14","aa4");

        area.put("21","bb1");
        area.put("22","bb2");
        area.put("23","bb3");
        area.put("24","bb4");

        try(Connection conn = RabbitMQUtils.getConnection();
            Channel channel = conn.createChannel()){

            for (String s : area.keySet()) {
                channel.basicPublish(RabbitMQConstant.EXCHANGE_WEATHER_ROUTING,s,null,area.get(s).getBytes());
            }
            System.out.println("发送数据成功！");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
