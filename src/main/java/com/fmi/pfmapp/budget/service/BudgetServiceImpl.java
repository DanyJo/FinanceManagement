package com.fmi.pfmapp.budget.service;

import com.fmi.pfmapp.budget.Budget;
import com.fmi.pfmapp.budget.repository.BudgetRepository;
import com.fmi.pfmapp.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class BudgetServiceImpl implements BudgetService {
    private BudgetRepository repository;

    @Override
    public void createBudget(Budget budget) {
        checkIsNull(budget);
        checkIsNull(budget.getAmount());
        checkIsPositive(budget.getAmount());

        repository.save(budget);
    }

    @Override
    public Budget removeBudget(long id) throws ResourceNotFoundException {
        Budget budget = getBudgetById(id);

        repository.delete(budget);

        return budget;
    }

    @Override
    public Budget getBudgetById(long id) throws ResourceNotFoundException {
        checkIsPositive(id);

        Optional<Budget> budget = repository.findById(id);

        if (budget.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find a budget with id " + id + ".");
        }

        return budget.get();
    }

    @Override
    public Set<Budget> getAllBudgets() {
        return Set.copyOf(repository.findAll());
    }


    private void checkIsNull(Budget budget) {
        if (budget == null) {
            throw new IllegalArgumentException("Trying to pass a null value Budget object.");
        }
    }

    private void checkIsNull(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Trying to pass a null value BigDecimal object.");
        }
    }

    private void checkIsPositive(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Trying to set a non-positive amount for budget limit.");
        }
    }

    private void checkIsPositive(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Trying to add negative amount to budget.");
        }
    }
}
