package com.fmi.pfmapp.transaction.mapper;

import com.fmi.pfmapp.transaction.Transaction;
import com.fmi.pfmapp.transaction.dto.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface TransactionMapper {
    Transaction toEntity(TransactionDto transactionDto);

    TransactionDto toDto(Transaction transaction);

    List<TransactionDto> toCollectionDto(Collection<Transaction> transactions);
}
