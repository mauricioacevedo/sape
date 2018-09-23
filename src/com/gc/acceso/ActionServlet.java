package com.gc.acceso;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.gc.utils.Acciones;


/*
 * Created on 14-ene-2004
 */

/**
 * @author Andres Aristizabal
 */
public class ActionServlet extends HttpServlet {

	Logger logs;

	private final String FILE_ACCIONES = "acciones.xml";
//	private Acciones acciones;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		logs = Logger.getLogger(getClass());

				////////////////////////////////////////////////////
				//Inicializo la ruta de acciones
				////////////////////////////////////////////////////
		String ruta = config.getServletContext().getRealPath("WEB-INF") + "/";
		Acciones.initialize(ruta + FILE_ACCIONES);
		if (logs.isInfoEnabled()) logs.info("Sistema Configurado.");
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
	    new GenericAction(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request,response);
	}
}
