package com.gc.utils;

/*
 * Created on 21/08/2003
 */


import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.gc.data.AplicacionDAO;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.maestros.auditoria.Visita;

/**
 * @author Andres Aristizabal
 *
 * Clase encargada de contar las sesiones Activas y cuales de estas sesiones se
 * encuentran registrados. 
 */
public class GestorSessionListener implements 
					HttpSessionAttributeListener, HttpSessionListener, ServletContextListener {

	private static int sesionesActivas = 0;
	private static int usuariosRegistrados = 0;
	private static int usuariosNoRegistrados = 0;
	private org.apache.log4j.Logger logs;
	private boolean debug;
	private AplicacionDAO aplicacionDAO;
	
	public GestorSessionListener() {
		logs = org.apache.log4j.Logger.getLogger(getClass());
		debug = logs.isDebugEnabled();
		aplicacionDAO = DAOFactoryImpl.getInstance().getAplicacionDAO();
	}

	public void attributeAdded(HttpSessionBindingEvent event) {
		if (debug) logs.debug("Atributo Adicionado: " + event.getName() + ": " + event.getValue());
//		if (event.getName().equals("usuario")) {
//			if (usuariosNoRegistrados > 0) usuariosNoRegistrados--;
//			usuariosRegistrados++;
//		}
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		if (debug) logs.debug("Atributo Removido: " + event.getName() + ": " + event.getValue());
		if (event.getName().equals("usuario")) {
//			if (usuariosRegistrados > 0) usuariosRegistrados--;
//			usuariosNoRegistrados++;
		}
		if (event.getName().equals("visita")) {
			Visita visitaActual = (Visita)event.getValue();
			if (debug) logs.debug("Registro la hora de salida de la visita actual: " + visitaActual);
			visitaActual.setFechaSalida(new Date());
			aplicacionDAO.registrarSalida(visitaActual);
		}
	}

	
	public void attributeReplaced(HttpSessionBindingEvent event) {
		if (debug) logs.debug("Atributo Cambiado: " + event.getName() + ": " + event.getValue());
	}

	
	public void sessionCreated(HttpSessionEvent event) {
	    sesionesActivas++;
//		usuariosNoRegistrados++;
		if (debug) logs.debug("Nueva Sesion Encontrada. Usuarios Activos: " + sesionesActivas);
	}

	
	public void sessionDestroyed(HttpSessionEvent event) {
		if (sesionesActivas > 0) sesionesActivas--;
//		if (usuariosNoRegistrados > 0) usuariosNoRegistrados--;
		if (debug) logs.debug("Session Destruida. Usuarios Activos: " + sesionesActivas);
			///XXX Luego de que se destruya una sesion, llamo al gc para que libere memoria.
		System.gc();
	}

	
	public static int getUsuariosActivos() {
//		if (logs.isDebugEnabled()) logs.debug("getUsuariosActivos");
		return sesionesActivas;
	}


	public static int getUsuariosRegistrados() {
//		if (logs.isDebugEnabled()) logs.debug("getUsuariosRegistrados");
		return usuariosRegistrados;
	}


	public static int getUsuariosNoRegistrados() {
		return usuariosNoRegistrados;
	}

	public void contextInitialized(ServletContextEvent event) {
		if (debug) logs.debug("Contexto Inicializado");
	}

	public void contextDestroyed(ServletContextEvent event) {
		if (debug) logs.debug("Contexto Destruido");
		DAOFactoryImpl.getInstance().getAplicacionDAO().finalizarPendientes();
	}

}
