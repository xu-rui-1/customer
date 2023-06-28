/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.common.dal.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ruitu.xr
 * @version CustomDalConfiguration.java, v 0.1 2023年06月09日 15:51 ruitu.xr Exp $
 */
@Configuration
@ComponentScan(value = "com.alipay.custom.customize.common.dal")
@MapperScan("com.alipay.custom.customize.common.dal.dao")
public class CustomDalConfiguration {
}
