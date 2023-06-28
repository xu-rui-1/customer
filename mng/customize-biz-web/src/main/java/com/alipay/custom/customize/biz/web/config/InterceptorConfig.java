/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.config;

import com.alipay.custom.customize.biz.web.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ruitu.xr
 * @version InterceptorConfig.java, v 0.1 2023年06月12日 11:58 ruitu.xr Exp $
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 在这里将其注册成bean,这样当在拦截器进行引用其他服务时就不会报空指针异常了
     * @return
     */
    @Bean
    public JWTInterceptor jWTInterceptor() {
        return new JWTInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/user/**");
    }
}
