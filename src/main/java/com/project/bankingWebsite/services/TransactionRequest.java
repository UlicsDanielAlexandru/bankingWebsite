package com.project.bankingWebsite.services;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TransactionRequest {

    private String receiverType;
    private String receiver;
    private Double amount;

}
