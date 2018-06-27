package org.ko.prototype.rest.user.service;

import org.ko.prototype.data.master.domain.AdminUser;
import org.ko.prototype.rest.user.condition.AdminUserQueryCondition;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminUserService extends UserDetailsService {

    List<AdminUser> queryUserList(AdminUserQueryCondition condition);

    AdminUser queryUserInfo(Long id);
}
