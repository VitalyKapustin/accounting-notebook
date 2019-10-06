package com.agileengine.testtask.web.dtos;

import com.agileengine.testtask.persistence.type.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionCreateDto {

    private TransactionType type;

    private BigDecimal amount;
}
