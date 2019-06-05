package org.ko.sigma.rest.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.ko.sigma.core.bean.entity.UserEntity;
import org.ko.sigma.core.support.Response;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.rest.user.condition.UserQueryListCondition;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.ko.sigma.rest.user.service.UserService;
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
    public Response<List<UserDTO>> queryUserList(@ApiParam("列表查询参数") @ModelAttribute UserQueryListCondition condition) {
        //1. 查询用户列表数据
        List<UserEntity> userEntities = userService.queryUserList(condition);

        //2. 如果不为空
        if (CollectionUtils.isNotEmpty(userEntities)) {
            List<UserDTO> userDTOS = userEntities.stream().map(this::map).collect(Collectors.toList());
            return new Response<>(userDTOS);
        }
        return new Response<>(SystemCode.EMPTY_DATA);
    }

    @GetMapping("{id:\\d+}")
    @ApiOperation("通过ID获取用户详细")
    public Response<UserDTO> queryUserInfo (
            @ApiParam("用户ID") @PathVariable Long id) {
        LOGGER.info("UserController#queryUserInfo");
        UserEntity userEntity = userService.queryUserInfo(id);
        if (Objects.nonNull(userEntity)) {
            return new Response<>(this.map(userEntity));
        }
        return new Response<>(SystemCode.EMPTY_DATA);
    }

    @PostMapping
    @ApiOperation("新建用户")
    public Response<Long> createUser (
            @ApiParam("用户传输对象实体") @RequestBody UserDTO userDTO) {
        Long adminUserId = userService.createUser(map(userDTO));
        return new Response<>(adminUserId);
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("通过ID更新用户信息")
    public Response<UserDTO> updateUser (
            @ApiParam("用户ID主键") @PathVariable Long id,
            @ApiParam("用户传输对象实体") @RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.updateUser(id, map(userDTO));
        return new Response<>(map(userEntity));
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("通过ID删除用户")
    public Response<Long> removeUser (
            @ApiParam("用户ID") @PathVariable Long id) {
        Long result = userService.removeUser(id);
        return new Response<>(result);
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

    /**
     * UserDTO mapTo UserEntity
     * @param userDTO
     * @return
     */
    private UserEntity map (UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        return userEntity;
    }


}
