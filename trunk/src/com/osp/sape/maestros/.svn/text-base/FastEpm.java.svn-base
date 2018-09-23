/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Aug 11, 2006 - 10:59:37 AM
 */

package com.osp.sape.maestros;

/**
 * @hibernate.class table="fast_eeppm"
 * @author root
 *
 */
public class FastEpm {
	
	//central 	character varying 	
	
	//cpr 	smallint 	
		
	//sltu 	character varying 	
		
	//idtiponodo 	smallint 	
		
	private int id2;//indigo_secundario 	smallint 	
		
	private String ll; 	
		
	private String vertical;
	
	
	private int id;
	//id_fast 	bigint nextval('id_fast_epm'::text)
	/**
	 * Numero consecutivo que se asigno a cada fast. Este es unico e irrepetible
	 * con la ayuda de este identificaremos el respectivo archivo de configuracion
	 * para el actual fast.
	 */
	private Integer numeroFast;// numero_fast_epm 	integer 	
	
	private String telefonoFast; //telefono_fast 	integer
	
	/**
	 * 09-sep-2006
	 * johnda...............
	 * Identifica al central en la que se encuentra el fast  
	 * */
	private String central;

	/**
     * @hibernate.property column = "idtiponodo" type = "integer"
     */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    /**
     * @hibernate.property column = "numerofast" type = "integer"
     */
	public Integer getNumeroFast() {
		return numeroFast;
	}

	public void setNumeroFast(Integer numeroFast) {
		this.numeroFast = numeroFast;
	}
    /** 
     * @hibernate.id type = "java.lang.String" column = "numero_telefono" unsaved-value = "-1" generator-class = "assigned"
     */
	public String getTelefonoFast() {
		return telefonoFast;
	}

	public void setTelefonoFast(String telefonoFast) {
		this.telefonoFast = telefonoFast;
	}
	
	/**
     * @hibernate.property column = "indigo_secundario" type = "integer"
     */
	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}
	/**
     * @hibernate.property column = "ll" type = "string"
     */
	public String getLl() {
		return ll;
	}

	public void setLl(String ll) {
		this.ll = ll;
	}
	/**
     * @hibernate.property column = "vertical" type = "string"
     */
	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	
	/**
	 * @hibernate.property column = "central" type = "string"
	 * */
	public String getCentral() {
		return central;
	}

	public void setCentral(String central) {
		this.central = central;
	}
	
}
