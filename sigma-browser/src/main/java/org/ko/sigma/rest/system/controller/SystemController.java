package org.ko.sigma.rest.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.ko.sigma.core.support.Response;
import org.ko.sigma.core.type.SystemCode;
import org.ko.sigma.rest.system.service.SystemService;
import org.ko.sigma.rest.user.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RestController
@Api(tags = "系统操作相关")
@Validated
public class SystemController {

    private static final Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired private SystemService systemService;

    /**
     * 请求的缓存
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     *
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @PostMapping("login")
    @ApiOperation("账号密码登录")
    public Response login (
            @ApiParam("用户名") @RequestParam String username,
            @ApiParam("用户口令") @RequestParam String password) {
        return Response.of(true);
    }


    @GetMapping("logout")
    @ApiOperation("登出系统")
    public Response<Long> logout () {
        return new Response<>(1L);
    }

    @PostMapping("signup")
    @ApiOperation("注册新用户")
    public Response<UserDTO> signup (@ApiParam("用户信息对象") @RequestBody UserDTO userDTO) {
        return new Response<>(userDTO);
    }

    @GetMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ApiOperation("需要权限验证的引导接口")
    public Response<String> requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 拿到引发跳转的请求
         */
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (Objects.nonNull(savedRequest)) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的URL是: {}", targetUrl);
        }
        return Response.of(SystemCode.REQUIRE_AUTHENTICATION);
    }

    @GetMapping("session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Response sessionInvalid () {
        return Response.of("session失效");
    }

}
