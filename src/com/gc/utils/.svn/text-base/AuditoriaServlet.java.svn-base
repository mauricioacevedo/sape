/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Jun 8, 2006 - 10:34:32 AM
 */

package com.gc.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.acceso.GestorServlet;
import com.gc.data.AplicacionDAO;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactoryImpl;

public class AuditoriaServlet extends GestorServlet{

	private AplicacionDAO aplicacionDAO;
	
	/**
	 * Recordemos que super.init() inicializa el logger y la vble debug. 
	 */
	public void init() throws ServletException {
		super.init();
		aplicacionDAO = DAOFactoryImpl.getInstance().getAplicacionDAO();
	}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	if(debug) logs.debug("doPost");
    	doGet(request,response);
    }
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String operacion = request.getParameter("operacion");
                
        if (debug)logs.debug("doGet: operacion=["+operacion+"]");
        
        if (operacion == null || operacion.equals("") || operacion.equals("visitasSape")) {
        	inicio(request,response);
        }else if(operacion.equals("rastrosSape")){
        	rastrosSape(request,response);
        }
    }
    
    private void rastrosSape(HttpServletRequest request, HttpServletResponse response) {
    	String fIni = request.getParameter("fIni");
    	String fFin = request.getParameter("fFin");
    	String user = request.getParameter("user");
    	String tipo = request.getParameter("tipo");
    	
    	if(fIni==null||fIni.equals("")||fFin==null||fFin.equals(""))
    		fIni=fFin=getFechaHoy();
    	
    	if(tipo == null)
    		tipo="todos";
    	
    	List l = null;
    	
    	try {
			l=aplicacionDAO.buscarRastro(fIni,fFin,user,tipo);
		} catch (SapeDataException e) {
			logs.error(e);
			error(e,request,response);
			return;
		}
		
		//logs.debug("lista:size"+l.size());
		
		request.setAttribute("listaRastros",l);
		request.setAttribute("fIni",fIni);
		request.setAttribute("fFin",fFin);
		request.setAttribute("user",(user==null?"":user));
		request.setAttribute("tipo",(tipo==null?"":tipo));
		
		redireccionarConPlantilla("rastrosSape",request,response);
    	
    }
    
    private void inicio(HttpServletRequest request, HttpServletResponse response) {
    	if (debug)logs.debug("operacion: inicio");
    	
    	getVisitas(request,response);
    }
    
    
    private void getVisitas(HttpServletRequest request, HttpServletResponse response) {
    	if (debug)	logs.debug("operacion: verVisitas");
    	
    	String fIni = request.getParameter("fIni");
    	String fFin = request.getParameter("fFin");
    	String user = request.getParameter("user");
    	
    	if(fIni==null||fIni.equals("")||fFin==null||fFin.equals(""))
    		fIni=fFin=getFechaHoy();
    	
    	List l = null;
    	
    	try {
			l=aplicacionDAO.buscarVisita(fIni,fFin,user);
		} catch (SapeDataException e) {
			logs.error(e);
			error(e,request,response);
			return;
		}
		
		request.setAttribute("listaVisitas",l);
		request.setAttribute("fIni",fIni);
		request.setAttribute("fFin",fFin);
		request.setAttribute("user",(user==null?"":user));
		
		redireccionarConPlantilla("visitas",request,response);
    }
}
