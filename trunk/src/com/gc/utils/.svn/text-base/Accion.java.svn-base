package com.gc.utils;

/*
 * Created on 12/01/2004
 *
 */

/**
 * @author Andres Aristizabal
 */
public class Accion {

	private String accion = null;
	private String perfiles[] = null;
	

	public Accion() {
			this(null,null);
	}
	
	public Accion(String accion, String perfiles[]) {
		this.accion = accion;
		this.perfiles = perfiles;
	}
	
	/**
	 * @return
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @return
	 */
	public String[] getPerfiles() {
		return perfiles;
	}

	/**
	 * @param string
	 */
	public void setAccion(String a) {
		accion = a;
	}

	/**
	 * @param string
	 */
	public void setPerfiles(String p[]) {
		perfiles = p;
	}
	
	public boolean containsPerfil(String perfil) {
	    boolean retorno = false;
	    for (int i = 0; i < perfiles.length; i++) {
            if (perfiles[i].equals(perfil)) {
                retorno = true;
                break;
            }
        }
	    return retorno;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer("Accion[accion=");
		buffer.append(accion);
		buffer.append(";perfiles=");
		for (int i = 0; i < perfiles.length; i++) {
            buffer.append(perfiles[i]);
            if (i < perfiles.length - 1) buffer.append(",");
        }
		buffer.append("]");
	    return buffer.toString();
	}


}
