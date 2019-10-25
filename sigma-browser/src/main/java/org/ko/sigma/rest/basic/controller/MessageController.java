package org.ko.sigma.rest.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.sigma.core.support.Response;
import org.ko.sigma.rest.basic.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Api(tags = "消息发送")
@RestController
@RequestMapping("message")
public class MessageController {

    /**
     * 注入具体实现
     */
    @Autowired
    private Map<String, MessageService> instances;

    @PostMapping("/{sendType}/send/{messageType}")
    @ApiOperation("发送消息")
    public Response send(
            @ApiParam("发送类型") @PathVariable String sendType,
            @ApiParam("使用的模板类型") @PathVariable String messageType,
            @ApiParam("接收方") @NotBlank(message = "接收方不能为空") @RequestParam String address)
            throws Exception {

        instances.get(sendType).send(address, messageType);
        return Response.of();
    }
}
