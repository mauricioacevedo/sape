/*
 * Created on Jan 14, 2005
 */
package com.osp.sape.maestros.rutinas;

/**
 * @hibernate.class table="rutina_cliente" * @author Andres Aristizabal */
public class RutinaCliente {


	private long oid;
    /**
     * @hibernate.id  column = "oid" type = "long" generator-class = "native"
     */
	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}    
	
    /**
     *  
     * @uml.property name="telefono"
     */
    private long telefono;

    /**
     * @hibernate.property  column = "telefono" type = "long"
     * @uml.property name="telefono"
     */
    public long getTelefono() {
        return telefono;
    }

    /**
     *  
     * @uml.property name="telefono"
     */
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**
     *  
     * @uml.property name="nombre"
     */
    private String nombre;

    /**
     * @hibernate.property column = "nombre" type = "string" 
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
     * @uml.property name="estatus"
     */
    private String estatus;
    /**
     * @hibernate.property column = "estatus" type = "string"
     * @uml.property name="estatus"
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     *  
     * @uml.property name="estatus"
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    private String usuario;
    /**
     * @hibernate.property column = "usuario" type = "string"
     */

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


}
