package com.fmi.pfmapp.saving.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class SavingDto {
    private String iban;
    private LocalDate createdOn;
}
