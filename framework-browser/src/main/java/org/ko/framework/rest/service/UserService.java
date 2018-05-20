package org.ko.framework.rest.service;

import org.ko.framework.rest.condition.UserQueryCondition;
import org.ko.framework.rest.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDTO> queryUserList(UserQueryCondition condition);
}
