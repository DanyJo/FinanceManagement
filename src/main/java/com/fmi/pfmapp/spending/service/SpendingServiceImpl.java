package com.fmi.pfmapp.spending.service;

import com.fmi.pfmapp.budget.Budget;
import com.fmi.pfmapp.exception.InsufficientFunds;
import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.spending.Spending;
import com.fmi.pfmapp.spending.repository.SpendingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SpendingServiceImpl implements SpendingService {
    private SpendingRepository repository;

    @Override
    public void addSpending(Budget budget, Spending spending) {
        checkIsNull(budget);
        checkIsNull(spending);
        checkIsPositive(spending.getAmount());

        BigDecimal newBudgetState = sumBudgetSpendings(budget).add(spending.getAmount());
        checkWithinBudget(newBudgetState, budget.getAmount());

        saveSpending(spending, budget);
    }


    private void saveSpending(Spending spending, Budget budget) {
        spending.setBudget(budget);

        repository.save(spending);
    }


    @Override
    public Spending getSpendingById(long spendingId) throws ResourceNotFoundException {
        checkIsPositive(spendingId);
        Optional<Spending> spending = repository.findById(spendingId);

        if (spending.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find a spending with id " + spendingId + ".");
        }

        return spending.get();
    }

    @Override
    public Set<Spending> getAllSpendings() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public Set<Spending> getAllSpendingsFromBudget(Budget budget) {
        checkIsNull(budget);

        return repository.findByBudget(budget);
    }

    @Override
    public Set<Spending> getSpendingsFromBudgetAfter(Budget budget, LocalDate date) {
        checkIsNull(budget);
        checkIsNull(date);

        return repository.findByBudgetAndMadeOnAfter(budget, date);
    }

    @Override
    public Set<Spending> getSpendingsFromBudgetBefore(Budget budget, LocalDate date) {
        checkIsNull(budget);
        checkIsNull(date);

        return repository.findByBudgetAndMadeOnBefore(budget, date);
    }

    @Override
    public BigDecimal sumBudgetSpendings(Budget budget) {
        checkIsNull(budget);

        return repository.sumSpendingFromBudget(budget);
    }

    private void checkIsNull(Spending spending) {
        if (spending == null) {
            throw new IllegalArgumentException("Trying to pass a null value Spending object.");
        }
    }

    private void checkIsNull(Budget budget) {
        if (budget == null) {
            throw new IllegalArgumentException("Trying to pass a null value Budget object.");
        }
    }

    private void checkIsNull(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Trying to pass a null value localDate object.");
        }
    }

    private void checkIsPositive(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Trying to access a record with non-positive id.");
        }
    }

    private void checkIsPositive(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Trying to add negative amount to budget.");
        }
    }

    private void checkWithinBudget(BigDecimal totalSpendings, BigDecimal budgetLimit) {
        if (totalSpendings.compareTo(budgetLimit) > 0) {
            throw new InsufficientFunds("Budget exceeds its limits by " + totalSpendings.subtract(budgetLimit) + ".");
        }
    }
}
