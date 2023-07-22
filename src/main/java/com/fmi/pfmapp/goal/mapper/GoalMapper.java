package com.fmi.pfmapp.goal.mapper;

import com.fmi.pfmapp.goal.Goal;
import com.fmi.pfmapp.goal.dto.GoalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        imports = {LocalDate.class})
public interface GoalMapper {
    GoalDto toDto(Goal goal);

    @Mapping(source = "startDate", target = "startDate", defaultExpression = "java(LocalDate.now())")
    Goal toEntity(GoalDto goalDto);

    List<GoalDto> toCollectionDto(Collection<Goal> goals);
}
