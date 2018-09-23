package com.osp.sape.maestros.siplexpro;

/**
 * @hibernate.class table="siplexpro_ping"
 */
public class PruebaPing {

	private int idEvento;
	private String connect; 
	private String loginId; 
	private String ipAssigned; 
	private String gwAssigned; 
	private String priDns; 
	private String secDns; 
	private String login; 
	private String ipDestiny; 
	private int packetsSent; 
	private String ping; 
	private int packetsRec; 
	private String minRoundTrip; 
	private String maxRoundTrip; 
	private String avgRoundTrip; 
	private String codv; 
	private String estado;
	
	
	/**
	 * @hibernate.property column = "avgroundtrip" type="string"
	 */
	public String getAvgRoundTrip() {
		return avgRoundTrip;
	}
	
	public void setAvgRoundTrip(String avgroundtrip) {
		this.avgRoundTrip = avgroundtrip;
	}
	
	/**
	 * @hibernate.property column = "codv" type="string"
	 */
	public String getCodv() {
		return codv;
	}
	public void setCodv(String codv) {
		this.codv = codv;
	}
	
	/**
	 * @hibernate.property column = "connect" type="string"
	 */
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	
	/**
	 * @hibernate.property column = "estado" type="string"
	 */
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * @hibernate.property column = "gwassigned" type="string"
	 */
	public String getGwAssigned() {
		return gwAssigned;
	}
	public void setGwAssigned(String gwassigned) {
		this.gwAssigned = gwassigned;
	}
	
	/**
	 * @hibernate.id column = "id" type="int" generator-class="assigned" 
	 */
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	
	/**
	 * @hibernate.property column = "ipassigned" type="string"
	 */
	public String getIpAssigned() {
		return ipAssigned;
	}
	public void setIpAssigned(String ipassigned) {
		this.ipAssigned = ipassigned;
	}

	/**
	 * @hibernate.property column = "ipdestiny" type="string"
	 */
	public String getIpDestiny() {
		return ipDestiny;
	}
	public void setIpDestiny(String ipdestiny) {
		this.ipDestiny = ipdestiny;
	}
	
	/**
	 * @hibernate.property column = "login" type="string"
	 */
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * @hibernate.property column = "loginid" type="string"
	 */
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginid) {
		this.loginId = loginid;
	}

	/**
	 * @hibernate.property column = "maxroundtrip" type="string"
	 */
	public String getMaxRoundTrip() {
		return maxRoundTrip;
	}
	
	public void setMaxRoundTrip(String maxroundtrip) {
		this.maxRoundTrip = maxroundtrip;
	}
	
	/**
	 * @hibernate.property column = "minroundtrip" type="string"
	 */
	public String getMinRoundTrip() {
		return minRoundTrip;
	}
	public void setMinRoundTrip(String minroundtrip) {
		this.minRoundTrip = minroundtrip;
	}
	
	/**
	 * @hibernate.property column = "packetsrec" type="int"
	 */
	public int getPacketsRec() {
		return packetsRec;
	}
	public void setPacketsRec(int packetsrec) {
		this.packetsRec = packetsrec;
	}
	
	/**
	 * @hibernate.property column = "packetssent" type="int"
	 */
	public int getPacketsSent() {
		return packetsSent;
	}
	public void setPacketsSent(int packetssent) {
		this.packetsSent = packetssent;
	}
	
	/**
	 * @hibernate.property column = "ping" type="string"
	 */
	public String getPing() {
		return ping;
	}
	public void setPing(String ping) {
		this.ping = ping;
	}
	
	/**
	 * @hibernate.property column = "pridns" type="string"
	 */
	public String getPriDns() {
		return priDns;
	}
	public void setPriDns(String pridns) {
		this.priDns = pridns;
	}
	
	/**
	 * @hibernate.property column = "secdns" type="string"
	 */
	public String getSecDns() {
		return secDns;
	}
	public void setSecDns(String secdns) {
		this.secDns = secdns;
	} 
	
	
	
	
}
