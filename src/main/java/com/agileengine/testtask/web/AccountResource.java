package com.agileengine.testtask.web;

import com.agileengine.testtask.service.AccountService;
import com.agileengine.testtask.web.dtos.AccountBalanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/accounts/current/balance")
public class AccountResource {

    private final AccountService accountService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountBalanceDto getAccountBalance() {
        BigDecimal balance = accountService.getBalance();
        return new AccountBalanceDto(balance);
    }
}
