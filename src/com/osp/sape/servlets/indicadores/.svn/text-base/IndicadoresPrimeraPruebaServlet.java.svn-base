/*
 * Created on Apr 18, 2005
 */
package com.osp.sape.servlets.indicadores;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gc.acceso.GestorServlet;
import com.gc.utils.Acciones;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.EventoSapeDAO;

/**
 * 
 * @author Andres Aristizabal
 */
public class IndicadoresPrimeraPruebaServlet extends GestorServlet {

    private org.apache.log4j.Logger logs;   
    private EventoSapeDAO eventoSapeDAO;
    ///** para formatear las fechas **/
    //private DecimalFormat dfFecha; 
    
    // 2006-06-08: la vble dfFecha y el metodo getFechaHoy se pasaron para gestorServlet!!!!
    
    public void init() throws ServletException {
        super.init();
        DAOFactory factory = DAOFactoryImpl.getInstance();
        logs = org.apache.log4j.LogManager.getLogger(getClass());
        acciones=Acciones.getInstance();        
        eventoSapeDAO = factory.getEventoSapeDAO();
        dfFecha = new DecimalFormat("00");
    }
    
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String operacion = request.getParameter("operacion");
        if(logs.isDebugEnabled())logs.debug("doGet: operacion=["+operacion+"]");

        if(operacion == null || operacion.equals("")) {
        	primerosTelefono(request, response);
            return;
        } else if (operacion.equals("graficaPrimeraPrueba")){
        	
        	operacionGraficaPrimeraPrueba(request,response);
        	return;
        }
    }

    
    private void operacionGraficaPrimeraPrueba(HttpServletRequest request, HttpServletResponse response) {
    	if (logs.isDebugEnabled()) logs.debug("GraficaPrimeraPrueba");
    	
    	String fechaIni = request.getParameter("fechaIni");
    	String fechaFin = request.getParameter("fechaFin");
    	if (fechaIni == null || fechaIni.equals("")) fechaIni = getFechaHoy();
    	if (fechaFin == null ||fechaFin.equals("")) fechaFin = getFechaHoy();
    	
    	String grafica = null;
    	try {
			grafica=eventoSapeDAO.getGraficaPrimeraPrueba(fechaIni,fechaFin);
		} catch (SapeDataException e) {
			request.setAttribute("tipo","popup");
			error(e,request,response);
			return;
		}
    	
    	request.setAttribute("datos",grafica);
        
    	redireccionarConPlantilla("graficaEfectividadPruebas",request,response);
    }
    
    
    private void primerosTelefono (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (logs.isDebugEnabled()) logs.debug("primerosTelefono");
    	
    	String fechaIni = request.getParameter("fechaIni");
    	String fechaFin = request.getParameter("fechaFin");
    	if (fechaIni == null || fechaIni.equals("")) fechaIni = getFechaHoy();
    	if (fechaFin == null ||fechaFin.equals("")) fechaFin = getFechaHoy();
    	
    	String filtro = request.getParameter("filtro");
    	if (filtro == null) filtro = "";
    	String valorFiltro = request.getParameter("valorFiltro");
    	if (valorFiltro == null) valorFiltro = "";
    	
    	int regPorPagina = 100;
    	String regxpag = request.getParameter("regPorPagina");
    	if (regxpag != null && !regxpag.equals("")) {
	    	try {
	    		regPorPagina = Integer.parseInt(regxpag);
	    	} catch (NumberFormatException e) { }
    	}
     	
        String pagActual = request.getParameter("pagActual");
        if(pagActual==null || pagActual.equals("")) pagActual = "1";
        try {
        	int pagina = Integer.parseInt(pagActual);
        	if(pagina <=0) pagActual = "1";
        } catch(NumberFormatException e){
        	pagActual = "1";
        }        
        int offset = 0;
        offset = (Integer.parseInt(pagActual)-1)* regPorPagina;

        
        String orderBy =request.getParameter("orderBy");
        if(orderBy==null || orderBy.equals("")) orderBy = "primero DESC";

        
    	List listaPrimero = null;
    	Integer totalRegistros = null;
    	
    	
    	String exportar = request.getParameter("exportar");
    	String formato = request.getParameter("formato");
    	String pantalla = request.getParameter("pantalla");
    	// Para que exporte la cantidad completa de registros del reporte y no pagine!!!
    	if(exportar != null && exportar.equals("yes"))
    		offset = -1;
    	// el -1 indicara dentro de eventosSapeDAO que no se kiere paginar!!!
    	
    	try {
    		List l = eventoSapeDAO.getPrimeraPruebaTelefono(fechaIni, fechaFin, filtro, valorFiltro, regPorPagina, offset, orderBy);
    		listaPrimero = (List) l.get(0);
    		totalRegistros = (Integer) l.get(1);
    	} catch (SapeDataException e) {
    		error(e, request, response);
    		return;
    	}
        //TODO revisar ke tan comveniente puede ser la utilizacion de este tipo de llamado
    	if(exportar != null && exportar.equals("yes")){
    		
    		// Esta forma no funciona porque no tiene ServletContext
    		// inicializo una instancia de la clase ReportesServlet para ke procese el reporte
    		/*
    		ReportesServlet rs = ReportesServlet.getInstance();
    		
    		
    		request.setAttribute("listaDatos",listaPrimero);
    		request.setAttribute("totalRegistros",totalRegistros);
    		
    		try {
    			rs.operacionExportarInforme(request,response);
        	} catch (SapeDataException e) {
        		error(e, request, response);
        		return;
        	}
        	
        	*/
    		
    		HttpSession session=request.getSession();
    		session.removeAttribute("listaDatos");
    		session.setAttribute("listaDatos",listaPrimero);
    		
    		String url = "/actionSape?accion=reportes&operacion=exportarInforme&pantalla="+pantalla+"&formato="+formato;
    		
    		//TODO: te cago?
    		request.getRequestDispatcher(url).forward(request,response);
    		return;
    	}
    	
    	
    	double division = (double)totalRegistros.intValue()/(double)regPorPagina;
        int totalPaginas = (int)Math.ceil(division);
        if(totalPaginas <=1) totalPaginas = 0;

    	String query = "&fechaIni=" + fechaIni + "&fechaFin=" + fechaFin + "&filtro=" + filtro + "&valorFiltro=" + valorFiltro;
        
    	
    	
        request.setAttribute("fechaInicial",fechaIni);
        request.setAttribute("fechaFinal",fechaFin);
        request.setAttribute("filtro", filtro);
        request.setAttribute("valorFiltro", valorFiltro);
        request.setAttribute("regPorPagina", String.valueOf(regPorPagina));
        request.setAttribute("totalPaginas", String.valueOf(totalPaginas));
        request.setAttribute("pagActual",pagActual);
        request.setAttribute("orderBy",orderBy);
        request.setAttribute("query",query);
        request.setAttribute("listaPrimero", listaPrimero);
        request.setAttribute("totalRegistros", totalRegistros);
    	redireccionarConPlantilla("primeraPruebaTelefono", request, response);
    }
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        doGet(request,response);
    }    
    
}
