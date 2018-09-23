/*
 * Created on Jan 14, 2005
 */
package com.osp.sape.maestros;

/**
 * @hibernate.class table="series" * @author Andres Aristizabal */
public class Serie {


    /**
     *  
     * @uml.property name="id"
     */
    private int id;

    /**
     * @hibernate.id type = "int" column = "id" unsaved-value = "-1" generator-class = "sequence"
     * @hibernate.generator-param name = "sequence" value = "idseries"  
     * @uml.property name="id"
     */
    public int getId() {
        return id;
    }

    /**
     *  
     * @uml.property name="id"
     */
    public void setId(int idd) {
        this.id = idd;
    }

    /**
     *  
     * @uml.property name="serieInicial"
     */
    private long serieInicial;

    /**
     * @hibernate.property column = "inicial" type = "long" 
     * @uml.property name="serieInicial"
     */ 
    public long getSerieInicial() {
        return serieInicial;
    }

    /**
     *  
     * @uml.property name="serieInicial"
     */
    public void setSerieInicial(long serieInicial) {
        this.serieInicial = serieInicial;
    }
    
    
    /**
     *  
     * @uml.property name="serieFinal"
     */
    private long serieFinal;

    /**
     * @hibernate.property column = "final" type = "long"
     * @uml.property name="serieFinal"
     */
    public long getSerieFinal() {
        return serieFinal;
    }

    /**
     *  
     * @uml.property name="serieFinal"
     */
    public void setSerieFinal(long serieFinal) {
        this.serieFinal = serieFinal;
    }

    /**
     *  
     * @uml.property name="central"
     */
    private String central;

    /**
     * @hibernate.property column = "central" type = "string"
     * @uml.property name="central"
     */
    public String getCentral() {
        return central;
    }

    /**
     *  
     * @uml.property name="central"
     */
    public void setCentral(String central) {
        this.central = central;
    }

    /**
     *  
     * @uml.property name="tipocentral"
     */
    private String tipocentral;

    /**
     * @hibernate.property column = "tipocentral" type = "string"
     * @uml.property name="tipocentral"
     */
    public String getTipocentral() {
        return tipocentral;
    }

    /**
     *  
     * @uml.property name="tipocentral"
     */
    public void setTipocentral(String tipocentral) {
        this.tipocentral = tipocentral;
    }

    /**
     *  
     * @uml.property name="cabezaId"
     */
    private Integer cabezaId;

    /**
     * @hibernate.property column = "cabezaid" type = "int" 
     * @uml.property name="cabezaId"
     */
    public Integer getCabezaId() {
        return cabezaId;
    }

    /**
     *  
     * @uml.property name="cabezaId"
     */
    public void setCabezaId(Integer cabezaId) {
        this.cabezaId = cabezaId;
    }
    
    /**
     *  
     * @uml.property name="codCentral"
     */
    private Integer codCentral;

    /**
     * @hibernate.property column = "codcentral" type = "int" 
     * @uml.property name="codCentral"
     */
    public Integer getCodCentral() {
        return codCentral;
    }

    /**
     *  
     * @uml.property name="codCentral"
     */
    public void setCodCentral(Integer codcentral2) {
        this.codCentral= codcentral2;
    }
    
    public String toString() {
        return getClass().getName() + "[id=" + id + ";serieInicial=" + serieInicial + 
        		";serieFinal=" + serieFinal + ";tipocentral=" + tipocentral + 
        		";cabezaId=" + cabezaId + ";central=" + central + ";codCentral=" + codCentral + "]";
    }
    
}
