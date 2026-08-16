package com.krakedev.peaje.test;

import com.krakedev.peaje.entidades.Conductor;
import com.krakedev.peaje.entidades.TagElectronico;
import com.krakedev.peaje.entidades.Vehiculo;
import com.krakedev.peaje.servicios.EstacionPeaje;
import com.krakedev.peaje.util.ImpresorUtil;

public class TestVehiculo {

	public static void main(String[] args) {
		
		System.out.println("===== CREACION MANUAL DEL VEHICULO =====");
		
		Conductor conductor1;
		conductor1 = new Conductor(
				"1712345678",
				"Carlos",
				"Mendoza"
		);
		
		Vehiculo vehiculo1;
		vehiculo1 = new Vehiculo("PBC1234");
		
		vehiculo1.setTipo("L");
		
		TagElectronico tag1;
		tag1 = new TagElectronico("TAG-001");
		
		vehiculo1.setPropietario(conductor1);
		vehiculo1.setTag(tag1);
		
		System.out.println();
		System.out.println("===== IMPRIMIR VEHICULO =====");
		
		vehiculo1.imprimir();
		
		System.out.println();
		System.out.println("===== IMPRESOR UTIL =====");
		
		ImpresorUtil.imprimirVehiculo(vehiculo1);
		
		System.out.println();
		System.out.println("===== PRUEBAS DEL SERVICIO =====");
		
		EstacionPeaje estacion;
		estacion = new EstacionPeaje();
		
		boolean resultado;
		
		resultado = estacion.recargarTag(10, tag1);
		
		System.out.println("Recarga exitosa: " + resultado);
		System.out.println("Saldo despues de recarga: " + tag1.getSaldo());
		
		resultado = estacion.cobrarPeaje(vehiculo1);
		
		System.out.println("Cobro de peaje exitoso: " + resultado);
		System.out.println("Saldo despues del peaje: " + tag1.getSaldo());
		
		System.out.println();
		System.out.println("===== SEGUNDO VEHICULO =====");
		
		Conductor conductor2;
		conductor2 = new Conductor(
				"1722222222",
				"Ana",
				"Lopez"
		);
		
		Vehiculo vehiculo2;
		vehiculo2 = estacion.registrarVehiculo(
				"PCC5678",
				"P",
				conductor2,
				"TAG-002"
		);
		
		estacion.recargarTag(20, vehiculo2.getTag());
		
		ImpresorUtil.imprimirVehiculo(vehiculo2);
		
		System.out.println();
		System.out.println("===== TRANSFERENCIA DE SALDO =====");
		
		resultado = estacion.transferirSaldoTag(
				4,
				vehiculo1.getTag(),
				vehiculo2.getTag()
		);
		
		System.out.println("Transferencia exitosa: " + resultado);
		
		System.out.println(
				"Saldo TAG-001: "
				+ vehiculo1.getTag().getSaldo()
		);
		
		System.out.println(
				"Saldo TAG-002: "
				+ vehiculo2.getTag().getSaldo()
		);
		
	}

}