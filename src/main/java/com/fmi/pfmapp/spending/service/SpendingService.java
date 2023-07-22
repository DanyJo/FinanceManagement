package com.fmi.pfmapp.spending.service;

import com.fmi.pfmapp.budget.Budget;
import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.spending.Spending;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public interface SpendingService {
    void addSpending(Budget budget, Spending spending);

    Spending getSpendingById(long spendingId) throws ResourceNotFoundException;

    Set<Spending> getAllSpendings();

    Set<Spending> getAllSpendingsFromBudget(Budget budget);

    Set<Spending> getSpendingsFromBudgetAfter(Budget budget, LocalDate date);

    Set<Spending> getSpendingsFromBudgetBefore(Budget budget, LocalDate date);

    BigDecimal sumBudgetSpendings(Budget budget);
}
