package com.osp.sape.servlets.rutinas;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.utils.Acciones;
import com.osp.sape.SapeConfiguration;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.CableDAO;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.RutinaCableDAO;
import com.osp.sape.data.RutinasDAO;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.maestros.rutinas.Rutinas;


public class RutinasCableServlet extends HttpServlet {

	private org.apache.log4j.Logger logs;
	private Acciones acciones;
	private RutinaCableDAO rutinaCableDAO;
	public void init() throws ServletException {
		super.init();
		logs = org.apache.log4j.LogManager.getLogger(getClass());
		acciones=Acciones.getInstance();
		
		rutinaCableDAO = DAOFactoryImpl.getInstance().getRutinaCableDAO();
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
        	importarCable(request, response);
        	return;
        }
	}
	
	
	private void importarCable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    request.setAttribute("tipo","cerrar");
		String cable = request.getParameter("cable");
		
		//String tipoCable=request.getParameter("tipoCable");
		
		if (cable == null || cable.equals("")) {
			error("Falta el parametro cable", request, response);
			return;
		}
		
		/*if(tipoCable!=null){
			// este tipo de cable se puede especificar desde el sape de metrotel. solo llamo al serviciogude con un parametro extra
			cable = cable + " "+tipoCable;
			
			
		}*/
		
		
		UserSipe user = (UserSipe)request.getSession().getAttribute("usuario");
		
		SapeConfiguration sapeConfig = SapeConfiguration.getInstance();
		String servicio = sapeConfig.getServicio("importarCable");
		
		String ipGude = sapeConfig.getIpGUDE();
		int puertoGude = sapeConfig.getPuertoGUDE();
		Socket socketGude = null;
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
        outSocket.writeBytes("G " + servicio + " " + user.getNick()+" " + cable + "\r");
        
        out.println("<pre>");
      
        String respuesta = "";
//        int cantFIN = 0;  //como activo los log, me llegan dos %FIN, me interesa el segundo.
        while((respuesta = bf.readLine())!= null){
            if(respuesta.equals("")){
                continue;
            }
            out.println(respuesta);
            response.flushBuffer();
            if(respuesta.indexOf("%FIN")!= -1){
//                if (cantFIN < 1) {
//                	cantFIN++; //la me llego el primer FIN
//                } else {
                	break; //la me llego el segundo FIN, termino.
//                }
            }
        } //fin del ciclo.
        
//        outSocket.writeBytes("set log=off\r");
        outSocket.writeBytes("~\r"); //Con este se cierra la comunicacion.
        
        out.println("</pre>");
        
        out.println("<br>");
        out.println("<p align=\"center\">");
        out.println("<a name=\"fin\" href=\"" + request.getContextPath() + "/actionSape?accion=rutinas&operacion=pantallaRutinaCable&cable=" + cable + "\">CONTINUAR</a>");
        out.println("</p>");
        
        /*
        if(tipoCable!=null&&tipoCable.equals("directo")){
        	logs.debug("Si el tipo de cable es directo se debe actualizar el cable de la rutina");
        	
        	List lista=null;
        	try {
				lista = rutinaCableDAO.getRutinaCable("directo_"+cable);
			} catch (SapeDataException e) {
				logs.warn("NO se pudo verificar si se insertaron registros de cable directo, cuidado!!!");
				return;
			} 
        	
			if(lista!=null&&lista.size()>0){//debo actualizar el nombre de la rutina!!!!
				
				RutinasDAO rutinas = DAOFactoryImpl.getInstance().getRutinasDAO();
				try {
					List lista2=rutinas.getElemento(cable, true);
					if(lista2!=null&&lista2.size()==1){//actualizo el valor del cable de la rutina.
						Rutinas r=(Rutinas) lista2.get(0);
						r.setValorTipo("directo_"+cable);
						rutinas.actualizarRutina(r);
					}
				} catch (SapeDataException e) {
					logs.warn("NO se pudo actualizar la rutina de cable directo!!!");
					return;
				}
			}
        }*/
        
	}
    
	
	private void error(String mensaje, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("error", mensaje);
        request.getRequestDispatcher(acciones.getTemplate("error")).forward(request,response);
        return;        
    }
}
