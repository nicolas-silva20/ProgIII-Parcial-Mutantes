package com.parcial_prog3.services;


import com.parcial_prog3.models.DnaRecord;
import com.parcial_prog3.repositories.DnaRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DnaValidationService {

    @Autowired
    private DnaRecordRepository dnaRepo;
    @Autowired
    private DnaAnalysis dnaAnalysis;
    public boolean evaluateDna(String[] dnaSample) {
        // Se usa el detector para verificar si es mutante
        boolean mutantStatus = DnaAnalysis.isMutantDna(dnaSample);

        // Si no se ha registrado el ADN antes, se almacena en la base de datos
        if (!dnaRepo.existsByDna(dnaSample)) {
            DnaRecord dnaEntry = new DnaRecord();
            dnaEntry.setDna(dnaSample);
            dnaEntry.setMutant(mutantStatus);
            dnaRepo.save(dnaEntry);
        }

        return mutantStatus;
    }
}
