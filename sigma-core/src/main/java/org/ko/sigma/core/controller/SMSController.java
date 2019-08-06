package org.ko.sigma.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.sigma.core.service.ISMSService;
import org.ko.sigma.core.support.Response;
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
    private ISMSService smsService;

    private static final String DEFAULT_TEMPLATE_CODE = "SMS_172007235";

    @PostMapping("code")
    @ApiOperation("发送手机短信验证码")
    public Response sendCode(
            @ApiParam("使用的模板名称") @RequestParam(required = false, defaultValue = DEFAULT_TEMPLATE_CODE) String name,
            @ApiParam("手机号码") @NotBlank(message = "手机不能为空")  @RequestParam String mobile) {
        smsService.sendCode(name, mobile);
        return Response.of();
    }

}