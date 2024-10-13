package com.parcial_prog3.repositories;


import com.parcial_prog3.models.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {
    boolean existsByDna(String[] dna);
    long countByIsMutant(boolean isMutant);
}
