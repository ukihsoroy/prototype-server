package org.ko.sigma.conf.security;

import org.ko.sigma.conf.security.session.ExpiredSessionStrategyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import org.ko.sigma.conf.security.authentication.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


//@Configuration
public class BrowserSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

    /**
     * 成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;

    /**
     * 失败处理器
     */
    @Autowired
    private AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;

    @Autowired private DataSource dataSource;

    @Autowired private UserDetailsService userDetailsService;

    @Autowired private SpringSocialConfigurer springSocialConfigurer;

    String[] permitAll = new String[]{
            "/authentication/require",
            "/system/register",
            "/system/login",
            "/session/invalid",
            "/code/*"
    };

    /**
     * 记住我功能的实现,
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository () {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


    /*@Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .apply(springSocialConfigurer) //往过滤器链添加过滤器
                .and()
                .formLogin() //表单登录
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")//用usernamePasswordFilter来处理请求
                .successHandler(authenticationSuccessHandlerImpl)
                .failureHandler(authenticationFailureHandlerImpl)
                //记住我功能配置
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1) //同时存在最大session数为1
                .maxSessionsPreventsLogin(true) //当session达到最大数量后 阻止后面用户登录
                .expiredSessionStrategy(new ExpiredSessionStrategyImpl()) //实现谁踢掉后记录, 有个事件
                .and()
                .and()
                .logout()
                .logoutUrl("/system/signOut")
                .logoutSuccessUrl("/ko-logout.html")
                .and()
                .authorizeRequests()//下面的请求
                .antMatchers(permitAll).permitAll()//放过这个URL-直接放行
                .anyRequest()   //所有的请求
                .authenticated() //都需要认证
                .and()
                .csrf().disable();
    }*/
}
