package com.project.bankingWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/resetPassword")
    public String resetPassword() {
        return "resetPassword";
    }

    @GetMapping("/accessDenied")
    public String getAccessDenied() {
        return "accessDenied";
    }

    @GetMapping("/admin")
    public String goAdmin()
    {
        return "admin";
    }
}
