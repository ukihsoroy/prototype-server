package org.ko.prototype.rest.user.service;

import org.ko.prototype.data.master.domain.AdminUser;
import org.ko.prototype.rest.user.condition.AdminUserQueryCondition;
import org.ko.prototype.rest.user.dto.AdminUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminUserService extends UserDetailsService {

    /**
     * <p>查询用户列表</p>
     * @param condition
     * @return
     */
    List<AdminUser> queryUserList(AdminUserQueryCondition condition);

    /**
     * <p>查询用户详细</p>
     * @param id
     * @return
     */
    AdminUser queryUserInfo(Long id);

    /**
     * <p>创建用户</p>
     * @param adminUser
     * @return
     */
    Long createUser(AdminUser adminUser);

    /**
     * <p>更新用户信息</p>
     * @param id
     * @param adminUser
     * @return
     */
    AdminUser updateUser(Long id, AdminUser adminUser);


    /**
     * <p>通过ID删除用户</p>
     * @param id
     * @return
     */
    Long removeUser(Long id);
}
