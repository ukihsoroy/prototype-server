package org.ko.shin.core.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * 自定义登录接口
 */
public interface SigmaUserDetailsService extends UserDetailsService, SocialUserDetailsService {

    /**
     * 通过手机号，验证码登录
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException;

}
