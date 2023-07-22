package com.fmi.pfmapp.saving.repository;

import com.fmi.pfmapp.saving.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
    Optional<Saving> findByIban(String iban);

    Set<Saving> findByCreatedOnAfter(LocalDate date);
    Set<Saving> findByCreatedOnBefore(LocalDate date);
}
