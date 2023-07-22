package com.fmi.pfmapp.spending.controller;

import com.fmi.pfmapp.budget.Budget;
import com.fmi.pfmapp.budget.service.BudgetService;
import com.fmi.pfmapp.spending.Spending;
import com.fmi.pfmapp.spending.dto.SpendingDto;
import com.fmi.pfmapp.spending.mapper.SpendingMapper;
import com.fmi.pfmapp.spending.service.SpendingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("spendings")
public class SpendingControllerImpl implements SpendingController {
    private SpendingService service;
    private SpendingMapper mapper;

    private BudgetService budgetService;

    @Override
    @PostMapping("/{budgetId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void spentFromBudget(@PathVariable("budgetId") long budgetId, @RequestBody SpendingDto spendingDto) {
        Budget budget = budgetService.getBudgetById(budgetId);
        Spending spending = mapper.toEntity(spendingDto);

        service.addSpending(budget, spending);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SpendingDto> getAllSpendings() {
        return mapper.toCollectionDto(service.getAllSpendings());
    }

    @Override
    @GetMapping("/{spendingId}")
    @ResponseStatus(HttpStatus.OK)
    public SpendingDto getSpendingById(@PathVariable("spendingId") long spendingId) {
        return mapper.toDto(service.getSpendingById(spendingId));
    }

    @Override
    @GetMapping("/budget/{budgetId}")
    @ResponseStatus(HttpStatus.OK)
    public List<SpendingDto> getSpendingsFromBudget(@PathVariable("budgetId") long budgetId) {
        Budget budget = budgetService.getBudgetById(budgetId);

        return mapper.toCollectionDto(service.getAllSpendingsFromBudget(budget));
    }

    @Override
    @GetMapping("/{budgetId}/after/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<SpendingDto> getSpendingsFromBudgetAfter(@PathVariable("budgetId") long budgetId,
                                                         @PathVariable("date") String date) {
        LocalDate localDate = getDateFromString(date);
        Budget budget = budgetService.getBudgetById(budgetId);

        return mapper.toCollectionDto(service.getSpendingsFromBudgetAfter(budget, localDate));
    }

    @Override
    @GetMapping("/{budgetId}/before/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<SpendingDto> getSpendingsFromBudgetBefore(@PathVariable("budgetId") long budgetId,
                                                          @PathVariable("date") String date) {
        LocalDate localDate = getDateFromString(date);
        Budget budget = budgetService.getBudgetById(budgetId);

        return mapper.toCollectionDto(service.getSpendingsFromBudgetBefore(budget, localDate));
    }

    private LocalDate getDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(date, formatter);
    }
}
