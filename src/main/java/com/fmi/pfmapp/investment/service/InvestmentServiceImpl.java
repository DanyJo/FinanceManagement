package com.fmi.pfmapp.investment.service;

import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.investment.Investment;
import com.fmi.pfmapp.investment.repository.InvestmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {
    private InvestmentRepository repository;

    @Override
    public void createInvestment(Investment investment) {
        checkValidInvestment(investment);

        repository.save(investment);
    }

    @Override
    public Investment removeInvestment(long id) throws ResourceNotFoundException {
        Investment investment = getInvestmentById(id);

        repository.delete(investment);

        return investment;
    }

    @Override
    public Investment getInvestmentById(long id) throws ResourceNotFoundException {
        checkIsPositive(id);

        Optional<Investment> investment = repository.findById(id);

        if (investment.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find a investment with id " + id + ".");
        }

        return investment.get();
    }

    @Override
    public Set<Investment> getAllInvestments() {
        return Set.copyOf(repository.findAll());
    }

    @Override
    public Set<Investment> getByName(String name) {
        checkIsNullEmptyOrBlank(name);

        return repository.findAllByName(name);
    }

    @Override
    public BigDecimal getInvestmentProfit(long id) {
        Investment investment = getInvestmentById(id);

        return investment.getInvestedAmount().add(investment.getInvestedAmount().multiply(investment.getGrowth()));
    }

    @Override
    public Investment updateInvestment(long id, Investment newInvestment) {
        checkValidInvestment(newInvestment);

        Investment oldInvestment = getInvestmentById(id);

        oldInvestment.setInvestedAmount(newInvestment.getInvestedAmount());
        oldInvestment.setName(newInvestment.getName());
        oldInvestment.setGrowth(newInvestment.getGrowth());
        oldInvestment.setMadeOn(newInvestment.getMadeOn());

        repository.save(oldInvestment);

        return oldInvestment;
    }

    private void checkIsNull(Investment investment) {
        if (investment == null) {
            throw new IllegalArgumentException("Trying to pass a null value Investment object.");
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
            throw new IllegalArgumentException("Name cannot be null, empty or blank.");
        }
    }

    private void checkIsPositive(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Trying to set a negative investedAmount/growth.");
        }
    }

    private void checkIsPositive(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Trying to use a non-positive number for id.");
        }
    }

    private void checkValidInvestment(Investment investment) {
        checkIsNull(investment);
        checkIsNullEmptyOrBlank(investment.getName());
        checkIsNull(investment.getInvestedAmount());
        checkIsPositive(investment.getInvestedAmount());
        checkIsNull(investment.getGrowth());
        checkIsPositive(investment.getGrowth());
        checkIsNull(investment.getMadeOn());
    }

}
