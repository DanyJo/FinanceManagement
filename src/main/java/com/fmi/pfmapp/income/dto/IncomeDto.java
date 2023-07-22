package com.fmi.pfmapp.income.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDto {
    private int amount;

    private String category;

    private String note;

    private LocalDate date;
}
