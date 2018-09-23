/*
 * Created on 16-abr-2005
 */
package com.osp.sape.servicios;

/**
 * Clase encargada de guardar una cola de EPM, por ejemplo ANALI, REPLI.
 * @author Andres
 */
public class Cola {

    private String nombre;
    private String descripcion;
    
   public Cola() {
       this(null, "");
   } 
    
    /**
     * @param nombre
     * @param descripcion
     */
    public Cola(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public String toString() {
        return getClass().getName() + "[nombre=" + nombre + ";descripcion=" + descripcion + "]";
    }
}
