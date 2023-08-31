package com.devyyj.pigdiary.login.service;

import com.devyyj.pigdiary.security.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final CookieUtil cookieUtil;

    @Override
    public void clearCookie(HttpServletResponse response) {
        Cookie isLogged = cookieUtil.createCookie("isLogged", null, false);
        Cookie jwt = cookieUtil.createCookie("jwt", null, false);

        isLogged.setMaxAge(0);
        jwt.setMaxAge(0);

        response.addCookie(isLogged);
        response.addCookie(jwt);
    }
}
