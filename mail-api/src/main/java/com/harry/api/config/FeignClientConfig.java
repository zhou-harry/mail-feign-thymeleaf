package com.harry.api.config;

import com.harry.api.interceptor.FeignBasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author zhouhong
 * @version 1.0
 * @title: FeignClientConfig
 * @description: 该config已经通过FeignClient注解进来了（见官网说明），所以此处并不需要加@Configuration注解
 * @date 2019/8/21 11:40
 */
//@Configuration
public class FeignClientConfig {

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
        return new FeignBasicAuthRequestInterceptor();
    }

}
