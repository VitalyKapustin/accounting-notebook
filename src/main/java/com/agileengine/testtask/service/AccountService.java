package com.agileengine.testtask.service;

import com.agileengine.testtask.persistence.entities.AccountEntity;
import com.agileengine.testtask.persistence.repositories.AccountRepository;
import com.agileengine.testtask.service.error.ObjectNotFoundException;
import com.agileengine.testtask.service.error.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class AccountService {

    private static final long CURRENT_ACCOUNT_ID = 1L;

    private final AccountRepository accountRepository;

    @Transactional
    public void updateBalance(BigDecimal amount) {
        AccountEntity currentAccount = accountRepository.findByIdWithPessimisticLock(CURRENT_ACCOUNT_ID);
        BigDecimal balance = currentAccount.getBalance();
        BigDecimal newBalance = balance.add(amount);

        validateBalance(newBalance);

        currentAccount.setBalance(newBalance);
        accountRepository.save(currentAccount);
    }

    private void validateBalance(BigDecimal newBalance) {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Refused amount due to account balance cannot be negative");
        }
    }

    @Transactional(readOnly = true)
    public BigDecimal getBalance() {
        AccountEntity currentAccount = accountRepository.findById(1L).orElseThrow(ObjectNotFoundException::new);
        return currentAccount.getBalance();
    }
}
