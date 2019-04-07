package org.ko.prototype.rest.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.ko.prototype.core.support.Response;
import org.ko.prototype.core.type.SystemCode;
import org.ko.prototype.data.master.domain.User;
import org.ko.prototype.rest.user.condition.UserQueryCondition;
import org.ko.prototype.rest.user.dto.UserDTO;
import org.ko.prototype.rest.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api(description = "用户接口")
@RestController
@RequestMapping("user")
@Validated
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired private UserService userService;

    @GetMapping
    @ApiOperation("查询用户列表")
    public Response<List<UserDTO>> queryUserList(UserQueryCondition condition) {
        //1. 查询用户列表数据
        List<User> users = userService.queryUserList(condition);

        //2. 如果不为空
        if (CollectionUtils.isNotEmpty(users)) {
            List<UserDTO> userDTOS = users.stream().map(this::mapAdminUser).collect(Collectors.toList());
            return new Response<>(userDTOS);
        }
        return new Response<>(SystemCode.EMPTY_DATA);
    }

    @GetMapping("{id:\\d+}")
    @ApiOperation("通过ID获取用户详细")
    public Response<UserDTO> queryUserInfo (
            @ApiParam("用户ID") @PathVariable Long id) {
        LOGGER.info("UserController#queryUserInfo");
        User user = userService.queryUserInfo(id);
        if (Objects.nonNull(user)) {
            return new Response<>(this.mapAdminUser(user));
        }
        return new Response<>(SystemCode.EMPTY_DATA);
    }

    @PostMapping
    @ApiOperation("新建用户")
    public Response<Long> createUser (
            @ApiParam("用户传输对象实体") @RequestBody UserDTO userDTO) {
        Long adminUserId = userService.createUser(mapAdminUser(userDTO));
        return new Response<>(adminUserId);
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("通过ID更新用户信息")
    public Response<UserDTO> updateUser (
            @ApiParam("用户ID主键") @PathVariable Long id,
            @ApiParam("用户传输对象实体") @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(id, mapAdminUser(userDTO));
        return new Response<>(mapAdminUser(user));
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("通过ID删除用户")
    public Response<Long> removeUser (
            @ApiParam("用户ID") @PathVariable Long id) {
        Long result = userService.removeUser(id);
        return new Response<>(result);
    }

    /**
     * User mapTo UserDTO
     * @param user
     * @return
     */
    private UserDTO mapAdminUser (User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    /**
     * UserDTO mapTo User
     * @param userDTO
     * @return
     */
    private User mapAdminUser (UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }


}
