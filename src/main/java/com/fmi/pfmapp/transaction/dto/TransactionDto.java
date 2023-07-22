package com.fmi.pfmapp.transaction.dto;

import com.fmi.pfmapp.transaction.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class TransactionDto {
    private BigDecimal amount;
    private TransactionType type;
    private LocalDate madeOn;
}
