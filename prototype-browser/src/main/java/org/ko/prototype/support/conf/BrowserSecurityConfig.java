package org.ko.prototype.support.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .httpBasic() //默认登陆
                .formLogin() //表单登陆
                .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
    }
}
