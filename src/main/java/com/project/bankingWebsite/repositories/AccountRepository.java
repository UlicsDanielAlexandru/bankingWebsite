package com.project.bankingWebsite.repositories;

import com.project.bankingWebsite.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
