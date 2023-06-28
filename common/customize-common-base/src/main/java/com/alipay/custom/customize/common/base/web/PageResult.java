/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.common.base.web;


/**
 * @author ruitu.xr
 * @version PageResult.java, v 0.1 2023年06月28日 16:05 ruitu.xr Exp $
 */
public class PageResult<T> {

    private long total;

    private T data;

    public PageResult(long total, T data) {
        this.total = total;
        this.data = data;
    }

    public PageResult() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
