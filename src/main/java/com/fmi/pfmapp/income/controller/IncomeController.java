package com.fmi.pfmapp.income.controller;

import com.fmi.pfmapp.income.dto.IncomeDto;
import com.fmi.pfmapp.income.mapper.IncomeMapper;
import com.fmi.pfmapp.income.service.IncomeService;
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

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("incomes")
public class IncomeController {
    private IncomeMapper mapper;
    private IncomeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IncomeDto createIncome(@RequestBody IncomeDto incomeDto) {
        service.createIncome(mapper.toEntity(incomeDto));

        return incomeDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IncomeDto deleteIncome(@PathVariable("id") long id) {
        return mapper.toDto(service.removeIncome(id));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IncomeDto getIncomeById(@PathVariable("id") long id) {
        return mapper.toDto(service.getIncomeById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IncomeDto> getAllIncomes() {
        return mapper.toCollectionDto(service.getAllIncomes());
    }

    @GetMapping("/category/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<IncomeDto> getIncomesByCategory(@PathVariable("category") String category) {
        return mapper.toCollectionDto(service.getByCategory(category));
    }


}
