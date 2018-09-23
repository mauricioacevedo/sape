package com.osp.sape.maestros;

/**
 * 
 * @hibernate.class table="indigos_epm"
 * 
 * @author Mauricio Acevedo
 *
 */
public class IndigoEpm {
	
	private Long id;
	//id_indigo     | bigint                | not null default nextval(('id_indigos_epm'::text)::regclass)
	private String  numeroIndigo;//numero_indigo | character varying(10) | not null
	
	private String ip; //ip_indigo
	
	private String port;//puerto_indigo

	
	/**
     * @hibernate.id type = "long" column = "id_indigo" unsaved-value = "-1" generator-class = "sequence"
     * @hibernate.generator-param name = "sequence" value = "id_indigos_epm"
     */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
     * @hibernate.property column = "ip_indigo" type = "string"
     */
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
     * @hibernate.property column = "numero_indigo" type = "string"
     */

	public String getNumeroIndigo() {
		return numeroIndigo;
	}

	public void setNumeroIndigo(String numeroIndigo) {
		this.numeroIndigo = numeroIndigo;
	}
	/**
     * @hibernate.property column = "puerto_indigo" type = "string"
     */
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
}
