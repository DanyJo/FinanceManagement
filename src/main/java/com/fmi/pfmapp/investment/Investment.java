package com.fmi.pfmapp.investment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "investments")
public class Investment {
    private static final int FLOATING_POINT_PRECISION = 20;
    private static final int FLOATING_POINT_SCALE = 6;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "invested_amount", nullable = false)
    private BigDecimal investedAmount;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = FLOATING_POINT_PRECISION, scale = FLOATING_POINT_SCALE)
    private BigDecimal growth;

    @Column(name = "made_on", nullable = false)
    private LocalDate madeOn = LocalDate.now();
}
