package com.project.bankingWebsite.controller;

import com.project.bankingWebsite.model.User;
import com.project.bankingWebsite.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class AuthenticationController {

    private ClientService clientService;
    private UserService userService;
    private ResetPasswordService resetPasswordService;

    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(Model model) {
        model.addAttribute("resetPasswordRequest", new ResetPasswordRequest());
        return "resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPasswordAndValidation(@ModelAttribute("resetPasswordRequest") ResetPasswordRequest
            resetPasswordRequest, Model model)
    {
        try
        {
            resetPasswordService.resetPassword(resetPasswordRequest);
        }
        catch (IllegalArgumentException e)
        {
            model.addAttribute("error", e.getMessage());
        }
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

    @GetMapping("/client")
    public String goClient(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        model.addAttribute("client", clientService.loadClient(user));
        return "client";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
