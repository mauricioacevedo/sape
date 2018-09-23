package com.osp.sape.maestros;

import java.util.List;

/**
 * Clase que representa una prueba de categorias en el sape.
 * @hibernate.class table = "categorias"
 * @author Andres Aristizabal
 */
public class Categorias {


    /**
     *  
     * @uml.property name="id"
     */
    private int id;
    /**
     * @hibernate.id  column = "id" type = "int" generator-class = "assigned"
     * @uml.property name="id"
     */
    public int getId() {
        return id;
    }

    /**
     *  
     * @uml.property name="id"
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  
     * @uml.property name="codigoVer"
     */
    private String codigoVer;

    /**
     * @hibernate.property column = "codv" type = "string"
     * @uml.property name="codigoVer"
     */
    public String getCodigoVer() {
        return codigoVer;
    }

    /**
     *  
     * @uml.property name="codigoVer"
     */
    public void setCodigoVer(String codigoVer) {
        this.codigoVer = codigoVer;
    }

    /**
     *  
     * @uml.property name="categorias"
     */
    private String categorias;

    /**
     * @hibernate.property column = "categorias" type = "string" 
     * @uml.property name="categorias"
     */
    public String getCategorias() {
        return categorias;
    }

    /**
     *  
     * @uml.property name="categorias"
     */
    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    /**
     *  
     * @uml.property name="estado"
     */
    private String estado;

    /**
     * @hibernate.property column = "estado" type = "string" 
     * @uml.property name="estado"
     */
    public String getEstado() {
        return estado;
    }

    /**
     *  
     * @uml.property name="estado"
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    private List categoriasList;
	
    public List getCategoriasList() {
		return categoriasList;
	}

	public void setCategoriasList(List categoriasList) {
		this.categoriasList = categoriasList;
	}
    
    protected String paramString() {
    	return "id=" + id + "; categorias=" + categorias + "; codigoVer=" + codigoVer + "; estado=" + estado;
    }
	
    public String toString() {
    	return getClass().getName() + "[" + paramString() + "]";
    }
    
   }
