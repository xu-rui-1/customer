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
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        //构建query查询条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        buildBasicQuery(params, boolQuery);
        nativeSearchQueryBuilder.withQuery(boolQuery);

        //算分控制
        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(
                //原始查询，相关性算分的查询
                boolQuery,
                //function score的数组
                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                        //其中的一个function score 元素
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                //过滤条件
                                QueryBuilders.termQuery("isAd", true),
                                //算分函数
                                ScoreFunctionBuilders.weightFactorFunction(10))
                });
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        //数据聚合
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("agg_name").field("brand").size(10);
        nativeSearchQueryBuilder.addAggregation(aggregationBuilder);

        //分页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(params.getPage(), params.getSize()));

        //查询es
        SearchHits<HotelDTO> searchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), HotelDTO.class);

        //获取命中总数
        long totalHits = searchHits.getTotalHits();

        //获取命中的值
        List<HotelDTO> hotelDTOS = new ArrayList<>();
        searchHits.getSearchHits().forEach((hotelDTOSearchHit -> {
            HotelDTO content = hotelDTOSearchHit.getContent();
            hotelDTOS.add(content);
        }));

        //获取聚合结果
        Aggregations aggregations = (Aggregations) searchHits.getAggregations();
        Terms terms = aggregations.get("agg_name");
        for (Terms.Bucket bucket : terms.getBuckets()) {
            String keyAsString = bucket.getKeyAsString(); //聚合字段列的值
            long docCount = bucket.getDocCount(); //聚合字段对应的数量
            System.out.println(keyAsString + " " + docCount);
        }

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
