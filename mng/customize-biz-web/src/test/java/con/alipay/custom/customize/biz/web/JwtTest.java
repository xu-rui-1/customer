/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package con.alipay.custom.customize.biz.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

/**
 * @author ruitu.xr
 * @version JwtTest.java, v 0.1 2023年06月12日 10:56 ruitu.xr Exp $
 */
public class JwtTest {
    public static final String SIGNATURE = "!HKHKJ@J$KJK";

    @Test
    public void testJwt() {

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 2000);

        String token = JWT.create()
                .withClaim("userId", 21) //payload
                .withClaim("userName", "xxx")
                .withExpiresAt(instance.getTime()) //指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGNATURE)); //签名

        //String token = "dadada";

        System.out.println(token);
    }

    @Test
    public void verifyTokenTest() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGNATURE)).build();
        try {
            DecodedJWT verify = jwtVerifier.verify(
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6Inh4eCIsImV4cCI6MTY4NjU0MDI5MywidXNlcklkIjoyMX0.Yz3X08PDyg67PDxVhv67YBv7pJC7rb0c9YtGOC9cBMQ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
