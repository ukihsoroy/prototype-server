package org.ko.prototype.rest.service;

import org.ko.prototype.rest.condition.UserQueryCondition;
import org.ko.prototype.rest.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDTO> queryUserList(UserQueryCondition condition);
}
