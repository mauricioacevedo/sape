/*
 * Created on Jan 14, 2005
 */
package com.osp.sape.maestros;

/**
 * @hibernate.class table="cabezaprueba" * @author Andres Aristizabal */
public class CabezaPrueba {

	/**
	 *  
	 * @uml.property name="id"
	 */
	private int id;

	/**
	 * @hibernate.id type = "int" column = "idcabeza" unsaved-value = "-1" generator-class = "sequence"
     * @hibernate.generator-param name = "sequence" value = "idcabeza" 
	 * @uml.property name="id"
	 */
	public int getId() {
		return id;
	}

	/**
	 *  
	 * @uml.property name="id"
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 *  
	 * @uml.property name="nombre"
	 */
	private String nombre;

	/**
	 *  @hibernate.property column = "nombre" type = "string"
	 * @uml.property name="nombre"
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 *  
	 * @uml.property name="nombre"
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 *  
	 * @uml.property name="proveedor"
	 */
	private String proveedor;

	/**
	 *  @hibernate.property column = "provedor" type = "string"
	 * @uml.property name="proveedor"
	 */
	public String getProveedor() {
		return proveedor;
	}

	/**
	 *  
	 * @uml.property name="proveedor"
	 */
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
	public String toString() {
		return getClass().getName() + "[id=" + id + ", nombre=" + nombre + ", proveedor=" + proveedor + "]";
	}

}
