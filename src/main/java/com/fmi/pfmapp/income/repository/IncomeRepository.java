package com.fmi.pfmapp.income.repository;

import com.fmi.pfmapp.income.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    Set<Income> findAllByCategory(String category);
}
