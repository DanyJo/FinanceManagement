package com.fmi.pfmapp.goal.controller;

import com.fmi.pfmapp.contribution.service.ContributionService;
import com.fmi.pfmapp.goal.Goal;
import com.fmi.pfmapp.goal.dto.GoalDto;
import com.fmi.pfmapp.goal.mapper.GoalMapper;
import com.fmi.pfmapp.goal.service.GoalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("goals")
public class GoalControllerImpl implements GoalController {
    private GoalService goalService;
    private ContributionService contributionService;

    private GoalMapper mapper;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoalDto createSaving(@RequestBody GoalDto goalDto) {
        goalService.createGoal(mapper.toEntity(goalDto));

        return goalDto;
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GoalDto deleteGoal(@PathVariable("id") long id) {
        return mapper.toDto(goalService.deleteGoal(id));
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GoalDto> getAllGoals() {
        return mapper.toCollectionDto(goalService.getAllGoals());
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GoalDto getGoalById(@PathVariable("id") long id) {
        return mapper.toDto(goalService.getGoalById(id));
    }

    @Override
    @GetMapping("/progress/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    public String getGoalProgress(@PathVariable("goalId") long goalId) {
        Goal goal = goalService.getGoalById(goalId);
        BigDecimal contributions = contributionService.sumGoalContributions(goal);

        return "Goal progress: " + contributions + "/" + goal.getAmount();
    }
}

