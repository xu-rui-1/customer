/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package com.alipay.custom.customize.biz.manager.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author ruitu.xr
 * @version JwtUtils.java, v 0.1 2023年06月12日 11:27 ruitu.xr Exp $
 */
public class JwtUtils {

    public static final String SIGNATURE = "!HKHKJ@J$KJK";

    /**
     * 生成token header.payload.sign
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); //默认7天过期

        //创建jwt的builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach(builder::withClaim);

        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGNATURE));
    }

    /**
     * 验证token ，如果不合法，会抛出异常,否则返回DecodedJWT结果信息
     * @param token
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }
}
