/*
 * Created on Jan 14, 2005
 */
package com.osp.sape.maestros;


 
import java.util.Date;



/**
 * @hibernate.class table = "eventossape" * @author Andres Aristizabal */
public class EventoSape {


    /**
     * @uml.property name="id"
     */
    private long id = -1;

    /**
     * @hibernate.id column = "ideventossape" type = "long" unsaved-value = "-1" generator-class = "sequence"
     * @hibernate.generator-param name = "sequence" value = "ideventossape"
     * @uml.property name="id"
   */
     public long getId() {
        return id;
    }

    /**
     *  
     * @uml.property name="id"
     */
    public void setId(long id) {
        this.id = id;
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
     * 
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     *  
     * @uml.property name="ip"
     */
    private String ip;

    /**
     * @hibernate.property column = "ip" type = "string"
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
     * @uml.property name="telefono"
     */
    private String telefono;

    /**
     * @hibernate.property column = "telefono" type = "string"
     * @uml.property name="telefono"
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *  
     * @uml.property name="telefono"
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     *  
     * @uml.property name="tipoPrueba"
     */
    private String tipoPrueba;

    /**
     * @hibernate.property column = "tipoprueba" type = "string"
     * @uml.property name="tipoPrueba"
     */
    public String getTipoPrueba() {
        return tipoPrueba;
    }

    /**
     *  
     * @uml.property name="tipoPrueba"
     */
    public void setTipoPrueba(String tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    /**
     *  
     * @uml.property name="site"
     */
    private String site;

    /**
     * @hibernate.property column = "site" type = "string"
     * @uml.property name="site"
     */
    public String getSite() {
        return site;
    }

    /**
     *  
     * @uml.property name="site"
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     *  
     * @uml.property name="fecha"
     */
    
    private Date fechaInicial;

    /**
     * @hibernate.property column = "fecha_inicial" type = "timestamp"
     * @uml.property name="fecha"
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     *  
     * @uml.property name="fecha"
     */
    public void setFechaInicial(Date fecha) {
        fechaInicial = fecha;
    }

    /**
     * 
     * @uml.property name="fechaFinal"
     */
    private Date fechaFinal;

    /**
     * @hibernate.property column = "fecha_final" type = "timestamp" 
     * @uml.property name="fechaFinal"
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * 
     * @uml.property name="fechaFinal"
     */
    public void setFechaFinal(Date fecha) {
        this.fechaFinal = fecha;
    }

    
    /**
     *  
     * @uml.property name="central"
     */
    private String central;

    /**
     * @hibernate.property column = "central" type = "string"
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
     * @uml.property name="estado"
     */
    private String estado;

    /**
     * @hibernate.property column = "estado" type = "string"
     * @uml.property name="estado"
     */
    public String getEstado() {
        return estado;
    }

    /**
     *  
     * @uml.property name="estado"
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * 
     * @uml.property name="codigoVer"
     */
    private String codigoVer;

    /**
     * 
     * @uml.property name="codigoVer"
     */
    public String getCodigoVer() {
        return codigoVer;
    }

    /**
     * 
     * @uml.property name="codigoVer"
     */
    public void setCodigoVer(String codigoVer) {
        this.codigoVer = codigoVer;
    }
    
    private double duracion = 0d;
    
    public double getDuracion() {
        if(fechaFinal != null && fechaInicial != null) {
            duracion = (fechaFinal.getTime() - fechaInicial.getTime())/1000.0;
        }
        return duracion;
    }
    
    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
    
    protected String paramString() {
    	return "id=" + id + ";telefono=" + telefono + ";tipoPrueba=" + tipoPrueba + 
    		";site=" + site + ";central=" + central + ";cliente=" + cliente + 
    		";fechaInicial=" + fechaInicial + "fechaFinal=" + fechaFinal + 
    		";duracion=" + duracion + ";estado=" + estado;
    }
    
    public String toString() {
    	return getClass().getName() + "[" + paramString() + "]";
    }
}
