package com.devyyj.pigdiary;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class MainController {
    @GetMapping("/")
    public String root(HttpServletRequest request) {
        log.info(request.getCookies());
        return "main!";
    }
}
