package com.osp.sape.maestros;
/**
 * @hibernate.class table = "siplexpro_ewsd"
 * @author Equipo de Desarrollo XpLoRa
 */
public class ConfiguracionEWSD {
	
	public ConfiguracionEWSD() {
		
	}
	
	public ConfiguracionEWSD(Long telefono, String central, Long dlu, TipoNodo nodo) {
		this.telefono = telefono;
		this.dlu = dlu;
		this.central = central;
		this.tipoNodo = nodo;
	}

	
	private Long telefono;
	/**
     * @hibernate.id  column = "telefono" type = "long" generator-class = "assigned"
     */
	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	private Long dlu;
    /**
     * @hibernate.property column = "dlu" type = "long" 
     */
	public Long getDlu() {
		return dlu;
	}

	public void setDlu(Long dlu) {
		this.dlu = dlu;
	}
	
	private Long bus_mta;
    /**
     * @hibernate.property column = "bus_mta" type = "long" 
     */
	public Long getBus_mta() {
		return bus_mta;
	}

	public void setBus_mta(Long bus_mta) {
		this.bus_mta = bus_mta;
	}

	private String central;
    /**
     * @hibernate.property column = "central" type = "string" 
     */
	public String getCentral() {
		return central;
	}

	public void setCentral(String central) {
		this.central = central;
	}
	
	public TipoNodo tipoNodo;
	/**
	 * @hibernate.many-to-one column="idtiponodo" class="com.osp.sape.maestros.TipoNodo" update="true" insert="true"
	 * @return
	 */
	public TipoNodo getTipoNodo() {
		return tipoNodo;
	}

	public void setTipoNodo(TipoNodo idTipoNodo) {
		this.tipoNodo = idTipoNodo;
	}
	
}
