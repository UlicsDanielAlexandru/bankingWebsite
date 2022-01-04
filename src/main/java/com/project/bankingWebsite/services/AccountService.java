package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AccountService {

    private AccountRepository accountRepository;
    private ClientService clientService;

    public void register(Account account)
    {
        accountRepository.save(account);
    }

    public Account loadAccount(Client client)
    {
        return accountRepository.findByClient(client);
    }

    public Account loadAccount(String receiverType, String receiver) throws IllegalArgumentException
    {
        Account account;
        if(receiverType.equals("IBAN"))
            account = accountRepository.findByIBAN(receiver);
        else
        {
            Client client = clientService.loadClient(receiver);
            if(client == null)
                throw new IllegalArgumentException("Contul beneficiarului nu a fost identificat!");
            account = accountRepository.findByClient(client);
        }
        if(account == null)
            throw new IllegalArgumentException("Contul beneficiarului nu a fost identificat!");
        else
            return account;
    }

    public void updateAccount(Account account)
    {
        accountRepository.save(account);
    }
}
