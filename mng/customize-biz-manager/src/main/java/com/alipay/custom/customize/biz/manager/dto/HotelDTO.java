/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.dto;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author ruitu.xr
 * @version HotelDTO.java, v 0.1 2023年06月28日 16:08 ruitu.xr Exp $
 */
@Document(indexName = "hotel")
public class HotelDTO {

    private Long id;

    /**
     * 名称*
     */
    private String name;

    /**
     * 地址*
     */
    private String address;

    /**
     * 价格*
     */
    private Integer price;

    /**
     * 评分*
     */
    private Integer score;

    /**
     * 品牌*
     */
    private String brand;

    /**
     * 城市*
     */
    private String city;

    /**
     * 星级*
     */
    private String starName;

    /**
     * 商圈*
     */
    private String business;

    /**
     * 坐标，经纬度*
     */
    private String location;

    /**
     * 照片描述*
     */
    private String pic;

    /**
     * 是否是广告*
     */
    private boolean isAd;

    public HotelDTO() {
    }

    public HotelDTO(Long id, String name, String address, Integer price, Integer score, String brand, String city, String starName,
                    String business, String location, String pic, boolean isAd) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.score = score;
        this.brand = brand;
        this.city = city;
        this.starName = starName;
        this.business = business;
        this.location = location;
        this.pic = pic;
        this.isAd = isAd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isAd() {
        return isAd;
    }

    public void setAd(boolean ad) {
        isAd = ad;
    }
}
