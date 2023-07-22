package com.fmi.pfmapp.transaction.repository;

import com.fmi.pfmapp.saving.Saving;
import com.fmi.pfmapp.transaction.Transaction;
import com.fmi.pfmapp.transaction.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.saving = :saving AND t.type = :type")
    BigDecimal sumAmountFromSavingByTransactionType(Saving saving, TransactionType type);

    Set<Transaction> findBySaving(Saving saving);

    Set<Transaction> findBySavingAndType(Saving saving, TransactionType type);
}
