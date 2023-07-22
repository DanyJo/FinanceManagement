package com.fmi.pfmapp.spending.mapper;

import com.fmi.pfmapp.spending.Spending;
import com.fmi.pfmapp.spending.dto.SpendingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = {LocalDate.class})
public interface SpendingMapper {
    SpendingDto toDto(Spending spending);

    @Mapping(source = "madeOn", target = "madeOn", defaultExpression = "java(LocalDate.now())")
    Spending toEntity(SpendingDto spendingDto);

    List<SpendingDto> toCollectionDto(Collection<Spending> spendings);
}
