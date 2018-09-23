package com.osp.sape.maestros;

/**
 * @hibernate.class table="cpr_siplexpro"
 * @author Andres Aristizabal
 */
public class CPRSiplexPro {

	private long telefono;
	private String central;
	private int cpr;
	private TipoNodo tipoNodo;
	
	
	/**
	 * @hibernate.property column = "central" type="string"
	 * @return
	 */	
	public String getCentral() {
		return central;
	}
	
	public void setCentral(String central) {
		this.central = central;
	}
	
	
	/**
	 * @hibernate.property column = "cpr" type="int"
	 * @return
	 */
	public int getCpr() {
		return cpr;
	}
	
	public void setCpr(int cpr) {
		this.cpr = cpr;
	}
	
	
	/**
	 * @hibernate.id type = "long" column = "telefono" unsaved-value = "-1" generator-class = "assigned"
	 */
	public long getTelefono() {
		return telefono;
	}
	
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	
	
	/**
	 * @hibernate.many-to-one column="idtiponodo" class="com.osp.sape.maestros.TipoNodo" update="true" insert="true"
	 * @return
	 */
	public TipoNodo getTipoNodo() {
		return tipoNodo;
	}
	
	public void setTipoNodo(TipoNodo tipoNodo) {
		this.tipoNodo = tipoNodo;
	}
	
	public String toString() {
		return getClass().getName() + ": [telefono=" + telefono + ", central=" + central + 
				", tipoNodo=" + tipoNodo + ", cpr=" + cpr + "]";
	}
}
