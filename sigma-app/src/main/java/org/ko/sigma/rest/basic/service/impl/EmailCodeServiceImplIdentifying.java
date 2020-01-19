package org.ko.sigma.rest.basic.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.ko.sigma.core.exception.BusinessException;
import org.ko.sigma.rest.basic.service.IdentifyingCodeService;
import org.ko.sigma.rest.dict.service.DictService;
import org.ko.sigma.rest.log.service.SendCodeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;

@Service("email")
@Transactional(rollbackFor = Throwable.class)
public class EmailCodeServiceImplIdentifying implements IdentifyingCodeService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private DictService dictService;

    @Autowired
    private SendCodeLogService sendCodeLogService;

    private static final String DEFAULT_MAIL_NAME = "register_mail";

    private static final String SEND_TYPE = "email";

    @Override
    public void sendCode(String address, String messageType) throws Exception {
        var mimeMessage = mailSender.createMimeMessage();

        var helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(address);
        helper.setSubject("主题：模板邮件");

        var model = new HashMap<>();
        model.put("code", "123456");
        var suffix = ".ftl";
        var template = freeMarkerConfigurer.getConfiguration().getTemplate(DEFAULT_MAIL_NAME + suffix);
        var text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        helper.setText(text, true);
        mailSender.send(mimeMessage);
    }

    @Override
    public void checkCode(String address, String messageType, String code) throws Exception {
        var logCode = sendCodeLogService.findCodeByType(SEND_TYPE, messageType, address);

        if (StringUtils.isEmpty(logCode) || !code.equalsIgnoreCase(logCode)) {
            throw new BusinessException("验证码不正确");
        }
    }
}
