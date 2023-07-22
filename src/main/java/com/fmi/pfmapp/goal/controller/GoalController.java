package com.fmi.pfmapp.goal.controller;

import com.fmi.pfmapp.goal.dto.GoalDto;

import java.util.List;

public interface GoalController {
    GoalDto createSaving(GoalDto goalDto);

    GoalDto deleteGoal(long id);

    public List<GoalDto> getAllGoals();

    GoalDto getGoalById(long id);

    String getGoalProgress(long id);
}
