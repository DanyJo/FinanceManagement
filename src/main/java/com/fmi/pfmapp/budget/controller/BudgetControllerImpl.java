package com.fmi.pfmapp.budget.controller;

import com.fmi.pfmapp.budget.Budget;
import com.fmi.pfmapp.budget.dto.BudgetDto;
import com.fmi.pfmapp.budget.mapper.BudgetMapper;
import com.fmi.pfmapp.budget.service.BudgetService;
import com.fmi.pfmapp.spending.service.SpendingService;
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

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("budgets")
public class BudgetControllerImpl implements BudgetController {
    private BudgetService budgetService;
    private SpendingService spendingService;
    private BudgetMapper budgetMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BudgetDto createBudget(@RequestBody BudgetDto budgetDto) {
        budgetService.createBudget(budgetMapper.toEntity(budgetDto));

        return budgetDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BudgetDto deleteBudget(@PathVariable("id") long id) {
        return budgetMapper.toDto(budgetService.removeBudget(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BudgetDto> getAllBudgets() {
        return budgetMapper.toCollectionDto(budgetService.getAllBudgets());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BudgetDto getBudgetById(@PathVariable("id") int id) {
        return budgetMapper.toDto(budgetService.getBudgetById(id));
    }

    @Override
    @GetMapping("/state/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getBudgetState(@PathVariable("id") long id) {
        Budget budget = budgetService.getBudgetById(id);
        BigDecimal spent = spendingService.sumBudgetSpendings(budget);

        return "Budget: " + spent + "/" + budget.getAmount();
    }
}
