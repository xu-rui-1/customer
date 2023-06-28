/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.service.impl;

import com.alipay.custom.customize.biz.manager.dto.ProductBaseInfoDTO;
import com.alipay.custom.customize.biz.manager.service.ProductService;
import com.alipay.custom.customize.biz.manager.utils.ProductServiceUtil;
import com.alipay.custom.customize.biz.manager.utils.RedisCacheUtil;
import com.alipay.custom.customize.common.base.constant.RedisConstant;
import com.alipay.custom.customize.common.base.enums.ResultCodeEnum;
import com.alipay.custom.customize.common.base.exception.ProductBizException;
import com.alipay.custom.customize.common.dal.dao.ProductBaseInfoMapper;
import com.alipay.custom.customize.common.dal.domain.ProductBaseInfo;
import com.alipay.custom.customize.common.dal.domain.ProductBaseInfoExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ruitu.xr
 * @version ProductServiceImpl.java, v 0.1 2023年06月09日 16:03 ruitu.xr Exp $
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductBaseInfoMapper productBaseInfoMapper;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Override
    public List<ProductBaseInfoDTO> queryProductInfoByName(String productName) {
        ProductBaseInfoExample example = new ProductBaseInfoExample();
        example.createCriteria().andProductNameEqualTo(productName);
        List<ProductBaseInfo> productBaseInfos = productBaseInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(productBaseInfos)) {
            String msg = String.format("不存在该名称的产品, productName=%s", productName);
            //LOGGER.error(msg);
            //throw new ProductBizException(ResultCodeEnum.QUERY_ERROR, msg);
            return null;
        }

        List<ProductBaseInfoDTO> productBaseInfoDTOS = new ArrayList<>();
        productBaseInfos.forEach(s -> {
            ProductBaseInfoDTO productBaseInfoDTO = ProductServiceUtil.convertProductBaseInfoDO2DTO(s);
            productBaseInfoDTOS.add(productBaseInfoDTO);

        });
        return productBaseInfoDTOS;
    }

    @Override
    public ProductBaseInfoDTO queryProductInfoById(Integer id) {
        //查询缓存
        String key = RedisConstant.PRODUCT_KEY_PREFIX + id;
        ProductBaseInfo productBaseInfo = redisCacheUtil.getObject(key, ProductBaseInfo.class);
        //双检加锁策略
        if (productBaseInfo == null) {
            synchronized (ProductServiceImpl.class) {
                productBaseInfo = redisCacheUtil.getObject(key, ProductBaseInfo.class);
                if (productBaseInfo == null) {
                    //缓存没有就查询数据库
                    productBaseInfo = productBaseInfoMapper.selectByPrimaryKey(id);
                    if (productBaseInfo == null) {
                        //数据库没有的话，就抛一个异常出去
                        throw new ProductBizException(ResultCodeEnum.QUERY_ERROR, "数据库中不存在此id的产品，请确认输入的产品id是否正确，如有问题请联系技术同学");
                    } else {
                        //数据库存在数据，则将这条数据存入redis缓存中
                        redisCacheUtil.add(key, productBaseInfo);
                    }
                }
            }
        }

        return ProductServiceUtil.convertProductBaseInfoDO2DTO(productBaseInfo);
    }

    @Override
    public List<ProductBaseInfoDTO> queryAllProduct(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        ProductBaseInfoExample example = new ProductBaseInfoExample();
        example.setOrderByClause("id desc");
        List<ProductBaseInfo> productBaseInfoDOS = productBaseInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(productBaseInfoDOS)) {
            String msg = "当前不存在商品";
            //LOGGER.error(msg);
            //throw new ProductBizException(ResultCodeEnum.QUERY_ERROR, msg);
            return null;
        }

        List<ProductBaseInfoDTO> productBaseInfoDTOS = new ArrayList<>();
        productBaseInfoDOS.forEach(s -> {
            ProductBaseInfoDTO productBaseInfoDTO = ProductServiceUtil.convertProductBaseInfoDO2DTO(s);
            productBaseInfoDTOS.add(productBaseInfoDTO);

        });
        return productBaseInfoDTOS;
    }
}
