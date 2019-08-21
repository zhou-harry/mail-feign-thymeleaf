package com.harry.api.fallback;

import com.harry.api.feign.MailApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhouhong
 * @version 1.0
 * @title: MailServiceFallbackFactory
 * @description: TODO
 * @date 2019/8/20 18:29
 */
@Component
public class MailServiceFallbackFactory implements FallbackFactory<MailApi> {

    @Override
    public MailApi create(Throwable cause) {
        return new MailServiceFallback(cause);
    }
}
