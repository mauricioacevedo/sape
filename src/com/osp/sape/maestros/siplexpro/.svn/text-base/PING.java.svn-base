package com.osp.sape.maestros.siplexpro;

import java.util.HashMap;

/**
 * Clase para mapear los campos correspondientes a la configuracion 
 * ADSL de PPPoE, PPPoA, Bridge y IPoA de la tabla siplexpro_configuracionadsl.  Tambien hereda de la clase ConfiguracionADSL que proporciona
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
 * @author DevelopTeam
 *
 * @hibernate.class table="siplexpro_configuracionadsl"
 */
@SuppressWarnings("unchecked")
public class PING extends ConfiguracionADSL{
	
	public PING(){
		super();
		inicializarConfiguracion();

	}
	
	public String tipo;
	/**
	 * @hibernate.property column = "ping_tipo" type="string"
	 */
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String ping_tipo) {
		this.tipo = ping_tipo;
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

	public String vpi;
	/**
	 * @hibernate.property column = "ping_vpi" type="string"
	 */	
	public String getVpi() {
		return vpi;
	}
	public void setVpi(String ping_vpi) {
		this.vpi = ping_vpi;
	}
	
	public String vci;
	/**
	 * @hibernate.property column = "ping_vci" type="string"
	 */
	public String getVci() {
		return vci;
	}
	public void setVci(String ping_vci) {
		this.vci = ping_vci;
	}
	
	public String localIp = "";
	/**
	 * @hibernate.property column = "ping_local_ip" type="string"
	 */
	public String getLocalIp() {
		return localIp;
	}
	public void setLocalIp(String ping_local_ip) {
		this.localIp = ping_local_ip;
	}
	
	public String ip;
	/**
	 * @hibernate.property column = "ping_ip" type="string"
	 */
	public String getIp() {
		return ip;
	}
	public void setIp(String ping_ip) {
		this.ip = ping_ip;
	}
	
	public String login;
	/**
	 * @hibernate.property column = "ping_login" type="string"
	 */
	public String getLogin() {
		return login;
	}
	public void setLogin(String ping_login) {
		this.login = ping_login;
	}
	
	public String domain;
	/**
	 * @hibernate.property column = "ping_domain" type="string"
	 */
	public String getDomain() {
		return domain;
	}
	public void setDomain(String ping_domain) {
		this.domain = ping_domain;
	}
	
	public String password;
	/**
	 * @hibernate.property column = "ping_password" type="string"
	 */
	public String getPassword() {
		return password;
	}
	public void setPassword(String ping_password) {
		this.password = ping_password;
	}
	
	public String encapType; 	
	/**
	 * @hibernate.property column = "ping_encap_type" type="string"
	 */
	public String getEncapType() {
		return encapType;
	}

	public void setEncapType(String ping_encap_type) {
		this.encapType = ping_encap_type;
	}
	
	public String gateway;
	/**
	 * @hibernate.property column = "ping_gateway" type="string"
	 */
	public String getGateway() {
		return gateway;
	}

	public void setGateway(String ping_gateway) {
		this.gateway = ping_gateway;
	}
	
	public String subMask;
	/**
	 * @hibernate.property column = "ping_sub_mask" type="string"
	 */
	public String getSubMask() {
		return subMask;
	}

	public void setSubMask(String ping_sub_mask) {
		this.subMask = ping_sub_mask;
	}
	
	public String lanType;
	/**
	 * @hibernate.property column = "ping_lan_type" type="string"
	 */
	public String getLanType() {
		return lanType;
	}

	public void setLanType(String ping_lan_type) {
		this.lanType = ping_lan_type;
	}
	
	public String fcsCont;
	/**
	 * @hibernate.property column = "ping_fcs_cont" type="string"
	 */
	public String getFcsCont() {
		return fcsCont;
	}

	public void setFcsCont(String ping_fcs_cont) {
		this.fcsCont = ping_fcs_cont;
	}

	

	/**
	 * Metodo que devuelve un HashMap con el comportamiento y posibles valores que puede
	 * tomar este campo.
	 */
	private HashMap crearCondiciones(){
		
		HashMap cond = new HashMap();	
		
		cond.put("tipo","[COMBO]PPPoE,PPPoA,Bridge,IPoA");
		cond.put("vpi","[RANGE]0,255");
		cond.put("vci","[RANGE]0,65535");
		cond.put("localIp","[COMBO]STATIC,DYNAMIC");
		//cond.put("ip","");
		cond.put("encapType","[COMBO]LLC,VC-MUX");
		cond.put("lanType","[COMBO]ETHERNET V2,IEEE");
		cond.put("fcsCont","[COMBO]ENABLE,DISABLE");
		return cond;
	}
	/**
	 * Metodo utilizado para relacionar a cada campo una descripcion del mismo.
	 * @return
	 */
	private HashMap crearNombres(){
		
		HashMap names = new HashMap();
		names.put("tipo","Tipo de configuracion(PPPoE,PPPoA,Bridge,IPoA)");
		names.put("vpi","Virtual Path Identifier");
		names.put("vci","Virtual Circuit Identifier");
		names.put("localIp","IP assignment");
		
		names.put("ip","IP Address");
		names.put("login","User login name");
		names.put("domain","Domain name");
		names.put("password","Password");
		names.put("encapType","Encapsulation type");
		names.put("gateway","Gateway");
		names.put("subMask","Subnet Mask");
		
		names.put("lanType","Lan Type");
		names.put("fcsCont","FCS Control");
		return names;
	}

	/**
	 * Este vector guardara en cada posicion el diferente conjunto de variables para cada
	 * configuracion (PPPoE, PPPoA, Bridge y IPoA).
	 */
	private HashMap configuraciones;

	
	/**
	 * Con este metodo configuro las variables que tengo disponibles con respecto al 
	 * tipo de configuracion (PPPoE, PPPoA, Bridge y IPoA) que se tenga en el momento.
	 * Y se inicializan otras variables necesarias para la correcta ejecucion del codigo.
	 *
	 */
	private void inicializarConfiguracion(){

		setCondiciones(crearCondiciones());
		setNombres(crearNombres());
		getValues();
		
		String[] configPPPoE = {"vci","vpi","localIp","ip","login","domain","password"};
		String[] configPPPoA = {"vci","vpi","localIp","ip","login","domain","password","encapType"};
		String[] configIPoA  = {"vci","vpi","ip","gateway","subMask"};
		String[] configBridge = {"vci","vpi","localIp","ip","gateway","subMask","encapType","lanType","fcsCont"};
		
		configuraciones = new HashMap();
		
		configuraciones.put("PPPoE",configPPPoE);
		configuraciones.put("PPPoA",configPPPoA);
		configuraciones.put("IPoA",configIPoA);
		configuraciones.put("Bridge",configBridge);
		/*
		configPPPoE.put("vci","true");
		configPPPoE.put("vpi","true");
		configPPPoE.put("localIp","true");
		configPPPoE.put("ip","true");
		configPPPoE.put("login","true");
		configPPPoE.put("domain","true");
		configPPPoE.put("password","true");
		
		configPPPoA.put("vci","true");
		configPPPoA.put("vpi","true");
		configPPPoA.put("localIp","true");
		configPPPoA.put("ip","true");
		configPPPoA.put("login","true");
		configPPPoA.put("domain","true");
		configPPPoA.put("password","true");
		configPPPoA.put("encapType","true");
		
		configIPoA.put("vci","true");
		configIPoA.put("vpi","true");
		configIPoA.put("ip","true");
		configIPoA.put("gateway","true");
		configIPoA.put("subMask","true");
		

		configBridge.put("vci","true");
		configBridge.put("vpi","true");
		configBridge.put("localIp","true");
		configBridge.put("ip","true");
		configBridge.put("gateway","true");
		configBridge.put("subMask","true");
		configBridge.put("encapType","true");
		configBridge.put("lanType","true");
		configBridge.put("fcsCont","true");
*/		
		// INIcializo el hash con la relacion de variables:
		
		HashMap relacionVbles = new HashMap();
		
		relacionVbles.put("vci","VCI");
		relacionVbles.put("vpi","VPI");
		relacionVbles.put("localIp","LOCAL_IP");
		relacionVbles.put("ip","IP");
		relacionVbles.put("login","LOGIN");
		relacionVbles.put("domain","DOMAIN");
		relacionVbles.put("password","PASSWORD");
		relacionVbles.put("encapType","ENCAP_TYPE");
		relacionVbles.put("gateway","GW");
		relacionVbles.put("subMask","SM");
		relacionVbles.put("lanType","LAN_TYPE");
		relacionVbles.put("fcsCont","FCS_CONT");
		
		setRelacionNombresVariables(relacionVbles);
	}
	
	public String[] getVarsConfig(String config){
		return (String[])configuraciones.get(config);
	}
}
