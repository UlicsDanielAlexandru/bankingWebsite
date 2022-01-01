package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.model.Role;
import com.project.bankingWebsite.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserService userService;
    private ClientService clientService;
    private AccountService accountService;

    public void register(RegistrationRequest request) {
        User user = new User(request.getUsername(), request.getPassword(), Role.CLIENT);
        userService.register(user);
        Client client = new Client(request.getCNP(), request.getFirstName(), request.getLastName(),
                request.getAddress(),request.getEmail(),request.getPhoneNumber(),user);
        clientService.register(client);
        Account account = new Account(IBANGenerator.generateIBAN(), 0, client);
        accountService.register(account);
    }
}
