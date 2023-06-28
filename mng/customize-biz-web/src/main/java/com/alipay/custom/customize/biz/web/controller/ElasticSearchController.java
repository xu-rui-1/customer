/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.controller;

import com.alipay.custom.customize.biz.manager.dto.HotelDTO;
import com.alipay.custom.customize.biz.manager.service.es.HotelService;
import com.alipay.custom.customize.common.base.web.RequestParams;
import com.alipay.custom.customize.common.base.web.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ruitu.xr
 * @version ElasticSearchController.java, v 0.1 2023年06月28日 16:03 ruitu.xr Exp $
 */
@RestController
@Api(tags = "ES搜索controller")
@RequestMapping("/hotel")
public class ElasticSearchController {
    @Resource
    private HotelService hotelService;

    @PostMapping("/search")
    @ApiOperation("条件查询")
    public PageResult<List<HotelDTO>> search(@RequestBody RequestParams params) {

        return hotelService.search(params);
    }

    public PageResult<List<HotelDTO>> searchLocal() {
        return hotelService.searchLocal();

    }
}
