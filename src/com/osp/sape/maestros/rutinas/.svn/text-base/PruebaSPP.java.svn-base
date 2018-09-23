/*
  * Created on Jan 14, 2005
 */
package com.osp.sape.maestros.rutinas;


/**
 * @hibernate.class table="pruebaspp" * @author Andres Aristizabal */

public class PruebaSPP {
	
	private String calificacion;
	private String velocidadUP;
	private String velocidadDown;
	private String calificacionDatos;
	

	private Integer idpruebapp;
	
    /**
     * @hibernate.id type = "java.lang.Integer" column = "idpruebapp" unsaved-value = "-1" generator-class = "sequence"
     * @hibernate.generator-param name = "sequence" value = "idpruebaspp"
     */
	public Integer getIdpruebapp() {
		return idpruebapp;
	}
	
	public void setIdpruebapp(Integer idpruebapp) {
		this.idpruebapp = idpruebapp;
	}
	
    /**
     * Id de la prueba basica 
     */
    private Integer transaccion_spp;
    /**
     * @hibernate.property column = "transaccion_spp" type = "integer"
     */
    public Integer getTransaccion_spp() {
        return transaccion_spp;
    }

    
    public void setTransaccion_spp(Integer transaccion_spp) {
        this.transaccion_spp = transaccion_spp;
    }

    private String codigover;
    /**
     * @hibernate.property column = "codigover" type = "string"  
     */
    public String getCodigover() {
        return codigover;
    }

    
    public void setCodigover(String codigover) {
        this.codigover = codigover;
    }

    private Long telefono;
    
    /**
     * @hibernate.property column = "telefono" type = "long"
     */ 
    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    
    private PruebaProgramada idpruebaprogramada;
    
    /**
     * @hibernate.many-to-one column="idpruebaprogramada" class="com.osp.sape.maestros.rutinas.PruebaProgramada" update="false" insert="false"
     */
    public PruebaProgramada getIdpruebaprogramada() {
        return idpruebaprogramada;
    }
 
    public void setIdpruebaprogramada(PruebaProgramada idpruebaprogramada) {
        this.idpruebaprogramada = idpruebaprogramada;
    }
    
    
    /**
     * @hibernate.property column = "calificacion" type = "string" 
     * @return
     */
	public String getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	
	
    /**
     *  
     * Este campo contiene las capacitancias de la prueba
     * en el orden (A-B, A-T, B-T).
     */
    private String capacitancias;

    /**
     * @hibernate.property column = "capacitancia" type = "string" 
     */
	public String getCapacitancias() {
		return capacitancias;
	}
   
	public void setCapacitancias(String capacitancias) {
		this.capacitancias = capacitancias;
	}	
    
    private String resistencias;

    /**
     * @hibernate.property column = "resistencia" type = "string" 
     */
	public String getResistencias() {
		return resistencias;
	}
	

	public void setResistencias(String resistencias) {
		this.resistencias = resistencias;
	}
    
    
	/**
	 * @hibernate.property column="calificaciondatos" type="string"
	 */
    public String getCalificacionDatos() {
		return calificacionDatos;
	}

    
	public void setCalificacionDatos(String calificacionDatos) {
		this.calificacionDatos = calificacionDatos;
	}

	/**
	 * @hibernate.property column="veldown" type="string"
	 */	
	public String getVelocidadDown() {
		return velocidadDown;
	}

	
	public void setVelocidadDown(String velocidadDown) {
		this.velocidadDown = velocidadDown;
	}

	/**
	 * @hibernate.property column="velup" type="string"
	 */
	public String getVelocidadUP() {
		return velocidadUP;
	}

	public void setVelocidadUP(String velocidadUP) {
		this.velocidadUP = velocidadUP;
	}

	protected String paramString() {
        return "idPruebaProgramada=" + idpruebaprogramada + ", telefono=" + telefono + 
				", transaccion_spp=" + transaccion_spp + ", codigover=" + codigover + 
				", capacitancias=" + capacitancias + ", resistencias=" + resistencias + 
				", velocidadUp=" + velocidadUP + ", velocidadDown=" + velocidadDown + 
				", calificacionDatos=" + calificacionDatos; 
    }
	
    
	public String toString() {
		return getClass().getName() + "[" + paramString() + "]";
	}

}
