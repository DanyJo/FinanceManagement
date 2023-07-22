package com.fmi.pfmapp.saving.controller;

import com.fmi.pfmapp.saving.dto.SavingDto;

import java.util.Set;

public interface SavingController {
    SavingDto createSaving(SavingDto savingDto);

    SavingDto deleteSaving(String iban);

    SavingDto getSavingByIban(String iban);

    Set<SavingDto> getAllSavings();

    String getSavingAmount(String iban);

    Set<SavingDto> getAllSavingsBefore(String date);

    Set<SavingDto> getAllSavingsAfter(String date);
}
