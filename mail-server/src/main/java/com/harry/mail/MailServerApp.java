package com.harry.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync//启用异步调用
@EnableHystrix //激活服务短路
public class MailServerApp {

    public static void main(String[] args) {
        SpringApplication.run(MailServerApp.class, args);
    }

}
