package com.fmi.pfmapp.goal;

import com.fmi.pfmapp.contribution.Contribution;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "numeric(38,2) default 0.00")
    private BigDecimal amount = BigDecimal.valueOf(0);

    @Column(columnDefinition = "varchar(255) default ''")
    private String description = "";

    @Column(name = "start_date")
    private LocalDate startDate = LocalDate.now();

    //Add account fk

    //

    @OneToMany(mappedBy = "goal", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Contribution> contributions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal goal = (Goal) o;
        return Objects.equals(id, goal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
