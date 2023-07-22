package com.fmi.pfmapp.income.service;

import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.income.Income;
import com.fmi.pfmapp.income.repository.IncomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class IncomeServiceImpl implements IncomeService {
    private IncomeRepository repository;

    @Override
    public void createIncome(Income income) {
        checkIsNull(income);
        checkIsNull(income.getAmount());
        checkIsPositive(income.getAmount());
        checkIsNull(income.getDate());
        checkIsNullEmptyOrBlank(income.getCategory());

        repository.save(income);
    }

    @Override
    public Income removeIncome(long id) throws ResourceNotFoundException {
        Income income = getIncomeById(id);

        repository.delete(income);

        return income;
    }

    @Override
    public Income getIncomeById(long id) throws ResourceNotFoundException {
        checkIsPositive(id);

        Optional<Income> income = repository.findById(id);

        if (income.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find a budget with id " + id + ".");
        }

        return income.get();
    }

    @Override
    public Income attachNote(long id, String note) {
        checkIsNullEmptyOrBlank(note);

        Income income = getIncomeById(id);

        income.setNote(note);
        repository.save(income);

        return income;
    }

    @Override
    public Set<Income> getAllIncomes() {
        return Set.copyOf(repository.findAll());
    }

    @Override
    public Set<Income> getByCategory(String category) {
        checkIsNullEmptyOrBlank(category);

        return repository.findAllByCategory(category);
    }

    private void checkIsNull(Income income) {
        if (income == null) {
            throw new IllegalArgumentException("Trying to pass a null value Income object.");
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

    private void checkIsNullEmptyOrBlank(String string) {
        if (string == null || string.isEmpty() || string.isBlank()) {
            throw new IllegalArgumentException("Categories/notes cannot be null, empty or blank.");
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
