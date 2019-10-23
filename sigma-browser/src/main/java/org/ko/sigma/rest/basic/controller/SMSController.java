package org.ko.sigma.rest.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.sigma.core.support.Response;
import org.ko.sigma.rest.basic.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Api(tags = "短信服务")
@RestController
@RequestMapping("sms")
public class SMSController {

    @Autowired
    private SmsService smsService;

    @PostMapping("code")
    @ApiOperation("发送手机短信验证码")
    public Response sendCode(@ApiParam("手机号码") @NotBlank(message = "手机不能为空")  @RequestParam String mobile,
            @ApiParam(value = "使用的模板类型", allowableValues = "1.注册 2.登陆 3.找回密码") @RequestParam Short type
            ) {
        smsService.sendCode(mobile, type);
        return Response.of();
    }

}