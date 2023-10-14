package com.aurora.utils;

import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    //生成token
    public static String createJWT(String signKey, long time, Map<String, Object> claims) {
        //创建一个JwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder();
        //创建jwt token: xxxx.yyyy.zzzz

        return jwtBuilder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256") //Header: 头部
                //Payload:载荷
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + time)) //过期时间
                //sign:签名
                .signWith(SignatureAlgorithm.HS256, signKey.getBytes(StandardCharsets.UTF_8)) //加密算法和签名的密钥
                .compact(); //token
    }

    /**
     * Token解密
     */
    public static Claims parseJWT(String signKey, String token) {
        //获取jwt的解析对象
        JwtParser jwtParser = Jwts.parser();
        return jwtParser.setSigningKey(signKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody(); //数据
    }

}