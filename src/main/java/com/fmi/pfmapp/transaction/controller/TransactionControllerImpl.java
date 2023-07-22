package com.fmi.pfmapp.transaction.controller;

import com.fmi.pfmapp.saving.Saving;
import com.fmi.pfmapp.saving.service.SavingService;
import com.fmi.pfmapp.transaction.Transaction;
import com.fmi.pfmapp.transaction.TransactionType;
import com.fmi.pfmapp.transaction.dto.TransactionDto;
import com.fmi.pfmapp.transaction.mapper.TransactionMapper;
import com.fmi.pfmapp.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("transactions")
public class TransactionControllerImpl implements TransactionController {

    private TransactionService transactionService;
    private SavingService savingService;
    private TransactionMapper mapper;

    @Override
    @PostMapping("/{iban}/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeDepositToSaving(@PathVariable("iban") String iban, @RequestBody String amount) {
        Saving saving = savingService.getSavingByIban(iban);

        transactionService.addTransaction(TransactionType.DEPOSIT, new BigDecimal(amount), saving);
    }

    @Override
    @PostMapping("/{iban}/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeWithdrawFromSaving(@PathVariable("iban") String iban, @RequestBody String amount) {
        Saving saving = savingService.getSavingByIban(iban);

        transactionService.addTransaction(TransactionType.WITHDRAW, new BigDecimal(amount), saving);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getAllTransactions() {
        return mapper.toCollectionDto(transactionService.getAllTransactions());
    }

    @Override
    @GetMapping("/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDto getTransactionById(@PathVariable("transactionId") long transactionId) {
        return mapper.toDto(transactionService.getTransactionById(transactionId));
    }

    @Override
    @GetMapping("/saving/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getAllTransactionsFromSaving(@PathVariable("iban") String iban) {
        Saving saving = savingService.getSavingByIban(iban);

        return mapper.toCollectionDto(transactionService.getAllTransactionsFromSaving(saving));
    }

    @Override
    @GetMapping("/{iban}/deposits")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getAllDepositsFromSaving(@PathVariable("iban") String iban) {
        Saving saving = savingService.getSavingByIban(iban);

        Set<Transaction> transactions = transactionService
                .getTransactionsByTypeFromSaving(TransactionType.DEPOSIT, saving);

        return mapper.toCollectionDto(transactions);
    }

    @Override
    @GetMapping("/{iban}/withdraws")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getAllWithdrawsSaving(@PathVariable("iban") String iban) {
        Saving saving = savingService.getSavingByIban(iban);

        Set<Transaction> transactions = transactionService
                .getTransactionsByTypeFromSaving(TransactionType.WITHDRAW, saving);

        return mapper.toCollectionDto(transactions);
    }

}
