package com.fmi.pfmapp.expense.controller;

import com.fmi.pfmapp.expense.RecurringPeriod;
import com.fmi.pfmapp.expense.dto.ExpenseDto;

import java.util.List;

public interface ExpenseController {
    ExpenseDto createExpense(ExpenseDto expenseDto);

    ExpenseDto deleteExpense(long id);

    ExpenseDto getExpenseById(long id);

    List<ExpenseDto> getAllExpenses();

    List<ExpenseDto> getExpensesByCategory(String category);

    List<ExpenseDto> getExpensesByRecurringPeriod(RecurringPeriod period);

    ExpenseDto attachReceipt(long id, String receipt);
}
