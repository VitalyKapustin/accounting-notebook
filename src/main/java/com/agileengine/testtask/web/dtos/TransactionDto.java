package com.agileengine.testtask.web.dtos;

import com.agileengine.testtask.persistence.type.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class TransactionDto {

    private UUID id;

    private TransactionType type;

    private BigDecimal amount;

    private Date effectiveDate;
}
