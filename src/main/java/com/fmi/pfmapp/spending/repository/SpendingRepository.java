package com.fmi.pfmapp.spending.repository;

import com.fmi.pfmapp.budget.Budget;
import com.fmi.pfmapp.spending.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long> {
    @Query("SELECT COALESCE(SUM(s.amount), 0) FROM Spending s WHERE s.budget = :budget")
    BigDecimal sumSpendingFromBudget(@Param("budget") Budget budget);

    Set<Spending> findByBudget(Budget budget);

    Set<Spending> findByBudgetAndMadeOnAfter(Budget budget, LocalDate date);

    Set<Spending> findByBudgetAndMadeOnBefore(Budget budget, LocalDate date);
}
