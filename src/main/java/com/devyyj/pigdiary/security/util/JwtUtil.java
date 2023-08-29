package com.devyyj.pigdiary.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.devyyj.pigdiary.User.entity.MyUser;
import com.devyyj.pigdiary.security.dto.MyOauth2UserDto;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "your-secret-key"; // 실제로는 보안에 강한 랜덤한 키를 사용해야 합니다.

    public String generateToken(MyUser user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // 1시간 유효한 토큰

        return JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withClaim("socialId", user.getSocialId())
                .withClaim("nickName", user.getNickName())
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public DecodedJWT verifyToken(String token) {
        try {
            // 토큰이 유효한 경우, 주체(subject)를 반환합니다.
            return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);
        } catch (Exception e) {
            // 토큰이 유효하지 않은 경우, 예외 처리를 수행합니다.
            // 유효하지 않은 토큰 또는 만료된 토큰일 수 있습니다.
            return null;
        }
    }
}
