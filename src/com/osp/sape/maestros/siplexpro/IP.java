package com.osp.sape.maestros.siplexpro;

import java.util.HashMap;

/**
 * Clase para mapear los campos correspondientes a la configuracion de "ADSL IP"
 * de la tabla siplexpro_configuracionadsl. Tambien hereda de la clase ConfiguracionADSL que proporciona
 * a la clase algunos elementos para extraer informacion. Tener en cuenta que esta informacion se esta
 * llevando de forma dinamica al jsp
 * 
 * RECOMENDACION: siempre tener bien configuradas las variables y sus getter:
 * - dejar solo como variables publicas los atributos que se van a mostrar en el jsp
 * - Que siempre estas variables publicas tengan sus metodos get* y set*
 * - Para cada campo, ingresar en el constructor una entrada en el hashmap, esto es para
 *   cuando se necesite algun tipo de validacion sobre el campo:
 *   - Si se necesita un comboBox, se ingresa como key el nombre del campo y como valor
 *     los diferentes posibles valores que tomara el combobox separados por "," y al inicio
 *     la cadena "[COMBO]" asi: condiciones.put("standard","[COMBO]ENABLE,DISABLE");
 *   - Si se necesita un rango de valores numericos que sean validado solo colocamos 
 *     la siguiente sentencia: condiciones.put("standard","[RANGE]1,3600");
 *   - Si no se agrega ninguna informacion al hashmap con respecto al campo en el jsp aparecera
 *     un input sencillo sin validaciones.
 * 
 * @author DevelopTeam
 *
 * @hibernate.class table="siplexpro_configuracionadsl"
 */
@SuppressWarnings("unchecked")
public class IP extends ConfiguracionADSL{
	
	public IP(){
		super();
		inicializarConfiguracion();
	}
	
	private void inicializarConfiguracion(){
		setCondiciones(crearCondiciones());
		setNombres(crearNombres());
		getValues();
		
		HashMap relacionVbles = new HashMap();
		
		relacionVbles.put("ipDest","IP_DEST");
		relacionVbles.put("totalPings","TOTAL_PINGS");
		relacionVbles.put("timeout","TIMEOUT");
		relacionVbles.put("packetSize","PACKET_SIZE");
		relacionVbles.put("maxHops","MAX_HOPS");

		setRelacionNombresVariables(relacionVbles);
	}
	
	private HashMap crearCondiciones(){
		
		HashMap cond = new HashMap();	
		
		//cond.put("ipDest","");
		cond.put("totalPings","[RANGE]1,100");
		cond.put("timeout","[RANGE]1,32");
		cond.put("packetSize","[RANGE]1,5000");
		cond.put("maxHops","[RANGE]1,64");
		return cond;
	}
	
	private HashMap crearNombres(){
		
		HashMap names = new HashMap();
		names.put("ipDest","Destination IP Address");
		names.put("totalPings","Total Number of Pings");
		names.put("timeout","Time to wait for PING");
		names.put("packetSize","PING packet size");
		names.put("maxHops","Maximum number of hops");
		
		return names;
	}
	
	
	private int idCabeza;
	
	/**
	 * @hibernate.id type = "int" column = "idcabeza" unsaved-value = "-1" generator-class = "assigned"
	 */
	public int getIdCabeza() {
		return idCabeza;
	}

	public void setIdCabeza(int  idcabeza) {
		this.idCabeza = idcabeza;
	}	

	
	public String ipDest;
	/**
	 * @hibernate.property column = "ip_ip_dest" type="string"
	 */
	public String getIpDest() { 
		return ipDest;
	}
	public void setIpDest(String ip_ip_dest) {
		this.ipDest = ip_ip_dest;
	}
	public String totalPings;
	/**
	 * @hibernate.property column = "ip_total_pings" type="string"
	 */
	public String getTotalPings() {
		return totalPings;
	}
	public void setTotalPings(String ip_total_pings) {
		this.totalPings = ip_total_pings;
	}
	public String timeout;
	/**
	 * @hibernate.property column = "ip_timeout" type="string"
	 */
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String ip_timeout) {
		this.timeout = ip_timeout;
	}
	public String packetSize="32";
	/**
	 * @hibernate.property column = "ip_packet_size" type="string"
	 */
	public String getPacketSize() {
		return packetSize;
	}
	public void setPacketSize(String ip_packet_size) {
		this.packetSize = ip_packet_size;
	}
	public String maxHops;
	/**
	 * @hibernate.property column = "ip_max_hops" type="string"
	 */
	public String getMaxHops() {
		return maxHops;
	}
	public void setMaxHops(String ip_max_hops) {
		this.maxHops = ip_max_hops;
	}
	
}
