package com.fmi.pfmapp.saving.controller;

import com.fmi.pfmapp.saving.Saving;
import com.fmi.pfmapp.saving.dto.SavingDto;
import com.fmi.pfmapp.saving.mapper.SavingMapper;
import com.fmi.pfmapp.saving.service.SavingService;
import com.fmi.pfmapp.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("savings")
public class SavingControllerImpl implements SavingController {
    private SavingService savingService;
    private TransactionService transactionService;
    private SavingMapper mapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public SavingDto createSaving(@RequestBody SavingDto savingDto) {
        savingService.createSaving(mapper.toEntity(savingDto));

        return savingDto;
    }

    @Override
    @DeleteMapping("/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public SavingDto deleteSaving(@PathVariable String iban) {
        return mapper.toDto(savingService.removeSaving(iban));
    }

    @Override
    @GetMapping("/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public SavingDto getSavingByIban(@PathVariable("iban") String iban) {
        return mapper.toDto(savingService.getSavingByIban(iban));
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<SavingDto> getAllSavings() {
        return mapper.toCollectionDto(savingService.getAllSavings());
    }

    @Override
    @GetMapping("/amount/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public String getSavingAmount(@PathVariable("iban") String iban) {
        Saving saving = savingService.getSavingByIban(iban);

        return "Saving amount is " + transactionService.getSavingAmount(saving);
    }

    @Override
    @GetMapping("/before/{date}")
    @ResponseStatus(HttpStatus.OK)
    public Set<SavingDto> getAllSavingsBefore(@PathVariable("date") String date) {
        LocalDate localDate = getDateFromString(date);

        return mapper.toCollectionDto(savingService.getSavingsCreatedBefore(localDate));
    }

    @Override
    @GetMapping("/after/{date}")
    @ResponseStatus(HttpStatus.OK)
    public Set<SavingDto> getAllSavingsAfter(@PathVariable("date") String date) {
        LocalDate localDate = getDateFromString(date);

        return mapper.toCollectionDto(savingService.getSavingsCreatedAfter(localDate));
    }

    private LocalDate getDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(date, formatter);
    }
}
