/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.service.es.impl;

import com.alipay.custom.customize.biz.manager.service.es.IProductService;
import com.alipay.custom.customize.common.dal.dao.ProductBaseInfoMapper;
import com.alipay.custom.customize.common.dal.domain.ProductBaseInfo;
import com.alipay.custom.customize.common.dal.domain.ProductBaseInfoExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ruitu.xr
 * @version IProductServiceImpl.java, v 0.1 2023年06月26日 10:06 ruitu.xr Exp $
 */
@Service
public class IProductServiceImpl implements IProductService {
    @Resource
    private ProductBaseInfoMapper productBaseInfoMapper;

    @Override
    public ProductBaseInfo queryProductById(Integer id) {
        return productBaseInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductBaseInfo> queryProductByName(String name) {
        ProductBaseInfoExample example = new ProductBaseInfoExample();
        example.createCriteria().andProductNameEqualTo(name);
        return productBaseInfoMapper.selectByExample(example);
    }
}
