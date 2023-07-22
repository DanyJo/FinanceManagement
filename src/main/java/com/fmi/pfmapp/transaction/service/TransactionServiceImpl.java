package com.fmi.pfmapp.transaction.service;

import com.fmi.pfmapp.exception.InsufficientFunds;
import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.saving.Saving;
import com.fmi.pfmapp.transaction.Transaction;
import com.fmi.pfmapp.transaction.TransactionType;
import com.fmi.pfmapp.transaction.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository repository;

    @Override
    public void addTransaction(TransactionType type, BigDecimal amount, Saving saving) {
        checkIsNull(type);
        checkIsNull(saving);
        checkIsNull(amount);
        checkIsPositive(amount);

        BigDecimal savingAmount = getSavingAmount(saving);
        checkValidWithdraw(type, savingAmount, amount);

        repository.save(createTransaction(type, amount, LocalDate.now(), saving));
    }

    private Transaction createTransaction(TransactionType type, BigDecimal amount, LocalDate madeOn, Saving saving) {
        return Transaction.builder()
                .amount(amount)
                .type(type)
                .madeOn(madeOn)
                .saving(saving)
                .build();
    }

    @Override
    public Transaction getTransactionById(long transactionId) throws ResourceNotFoundException {
        checkIsPositive(transactionId);

        Optional<Transaction> transaction = repository.findById(transactionId);

        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find a transaction with id " + transaction + ".");
        }

        return transaction.get();
    }

    @Override
    public Set<Transaction> getAllTransactions() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public Set<Transaction> getAllTransactionsFromSaving(Saving saving) {
        checkIsNull(saving);

        return repository.findBySaving(saving);
    }

    @Override
    public Set<Transaction> getTransactionsByTypeFromSaving(TransactionType type, Saving saving) {
        checkIsNull(type);
        checkIsNull(saving);

        return repository.findBySavingAndType(saving, type);
    }

    @Override
    public BigDecimal getSavingAmount(Saving saving) {
        BigDecimal savingDeposits = repository
                .sumAmountFromSavingByTransactionType(saving, TransactionType.DEPOSIT);
        BigDecimal savingWithdraws = repository
                .sumAmountFromSavingByTransactionType(saving, TransactionType.WITHDRAW);

        return savingDeposits.subtract(savingWithdraws);
    }

    private void checkIsNull(TransactionType type) {
        if (type == null) {
            throw new IllegalArgumentException("Trying to pass a null value savingTransactionType object.");
        }
    }

    private void checkIsNull(Saving saving) {
        if (saving == null) {
            throw new IllegalArgumentException("Trying to pass a null value saving object.");
        }
    }

    private void checkIsNull(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Trying to pass a null value BigDecimal object.");
        }
    }

    private void checkIsPositive(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Trying to access a record with non-positive id.");
        }
    }

    private void checkIsPositive(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Trying to add negative amount to saving.");
        }
    }


    private void checkValidWithdraw(TransactionType type, BigDecimal savingAmount, BigDecimal amount) {
        if (type.equals(TransactionType.WITHDRAW)) {
            checkSufficientFunds(savingAmount, amount);
        }
    }

    private void checkSufficientFunds(BigDecimal savingAmount, BigDecimal withdrawAmount) throws InsufficientFunds {
        if (savingAmount.subtract(withdrawAmount).compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFunds("Current balance is " + withdrawAmount.subtract(savingAmount) +
                    " less than the desired withdrawn amount.");
        }
    }
}
