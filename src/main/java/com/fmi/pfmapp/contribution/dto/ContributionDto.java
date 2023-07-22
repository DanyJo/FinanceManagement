package com.fmi.pfmapp.contribution.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContributionDto {
    private BigDecimal amount;

    private LocalDate madeOn;
}
