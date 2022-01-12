package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Transaction;
import com.project.bankingWebsite.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private TransactionRepository transactionRepository;

    public void makeTransaction(Account accountFrom, Account accountTo, Double amount)
    {
        Transaction transaction = new Transaction(accountFrom, accountTo, amount, LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    public List<Transaction> loadAllTransactions()
    {
        List<Transaction> transactions = transactionRepository.findAll();
        transactions.sort((x, y) -> y.getDate().compareTo(x.getDate()));
        return transactions;
    }

    public List<Transaction> loadTransactions(Account account)
    {
        List<Transaction> transactionsTo = transactionRepository.findByAccountTo(account);
        List<Transaction> transactionsFrom = transactionRepository.findByAccountFrom(account);
        transactionsTo.addAll(transactionsFrom);
        transactionsTo.sort((x, y) -> y.getDate().compareTo(x.getDate()));
        return transactionsTo;
    }

    public void deleteTransactionByAccountTo(Account account)
    {
        transactionRepository.deleteByAccountTo(account);
    }

    public void deleteTransactionByAccountFrom(Account account)
    {
        transactionRepository.deleteByAccountFrom(account);
    }


}
