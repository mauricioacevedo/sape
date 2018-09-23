/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en May 30, 2006 - 4:28:57 PM
 */

package com.osp.sape.indicadores;

public class ViewIndicadoresTecnologia {

	// Nombre de la tecnologia
	private String nombre;
	
	private String idTipoCabeza;
	private String exitos;
	private String fallas;
	private String advertencias;
	private String inesperados;
	
	public ViewIndicadoresTecnologia(String id, String name,String exito,String fallo,String adv,String ines) {
		
		nombre=name;
		idTipoCabeza=id;
		exitos=exito;
		fallas=fallo;
		advertencias=adv;
		inesperados=ines;
	}

	public String getAdvertencias() {
		return advertencias;
	}

	public void setAdvertencias(String advertencias) {
		this.advertencias = advertencias;
	}

	public String getExitos() {
		return exitos;
	}

	public void setExitos(String exitos) {
		this.exitos = exitos;
	}

	public String getFallas() {
		return fallas;
	}

	public void setFallas(String fallas) {
		this.fallas = fallas;
	}

	public String getIdTipoCabeza() {
		return idTipoCabeza;
	}

	public void setIdTipoCabeza(String idTipoCabeza) {
		this.idTipoCabeza = idTipoCabeza;
	}

	public String getInesperados() {
		return inesperados;
	}

	public void setInesperados(String inesperados) {
		this.inesperados = inesperados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
