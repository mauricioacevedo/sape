/*
 * Created on Jun 16, 2005
 */

package com.osp.sape.maestros.rutinas;

/**
 * @hibernate.class table = "rutina_cable"
 * @author Andres Aristizabal
 */

public class RutinaCable {

    /**
     *  
     * @uml.property name="telefono"
     */
    
    private int telefono;

    /**
     * @hibernate.id column = "telefono" generator-class = "assigned" type = "int"
     * @uml.property name="telefono"
     */

    public int getTelefono() {
        return telefono;
    }

    /**
     *  
     * @uml.property name="telefono"
     */
    
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    /**
     *  
     * @uml.property name="direccion"
     */    
    private String direccion;

    /**
     * @hibernate.property column = "direccion" type = "string"
     * @uml.property name="direccion"
     */

	public String getDireccion() {
		return direccion;
	}

	/**
     *  
     * @uml.property name="direccion"
     */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


    /**
     * @uml.property name="tipoCliente"
     */
    private String tipoCliente; 

    /**
     * @hibernate.property column = "tipocliente" type = "string"
     * @uml.property name="tipoCliente"
     */
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     *  
     * @uml.property name="tipoCliente"
     */
    public void setTipoCliente(String tipo) {
        this.tipoCliente = tipo;
    }
  

    /**
     *  
     * @uml.property name="cable"
     */
    
    private String cable;

    /**
     * @hibernate.property column = "cable" type = "string"
     * @uml.property name="cable"
     */
    
    public String getCable() {
        return cable;
    }

    /**
     *  
     * @uml.property name="cable"
     */
    
    public void setCable(String cable) {
        this.cable = cable;
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

}
