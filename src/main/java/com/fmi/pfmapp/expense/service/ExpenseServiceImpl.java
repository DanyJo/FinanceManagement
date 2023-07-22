package com.fmi.pfmapp.expense.service;

import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.expense.Expense;
import com.fmi.pfmapp.expense.RecurringPeriod;
import com.fmi.pfmapp.expense.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private ExpenseRepository repository;

    @Override
    public void createExpense(Expense expense) {
        checkIsNull(expense);
        checkIsNull(expense.getAmount());
        checkIsPositive(expense.getAmount());
        checkIsNull(expense.getDate());
        checkIsNullEmptyOrBlank(expense.getCategory());

        repository.save(expense);
    }

    @Override
    public Expense removeExpense(long id) throws ResourceNotFoundException {
        Expense expense = getExpenseById(id);

        repository.delete(expense);

        return expense;
    }

    @Override
    public Expense getExpenseById(long id) throws ResourceNotFoundException {
        checkIsPositive(id);

        Optional<Expense> expense = repository.findById(id);

        if (expense.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find a expense with id " + id + ".");
        }

        return expense.get();
    }

    @Override
    public Expense attachReceipt(long id, String receipt) {
        checkIsNullEmptyOrBlank(receipt);

        Expense expense = getExpenseById(id);

        expense.setReceipt(receipt);
        repository.save(expense);

        return expense;
    }

    @Override
    public Set<Expense> getAllExpenses() {
        return Set.copyOf(repository.findAll());
    }

    @Override
    public Set<Expense> getByCategory(String category) {
        checkIsNullEmptyOrBlank(category);

        return repository.findAllByCategory(category);
    }

    @Override
    public Set<Expense> getByRecurringPeriod(RecurringPeriod period) {
        checkIsNull(period);

        return repository.findAllByRecurringPeriod(period);
    }

    private void checkIsNull(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Trying to pass a null value Expense object.");
        }
    }

    private void checkIsNull(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Trying to pass a null value BigDecimal object.");
        }
    }

    private void checkIsNull(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Trying to pass a null value LocalDate object");
        }
    }

    private void checkIsNull(RecurringPeriod period) {
        if (period == null) {
            throw new IllegalArgumentException("Trying to pass a null value RecurringPeriod object.");
        }
    }

    private void checkIsNullEmptyOrBlank(String string) {
        if (string == null || string.isEmpty() || string.isBlank()) {
            throw new IllegalArgumentException("Categories cannot be null, empty or blank.");
        }
    }

    private void checkIsPositive(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Trying to set a negative income.");
        }
    }

    private void checkIsPositive(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Trying to use a non-positive number for id.");
        }
    }
}
