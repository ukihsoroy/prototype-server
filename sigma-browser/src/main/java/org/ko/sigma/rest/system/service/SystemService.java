package org.ko.sigma.rest.system.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;

public interface SystemService extends UserDetailsService, SocialUserDetailsService {

}
