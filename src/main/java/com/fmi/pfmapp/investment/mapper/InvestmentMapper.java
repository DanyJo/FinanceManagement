package com.fmi.pfmapp.investment.mapper;

import com.fmi.pfmapp.investment.Investment;
import com.fmi.pfmapp.investment.dto.InvestmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = {LocalDate.class})
public interface InvestmentMapper {
    InvestmentDto toDto(Investment investment);

    @Mapping(source = "madeOn", target = "madeOn", defaultExpression = "java(LocalDate.now())")
    Investment toEntity(InvestmentDto investmentDto);

    List<InvestmentDto> toCollectionDto(Collection<Investment> investments);
}
