package org.ko.sigma.rest.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.ko.sigma.core.support.Response;
import org.ko.sigma.rest.system.service.SystemService;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.ko.sigma.rest.user.bean.UserEntity;
import org.ko.sigma.rest.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(description = "系统操作相关")
@Validated
public class SystemController {

    @Autowired private SystemService systemService;

    @PostMapping("login")
    @ApiOperation("账号密码登录")
    public Response<UserDTO> login (
            @ApiParam("用户名") @RequestParam String username,
            @ApiParam("用户口令") @RequestParam String password) {
        UserEntity userEntity =  systemService.login(username, password);
        return new Response<>(map(userEntity));
    }

    @GetMapping("logout")
    @ApiOperation("登出系统")
    public Response<Long> logout () {
        return new Response<>(1L);
    }

    @PostMapping("signup")
    @ApiOperation("注册新用户")
    public Response<UserDTO> signup (@ApiParam("用户信息对象") @RequestBody UserDTO userDTO) {
        return new Response<>(userDTO);
    }



    /**
     * UserEntity mapTo UserDTO
     * @param userEntity
     * @return
     */
    private UserDTO map (UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        return userDTO;
    }


}
