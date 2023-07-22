package com.fmi.pfmapp.goal.service;

import com.fmi.pfmapp.exception.ResourceNotFoundException;
import com.fmi.pfmapp.goal.Goal;

import java.math.BigDecimal;
import java.util.Set;

public interface GoalService {
    void createGoal(Goal goal);

    Goal deleteGoal(long id) throws ResourceNotFoundException;

    Goal getGoalById(long id) throws ResourceNotFoundException;

    Set<Goal> getAllGoals();
}
