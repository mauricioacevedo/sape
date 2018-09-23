/*
 * Created on Mar 22, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.osp.sape.maestros;

import java.util.ArrayList;
import java.util.List;

public class CodigoFallaCola{
	
	private String cola;
	private List fallasId;
	private List naturalezasId;
	
	public List getNaturalezasId() {
		return naturalezasId;
	}

	public void setNaturalezasId(List naturalezasId) {
		this.naturalezasId = naturalezasId;
	}

	public CodigoFallaCola(List fallasId, String cola, List naturalezas) {
		this.cola = cola;
		this.fallasId = fallasId;
		this.naturalezasId=naturalezas;
		
		if(fallasId == null)
			this.fallasId = new ArrayList();
		
		if(naturalezas==null)
			this.naturalezasId = new ArrayList();
	}

	public String getCola() {
		return cola;
	}

	public void setCola(String cola) {
		this.cola = cola;
	}

	public List getFallasId() {
		return fallasId;
	}

	public void setFallasId(List fallasId) {
		this.fallasId = fallasId;
	}

	public String toStringCodigosFalla(){
		
		String ret = "";
		
		if(fallasId != null){
			int size = fallasId.size();
			
			for(int i=0;i<size;i++){
				ret += (String)fallasId.get(i)+",";
			}
		}
		return ret;
	}
	
	public String toStringNaturalezasReclamo(){
		
		String ret = "";
		
		if(naturalezasId != null){
			int size = naturalezasId.size();
			
			for(int i=0;i<size;i++){
				ret += (String)naturalezasId.get(i)+",";
			}
		}
		return ret;
	}
}
