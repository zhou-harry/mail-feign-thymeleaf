package com.harry.mail.controller;

import com.harry.api.domain.EmailDomain;
import com.harry.api.response.ResponseData;
import com.harry.mail.service.MailService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mail")
public class MailController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/findEmailTemplate",method = RequestMethod.GET)
    public String findEmailTemplate(@RequestParam("systemid") String systemid, @RequestParam("serviceid") String serviceid) {
        return mailService.findEmailTemplate(systemid, serviceid);
    }

    @Async//@EnableAsync//启用异步调用
    @RequestMapping(value = "/notify/email",method = RequestMethod.POST)
    public ResponseData sendEmail(@RequestBody EmailDomain domain) {
        return mailService.sendEmail(domain);
    }

    @HystrixCommand(
            commandProperties = { // Command 熔断配置
                    // 设置操作时间为 100 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            },
            fallbackMethod = "fallbackForgetLocate" // 设置 fallback 方法
    )
    @RequestMapping(value = "/locate",method = RequestMethod.GET)
    public String getLocate() {
        return mailService.locatePort();
    }

    public String fallbackForgetLocate() {
        return "default port is：80";
    }

}
