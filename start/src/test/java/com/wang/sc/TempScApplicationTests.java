package com.wang.sc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TempScApplicationTests {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Resource
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            String result = restTemplate.getForObject("http://localhost:8089/user/process", String.class);
            Thread.sleep(1000);
        }
    }

}
