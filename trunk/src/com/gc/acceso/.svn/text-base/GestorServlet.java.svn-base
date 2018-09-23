/*
 * Created on 19/11/2004
 */
package com.gc.acceso;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.data.AplicacionDAO;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.maestros.UserSipe;

/**
 * Clase que sera padre de todos los servlet que se implementen. 
 * La utilidad de esta clase redica en que se encuentran definidos los
 * metodos log y que ademas tiene el metodo redireccionar, para que en todos
 * lso servlet no haya que implementarlo
 * @author Andres
 */
public class GestorServlet extends HttpServlet {
	
	protected com.gc.utils.Acciones acciones;
	protected org.apache.log4j.Logger logs;
	protected boolean debug = false;
    /** para formatear las fechas **/
    protected DecimalFormat dfFecha;
    private AplicacionDAO aplicacionDAO;
    //protected String horaIni , horaFin;
    
	public void init() throws ServletException {
		super.init();
		acciones = com.gc.utils.Acciones.getInstance();
		logs = org.apache.log4j.LogManager.getLogger(getClass());
		debug = logs.isDebugEnabled();
		//horaIni = " 00:00:00";
		//horaFin = " 23:59:59";
		dfFecha=new DecimalFormat("00");
		aplicacionDAO = DAOFactoryImpl.getInstance().getAplicacionDAO();
	}
    
	
	/**
	 * Redirecciona hacia un destino determinado por plantilla nombre. <br>
	 * Este nombre debe estar configurado dentro del archivo acciones.xml
	 * @param nombre El nombre de la plantilla.
	 * @param request
	 * @param response
	 */
	protected void redireccionarConPlantilla(String nombre, HttpServletRequest request, HttpServletResponse response) {
		String destino = acciones.getTemplate(nombre);
		if (destino == null) {
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "La plantilla " + nombre + 
								" no fue encontrada. Por favor revisar el archivo de acciones");
				return;
			} catch (IOException e) {
				logs.error (e.toString());
			}
		}
		redireccionar(destino, request, response);
	}
	
	/**
	 * Con este metodo se pretende que todos los servlets redireccionen INTERNAMENTE.
	 * en caso que ocurra un error, se le manda un error 500 al servidor para informar del problema. 
	 * @param destino
	 * @param request
	 * @param response
	 */
	protected void redireccionar(String destino, HttpServletRequest request, HttpServletResponse response) {
		if (logs.isDebugEnabled()) logs.debug("Redirecciona hacia: " + destino);
	    javax.servlet.RequestDispatcher disp = request.getRequestDispatcher(destino);
			//En caso que ocurra una excepcion voy a almacenarla en esta variable para luego mostrar el mensaje.
	    Exception error = null;
	    try {
			disp.forward(request, response);
		} catch (ServletException e) {
		    logs.error(e);
		    error = e;
		} catch (IOException e) {
		    logs.error(e);
		    error = e;
		}
			//Si ocurrio un error haciendo el forward
		if (error != null) {
		    try {
                	//Mando el error 500
		        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, error.toString());
            } catch (IOException e) {
                logs.error(e);
            }
		}
	} //Fin de redireccionar.
	
	
    public void error(Exception e, HttpServletRequest request, HttpServletResponse response) {
    	error(e.toString(), request, response);
    	return;
    }

    public void error(String e, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("error", e);
        redireccionarConPlantilla("error", request, response);
        return;
    }
    /**
     * Metodo utilizado para enviar mensajes al usuario actual del sistema.
     * 
     * @param msg El mensaje a ser enviado
     * @param tipo es la clase de ventana, popup, normal, por default es normal.
     * @param request
     * @param response
     * @throws ServletException
     */
    public void mensaje(String msg,String tipo,HttpServletRequest request, HttpServletResponse response)  {
    	
    	if(tipo == null || tipo.equals("")){
    		tipo = "normal";
    	}

   		request.setAttribute("tipo",tipo);
   		request.setAttribute("mensaje",msg);
    	
    	redireccionarConPlantilla("MantenimientoMensaje",request,response);
    	
    }
    
    protected String getFechaHoy() {
    	Calendar cal = Calendar.getInstance();
    	
    	String ano = dfFecha.format(cal.get(Calendar.YEAR));
        String mes = dfFecha.format(cal.get(Calendar.MONTH)+1);
        String day = dfFecha.format(cal.get(Calendar.DAY_OF_MONTH));
        
        return ano+"-"+mes+"-"+day;
    }
    
    protected String getUsuarioSession(HttpServletRequest request){
    	UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
    	
    	if(usuario!= null)
    		return usuario.getNick();
    	else
    		return null;
    }
    
    
    /**
     * Si es eliminacion solo se manda el nuevo.
     * @param tipoRastro
     * @param nuevo
     * @param viejo
     */
    protected void registrarRastro (HttpServletRequest request, AplicacionDAO.TipoRastros tipoRastro, Object nuevo, Object viejo) {
    	if (debug) logs.debug("registrarRastro: " + tipoRastro); //no se muestran los otros porque quedaria muy largo el log.
    	
    	UserSipe usu = (UserSipe)request.getSession().getAttribute("usuario");
    	String usuario = null;
    	if (usu != null) usuario = usu.getNick();
    	
    	switch (tipoRastro) {
    	case NUEVO: aplicacionDAO.registrarInsersion(new java.util.Date(), usuario, nuevo);
    		break;
    	case ACTUALIZACION: aplicacionDAO.registrarActualizacion(new java.util.Date(), usuario, viejo, nuevo);
    		break;
    	case ELIMINACION: aplicacionDAO.registrarEliminacion(new java.util.Date(), usuario, nuevo);
    		break;
   		}
    }
    
    
    public String getFechaFormato(Date d,String tipoFormato){
    	
    	Calendar cal =Calendar.getInstance();
    	cal.setTime(d);
    	
    	DecimalFormat df = new DecimalFormat("00");
    	
    	String ret=null;
    	
    	if(tipoFormato==null){
    		ret = cal.get(Calendar.YEAR)+"-"+df.format(cal.get(Calendar.MONTH)+1)+"-"+df.format(cal.get(Calendar.DAY_OF_MONTH));
    	}else if(tipoFormato.equalsIgnoreCase("yyyy-mm-dd")){
    		ret = cal.get(Calendar.YEAR)+"-"+df.format(cal.get(Calendar.MONTH)+1)+"-"+df.format(cal.get(Calendar.DAY_OF_MONTH));
    	}else if(tipoFormato.equals("hora")){
    		ret = df.format(cal.get(Calendar.HOUR_OF_DAY));
    	}else if(tipoFormato.equals("minuto")){
    		ret = df.format(cal.get(Calendar.MINUTE));
    	}else if(tipoFormato.equalsIgnoreCase("yyyy-mm-dd hh:MM:ss")){
    		ret = cal.get(Calendar.YEAR)+"-"+df.format(cal.get(Calendar.MONTH)+1)+"-"+df.format(cal.get(Calendar.DAY_OF_MONTH));
    		ret += " "+df.format(cal.get(Calendar.HOUR_OF_DAY))+":"+df.format(cal.get(Calendar.MINUTE))+":"+df.format(cal.get(Calendar.SECOND));
    	}
    	
    	return ret;
    }
    
    /*
     * La idea es ke valide una o dos fechas
     * El metodo retorna null si todas las cadenas estan buenas, si alguna no cumple retorna la la fecha mala.
     */
    protected String validarFechas(String...  fechas){
    	
    	String retorno=null;
    	int counterNULL=0;
    	int counterVACIO=0;
    	for(int i=0;i<fechas.length;i++){
    		
    		String e=fechas[i];
    		
    		if(e==null){
    			retorno="valor null";
    			counterNULL++;
    		}
    		if(e.equals("")){
    			retorno="fecha vacia";
    			counterVACIO++;
    		}
    	}
    	
    	if(counterNULL==fechas.length||counterVACIO==fechas.length){
    		retorno="all_empty";
    	}
    	
    	return retorno;
    }
}
