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

}
