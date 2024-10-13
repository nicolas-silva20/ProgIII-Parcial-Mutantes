package com.parcial_prog3.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DnaResponse {
    private boolean isMutant;
    private String message;
}
