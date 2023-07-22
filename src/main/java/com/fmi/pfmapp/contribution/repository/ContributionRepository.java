package com.fmi.pfmapp.contribution.repository;

import com.fmi.pfmapp.contribution.Contribution;
import com.fmi.pfmapp.goal.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM Contribution c WHERE c.goal = :goal")
    BigDecimal sumContributionsTowardsGoal(@Param("goal") Goal goal);
    Set<Contribution> findByGoal(Goal goal);

    Set<Contribution> findByGoalAndMadeOnAfter(Goal goal, LocalDate date);

    Set<Contribution> findByGoalAndMadeOnBefore(Goal goal, LocalDate date);
}
