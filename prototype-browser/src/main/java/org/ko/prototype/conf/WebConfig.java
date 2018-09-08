package org.ko.prototype.conf;

import org.ko.prototype.filter.TimeFilter;
import org.ko.prototype.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

/**
 * <p>配置</p>
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter () {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(timeInterceptor);
    }

    /**
     * <p>异步请求一些配置</p>
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
//        configurer.registerDeferredResultInterceptors()
//        configurer.registerCallableInterceptors()
        //异步调用超时时间
        configurer.setDefaultTimeout(1000);
        //配置线程池
//        configurer.setTaskExecutor(new SimpleAsyncTaskExecutor());
    }
}
