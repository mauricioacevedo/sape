/*
 * Created on Jun 21, 2005
 */
package com.osp.sape.maestros.rutinas;

import java.util.List;

/**
 * 
 * @author Andres Aristizabal y Mauricio Acevedo
 */
//XXX Este bean contiene informacion para las rutinas en la parte de 
//cables y armarios. Contiene una lista con las centrales obtenidas por tecnologia
//y una variable donde se tiene la tecnologia asociada a esta lista!!!!!

public class ReporteRutinasCableArmario {

    /**
     * 
     */
    
    public ReporteRutinasCableArmario(String tec, List centrales) {
        tecnologia=tec;
        listaCentrales=centrales;
    }
    
    public List getListaCentrales() {
        return listaCentrales;
    }
    public void setListaCentrales(List listaCentrales) {
        this.listaCentrales = listaCentrales;
    }
    public String getTecnologia() {
        return tecnologia;
    }
    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }
    private String tecnologia;
    private List listaCentrales;
    public String getArmario() {
        return armario;
    }
    public void setArmario(String armario) {
        this.armario = armario;
    }
    public String getCable() {
        return cable;
    }
    public void setCable(String cable) {
        this.cable = cable;
    }
    public String getCentral() {
        return central;
    }
    public void setCentral(String central) {
        this.central = central;
    }
    private String central;
    private String cable;
    private String armario;
    private List listaHoras;

    public List getListaHoras() {
        return listaHoras;
    }
    public void setListaHoras(List listaHoras) {
        this.listaHoras = listaHoras;
    }
}
