
package com.osp.sape.maestros;
/**
 * @author Develop Team XpLoRa
 */
public class NaturalezaReclamo extends CodigosFallaNaturaleza{
	
	private String fallaId;
	private String nombre;
	
	public NaturalezaReclamo(String fallaId,String nombre){
		
		this.fallaId = fallaId;
		this.nombre = nombre;
	}
	
	public String getFallaId() {
		return fallaId;
	}
	
	public void setFallaId(String fallaId) {
		this.fallaId = fallaId;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    protected String paramString() {
    	return "fallaId=" + fallaId + "; nombre=" + nombre;
    }
    
    public String toString() {
    	return getClass().getName() + "[" + paramString() + "]";
    }
}
