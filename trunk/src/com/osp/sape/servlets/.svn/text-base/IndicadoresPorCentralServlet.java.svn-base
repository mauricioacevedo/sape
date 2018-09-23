/*
 * Created on Apr 18, 2005
 */
package com.osp.sape.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.acceso.GestorServlet;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.EventoSapeDAO;

/**
 * 
 * @author Andres Aristizabal
 */
public class IndicadoresPorCentralServlet extends GestorServlet {

    private EventoSapeDAO eventoSapeDAO;
    
    // 2006-06-08: la vble dfFecha y el metodo getFechaHoy se pasaron para gestorServlet!!!!
    
    public void init() throws ServletException {
        super.init();
        DAOFactory factory = DAOFactoryImpl.getInstance();
        eventoSapeDAO = factory.getEventoSapeDAO();

    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String operacion = request.getParameter("operacion");
        if(debug)logs.debug("doGet: operacion=["+operacion+"]");

        if(operacion == null || operacion.equals("")){
        	estadisticoPorCentral(request, response);
            return;
        }
        if (operacion.equals("detallesCentral")) {
        	detallesCentral(request, response);
        	return;
        }
    }
    
    
    private void detallesCentral (HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("detallesCentral");
    	
    	String fechaIni = request.getParameter("fechaIni");
    	String fechaFin = request.getParameter("fechaFin");
    	String central = request.getParameter("central");
    	if (central == null || central.equals("")) {
    		error("Falta el parametro Central", request, response);
    		return;
    	}
    	if (fechaIni == null || fechaIni.equals("")) {
    		fechaIni = getFechaHoy();
    	}
    	if (fechaFin == null ||fechaFin.equals("")) {
    		fechaFin = getFechaHoy();
    	}
    	List listaDetalles = null;
    	Integer totalEventos = null;
    	try {
    		List l = eventoSapeDAO.getDetallesCentral(central, fechaIni, fechaFin);
    		listaDetalles = (List)l.get(0);
    		totalEventos = (Integer) l.get(1);
    	} catch (SapeDataException e) {
    		error(e, request, response);
    		return;
    	}	
    	request.setAttribute("listaDetallesCentral", listaDetalles);
    	request.setAttribute("totalEventos", totalEventos);
    	request.setAttribute("query", "&central="+central+"&fechaIni="+fechaIni+"&fechaFin="+fechaFin);
    	redireccionarConPlantilla("detallesCentral", request, response);
    }
    
    
    private void estadisticoPorCentral (HttpServletRequest request, HttpServletResponse response) {    	
    	if (debug) logs.debug("estadisticoPorCentral");
    	
    	String fechaIni = request.getParameter("fechaIni");
    	String fechaFin = request.getParameter("fechaFin");
    	if (fechaIni == null || fechaIni.equals("")) {
    		fechaIni = getFechaHoy();
    	}
    	if (fechaFin == null ||fechaFin.equals("")) {
    		fechaFin = getFechaHoy();
    	}
    	List listaEstadosCentral = null;
    	try {
    		listaEstadosCentral = eventoSapeDAO.getEstadosCentral(fechaIni, fechaFin);
    	} catch (SapeDataException e) {
    		error(e, request, response);
    		return;
    	}
    	request.setAttribute("listaEstadosCentral", listaEstadosCentral);
    	request.setAttribute("fIni", fechaIni);
    	request.setAttribute("fFin", fechaFin);
    	redireccionarConPlantilla("estadisticoPorCentral", request, response);
    }
   
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        doGet(request,response);
    }
}
