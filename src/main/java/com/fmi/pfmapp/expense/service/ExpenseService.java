package com.fmi.pfmapp.expense.service;

import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.expense.Expense;
import com.fmi.pfmapp.expense.RecurringPeriod;

import java.util.Set;

public interface ExpenseService {
    void createExpense(Expense expense);

    Expense removeExpense(long id) throws ResourceNotFoundException;

    Expense getExpenseById(long id) throws ResourceNotFoundException;

    Expense attachReceipt(long id, String receipt);

    Set<Expense> getAllExpenses();

    Set<Expense> getByCategory(String category);

    Set<Expense> getByRecurringPeriod(RecurringPeriod period);
}
