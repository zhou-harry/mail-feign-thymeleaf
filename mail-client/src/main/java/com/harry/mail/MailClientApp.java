package com.harry.mail;

import com.harry.api.feign.MailApi;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = MailApi.class)
public class MailClientApp {

    public static void main(String[] args) {
        SpringApplication.run(MailClientApp.class, args);
    }

    /**
     * 修改负载均衡策略
     * @return
     */
    @Bean
    public IRule feignRule() {
        return new ZoneAvoidanceRule();
    }
}
