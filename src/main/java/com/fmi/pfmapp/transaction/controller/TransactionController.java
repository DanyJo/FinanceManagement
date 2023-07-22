package com.fmi.pfmapp.transaction.controller;

import com.fmi.pfmapp.transaction.dto.TransactionDto;

import java.util.List;

public interface TransactionController {
    void makeDepositToSaving(String iban, String amount);

    void makeWithdrawFromSaving(String iban, String amount);

    List<TransactionDto> getAllTransactions();

    TransactionDto getTransactionById(long transactionId);

    List<TransactionDto> getAllTransactionsFromSaving(String iban);

    List<TransactionDto> getAllDepositsFromSaving(String iban);

    List<TransactionDto> getAllWithdrawsSaving(String iban);
}
