package com.osp.sape.servlets.rutinas;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.acceso.GestorServlet;
import com.gc.utils.Acciones;
import com.osp.sape.SapeConfiguration;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.RutinasDAO;
import com.osp.sape.data.RutinasDAOImpl;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.maestros.rutinas.Rutinas;

public class RutinasArmarioServlet extends GestorServlet {

	private org.apache.log4j.Logger logs;
	private Acciones acciones;
	private RutinasDAO rutinasDAO;
	
	public void init() throws ServletException {
		super.init();
		logs = org.apache.log4j.LogManager.getLogger(getClass());
		acciones=Acciones.getInstance();
		rutinasDAO=DAOFactoryImpl.getInstance().getRutinasDAO();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operacion = request.getParameter("operacion");
        if(logs.isDebugEnabled())logs.debug("doGet: operacion=["+operacion+"]");
		
        if (operacion == null || operacion.equals("")) {
        	request.setAttribute("tipo","cerrar");
        	error("Debe definir la operacion a realizar", request, response);
        	return;
        }
        if (operacion.equals("importar")) {
        	importarArmario(request, response);
        	return;
        }
        if(operacion.equals("enviarCalificacion")){
        	enviarCalificacion(request,response);
        	return;
        }
	}
	
	private void enviarCalificacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    request.setAttribute("tipo","cerrar");
		
		String idRutina=request.getParameter("idRutina");
		int id=-1;
		try{
			id=Integer.parseInt(idRutina);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		
		Rutinas r = null;
		
		try {
			r=rutinasDAO.getRutina(id);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		if(r.getHabilitado()){
			error("La rutina "+idRutina+" no ha terminado la calificacion.",request,response);
			return;
		}
		
		String usuario = getUsuarioSession(request);
		
		SapeConfiguration sapeConfig = SapeConfiguration.getInstance();
		String servicio = sapeConfig.getServicio("enviarCalificacion");
		String ipGude = sapeConfig.getIpGUDE();
		int puertoGude = sapeConfig.getPuertoGUDE();
		Socket socketGude = null;
		
		logs.debug("Ip Gude: "+ipGude+" puertoGude: "+puertoGude);
		
		try {
			socketGude = new Socket(ipGude, puertoGude);
		} catch (IOException e) {
			error(e.toString(), request, response);
			return;
		}
		if (!socketGude.isConnected()) {
			out.println("[ERROR]: no hay conexion con el ServicioGUDE");
			return;
		}
		if (logs.isInfoEnabled()) logs.info("Conectado al ServicioGude via " + ipGude + ":" + puertoGude);
		out.println("Conectado al ServicioGude via " + ipGude + ":" + puertoGude);
        BufferedReader bf = new BufferedReader(new InputStreamReader(socketGude.getInputStream()));
        DataOutputStream outSocket = new DataOutputStream(socketGude.getOutputStream());
        		//Habilito el socket para que me muestre todo.
//        outSocket.writeBytes("set log=on\r");	
        outSocket.writeBytes("G " + servicio +" "+idRutina +" "+r.getValorTipo()+" "+usuario+"\r");
        
        out.println("<pre>");
      
        String respuesta = "";
        while((respuesta = bf.readLine()) != null ){
            if(respuesta.equals("")){
                continue;
            }
            out.println(respuesta);
            response.flushBuffer();
            if(respuesta.indexOf("%FIN")!= -1){
                break;
            }
        } //fin del ciclo.
        
        bf.readLine(); //leo el %
        outSocket.writeBytes("~\r"); //Con este se cierra la comunicacion.
        outSocket.flush();
        socketGude.close();
        
        out.println("</pre>");
        
        out.println("<br>");
        out.println("<p align=\"center\">");
        out.println("<a name=\"fin\" href=\"javascript:window.close();\">CERRAR</a>");
        out.println("</p>");			
	}
	
	private void importarArmario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    request.setAttribute("tipo","cerrar");
	    
		String armario = request.getParameter("armario");
		
		UserSipe user = (UserSipe)request.getSession().getAttribute("usuario");
		
		if (armario == null || armario.equals("")) {
			error("Falta el parametro armario", request, response);
			return;
		}
		SapeConfiguration sapeConfig = SapeConfiguration.getInstance();
		String servicio = sapeConfig.getServicio("importarArmario");
		String ipGude = sapeConfig.getIpGUDE();
		int puertoGude = sapeConfig.getPuertoGUDE();
		Socket socketGude = null;
		
		logs.debug("Ip Gude: "+ipGude+" puertoGude: "+puertoGude);
		
		try {
			socketGude = new Socket(ipGude, puertoGude);
		} catch (IOException e) {
			error(e.toString(), request, response);
			return;
		}
		if (!socketGude.isConnected()) {
			out.println("[ERROR]: no hay conexion con el ServicioGUDE");
			return;
		}
		if (logs.isInfoEnabled()) logs.info("Conectado al ServicioGude via " + ipGude + ":" + puertoGude);
		out.println("Conectado al ServicioGude via " + ipGude + ":" + puertoGude);
        BufferedReader bf = new BufferedReader(new InputStreamReader(socketGude.getInputStream()));
        DataOutputStream outSocket = new DataOutputStream(socketGude.getOutputStream());
        		//Habilito el socket para que me muestre todo.
//        outSocket.writeBytes("set log=on\r");	
        outSocket.writeBytes("G " + servicio + " "+user.getNick()+" " + armario + "\r");
        
        out.println("<pre>");
      
        String respuesta = "";
        while((respuesta = bf.readLine()) != null ){
            if(respuesta.equals("")){
                continue;
            }
            out.println(respuesta);
            response.flushBuffer();
            if(respuesta.indexOf("%FIN")!= -1){
                break;
            }
        } //fin del ciclo.
        
        bf.readLine(); //leo el %
        outSocket.writeBytes("~\r"); //Con este se cierra la comunicacion.
        outSocket.flush();
        socketGude.close();
        
        out.println("</pre>");
        
        out.println("<br>");
        out.println("<p align=\"center\">");
        out.println("<a name=\"fin\" href=\"" + request.getContextPath() + "/actionSape?accion=rutinas&operacion=pantallaRutinaArmario&armario=" + armario + "\">CONTINUAR</a>");
        out.println("</p>");
        
	}
    
}
