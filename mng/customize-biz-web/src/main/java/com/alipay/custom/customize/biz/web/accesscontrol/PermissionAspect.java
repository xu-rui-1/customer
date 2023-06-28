/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.web.accesscontrol;

import com.alipay.custom.customize.biz.web.utils.WebHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ruitu.xr
 * @version PermissionAspect.java, v 0.1 2023年06月15日 16:32 ruitu.xr Exp $
 */
@Aspect
@Component
public class PermissionAspect {

    @Pointcut("@annotation(com.alipay.custom.customize.biz.web.accesscontrol.Permission)")
    public void permissionPointCut(){}

    @Around("permissionPointCut()")
    public Object doAround(ProceedingJoinPoint jointPoint) throws Throwable {
        System.out.println("========= aop 切面 =========");
        //获取权限
        Permission permission = getPermission(jointPoint);

        HttpServletRequest request = WebHelper.getCurrentRequest();
        List<String> permissionList = new ArrayList<>();
        for (PermissionEnum permissionEnum : permission.value()) {
            permissionList.add(permissionEnum.getCode());
        }

        boolean hasPermission = hasPermission(request, permissionList);
        if (!hasPermission) {
            System.out.println("无权限，请先申请权限！");
            return null;
        }

        return jointPoint.proceed();

    }

    //判断当前用户请求是否有权限，这里的判断逻辑需要根据实际业务进行修改
    private boolean hasPermission(HttpServletRequest request, List<String> permissionList) {
        for (String permission : permissionList) {
            if (PermissionEnum.getPermissionList().contains(permission)) {
                return true;
            }
        }
        return false;
    }

    private Permission getPermission(JoinPoint jointPoint) {
        //先从方法中获取，如果方法中没有，就从类上获取
        MethodSignature signature = (MethodSignature) jointPoint.getSignature();
        Permission permission = signature.getMethod().getAnnotation(Permission.class);

        if (permission == null) {
            permission = jointPoint.getTarget().getClass().getAnnotation(Permission.class);
        }

        return permission;
    }
}
