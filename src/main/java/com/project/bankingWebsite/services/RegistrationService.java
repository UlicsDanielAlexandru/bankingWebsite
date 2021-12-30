package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.Role;
import com.project.bankingWebsite.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserService userService;

    public void register(RegistrationRequest request) {
        userService.register(new User(request.getUsername(), request.getPassword(), Role.ADMIN));
    }
}
