package org.ko.prototpye.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ko.prototpye.core.support.Response;
import org.ko.prototpye.rest.condition.UserQueryCondition;
import org.ko.prototpye.rest.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Validated
@Api("用户模块")
public class UserController {

    @GetMapping
    @ApiOperation("获取用户列表")
    public Response<UserDTO> queryList (UserQueryCondition condition) {
        return new Response<UserDTO>(new UserDTO());
    }
}
