package com.agileengine.testtask.web.mappers;

import com.agileengine.testtask.persistence.entities.TransactionEntity;
import com.agileengine.testtask.web.dtos.TransactionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto toDto(TransactionEntity entity);

    List<TransactionDto> toDto(List<TransactionEntity> entities);
}
