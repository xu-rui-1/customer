/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.mng.restful.facade.controller;

import com.alipay.custom.customize.mng.common.facade.service.ProductMngServiceFacade;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ruitu.xr
 * @version ProductMngFacadeController.java, v 0.1 2023年06月09日 18:12 ruitu.xr Exp $
 */
@RestController
@RequestMapping("/product/mng/facade/")
public class ProductMngFacadeController {
    @Resource
    private ProductMngServiceFacade productMngServiceFacade;

}
