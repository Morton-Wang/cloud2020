package com.wqm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients  //开启feign
public class OrderFeignMain80 {
    public static void main(String[] args){
        SpringApplication.run(OrderFeignMain80.class,args);
    }
}
