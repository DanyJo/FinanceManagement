package com.fmi.pfmapp.contribution.controller;

import com.fmi.pfmapp.contribution.Contribution;
import com.fmi.pfmapp.contribution.dto.ContributionDto;
import com.fmi.pfmapp.contribution.mapper.ContributionMapper;
import com.fmi.pfmapp.contribution.service.ContributionService;
import com.fmi.pfmapp.goal.Goal;
import com.fmi.pfmapp.goal.service.GoalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("contributions")
public class ContributionControllerImpl implements ContributionController {
    private ContributionService contributionService;
    private GoalService goalService;
    private ContributionMapper mapper;

    @Override
    @PostMapping("/goal/{goalId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void contributeToGoal(@PathVariable("goalId") long goalId, @RequestBody String amount) {
        Goal goal = goalService.getGoalById(goalId);

        contributionService.addContribution(goal, new BigDecimal(amount));
    }

    @Override
    @GetMapping("/{contributionId}")
    @ResponseStatus(HttpStatus.OK)
    public ContributionDto getContributionById(@PathVariable("contributionId") long contributionId) {
        return mapper.toDto(contributionService.getContributionById(contributionId));
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContributionDto> getAllContributions() {
        return mapper.toCollectionDto(contributionService.getAllContributions());
    }

    @Override
    @GetMapping("/goal/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContributionDto> getContributionsTowardsGoal(@PathVariable("goalId") long goalId) {
        Goal goal = goalService.getGoalById(goalId);

        return mapper.toCollectionDto(contributionService.getAllContributionsFromGoal(goal));
    }

    @Override
    @GetMapping("/goal/{goalId}/after/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContributionDto> getContributionsTowardsGoalAfter(@PathVariable("goalId") long goalId,
                                                                  @PathVariable("date") String date) {
        LocalDate localDate = getDateFromString(date);
        Goal goal = goalService.getGoalById(goalId);

        Set<Contribution> contributions = contributionService.getContributionsTowardsGoalAfter(goal, localDate);

        return mapper.toCollectionDto(contributions);
    }

    @Override
    @GetMapping("/goal/{goalId}/before/{date}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContributionDto> getContributionsTowardsGoalBefore(@PathVariable("goalId") long goalId,
                                                                   @PathVariable("date") String date) {
        LocalDate localDate = getDateFromString(date);
        Goal goal = goalService.getGoalById(goalId);

        Set<Contribution> contributions = contributionService.getContributionsTowardsGoalBefore(goal, localDate);

        return mapper.toCollectionDto(contributions);
    }

    private LocalDate getDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(date, formatter);
    }
}
