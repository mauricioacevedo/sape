/*
 * Created on Jun 15, 2005
 */
package com.osp.sape.maestros;

/**
 * @hibernate.class table = "cables"
 * @author Andres Aristizabal
 */

public class Cable {

    /**
     *  
     * @uml.property name="central"
     */
    private String central;

    /**
     * @hibernate.id  column = "central" type = "java.lang.String" generator-class = "assigned"
     * @uml.property name="central"
     */
    public String getCentral() {
        return central;
    }

    /**
     *  
     * @uml.property name="central"
     */
    public void setCentral(String central) {
        this.central = central;
    }

    /**
     *  
     * @uml.property name="nombreCable"
     */
    private String nombreCable;

    /**
     * @hibernate.property column = "cable" type = "string"
     * @uml.property name="nombreCable"
     */
    public String getNombreCable() {
        return nombreCable;
    }

    /**
     *  
     * @uml.property name="nombreCable"
     */
    public void setNombreCable(String nombreCable) {
        this.nombreCable = nombreCable;
    }

    /**
     *  
     * @uml.property name="nombreArmario"
     */
    private String nombreArmario;

    /**
     * @hibernate.property column = "armario" type = "string"
     * @uml.property name="nombreArmario"
     */
    public String getNombreArmario() {
        return nombreArmario;
    }

    /**
     *  
     * @uml.property name="nombreArmario"
     */
    public void setNombreArmario(String nombreArmario) {
        this.nombreArmario = nombreArmario;
    }
    
    
    protected String paramString() {
    	return "central=" + central + "; nombreArmario=" + nombreArmario + "; nombreCable=" + nombreCable;
    }
    
    public String toString() {
    	return getClass().getName() + "[" + paramString() + "]";
    }

}
