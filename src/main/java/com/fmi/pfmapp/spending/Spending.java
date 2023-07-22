package com.fmi.pfmapp.spending;

import com.fmi.pfmapp.budget.Budget;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "spendings")
public class Spending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private BigDecimal amount;

    @Column(name = "made_on")
    private LocalDate madeOn = LocalDate.now();

    @Column(columnDefinition = "varchar(255) default ''")
    private String spentOn = "";

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;
}
