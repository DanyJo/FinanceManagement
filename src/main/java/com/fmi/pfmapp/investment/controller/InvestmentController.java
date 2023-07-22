package com.fmi.pfmapp.investment.controller;

import com.fmi.pfmapp.investment.dto.InvestmentDto;

import java.math.BigDecimal;
import java.util.List;

public interface InvestmentController {
    InvestmentDto createInvestment(InvestmentDto investmentDto);

    InvestmentDto deleteInvestment(long id);

    InvestmentDto getInvestmentById(long id);

    List<InvestmentDto> getAllInvestments();

    List<InvestmentDto> getInvestmentsByCategory(String name);

    BigDecimal getInvestmentProfit(long id);

    InvestmentDto updateInvestment(long id, InvestmentDto investmentDto);
}
