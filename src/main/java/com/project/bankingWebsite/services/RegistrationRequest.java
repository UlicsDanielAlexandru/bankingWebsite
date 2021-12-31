package com.project.bankingWebsite.services;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String CNP;
    private String address;
    private String email;
    private String phoneNumber;

}
