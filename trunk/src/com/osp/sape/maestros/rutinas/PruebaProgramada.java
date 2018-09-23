/*
 * Created on Jan 14, 2005
 */
package com.osp.sape.maestros.rutinas;



import java.util.Date;


/**
 * @hibernate.class table="pruebaprogramada" * @author Andres Aristizabal */
public class PruebaProgramada {

	
	
    private int transaccion;
    private int idRutina;
    private String duracion;

    /**
     * @hibernate.id type = "int" column = "transaccion" unsaved-value = "-1" generator-class = "sequence"
     * @hibernate.generator-param name = "sequence" value = "transaccion_seq"
     * 
     * @uml.property name="transaccion"
     */
    public int getTransaccion() {
        return transaccion;
    }

    /**
     *  
     * @uml.property name="transaccion"
     */
    public void setTransaccion(int transaccion) {
        this.transaccion = transaccion;
    }

    /**
     *  
     * @uml.property name="tipoDePrueba"
     */
    private String tipoDePrueba;

    /**
     * @hibernate.property column = "tipodeprueba" type = "string" 
     * @uml.property name="tipoDePrueba"
     */
    public String getTipoDePrueba() {
        return tipoDePrueba;
    }

    /**
     *  
     * @uml.property name="tipoDePrueba"
     */
    public void setTipoDePrueba(String tipodeprueba) {
        this.tipoDePrueba = tipodeprueba;
    }

    /**
     *  
     * @uml.property name="telefonoIni"
     */
    private Long telefonoIni;

    /**
     * @hibernate.property column = "telefonoini" type = "long"
     * @uml.property name="telefonoIni"
     */
    public Long getTelefonoIni() {
        return telefonoIni;
    }

    /**
     *  
     * @uml.property name="telefonoIni"
     */
    public void setTelefonoIni(Long telefonoini) {
        this.telefonoIni = telefonoini;
    }

    /**
     *  
     * @uml.property name="telefonoFin"
     */
    
    private Long telefonoFin;

    /**
     * @hibernate.property column = "telefonofin" type = "long"
     * @uml.property name="telefonoFin"
     */
	public Long getTelefonoFin() {
		return telefonoFin;
	}
	
    /**
     *  
     * @uml.property name="telefonoFin"
     */
    
	public void setTelefonoFin(Long telefonoFin) {
		this.telefonoFin = telefonoFin;
	}
    
    /**
     *  
     * @uml.property name="numeroPruebas"
     */
    private Integer numeroPruebas;

    /**
     * @hibernate.property column = "numpruebas" type = "integer" 
     * @uml.property name="numeroPruebas"
     */
    public Integer getNumeroPruebas() {
        return numeroPruebas;
    }

    /**
     *  
     * @uml.property name="numeroPruebas"
     */
    public void setNumeroPruebas(Integer numeroPruebas) {
        this.numeroPruebas = numeroPruebas;
    }

    /**
     *  
     * @uml.property name="fechaIni"
     */
    private Date fechaIni;

    /**
     * @hibernate.property column = "fechaini" type = "timestamp"
     * @uml.property name="fechaIni"
     */
    public Date getFechaIni() {
        return fechaIni;
    }

    /**
     *  
     * @uml.property name="fechaIni"
     */
    public void setFechaIni(Date fechaini) {
        this.fechaIni = fechaini;
    }

    private Date fechaFin;

    /**
     * @hibernate.property column = "fechafin" type = "timestamp"
     */
    public Date getFechaFin() {
        return fechaFin;
    }


    public void setFechaFin(Date fechafin) {
        this.fechaFin = fechafin;
    }

    
    /**
     * @hibernate.property formula="to_char(fechafin - fechaini, 'HH24:MI:SS')"
     * @return
     */
    public String getDuracion() {
    	return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	/**
	 * @hibernate.property column="idrutina" type="int"
	 * @return
	 */
	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}

	protected String paramString() {
    	return "transaccion=" + transaccion + "; telefonoIni=" + telefonoIni + "; telefonoFin=" + telefonoFin + 
    			"; numeroPruebas=" + numeroPruebas + "; fechaIni=" + fechaIni + "; fechaFin=" + fechaFin + 
    			"; idRutina=" + idRutina + "; duracion=" + duracion;
    }
    
    public String toString() {
    	return getClass().getName() + " [" + paramString() + "]";
    }
}
