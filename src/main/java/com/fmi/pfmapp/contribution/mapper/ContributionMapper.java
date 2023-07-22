package com.fmi.pfmapp.contribution.mapper;

import com.fmi.pfmapp.contribution.Contribution;
import com.fmi.pfmapp.contribution.dto.ContributionDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ContributionMapper {
    Contribution toEntity(ContributionDto contributionDto);

    ContributionDto toDto(Contribution contribution);

    List<ContributionDto> toCollectionDto(Collection<Contribution> contributions);
}
