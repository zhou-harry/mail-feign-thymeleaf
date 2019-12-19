package com.harry.api.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhouhong
 * @version 1.0
 * @title: FeignBasicAuthRequestInterceptor
 * @description: TODO
 * @date 2019/10/15 18:19
 */
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    Logger logger = LoggerFactory.getLogger(getClass());

    public FeignBasicAuthRequestInterceptor() {
        logger.info("====init interceptor");
    }

    /**
     * Feign不开启Hystrix支持时:RequestContextHolder.currentRequestAttributes()才能拿到内容<br/>
     * Feign开启Hystrix支持时:RequestContextHolder.currentRequestAttributes()返回null<br/>
     * 原因在于，Hystrix的默认隔离策略是THREAD 。而 RequestContextHolder 源码中，使用了两个血淋淋的ThreadLocal 。
     * 解决方案见有道云笔记spring篇：《Hystrix传播ThreadLocal对象（两种方案）》
     *
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        logger.info("====token:{}", request.getHeader("Access-Token"));

        //添加token
        requestTemplate.header("Access-Token", request.getHeader("Access-Token"));
    }
}
