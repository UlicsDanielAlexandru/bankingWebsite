package com.project.bankingWebsite.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from_id", referencedColumnName = "id")
    private Account accountFrom;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to_id", referencedColumnName = "id")
    private Account accountTo;
    private double sum;
    private LocalDateTime date;

    public Transactions(Account accountFrom, Account accountTo, double sum, LocalDateTime date) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.sum = sum;
        this.date = date;
    }
}
