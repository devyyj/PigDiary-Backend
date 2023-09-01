package com.devyyj.pigdiary.login;

import com.devyyj.pigdiary.login.service.LoginServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginServiceImpl loginService;
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        loginService.clearCookie(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
