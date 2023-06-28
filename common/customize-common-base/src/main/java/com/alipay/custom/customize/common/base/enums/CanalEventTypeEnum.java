package com.alipay.custom.customize.common.base.enums;

/**
 * @author ruitu.xr
 * @version CanalEventTypeEnum.java, v 0.1 2023年06月20日 15:00 ruitu.xr Exp $
 */
public enum CanalEventTypeEnum {
    INSERT("INSERT", "插入"),

    UPDATE("UPDATE", "更新"),

    DELETE("DELETE", "删除"),
    ;

    private String code;

    private String desc;

    CanalEventTypeEnum(String code, String desc) {
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

    public static CanalEventTypeEnum getEnumByCode(String code) {
        for (CanalEventTypeEnum eventType : CanalEventTypeEnum.values()) {
            if (eventType.getCode().equalsIgnoreCase(code)) {
                return eventType;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "CanalEventTypeEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
