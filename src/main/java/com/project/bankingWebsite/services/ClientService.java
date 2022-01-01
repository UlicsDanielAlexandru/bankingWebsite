package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.model.User;
import com.project.bankingWebsite.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    public void register(Client client)
    {
        clientRepository.save(client);
    }

    public Client loadClient(User user)
    {
        return clientRepository.findByUser(user);
    }
}
