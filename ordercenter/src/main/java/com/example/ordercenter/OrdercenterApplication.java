package com.example.ordercenter;

import com.liang.framework.springmqstarter.rabbitmq.FirstSender;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.example.*.mapper")
public class OrdercenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdercenterApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    FirstSender firstSender(){return  new FirstSender();}
}
