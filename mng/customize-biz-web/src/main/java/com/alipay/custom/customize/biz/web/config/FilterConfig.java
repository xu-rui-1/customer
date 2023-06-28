/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.config;

import com.alipay.custom.customize.biz.web.filter.WebFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ruitu.xr
 * @version FilterConfig.java, v 0.1 2023年06月15日 16:23 ruitu.xr Exp $
 */
@Configuration
public class FilterConfig {

    @Bean
    public WebFilter webFilter() {
        return new WebFilter();
    }

    @Bean
    public FilterRegistrationBean<WebFilter> filterFilterRegistrationBean() {
        final FilterRegistrationBean<WebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(webFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
