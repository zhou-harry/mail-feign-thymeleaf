package com.harry.mail.service;

import com.harry.api.domain.EmailDomain;
import com.harry.api.entity.NotifyTemplateEntity;
import com.harry.api.feign.MailApi;
import com.harry.api.response.ResponseData;
import com.harry.mail.repository.MailRepository;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouhong
 * @version 1.0
 * @title: MailService
 * @description: TODO
 * @date 2019/8/20 11:37
 */
@Service
public class MailService implements MailApi {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${server.port}")
    private Integer port;

    private Random random = new Random();

    private final MailRepository repository;
    private final Mailer mailer;
    //template模板引擎
    private final TemplateEngine templateEngine;

    public MailService(MailRepository repository, Mailer mailer, TemplateEngine templateEngine) {
        this.repository = repository;
        this.mailer = mailer;
        this.templateEngine = templateEngine;
    }

    @Override
    public String findEmailTemplate(String systemid, String serviceid) {
        logger.info("当前模板系统编号：{},服务号：{}", systemid, serviceid);
        return repository.findEmailTemplate(new NotifyTemplateEntity(systemid,serviceid));
    }

    @Override
    public ResponseData sendEmail(EmailDomain domain) {
        //从数据库取模板
        String emailTemplate = repository.findEmailTemplate(new NotifyTemplateEntity(domain.getFromID(), domain.getServiceID()));
        if (emailTemplate == null) {
            return new ResponseData(ResponseData.ERROR, "找不到匹配的邮件模板", "");
        }
        //定义模板数据
        Context context = new Context();
        context.setVariables(domain.getVariables());
        //获取thymeleaf的html模板
//        String emailContent = templateEngine.process("/mail/mail", context); //指定模板路径

        //动态模板
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setCacheable(true);
        stringTemplateResolver.setTemplateMode(TemplateMode.HTML);
        springTemplateEngine.setTemplateResolver(stringTemplateResolver);


        String emailContent = springTemplateEngine.process(emailTemplate, context);

        System.out.println("邮件内容：" + emailContent);
        //发送邮件
        Email email = EmailBuilder.startingBlank()
                .to(null, domain.getTo())
                .withSubject(domain.getSubject())
                .withHTMLText(emailContent)
                .buildEmail();

        mailer.sendMail(email, true);

        ResponseData data = new ResponseData(domain.getFromID() + "-" + random.nextInt(100000));
        data.setMessage(ResponseData.SUCCESS);

        return data;
    }

    @Override
    public String locatePort() {
        long executeTime = random.nextInt(300);
        if (executeTime>200){
            throw new RuntimeException("执行请求超时："+executeTime);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(executeTime);
        } catch (InterruptedException e) {
            throw new RuntimeException("执行请求超时异常："+executeTime);
        }
        return "当前访问的服务端口：" + port;
    }

}
