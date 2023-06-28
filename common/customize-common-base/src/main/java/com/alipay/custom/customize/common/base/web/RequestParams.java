/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.common.base.web;

import java.io.Serializable;

/**
 * @author ruitu.xr
 * @version RequestParams.java, v 0.1 2023年06月28日 15:54 ruitu.xr Exp $
 */
public class RequestParams implements Serializable {
    private static final long serialVersionUID = 8775491532770451916L;
    /**
     * 关键词*
     */
    private String key;

    /**
     * 页码*
     */
    private Integer page;

    /**
     * 页数*
     */
    private Integer size;

    /**
     * 排序条件*
     */
    private String sortBy;

    /**
     * 品牌*
     */
    private String brand;

    /**
     * 星级*
     */
    private String starName;

    /**
     * 城市*
     */
    private String city;

    /**
     * 最小价格*
     */
    private Integer minPrice;

    /**
     * 最大价格*
     */
    private Integer maxPrice;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "RequestParams{" +
                "key='" + key + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", sortBy='" + sortBy + '\'' +
                ", brand='" + brand + '\'' +
                ", starName='" + starName + '\'' +
                ", city='" + city + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
