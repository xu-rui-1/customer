package com.alipay.custom.customize.biz.web.accesscontrol;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限枚举类型
 *
 * @author ruitu.xr
 * @version PermissionEnum.java, v 0.1 2023年06月15日 16:29 ruitu.xr Exp $
 */
public enum PermissionEnum {
    MNG_QUERY("MNG_QUERY", "查询权限"),
    MNG_EDIT("MNG_EDIT", "编辑权限"),
    MNG_AUDIT("MNG_AUDIT", "审核权限"),
    ;

    private String code;

    private String desc;

    PermissionEnum(String code, String desc) {
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

    public static List<String> getPermissionList() {
        List<String> allPermissions = new ArrayList<>();
        for (PermissionEnum permissionEnum : PermissionEnum.values()) {
            allPermissions.add(permissionEnum.getCode());
        }
        return allPermissions;
    }

    @Override
    public String toString() {
        return "PermissionEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
