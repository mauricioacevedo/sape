/*
 * Created on Jan 14, 2005
 */
package com.osp.sape.maestros;

/** * @author Andres Aristizabal */
public class RutinaCliente {


    /**
     *  
     * @uml.property name="telefono"
     */
    private int telefono;

    /**
     *  
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
     * @uml.property name="nombre"
     */
    private String nombre;

    /**
     *  
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
     *  
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

    protected String paramString() {
    	return "telefono=" + telefono + "; nombre=" + nombre + "; estatus=" + estatus;
    }
    
    public String toString() {
    	return getClass().getName() + "[" + paramString() + "]";
    }
    
}
