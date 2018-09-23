/*
 * Created on 7/02/2005
 */
package com.osp.sape.maestros.auditoria;

import java.util.Date;

/**
 * @hibernate.class table="visitas"
 * @author Andres
 */
public class Visita {

    private int id;
	private Date fechaIngreso;
    private Date fechaSalida;
    /** El nombre de usuario que hace la visita por defecto ANONIMO */
    private String usuario = "ANONIMO";
    private String ip;
    
    private String duracion;
    
    
    public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Visita() {
    	
    }
    
	public Visita(String usuario, Date fechaIngreso, String ip) {
    	this.usuario = usuario;
    	this.fechaIngreso = fechaIngreso;
    	this.ip = ip;
    }
    
    /**
     * @hibernate.id type = "int" column = "id" unsaved-value = "-1" generator-class = "sequence"
     * @hibernate.generator-param name = "sequence" value = "idvisitas"
     */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    /**
     * @hibernate.property column="fecha_ingreso" type="timestamp"
     * @return
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }
    
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    /**
     * @hibernate.property column="fecha_salida" type="timestamp"
     * @return
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }
    
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    /**
     * @hibernate.property type="string" column="usuario"
     * @return
     */
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * @hibernate.property column="ip" type="string"
     * @return
     */
    public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

    public String toString() {
        return getClass().getName() + ": [id=" + id + "; fechaIngreso=" + fechaIngreso + 
        				", usuario=" + usuario + ", fechaSalida=" + fechaSalida + "]";
    }

}
