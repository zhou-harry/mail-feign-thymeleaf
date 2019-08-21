package com.harry.mail.controller;

import com.harry.api.domain.EmailDomain;
import com.harry.api.feign.MailApi;
import com.harry.api.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class MailClientController{

    @Autowired
//    @Qualifier("feignMailApi")
    private MailApi mailApi;

    @GetMapping("/findTemplate")
    public String findEmailTemplate(@RequestParam("systemid")String systemid, @RequestParam("serviceid")String serviceid) {
        return mailApi.findEmailTemplate(systemid, serviceid);
    }

    @PostMapping("/notify/email")
    public ResponseData sendEmail(@RequestBody EmailDomain domain) {
        return mailApi.sendEmail(domain);
    }

    @GetMapping("/locate")
    public String getLocate() {
        return mailApi.locatePort();
    }
}
