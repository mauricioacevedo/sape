package com.osp.sape.maestros;
/**
 * @hibernate.class table = "siplexpro_li"
 * @author Equipo de Desarrollo XpLoRa
 */
public class SiplexproLI {
	
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

	private String li;
    /**
     * @hibernate.property column = "li" type = "string" 
     */
	public String getLi() {
		return li;
	}

	public void setLi(String li) {
		this.li = li;
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
