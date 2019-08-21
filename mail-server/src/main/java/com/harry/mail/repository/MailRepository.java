package com.harry.mail.repository;

import com.harry.api.entity.NotifyTemplateEntity;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MailRepository {

    @Autowired
    private SqlSessionTemplate sessionTemplate;

    public String findEmailTemplate(NotifyTemplateEntity entity) {
        List<String> list = sessionTemplate.selectList("findEmailTemplate", entity);
        if (list != null&&list.size()>0) {
            return list.get(0);
        }
        return null;
    }
}
