package org.ko.sigma.rest.log.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.ko.sigma.core.support.Response;
import org.ko.sigma.core.constant.SystemCode;
import org.ko.sigma.data.entity.SendCodeLog;
import org.ko.sigma.rest.log.condition.QuerySendCodeLogCondition;
import org.ko.sigma.rest.log.dto.SendCodeLogDTO;
import org.ko.sigma.rest.log.service.SendCodeLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "验证码日志接口")
@RestController
@RequestMapping("/log/code")
@Validated
public class SendCodeLogController {

    /**
     * 验证码日志service
     */
    @Autowired private SendCodeLogService sendCodeLogService;

    @GetMapping
    @ApiOperation("查询全部验证码日志")
    public Response<List<SendCodeLogDTO>> querySendCodeLogList(@ApiParam("验证码日志查询参数") @ModelAttribute QuerySendCodeLogCondition condition) {
        //1. 查询验证码日志列表数据
        List<SendCodeLog> sendCodeLogs = sendCodeLogService.querySendCodeLogList(condition);

        //2. 如果不为空
        if (CollectionUtils.isNotEmpty(sendCodeLogs)) {
            List<SendCodeLogDTO> sendCodeLogDTOS = sendCodeLogs.stream().map(this::map).collect(Collectors.toList());
            return new Response<>(sendCodeLogDTOS);
        }
        return new Response<>(SystemCode.EMPTY_DATA);
    }

    @GetMapping("{id:\\d+}")
    @ApiOperation("通过ID查询验证码日志")
    public Response<SendCodeLogDTO> querySendCodeLogInfo (@ApiParam("主键") @PathVariable Long id) {
        SendCodeLog sendCodeLog = sendCodeLogService.querySendCodeLogInfo(id);
        return new Response<>(map(sendCodeLog));
    }

    @PostMapping
    @ApiOperation("新增验证码日志")
    public Response<Long> createSendCodeLog (
            @ApiParam("验证码日志传输对象实体") @RequestBody SendCodeLogDTO sendCodeLogDTO) {
        Long sendCodeLogId = sendCodeLogService.createSendCodeLog(map(sendCodeLogDTO));;
        return new Response<>(sendCodeLogId);
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("修改验证码日志")
    public Response<SendCodeLogDTO> updateSendCodeLog (
            @ApiParam("验证码日志ID主键") @PathVariable Long id,
            @ApiParam("验证码日志传输对象实体") @RequestBody SendCodeLogDTO sendCodeLogDTO) {
        SendCodeLog sendCodeLog = sendCodeLogService.updateSendCodeLog(id, map(sendCodeLogDTO));
        return new Response<>(map(sendCodeLog));
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("删除验证码日志")
    public Response<Long> deleteSendCodeLog(@ApiParam("用户ID主键") @PathVariable Long id) {
        Long result = sendCodeLogService.deleteSendCodeLog(id);
        return new Response<>(result);
    }

    /**
     * SendCodeLog mapTo SendCodeLogDTO
     * @param sendCodeLog
     * @return sendCodeLogDTO
     */
    private SendCodeLogDTO map (SendCodeLog sendCodeLog) {
        SendCodeLogDTO sendCodeLogDTO = new SendCodeLogDTO();
        BeanUtils.copyProperties(sendCodeLog, sendCodeLogDTO);
        return sendCodeLogDTO;
    }

    /**
     * SendCodeLogDTO mapTo SendCodeLog
     * @param sendCodeLogDTO
     * @return
     */
    private SendCodeLog map (SendCodeLogDTO sendCodeLogDTO) {
        SendCodeLog sendCodeLog = new SendCodeLog();
        BeanUtils.copyProperties(sendCodeLogDTO, sendCodeLog);
        return sendCodeLog;
    }

}
