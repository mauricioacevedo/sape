package com.osp.sape.utils;

/**
 * Esta clase representa un Modem de cyclades, el cual contiene un identificador y
 * un estado
 * */
public class Modem {
	
	/**
	 * Identificador del Modem
	 * */
	private Integer id;
	
	/**
	 * Estado del Modem
	 * */
	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
