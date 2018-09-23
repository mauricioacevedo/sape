/*
 * Created on 17-abr-2005
 */
package com.osp.sape.servicios;

/**
 * Clase que representa una Alarma para avisarle a alguna persona.
 * @author Andres
 */
public class Alarma {

    private String nombreCola;
    /** El tama�o limite de la cola para activar la alarma */
    private int limite;
    /** es el nombre de la persona a la que se de debe avisar */
    private String avisar;
    
    private String mensaje;
    
    private MedioInformar medio;
    /** Es el tiempo a recordarle a la persona dado en minutos */
    private int recordar = 60;
    
    public Alarma(String ncola, int limit,String avis,String mensaj,MedioInformar med, int rec){
        nombreCola = ncola;
        limite=limit;
        avisar=avis;
        mensaje=mensaj;
        medio = med;
        recordar=rec;
    }
    
    public String getAvisar() {
        return avisar;
    }
    public void setAvisar(String avisar) {
        this.avisar = avisar;
    }
    public int getLimite() {
        return limite;
    }
    
    public void setLimite(int limite) {
        this.limite = limite;
    }
    
    public MedioInformar getMedio() {
        return medio;
    }
    
    public void setMedio(MedioInformar medio) {
        this.medio = medio;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getNombreCola() {
        return nombreCola;
    }
    
    public void setNombreCola(String nombreCola) {
        this.nombreCola = nombreCola;
    }
    
    public int getRecordar() {
        return recordar;
    }
    
    public void setRecordar(int recordar) {
        this.recordar = recordar;
    }
}
