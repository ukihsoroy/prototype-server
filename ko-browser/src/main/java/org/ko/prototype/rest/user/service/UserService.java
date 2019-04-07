package org.ko.prototype.rest.user.service;

import org.ko.prototype.data.master.domain.User;
import org.ko.prototype.rest.user.condition.UserQueryCondition;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * <p>查询用户列表</p>
     * @param condition
     * @return
     */
    List<User> queryUserList(UserQueryCondition condition);

    /**
     * <p>查询用户详细</p>
     * @param id
     * @return
     */
    User queryUserInfo(Long id);

    /**
     * <p>创建用户</p>
     * @param user
     * @return
     */
    Long createUser(User user);

    /**
     * <p>更新用户信息</p>
     * @param id
     * @param user
     * @return
     */
    User updateUser(Long id, User user);


    /**
     * <p>通过ID删除用户</p>
     * @param id
     * @return
     */
    Long removeUser(Long id);
}
