package com.parcial_prog3.controllers;

import com.parcial_prog3.dto.DnaRequest;
import com.parcial_prog3.dto.DnaResponse;
import com.parcial_prog3.services.DnaValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    @Autowired
    private DnaValidationService dnaService;

    @PostMapping("/")
    public ResponseEntity<DnaResponse> checkMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = dnaService.evaluateDna(dnaRequest.getDna());
        if (isMutant) {
            return ResponseEntity.ok(new DnaResponse(true, "Mutant detected"));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new DnaResponse(false, "Not a mutant"));
        }
    }
}
