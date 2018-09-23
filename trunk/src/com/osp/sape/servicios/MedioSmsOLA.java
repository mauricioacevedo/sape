package com.osp.sape.servicios;

/**
 * 
 * @author Andres Aristizabal
 */
public class MedioSmsOLA extends MedioInformar{


    private String telefono;
    
    public MedioSmsOLA(String tel) {

        setMedio("smsOla");
        telefono=tel;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String tel) {
        this.telefono = tel;
    }
    
    protected String paramString() {
        return super.paramString() + ";telefono=" + telefono;
    }
}
