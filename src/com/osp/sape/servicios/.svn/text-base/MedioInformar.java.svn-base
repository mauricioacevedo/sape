/*
 * Created on 17-abr-2005
 */
package com.osp.sape.servicios;

/**
 * Clase que representa un medio para informar a una persona cuando se presente 
 * una alarma, por ejemplo, correo, beeper
 * @author Andres
 */
 
public abstract class MedioInformar {
// TODO ANALIZAR QUE TAN COMVENIENTE ES TENER UNA CLASE POR CADA MEDIO!!!!!!!
    private String medio;
    
    protected String paramString() {
        return "medio=" + medio;
    }

    public String toString() {
    	return getClass().getName() + "[" + paramString() + "]";
    }

    
    public String getMedio() {
        return medio;
    }
    
    public void setMedio(String medio) {
        this.medio = medio;
    }
}
