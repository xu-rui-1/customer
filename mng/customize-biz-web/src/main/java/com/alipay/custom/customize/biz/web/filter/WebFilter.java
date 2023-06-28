/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.filter;

import com.alipay.custom.customize.biz.web.utils.WebHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ruitu.xr
 * @version WebFilter.java, v 0.1 2023年06月15日 16:15 ruitu.xr Exp $
 */
public class WebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("========== 过滤器 ==========");

        //获取请求request
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        WebHelper.setCurrentRequest(request);

        //获取用户名
        String userName = "12345";
        WebHelper.setCurrentLoginUser(userName);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        WebHelper.clearContext();
    }
}
