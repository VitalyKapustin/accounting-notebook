package com.agileengine.testtask.persistence.entities;

import com.agileengine.testtask.persistence.type.TransactionType;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal amount;

    private Date effectiveDate = new Date();

    private TransactionEntity() {
    }

    public TransactionEntity(TransactionType type, BigDecimal amount) {
        this.type = type;
        this.amount = type == TransactionType.DEBIT
                ? amount.negate()
                : amount;
    }
}
