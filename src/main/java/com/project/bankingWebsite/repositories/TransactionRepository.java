package com.project.bankingWebsite.repositories;

import com.project.bankingWebsite.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
