package com.krakedev.peaje.testjunit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.TagElectronico;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.servicios.EstacionPeaje;

public class TestEstacionPeajeIA {

	private static final double TOLERANCIA = 0.0001;
	
	@Test
	public void testRegistrarVehiculoLiviano() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		
		Conductor conductor = new Conductor(
				"1711111111",
				"Mario",
				"Perez"
		);
		
		Vehiculo vehiculo;
		vehiculo = estacion.registrarVehiculo(
				"PBC1234",
				"L",
				conductor,
				"TAG-001"
		);
		
		assertNotNull(vehiculo);
		assertEquals("PBC1234", vehiculo.getPlaca());
		assertEquals("L", vehiculo.getTipo());
		
		assertEquals(
				"1711111111",
				vehiculo.getPropietario().getCedula()
		);
		
		assertEquals(
				"TAG-001",
				vehiculo.getTag().getIdTag()
		);
		
		assertEquals(
				0,
				vehiculo.getTag().getSaldo(),
				TOLERANCIA
		);
		
		assertTrue(vehiculo.getTag().isActivo());
		
	}
	
	@Test
	public void testRegistrarVehiculoTipoInvalido() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		
		Conductor conductor = new Conductor(
				"1722222222",
				"Ana",
				"Lopez"
		);
		
		Vehiculo vehiculo;
		vehiculo = estacion.registrarVehiculo(
				"PCC5678",
				"X",
				conductor,
				"TAG-002"
		);
		
		assertNull(vehiculo);
		
	}
	
	@Test
	public void testRecargarTagMontoValido() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		TagElectronico tag = new TagElectronico("TAG-003");
		
		boolean resultado;
		resultado = estacion.recargarTag(10, tag);
		
		assertTrue(resultado);
		assertEquals(10, tag.getSaldo(), TOLERANCIA);
		
	}
	
	@Test
	public void testRecargarTagMontoCero() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		TagElectronico tag = new TagElectronico("TAG-004");
		
		boolean resultado;
		resultado = estacion.recargarTag(0, tag);
		
		assertFalse(resultado);
		assertEquals(0, tag.getSaldo(), TOLERANCIA);
		
	}
	
	@Test
	public void testRecargarTagMontoNegativo() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		TagElectronico tag = new TagElectronico("TAG-005");
		
		boolean resultado;
		resultado = estacion.recargarTag(-20, tag);
		
		assertFalse(resultado);
		assertEquals(0, tag.getSaldo(), TOLERANCIA);
		
	}
	
	@Test
	public void testCobrarPeajeVehiculoLiviano() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		
		Conductor conductor = new Conductor(
				"1733333333",
				"Luis",
				"Castro"
		);
		
		Vehiculo vehiculo;
		vehiculo = estacion.registrarVehiculo(
				"PAA1111",
				"L",
				conductor,
				"TAG-006"
		);
		
		estacion.recargarTag(5, vehiculo.getTag());
		
		boolean resultado;
		resultado = estacion.cobrarPeaje(vehiculo);
		
		assertTrue(resultado);
		
		assertEquals(
				4,
				vehiculo.getTag().getSaldo(),
				TOLERANCIA
		);
		
	}
	
	@Test
	public void testCobrarPeajeVehiculoPesado() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		
		Conductor conductor = new Conductor(
				"1744444444",
				"Pedro",
				"Mora"
		);
		
		Vehiculo vehiculo;
		vehiculo = estacion.registrarVehiculo(
				"PBB2222",
				"P",
				conductor,
				"TAG-007"
		);
		
		estacion.recargarTag(10, vehiculo.getTag());
		
		boolean resultado;
		resultado = estacion.cobrarPeaje(vehiculo);
		
		assertTrue(resultado);
		
		assertEquals(
				7.5,
				vehiculo.getTag().getSaldo(),
				TOLERANCIA
		);
		
	}
	
	@Test
	public void testCobrarPeajeSaldoInsuficiente() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		
		Conductor conductor = new Conductor(
				"1755555555",
				"Maria",
				"Reyes"
		);
		
		Vehiculo vehiculo;
		vehiculo = estacion.registrarVehiculo(
				"PCC3333",
				"L",
				conductor,
				"TAG-008"
		);
		
		estacion.recargarTag(0.50, vehiculo.getTag());
		
		boolean resultado;
		resultado = estacion.cobrarPeaje(vehiculo);
		
		assertFalse(resultado);
		
		assertEquals(
				0.50,
				vehiculo.getTag().getSaldo(),
				TOLERANCIA
		);
		
	}
	
	@Test
	public void testTransferirSaldoCorrectamente() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		
		TagElectronico origen = new TagElectronico("TAG-009");
		TagElectronico destino = new TagElectronico("TAG-010");
		
		estacion.recargarTag(10, origen);
		estacion.recargarTag(2, destino);
		
		boolean resultado;
		resultado = estacion.transferirSaldoTag(
				4,
				origen,
				destino
		);
		
		assertTrue(resultado);
		
		assertEquals(
				6,
				origen.getSaldo(),
				TOLERANCIA
		);
		
		assertEquals(
				6,
				destino.getSaldo(),
				TOLERANCIA
		);
		
	}
	
	@Test
	public void testTransferirTodoElSaldo() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		
		TagElectronico origen = new TagElectronico("TAG-011");
		TagElectronico destino = new TagElectronico("TAG-012");
		
		estacion.recargarTag(5, origen);
		estacion.recargarTag(1, destino);
		
		boolean resultado;
		resultado = estacion.transferirSaldoTag(
				5,
				origen,
				destino
		);
		
		assertTrue(resultado);
		
		assertEquals(
				0,
				origen.getSaldo(),
				TOLERANCIA
		);
		
		assertEquals(
				6,
				destino.getSaldo(),
				TOLERANCIA
		);
		
	}
	
	@Test
	public void testTransferenciaConSaldoInsuficiente() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		
		TagElectronico origen = new TagElectronico("TAG-013");
		TagElectronico destino = new TagElectronico("TAG-014");
		
		estacion.recargarTag(3, origen);
		estacion.recargarTag(2, destino);
		
		boolean resultado;
		resultado = estacion.transferirSaldoTag(
				5,
				origen,
				destino
		);
		
		assertFalse(resultado);
		
		assertEquals(
				3,
				origen.getSaldo(),
				TOLERANCIA
		);
		
		assertEquals(
				2,
				destino.getSaldo(),
				TOLERANCIA
		);
		
	}
	
	@Test
	public void testTransferenciaConMontoNegativo() {
		
		EstacionPeaje estacion = new EstacionPeaje();
		
		TagElectronico origen = new TagElectronico("TAG-015");
		TagElectronico destino = new TagElectronico("TAG-016");
		
		estacion.recargarTag(10, origen);
		
		boolean resultado;
		resultado = estacion.transferirSaldoTag(
				-5,
				origen,
				destino
		);
		
		assertFalse(resultado);
		
		assertEquals(
				10,
				origen.getSaldo(),
				TOLERANCIA
		);
		
		assertEquals(
				0,
				destino.getSaldo(),
				TOLERANCIA
		);
		
	}
	
}