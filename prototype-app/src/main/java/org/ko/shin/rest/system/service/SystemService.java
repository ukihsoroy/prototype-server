package org.ko.prototype.rest.system.service;

import org.ko.prototype.core.authentication.SigmaUserDetailsService;
import org.ko.prototype.rest.user.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;

import javax.servlet.http.HttpServletRequest;

public interface SystemService extends SigmaUserDetailsService {

    /**
     * 注册
     * @param userDTO
     * @param request
     * @return
     */
    Long register(UserDTO userDTO, HttpServletRequest request);

    /**
     * 校验用户唯一索引字段是否重复
     * @param column
     */
    void validUserUnique(String column, String value);
}
