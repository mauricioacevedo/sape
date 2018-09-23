/*
 * Created on Jun 7, 2005
 */
package com.osp.sape.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osp.sape.SapeConfiguration;
import com.osp.sape.Exceptions.SapeAppException;
import com.osp.sape.maestros.CodigoFallaCola;
import com.osp.sape.servicios.Alarma;
import com.osp.sape.servicios.CodigosFalla;
import com.osp.sape.servicios.Cola;
import com.osp.sape.servicios.ConfiguracionServicios;
import com.osp.sape.servicios.EvaluacionResultados;
import com.osp.sape.servicios.MedioBeeper;
import com.osp.sape.servicios.MedioCorreo;
import com.osp.sape.servicios.MedioInformar;
import com.osp.sape.servicios.MedioSmsOLA;
import com.osp.sape.servicios.Secuencia;

/**
 * 
 * @author Andres Aristizabal
 */
public class RecogerParametrosTCLServlet extends HttpServlet {
    
    private org.apache.log4j.Logger logs;    
    private ConfiguracionServicios configuracionServicios;
    private EvaluacionResultados evaluacionResultados;
    
    private PrintWriter pr; 
    
    
    
    public void init() throws ServletException {
		super.init();
		
		logs = org.apache.log4j.LogManager.getLogger(getClass());
		    
		try {
		    configuracionServicios = new ConfiguracionServicios(new File(SapeConfiguration.getInstance().getRutaServicios()));
		} catch (SapeAppException e) {
		    logs.error(e);
		}
		
        try {
			evaluacionResultados = new EvaluacionResultados(new File(SapeConfiguration.getInstance().getRutaEvalResultados()));
		} catch (SapeAppException e) {
			if(logs.isDebugEnabled())logs.debug("No inicio evaluacionResultados:\n\n"+e.toString());
			
		}
		
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String operacion = request.getParameter("operacion");
        if(logs.isDebugEnabled())logs.debug("doGet: operacion=["+operacion+"]");
        
        pr = response.getWriter();
        response.setContentType("text");
        
        	//si no mando parametros muestro la ayuda.
        if(operacion == null || operacion.equals("")) {
        	mostrarAyuda();
        }
        
        if(operacion.equals("listaColas")){
            List l = null;
            try {
                l=configuracionServicios.getAllColas();
            } catch (SapeAppException e) {
                error(e,request,response);
                return;
            }
            int limit = l.size();
            StringBuffer colas = new StringBuffer(limit * 4);
            for(int i = 0; i < limit; i++){
                Cola c = (Cola)l.get(i);
                colas.append(c.getNombre());
                if (i < limit - 1) colas.append(",");
            }
            
            if(colas == null){
                pr.println("[ERROR]");
                return;
            }else if(colas.equals("")){
                pr.println("[VACIO]");
                return;
            }
            pr.print(colas);
            return;
        }else if(operacion.equals("alarmaCola")){
            alarmaCola(request, response);
            return;
        }else if(operacion.equals("evaluarResultado")){
        	operacionEvaluarResultado(request,response);
        	return;
        }
        
        if (operacion.equals("servicioAlarma")) {
        	// XXX: se cambio el 13-06-2006
        	//pr.print("["+SapeConfiguration.getInstance().getRutaServicios()+"]");
        	System.out.println("PARAMETRO sape-config:[servicioAlarma="+SapeConfiguration.getInstance().getUrlServicios()+"]");
        	pr.print("["+SapeConfiguration.getInstance().getUrlServicios()+"]");
        	pr.flush();
        	return;
        }
        if(operacion.equals("codigosFalla")){
        	operacionCodigosFalla(request,response);
        	return;
        }
        if(operacion.equals("naturalezasReclamo")){
        	operacionNaturalezasReclamo(request,response);
        	return;
        }
    }
    
    private void operacionCodigosFalla(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    
    	String cola = request.getParameter("cola");
    	
    	CodigosFalla cf = null;
    	CodigoFallaCola cfc = null;
    	try {
			cf = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
			cfc=cf.getCodigoFallaCola(cola);
		} catch (SapeAppException e) {
			error(e,request,response);
			return;
		}
		pr = response.getWriter();
        response.setContentType("text");
		if(cfc == null){
			//pr.print("[ERROR] la cola "+cola+" no tiene Codigos de Falla asociados.");
			pr.flush();
			return;
		}
		
		pr.print(cfc.toStringCodigosFalla());
		pr.flush();
    }
    
