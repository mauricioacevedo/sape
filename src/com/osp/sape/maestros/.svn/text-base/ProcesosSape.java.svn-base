package com.osp.sape.maestros;

/**
 * @hibernate.class table = "procesosruntime"
 * @author Andres Aristizabal
 */
public class ProcesosSape {


	private Integer id;
    /**
     * @hibernate.id type = "java.lang.Integer" column = "id" unsaved-value = "-1" generator-class = "assigned"
     */	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	private String nombre;
    /**
     * @hibernate.property column = "nombre" type = "string" 
     */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private String comando;
    /**
     * @hibernate.property column = "comando" type = "string" 
     */
	public String getComando() {
		return comando;
	}
	public void setComando(String comando) {
		this.comando = comando;
	}
	
	private String expresion_verificar;
    /**
     * @hibernate.property column = "expresion_verificar" type = "string" 
     */
	public String getExpresion_verificar() {
		return expresion_verificar;
	}
	public void setExpresion_verificar(String expresion_verificar) {
		this.expresion_verificar = expresion_verificar;
	}
	
	private String host;
    /**
     * @hibernate.property column = "host" type = "string" 
     */
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	private String logfile;
    /**
     * @hibernate.property column = "logfile" type = "string" 
     */
	public String getLogfile() {
		return logfile;
	}
	public void setLogfile(String logfile) {
		this.logfile = logfile;
	}
	
	private String activo;
    /**
     * @hibernate.property column = "activo" type = "string" 
     */
	
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
    protected String paramString() {
    	return "id=" + id + "; nombre=" + nombre + "; comando=" + comando + 
    			"; expresion_verificar=" + expresion_verificar + 
    			"; host=" + host + "; logfile=" + logfile + "; activo=" + activo;
    }
    
    public String toString() {
    	return getClass().getName() + "[" + paramString() + "]";
    }
}
