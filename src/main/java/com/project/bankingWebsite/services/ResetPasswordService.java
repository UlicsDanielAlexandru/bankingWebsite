package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResetPasswordService {

    private UserService userService;
    private ClientService clientService;

    public void resetPassword(ResetPasswordRequest resetPasswordRequest) throws IllegalArgumentException
    {
        User specifiedUser;
        try
        {
            specifiedUser = (User) userService.loadUserByUsername(resetPasswordRequest.getUsername());
        }
        catch (UsernameNotFoundException e)
        {
            throw new IllegalArgumentException("Date de identificare incorecte!");
        }
        Client wantedClient = clientService.loadClientByCNP(resetPasswordRequest.getCNP());
        if(wantedClient == null || specifiedUser != wantedClient.getUser())
            throw new IllegalArgumentException("Date de identificare incorecte!");
        else
        {
            specifiedUser.setPassword(resetPasswordRequest.getPassword());
            userService.updateUser(specifiedUser);
        }
    }

}
