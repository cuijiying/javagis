package cjy.aigis.javagis;

import cjy.aigis.javagis.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class JJWTTest {

    @Test
    public void testGenerateJwt() throws Exception{
        // 生成用于签名的密钥
        Map<String, String> claimsMap = new HashMap<>();
        claimsMap.put("id","1");
        claimsMap.put("name","tom");
        // 生成JWT token
        String jws = JwtUtils.generateJwt(claimsMap);
        System.out.println("JWT Token: " + jws);

        // 解析 JWT Token
        Claims claims = JwtUtils.parseJWT(jws);
        // 输出claims中的数据
        System.out.println("sub: " + claims.getSubject());
        System.out.println("iss: " + claims.getIssuer());
        System.out.println("exp: " + claims.getExpiration());
        System.out.println("id: " + claims.get("id"));
    }


}
