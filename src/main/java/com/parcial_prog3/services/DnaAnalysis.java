package com.parcial_prog3.services;

import org.springframework.stereotype.Service;

@Service
public class DnaAnalysis {

    private static final int SEQUENCE_LENGTH = 4;

    public static boolean isMutantDna(String[] dnaGrid) {
        if (dnaGrid == null) {
            throw new IllegalArgumentException("El ADN no debe ser nulo");
        }
        int dimension = dnaGrid.length;
        if (dimension == 0) {
            throw new IllegalArgumentException("El ADN no puede estar vacío");
        }
        for (String row : dnaGrid) {
            if (row == null) {
                throw new IllegalArgumentException("Una fila de ADN no puede ser nula");
            }
            if (row.length() != dimension) {
                throw new IllegalArgumentException("El ADN debe ser una matriz NxN");
            }
            if (!row.matches("[ATCG]+")) {
                throw new IllegalArgumentException("El ADN contiene caracteres no válidos");
            }
        }

        int matchCount = 0;

        // Verificación de secuencias en filas, columnas y diagonales
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (j <= dimension - SEQUENCE_LENGTH && verifySequence(dnaGrid, i, j, 0, 1)) matchCount++;
                if (i <= dimension - SEQUENCE_LENGTH && verifySequence(dnaGrid, i, j, 1, 0)) matchCount++;
                if (i <= dimension - SEQUENCE_LENGTH && j <= dimension - SEQUENCE_LENGTH && verifySequence(dnaGrid, i, j, 1, 1)) matchCount++;
                if (i <= dimension - SEQUENCE_LENGTH && j >= SEQUENCE_LENGTH - 1 && verifySequence(dnaGrid, i, j, 1, -1)) matchCount++;
                if (matchCount > 1) return true;
            }
        }
        return false;
    }

    private static boolean verifySequence(String[] dnaGrid, int startX, int startY, int xIncrement, int yIncrement) {
        char startingBase = dnaGrid[startX].charAt(startY);
        for (int step = 1; step < SEQUENCE_LENGTH; step++) {
            if (dnaGrid[startX + step * xIncrement].charAt(startY + step * yIncrement) != startingBase) {
                return false;
            }
        }
        return true;
    }
}
