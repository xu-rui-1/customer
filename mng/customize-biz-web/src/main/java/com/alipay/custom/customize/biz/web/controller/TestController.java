/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.controller;

import com.alipay.custom.customize.biz.manager.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ruitu.xr
 * @version TestController.java, v 0.1 2023年06月09日 14:04 ruitu.xr Exp $
 */
@RestController
@RequestMapping("/test/")
@Api(tags = "TestController")
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("queryTest.json")
    @ApiOperation(value = "queryTest")
    public String queryTest() {
        return testService.queryTest();
    }
}
