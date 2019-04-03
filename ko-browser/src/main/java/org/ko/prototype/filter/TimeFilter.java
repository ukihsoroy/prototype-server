package org.ko.prototype.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * servlet API提供的Filter
 * <p>过滤器</p>
 */
public class TimeFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeFilter.class);

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("TimeFilter#init");
    }

    /**
     * 业务逻辑
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("TimeFilter#doFilter start.");
        long startTime = new Date().getTime();
        chain.doFilter(request, response);
        long endTime = new Date().getTime();
        LOGGER.info("TimeFilter use time: {} ms", endTime - startTime);
        LOGGER.info("TimeFilter#doFilter finish.");
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        LOGGER.info("TimeFilter#destroy");
    }
}
