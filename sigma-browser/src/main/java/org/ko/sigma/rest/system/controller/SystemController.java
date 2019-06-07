package org.ko.sigma.rest.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.ko.sigma.core.support.Response;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.ko.sigma.rest.user.entity.UserEntity;
import org.ko.sigma.rest.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "系统操作相关")
@Validated
public class SystemController {

    @Autowired private UserService userService;

    @PostMapping("login")
    public Response<UserDTO> login (
            @ApiParam("用户名") @RequestParam String username,
            @ApiParam("用户口令") @RequestParam String password) {
        UserEntity userEntity =  userService.login(username, password);
        return new Response<>(map(userEntity));
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
