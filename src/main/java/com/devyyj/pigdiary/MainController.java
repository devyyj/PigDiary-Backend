package com.devyyj.pigdiary;

import com.devyyj.pigdiary.security.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@RequiredArgsConstructor
@RestController
public class MainController {

    private final CookieUtil cookieUtil;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        log.info(request.getCookies());
        return "login!";
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie isLogged = cookieUtil.createCookie("isLogged", null, false);
        Cookie jwt = cookieUtil.createCookie("jwt", null, false);

        isLogged.setMaxAge(0);
        jwt.setMaxAge(0);

        response.addCookie(isLogged);
        response.addCookie(jwt);

        return new RedirectView("/", true);
    }

    @DeleteMapping("/logout")
    public String delout(){
        return "delout";
    }
}
