package com.agileengine.testtask.web;

import com.agileengine.testtask.persistence.entities.TransactionEntity;
import com.agileengine.testtask.service.TransactionService;
import com.agileengine.testtask.web.dtos.TransactionCreateDto;
import com.agileengine.testtask.web.dtos.TransactionDto;
import com.agileengine.testtask.web.mappers.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionResource {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionDto> list() {
        List<TransactionEntity> transactionEntityEntities = transactionService.list();
        return transactionMapper.toDto(transactionEntityEntities);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDto create(@RequestBody TransactionCreateDto createDto) {
        TransactionEntity transactionEntity = new TransactionEntity(createDto.getType(), createDto.getAmount());
        TransactionEntity savedTransactionEntity = transactionService.create(transactionEntity);
        return transactionMapper.toDto(savedTransactionEntity);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDto get(@PathVariable UUID id) {
        TransactionEntity transactionEntity = transactionService.get(id);
        return transactionMapper.toDto(transactionEntity);
    }
}
