package com.harry.api.fallback;

import com.harry.api.domain.EmailDomain;
import com.harry.api.feign.MailApi;
import com.harry.api.response.ResponseData;

public class MailServiceFallback implements MailApi {

    private final Throwable cause;

    public MailServiceFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String findEmailTemplate(String systemid, String serviceid) {
        return "fallback; reason was: " + cause.getMessage();
    }

    @Override
    public ResponseData sendEmail(EmailDomain domain) {
        return new ResponseData(ResponseData.ERROR,"fallback; reason was: " + cause.getMessage(),null);
    }

    @Override
    public String locatePort() {
        return "fallback; reason was: " + cause.getMessage();
    }
}
