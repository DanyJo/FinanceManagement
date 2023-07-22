package com.fmi.pfmapp.income.service;

import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.income.Income;

import java.util.Set;

public interface IncomeService {
    void createIncome(Income income);

    Income removeIncome(long id) throws ResourceNotFoundException;

    Income getIncomeById(long id) throws ResourceNotFoundException;

    Income attachNote(long id, String note);

    Set<Income> getAllIncomes();

    Set<Income> getByCategory(String category);
}
