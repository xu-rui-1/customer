package com.alipay.custom.customize.biz.web.accesscontrol;

import java.lang.annotation.*;

/**
 * 权限注解
 *
 * @author ruitu.xr
 * @version Permission.java, v 0.1 2023年06月15日 16:30 ruitu.xr Exp $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Permission {
    PermissionEnum[] value();
}
