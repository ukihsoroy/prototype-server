package org.ko.framework.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ko.framework.core.support.Response;
import org.ko.framework.rest.condition.UserQueryCondition;
import org.ko.framework.rest.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    @ApiOperation("通过ID获取用户")
    public String queryDetail (@PathVariable String id) {
        return "Hello, world!";
    }

}
