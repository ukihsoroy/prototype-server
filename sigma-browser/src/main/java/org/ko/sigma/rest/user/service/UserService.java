package org.ko.sigma.rest.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ko.sigma.core.support.Response;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.ko.sigma.rest.user.entity.UserEntity;
import org.ko.sigma.rest.user.condition.UserQueryListCondition;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService, IService<UserEntity> {

    /**
     * <p>查询用户列表</p>
     * @param condition
     * @return
     */
    List<UserEntity> queryUserList(UserQueryListCondition condition);

    /**
     * <p>查询用户详细</p>
     * @param id
     * @return
     */
    UserEntity queryUserInfo(Long id);

    /**
     * <p>创建用户</p>
     * @param userEntity
     * @return
     */
    Long createUser(UserEntity userEntity);

    /**
     * <p>更新用户信息</p>
     * @param id
     * @param userEntity
     * @return
     */
    UserEntity updateUser(Long id, UserEntity userEntity);


    /**
     * <p>通过ID删除用户</p>
     * @param id
     * @return
     */
    Long removeUser(Long id);

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    UserEntity login(String username, String password);
}
