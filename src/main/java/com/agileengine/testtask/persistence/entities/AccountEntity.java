package com.agileengine.testtask.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Entity
public class AccountEntity {

    @Id
    private Long id;

    @Setter
    private BigDecimal balance;

    private AccountEntity() {
    }
}
