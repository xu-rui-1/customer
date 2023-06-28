/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.common.base.exception;

import com.alipay.custom.customize.common.base.enums.ResultCodeEnum;

/**
 * @author ruitu.xr
 * @version ProductBizException.java, v 0.1 2023年06月09日 17:20 ruitu.xr Exp $
 */
public class ProductBizException extends RuntimeException {
    private String code;

    private String msg;

    public ProductBizException(ResultCodeEnum resultCodeEnum, String errMsg) {
        super(errMsg);
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getDesc();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
