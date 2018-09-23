package com.osp.sape.maestros.siplexpro;

import java.util.HashMap;

/**
 * Clase para mapear los campos correspondientes a la configuracion de "ADSL hacia CPE"
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
 * @author DevelopTeam
 *
 * @hibernate.class table="siplexpro_configuracionadsl"
 */

public class CPE extends ConfiguracionADSL{
	
	public CPE(){
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

	

	public String std;
	/**
	 * @hibernate.property column = "cpe_std" type="string"
	 */	
	public String getStd() {
		return std;
	}
	public void setStd(String Standard) {
		this.std = Standard;
	}	
	
	public String txPwrAtt;
	/**
	 * @hibernate.property column = "cpe_tx_pwr_att" type="string"
	 */
	
	public String getTxPwrAtt() {
		return txPwrAtt;
	}
	public void setTxPwrAtt(String cpe_tx_pwr_att) {
		this.txPwrAtt = cpe_tx_pwr_att;
	}
	
	public String upMargin;
	/**
	 * @hibernate.property column = "cpe_up_margin" type="string"
	 */
	public String getUpMargin() {
		return upMargin;
	}
	public void setUpMargin(String cpe_up_margin) {
		this.upMargin = cpe_up_margin;
	}
	
	public String dwnMargin;
	/**
	 * @hibernate.property column = "cpe_dwn_margin" type="string"
	 */
	public String getDwnMargin() {
		return dwnMargin;
	}
	public void setDwnMargin(String cpe_dwn_margin) {
		this.dwnMargin = cpe_dwn_margin;
	}
	
	public String ntr;
	/**
	 * @hibernate.property column = "cpe_ntr" type="string"
	 */
	public String getNtr() {
		return ntr;
	}
	public void setNtr(String cpe_ntr) {
		this.ntr = cpe_ntr;
	}
	
	public String pmode;
	/**
	 * @hibernate.property column = "cpe_pmode" type="string"
	 */
	public String getPmode() {
		return pmode;
	}
	public void setPmode(String cpe_pmode) {
		this.pmode = cpe_pmode;
	}
	
	public String toneOrdering;
	/**
	 * @hibernate.property column = "cpe_tone_ordering" type="string"
	 */
	public String getToneOrdering() {
		return toneOrdering;
	}
	public void setToneOrdering(String cpe_tone_ordering) {
		this.toneOrdering = cpe_tone_ordering;
	}

	public String escapeFastRetrain;
	/**
	 * @hibernate.property column = "cpe_escape_fast_retrain" type="string"
	 */
	public String getEscapeFastRetrain() {
		return escapeFastRetrain;
	}
	public void setEscapeFastRetrain(String rapido_directamente) {
		this.escapeFastRetrain = rapido_directamente;
	}
	
	public String fastRetrainEnable;
	/**
	 * @hibernate.property column = "cpe_fast_retrain_enable" type="string"
	 */
	public String getFastRetrainEnable() {
		return fastRetrainEnable;
	}
	public void setFastRetrainEnable(String cpe_fast_retrain_enable) {
		this.fastRetrainEnable = cpe_fast_retrain_enable;
	}
	
	public String bitSwap;
	/**
	 * @hibernate.property column = "cpe_bit_swap" type="string"
	 */
	public String getBitSwap() {
		return bitSwap;
	}
	public void setBitSwap(String cpe_bit_swap) {
		this.bitSwap = cpe_bit_swap;
	}
	
	public String usBitSwap;
	/**
	 * @hibernate.property column = "cpe_us_bit_swap" type="string"
	 */	
	public String getUsBitSwap() {
		return usBitSwap;
	}
	public void setUsBitSwap(String cpe_us_bit_swap) {
		this.usBitSwap = cpe_us_bit_swap;
	}
	
	public String codingGain;
	/**
	 * @hibernate.property column = "cpe_coding_gain" type="string"
	 */
	public String getCodingGain() {
		return codingGain;
	}
	public void setCodingGain(String cpe_coding_gain) {
		this.codingGain = cpe_coding_gain;
	}
	
	public String spd1As0Latency;
	/**
	 * @hibernate.property column = "cpe_spd1_as0_latency" type="string"
	 */
	public String getSpd1As0Latency() {
		return spd1As0Latency;
	}
	public void setSpd1As0Latency(String cpe_spd1_as0_latency) {
		this.spd1As0Latency = cpe_spd1_as0_latency;
	}
	
	public String spd1Ls0Latency;
	/**
	 * @hibernate.property column = "cpe_spd1_ls0_latency" type="string"
	 */
	public String getSpd1Ls0Latency() {
		return spd1Ls0Latency;
	}
	public void setSpd1Ls0Latency(String cpe_spd1_ls0_latency) {
		this.spd1Ls0Latency = cpe_spd1_ls0_latency;
	}
	
	public String spd1As0Bytes;
	/**
	 * @hibernate.property column = "cpe_spd1_as0_bytes" type="string"
	 */
	public String getSpd1As0Bytes() {
		return spd1As0Bytes;
	}
	public void setSpd1As0Bytes(String cpe_spd1_as0_bytes) {
		this.spd1As0Bytes = cpe_spd1_as0_bytes;
	}
	
	public String spd1Ls0Bytes;
	/**
	 * @hibernate.property column = "cpe_spd1_ls0_bytes" type="string"
	 */
	public String getSpd1Ls0Bytes() {
		return spd1Ls0Bytes;
	}
	public void setSpd1Ls0Bytes(String cpe_spd1_ls0_bytes) {
		this.spd1Ls0Bytes = cpe_spd1_ls0_bytes;
	}
	
	public String spd4As0Bytes;
	/**
	 * @hibernate.property column = "cpe_spd4_as0_bytes" type="string"
	 */
	public String getSpd4As0Bytes() {
		return spd4As0Bytes;
	}
	public void setSpd4As0Bytes(String cpe_spd4_as0_bytes) {
		this.spd4As0Bytes = cpe_spd4_as0_bytes;
	}
	
	public String spd4Ls0Bytes;
	/**
	 * @hibernate.property column = "cpe_spd4_ls0_bytes" type="string"
	 */	
	public String getSpd4Ls0Bytes() {
		return spd4Ls0Bytes;
	}
	public void setSpd4Ls0Bytes(String cpe_spd4_ls0_bytes) {
		this.spd4Ls0Bytes = cpe_spd4_ls0_bytes;
	}
	
	public String negTime = "300";
	/**
	 * @hibernate.property column = "cpe_neg_time" type="string"
	 */	
	public String getNegTime() {
		return negTime;
	}
	public void setNegTime(String cpe_neg_time) {
		this.negTime = cpe_neg_time;
	}
	
	private void inicializarConfiguracion(){
		setCondiciones(crearCondiciones());
		setNombres(crearNombres());
		getValues();
		
		HashMap relacionVbles = new HashMap();
		
		
		relacionVbles.put("std","STD");
		relacionVbles.put("txPwrAtt","TX_PWR_ATT");
		relacionVbles.put("upMargin","UP_MARGIN");
		relacionVbles.put("dwnMargin","DWN_MARGIN");
		relacionVbles.put("ntr","NTR");
		relacionVbles.put("pmode","PMODE");
		relacionVbles.put("codingGain","CODING_GAIN");
		relacionVbles.put("toneOrdering","TONE_ORDERING");
		relacionVbles.put("escapeFastRetrain","ESCAPE_FAST_RETRAIN");
		relacionVbles.put("fastRetrainEnable","FAST_RETRAIN_ENABLE");
		relacionVbles.put("bitSwap","BIT_SWAP");
		relacionVbles.put("usBitSwap","US_BIT_SWAP");
		relacionVbles.put("spd1As0Latency","SPD1_AS0_LATENCY");
		relacionVbles.put("spd1Ls0Latency","SPD1_LS0_LATENCY");
		relacionVbles.put("spd1As0Bytes","SPD1_AS0_BYTES");
		relacionVbles.put("spd1Ls0Bytes","SPD1_LS0_BYTES");
		relacionVbles.put("spd4As0Bytes","SPD4_AS0_BYTES");
		relacionVbles.put("spd4Ls0Bytes","SPD4_LS0_BYTES");
		relacionVbles.put("negTime","NEG_TIME");

		setRelacionNombresVariables(relacionVbles);
	}

	private HashMap crearCondiciones(){
		
		HashMap cond = new HashMap();
		cond.put("std","[COMBO]T1_413,G_DMT,MULTIMODE,GLITE");
		cond.put("txPwrAtt","[COMBO]0,1,2,3,4,5,6,7,8,9,10,11,12");
		cond.put("upMargin","[COMBO]0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
		cond.put("dwnMargin","[COMBO]0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
		cond.put("ntr","[COMBO]ENABLE,DISABLE");
		cond.put("pmode","[COMBO]ENABLE,DISABLE");
		cond.put("codingGain","[COMBO]AUTO,0,1,2,3,4,5,6,7");
		cond.put("toneOrdering","[COMBO]ENABLE,DISABLE");
		cond.put("escapeFastRetrain","[COMBO]ENABLE,DISABLE");
		cond.put("fastRetrainEnable","[COMBO]ENABLE,DISABLE");
		cond.put("bitSwap","[COMBO]ENABLE,DISABLE");
		cond.put("usBitSwap","[COMBO]ENABLE,DISABLE");
		cond.put("spd1As0Latency","[COMBO]FAST,INTERLEAVE");
		cond.put("spd1Ls0Latency","[COMBO]FAST,INTERLEAVE");
		cond.put("spd1As0Bytes","[RANGE]1,254");
		cond.put("spd1Ls0Bytes","[RANGE]1,32");
		cond.put("spd4As0Bytes","[RANGE]1,254");
		cond.put("spd4Ls0Bytes","[RANGE]1,32");
		cond.put("negTime","[RANGE]1,3600");
		return cond;
	}
	
	private HashMap crearNombres(){
		
		HashMap names = new HashMap();
		names.put("std","Standard");
		names.put("txPwrAtt","Tx Power Attenuation");
		names.put("upMargin","Upstream Noise Margin");
		names.put("dwnMargin","Downstream Noise Margin");
		names.put("ntr","Network Timing Reference");
		names.put("pmode","Use Upstream Pilot tone");
		names.put("codingGain","Coding Gain");
		names.put("toneOrdering","Tone Ordering");
		names.put("escapeFastRetrain","Directly to fast");
		names.put("fastRetrainEnable","Enables FastRetrain for G.Lite");
		names.put("bitSwap","Bit Swapping");
		names.put("usBitSwap","Upstream Bit Swapping");
		names.put("spd1As0Latency","Fast or Interleave operation (as0)");
		names.put("spd1Ls0Latency","Fast or Interleave operation (ls0)");
		names.put("spd1As0Bytes","Max down stream speed");
		names.put("spd1Ls0Bytes","Max up stream speed");
		names.put("spd4As0Bytes","Min down stream speed");
		names.put("spd4Ls0Bytes","Min up stream speed");
		names.put("negTime","Negotiation time with ATU-R");
		return names;
	}

	
}
