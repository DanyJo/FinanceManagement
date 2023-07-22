package com.fmi.pfmapp.spending.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SpendingDto {
    private BigDecimal amount;
    private LocalDate madeOn;
    private String spentOn;
}
