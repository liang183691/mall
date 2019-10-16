package com.example.ordercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrdercenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdercenterApplication.class, args);
    }

}
