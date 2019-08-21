package com.harry.api.config;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouhong
 * @version 1.0
 * @title: FeignClientConfig
 * @description: TODO
 * @date 2019/8/21 11:40
 */
//@Configuration
public class FeignClientConfig {

    /**
     * Contract feign的默认契约
     * 在FeignClient中的接口中使用Feign自带的注解
     * 例如：@RequestLine("GET /eureka/apps/{serviceName}")
     * @return
     */
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }

    /**
     * 请求接口中要进行基于Http Basic的认证后才能调用
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }

}
