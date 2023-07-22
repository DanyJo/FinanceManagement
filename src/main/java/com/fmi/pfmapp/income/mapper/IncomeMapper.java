package com.fmi.pfmapp.income.mapper;

import com.fmi.pfmapp.income.Income;
import com.fmi.pfmapp.income.dto.IncomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = {LocalDate.class})
public interface IncomeMapper {
    IncomeDto toDto(Income entity);

    @Mapping(source = "date", target = "date", defaultExpression = "java(LocalDate.now())")
    Income toEntity(IncomeDto dto);

    List<IncomeDto> toCollectionDto(Collection<Income> incomes);
}
