package com.project.bankingWebsite.repositories;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountTo(Account accountTo);

    List<Transaction> findByAccountFrom(Account accountFrom);

    long deleteByAccountTo(Account accountTo);

    long deleteByAccountFrom(Account accountFrom);
}
