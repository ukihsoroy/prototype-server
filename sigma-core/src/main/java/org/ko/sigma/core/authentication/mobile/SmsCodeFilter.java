package org.ko.sigma.core.authentication.mobile;

import org.apache.commons.lang3.StringUtils;
import org.ko.sigma.core.exception.SigmaAuthenticationException;
import org.ko.sigma.core.properties.SecurityProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * #{
 *     @see OncePerRequestFilter spring 开放的过滤器 保证我们的过滤器只会被调用1次
 *     @see InitializingBean 在其他变量都初始化完毕以后来初始化url
 * }
 */
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private ISmsCodeService smsCodeService;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 当其他配置都被初始化完毕后加载
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
//        String[] configUrls = StringUtils.split(securityProperties.getCode().getImage().getUrl(), ",");
//        if (ArrayUtils.isNotEmpty(configUrls)) {
//            for (String configUrl : configUrls) {
//                urls.add(StringUtils.trimToNull(configUrl));
//            }
//        }
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;

        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                action = true;
                break;
            }
        }

        if (action && StringUtils.endsWithIgnoreCase(request.getMethod(), "POST")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (SigmaAuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {

        //查询短信验证码
        String codeInDatabase = smsCodeService
                .findSmsCode(ServletRequestUtils.getStringParameter(request.getRequest(), "mobile"));

        //获取请求的手机验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new SigmaAuthenticationException("验证码不能为空");
        }

        if (Objects.isNull(codeInDatabase)) {
            throw new SigmaAuthenticationException("验证码不存在");
        }

//        if (codeInSession.isExpired()) {
//            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
//            throw new SigmaAuthenticationException("验证码已过期");
//        }

        if (!StringUtils.equals(codeInDatabase, codeInRequest)) {
            throw new SigmaAuthenticationException("验证码不匹配");
        }

    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public void setSmsCodeService(ISmsCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
    }
}
