package com.fmi.pfmapp.contribution.controller;

import com.fmi.pfmapp.contribution.dto.ContributionDto;

import java.util.List;

public interface ContributionController {
    void contributeToGoal(long goalId, String amount);

    List<ContributionDto> getAllContributions();

    ContributionDto getContributionById(long contributionId);

    List<ContributionDto> getContributionsTowardsGoal(long goalID);

    List<ContributionDto> getContributionsTowardsGoalAfter(long goalID, String date);

    List<ContributionDto> getContributionsTowardsGoalBefore(long goalID, String date);
}
