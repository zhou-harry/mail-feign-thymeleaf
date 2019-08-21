package com.harry.api.feign;

import com.harry.api.config.FeignClientConfig;
import com.harry.api.domain.EmailDomain;
import com.harry.api.fallback.MailServiceFallbackFactory;
import com.harry.api.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        value = "${provider.service.name}",
        qualifier = "feignMailApi",
        configuration = FeignClientConfig.class,
        fallbackFactory = MailServiceFallbackFactory.class
)
public interface MailApi {

    @GetMapping(value = "/mail/findEmailTemplate")
    String findEmailTemplate(@RequestParam("systemid") String systemid, @RequestParam("serviceid") String serviceid);

    @PostMapping(value = "/mail/notify/email")
    ResponseData sendEmail(@RequestBody EmailDomain domain);

    @GetMapping(value = "/mail/locate")
    String locatePort();
}
