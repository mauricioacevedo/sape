/*
 * Created on 17-abr-2005
 */
package com.osp.sape.servicios;

/**
 *
 * @author Andres
 */
public class MedioCorreo extends MedioInformar {

    private String direccion;
    
    public MedioCorreo(String dir){
        setMedio("correo");
        direccion=dir;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    protected String paramString() {
        return super.paramString() + ";direccion=" + direccion;
    }
}
