package org.ko.prototpye.core.base;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * BaseController
 *
 * @author <A>chent</A>
 *
 */
public class BaseController {

    /**
     * 获得用户真实IP
     *
     * @param request
     *            请求对象
     * @return 真实IP地址
     */
    protected String getIpAddr(HttpServletRequest request) {
        // 多层nginx
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip)) {
            if (ip.contains(",")) {
                String[] forwardeds = ip.split(",");
                if (forwardeds != null && forwardeds.length > 0) {
                    ip = forwardeds[0] == null ? forwardeds[0] : forwardeds[0]
                            .trim();
                }
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            // 单层nginx
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
