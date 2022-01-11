package com.project.bankingWebsite.services;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ResetPasswordRequest {

    private String username;
    private String CNP;
    private String password;

}
