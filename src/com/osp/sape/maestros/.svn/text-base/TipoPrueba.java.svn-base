package com.osp.sape.maestros;
/**
 * 
 * 
 * @author Develop Team XpLoRa
 * Clase generada en May 5, 2006 - 7:50:54 AM
 * 
 * Este Bean relaciona los tipos de prueba para los SIPLEXPRO
 * 
 * @hibernate.class table="siplexpro_tipopruebas"
 */
public class TipoPrueba {
//TODO mover esta clase a maestros.siplexpro (por Andres)
	private Integer id;
	/**
	 * @hibernate.id type = "int" column = "id" unsaved-value = "-1" generator-class = "assigned"
	 */

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	private String tipo;
	/**
	 * @hibernate.property column = "tipo" type="string"
	 */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	private String descripcion;
	/**
	 * @hibernate.property column = "descripcion" type="string"
	 */
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	private String disponible;
	/**
	 * @hibernate.property column = "disponible" type="string"
	 */
	public String getDisponible() {
		return disponible;
	}
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	
    protected String paramString() {
    	return "id=" + id + "; tipo=" + tipo + "; descripcion=" + descripcion + "; disponible=" + disponible; 
    }
    
    public String toString() {
    	return getClass().getName() + "[" + paramString() + "]";
    }
}
