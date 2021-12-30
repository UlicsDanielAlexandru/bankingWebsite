package com.project.bankingWebsite.controller;

import com.project.bankingWebsite.services.RegistrationRequest;
import com.project.bankingWebsite.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public void register(@RequestBody RegistrationRequest request)
    {
        registrationService.register(request);
    }
}
