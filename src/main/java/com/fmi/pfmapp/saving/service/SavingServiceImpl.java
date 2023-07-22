package com.fmi.pfmapp.saving.service;

import com.fmi.pfmapp.exception.ResourceAlreadyExists;
import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.saving.Saving;
import com.fmi.pfmapp.saving.repository.SavingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SavingServiceImpl implements SavingService {
    private SavingRepository repository;

    @Override
    public void createSaving(Saving saving) throws ResourceAlreadyExists {
        checkIsNull(saving);
        checkIsNullEmptyOrBlank(saving.getIban());
        checkIsNull(saving.getCreatedOn());

        if (repository.findByIban(saving.getIban()).isPresent()) {
            throw new ResourceAlreadyExists("Saving with iban: " + saving.getIban() + " already exists.");
        }

        repository.save(saving);
    }

    @Override
    public Saving removeSaving(String iban) throws ResourceNotFoundException {
        Saving saving = getSavingByIban(iban);

        repository.delete(saving);

        return saving;
    }

    @Override
    public Saving getSavingByIban(String iban) throws ResourceNotFoundException {
        checkIsNullEmptyOrBlank(iban);

        Optional<Saving> saving = repository.findByIban(iban);

        if (saving.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find a saving with iban " + iban + ".");
        }

        return saving.get();
    }

    @Override
    public Set<Saving> getAllSavings() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public Set<Saving> getSavingsCreatedAfter(LocalDate date) {
        checkIsNull(date);

        return repository.findByCreatedOnAfter(date);
    }

    @Override
    public Set<Saving> getSavingsCreatedBefore(LocalDate date) {
        checkIsNull(date);

        return repository.findByCreatedOnBefore(date);
    }

    private void checkIsNull(Saving saving) {
        if (saving == null) {
            throw new IllegalArgumentException("Trying to pass a null value saving object.");
        }
    }

    private void checkIsNull(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Trying to pass a null value localDate object.");
        }
    }

    private void checkIsNullEmptyOrBlank(String string) {
        if (string == null || string.isEmpty() || string.isBlank()) {
            throw new IllegalArgumentException("Cannot search for a saving " +
                    "with null, blank or empty string.");
        }
    }

    private void checkIsPositive(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Trying to add negative amount to saving.");
        }
    }
}
