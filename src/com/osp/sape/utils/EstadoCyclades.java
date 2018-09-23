package com.osp.sape.utils;

import java.util.ArrayList;

public class EstadoCyclades {
	/**
	 * Contiene un listado de los estados de los canales del ras de ciclades?
	 * El valor de clave es un integer con el numero del canal, el dato es
	 * un objeto tipo Channel
	 * */
	private ArrayList canales;

	/**
	 * Contiene un listado de los estados de los modems del ras de ciclades?
	 * El valor de clave es el identificador del Modem y el dato es un objeto
	 * tipo Modem
	 * */
	private ArrayList modems;
	
	/**
	 * Esta variable almacena el estado de sincronizacion de la linea 1 de cyclades.
	 * Contiene un string sin manupular.
	 * */
	private String sincronizado;
	
	/**
	 * Esta variable contiene el estado de la alarma roja
	 * */
	private String alarmaRoja;
	
	/**
	 * Esta variable contiene el estado de la alarma azul
	 * */
	private String alarmaAzul;
	
	/**
	 * Esta variable contiene el estado de la alarma amarilla
	 * */
	private String alarmaAmarilla;

	public String getAlarmaAmarilla() {
		return alarmaAmarilla;
	}

	public void setAlarmaAmarilla(String alarmaAmarilla) {
		this.alarmaAmarilla = alarmaAmarilla;
	}

	public String getAlarmaAzul() {
		return alarmaAzul;
	}

	public void setAlarmaAzul(String alarmaAzul) {
		this.alarmaAzul = alarmaAzul;
	}

	public String getAlarmaRoja() {
		return alarmaRoja;
	}

	public void setAlarmaRoja(String alarmaRoja) {
		this.alarmaRoja = alarmaRoja;
	}

	public String getSincronizado() {
		return sincronizado;
	}

	public void setSincronizado(String sincronizado) {
		this.sincronizado = sincronizado;
	}

	public ArrayList getCanales() {
		return canales;
	}

	public void setCanales(ArrayList canales) {
		this.canales = canales;
	}

	public ArrayList getModems() {
		return modems;
	}

	public void setModems(ArrayList modems) {
		this.modems = modems;
	}
}
