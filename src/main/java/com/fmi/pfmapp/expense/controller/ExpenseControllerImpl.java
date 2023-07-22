package com.fmi.pfmapp.expense.controller;

import com.fmi.pfmapp.expense.RecurringPeriod;
import com.fmi.pfmapp.expense.dto.ExpenseDto;
import com.fmi.pfmapp.expense.mapper.ExpenseMapper;
import com.fmi.pfmapp.expense.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("expenses")
public class ExpenseControllerImpl implements ExpenseController {
    private ExpenseService service;
    private ExpenseMapper mapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseDto createExpense(@RequestBody ExpenseDto expenseDto) {
        service.createExpense(mapper.toEntity(expenseDto));

        return expenseDto;
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExpenseDto deleteExpense(@PathVariable("id") long id) {
        return mapper.toDto(service.removeExpense(id));
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExpenseDto getExpenseById(@PathVariable("id") long id) {
        return mapper.toDto(service.getExpenseById(id));
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseDto> getAllExpenses() {
        return mapper.toCollectionDto(service.getAllExpenses());
    }

    @Override
    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseDto> getExpensesByCategory(@PathVariable("category") String category) {
        return mapper.toCollectionDto(service.getByCategory(category));
    }

    @Override
    @GetMapping("/period/{period}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseDto> getExpensesByRecurringPeriod(@PathVariable("period") RecurringPeriod period) {
        return mapper.toCollectionDto(service.getByRecurringPeriod(period));
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExpenseDto attachReceipt(@PathVariable("id") long id, @RequestBody String receipt) {
        return mapper.toDto(service.attachReceipt(id, receipt));
    }
}
