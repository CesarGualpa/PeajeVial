package com.krakedev.peaje.testjunit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.peaje.util.ValidadorUtil;

public class TestValidadorUtilJUnit {

	@Test
	public void testMontoPositivo() {
		
		boolean resultado;
		resultado = ValidadorUtil.esMontoValido(10);
		
		assertTrue(resultado);
		
	}
	
	@Test
	public void testMontoCero() {
		
		boolean resultado;
		resultado = ValidadorUtil.esMontoValido(0);
		
		assertFalse(resultado);
		
	}
	
	@Test
	public void testMontoNegativo() {
		
		boolean resultado;
		resultado = ValidadorUtil.esMontoValido(-10);
		
		assertFalse(resultado);
		
	}
	
	@Test
	public void testTipoLiviano() {
		
		assertTrue(ValidadorUtil.esTipoValido("L"));
		
	}
	
	@Test
	public void testTipoPesado() {
		
		assertTrue(ValidadorUtil.esTipoValido("P"));
		
	}
	
	@Test
	public void testTipoInvalido() {
		
		assertFalse(ValidadorUtil.esTipoValido("X"));
		
	}
	
}