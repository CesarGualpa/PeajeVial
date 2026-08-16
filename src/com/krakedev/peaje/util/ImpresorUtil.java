package com.krakedev.peaje.util;

import com.krakedev.peaje.entidades.Vehiculo;

public class ImpresorUtil {

	public static void imprimirVehiculo(Vehiculo vehiculo) {
		
		System.out.println("=================================");
		System.out.println("INFORMACION COMPLETA DEL VEHICULO");
		System.out.println("=================================");
		
		System.out.println("--- VEHICULO ---");
		vehiculo.imprimir();
		
		System.out.println();
		
		System.out.println("--- PROPIETARIO ---");
		vehiculo.getPropietario().imprimir();
		
		System.out.println();
		
		System.out.println("--- TAG ELECTRONICO ---");
		vehiculo.getTag().imprimir();
		
		System.out.println("=================================");
		
	}
	
}