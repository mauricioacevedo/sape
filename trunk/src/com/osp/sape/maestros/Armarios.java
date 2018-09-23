/*
 * Created on Jun 15, 2005
 */
package com.osp.sape.maestros;
/**
 * @hibernate.class table="armarios"
 * @author Andres Aristizabal
 * Clase para visualizar las distancias de la central al armario.
 */


public class Armarios { 
	
	private String armario;
	/**
	 * @hibernate.id  column = "armario" type = "java.lang.String" generator-class = "assigned"
	 */
	public String getArmario() {
		return armario;
	}

	public void setArmario(String armario) {
		this.armario = armario;
	}

	private String distancia;
	
	/**
	 * @hibernate.property  column = "distancia" type = "string"
	 */
	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}
	
	
	public String paramString() {
		return "armario=" + armario + "; " + distancia;
	}
	
	public String toString() {
		return getClass().getName() + "[" + paramString() + "]";
	}
}
