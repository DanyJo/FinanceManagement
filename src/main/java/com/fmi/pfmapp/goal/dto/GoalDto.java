package com.fmi.pfmapp.goal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GoalDto {
    private String name;
    private BigDecimal amount;
    private String description;
    private LocalDate startDate;
}
