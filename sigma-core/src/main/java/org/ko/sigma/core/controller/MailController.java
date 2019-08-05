package org.ko.sigma.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.sigma.core.service.IMailService;
import org.ko.sigma.core.support.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Api(tags = "邮件服务")
@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired private IMailService mailService;

    private static final String DEFAULT_MAIL_NAME = "register_mail";

    @PostMapping("code")
    @ApiOperation("使用邮件发送验证码")
    public Response sendCode(
            @ApiParam("使用的模板名称") @RequestParam(required = false, defaultValue = DEFAULT_MAIL_NAME) String name,
            @ApiParam("mail") @NotBlank(message = "邮箱不能为空") @RequestParam String mail) throws Exception {
        mailService.sendCode(name, mail);
        return Response.of();
    }

}
