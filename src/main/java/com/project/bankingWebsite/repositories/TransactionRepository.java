package com.project.bankingWebsite.repositories;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountTo(Account accountTo);

    List<Transaction> findByAccountFrom(Account accountFrom);

}
