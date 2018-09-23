/*
 * Created on 17-abr-2005
 */
package com.osp.sape.servicios;

/**
 *
 * @author Andres
 */
public class MedioBeeper extends MedioInformar {

    private String telefono;
    private String codigo;
   
    public MedioBeeper(String tel, String cod){
        setMedio("beeper");
        telefono=tel;
        codigo=cod;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    protected String paramString() {
        return super.paramString() + ";telefono=" + telefono + ";codigo=" + codigo;
    }
}
