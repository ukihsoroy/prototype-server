package org.ko.sigma.core.service.impl;

import freemarker.template.Template;
import org.ko.sigma.core.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(rollbackFor = Throwable.class)
public class IMailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${spring.mail.username}")
    private String from;

    private static String suffix = ".ftl";

    @Override
    public void sendCode(String name, String mail) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(mail);
        helper.setSubject("主题：模板邮件");

        Map<String, Object> model = new HashMap<>();
        model.put("code", "123456");
        Template template = freeMarkerConfigurer.getConfiguration().getTemplate(name + suffix);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(text, true);
        mailSender.send(mimeMessage);

    }
}
