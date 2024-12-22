package com.group.jwt;


import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.print.DocFlavor;
import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

/**
 * jwt 클레임 정의
 */
@Component
public class JwtUtil {

    private SecretKey secretKey;

    // jwt key 암호화를 진행하는 부분
    public JwtUtil(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getEmpUUID(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload().get("empUUID", String.class);
    }

    public String getUsername(String token) {
        // verifyWith : 토큰을 검증
        // parseSignedClaims : 클레임 확인
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload().get("empEmail", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload().get("roleType", String.class);
    }

    public String getTokenType(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build().parseSignedClaims(token)
                .getPayload().get("tokenType", String.class);
    }

    // 토큰 만료 확인을 위해 메서드 파라미터로 현재 받은 token 을 인자로 받는다
    // 토큰 만료 여부 확인 (만료 : true, 유지 : false)
    public Boolean isExpired(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build().parseSignedClaims(token)
                .getPayload().getExpiration().before(new Date());
    }

    // 요약 : 토큰 발행 시간 + 토큰 소멸 시간
    public String createJwt(String tokenType, String empEmail, String empUUID,  String roleType, Long expired) {
        return Jwts.builder()
                .claim("tokenType", tokenType)
                .claim("empEmail", empEmail)
                .claim("empUUID", empUUID)
                .claim("roleType", roleType)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expired))
                .signWith(secretKey)
                .compact();
    }

}
