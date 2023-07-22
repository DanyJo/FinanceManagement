package com.fmi.pfmapp.budget.service;

import com.fmi.pfmapp.budget.Budget;
import com.fmi.pfmapp.exception.ResourceNotFoundException;

import java.util.Set;

public interface BudgetService {
    void createBudget(Budget budget);

    Budget removeBudget(long id) throws ResourceNotFoundException;

    Budget getBudgetById(long id) throws ResourceNotFoundException;

    Set<Budget> getAllBudgets();
}
