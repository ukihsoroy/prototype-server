package org.ko.prototype.rest.user.controller;

import io.swagger.annotations.Api;
import org.ko.prototype.core.support.Response;
import org.ko.prototype.rest.user.condition.AdminUserQueryCondition;
import org.ko.prototype.rest.user.dto.AdminUserDTO;
import org.ko.prototype.rest.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("user")
@Api("用户模块")
@Validated
public class AdminUserController {

    @Autowired private UserService userService;

    @GetMapping
    public Response<List<AdminUserDTO>> queryUserList(AdminUserQueryCondition condition) {
        List<AdminUserDTO> adminUserDTOS = userService.queryUserList(condition);
        return new Response<>(adminUserDTOS);
    }


}
