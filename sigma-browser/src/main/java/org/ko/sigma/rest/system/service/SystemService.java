package org.ko.sigma.rest.system.service;

import org.ko.sigma.rest.user.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;

import javax.servlet.http.HttpServletRequest;

public interface SystemService extends UserDetailsService, SocialUserDetailsService {

    Long register(UserDTO userDTO, HttpServletRequest request);
}
