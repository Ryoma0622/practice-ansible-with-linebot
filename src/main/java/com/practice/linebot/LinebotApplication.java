package com.practice.linebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LinebotApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinebotApplication.class, args);
    }

}
