package com.osp.sape.maestros.siplexpro;

import java.util.HashMap;

/**
 * Clase para mapear los campos correspondientes a la configuracion de "ADSL hacia DSLAM"
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
 *  - Los campos de esta clase tienen en la parte final un identificador (Dslam) esta se utiliza
 *    para diferenciar estos de las demas clases debido a que estos mismos campos se repiten
 *    en CPE.
 * @author DevelopTeam
 *
 * @hibernate.class table="siplexpro_configuracionadsl"
 */
@SuppressWarnings("unchecked")
public class DSLAM  extends ConfiguracionADSL {
	
	public DSLAM(){
		super();
		inicializarConfiguracion();
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
	
	public String stdDslam; 
	/**
	 * @hibernate.property column = "dslam_std" type="string"
	 */	
	public String getStdDslam() {
		return stdDslam;
	}
	
	public void setStdDslam(String dslam_std) {
		this.stdDslam = dslam_std;
	}
	
	public String txPwrAttDslam; 	
	/**
	 * @hibernate.property column = "dslam_tx_pwr_att" type="string"
	 */
	
	public String getTxPwrAttDslam() {
		return txPwrAttDslam;
	}
	public void setTxPwrAttDslam(String dslam_tx_pwr_att) {
		this.txPwrAttDslam = dslam_tx_pwr_att;
	}
	
	public String bitSwapDslam; 	
	/**
	 * @hibernate.property column = "dslam_bit_swap" type="string"
	 */
	
	public String getBitSwapDslam() {
		return bitSwapDslam;
	}
	public void setBitSwapDslam(String dslam_bit_swap) {
		this.bitSwapDslam = dslam_bit_swap;
	}
	
	public String negTimeDslam;
	/**
	 * @hibernate.property column = "dslam_neg_time" type="string"
	 */
	
	public String getNegTimeDslam() {
		return negTimeDslam;
	}
	public void setNegTimeDslam(String dslam_neg_time) {
		this.negTimeDslam = dslam_neg_time;
	}
	
	
	private void inicializarConfiguracion(){
		setCondiciones(crearCondiciones());
		setNombres(crearNombres());
		getValues();
		
		HashMap relacionVbles = new HashMap();
		
		
		relacionVbles.put("stdDslam","STD");
		relacionVbles.put("txPwrAttDslam","TX_PWR_ATT");
		relacionVbles.put("bitSwapDslam","BIT_SWAP");
		relacionVbles.put("negTimeDslam","NEG_TIME");

		setRelacionNombresVariables(relacionVbles);
	}
	
	
	private HashMap crearCondiciones(){
		
		HashMap cond = new HashMap();	
		
		cond.put("stdDslam","[COMBO]T1_413,G_DMT,MULTIMODE,GLITE");
		cond.put("txPwrAttDslam","[COMBO]0,1,2,3,4,5,6,7,8,9,10,11,12");
		cond.put("bitSwapDslam","[COMBO]ENABLE,DISABLE");
		cond.put("negTimeDslam","[RANGE]1,120");
		return cond;
	}
	
	private HashMap crearNombres(){
		
		HashMap names = new HashMap();
		names.put("stdDslam","Standard");
		names.put("txPwrAttDslam","Tx Power Attenuation");
		names.put("bitSwapDslam","Adjust bits/bin");
		names.put("negTimeDslam","Negotiation Time");
		return names;
	}
	

	
}
