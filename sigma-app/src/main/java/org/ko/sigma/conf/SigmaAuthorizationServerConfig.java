package org.ko.sigma.conf;

import org.ko.sigma.authentication.AuthenticationFailureHandlerImpl;
import org.ko.sigma.authentication.AuthenticationSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SigmaAuthorizationServerConfig extends WebSecurityConfigurerAdapter {

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

    @Autowired private SpringSocialConfigurer springSocialConfigurer;

    private String[] permitAll = new String[]{
           "/**"
    };

    /**
     * Spring security manager
     * 升级boot2.0后容器中没有，自己添加注入
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
               /* .formLogin() //表单登录
                .loginPage("/authentication/require")
                .loginProcessingUrl("/login") //用usernamePasswordFilter来处理请求
                .successHandler(authenticationSuccessHandlerImpl)
                .failureHandler(authenticationFailureHandlerImpl)
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid")
//                    .maximumSessions(1) //同时存在最大session数为1
//                    .maxSessionsPreventsLogin(true) //当session达到最大数量后 阻止后面用户登录
//                    .expiredSessionStrategy(new ExpiredSessionStrategyImpl()) //实现谁踢掉后记录, 有个事件
//                    .and()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()*/
                .authorizeRequests()//下面的请求
                .antMatchers(permitAll).permitAll();//放过这个URL-直接放行
//                .anyRequest()   //所有的请求
//                .authenticated() //都需要认证
//                .and()
//                .csrf().disable()
//                .apply(springSocialConfigurer); //往过滤器链添加过滤器

    }

}
