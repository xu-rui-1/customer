/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.utils;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author ruitu.xr
 * @version ElasticsearchClient.java, v 0.1 2023年06月27日 15:51 ruitu.xr Exp $
 */
@Component
public class ElasticsearchClient {
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 新增索引(表)*
     * @param entity
     * @param <T>
     * @return
     */
    public <T> boolean put (T entity) {
        IndexOperations indexOps = elasticsearchRestTemplate.indexOps(entity.getClass());
        return indexOps.create();
    }

    /**
     * 判断索引(表)是否存在*
     * @param entity
     * @param <T>
     * @return
     */
    public <T> boolean exists(T entity) {
        IndexOperations indexOps = elasticsearchRestTemplate.indexOps(entity.getClass());
        return indexOps.exists();
    }

    /**
     * 删除索引(表)*
     * @param entity
     * @param <T>
     * @return
     */
    public <T> boolean delete(T entity) {
        IndexOperations indexOps = elasticsearchRestTemplate.indexOps(entity.getClass());
        return indexOps.delete();
    }

    /**
     * 修整索引字段（与实体类保持一致，一般为增加，减少字段未测试）*
     * @param entity
     * @param <T>
     * @return
     */
    public <T> Boolean mapping(T entity) {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(entity.getClass());
        return indexOperations.putMapping();
    }

    /**
     * 插入*
     * @param entity
     * @param <T>
     * @return
     */
    public <T> T save(T entity) {
        return elasticsearchRestTemplate.save(entity);
    }

    /**
     * 批量插入*
     * @param collectors
     * @return
     */
    public Iterable saveBatch(Collection collectors) {
        return elasticsearchRestTemplate.save(collectors);
    }

    /**
     * 根据主键查询*
     * @param id
     * @param entity
     * @param <T>
     * @return
     */
    public <T> Object get(String id, T entity) {
        return elasticsearchRestTemplate.get(id, entity.getClass());
    }

    /**
     * 根据主键删除*
     * @param id
     * @param entity
     * @param <T>
     * @return
     */
    public <T> String delete(String id, T entity) {
        return elasticsearchRestTemplate.delete(id, entity.getClass());
    }

    /**
     * 多条件查询*
     * @param entity
     * @param queryBuilder
     * @param <T>
     * @return
     */
    public <T> SearchHits<?> search(T entity, NativeSearchQueryBuilder queryBuilder) {
        IndexCoordinates indexCoordinatesFor = elasticsearchRestTemplate.getIndexCoordinatesFor(entity.getClass());
        return elasticsearchRestTemplate.search(queryBuilder.build(), entity.getClass(), indexCoordinatesFor);
    }

}
