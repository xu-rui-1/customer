package com.alipay.custom.customize.common.base.enums;

import com.alipay.custom.customize.common.base.exception.ProductBizException;

/**
 * @author ruitu.xr
 * @version ResultCodeEnum.java, v 0.1 2023年06月09日 17:20 ruitu.xr Exp $
 */
public enum ResultCodeEnum {
    SUCCESS("SUCCESS", "成功"),

    INVALID_PARAMETER("INVALID_PARAMETER", "无效参数"),
    ILLEGAL_PARAMETER("ILLEGAL_PARAMETER", "非法参数"),
    QUERY_ERROR("MODEL_QUERY_ERROR","查询失败"),
    INSERT_ERROR("MODEL_INSERT_ERROR", "插入失败"),
    UPDATE_ERROR("MODEL_UPDATE_ERROR", "更新失败"),
    DELETE_ERROR("MODEL_DELETE_ERROR", "删除失败"),
    ENUM_NOT_MATCH("ENUM_NOT_MATCH", "枚举类匹配失败"),
    REGISTER_ERROR("REGISTER_ERROR", "注册失败"),
    PERMISSION_DENIED("PERMISSION_DENIED", "无权限"),

    UNKNOWN_ERROR("UNKNOWN_ERROR", "未知异常"),
    ;
    private String code;

    private String desc;

    ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ResultCodeEnum getEnumByCode(String code) {
        for(ResultCodeEnum resultCodeEnum : values()) {
            if(resultCodeEnum.getCode().equals(code)) {
                return resultCodeEnum;
            }
        }
        throw new ProductBizException(ResultCodeEnum.ENUM_NOT_MATCH, "没有匹配成功的枚举类码");
    }
}
