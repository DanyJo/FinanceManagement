package com.fmi.pfmapp.budget.controller;

import com.fmi.pfmapp.budget.dto.BudgetDto;

import java.util.List;

public interface BudgetController {
    BudgetDto createBudget(BudgetDto budgetDto);

    BudgetDto deleteBudget(long id);

    List<BudgetDto> getAllBudgets();

    BudgetDto getBudgetById(int id);

    String getBudgetState(long id);
}
