package com.osp.sape.utils;

public class Firmware {

	private String site;
	private String archivo;
	private String ipCabeza;
	private int puertoCabeza;
	private long fechaInicial; 
	private String version;
	private String binFile;
	private int paquetes;
	private int tamano;
	private String ipSocket;
	private int puertoSocket;
	
	public Firmware() {
		
	}
	
	/**
	 * Asigna la fechaInicial una vez se instancia la clase
	 * @param ipCabeza
	 * @param puertoCabeza
	 * @param archivo
	 */
	public Firmware(String site, String ipCabeza, int puertoCabeza, String archivo) {
		this.site = site;
		this.archivo = archivo;
		this.ipCabeza = ipCabeza;
		this.puertoCabeza = puertoCabeza;
		this.fechaInicial = System.currentTimeMillis();
	}

	public String getArchivo() {
		return archivo;
	}
	
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	public String getBinFile() {
		return binFile;
	}
	
	public void setBinFile(String binFile) {
		this.binFile = binFile;
	}
	
	public long getFechaInicial() {
		return fechaInicial;
	}
	
	public void setFechaInicial(long fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	
	public String getIpCabeza() {
		return ipCabeza;
	}
	
	public void setIpCabeza(String ipCabeza) {
		this.ipCabeza = ipCabeza;
	}
	
	public String getIpSocket() {
		return ipSocket;
	}
	
	public void setIpSocket(String ipSocket) {
		this.ipSocket = ipSocket;
	}
	
	public int getPaquetes() {
		return paquetes;
	}
	
	public void setPaquetes(int paquetes) {
		this.paquetes = paquetes;
	}
	
	public int getPuertoCabeza() {
		return puertoCabeza;
	}
	
	public void setPuertoCabeza(int puertoCabeza) {
		this.puertoCabeza = puertoCabeza;
	}
	
	public int getPuertoSocket() {
		return puertoSocket;
	}
	
	public void setPuertoSocket(int puertoSocket) {
		this.puertoSocket = puertoSocket;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
		
	private String paramString() {
		return "archivo=" + archivo + ";ipCabeza=" + ipCabeza + ";puertoCabeza=" + puertoCabeza + 
				";fechaInicial=" + fechaInicial + ";version=" + version + ";binFile=" + binFile + 
				";paquetes=" + paquetes + ";tamano=" + tamano + ";ipSocket=" + ipSocket + ";puertoSocket=" + puertoSocket;
	}
	
	public String toString() {
		return getClass().getName() + "[" + paramString() + "]";
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
}

 