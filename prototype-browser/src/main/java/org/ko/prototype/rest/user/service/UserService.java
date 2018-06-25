package org.ko.prototype.rest.user.service;

import org.ko.prototype.rest.user.condition.AdminUserQueryCondition;
import org.ko.prototype.rest.user.dto.AdminUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<AdminUserDTO> queryUserList(AdminUserQueryCondition condition);
}
