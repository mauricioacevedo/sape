package com.osp.sape.utils;

/**
 * Esta clase representa un Channel de cyclades, el cual contiene un identificador,
 * un estado y un numero de telefono, el cual puede ser vacio en caso tal de que el
 * Channel no este conectado.
 * */
public class ChannelCyclades {
	
	/**
	 * Identificador del Channel. Es de tipo Integer para facilitar su
	 * manipulacion, ya que esta clase esta diseñada para ser almacenada en
	 * un HashMap
	 * */
	private Integer id;
	
	/**
	 * Estado del Channel
	 * */
	private String estado;
	
	/**
	 * Numero telefonico asociado a la conexion del Channel. Si el Channel no esta
	 * conectado este campo es vacio
	 * */
	private String telefono;

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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
