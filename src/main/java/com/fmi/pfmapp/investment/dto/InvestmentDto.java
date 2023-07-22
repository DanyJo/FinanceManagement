package com.fmi.pfmapp.investment.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvestmentDto {
    private BigDecimal investedAmount;
    private String name;
    private BigDecimal growth;
    private LocalDate madeOn;
}