    private void operacionNaturalezasReclamo(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	String cola = request.getParameter("cola");
    	
    	CodigosFalla cf = null;
    	CodigoFallaCola cfc = null;
    	try {
			cf = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
			cfc=cf.getCodigoFallaCola(cola);
		} catch (SapeAppException e) {
			error(e,request,response);
			return;
		}
		pr = response.getWriter();
        response.setContentType("text");
		if(cfc == null){
			//pr.println("[ERROR] la cola "+cola+" no tiene Naturalezas de Reclamo asociadas.");
			pr.flush();
			return;
		}
		
		pr.print(cfc.toStringNaturalezasReclamo());
		pr.flush();
    }
    
    private void mostrarAyuda () {
        pr.println("Debe Definir una operacion.");
        pr.println();
        pr.println("use:");
        pr.println("- listaColas: muestra la lista de colas configuradas");
        pr.println("- alarmaCola: muestra la configuracion de la alarma de una cola. necesita el parametro 'cola'");
        pr.println("     ejemplo: ?operacion=alarmaCola&cola=TSTLI");
        pr.println("- evaluarResultado: para recoger informacion de una secuencia en particular. Necesita el parametro 'condicion'");
        pr.println("     ejemplo: ?operacion=evaluarResultado&condicion=1");
        pr.println("- servicioAlarma: da la ruta del archivo de configuracion de las alarmas. Para el beeper o el correo");
        pr.println("- codigosFalla: recibe, aparte, un parametro cola y devuelve los codigos de falla asociados a esa cola");
        pr.println("     ejemplo: ?operacion=codigosFalla&cola=ANALI");
        pr.println("- naturalezasReclamo: recibe, aparte, un parametro cola y devuelve las naturalezas de reclamo asociados a esa cola");
        pr.println("     ejemplo: ?operacion=naturalezasReclamo&cola=ANALI");
        pr.println();
        pr.println();
        pr.println();
        
        pr.flush();
        return;
    }
    
    private void operacionEvaluarResultado(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if (logs.isDebugEnabled()) logs.debug("operacionEvaluarResultado");
    	String secuencia = request.getParameter("condicion");
    	
    	int sec;
    	
        pr = response.getWriter();
        response.setContentType("text");
    	
    	try {
    		sec=Integer.parseInt(secuencia);
    	} catch(NumberFormatException e){
    		pr.print("[ERROR]");
    		return;
    	}
    	Secuencia sec2 = null;
    	
		
    	sec2=(Secuencia)evaluacionResultados.getSecuencia(sec);
    	
    	if(sec2 == null) {
    		pr.print("[ERROR]");
    		return;
    	} else {
    		pr.print(sec2.toString());
    		return;
    	}
    	
    }
    
    
    private void alarmaCola(HttpServletRequest request, HttpServletResponse response) 
    											throws ServletException, IOException {
        if (logs.isDebugEnabled()) logs.debug("alarmaCola");
    	String cola = request.getParameter("cola");
        Alarma alarma = null;
        try {
            alarma=configuracionServicios.getAlarma(cola);
        } catch (SapeAppException e) {
            error(e,request,response);
            return;
        }
        
        if(alarma == null){
            pr.print("[ND]");
            return;
        }
        
        int limite = alarma.getLimite();
        String avisar=alarma.getAvisar();
        MedioInformar m =alarma.getMedio();
        String medio = null;
        String valorMedio = null;
        if(m instanceof MedioBeeper){
            MedioBeeper mb =(MedioBeeper)m;
            medio = mb.getMedio();
            valorMedio = mb.getCodigo();
        }else if(m instanceof MedioCorreo){
            MedioCorreo mc =(MedioCorreo)m;
            medio = mc.getMedio();
            valorMedio = mc.getDireccion();
        }else{//si no es un smsOla
            MedioSmsOLA ms = (MedioSmsOLA)m;
            medio = ms.getMedio();
            valorMedio = ms.getTelefono();
        }
        String msg=alarma.getMensaje();
        // NO SE ESTA MANDANDO NI EL NOMBRE DE LA COLA NI RECORDAR!!!!!!
        
        pr.print("["+limite+"]"+"["+avisar+"]"+"["+medio+"]"+"["+valorMedio+"]"+"["+msg+"]"+"["+cola+"]");        
        
    }
    
    public void error(Exception e,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("error",e.toString());
        request.getRequestDispatcher("/error/error.jsp").forward(request, response);
        return;        
    }
    
    public void error(String e,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("error",e);
        request.getRequestDispatcher("/error/error.jsp").forward(request, response);
        return;        
    }
}
