package com.fmi.pfmapp.spending.controller;


import com.fmi.pfmapp.spending.dto.SpendingDto;

import java.util.List;

public interface SpendingController {
    void spentFromBudget(long budgetId, SpendingDto spendingDto);

    List<SpendingDto> getAllSpendings();

    SpendingDto getSpendingById(long spendingId);

    List<SpendingDto> getSpendingsFromBudget(long budgetId);

    List<SpendingDto> getSpendingsFromBudgetAfter(long budgetId, String date);

    List<SpendingDto> getSpendingsFromBudgetBefore(long budgetId, String date);
}
