package org.ko.sigma.rest.testunit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ko.sigma.core.support.api.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testunit/version")
@Api(tags = "多版本Api测试")
public class ApiVersionController {

    @GetMapping
    @ApiOperation("第一个版本Api")
    public String apiVersion1() {
        return "v1";
    }

    @GetMapping
    @ApiVersion(2)
    @ApiOperation("第二个版本Api")
    public String apiVersion2() {
        return "v2";
    }
}
