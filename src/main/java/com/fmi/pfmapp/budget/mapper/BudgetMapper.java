package com.fmi.pfmapp.budget.mapper;

import com.fmi.pfmapp.budget.Budget;
import com.fmi.pfmapp.budget.dto.BudgetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = {LocalDate.class})
public interface BudgetMapper {
    BudgetDto toDto(Budget budget);

    @Mapping(source = "startDate", target = "startDate", defaultExpression = "java(LocalDate.now())")
    @Mapping(source = "endDate", target = "endDate", defaultExpression = "java(LocalDate.now())")
    Budget toEntity(BudgetDto budgetDto);

    List<BudgetDto> toCollectionDto(Collection<Budget> budgets);
}
