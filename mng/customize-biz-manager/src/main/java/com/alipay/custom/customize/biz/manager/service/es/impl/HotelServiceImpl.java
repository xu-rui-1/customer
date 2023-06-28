/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.service.es.impl;

import cn.hutool.core.util.StrUtil;
import com.alipay.custom.customize.biz.manager.dto.HotelDTO;
import com.alipay.custom.customize.biz.manager.service.es.HotelService;
import com.alipay.custom.customize.common.base.web.PageResult;
import com.alipay.custom.customize.common.base.web.RequestParams;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ruitu.xr
 * @version HotelServiceImpl.java, v 0.1 2023年06月28日 16:11 ruitu.xr Exp $
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public PageResult<List<HotelDTO>> search(RequestParams params) {
        //构建query查询条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        buildBasicQuery(params, boolQuery);

        NativeSearchQuery query = new NativeSearchQuery(boolQuery);

        //分页
        query.setPageable(PageRequest.of(params.getPage(), params.getSize()));

        //查询es
        SearchHits<HotelDTO> searchHits = elasticsearchRestTemplate.search(query, HotelDTO.class);

        //获取命中总数
        long totalHits = searchHits.getTotalHits();

        //获取命中的值
        List<HotelDTO> hotelDTOS = new ArrayList<>();
        searchHits.getSearchHits().forEach((hotelDTOSearchHit -> {
            HotelDTO content = hotelDTOSearchHit.getContent();
            hotelDTOS.add(content);
        }));

        return new PageResult<>(totalHits, hotelDTOS);
    }

    @Override
    public PageResult<List<HotelDTO>> searchLocal() {
        return null;
    }

    private void buildBasicQuery(RequestParams params, BoolQueryBuilder boolQuery) {
        //关键字搜索
        if(StrUtil.isNotBlank(params.getKey())) {
            boolQuery.must(QueryBuilders.matchQuery("all", params.getKey()));
        } else {
            boolQuery.must(QueryBuilders.matchAllQuery());
        }
        //条件过滤
        //城市：精确查询
        if (StrUtil.isNotBlank(params.getCity())) {
            boolQuery.filter(QueryBuilders.termQuery("city", params.getCity()));
        }
        //品牌：精确查询
        if (StrUtil.isNotBlank(params.getBrand())) {
            boolQuery.filter(QueryBuilders.termQuery("brand", params.getBrand()));
        }
        //星级：精确查询
        if (StrUtil.isNotBlank(params.getStarName())) {
            boolQuery.filter(QueryBuilders.termQuery("starName", params.getStarName()));
        }
        //价格：范围查询
        if (params.getMinPrice() != null && params.getMaxPrice() != null) {
            boolQuery.filter(QueryBuilders.rangeQuery("price").gte(params.getMinPrice()).lte(params.getMaxPrice()));
        }
    }
}
