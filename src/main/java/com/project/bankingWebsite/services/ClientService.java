package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.model.User;
import com.project.bankingWebsite.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Client loadClient(String phoneNumber)
    {
        return clientRepository.findByPhoneNumber(phoneNumber);
    }

    public Client loadClientByCNP(String CNP)
    {
        return clientRepository.findByCNP(CNP);
    }

    public List<Client> loadAllClients()
    {
        return clientRepository.findAll();
    }

    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    public void deleteClient(Client client)
    {
        clientRepository.delete(client);
    }

}
