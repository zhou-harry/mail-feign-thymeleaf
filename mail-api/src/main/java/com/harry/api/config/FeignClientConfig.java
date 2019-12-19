package com.harry.api.config;

import com.harry.api.interceptor.FeignBasicAuthRequestInterceptor;
import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author zhouhong
 * @version 1.0
 * @title: FeignClientConfig
 * @description: TODO
 * @date 2019/8/21 11:40
 */
//@Configuration
public class FeignClientConfig {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Contract feign的默认契约
     * 在FeignClient中的接口中使用Feign自带的注解
     * 例如：@RequestLine("GET /eureka/apps/{serviceName}")
     *
     * @return
     */
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }

//    @Bean
//    public RequestContextListener requestContextListener() {
//        logger.info("init listener...");
//        return new RequestContextListener();
//    }

    /**
     * 请求接口中要进行基于Http Basic的认证后才能调用
     *
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("user", "password");
        return new FeignBasicAuthRequestInterceptor();
    }

}
