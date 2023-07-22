package com.fmi.pfmapp.investment.controller;

import com.fmi.pfmapp.investment.dto.InvestmentDto;
import com.fmi.pfmapp.investment.mapper.InvestmentMapper;
import com.fmi.pfmapp.investment.service.InvestmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("investments")
public class InvestmentControllerImpl implements InvestmentController {
    private InvestmentService service;
    private InvestmentMapper mapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvestmentDto createInvestment(@RequestBody InvestmentDto investmentDto) {
        service.createInvestment(mapper.toEntity(investmentDto));

        return investmentDto;
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvestmentDto deleteInvestment(@PathVariable("id") long id) {
        return mapper.toDto(service.removeInvestment(id));
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvestmentDto getInvestmentById(@PathVariable("id") long id) {
        return mapper.toDto(service.getInvestmentById(id));
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvestmentDto> getAllInvestments() {
        return mapper.toCollectionDto(service.getAllInvestments());
    }

    @Override
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvestmentDto> getInvestmentsByCategory(@PathVariable("name") String name) {
        return mapper.toCollectionDto(service.getByName(name));
    }

    @Override
    @GetMapping("/profit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getInvestmentProfit(@PathVariable("id") long id) {
        return service.getInvestmentProfit(id);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvestmentDto updateInvestment(@PathVariable("id") long id, @RequestBody InvestmentDto investmentDto) {
        service.updateInvestment(id, mapper.toEntity(investmentDto));

        return investmentDto;
    }
}
