package com.fmi.pfmapp.budget.repository;

import com.fmi.pfmapp.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
