package com.alipay.custom.customize.biz.manager.service;

import com.alipay.custom.customize.biz.manager.dto.ProductBaseInfoDTO;

import java.util.List;

/**
 * @author ruitu.xr
 * @version ProductService.java, v 0.1 2023年06月09日 16:03 ruitu.xr Exp $
 */
public interface ProductService {
    /**
     * 根据名称查询产品
     * @param productName
     * @return
     */
    List<ProductBaseInfoDTO> queryProductInfoByName(String productName);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    ProductBaseInfoDTO queryProductInfoById(Integer id);

    /**
     * 查询所有产品
     * @return
     */
    List<ProductBaseInfoDTO> queryAllProduct(int pageIndex, int pageSize);
}
