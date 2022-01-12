package com.project.bankingWebsite.repositories;

import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByClient(Client client);

    Account findByIBAN(String IBAN);

}
