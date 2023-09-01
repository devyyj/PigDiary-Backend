package com.devyyj.pigdiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PigDiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PigDiaryApplication.class, args);
    }

}
