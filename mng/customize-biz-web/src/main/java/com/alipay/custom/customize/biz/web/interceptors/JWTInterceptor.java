/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.interceptors;

import com.alipay.custom.customize.biz.manager.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT登录拦截器
 *
 * @author ruitu.xr
 * @version JWTInterceptor.java, v 0.1 2023年06月12日 11:51 ruitu.xr Exp $
 */
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("========= 拦截器 ===========");

        Map<String, Object> map = new HashMap<>();
        //获取请求头中的令牌
        String token = request.getHeader("token");

        //验证令牌
        try {
            JwtUtils.verifyToken(token);
            return true; //放行请求
        } catch (Exception e) {
            map.put("msg", "token认证失败");
        }

        map.put("state", false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
