/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.service.impl;

import com.alipay.custom.customize.biz.manager.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author ruitu.xr
 * @version TestServiceImpl.java, v 0.1 2023年06月09日 14:09 ruitu.xr Exp $
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String queryTest() {
        return "this is a test";
    }
}
