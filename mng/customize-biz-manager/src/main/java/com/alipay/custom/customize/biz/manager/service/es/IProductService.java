package com.alipay.custom.customize.biz.manager.service.es;

import com.alipay.custom.customize.common.dal.domain.ProductBaseInfo;

import java.util.List;

/**
 * @author ruitu.xr
 * @version IProductService.java, v 0.1 2023年06月26日 10:05 ruitu.xr Exp $
 */
public interface IProductService {

    /**
     * 根据产品id查询产品信息*
     * @param id 产品id
     * @return
     */
    ProductBaseInfo queryProductById(Integer id);

    /**
     * 根据产品名称查询产品信息*
     * @param name 产品名称
     * @return
     */
    List<ProductBaseInfo> queryProductByName(String name);

}
