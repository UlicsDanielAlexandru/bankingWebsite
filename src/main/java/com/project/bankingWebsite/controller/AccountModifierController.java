package com.project.bankingWebsite.controller;

import com.project.bankingWebsite.services.RegistrationRequest;
import com.project.bankingWebsite.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class AccountModifierController {

    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String register(Model model)
    {
        RegistrationRequest registrationRequest = new RegistrationRequest();
        model.addAttribute("registrationRequest", registrationRequest);
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute("registrationRequest") RegistrationRequest registrationRequest, Model model)
    {
        try
        {
            registrationService.register(registrationRequest);
            return "registration";
        }
        catch (IllegalStateException e)
        {
            model.addAttribute("error",e.getMessage());
            return "registration";
        }
    }

}
