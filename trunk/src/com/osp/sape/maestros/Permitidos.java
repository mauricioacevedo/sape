/*
 * Created on Jan 14, 2005
 */
package com.osp.sape.maestros;

/**
 * @hibernate.class table="permitidos" * @author Andres Aristizabal */
public class Permitidos {


    /**
     *  
     * @uml.property name="ip"
     */
    private String ip;

    /**
     * @hibernate.id  column = "ip" type = "java.lang.String" generator-class = "assigned" 
     * @uml.property name="ip"
     */
    public String getIp() {
        return ip;
    }

    /**
     *  
     * @uml.property name="ip"
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     *  
     * @uml.property name="cliente"
     */
    private String cliente;

    /**
     * @hibernate.property column = "cliente" type = "string"
     * @uml.property name="cliente"
     */
    public String getCliente() {
        return cliente;
    }

    /**
     *  
     * @uml.property name="cliente"
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    protected String paramString() {
    	return "cliente=" + cliente + "; ip=" + ip;
    }

    public String toString() {
    	return getClass().getName() + "[" + paramString() + "]";
    }
}
