/*
 * Created on Apr 6, 2005
 */
package com.osp.sape.reportes;

/**
 * 
 * @author Andres Aristizabal
 */
public class SerieReportes {
    
    public String getCentral() {
        return central;
    }
    public void setCentral(String central) {
        this.central = central;
    }
    public String getFINAL() {
        return FINAL;
    }
    public void setFINAL(String final1) {
        FINAL = final1;
    }
    public String getInicial() {
        return inicial;
    }
    public void setInicial(String inicial) {
        this.inicial = inicial;
    }
    public String getIpCabeza() {
        return ipCabeza;
    }
    public void setIpCabeza(String ipCabeza) {
        this.ipCabeza = ipCabeza;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPuertocabeza() {
        return puertocabeza;
    }
    public void setPuertocabeza(String puertocabeza) {
        this.puertocabeza = puertocabeza;
    }
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }
    public String getTipoCentral() {
        return tipoCentral;
    }
    public void setTipoCentral(String tipoCentral) {
        this.tipoCentral = tipoCentral;
    }
    private String inicial;
    private String FINAL;
    private String ipCabeza;
    private String puertocabeza;
    private String central;
    private String tipoCentral;
    private String site;
    private String nombre;
    private String proveedor;
    
    public String getProveedor() {
        return proveedor;
    }
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
