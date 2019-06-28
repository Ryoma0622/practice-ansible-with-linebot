package com.practice.linebot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    @GetMapping("/")
    public String index () {
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login () {
        return "login";
    }

    @GetMapping("/login-success")
    public String loginSuccess () {
        return "login-success";
    }

    @GetMapping("/login-failure")
    public String loginFailure () {
        return "login-failure";
    }
}
