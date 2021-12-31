package com.project.bankingWebsite.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String login(Model model)
    {
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
    public String goAdmin(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        model.addAttribute("username", userDetails.getUsername());
        return "admin";
    }
}
