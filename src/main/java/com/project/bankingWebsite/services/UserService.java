package com.project.bankingWebsite.services;

import com.project.bankingWebsite.model.User;
import com.project.bankingWebsite.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException
                ("Contul cu numele de utilizator " + username +" nu a fost găsit!"));
    }

    public void register(User user)
    {
        boolean alreadyExists = userRepository.findByUsername(user.getUsername()).isPresent();
        if(alreadyExists)
            throw new IllegalStateException("Nume de utilizator luat deja!");
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void updateUser(User user)
    {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void deleteClient(String username)
    {
        userRepository.deleteByUsername(username);
    }

}
