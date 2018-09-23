/*
 * Created on 6/07/2004
 */
package com.gc.acceso;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.utils.Accion;
import com.gc.utils.Acciones;
import com.osp.sape.maestros.UserSipe;


/**
 * @author Andres Aristizabal
 */
public class GenericAction {
	
	private Acciones acciones;
	private org.apache.log4j.Logger logs = org.apache.log4j.LogManager.getLogger(getClass());
	private boolean debug = logs.isDebugEnabled();
	public GenericAction(HttpServletRequest request, HttpServletResponse response) {
	    //XXX revisar...
		
		acciones = Acciones.getInstance();
//	    manejoAplicacion = AplicacionDAOImpl.getInstance();
//	    if (LogManagerWeb.getInstance().getNivel() >= 3)
//	        log(3, "Peticion Recibida de " + request.getRemoteAddr()); TODO
		String accion =  request.getParameter("accion");
//		if (LogManagerWeb.getInstance().getNivel() >= 3)TODO 
//		    log(3, "La accion es: " + accion);

	    	//almaceno los datos del cliente para estaditicas
//	    HeaderHttp h = new HeaderHttp(request);
	    
//	    System.out.println("Accion: "+accion);
	    
//	    Hit hit = new Hit();
//	    hit.setFecha(new java.util.Date(System.currentTimeMillis()));
//	    hit.setPagina(request.getRequestURI() + "?accion=" + accion);
//	    hit.setCliente(request.getRemoteAddr() + ":" + request.getRemotePort());
//	    if (h.getBrowser() == HeaderHttp.BROWSER_OTRO) {
	        //si no encuentro el browser, mando el header completo para que quede registrado 
	        //y asi adicionarlo a la clase HeaderHttp. 
//	        hit.setBrowser(request.getHeader("user-agent"));
//	        hit.setSo(h.getSistemaOperativo());
//	    } else {
//	        hit.setBrowser(h.getBrowser());
//	        hit.setBrowserVersion(h.getBrowserVersion());
//	        hit.setSo(h.getSistemaOperativo());
//	        hit.setSoVersion(h.getSistemaOperativoVersion());
//	    }
//	    manejoAplicacion.registrarHit(hit);
		
		if(debug) logs.debug("Los parametros del request son: " + request.getQueryString());

		if (accion == null || accion.equals("")) {
			redireccionar(Acciones.getInstance().getIndexPage(), request, response);
		} else {
			Accion a = acciones.getAccion(accion);

			if (a == null) {
			    try {
                    response.sendError(404, "La Accion: "+accion+" no fue Encontrada");
                } catch (IOException e) {
					logs.error(e.toString());
                }
			    return;
			} else {
			    	//Verifico si la accion la pueden acceder todos los usuarios, entonces no valido el usuario.
			    if (!a.containsPerfil(acciones.getRolDefecto())) {
					//valido que el usuario si tenga permiso para acceder a la pagina.
					UserSipe usuario = (UserSipe)request.getSession().getAttribute("usuario");
					if(debug) logs.debug("El usuario Actual es: " + usuario);//System.out.println("El usuario Actual es: " + usuario);
					if (usuario == null) { 
				        	//En caso que el usuario sea null, lo mando para la pagina de login.
					    redireccionar(acciones.getLoginPage(), request, response);
				        return;
					} else {
					    
						if(debug) logs.debug("El Perfil del usuario es: " + usuario.getNivel());
						
					    if (!a.containsPerfil(usuario.getNivel())) {
					        redireccionar(acciones.getRestrictedPage(), request, response);
					        return;
					    }
					}
			    }
			}
			
			if(debug) logs.debug("LA ACCION ES: "+accion);			
			redireccionar(a.getAccion(), request, response);
		}
	}
	
	private void redireccionar(String destino, HttpServletRequest request, HttpServletResponse response) {
	    javax.servlet.RequestDispatcher disp = request.getRequestDispatcher(destino);
	    if(debug)logs.debug("Envio al destino: "+destino);
	    
		try {
			disp.forward(request, response);
		} catch (ServletException e) {
			logs.error(e);
		} catch (IOException e) {
			logs.error(e);
		}
	}


}
