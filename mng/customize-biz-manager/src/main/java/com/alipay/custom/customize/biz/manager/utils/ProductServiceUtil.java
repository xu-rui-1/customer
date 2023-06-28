/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.utils;

import com.alipay.custom.customize.biz.manager.dto.ProductBaseInfoDTO;
import com.alipay.custom.customize.common.dal.domain.ProductBaseInfo;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author ruitu.xr
 * @version ProductServiceUtil.java, v 0.1 2023年05月19日 12:04 ruitu.xr Exp $
 */
public class ProductServiceUtil {

    /**
     * DTO转DO
     * @param productBaseInfoDTO
     * @return
     */
    public static ProductBaseInfo convertProductBaseInfoDTO2DO(ProductBaseInfoDTO productBaseInfoDTO) {
        ProductBaseInfo productBaseInfo = new ProductBaseInfo();
        BeanUtils.copyProperties(productBaseInfoDTO, productBaseInfo);
        productBaseInfo.setGmtModified(new Date());
        return productBaseInfo;
    }

    /**
     * DO转DTO
     * @param productBaseInfo
     * @return
     */
    public static ProductBaseInfoDTO convertProductBaseInfoDO2DTO(ProductBaseInfo productBaseInfo) {
        ProductBaseInfoDTO productBaseInfoDTO = new ProductBaseInfoDTO();
        BeanUtils.copyProperties(productBaseInfo, productBaseInfoDTO);
        return productBaseInfoDTO;
    }
}
