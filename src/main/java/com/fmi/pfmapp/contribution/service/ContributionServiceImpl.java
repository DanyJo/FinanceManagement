package com.fmi.pfmapp.contribution.service;

import com.fmi.pfmapp.contribution.Contribution;
import com.fmi.pfmapp.contribution.repository.ContributionRepository;
import com.fmi.pfmapp.exception.ConditionAlreadySatisfied;
import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.goal.Goal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ContributionServiceImpl implements ContributionService {
    private ContributionRepository repository;

    @Override
    public void addContribution(Goal goal, BigDecimal amount) {
        checkIsNull(amount);
        checkIsPositive(amount);

        checkGoalMet(sumGoalContributions(goal), goal.getAmount());

        repository.save(createContribution(amount, LocalDate.now(), goal));
    }

    private Contribution createContribution(BigDecimal amount, LocalDate date, Goal goal) {
        return Contribution.builder()
                .amount(amount)
                .madeOn(date)
                .goal(goal)
                .build();
    }

    @Override
    public Contribution getContributionById(long id) throws ResourceNotFoundException {
        checkIsPositive(id);
        Optional<Contribution> contribution = repository.findById(id);

        if (contribution.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find a contribution with id " + id + ".");
        }

        return contribution.get();
    }

    @Override
    public Set<Contribution> getAllContributions() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public Set<Contribution> getAllContributionsFromGoal(Goal goal) {
        checkIsNull(goal);

        return new HashSet<>(repository.findByGoal(goal));
    }

    @Override
    public Set<Contribution> getContributionsTowardsGoalAfter(Goal goal, LocalDate date) {
        checkIsNull(date);
        checkIsNull(goal);

        return repository.findByGoalAndMadeOnAfter(goal, date);
    }

    @Override
    public Set<Contribution> getContributionsTowardsGoalBefore(Goal goal, LocalDate date) {
        checkIsNull(date);
        checkIsNull(goal);

        return repository.findByGoalAndMadeOnBefore(goal, date);
    }

    @Override
    public BigDecimal sumGoalContributions(Goal goal) {
        checkIsNull(goal);

        return repository.sumContributionsTowardsGoal(goal);
    }

    private void checkIsNull(Goal goal) {
        if (goal == null) {
            throw new IllegalArgumentException("Trying to pass a null value goal object.");
        }
    }

    private void checkIsNull(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            throw new IllegalArgumentException("Trying to pass a null value BigDecimal object.");
        }
    }

    private void checkIsNull(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Trying to pass a null value localDate object.");
        }
    }

    private void checkIsPositive(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Trying to access a record with non-positive id.");
        }
    }

    private void checkIsPositive(BigDecimal number) {
        if (number.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Trying to add negative amount to goal.");
        }
    }

    private void checkGoalMet(BigDecimal current, BigDecimal goal) throws ConditionAlreadySatisfied {
        if (current.compareTo(goal) >= 0) {
            throw new ConditionAlreadySatisfied("The desired amount have already been met. " +
                    "No need for more contributions. :)");
        }
    }
}
