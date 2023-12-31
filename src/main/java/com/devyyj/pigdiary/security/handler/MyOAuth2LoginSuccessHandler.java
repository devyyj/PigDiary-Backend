package com.devyyj.pigdiary.security.handler;

import com.devyyj.pigdiary.user.entity.MyUser;
import com.devyyj.pigdiary.user.repository.MyUserRepository;
import com.devyyj.pigdiary.security.util.CookieUtil;
import com.devyyj.pigdiary.security.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class MyOAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${front.url}")
    private String frontUrl;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final MyUserRepository userRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        Optional<MyUser> user = userRepository.findBySocialIdAndDeleted(oauth2User.getName(), false);
        // JWT 토큰 생성
        String jwt = jwtUtil.generateToken(user.orElseThrow().getId().toString());

        // JWT 토큰을 클라이언트에게 반환 (예: 응답 헤더에 추가)
        response.addHeader("Authorization", "Bearer " + jwt);

        // todo 프론트 개발 되면 해당 코드 제거, JWT는 헤더로 전송함
        Cookie jwtCookie = cookieUtil.createCookie("jwt", jwt, true);
        Cookie isLogged = cookieUtil.createCookie("isLogged", "true", false);
        response.addCookie(jwtCookie);
        response.addCookie(isLogged);

        // 로그인 성공 후 리다이렉트 또는 추가 작업을 수행할 수 있습니다.
        clearAuthenticationAttributes(request);
        String prevUrl = cookieUtil.getCookie(request.getCookies(), "prevUrl");
        // todo 프론트엔드 주소 환경변수로 빼기
        super.setDefaultTargetUrl(frontUrl + (prevUrl == null ? "" : prevUrl));
        super.onAuthenticationSuccess(request, response, authentication);
    }
}