package com.fmi.pfmapp.expense.mapper;

import com.fmi.pfmapp.expense.Expense;
import com.fmi.pfmapp.expense.dto.ExpenseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ExpenseMapper {
    ExpenseDto toDto(Expense entity);

    @Mapping(source = "recurringPeriod", target = "recurringPeriod", defaultValue = "NONE")
    Expense toEntity(ExpenseDto dto);

    List<ExpenseDto> toCollectionDto(Collection<Expense> expenses);
}
