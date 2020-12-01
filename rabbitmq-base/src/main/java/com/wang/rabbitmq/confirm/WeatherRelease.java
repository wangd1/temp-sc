package com.wang.rabbitmq.confirm;

import com.rabbitmq.client.*;
import com.wang.rabbitmq.utils.RabbitMQConstant;
import com.wang.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangdi
 * @Date: 2020/11/29
 */
public class WeatherRelease {

    public static void main(String[] args) throws IOException {

        Map<String,String > area = new HashMap<>();
        area.put("11.a.1.c","aa1");
        area.put("12.b.2.c","aa2");
        area.put("13.c.3.c","aa3");
        area.put("14.d.4.d","aa4");

        area.put("14.c.a.a","aa6");
        area.put("14.e.b.b","aa7");

        Connection conn = RabbitMQUtils.getConnection();
        Channel channel = conn.createChannel();
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("消息已被接收"+l);
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("消息已被拒收"+l);
            }
        });

        channel.addReturnListener(new ReturnCallback() {
            @Override
            public void handle(Return r) {
                System.out.println("===============");
                System.out.println("return编码："+r.getReplyCode()+";return描述："+r.getReplyText());
                System.out.println("jhj："+r.getExchange()+";routing："+r.getRoutingKey());
            }
        });

        for (String s : area.keySet()) {
            channel.basicPublish(RabbitMQConstant.EXCHANGE_WEATHER_TOPIC,s,null,area.get(s).getBytes());
        }
        System.out.println("发送数据成功！");
    }
}
