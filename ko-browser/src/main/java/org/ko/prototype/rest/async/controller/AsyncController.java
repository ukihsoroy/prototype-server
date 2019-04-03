package org.ko.prototype.rest.async.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.ko.prototype.core.support.Response;
import org.ko.prototype.rest.async.component.MockQueue;
import org.ko.prototype.rest.async.component.DeferredResultHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@Api(description = "异步Rest调用")
@Validated
@RequestMapping("async")
public class AsyncController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncController.class);

    @Autowired private MockQueue mockQueue;

    @Autowired private DeferredResultHolder deferredResultHolder;


    @GetMapping("/callable")
    @ApiOperation("callable异步返回")
    public Callable<Response<String>> callable () {
        LOGGER.info("main thread start!");
        Callable<Response<String>> result = () -> {
            LOGGER.info("minor thread start!");
            TimeUnit.SECONDS.sleep(1);
            LOGGER.info("minor thread end!");
            return new Response<>("SUCCESS");
        };
        LOGGER.info("main thread end!");
        return result;
    }

    @GetMapping("deferred/result")
    public DeferredResult<Response<String>> deferredResult () {
        LOGGER.info("main thread start!");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        LOGGER.info("AsyncController#deferredResult order number: {}", orderNumber);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<Response<String>> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        LOGGER.info("main thread end!");
        return result;
    }

}
