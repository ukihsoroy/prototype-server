package org.ko.sigma.rest.system.service;

import org.ko.sigma.rest.user.bean.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SystemService extends UserDetailsService {

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    UserEntity login(String username, String password);

    UserEntity signIn(UserEntity userEntity);

    Long logout();
}
