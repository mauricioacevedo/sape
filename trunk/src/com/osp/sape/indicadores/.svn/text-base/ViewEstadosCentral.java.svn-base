package com.osp.sape.indicadores;

/**
 * Bean que almacena los datos de las estadisticas por central.
 * @author Andres Aristizabal
 */
public class ViewEstadosCentral {

	private String central;
	private int exito;
	private int fallo;
	private int advertencia;
	private int inesperado;
	private int total;
	
	public ViewEstadosCentral() {
		
	}
	
	public ViewEstadosCentral(String central, int exito, int advertencia, int fallo, int inesperado) {
		this.advertencia = advertencia;
		this.central = central;
		this.exito = exito;
		this.fallo = fallo;
		this.inesperado = inesperado;
		
		calcularTotal();
	}



	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		calcularTotal();
	}

	public String getCentral() {
		return central;
	}
	
	public void setCentral(String central) {
		this.central = central;
	}
	
	
	public int getAdvertencia() {
		return advertencia;
	}


	public void setAdvertencia(int advertencia) {
		this.advertencia = advertencia;
		calcularTotal();
	}


	public int getExito() {
		return exito;
	}


	public void setExito(int exito) {
		this.exito = exito;
		calcularTotal();
	}



	public int getFallo() {
		return fallo;
	}



	public void setFallo(int fallo) {
		this.fallo = fallo;
		calcularTotal();
	}



	public int getInesperado() {
		return inesperado;
	}



	public void setInesperado(int inesperado) {
		this.inesperado = inesperado;
		calcularTotal();
	}



	protected String paramString() {
		return "central=" + central + ";exito=" + exito + ";fallo=" + fallo + 
			";advertencia=" + advertencia + ";inesperado=" + inesperado;
	}
	
	public String toString() {
		return getClass().getName() + ": [" + paramString() + "]";
	}
	
	private void calcularTotal() {
		total = exito + advertencia + fallo + inesperado;
	}
	
}
