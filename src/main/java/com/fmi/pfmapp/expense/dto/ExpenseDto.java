package com.fmi.pfmapp.expense.dto;

import com.fmi.pfmapp.expense.RecurringPeriod;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseDto {
    private BigDecimal amount;
    private String category;
    private String receipt;
    private RecurringPeriod recurringPeriod;
    private LocalDate date;
}
