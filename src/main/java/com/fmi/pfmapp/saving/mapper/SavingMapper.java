package com.fmi.pfmapp.saving.mapper;

import com.fmi.pfmapp.saving.Saving;
import com.fmi.pfmapp.saving.dto.SavingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = {LocalDate.class})
public interface SavingMapper {
    SavingDto toDto(Saving saving);

    @Mapping(source = "createdOn", target = "createdOn", defaultExpression = "java(LocalDate.now())")
    Saving toEntity(SavingDto savingDto);

    Set<SavingDto> toCollectionDto(Collection<Saving> savings);
}
