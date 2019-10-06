package com.agileengine.testtask.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AccountBalanceDto {

    private BigDecimal balance;
}
