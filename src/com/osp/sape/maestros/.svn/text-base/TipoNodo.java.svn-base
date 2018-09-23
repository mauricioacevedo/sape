/*
 * Created on Jan 14, 2005
 */
package com.osp.sape.maestros;

/**
 * @hibernate.class table="tiponodo"  * @author Andres Aristizabal */
public class TipoNodo {

	private String estado;
	
	
    /**
     *  
     * @uml.property name="id"
     */
    private int id;

    /**
     * @hibernate.id type = "int" column = "idtiponodo" unsaved-value = "-1" generator-class = "sequence"
     * @hibernate.generator-param name = "sequence" value = "idtiponodo" 
     * @uml.property name="id"
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @uml.property name="id"
     * 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @uml.property name="site"
     */
    private String site;

    /**
     * @hibernate.property column = "site" type = "string" 
     * @uml.property name="site"
     */
    public String getSite() {
        return site;
    }

    /**
     *  
     * @uml.property name="site"
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     *  
     * @uml.property name="tipoCabeza"
     */
    private CabezaPrueba tipoCabeza;

    /**
     * @hibernate.many-to-one column="tipocabeza" class="com.osp.sape.maestros.CabezaPrueba" update="false" insert="false" 
     * @uml.property name="tipoCabeza"
     */
    public CabezaPrueba getTipoCabeza() {
        return tipoCabeza;
    }

    /**
     *  
     * @uml.property name="tipoCabeza"
     */
    public void setTipoCabeza(CabezaPrueba tipoCabeza) {
        this.tipoCabeza = tipoCabeza;
    }
        

    /**
     *  
     * @uml.property name="ipServidor"
     * 
     */
    private String ipServidor;
    /**
     * @hibernate.property column = "ipservidor" type = "string"
     * @uml.property name="ipServidor"
     *  
     * */
    
    public String getIpServidor() {
        return ipServidor;
    }

    /**
     *  
     * @uml.property name="ipServidor"
     */
    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    
    
    /**
     *  
     * @uml.property name="ipCabeza"
     */
    private String ipCabeza;

    /**
     * @hibernate.property column = "ipcabeza" type = "string"
     * @uml.property name="ipCabeza"
     */
    public String getIpCabeza() {
        return ipCabeza;
    }

    /**
     *  
     * @uml.property name="ipCabeza"
     */
    public void setIpCabeza(String ipCabeza) {
        this.ipCabeza = ipCabeza;
    }


    /**
     * 
     * @uml.property name="puertoServidor"
     */
    
    private String puertoServidor; 
    /**
     * @hibernate.property column = "puertoservidor" type = "string" 
     * @uml.property name="puertoServidor"
     */
    public String getPuertoServidor() {
        return puertoServidor;
    }

    /**
     *  
     * @uml.property name="puertoServidor"
     */
    public void setPuertoServidor(String puertoServidor) {
        this.puertoServidor = puertoServidor;
    }

    
    /**
     *  
     * @uml.property name="puertoCabeza"
     */
    private String puertoCabeza;

    /**
     * @hibernate.property column = "puertocabeza" type = "string"  
     * @uml.property name="puertoCabeza"
     */
    public String getPuertoCabeza() {
        return puertoCabeza;
    }

    /**
     *  
     * @uml.property name="puertoCabeza"
     */
    public void setPuertoCabeza(String puertoCabeza) {
        this.puertoCabeza = puertoCabeza;
    }

    /**
     * @hibernate.property name="estado" type="string"
     * @return
     */
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
    /**
     *  
     * @uml.property name="ipEsclavo"
     */
	private String ipEsclavo;
    /**
     * @hibernate.property column = "ipesclavo" type = "string"  
     * @uml.property name="ipEsclavo"
     */
	public String getIpEsclavo() {
		return ipEsclavo;
	}
	/**
     *  
     * @uml.property name="ipEsclavo"
     */
	public void setIpEsclavo(String ipEsclavo) {
		this.ipEsclavo = ipEsclavo;
	}
    /**
     *  
     * @uml.property name="puertoEsclavo"
     */
	private String puertoEsclavo;
    /**
     * @hibernate.property column = "puertoesclavo" type = "string"  
     * @uml.property name="puertoEsclavo"
     */
	public String getPuertoEsclavo() {
		return puertoEsclavo;
	}
    /**
     *  
     * @uml.property name="puertoEsclavo"
     */
	public void setPuertoEsclavo(String puertoEsclavo) {
		this.puertoEsclavo = puertoEsclavo;
	}
	
    /**
     *  
     * @uml.property name="usuarioUltimoCambio"
     */
	
	private String usuarioUltimoCambio;
    /**
     * @hibernate.property column = "usuarioultimocambio" type = "string"  
     * @uml.property name="usuarioUltimoCambio"
     */
	public String getUsuarioUltimoCambio(){
		return usuarioUltimoCambio;
	}
    /**
     *  
     * @uml.property name="usuarioUltimoCambio"
     */
	
	public void setUsuarioUltimoCambio(String usuarioUltimoCambio){
		this.usuarioUltimoCambio = usuarioUltimoCambio;
	}
	
	private String tipoCentral;
    /**
     * @hibernate.property column = "tipocentral" type = "string"  
     */
	public String getTipoCentral() {
		return tipoCentral;
	}

	public void setTipoCentral(String tipoCentral) {
		this.tipoCentral = tipoCentral;
	}

	protected String paramString() {
		return "id=" + id + ", site=" + site + ", tipoCabeza=" + tipoCabeza + 
			", estado=" + estado + ", ipCabeza=" + ipCabeza + ", puertoCabeza=" + puertoCabeza + 
			", ipEsclavo=" + ipEsclavo + ", puertoEsclavo=" + puertoEsclavo + 
			", ipServidor=" + ipServidor + ", puertoServidor=" + puertoServidor + ", tipoCentral=" + tipoCentral;
	}
	
	public String toString() {
		return getClass().getName() + ": [" + paramString() + "]";
	}
	

}
