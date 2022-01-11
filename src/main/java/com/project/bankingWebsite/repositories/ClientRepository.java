package com.project.bankingWebsite.repositories;


import com.project.bankingWebsite.model.Account;
import com.project.bankingWebsite.model.Client;
import com.project.bankingWebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


    Client findByUser(User user);

    Client findByPhoneNumber(String phoneNumber);

    Client findByCNP(String CNP);
}
