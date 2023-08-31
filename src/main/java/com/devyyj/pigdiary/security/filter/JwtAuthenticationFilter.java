package com.devyyj.pigdiary.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.devyyj.pigdiary.User.dto.UserRequestDto;
import com.devyyj.pigdiary.security.util.CookieUtil;
import com.devyyj.pigdiary.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = cookieUtil.getCookie(request.getCookies(), "jwt");
        if (jwt != null) {
            DecodedJWT decodedJWT = jwtUtil.verifyToken(jwt);
            if (decodedJWT != null) {
                // 권한 설정 꼭 필요! 하지 않으면 무한 인증 요청
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(
                        decodedJWT.getSubject()
                        , ""
                        , List.of(new SimpleGrantedAuthority("USER"))
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
