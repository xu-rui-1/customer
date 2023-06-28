/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.controller;

import com.alipay.custom.customize.biz.manager.dto.ProductBaseInfoDTO;
import com.alipay.custom.customize.biz.manager.service.ProductService;
import com.alipay.custom.customize.biz.web.accesscontrol.Permission;
import com.alipay.custom.customize.biz.web.accesscontrol.PermissionEnum;
import com.alipay.custom.customize.biz.web.utils.ProductBaseInfoConvertUtil;
import com.alipay.custom.customize.biz.web.vo.ProductBaseInfoVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ruitu.xr
 * @version ProductController.java, v 0.1 2023年06月09日 16:27 ruitu.xr Exp $
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/queryProductInfoByName")
    @ApiOperation("根据名称查询产品")
    @Permission({PermissionEnum.MNG_QUERY})
    public List<ProductBaseInfoVO> queryProductInfoByName(@RequestParam String productName) {
        List<ProductBaseInfoDTO> productBaseInfoDTOS = productService.queryProductInfoByName(productName);
        List<ProductBaseInfoVO> productBaseInfoVOS = new ArrayList<>();
        productBaseInfoDTOS.forEach(s -> {
            ProductBaseInfoVO productBaseInfoVO = ProductBaseInfoConvertUtil.convertProductBaseInfoDTO2VO(s);
            productBaseInfoVOS.add(productBaseInfoVO);
        });

        return productBaseInfoVOS;
    }

    @GetMapping("/queryProductById")
    @ApiOperation("根据id查询产品")
    public ProductBaseInfoVO queryProductById(@RequestParam int id) {
        ProductBaseInfoDTO productBaseInfoDTO = productService.queryProductInfoById(id);
        return ProductBaseInfoConvertUtil.convertProductBaseInfoDTO2VO(productBaseInfoDTO);
    }

    @GetMapping("/queryAllProduct")
    @ApiOperation("分页查询全部产品")
    public List<ProductBaseInfoVO> queryAllProduct(@RequestParam (required = false, defaultValue = "1") int pageIndex,
                                                   @RequestParam (required = false, defaultValue = "10") int pageSize) {
        List<ProductBaseInfoDTO> productBaseInfoDTOS = productService.queryAllProduct(pageIndex, pageSize);
        List<ProductBaseInfoVO> productBaseInfoVOS = new ArrayList<>();
        productBaseInfoDTOS.forEach(s -> {
            ProductBaseInfoVO productBaseInfoVO = ProductBaseInfoConvertUtil.convertProductBaseInfoDTO2VO(s);
            productBaseInfoVOS.add(productBaseInfoVO);
        });

        return productBaseInfoVOS;
    }
}
