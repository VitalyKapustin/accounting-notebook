package com.agileengine.testtask.service;

import com.agileengine.testtask.persistence.entities.TransactionEntity;
import com.agileengine.testtask.persistence.repositories.TransactionRepository;
import com.agileengine.testtask.service.error.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Transactional(readOnly = true)
    public List<TransactionEntity> list() {
        return transactionRepository.findAll();
    }

    @Transactional
    public TransactionEntity create(TransactionEntity transactionEntity) {
        accountService.updateBalance(transactionEntity.getAmount());
        return transactionRepository.save(transactionEntity);
    }

    @Transactional(readOnly = true)
    public TransactionEntity get(UUID id) {
        return transactionRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
    }
}
