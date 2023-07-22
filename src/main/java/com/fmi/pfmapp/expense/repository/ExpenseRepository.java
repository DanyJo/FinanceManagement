package com.fmi.pfmapp.expense.repository;

import com.fmi.pfmapp.expense.Expense;
import com.fmi.pfmapp.expense.RecurringPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Set<Expense> findAllByCategory(String category);

    Set<Expense> findAllByRecurringPeriod(RecurringPeriod period);
}
