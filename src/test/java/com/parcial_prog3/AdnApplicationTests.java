package com.parcial_prog3;

import com.parcial_prog3.services.DnaValidationService;
import com.parcial_prog3.services.DnaAnalysis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc



class AdnApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private DnaValidationService dnaValidationService;
	@Autowired
	private DnaAnalysis dnaAnalysis;

	@Test
	void testMutant() throws Exception {
		String[] dna = {"AAAA", "CCCC", "TCAG", "GGTC"};
		mockMvc.perform(post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"dna\":[\"AAAA\",\"CCCC\",\"TCAG\",\"GGTC\"]}"))
				.andExpect(status().isOk());
	}

	@Test
	void testNotMutant() throws Exception {
		String[] dna = {"TGAC", "ATCC", "TAAG", "GGTC"};
		mockMvc.perform(post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"dna\":[\"TGAC\",\"ATCC\",\"TAAG\",\"GGTC\"]}"))
				.andExpect(status().isForbidden());
	}


	@Test
	void testMutante1() {
		String[] mutante1 = {"AAAA", "CCCC", "TCAG", "GGTC"};
		assertTrue(DnaAnalysis.isMutantDna(mutante1));
	}

	@Test
	void testMutante2() {
		String[] mutante2 = {"TGAC", "AGCC", "TGAC", "GGTC"};
		assertTrue(DnaAnalysis.isMutantDna(mutante2));
	}

	@Test
	void testMutante3() {
		String[] mutante3 = {"AAAA", "AAAA", "AAAA", "AAAA"};
		assertTrue(DnaAnalysis.isMutantDna(mutante3));
	}

	@Test
	void testNoMutante1() {
		String[] noMutante1 = {"TGAC", "ATCC", "TAAG", "GGTC"};
		assertFalse(DnaAnalysis.isMutantDna(noMutante1));
	}

	@Test
	void testNoMutante2() {
		String[] noMutante2 = {"AAAT", "AACC", "AAAC", "CGGG"};
		assertFalse(DnaAnalysis.isMutantDna(noMutante2));
	}

	@Test
	void testMutante4() {
		String[] mutante4 = {
				"TCGGGTGAT", "TGATCCTTT", "TACGAGTGA",
				"AAATGTACG", "ACGAGTGCT", "AGACACATG",
				"GAATTCCAA", "ACTACGACC", "TGAGTATCC"
		};
		assertTrue(DnaAnalysis.isMutantDna(mutante4));
	}

	@Test
	void testMutante5() {
		String[] mutante5 = {
				"TTTTTTTTT", "TTTTTTTTT", "TTTTTTTTT",
				"TTTTTTTTT", "CCGACCAGT", "GGCACTCCA",
				"AGGACACTA", "CAAAGGCAT", "GCAGTCCCC"
		};
		assertTrue(DnaAnalysis.isMutantDna(mutante5));
	}

}
