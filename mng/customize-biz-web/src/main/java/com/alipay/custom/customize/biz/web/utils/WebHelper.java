/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ruitu.xr
 * @version WebHelper.java, v 0.1 2023年06月15日 16:17 ruitu.xr Exp $
 */
public class WebHelper {

    public static final String LOGIN_USER = "_CurrentLoginUser_";

    public static final String DEFAULT_USER = "_system_";

    private static final String CURRENT_REQUEST = "_CurrentRequest_";

    private static final ThreadLocal<Map<String, Object>> context = ThreadLocal.withInitial(() -> {
        return new HashMap<>();
    });

    public static void clearContext() {
        context.remove();
    }

    /**
     * 获取当前请求
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        return (HttpServletRequest) context.get().get(CURRENT_REQUEST);
    }

    /**
     * 设置当前请求，在Filter时使用
     * @param request
     */
    public static void setCurrentRequest(HttpServletRequest request) {
        context.get().put(CURRENT_REQUEST, request);
    }

    /**
     * 获取当前用户名称
     * @return
     */
    public static String getOperatorName() {
        return (String) (context.get().get(LOGIN_USER) == null ? DEFAULT_USER : context.get().get(LOGIN_USER));
    }

    /**
     * 设置当前用户名称
     * @param userName
     */
    public static void setCurrentLoginUser(String userName) {
        if (StringUtils.isEmpty(userName)) {
            context.get().put(LOGIN_USER, DEFAULT_USER);
            return;
        }
        context.get().put(LOGIN_USER, userName);
    }
}
