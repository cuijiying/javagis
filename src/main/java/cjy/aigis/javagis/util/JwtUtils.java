package cjy.aigis.javagis.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private final static String secretString = "aigis-cuijiying-everyday-happy12";
    // 使用256位（32字节）密钥生成适合HS256的密钥
    private final static  SecretKey secretKey = Keys.hmacShaKeyFor(secretString.getBytes());

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, String> claims){
        long expire = 43200000L;
        // 生成JWT token
        return Jwts.builder()
                .claim("sub", "user")  // 设置主题
                .claim("iss", "aigis") // 设置签发者
                .issuedAt(new Date()) // 设置签发时间
                .expiration(new Date(System.currentTimeMillis() + expire)) // 设置过期时间
                .claims(claims)
                .signWith(secretKey) // 使用密钥签名
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        return (Claims) Jwts.parser()
                .verifyWith(secretKey)   // 设置密钥以验证签名
                .build()
                .parse(jwt)  // 解析JWT并验证签名
                .getPayload();

    }
}
