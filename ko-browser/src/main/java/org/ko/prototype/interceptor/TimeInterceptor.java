package org.ko.prototype.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * spring 拦截器
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("TimeInterceptor#preHandle");
        LOGGER.info("object param bean name: {}", HandlerMethod.class.cast(handler).getBean().getClass().getName());
        LOGGER.info("object param bean name: {}", HandlerMethod.class.cast(handler).getMethod().getName());
        request.setAttribute("startTime", new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        LOGGER.info("TimeInterceptor#postHandle");
        long startTime = (long)request.getAttribute("startTime");
        LOGGER.info("time interceptor use time: {}", new Date().getTime() - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        LOGGER.info("TimeInterceptor#afterCompletion");
        long startTime = (long)request.getAttribute("startTime");
        LOGGER.info("time interceptor use time: {}", new Date().getTime() - startTime);
        LOGGER.info("TimeInterceptor#afterCompletion exception: {}", e);
    }
}
