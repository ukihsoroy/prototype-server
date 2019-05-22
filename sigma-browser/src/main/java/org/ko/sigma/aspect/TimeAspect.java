package org.ko.sigma.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 切面
 */
@Aspect
@Component
public class TimeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeAspect.class);

    /**
     * <p>拦截AdminUserController全部方法</p>
     */
    @Around("execution(* org.ko.sigma.rest.user.controller.UserController.*(..))")
    public Object handleControllerMethod (ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("TimeAspect#handleControllerMethod start!");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            LOGGER.info("args: {}", arg);
        }

        long startTime = new Date().getTime();
        Object object = joinPoint.proceed();
        long endTime = new Date().getTime();
        LOGGER.info("TimeAspect#handleControllerMethod use time: {} ms", endTime - startTime);
        LOGGER.info("TimeAspect#handleControllerMethod end!");

        return object;
    }
}
