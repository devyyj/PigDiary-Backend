package com.devyyj.pigdiary.security.util;

import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
    @Value("${front.domain}")
    private String frontDomain;

    public Cookie createCookie(String name, String value, Boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(frontDomain);
        cookie.setPath("/");
        cookie.setHttpOnly(isHttpOnly);
        cookie.setMaxAge(3600); // 토큰 만료 시간 (초)
        return cookie;
    }

    public String getCookie(Cookie[] cookies, String name) {
        // 쿠키 배열 가져오기
        if (cookies != null) {
            // 쿠키에서 JWT 토큰 찾기
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null; // JWT 토큰이 쿠키에 없을 경우 null 반환
    }
}
