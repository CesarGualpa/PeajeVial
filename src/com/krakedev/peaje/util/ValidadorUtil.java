package com.krakedev.peaje.util;

public class ValidadorUtil {

	public static boolean esMontoValido(double monto) {
		
		if(monto > 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static boolean esTipoValido(String tipo) {
		
		if(tipo != null && (tipo.equals("L") || tipo.equals("P"))) {
			return true;
		}else {
			return false;
		}
		
	}
	
}