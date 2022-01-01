package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Transaction;
import com.project.bankingWebsite.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TransactionService {

    private TransactionRepository transactionRepository;

    public void makeTransaction(Account accountFrom, Account accountTo, Double amount)
    {
        Transaction transaction = new Transaction(accountFrom, accountTo, amount, LocalDateTime.now());
        transactionRepository.save(transaction);
    }

}
