/*
 * Created on May 28, 2005
 */
package com.osp.sape.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.acceso.GestorServlet;
import com.osp.sape.SapeConfiguration;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.RequerimientosColaDAO;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.utils.ServicioGUDE;

/**
 * Encargado del reporte de Consultas de telefonos en colas. 
 * @author Andres Aristizabal
 */

public class ReportesColasServlet extends GestorServlet {
   
	
	private RequerimientosColaDAO requerimientosColaDAO;
	private ServicioGUDE servicioGUDE;

    
    public void init() throws ServletException {
        super.init();
        DAOFactory daoFactory = DAOFactoryImpl.getInstance(); 
        requerimientosColaDAO = daoFactory.getRequerimientosColaDAO();
        servicioGUDE = new ServicioGUDE();
    }
    

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	String operacion = request.getParameter("operacion");
    	if (logs.isDebugEnabled())logs.debug("doGet: operacion=["+operacion+"]");
                
        if (operacion.equals("actualizarEstado")) {
        	actualizarEstado(request, response);
        	return;
        }
        
        
        if (operacion.equals("desbloquearTelefono")) {
        	desbloquearTelefono (request, response);
        	return;
        }
        	//En caso que pase por aqui muestro un mensaje de error
        error("La operacion " + operacion + " no esta definida.", request, response);

        
    }
    
    
    

    private void actualizarEstado ( HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    	PrintWriter out = response.getWriter();
    	UserSipe usuario = (UserSipe) request.getSession().getAttribute("usuario");
		if (usuario == null) {
			out.print("usuario invalido");
			return;
		}
		if (!usuario.getNivel().equals("3")) {
			out.print("Operacion No permitida");
			return;			
		}
    	
    	int oid = 0;
    	try{
    		oid = Integer.parseInt(request.getParameter("oid"));
    	}catch(NumberFormatException e){
			logs.error(e);
			out.print(e);
    		return;
    	}
    	if (oid == 0) {
    		out.print("oid invalido");
    		return;
    	}
    	String estadoNuevo = request.getParameter("estadoNuevo");
    	if (estadoNuevo == null || estadoNuevo.equals("")) {
    		out.print("Falta el estado");
    		return;
    	} else {
    		if (!estadoNuevo.equals("II") && !estadoNuevo.equals("ET") && !estadoNuevo.equals("NI")) {
    			out.print("Estado invalido");
    			return;
    		}
    	}
    	if (estadoNuevo.equals("NI")) estadoNuevo += "-" + usuario.getNick();
    	
    	try {
			requerimientosColaDAO.actualizarEstado(oid,estadoNuevo);
		} catch (SapeDataException e) {
			logs.error(e);
			out.print(e);
			return;
		}
		out.print("OK");
		out.flush();
    }

    
    private void desbloquearTelefono ( HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if (logs.isDebugEnabled()) logs.debug("desbloquearTelefono");
    	
    	
    	PrintWriter out = response.getWriter();
    	UserSipe usuario = (UserSipe) request.getSession().getAttribute("usuario");
		if (usuario == null) {
			out.print("usuario invalido");
			return;
		}
		if (!usuario.getNivel().equals("3")) {
			out.print("Operacion No permitida");
			return;			
		}

		int telefono = 0;
		try {
			telefono = Integer.parseInt(request.getParameter("telefono"));
		} catch (NumberFormatException e) {
			logs.error(e);
			out.print(e);
    		return;	
		}
		if (telefono == 0) {
    		out.print("Telefono invalido");
    		return;	
		}
		String servicio = SapeConfiguration.getInstance().getServicio("desbloqueoTelefonoFenix");
		if (logs.isDebugEnabled()) logs.debug("Servicio: " + servicio);
		String respuesta = null;
    	try {
			respuesta = servicioGUDE.ejecutarServicio(usuario.getNick(),servicio,String.valueOf(telefono));
			respuesta = respuesta.replace(',',' ');
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		if (logs.isDebugEnabled()) logs.debug("Respuesta del GUDE: " + respuesta);
		out.print("Respuesta: \n" + respuesta);
		out.flush();
    }
    
}
