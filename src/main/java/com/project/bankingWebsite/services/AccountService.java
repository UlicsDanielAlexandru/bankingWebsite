package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class AccountService {

    private AccountRepository accountRepository;

    public void register(Account account)
    {
        accountRepository.save(account);
    }
}
