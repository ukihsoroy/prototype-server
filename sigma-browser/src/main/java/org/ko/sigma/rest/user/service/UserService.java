package org.ko.sigma.rest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ko.sigma.rest.user.bean.UserEntity;
import org.ko.sigma.rest.user.condition.QueryUserPageCondition;
import org.ko.sigma.rest.user.dto.UserDTO;

public interface UserService extends IService<UserEntity> {

    /**
     * <p>查询用户列表</p>
     * @param condition
     * @return
     */
    IPage<UserDTO> queryUserList(QueryUserPageCondition<UserEntity> condition);

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

}
