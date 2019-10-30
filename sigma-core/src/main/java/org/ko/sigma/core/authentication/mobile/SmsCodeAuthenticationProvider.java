package org.ko.sigma.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @author koshe
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * 进行身份认证的逻辑
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        //通过用户名获取用户信息，这里默认用户名是手机号，要调整一下
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        //如果用户为空，抛出异常
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        //如果获取到用户，构建用户新的认证信息。
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());

        //将老smsToken的details信息拷贝到新的对象中。
        authenticationResult.setDetails(authentication.getDetails());

        return authenticationResult;
    }

    /**
     * 判断
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        //判断类是不是SmsCodeAuthenticationToken这种类型
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
