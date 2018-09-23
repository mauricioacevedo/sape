package com.gc.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/*
 * Created on 12/01/2004
 *
 */

/**
 * Clase que se encarga de tener en un Hasmap todas las acciones 
 * encontradas en un archivo de configuracion XML.
 * @author Andres Aristizabal
 */
public class Acciones {
	
	private Logger 	logs = LogManager.getLogger(getClass());
	private HashMap listaAcciones = null;
	private String indexPage;
	private String restrictedPage;
	private String loginPage;
	private String rolDefecto;
	private HashMap listaPlantillas;
	private boolean debug=logs.isDebugEnabled();

	private static Acciones _instancia = null; //La instancia del singleton
	
	public static void initialize(String archAcciones) {
		_instancia = new Acciones(archAcciones);
	}
	
	public static Acciones getInstance() {
		if (_instancia == null) {
			throw new RuntimeException("Falta inicializar la clase.");
		}
		return _instancia;
	}
	
	private Acciones(String archAcciones) {	    
		
		File archivo = new File(archAcciones);
		if (!archivo.exists()) {
			if(debug) logs.debug("El Archivo '" + archivo.getPath() + "' no existe");
			//System.out.println("El Archivo '" + archivo.getPath() + "' no existe");
			return;
		}
		if (!archivo.canRead()) {
			if(debug) logs.debug("El archivo no se puede leer");
			//System.out.println("El archivo se se puede leer");
			return;
		}
		Document document = null;
		SAXBuilder builder = new SAXBuilder();
		try {
            document = builder.build(archivo);
        } catch (JDOMException e1) {
            logs.error(e1);
            return;
        } catch (IOException e1) {
            logs.error(e1);
            return;
        } 
		
        Element root = document.getRootElement();
		

		indexPage = root.getChild("index-page").getAttributeValue("destiny");
		restrictedPage = root.getChild("restricted-page").getAttributeValue("destiny");
		loginPage = root.getChild("login-page").getAttributeValue("destiny");
		rolDefecto = root.getChild("default-rol").getAttributeValue("value");

    		//Leo las acciones 
		Element hijo = root.getChild("actions");
		List lista = hijo.getChildren("action");
		int cantAcciones = lista.size();
		listaAcciones = new HashMap(cantAcciones);
		for (Iterator iter = lista.iterator(); iter.hasNext() ;) {
            Element element = (Element) iter.next();
            String clave = element.getAttributeValue("name");
            String destino = element.getAttributeValue("destiny");
            	//Cargo la lista de roles que tiene una accion.
            	//Si el tamano es 0, le asigno el rol por defecto.
            List roles = element.getChildren("role");
//            if (logs.isDebugEnabled()) 
//            	logs.debug(4, "roles es: " + roles + " es nulo: " + (roles == null) + " size: " + roles.size());
            String perfiles[] = null;
            if (roles.size() == 0) {
                perfiles = new String[] { rolDefecto };
            } else {
                perfiles = new String[roles.size()];
                for (int i = 0; i < perfiles.length; i++) {
                    Element rol = (Element) roles.get(i);
                    perfiles[i] = rol.getText();
                }                
            }
			Accion accion = new Accion(destino, perfiles);
			listaAcciones.put(clave, accion);
			if (debug) logs.debug("Carga Accion: " + clave + "(" + accion + ")");
        }
				//Leo las plantillas de la aplicacion
		hijo = root.getChild("templates");
		lista = hijo.getChildren("template");
		listaPlantillas = new HashMap(lista.size());
		for (Iterator iter = lista.iterator(); iter.hasNext() ;) {
            Element element = (Element) iter.next();
            String nombre = element.getAttributeValue("name");
            String plantilla = element.getValue();
            
			listaPlantillas.put(nombre, plantilla);
			if (logs.isDebugEnabled()) logs.debug("Carga Plantilla: (" + nombre + "," + plantilla + ")");
        }

		
			//Libero todos los recursos.
		archivo = null;
		document = null;
	}	//fin del constructor
	

	public synchronized Accion getAccion(String clave) {
		return (Accion)listaAcciones.get(clave);
	}
	
	
	public String getIndexPage() {
	    return indexPage;
	}
	
	public String getRestrictedPage() {
	    return restrictedPage;
	}
	

    public String getRolDefecto() {
        return rolDefecto;
    }

    public void setRolDefecto(String rolDefecto) {
        this.rolDefecto = rolDefecto;
    }


    public String getLoginPage() {
        return loginPage;
    }
    
    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
  
    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }
 
    public void setRestrictedPage(String restrictedPage) {
        this.restrictedPage = restrictedPage;
    }
    
    
    /**
     * Devuelve la pagina de destino para hacer el fordwar en un servlet.
     * @param nombre
     * @return
     */
    public String getTemplate(String nombre) {
    	if (logs.isDebugEnabled()) logs.debug("getTemplate: " + nombre);
    	return (String)listaPlantillas.get(nombre);
    }
}
