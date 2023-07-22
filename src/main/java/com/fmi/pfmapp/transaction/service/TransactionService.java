package com.fmi.pfmapp.transaction.service;

import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.saving.Saving;
import com.fmi.pfmapp.transaction.Transaction;
import com.fmi.pfmapp.transaction.TransactionType;

import java.math.BigDecimal;
import java.util.Set;

public interface TransactionService {
    void addTransaction(TransactionType type, BigDecimal amount, Saving saving);

    Transaction getTransactionById(long transactionId) throws ResourceNotFoundException;

    Set<Transaction> getAllTransactions();

    Set<Transaction> getAllTransactionsFromSaving(Saving saving);

    Set<Transaction> getTransactionsByTypeFromSaving(TransactionType type, Saving saving);

    BigDecimal getSavingAmount(Saving saving);
}
