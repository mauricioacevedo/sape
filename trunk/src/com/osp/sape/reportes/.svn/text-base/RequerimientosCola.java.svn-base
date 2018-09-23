/*
 * Created on Jun 2, 2005
 */
package com.osp.sape.reportes;

import java.util.Date;



/**
 * @hibernate.class table="requerimientoscola"
 * @author Andres Aristizabal
 */
public class RequerimientosCola {
    
    private long oid;
	
    /**
     * @param name="id"
       */
    private String identificador;
    
    private String colaEnruta;
    private String codObservacion;
    private long ticketPrueba;
    private String producto;
    private String tipoCliente;
    



	/**
     * @hibernate.property type = "string" column = "identificador" 
     * @author Andres Aristizabal
     */
    
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    /**
     * @param name="tipo_nodo"
     */
    
    private String tipo_nodo;
    
    /**
     * @hibernate.property column = "tipo_nodo" type = "string"
     */
    
    public String getTipo_nodo() {
        return tipo_nodo;
    }
    public void setTipo_nodo(String tipo_nodo) {
        this.tipo_nodo = tipo_nodo;
    }
    
    /**
     * @param name="area_trabajo_id"
     */
    
    private String area_trabajo_id;
    /**
     * @hibernate.property column = "area_trabajo_id" type = "string"
     */
    public String getArea_trabajo_id() {
        return area_trabajo_id;
    }
    public void setArea_trabajo_id(String area_trabajo_id) {
        this.area_trabajo_id = area_trabajo_id;
    }
    
    /**
     * @param name="cable"
     */
    
    private String cable;
    /**
     * @hibernate.property column = "cable" type = "string"
     */
    public String getCable() {
        return cable;
    }
    public void setCable(String cable) {
        this.cable = cable;
    }

    /**
     * @param name="strip_id"
     */
    
    private String strip_id;
    /**
     * @hibernate.property column = "strip_id" type = "string"
     */
    
    
    public String getStrip_id() {
        return strip_id;
    }
    public void setStrip_id(String strip_id) {
        this.strip_id = strip_id;
    }
    
    /**
     * @param name="armario_id"
     */
    
    private String armario_id;
    /**
     * @hibernate.property column = "armario_id" type = "string"
     */
    public String getArmario_id() {
        return armario_id;
    }
    public void setArmario_id(String armario_id) {
        this.armario_id = armario_id;
    }
    
    
    /**
     * @param name="par_primario_id"
     */
    private String par_primario_id;
    /**
     * @hibernate.property column = "par_primario_id" type = "string"
     */
    public String getPar_primario_id() {
        return par_primario_id;
    }
    public void setPar_primario_id(String par_primario_id) {
        this.par_primario_id = par_primario_id;
    }
    
    /**
     * @param name="caja_id"
     */
    private String caja_id;
    /**
     * @hibernate.property column = "caja_id" type = "string"
     */
    public String getCaja_id() {
        return caja_id;
    }
    public void setCaja_id(String caja_id) {
        this.caja_id = caja_id;
    }
    
    
    /**
     * @param name="par_secundario_id"
     */
    private String par_secundario_id;
    /**
     * @hibernate.property column = "par_secundario_id" type = "string"
     */
    public String getPar_secundario_id() {
        return par_secundario_id;
    }
    public void setPar_secundario_id(String par_secundario_id) {
        this.par_secundario_id = par_secundario_id;
    }
    
    /**
     * @param name="cola_id"
     */
    private String cola_id;
    /**
     * @hibernate.property column = "cola_id" type = "string"
     */
    public String getCola_id() {
        return cola_id;
    }
    public void setCola_id(String cola_id) {
        this.cola_id = cola_id;
    }
    
    /**
     * @param name="secuencia"
     */
    private String secuencia;
    /**
     * @hibernate.property column = "secuencia" type = "string"
     */
    public String getSecuencia() {
        return secuencia;
    }
    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }
    
    /**
     * @param name="estado"
     */
    private String estado;
    /**
     * @hibernate.property column = "estado" type = "string"
     */
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /**
     * @param name="subzona_id"
     */
    private String subzona_id;
    /**
     * @hibernate.property column = "subzona_id" type = "string"
     */
    public String getSubzona_id() {
        return subzona_id;
    }
    public void setSubzona_id(String subzona_id) {
        this.subzona_id = subzona_id;
    }
    
    /**
     * @param name="fecha"
     */
    private Date fecha;              //timestamp without time zone |
    /**
     * @hibernate.property column = "fecha" type = "timestamp"
     * 
     */

    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
	/**
	 * @hibernate.property column = "codobserva" type = "string"
	 */

    public String getCodObservacion() {
		return codObservacion;
	}

	public void setCodObservacion(String codObservacion) {
		this.codObservacion = codObservacion;
	}

	/**
	 * @hibernate.property column = "cola_enruta" type = "string"
	 * Esta es la cola a la que se enruta.
	 */
	public String getColaEnruta() {
		return colaEnruta;
	}

	public void setColaEnruta(String cola) {
		this.colaEnruta = cola;
	}
	
	/**
	 * @hibernate.id column = "ticket_prueba" type = "long" generator-class = "assigned"
	 */
	public long getTicketPrueba() {
		return ticketPrueba;
	}

	public void setTicketPrueba(long ticketPrueba) {
		this.ticketPrueba = ticketPrueba;
	}

	/**
	 * @hibernate.property column = "producto_id" type = "string"
	 * @return
	 */
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	/**
	 * @hibernate.property column = "tipo_cliente" type = "string"
	 * @return
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

    

}
