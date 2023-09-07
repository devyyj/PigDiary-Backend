package com.devyyj.pigdiary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/check")
    public String main(){
        return "I'm alive!";
    }
}
