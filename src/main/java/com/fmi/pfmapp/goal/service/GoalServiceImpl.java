package com.fmi.pfmapp.goal.service;

import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.goal.Goal;
import com.fmi.pfmapp.goal.repository.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class GoalServiceImpl implements GoalService {
    private GoalRepository repository;

    @Override
    public void createGoal(Goal goal) {
        checkIsNull(goal);
        checkIsNullEmptyOrBlank(goal.getName());

        repository.save(goal);
    }

    @Override
    public Goal deleteGoal(long id) throws ResourceNotFoundException {
        Goal goal = getGoalById(id);

        repository.delete(goal);

        return goal;
    }

    @Override
    public Goal getGoalById(long id) throws ResourceNotFoundException {
        checkIsPositive(id);
        Optional<Goal> goal = repository.findById(id);

        if (goal.isEmpty()) {
            throw new ResourceNotFoundException("Couldn't find a goal with id " + id + ".");
        }

        return goal.get();
    }

    @Override
    public Set<Goal> getAllGoals() {
        return new HashSet<>(repository.findAll());
    }

    private void checkIsNull(Goal goal) {
        if (goal == null) {
            throw new IllegalArgumentException("Trying to pass a null value Goal object.");
        }
    }

    private void checkIsPositive(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Trying to access a record with non-positive id.");
        }
    }

    private void checkIsNullEmptyOrBlank(String name) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Goal name cannot be null, empty or blank.");
        }
    }
}
