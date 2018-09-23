package com.osp.sape.servicios;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.CodigosVerDAO;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.maestros.CodigoVer;


public class Secuencia {

	private int numSec	  ;
	private String codv   ;
	private String cola   ;
	private String codigo ;
	private String desc	  ; 	// esta se obtendra directamente de la BD con el codv
	private boolean condicion; 	// Esta variable sera false si es una secuencia normal,
								//o sera true si es una condicion compuesta. 
	
	
	/*
	 * Variables para la condicion compuesta.
	 */
	
	private String clienteRepetido;
	private String siCola;
	private String siCodigo;
	private String noCodigo;
	private String noUsuario;
	
	
	/*
	 * Variables para la condicion de la COLA GRANC.
	 */
	
	private String colaDia;
	private String codigoDia;
	private String colaNoche;
	private String codigoNoche;
	private String horaGrancNoche;

	public Secuencia(int numSec,String colaDia, String codigoDia, String colaNoche,String codigoNoche, String horaGrancNoche){
	
		this.colaDia		= colaDia;
		this.codigoDia		= codigoDia;
		this.colaNoche		= colaNoche;
		this.codigoNoche	= codigoNoche;
		this.horaGrancNoche	= horaGrancNoche;
		this.numSec 		= numSec; 
	}
	
		
	public Secuencia(String codv, String cola, String codigo, int numSec){
		
		this.codv   = codv;
    	this.cola   = cola;
    	this.codigo = codigo;
    	this.numSec = numSec;
    	this.condicion = false;
    	
    	obtenerDescripcion();
	}
	
	public Secuencia(String clienteRepetido, String siCola, String siCodigo, String noCodigo, String noUsuario, int numSec){
		
	   	this.clienteRepetido = clienteRepetido;
    	this.siCola = siCola;
    	this.siCodigo = siCodigo;
    	this.noCodigo = noCodigo;
    	this.noUsuario = noUsuario;
    	this.numSec = numSec;
    	this.condicion = true;
	}
	
	
	private void obtenerDescripcion(){
		
	    CodigosVerDAO codvDAO=DAOFactoryImpl.getInstance().getCodigosVerDAO();
	    CodigoVer cod = null;
	    
	    if(codv == null || codv.equals(""))
	    	return;
	    
	    
	    try {
	    	
			cod =codvDAO.getCodigoVer(codv);
		} catch (SapeDataException e) {
			System.out.println("OCURRIO UN ERROR MIENTAS SE OBTENIA CODIGO VER: \n"+e.toString());
			return;
		}
		
		desc=cod.getClasificacion();
	}
	
	public String toString(){
		
		String cadena = "";
		cadena  = "codv="+codv+";";
    	cadena += "cola="+cola+";";
    	cadena += "codigo="+codigo+";";
    	
//		if (condicion){
//			cadena +=  "clienteRepetido="+clienteRepetido+";";
//			cadena +=  "siCola="+siCola+";";
//			cadena +=  "siCodigo="+siCodigo+";";
//			cadena +=  "noCodigo="+noCodigo+";";
//			cadena +=  "noUsuario="+noUsuario+";";
//		}
		
		return cadena;
	}

	public String getClienteRepetido() {
		return clienteRepetido;
	}

	public void setClienteRepetido(String clienteRepetido) {
		this.clienteRepetido = clienteRepetido;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodv() {
		return codv;
	}

	public void setCodv(String codv) {
		this.codv = codv;
	}

	public String getCola() {
		return cola;
	}

	public void setCola(String cola) {
		this.cola = cola;
	}

	public boolean isCondicion() {
		return condicion;
	}

	public void setCondicion(boolean condicion) {
		this.condicion = condicion;
	}

	public String getNoCodigo() {
		return noCodigo;
	}

	public void setNoCodigo(String noCodigo) {
		this.noCodigo = noCodigo;
	}

	public String getNoUsuario() {
		return noUsuario;
	}

	public void setNoUsuario(String noUsuario) {
		this.noUsuario = noUsuario;
	}

	public int getNumSec() {
		return numSec;
	}

	public void setNumSec(int numSec) {
		this.numSec = numSec;
	}

	public String getSiCodigo() {
		return siCodigo;
	}

	public void setSiCodigo(String siCodigo) {
		this.siCodigo = siCodigo;
	}

	public String getSiCola() {
		return siCola;
	}

	public void setSiCola(String siCola) {
		this.siCola = siCola;
	}


	public String getCodigoDia() {
		return codigoDia;
	}


	public void setCodigoDia(String codigoDia) {
		this.codigoDia = codigoDia;
	}


	public String getCodigoNoche() {
		return codigoNoche;
	}


	public void setCodigoNoche(String codigoNoche) {
		this.codigoNoche = codigoNoche;
	}


	public String getColaDia() {
		return colaDia;
	}


	public void setColaDia(String colaDia) {
		this.colaDia = colaDia;
	}


	public String getColaNoche() {
		return colaNoche;
	}


	public void setColaNoche(String colaNoche) {
		this.colaNoche = colaNoche;
	}


	public String getHoraGrancNoche() {
		return horaGrancNoche;
	}


	public void setHoraGrancNoche(String horaGrancNoche) {
		this.horaGrancNoche = horaGrancNoche;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}