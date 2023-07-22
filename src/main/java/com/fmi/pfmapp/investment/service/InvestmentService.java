package com.fmi.pfmapp.investment.service;

import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.investment.Investment;

import java.math.BigDecimal;
import java.util.Set;

public interface InvestmentService {
    void createInvestment(Investment investment);

    Investment removeInvestment(long id) throws ResourceNotFoundException;

    Investment getInvestmentById(long id) throws ResourceNotFoundException;

    Set<Investment> getAllInvestments();

    Set<Investment> getByName(String name);

    BigDecimal getInvestmentProfit(long id);

    Investment updateInvestment(long id, Investment newInvestment);
}
