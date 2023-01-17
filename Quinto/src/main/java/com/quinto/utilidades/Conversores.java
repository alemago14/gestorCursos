package com.quinto.utilidades;

import com.quinto.excepciones.WebException;

public class Conversores {

	//convertir un string a num entero
	public int aEntero(String texto) throws WebException {
		try {
			int numero = Integer.parseInt(texto);
			return numero;
		} catch (Exception e) {
			throw new WebException(e.toString());
		}
	}
}
