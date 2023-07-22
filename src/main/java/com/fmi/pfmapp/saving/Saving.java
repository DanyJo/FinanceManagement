package com.fmi.pfmapp.saving;

import com.fmi.pfmapp.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "savings")
public class Saving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(unique = true, nullable = false)
    private String iban;

    @Column(name = "created_on", nullable = false)
    private LocalDate createdOn = LocalDate.now();

    //Add account fk

    //

    @OneToMany(mappedBy = "saving", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Transaction> transactions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Saving saving = (Saving) o;
        return id == saving.id && Objects.equals(iban, saving.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban);
    }
}
