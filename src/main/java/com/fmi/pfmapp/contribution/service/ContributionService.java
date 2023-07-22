package com.fmi.pfmapp.contribution.service;

import com.fmi.pfmapp.contribution.Contribution;
import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.goal.Goal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public interface ContributionService {
    void addContribution(Goal goal, BigDecimal amount);

    Contribution getContributionById(long id) throws ResourceNotFoundException;

    Set<Contribution> getAllContributions();

    Set<Contribution> getAllContributionsFromGoal(Goal goal);

    Set<Contribution> getContributionsTowardsGoalAfter(Goal goal, LocalDate date);

    Set<Contribution> getContributionsTowardsGoalBefore(Goal goal, LocalDate date);

    BigDecimal sumGoalContributions(Goal goal);
}
