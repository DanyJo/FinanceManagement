package com.fmi.pfmapp.saving.service;

import com.fmi.pfmapp.exception.ResourceAlreadyExists;
import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.saving.Saving;

import java.time.LocalDate;
import java.util.Set;

public interface SavingService {
    void createSaving(Saving saving) throws ResourceAlreadyExists;

    Saving removeSaving(String iban) throws ResourceNotFoundException;

    Saving getSavingByIban(String iban) throws ResourceNotFoundException;

    Set<Saving> getAllSavings();

    Set<Saving> getSavingsCreatedAfter(LocalDate date);

    Set<Saving> getSavingsCreatedBefore(LocalDate date);
}
