/*
 * Created on 04-oct-2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.gc.acceso;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.data.AplicacionDAO;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.UsuarioDAO;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.maestros.auditoria.Visita;
import com.osp.sape.utils.ConfiguracionClienteSape;

/**
 * @author Harvey
 *
 */
public class InicioServlet extends GestorServlet {

	private DAOFactory factory;
	private UsuarioDAO usuarioDAO;
	private AplicacionDAO aplicacionDAO;
	
	
	public void init() throws ServletException {
		super.init();
		factory = DAOFactoryImpl.getInstance();
		usuarioDAO = factory.getUsuarioDAO();
		aplicacionDAO = factory.getAplicacionDAO();
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (debug) logs.debug("doPost");
		
		
			//los remuevo por si depronto ya el usuario estaba adentro.
		request.getSession().removeAttribute("usuario");
		request.getSession().removeAttribute("visita");
		
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(login == null || login.equals("") || password == null || password.equals("")) {
			request.setAttribute("msg","Informacion de ingreso no valida. Verifique su datos.");
			redireccionar(acciones.getLoginPage(), request, response);
			return;
		}

		UserSipe usuario = null;
		login = login.toLowerCase();
		try {
			 // XXX: En esta parte se hace la validacion dependiendo del metodo que se tenga.				
			String metodo=ConfiguracionClienteSape.getInstance().getMetodoValidacion();
			if(debug) logs.debug("Metodo de validacion de cliente ["+login+"] : ["+metodo+"]");
			
			HashMap opValidacion=ConfiguracionClienteSape.getInstance().getOpcionesValidacion();
			usuario = usuarioDAO.validarUsuario(login , password, metodo, opValidacion);
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
		if (debug) logs.debug("usuario es: " + usuario);

		if (usuario == null){
			//El usuario no existe
			if (debug) logs.debug("El usuario no existe.");
			request.setAttribute("msg","El usuario "+login+" no existe. Verifique su usuario y contrasena.");
			aplicacionDAO.registrarLoginFallido(new Date(), login, password, request.getRemoteHost());
			// esta plantilla es igual a index.jsp!!
			redireccionarConPlantilla("loginError", request, response);
			return;
		}
		
		
			//Registrar el ingreso de este usuario. 
		request.getSession().setAttribute("usuario",usuario);
		
		Visita visita = new Visita(usuario.getNick(), new Date(), request.getRemoteAddr());
		request.getSession().setAttribute("visita",visita);
		
		aplicacionDAO.registrarVisita(visita);
		
		//Como es un usuario nuevo, se retira la variable del usuario anterior!!
		request.getSession().removeAttribute("telOperador");
		 
		response.sendRedirect(request.getContextPath() + "/actionSape?accion=pruebaTelefono");
	}
	
	
		//cuando entra por aca es que se va a salir
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("usuario");
		//response.sendRedirect(acciones.getLoginPage());
		
		request.getSession().removeAttribute("visita");
		
		request.getRequestDispatcher(acciones.getLoginPage()).forward(request,response);
	}
}
