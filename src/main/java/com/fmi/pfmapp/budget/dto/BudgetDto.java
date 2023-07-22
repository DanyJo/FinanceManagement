package com.fmi.pfmapp.budget.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BudgetDto {
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
