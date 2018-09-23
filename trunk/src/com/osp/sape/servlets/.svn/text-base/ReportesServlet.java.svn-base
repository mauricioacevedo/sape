/*
 * Created on May 28, 2005
 */
package com.osp.sape.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;

import com.gc.acceso.GestorServlet;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.CodigosVerDAO;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.EventoSapeDAO;
import com.osp.sape.data.PruebaBasicaDAO;
import com.osp.sape.data.PruebaProgramadaDAO;
import com.osp.sape.data.PruebaSPPDAO;
import com.osp.sape.data.ReportesDAO;
import com.osp.sape.data.RequerimientosColaDAO;
import com.osp.sape.data.RutinaArmarioDAO;
import com.osp.sape.data.RutinaCableDAO;
import com.osp.sape.data.RutinaClienteDAO;
import com.osp.sape.data.RutinasDAO;
import com.osp.sape.data.SerieDAO;
import com.osp.sape.data.TipoNodoDAO;
import com.osp.sape.data.UsuarioDAO;
import com.osp.sape.indicadores.ViewCodvCentral;
import com.osp.sape.indicadores.ViewEstadosCentral;
import com.osp.sape.indicadores.ViewPrimeraPruebaTelefonos;
import com.osp.sape.maestros.CabezaPrueba;
import com.osp.sape.maestros.CodigoVer;
import com.osp.sape.maestros.Serie;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.maestros.rutinas.PruebaSPP;
import com.osp.sape.maestros.rutinas.Rutinas;
import com.osp.sape.reportes.RequerimientosCola;
import com.osp.sape.reportes.SerieReportes;
import com.osp.sape.reportes.ViewReportePruebas;
import com.osp.sape.utils.CustomDataSource;
import com.vladium.utils.IObjectProfileNode;
import com.vladium.utils.ObjectProfiler;

/**
 * 
 * @author Develop Team XpLoRa
 */
//ESTE SERVLET SE ENCARGA DE MOSTRAR INFORMES ACERCA DE LAS PRUEBAS, SE DAN EN FORMATO HTML.
//EL SERVLET TAMBIEN TIENE PROCEDIMIENTOS PARA EXPORTAR A DIFERENTES FORMATOS ( PDF, XLS, CSV...)
public class ReportesServlet extends GestorServlet {
    
    private PruebaBasicaDAO pruebaBasicaDAO;
    private EventoSapeDAO eventoSapeDAO;
    private UsuarioDAO usuarioDAO;
    private CodigosVerDAO codigosVerDAO;
    private RequerimientosColaDAO requerimientosColaDAO;
    private ReportesDAO reportesDAO;
    private PruebaProgramadaDAO pruebaProgramadaDAO;
    private PruebaSPPDAO pruebasPPDAO;
    private TipoNodoDAO tipoNodoDAO;
    private RutinaCableDAO rutinaCableDAO;
    private RutinaArmarioDAO rutinaArmarioDAO;
    private RutinaClienteDAO rutinaClienteDAO;
    private RutinasDAO rutinasDAO;
    
    private SerieDAO serieDAO;
    
    // esto es para agregarlo a las fechas y optimizar un poco la consulta
    private String horaIni = " 00:00:00", horaFin = " 23:59:59";
    
    // 2006-06-08: la vble dfFecha y el metodo getFechaHoy se pasaron para gestorServlet!!!!
    
    
    public void init() throws ServletException {
        super.init();
        
        DAOFactory daoFactory = DAOFactoryImpl.getInstance(); 
                
        pruebaBasicaDAO= daoFactory.getPruebaBasicaDAO();
        eventoSapeDAO=daoFactory.getEventoSapeDAO();
        usuarioDAO = daoFactory.getUsuarioDAO();
        codigosVerDAO =daoFactory.getCodigosVerDAO();
        requerimientosColaDAO =daoFactory.getRequerimientosColaDAO();

        reportesDAO = daoFactory.getReportesDAO();
        pruebaProgramadaDAO = daoFactory.getPruebaProgramadaDAO();
        pruebasPPDAO = daoFactory.getPruebaSPPDAO();
        tipoNodoDAO = daoFactory.getTipoNodoDAO();
        
        rutinasDAO=daoFactory.getRutinasDAO();
        rutinaCableDAO=daoFactory.getRutinaCableDAO();
        rutinaArmarioDAO=daoFactory.getRutinaArmarioDAO();
        rutinaClienteDAO=daoFactory.getRutinaClienteDAO();
        serieDAO = daoFactory.getSerieDAO();
        dfFecha = new DecimalFormat("00");
    }
    
    
    private void dlusTipoNodo(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("dlusTipoNodo");
    	String tipoReporte = request.getParameter("tipoReporte");
    	if (tipoReporte == null || tipoReporte.equals("")) tipoReporte = "resumido";
    	int idTipoNodo = 0;
    	try {
    		idTipoNodo = Integer.parseInt(request.getParameter("tipoNodo"));
    	} catch (NumberFormatException e) {
    		logs.error(e);
    		request.setAttribute("tipo","cerrar");
    		error("Tipo de Nodo Inv&aacute;lido.", request, response);
    		return;
    	}
    	TipoNodo tipoNodo = null;
    	List listaDLUs = null;
    	try {
			tipoNodo = tipoNodoDAO.getTipoNodo(idTipoNodo);
			if (tipoReporte.equals("resumido")) {
				listaDLUs = tipoNodoDAO.getDLUsTipoNodoResumido(tipoNodo);
			} else {
				listaDLUs = tipoNodoDAO.getDLUsTipoNodo(tipoNodo);
			}
		} catch (SapeDataException e) {
			request.setAttribute("tipo","cerrar");
			error(e, request, response);
			return;
		}
			//organizo el tipo de reporte para el JSP
		if (tipoReporte.equals("resumido")) tipoReporte = "detallado";
		else tipoReporte = "resumido";
		request.setAttribute("tipoNodo", tipoNodo);
		request.setAttribute("listaDLUs", listaDLUs);
		request.setAttribute("tipoReporte", tipoReporte);
		redireccionarConPlantilla("dlusTipoNodo", request, response);			
    }

    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        if (debug) logs.info("Peticion realizada");
        // en la variable operacion se obtiene la solicitud a tramitar por este servlet        
        String operacion = request.getParameter("operacion");
        
        UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
        
        if(usuario == null){
        	redireccionar(acciones.getLoginPage(),request,response);
        	return;
        }
        
        String level = usuario.getNivel();
        
        if(operacion != null && !operacion.equals("inicio") && !operacion.equals("inicioConsultaPruebas")
        && !operacion.equals("inicioConsultaRutinaPruebas") && !operacion.equals("abrirVentanaDetalles")
        && !operacion.equals("realizarConsultaRutinaPruebas") && !operacion.equals("inicioPruebasProgramadas")
        && !operacion.equals("realizarConsultaPrueba") && !operacion.equals("exportarInforme") 
        && !operacion.equals("mostrarGrafica") && !operacion.equals("consultaPruebasProgramadas")
        && !operacion.equals("estadisticoPruebasProgramadas") && !operacion.equals("mostrarGraficaEfectividad")){
        	if(level.equals("1")){
        		if(debug)logs.debug("ENTRO A LA CONDICION DE PERMISO");
        		response.sendRedirect("/sape/"+acciones.getRestrictedPage());
            	return;
        	}
        }
        
        if (debug)logs.debug("doGet: operacion=["+operacion+"]");
                
        if (operacion == null || operacion.equals("")||operacion.equals("inicio")) {
            // MUESTRA LA PAGINA INICIAL DE ESTOS REPORTES
            redireccionarConPlantilla("inicioReportes",request,response);
            //request.getRequestDispatcher(acciones.getTemplate("inicioReportes")).forward(request,response);
            return;
        } else if(operacion.equals("inicioConsultaPruebas")) {
            // DESPLIEGA LA PANTALLA INICIAL DE CONSULTA DE PRUEBAS.
            operacionConsultaPruebas(request,response);
            return;
        } else if(operacion.equals("realizarConsultaPrueba")) {
            // PARA REALIZAR UNA CONSULTA EN LA PANTALLA CONSULTA DE PRUEBAS.
            
            operacionConsultaPruebas(request,response);
            return;
        } else if(operacion.equals("mostrarGrafica")) {
            // MUESTRA GRAFICA CON INFORMACION, SOLO SI LA CABEZA ES DE OSP
            // SIPLEX PRO, Y SI TIENE INFORMACION EN LA TABLA.
            
            operacionMostrarGrafica(request, response);
            return;
        } else if(operacion.equals("exportarInforme")) {
        // CODIGO PARA EXPORTAR ALGUNA DE LAS PANTALLAS DEL SAPE AUN FORMATO.    
            operacionExportarInforme(request,response);
            return;
        } else if(operacion.equals("inicioConsultaTelefonosTSTLI")) {
            
        	realizarConsultaTelefonosTSTLI(request,response);
            return;
        } else if(operacion.equals("realizarConsultaTelefonosTSTLI")) {
            
            realizarConsultaTelefonosTSTLI(request,response);
            return;
        }  else if(operacion.equals("mostrarGraficaInicialTSTLI")) {
        	
        	operacionMostrarGraficaInicialTSTLI(request,response);
        	return;
        }else if(operacion.equals("inicioConsultaRutinaPruebas")) {
        	operacionRealizarConsultaRutinaPruebas(request,response);
        	return;
        } else if(operacion.equals("realizarConsultaRutinaPruebas")) {
        	
        	operacionRealizarConsultaRutinaPruebas(request,response);
        	return;
        }else if(operacion.equals("abrirVentanaDetalles")){
        	abrirVentanaDetalles(request,response);
        	return;
        }else if(operacion.equals("inicioEfectividadPrueba2")){
        	operacionRealizarConsultaEfectividadPrueba2(request,response);
        	return;
        } else if(operacion.equals("inicioEfectividadPrueba")) {
        	operacionRealizarConsultaEfectividadPrueba(request,response);
        	return;
//        } else if(operacion.equals("realizarConsultaEfectividadPrueba")) {//al parecer no se utiliza Andres dic 22 2005
//        	
//        	operacionRealizarConsultaEfectividadPrueba(request,response);
//        	return;
        } else if(operacion.equals("abrirVentanaEfectividad")) {
        	
        	operacionAbrirVentanaEfectividad(request,response);
        	return;
        } else if(operacion.equals("mostrarGraficaEfectividad")) {
        	
        	operacionMostrarGraficaEfectividad(request,response);
        	return;
        } else if(operacion.equals("inicioPruebasProgramadas")) {
        	consultaPruebasProgramadas(request,response);
        	return;
        } else if(operacion.equals("consultaPruebasProgramadas")) {
        	consultaPruebasProgramadas(request,response);
        	return;
        } else if ( operacion.equals("eliminarTipoPrueba") ){
        	
        	operacionEliminarTipoPrueba(request,response);
        	return;
        }
        
        if (operacion.equals("cprsTipoNodo")) {
        	cprsTipoNodo(request, response);
        	return;
        }
        if (operacion.equals("listaCabezasCentral")) {
        	listaCabezas(request, response);
        	return;
        }
        if (operacion.equals("dlusTipoNodo")) {
        	dlusTipoNodo(request, response);
        	return;
        }
        if (operacion.equals("lisTipoNodo")) {
        	lisTipoNodo(request, response);
        	return;
        }
        if(operacion.equals("estadisticoPruebasProgramadas")){
        	estadisticoPruebasProgramadas(request,response);
        	return;
        }
        if(operacion.equals("calificacionRutinas")){
        	
        	//request.setAttribute("tipo", "reporteDetalles");
        	operacionCalificacionRutinas(request,response);
        	return;
        }
        if(operacion.equals("mensualCalificacionRutinas")){
        	
        	operacionMensualCalificacionRutinas(request,response);
        	return;
        }
        	//En caso que pase por aqui muestro un mensaje de error
        error("La operacion " + operacion + " no esta definida.", request, response);
    }
    
    ////////////************METODOS DE CADA SOLICITUD***********////////////////
    
    
    private void operacionMensualCalificacionRutinas(HttpServletRequest request, HttpServletResponse response){
    	
    	String fIni=request.getParameter("fIni");
    	String fFin=request.getParameter("fFin");
    	
    	String valid=validarFechas(fIni,fFin);
    	
    	if(valid!=null&&!valid.equals("all_empty")){
    		error("Fecha invalida, por favor corregir",request,response);
    		return;
    	}
    	
    	if(valid!=null&&valid.equals("all_empty")){
    		fIni=getFechaHoy();
    		fFin=getFechaHoy();
    	}
    	
    	List l = null;
    	
    	try {
			l=pruebaProgramadaDAO.getEstadisticoMensualCalificacion(fIni+horaIni, fFin+horaFin);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("lista", l);
		request.setAttribute("fIni",fIni);
		request.setAttribute("fFin",fFin);
		
		redireccionarConPlantilla("calificacionMensualRutinas", request, response);
    }
    
    private void operacionCalificacionRutinas( HttpServletRequest request, HttpServletResponse response) {
    	
    	// parametros para la paginacion
    	
        String pagActual = request.getParameter("pagActual");
        String regPorPagina =request.getParameter("regPorPagina");
        String filtro = request.getParameter("filtro");
        String valorFiltro = request.getParameter("valorFiltro");
        
        String fIni = request.getParameter("fIni");
        String fFin = request.getParameter("fFin");

        String orderBy = request.getParameter("orderBy");
        
        
        // validaciones para los parametros
        
        if(filtro!=null&&filtro.equals(""))
        	filtro=null;
        if(valorFiltro!=null&&valorFiltro.equals(""))
        	valorFiltro=null;
        
        if(fIni==null||fIni.equals("")||fFin==null||fFin.equals("")){
        	fIni=getFechaHoy();
        	fFin=getFechaHoy();
        }
        	
        if(orderBy==null||orderBy.equals(""))
        	orderBy="idpruebaprogramada DESC";
        
        if(pagActual==null || pagActual.equals("")) pagActual = "1";
        try{
        	int pagina = Integer.parseInt(pagActual);
        	if(pagina <=0) pagActual = "1";
        }catch(NumberFormatException e){
        	pagActual = "1";
        }

        String offset ="";
        
        if(regPorPagina==null || regPorPagina.equals(""))
        	regPorPagina = "100";
        
        if(pagActual.equals("1"))
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regPorPagina));

        String tmpFiltro=filtro;
        if(filtro!=null&&filtro.equals("todas")){// se solicitan todos los registros
        	filtro=null;
        	valorFiltro="";
        }else if(filtro!=null&&filtro.equals("buenos")){
        	filtro="calificacionDatos";
        	valorFiltro="B";
        }else if(filtro!=null&&filtro.equals("malos")){
        	filtro="calificacionDatos";
        	valorFiltro="M";
        }else if(filtro!=null&&filtro.equals("regulares")){
        	filtro="calificacionDatos";
        	valorFiltro="R";
        }
        
    	List l1 ;
    	try{
    		l1 = pruebaProgramadaDAO.getRegistrosCalificacion(filtro, valorFiltro, fIni+horaIni, fFin+horaFin, regPorPagina, offset, orderBy);
    	}catch(SapeDataException e){
    		request.setAttribute("tipo","popup");
    		error(e,request,response);
    		return;
    	}
        
        double division = Double.parseDouble((String)l1.get(0))/Double.parseDouble(regPorPagina);
        int totalPaginas = (int)Math.ceil(division);
        if(totalPaginas <=1) totalPaginas = 0;
      
        request.setAttribute("offset",offset);
        request.setAttribute("regPorPagina",regPorPagina);
        request.setAttribute("pagActual",pagActual);
        request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
           
		request.setAttribute("cantidadTotalRegistros",((String)l1.get(0)));

    	filtro=(tmpFiltro==null?"":tmpFiltro);
		String msg = "";
    	if(filtro.equals("todas")&&!((String)l1.get(0)).equals("0")){
    		msg=", "+((String)l1.get(0))+" registros.";
    	} else if(filtro.equals("buenos")){
    		msg=", "+((String)l1.get(0))+" Buenos para Datos";
    	} else if(filtro.equals("malos")){
    		msg=", "+((String)l1.get(0))+" Malos para Datos";
    	} else if(filtro.equals("regulares")){
    		msg=", "+((String)l1.get(0))+" Regulares para Datos";
    	}
        
    	String query = "&pagActual="+pagActual+"&regPorPagina="+regPorPagina+
    					"&filtro="+filtro+"&fIni="+fIni+"&fFin="+fFin;
		request.setAttribute("query",query);
		
    	request.setAttribute("msgCentral", msg);
    	request.setAttribute("listaDetalles", l1.get(1));
    	request.setAttribute("fIni", fIni);
    	request.setAttribute("fFin", fFin);
    	request.setAttribute("filtro", filtro);
    	request.setAttribute("valorFiltro", (valorFiltro==null?"":valorFiltro));
    	
    	redireccionarConPlantilla("calificacionRutinas", request, response);
    }
    
    
    private void operacionEliminarTipoPrueba ( HttpServletRequest request, HttpServletResponse response) {
    	
    	String tipoPrueba = request.getParameter("tipoPrueba");
    	String transaccion = request.getParameter("transaccion");
    	System.out.println("Se eliminara la transaccion numero: "+transaccion);
    	
    	int trans = 0;
    	try{
    		trans = Integer.parseInt(transaccion);
    	}catch(NumberFormatException e){
    		error(e,request,response);
    		return;
    	}
    	
    	try {
			pruebasPPDAO.eliminarPruebaSPPPorTransaccion(trans,tipoPrueba);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		request.setAttribute("msg","Se realizo la actualizacion de la transaccion "+trans+" con exito.");
		request.setAttribute("url","/sape/actionSape?accion=reportes&operacion=inicioConsultaRutinaPruebas");
		redireccionarConPlantilla("msgGeneral",request,response);
    }
    

    private void operacionMostrarGraficaInicialTSTLI( HttpServletRequest request, HttpServletResponse response) {
    	
    	String tip = request.getParameter("tipo");
    	int tipo = 0;
    	try{
    		tipo = Integer.parseInt(tip);
    	}catch(NumberFormatException e){
    		error(e,request,response);
    		return;
    	}
    	
    	///////////////////////////////////////////
    	
    	

        String filtro = request.getParameter("filtro");
        String valorFiltro = request.getParameter("valorFiltro");
        
        String orderBy = request.getParameter("orderBy");
        

        if(filtro == null || filtro.equals("") ||filtro.equals("ninguno")){
        	filtro = "ninguno";
        	valorFiltro = "";
        }

        String cola = request.getParameter("cola");
        
        if(cola == null || cola.equals(""))
        	cola = "TSTLI";
        
        
        if(orderBy == null || orderBy.equals(""))
        	orderBy = "fecha DESC";
        
        String rpp = request.getParameter("regPorPagina");
        
        if (rpp == null || rpp.equals(""))
        	rpp = "100";
        
        int regXpag = Integer.parseInt(rpp);
        
        String pa = request.getParameter("pagActual");
        if (pa == null || pa.equals(""))
        	pa = "1";
        
        try{
        	Integer.parseInt(pa);
        }catch(NumberFormatException e){
        	pa = "1";
        }
        
        String offset = null;
        
        if(pa.equals("1"))
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pa)-1)*regXpag);
    	
    	////////////////////////////////////////
    	
        String fIni=	request.getParameter("fechaIni");
        String fFin=	request.getParameter("fechaFin");    	
    	
        if(fIni == null || fFin ==null){
        	fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        if(fIni.equals("") || fFin.equals("")){
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        String data = "";
    	try {
    		if(tipo==3)offset = request.getParameter("contador");
    		data = requerimientosColaDAO.graficasInicialesTSTLI(cola,tipo,filtro,valorFiltro,fIni,fFin,rpp,offset,orderBy);
		} catch (SapeDataException e) {
			request.setAttribute("tipo","cerrar");
			error(e,request,response);
			return;
		}
		
		request.setAttribute("datos",data);
		if(tipo==3){
			request.setAttribute("modoGrafica","3");
			String query = "actionSape?accion=reportes&operacion=mostrarGraficaInicialTSTLI&tipo=3&fechaIni="+fIni+"&fechaFin="+fFin+"&filtro="+filtro+"&valorFiltro="+valorFiltro+"&orderBy="+orderBy+"&regPorPagina="+regXpag+"&cola="+cola;
			request.setAttribute("contador",(offset == null ?"0":offset));
			request.setAttribute("queryString",query);
			
		}
		operacionMostrarGraficaEfectividad(request,response);
    }
    
    private void estadisticoPruebasProgramadas ( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		//el parametro opcion es el campo tipodeprueba de pruebaprogramada
        String prueba = request.getParameter("prueba");     
        // validar ke si venga la prueba(id de rutina)
        	//esta variable es para variar las pantallas que se muestran en este reporte.
        String modo = request.getParameter("modo");
        
    	// parametros para la paginacion CUANDO ESTA SE NECESITE
    	
        String pagActual = request.getParameter("pagActual");
        String regPorPagina =request.getParameter("regPorPagina");
        
        if(pagActual==null || pagActual.equals("")) pagActual = "1";
        try{
        	int pagina = Integer.parseInt(pagActual);
        	if(pagina <=0) pagActual = "1";
        }catch(NumberFormatException e){
        	pagActual = "1";
        }

        String offset ="";
        
        if(regPorPagina==null || regPorPagina.equals(""))
        	regPorPagina = "100";
        
        if(pagActual.equals("1"))
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regPorPagina));

        
        
    	// parametros
        Rutinas r = null;
        String tipoR = "";
        String labelRutina = "";
        try{
        	r = rutinasDAO.getRutina(Integer.parseInt(prueba));
        	labelRutina=r.getValorTipo();
        	if(r.getTipo().equals("CA")){
        		tipoR = "Cable";
        	}else if(r.getTipo().equals("AR")){
        		tipoR="Armario";
        	}else if(r.getTipo().equals("CL")){
        		tipoR="Cliente";
        	}else if(r.getTipo().equals("SE")){
        		tipoR="Serie";
        		StringTokenizer stt = new StringTokenizer(labelRutina,"-");
        		labelRutina = stt.nextToken()+"-"+stt.nextToken();
        	}
        	
        }catch(SapeDataException e){
        	error(e,request,response);
        	return;
        }
        
         	//Para mostrar el estadistico inicial del reporte!!!
        if(modo == null || modo.equals("")){
        	List l1 = null;
        	List l2 = null;
//        	List l3 = null;
        	String estado = null;
        	
        	try {
				l1 = pruebaProgramadaDAO.getEstadisticoCodigosVer(null,prueba,null,null);
				
				
				String valor = r.getValorTipo();
				if(r.getTipo().equals("CA")){
					l2 = rutinaCableDAO.getTelefonosPorCable(valor,estado);					
				}else if(r.getTipo().equals("AR")){
					l2= rutinaArmarioDAO.getTelefonosPorArmario(valor,estado);
				}else if(r.getTipo().equals("CL")){
					l2=rutinaClienteDAO.getRutinasClientePorEstado(valor,estado);
				}else if(r.getTipo().equals("SE")){
					l2 = null;
				}
				
				request.setAttribute("estaProgramado",(r.getHabilitado()?"yes":"no"));
				
						
			} catch (SapeDataException e) {
				error(e,request,response);
				return;
			}

			if(l2 != null){
				request.setAttribute("estadistica",l2.get(1));
			}
			request.setAttribute("tipo","inicial");
			request.setAttribute("listaDetalles",l1.get(0));
			//request.setAttribute("listaTipos",l1.get(1));
			request.setAttribute("msgCentral","Estadistico por Codigos Ver");
			request.setAttribute("idPrueba",prueba);
			request.setAttribute("labelRutina", labelRutina);
			request.setAttribute("labelTipoRutina", tipoR );
			redireccionarConPlantilla("pruebaProgramada", request, response);
			//request.getRequestDispatcher(acciones.getTemplate("pruebaProgramada")).forward(request,response);
			return;
        } else if ( modo.equals("consultaPorCodigoVer") ){
        	String codv = request.getParameter("codv");
        	
        	// parametros para la paginacion
        	
        	List l1 ;
        	try{
        		l1 = pruebaProgramadaDAO.getEstadisticoCodigosVer(codv,prueba, regPorPagina, offset);
        	}catch(SapeDataException e){
        		request.setAttribute("tipo","popup");
        		error(e,request,response);
        		return;
        	}
        	
            double division = ((Integer)l1.get(1)).doubleValue()/Double.parseDouble(regPorPagina);
            int totalPaginas = (int)Math.ceil(division);
            if(totalPaginas <=1) totalPaginas = 0;
          
            request.setAttribute("offset",offset);
            request.setAttribute("regPorPagina",regPorPagina);
            request.setAttribute("pagActual",pagActual);
            request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
               
            String query = "&prueba="+prueba+"&modo="+modo+"&codv="+codv+
          				   "&pagActual="+pagActual+"&regPorPagina="+regPorPagina;
          
			request.setAttribute("query",query);
			request.setAttribute("cantidadTotalRegistros",((Integer)l1.get(1)).doubleValue());
		
			request.setAttribute("tipo","patallaPorCodigos");
			request.setAttribute("listaDetalles",l1.get(0));
			request.setAttribute("codv",codv);
			request.setAttribute("idPrueba",prueba);
			request.setAttribute("labelRutina", labelRutina);
			request.setAttribute("labelTipoRutina", tipoR);
			
			
			//para la paginacion
			
			
			request.getRequestDispatcher(acciones.getTemplate("ventanaDetallesRutinaPruebas")).forward(request,response);
			return;
        	
        } else if (modo.equals("detallesPruebasProgramadas")){
        
	        List l = null;
	        
			try{
				
				l= pruebaProgramadaDAO.getPruebasProgramadas(prueba, regPorPagina, offset);
				

				//parametros para la paginacion
				
	            double division = ((Integer)l.get(1)).doubleValue()/Double.parseDouble(regPorPagina);
	            int totalPaginas = (int)Math.ceil(division);
	            if(totalPaginas <=1) totalPaginas = 0;
	          
	            request.setAttribute("offset",offset);
	            request.setAttribute("regPorPagina",regPorPagina);
	            request.setAttribute("pagActual",pagActual);
	            request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
	               
	            String query = "&prueba="+prueba+"&modo="+modo+"&pagActual="+pagActual+"&regPorPagina="+regPorPagina;
	          
				request.setAttribute("query",query);
				request.setAttribute("cantidadTotalRegistros",((Integer)l.get(1)).doubleValue());
				
				// parametros
				
				
				if(prueba != null && !prueba.equals("")){
					request.setAttribute("listaDetalles",l.get(0));
					request.setAttribute("tipo","detalles");
					request.setAttribute("msgCentral","Numero de registros: "+((Integer)l.get(1))+" para la rutina por "+tipoR+" "+labelRutina);
				}
				//request.setAttribute("listaTipos",l.get(1));
				
			}catch(SapeDataException e){
				error(e,request,response);
				return;
			}
			/*este atributo es para ke el jsp ventanaDetallesRutinaPruebas.jsp no coloke el 
			boton de cerrar*/
			
			
			request.setAttribute("idPrueba",prueba);
			request.setAttribute("labelRutina", labelRutina);
			request.setAttribute("labelTipoRutina", tipoR );
			request.getRequestDispatcher(acciones.getTemplate("pruebaProgramada")).forward(request,response);
			
        } else if( modo.equals("actualizarEstadoPruebasProgramadas")){
        	int cuantos = 0;
        	
        	String codv = request.getParameter("codv");
        	try{
        		//Rutinas r = rutinasDAO.getRutina(Integer.parseInt(prueba));
        		cuantos = pruebaProgramadaDAO.actualizarEstadosPruebasProgramadas(r.getTipo(),codv,prueba,r.getValorTipo());
        	}catch(SapeDataException e){
        		error(e,request,response);
        		return;
        	}
        	
        	if(cuantos != -1){
        		
        		System.out.println("Entro a la condicion!!!");
        		response.setContentType("text/html");
        		PrintWriter out = response.getWriter();
        		out.println("<script language=\"javascript\">");
        		out.println("alert('Se actualizaron "+cuantos+" Registros con exito!');");
        		out.println("location.href = \"/sape/actionSape?accion=reportes&operacion=estadisticoPruebasProgramadas&prueba="+prueba+"\"");
        		out.println("</script>");
        		
        		return;
        	} else {
        		error("No fue posible realizar la actualizacion.",request,response);
        		return;
        	}
        		
        	
        	//response.setContentType("text/html");
        	
        	//response.getWriter().println("Se actualizaron con exito "+cuantos+" registros.");
        }
    }
    
    private void consultaPruebasProgramadas ( HttpServletRequest request, HttpServletResponse response) {
    	
        String fIni=request.getParameter("fechaIni");
        String fFin=request.getParameter("fechaFin");
        
        String pagActual = request.getParameter("pagActual");
        //String offset =request.getParameter("offset");
        String regPorPagina =request.getParameter("regPorPagina"); 
        String orderBy =request.getParameter("orderBy");
    	
        //String user = request.getParameter("usuario");
        
        String opcion = request.getParameter("opcion");
        String valorOpcion = request.getParameter("valorOpcion");
        
        if(opcion!=null&&!opcion.equals("")){
        	if(opcion.equals("todos")){
        		opcion = "";
        		valorOpcion = "";
        	}
        }
        
        if(pagActual==null || pagActual.equals("")) pagActual = "1";
        try{
        	int pagina = Integer.parseInt(pagActual);
        	if(pagina <=0) pagActual = "1";
        }catch(NumberFormatException e){
        	pagActual = "1";
        }
        
        if(fIni == null || fFin == null || fIni.equals("") || fFin.equals("")){
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        request.setAttribute("fIni",fIni);
        request.setAttribute("fFin",fFin); 
        
        String offset = "";
        
        if(regPorPagina==null || regPorPagina.equals(""))
        	regPorPagina = "100";
        
        if(pagActual.equals("1"))
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regPorPagina));
        
        if(orderBy==null || orderBy.equals(""))
        	orderBy = "fechaProgramada DESC";

        //user = (user!=null&&user.equals("")?null:user);
        
        List<Rutinas> listaRutinas = null;
        List registros = null;
        try{
        	// esta consulta devuelve una lista de 2 pocisiones:
        	//	[0] lista con todos los registros de la consulta.
        	//	[1] entero con el conteo de la consulta.
        	registros=rutinasDAO.getRutinas(opcion,valorOpcion, fIni+horaIni, fFin+horaFin, regPorPagina, offset, orderBy);
        }catch(SapeDataException e){
        	error(e,request,response);
        	return;
        }
        //lkjlk
        
        listaRutinas = (List<Rutinas>) registros.get(0);
        
        int cantidadRegistros = ((Integer)registros.get(1)).intValue();
        
        request.setAttribute("fIni",fIni);
        request.setAttribute("fFin",fFin);
        
        double division = ((double)cantidadRegistros)/Double.parseDouble(regPorPagina);
		if(debug)logs.debug(" la division da: " + division + ". Ceil: " + Math.ceil(division) + ". toInt " + (int) Math.ceil(division));
		int totalPaginas = (int)Math.ceil(division);
		if(totalPaginas <=1) totalPaginas = 0;
	  
		request.setAttribute("orderBy",orderBy);
		request.setAttribute("offset",offset);
		request.setAttribute("regPorPagina",regPorPagina);
		request.setAttribute("pagActual",pagActual);
		request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
		request.setAttribute("opcion",(opcion==null||opcion.equals("")?"":opcion));
		request.setAttribute("valorOpcion",(opcion==null||valorOpcion.equals("")?"":valorOpcion));
		String query = "&fechaIni="+fIni+"&fechaFin="+fFin+"&opcion="+opcion+"&valorOpcion="+valorOpcion;//+(user==null?"":user);
        request.setAttribute("query",query);
		
        request.setAttribute("listaRutinas", listaRutinas);
        request.setAttribute("msgCentral","Rutinas Programadas");
        
        
        redireccionarConPlantilla("pruebasProgramadas", request, response);
        
    	/*
    		//el parametro opcion es el campo tipodeprueba de pruebaprogramada
        String prueba = request.getParameter("prueba");     
        
        	//esta variable es para variar las pantallas que se muestran en este reporte.
        String modo = request.getParameter("modo");
        
         	//Este es el primer acceso a la pantalla
        if(prueba == null || prueba.equals("")){
        	List l1 = new ArrayList();
        	try{
        		l1 = pruebaProgramadaDAO.getListaInicialPruebasProgramadas();
        	}catch(SapeDataException e){
        		error(e,request,response);
        		return;
        	}
        	request.setAttribute("listaTipos",l1);
        	request.setAttribute("msgCentral","Seleccione un tipo de Prueba.");
        	request.getRequestDispatcher(acciones.getTemplate("pruebaProgramada")).forward(request,response);
        	return;
        }
        
        
         	//Para mostrar el estadistico inicial del reporte!!!
        if(modo == null || modo.equals("")){
        	List l1 = null;
        	List l2 = null;
        	List l3 = null;
        	String estado = null;
        	try {
				l1 = pruebaProgramadaDAO.getEstadisticoCodigosVer(null,prueba);
				System.out.println("PASO EL ESTADISTICO POR CODSV");				
				if (prueba.startsWith("CA")) {
					l2 = rutinaCableDAO.getTelefonosPorCable(prueba.substring(3),estado);
					l3 = horaPruebaCableDAO.getAllCables(); 
				} else if (prueba.startsWith("CL")) {
					l2 = rutinaClienteDAO.getRutinasClientePorEstado(prueba.substring(3),estado);
				} else if (prueba.startsWith("AR")){
					l2 = rutinaArmarioDAO.getTelefonosPorArmario(prueba.substring(3),estado);
					l3 = horaPruebaArmarioDAO.getAllArmarios();
				}
				
				if(l3 != null && l3.size() > 0){
					int flag = -1;
					for(int i=0; i < l3.size(); i++){
						String prob = prueba.substring(3);
						String probTabla = (String)l3.get(i);
						if(prob.equals(probTabla)){
							//No hay problema!!!!!!
							flag = 0;
						}
					}
					if(flag == 0){// el elemento no esta programado!!!!
						request.setAttribute("estaProgramado","yes");
					}else{
						request.setAttribute("estaProgramado","no");
					}
				}else{
					request.setAttribute("estaProgramado","no");
				}
//				System.out.println("la lista de reportes inicial tiene ="+l1.size());
			} catch (SapeDataException e) {
				error(e,request,response);
				return;
			}

			
			request.setAttribute("estadistica",l2.get(1));
			request.setAttribute("tipo","inicial");
			request.setAttribute("listaDetalles",l1.get(0));
			request.setAttribute("listaTipos",l1.get(1));
			request.setAttribute("msgCentral","Estadistico por Codigos Ver");
			request.setAttribute("idPrueba",prueba);
			request.getRequestDispatcher(acciones.getTemplate("pruebaProgramada")).forward(request,response);
			return;
        } else if ( modo.equals("consultaPorCodigoVer") ){
        	String codv = request.getParameter("codv");
        	List l1 ;
        	try{
        		l1 = pruebaProgramadaDAO.getEstadisticoCodigosVer(codv,prueba);
        	}catch(SapeDataException e){
        		request.setAttribute("tipo","popup");
        		error(e,request,response);
        		return;
        	}
        	
			request.setAttribute("tipo","patallaPorCodigos");
			request.setAttribute("listaDetalles",l1.get(0));
			request.setAttribute("codv",codv);
			request.setAttribute("idPrueba",prueba);
			request.getRequestDispatcher(acciones.getTemplate("ventanaDetallesRutinaPruebas")).forward(request,response);
			return;
        	
        } else if (modo.equals("detallesPruebasProgramadas")){
        
	        List l = null;
	        
			try{
				
				l= pruebaProgramadaDAO.getPruebasProgramadas(prueba);
				
				if(prueba != null && !prueba.equals("")){
					request.setAttribute("listaDetalles",l.get(0));
					request.setAttribute("tipo","detalles");
					request.setAttribute("msgCentral","Numero de registros: "+((List)l.get(0)).size());
				}
				request.setAttribute("listaTipos",l.get(1));
				
			}catch(SapeDataException e){
				error(e,request,response);
				return;
			}
			/*este atributo es para ke el jsp ventanaDetallesRutinaPruebas.jsp no coloke el 
			boton de cerrar*
			
			
			request.setAttribute("idPrueba",prueba);
			request.getRequestDispatcher(acciones.getTemplate("pruebaProgramada")).forward(request,response);
			
        } else if( modo.equals("actualizarEstadoPruebasProgramadas")){
        	int cuantos = 0;
        	
        	String codv = request.getParameter("codv");
        	try{
        		cuantos = pruebaProgramadaDAO.actualizarEstadosPruebasProgramadas(prueba,codv);
        	}catch(SapeDataException e){
        		error(e,request,response);
        		return;
        	}
        	
        	if(cuantos != -1){
        		
        		System.out.println("Entro a la condicion!!!");
        		response.setContentType("text/html");
        		PrintWriter out = response.getWriter();
        		out.println("<script language=\"javascript\">");
        		out.println("alert('Se actualizaron "+cuantos+" Registros con exito!');");
        		out.println("location.href = \"/sape/actionSape?accion=reportes&operacion=consultaPruebasProgramadas&prueba="+prueba+"\"");
        		out.println("</script>");
        		
        		return;
        	} else {
        		error("No fue posible realizar la actualizacion.",request,response);
        		return;
        	}
        		
        	
        	//response.setContentType("text/html");
        	
        	//response.getWriter().println("Se actualizaron con exito "+cuantos+" registros.");
        }*/
    }
    
    private void operacionMostrarGraficaEfectividad(HttpServletRequest request, HttpServletResponse response) {
    	
        String grafica = request.getParameter("datos");
        //XXX esto es para que coloque un boton de cerrar en el jsp de error si pasa algo!!!!
        request.setAttribute("tipo","cerrar");
        
        if(grafica == null || grafica.equals("")){
        	
        	grafica = (String)request.getAttribute("datos");
        }
        if(grafica == null || grafica.equals("")){
        	error("No se encontraron datos para graficar. Campo Vacio!!!",request,response);
        	return;
        }
    	
    	request.setAttribute("datos",grafica);
        
    	redireccionarConPlantilla("graficaEfectividadPruebas",request,response);
        //request.getRequestDispatcher(acciones.getTemplate("graficaEfectividadPruebas")).forward(request,response);
    }
    
    
    private void operacionAbrirVentanaEfectividad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcion");     
        String fIni=	request.getParameter("fechaIni");
        String fFin=	request.getParameter("fechaFin");
        
        //XXX SE DEBE CUIDAR LAS CONSULTAS POR FECHA DE EL JSP DE EFECTIVIDAD!!!!! 
        String pagActual = request.getParameter("pagActual");
        String regPorPagina =request.getParameter("regPorPagina"); 
        String orderBy =request.getParameter("orderBy");
        
//        System.out.println("registros por pagina: "+regPorPagina);
//        System.out.println("la opcion: "+opcion);
        
        String estado = request.getParameter("estado");
                
        if(fIni == null || fFin ==null){
        	fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        if(fIni.equals("") || fFin.equals("")) {
            fIni = getFechaHoy();
        	fFin = getFechaHoy();            	
        }
        
        try{
        	int pagina = Integer.parseInt(pagActual);
        	if(pagina <= 0)
        		pagActual = "1";
        }catch(NumberFormatException e){
        	pagActual = "1";
        }
        
        if(estado == null || estado.equals(""))
        	estado="todos";
        
        String offset ="";
        
        if(regPorPagina==null)
        	regPorPagina = "100";
        if(regPorPagina.equals(""))
        	regPorPagina = "100";
        
        if(pagActual==null)
        	pagActual = "1";
        if(pagActual.equals(""))
        	pagActual = "1";
                
        if(pagActual.equals("1"))
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regPorPagina));
        	
        if(orderBy==null || orderBy.equals("")) {
       		orderBy = " ticket DESC";	
        }
              
        String usuario = request.getParameter("usuario");
        
        if(usuario != null && usuario.equals("todos"))
        	usuario = null;
        
        


            String filtro = (opcion.equals("todos"))?"":"estado";
            
            if(opcion.equals("reportePorSite")){
            	filtro = opcion;
            	opcion = request.getParameter("site");
            }
            
            List l = null;
            try {            	
            l = reportesDAO.getRegistros(filtro,opcion,usuario,fIni,fFin,regPorPagina,offset,orderBy);
        	} catch (SapeDataException e) {
        		logs.error(e);
        		request.setAttribute("tipo","popup");
        		error(e,request,response);
        		return;
        	}
            
            request.setAttribute("listaEventos",l.get(1));
            
            request.setAttribute("fIni",fIni);
            request.setAttribute("fFin",fFin);
            
            if (debug) logs.debug("la opcion es: "+opcion+" el filtro es: "+filtro);
            
            if(filtro.equals("reportePorSite")) {
            	request.setAttribute("opcion","reportePorSite&site="+opcion);
            } else {
            	request.setAttribute("opcion",opcion);
            }
            
            request.setAttribute("offset",offset);
            request.setAttribute("regPorPagina",regPorPagina);
            request.setAttribute("pagActual",pagActual);

            double division = Double.parseDouble((String)l.get(0))/Double.parseDouble(regPorPagina);
            int totalPaginas = (int)Math.ceil(division);
            if(totalPaginas <=1) totalPaginas = 0;
            
            
            
            request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
            
//            System.out.println("el total de paginas es: '"+String.valueOf(totalPaginas)+"'");
            
            String query = null;
            if(filtro.equals("reportePorSite")) {
            	query = "&opcion=reportePorSite&site="+opcion+"&fechaIni="+fIni+"&fechaFin="+fFin;
            } else {
            	query = "&opcion="+opcion+"&fechaIni="+fIni+"&fechaFin="+fFin;
            }
            
            
            request.setAttribute("query",query);
            request.setAttribute("orderBy",orderBy);
            request.setAttribute("cantidadTotalRegistros",l.get(0));
            
            String opcion2 = opcion;
            if(filtro.equals("reportePorSite")){
            	
            	opcion2 = " "+request.getParameter("site");
            }else if(opcion2.equals("E"))
            	opcion2= "Exito";
            else if(opcion2.equals("F"))
            	opcion2 = "No Exito";
            else if(opcion2.equals("N"))
            	opcion2="Inesperados";
            else if(opcion2.equals("A"))
            	opcion2 = "Advertencia";
            else if(opcion2.equals("EA"))
            	opcion2 = "Exito + Advertencia";
            else
            	opcion2 = "TODOS"+(usuario != null?" los registros para el usuario '"+usuario+"'":"");
            //request.setAttribute("msgCentral","Numero de registros "+l.get(0)+", Rango de fechas: "+fIni+" a "+fFin+". Parametro de busqueda: <b>"+opcion2+"</b>");
            request.setAttribute("msgCentral","Numero de registros "+l.get(0)+". Parametro de busqueda: <b><font color=\"red\">"+opcion2+"</font></b>");
            request.getRequestDispatcher(acciones.getTemplate("ventanaDetallesEfectividadPrueba")).forward(request,response);
    }
    
    private void operacionRealizarConsultaRutinaPruebas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String opcion = request.getParameter("opcion");     
        String fIni=	request.getParameter("fechaIni");
        String fFin=	request.getParameter("fechaFin");
        
//        System.out.println("LAS FECHAS!!!: "+fIni+" - "+fFin);
//        System.out.println("request query: "+request.getQueryString());
        String pagActual = request.getParameter("pagActual");
        String regPorPagina =request.getParameter("regPorPagina"); 
        String orderBy =request.getParameter("orderBy");
        
//        System.out.println("registros por pagina: "+regPorPagina);
        
        try{
        	int pagina = Integer.parseInt(pagActual);
        	if(pagina <=0)
        		pagina = 0;
        }catch(NumberFormatException e){
        	pagActual = "1";
        }
        if(opcion == null)
        	opcion="todos";
        if(opcion.equals(""))
        	opcion="todos";
        
        String offset ="";
        
        if(regPorPagina==null)
        	regPorPagina = "100";
        if(regPorPagina.equals(""))
        	regPorPagina = "100";
        
        if(pagActual==null)
        	pagActual = "1";
        if(pagActual.equals(""))
        	pagActual = "1";
                
        if(pagActual.equals("1"))
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regPorPagina));
        
        if(orderBy==null)
        	orderBy = "transaccion DESC";
        if(orderBy.equals(""))
        	orderBy = "transaccion DESC";
        
        if(fIni == null || fFin ==null){
        	fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        if(fIni.equals("") || fFin.equals("")){
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        List l = null;
        try {
        	//cantidad total de registros que devolvio la consulta.
//            String cantReg = "0";
            l= pruebaProgramadaDAO.getRegistros(opcion,fIni,fFin,regPorPagina,offset,orderBy);
            
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        request.setAttribute("listaEventos",l.get(1));
        
        
        request.setAttribute("fIni",fIni);
        request.setAttribute("fFin",fFin);
        

        request.setAttribute("opcion",opcion);
        request.setAttribute("offset",offset);
        request.setAttribute("regPorPagina",regPorPagina);
        request.setAttribute("pagActual",pagActual);

        int totalPaginas = ((Integer)l.get(0)).intValue()/Integer.parseInt(regPorPagina);
        if(totalPaginas <= 1)
        	totalPaginas = 0;
        request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
        
        System.out.println("el total de paginas es: '"+String.valueOf(totalPaginas)+"'");
        String query = "&opcion="+opcion+"&fechaIni="+fIni+"&fechaFin="+fFin;
        request.setAttribute("query",query);
        request.setAttribute("orderBy",orderBy);
        request.setAttribute("cantidadTotalRegistros",l.get(0).toString());
        
        String opcion2 = opcion;
        if(opcion2.equals("CL"))
        	opcion2= "Cliente";
        else if(opcion2.equals("AR"))
        	opcion2 = "Armario";
        else if(opcion2.equals("CA"))
        	opcion2="Cable";
        else
        	opcion2 = "todos";
        request.setAttribute("msgCentral","Numero de registros "+l.get(0)+", Rango de fechas: "+fIni+" a "+fFin+". Parametro de busqueda: <b>"+opcion2+"</b>");        
        request.getRequestDispatcher(acciones.getTemplate("consultaRutinaPruebas")).forward(request,response);
            
    }

    
    private void abrirVentanaDetalles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String ticketDetalle = request.getParameter("ticket");
    	int ticketPruebaProg = 0;
    	String tipo=request.getParameter("tipo");
    	try {
    		if(tipo == null || tipo.equals(""))
    			ticketPruebaProg=Integer.parseInt(ticketDetalle);
    	}catch(NumberFormatException err){
    		request.setAttribute("tipo","cerrar");
    		error(err,request,response);
    		return;
    	}
    	
    	List listaDetalles = null;
    	
    	try{
    		//TODO para que es este if???
    		if(tipo != null && !tipo.equals("")){
    			if (tipo.equals("patallaPorCodigos")) {
    				String codv = request.getParameter("codv");
//    				System.out.println("el codigo ver es:"+codv);
    				listaDetalles = pruebaProgramadaDAO.getEstadisticoCodigosVer(codv,ticketDetalle,null,null);
    				listaDetalles = (List)listaDetalles.get(0);
    			} else {
    				listaDetalles= pruebaProgramadaDAO.getPruebasProgramadas(ticketDetalle,null,null);
    				listaDetalles= (List)listaDetalles.get(0);
    			}
    			
    		}else{
    			listaDetalles=pruebasPPDAO.getRegistros(ticketPruebaProg);
    		}
    	}catch (SapeDataException e) {
    		request.setAttribute("tipo","cerrar");
			error(e.toString(),request,response);
			return;
		}
    	
    	request.setAttribute("listaDetalles",listaDetalles);
    	request.setAttribute("idPrueba",ticketDetalle);
    	
        String exportar = request.getParameter("exportar");
        if ( exportar != null && exportar.equals("yes") ) {
        	request.getRequestDispatcher("/actionSape?accion=reportes&operacion=exportarInforme&pantalla=ventanaDetalles").forward(
                    request, response);
        	return;
        }
    	   	
    	request.getRequestDispatcher(acciones.getTemplate("ventanaDetallesRutinaPruebas")).forward(request,response);
    }
    
    //TODO: operacionRealizarConsultaEfectividadPrueba2() es solo para pruebas. Borrar despues de que la efectividad
    // 		se estabilice.
    private void operacionRealizarConsultaEfectividadPrueba2(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if (debug) logs.debug("operacionRealizarConsultaEfectividadPrueba2");
        
        String fIni=request.getParameter("fechaIni");
        String fFin=request.getParameter("fechaFin");
                
        if(fIni == null || fFin ==null){
        	fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        if(fIni.equals("") || fFin.equals("")){
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        request.setAttribute("fIni",fIni);
        request.setAttribute("fFin",fFin);
               
        try {
            //List l = eventoSapeDAO.getEfectividadPrueba(usuario,fIni+horaIni,fFin+horaFin);
            
        	// TODO: borrar el metodo getEfectividadPrueba2 del dao eventoSapeDAOImpl.
        	List l = eventoSapeDAO.getEfectividadPrueba2(fIni+horaIni,fFin+horaFin);
        	
            request.setAttribute("A",l.get(1));
            request.setAttribute("E",l.get(2));
            request.setAttribute("F",l.get(3));
            request.setAttribute("N",l.get(4));
            
            
            request.setAttribute("tiempoPromedio",l.get(6));
            //lista centrales contiene arreglos de objetos con el nombre de la central(site)
            //y el numero de registros por esa central
            request.setAttribute("listaCentrales",l.get(7));

            
            request.setAttribute("cantidadTotalRegistros",l.get(0));
            
            request.setAttribute("msgCentral","Numero de registros "+l.get(0)+", Rango de fechas: "+fIni+" a "+fFin+".");
            

            String exportar = request.getParameter("exportar");
            if(exportar != null && exportar.equals("yes")){

			if(debug)logs.debug("Enviando informacion para exportar");
			operacionExportarInforme(request,response);

           	return;
            }
            
            
            
            redireccionarConPlantilla("consultaEfectividadPrueba", request, response);
            
        } catch (SapeDataException e) {
            error(e, request, response);
        }    	
    }
    
    
    
    private void operacionRealizarConsultaEfectividadPrueba(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if (debug) logs.debug("operacionRealizarConsultaEfectividadPrueba");
    
        String fIni=request.getParameter("fechaIni");
        String fFin=request.getParameter("fechaFin");
                
        if(fIni == null || fFin ==null){
        	fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        if(fIni.equals("") || fFin.equals("")){
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        request.setAttribute("fIni",fIni);
        request.setAttribute("fFin",fFin);
               
        try {
            //List l = eventoSapeDAO.getEfectividadPrueba(usuario,fIni+horaIni,fFin+horaFin);
            
        	List l = eventoSapeDAO.getEfectividadPrueba(fIni+horaIni,fFin+horaFin);
        	
            request.setAttribute("A",l.get(1));
            request.setAttribute("E",l.get(2));
            request.setAttribute("F",l.get(3));
            request.setAttribute("N",l.get(4));
            
            
            request.setAttribute("tiempoPromedio",l.get(6));
            //lista centrales contiene arreglos de objetos con el nombre de la central(site)
            //y el numero de registros por esa central
            request.setAttribute("listaCentrales",l.get(7));

            
            request.setAttribute("cantidadTotalRegistros",l.get(0));
            
            request.setAttribute("msgCentral","Numero de registros "+l.get(0)+", Rango de fechas: "+fIni+" a "+fFin+".");
            

            String exportar = request.getParameter("exportar");
            if(exportar != null && exportar.equals("yes")){
    		if(debug)logs.debug("Enviando informacion para exportar");
    		operacionExportarInforme(request,response);
           	return;
            }
            
            
            
            redireccionarConPlantilla("consultaEfectividadPrueba", request, response);
            
        } catch (SapeDataException e) {
            error(e, request, response);
        }
    }
    
    
    private void realizarConsultaTelefonosTSTLI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String fInicio=request.getParameter("fechaIni");
        String fFin=request.getParameter("fechaFin");
        String filtro = request.getParameter("filtro");
        String valorFiltro = request.getParameter("valorFiltro");
        
        String cola = request.getParameter("cola");
        
        if(cola == null || cola.equals("")){
        	cola = "TSTLI";
        }
        
        String orderBy = request.getParameter("orderBy");
        
        if(fInicio == null || fInicio.equals("") || fFin == null || fFin.equals("")){
            fInicio = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        if(filtro == null || filtro.equals("") ||filtro.equals("ninguno")){
        	filtro = "ninguno";
        	valorFiltro = "";
        }

        if(orderBy == null || orderBy.equals(""))
        	orderBy = "fecha DESC";
        
        String rpp = request.getParameter("regPorPagina");
        
        if (rpp == null || rpp.equals(""))
        	rpp = "100";
        
        int regXpag = Integer.parseInt(rpp);
        
        String pa = request.getParameter("pagActual");
        if (pa == null || pa.equals(""))
        	pa = "1";
        
        try{
        	Integer.parseInt(pa);
        }catch(NumberFormatException e){
        	pa = "1";
        }
        
        String offset = null;
        
        if(pa.equals("1"))
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pa)-1)*regXpag);

        List lista = null;

        String exportar = request.getParameter("exportar");
        
        if(exportar != null && exportar.equals("yes")){
        	rpp = "0";// para ke me traiga todos lo registros y no pagine el resultado!!
        }
        
        
        try {
        	/**
        	 * Este metodo retorna los registros de una cola cualkiera
        	 * (TSTLI es la cola por default!!!)
        	 */
            lista = requerimientosColaDAO.getRegistrosTSTLI(cola,filtro,valorFiltro,fInicio,fFin,rpp,offset,orderBy);
            
        } catch (SapeDataException e) {
            error(e, request,response);
            return;
        }
        double division = 0;
        if(!rpp.equals("0"))
        	division = ((Long)lista.get(0)).longValue()/Double.parseDouble(rpp);
        int totalPaginas = (int)Math.ceil(division);
        if(totalPaginas <=1) totalPaginas = 0;
        
        String query = "&filtro="+filtro+"&valorFiltro="+valorFiltro+"&fechaIni="+fInicio+"&fechaFin="+fFin+"&cola="+cola;
        
        request.setAttribute("listaRequerimientos",lista.get(1));

        request.setAttribute("msgCentral","Numero de registros "+lista.get(0)+" para la cola "+cola+", Rango de fechas: "+fInicio+" a "+fFin);
        request.setAttribute("fIni",fInicio);
        request.setAttribute("fFin",fFin);
        request.setAttribute("query",query);
        
        request.setAttribute("cola",cola);
        request.setAttribute("filtro",filtro);
        request.setAttribute("valorFiltro",valorFiltro);
        request.setAttribute("regPorPagina",rpp);
        request.setAttribute("pagActual",pa);
        request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
        request.setAttribute("orderBy",orderBy);
        
        
        /*La variable exportar verifica si la solicitud es de consulta solamente o
         * se requiere exportar algun pantallazo a determinado formato(xls,cvs,pdf)
         */
        
        if(exportar != null && exportar.equals("yes")){
		if(debug)logs.debug("Enviando informacion para exportar");
		operacionExportarInforme(request,response);
        	
        	return;
        }
        
        request.getRequestDispatcher(acciones.getTemplate("consultaTelefonosTSTLI")).forward(request,response);
        return;
    }
    
     
//    private void operacionInicioConsultaTelefonosTSTLI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	//TODO borrar este metodo!!!!
//        List lista=null;
//        try {
//            lista = requerimientosColaDAO.getRequerimientosTSTLI("CURRENT_DATE");
//        } catch (SapeDataException e) {
////            e.printStackTrace();
//            logs.error("operacionInicioConsultaTelefonosTSTLI: " + e);
//            error(e,request,response);
//            return;
//        }
//        
//        request.setAttribute("listaRequerimientos",lista);
//        request.setAttribute("msgCentral","Numero de registros para el dia de hoy: "+lista.size());
//        
//        Calendar cal = Calendar.getInstance();
//        int ano = cal.get(Calendar.YEAR);
//        int mes=cal.get(Calendar.MONTH)+1;
//        int day=cal.get(Calendar.DAY_OF_MONTH);
//        request.setAttribute("fIni",""+ano+"-"+mes+"-"+day);
//        request.setAttribute("fFin",""+ano+"-"+mes+"-"+day);
//        
//        
//        request.getRequestDispatcher(acciones.getTemplate("consultaTelefonosTSTLI")).forward(request,response);
//    }
    
    
	public void operacionExportarInforme(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean log = debug;

        String formato = request.getParameter("formato");
        String pantalla = request.getParameter("pantalla");
        
        String nombre_archivo = null;

        if(log) logs.debug("operacionExportarInforme: formato="+formato+" pantalla="+pantalla);
        
        CustomDataSource cd = getDataSourceObject(pantalla,request,response);
        
        //IObjectProfileNode profile = ObjectProfiler.profile (cd);
        //logs.debug("Tamano del datasource = " + profile.size () + " bytes");
        
        if(cd == null) {
        
        	error("ERROR al acceder a la informacion de la pantalla "+pantalla,request,response);
        	return;
        }
        //TODO JASPER ojo con esta condicion hasta ke no se encuentre la manera de optimizar
        // el exporte de informacion a excel se debe tratar de dejar asi!!!!!
        // 20-04-2006
        /*if(cd.getData().length > 7000){
        	
        	error("Demasiados registros para exportar.",request,response);
        	return;
        }*/
        
        if(debug) logs.debug("SALIO DEL METODO getDataSourceObject, ya tiene el customDataSource");
        
        JasperReport jr;
        Map parameters = new HashMap(); // para enviarle parametros al reporte!!!!
        try {
            System.out.println("el path "+request.getContextPath());
            
            String path = getServletContext().getRealPath("/");
            
            System.out.println("el path real: ["+path+"]");
            
            File f = null;
            String tituloReporte ="REPORTE SAPE ";
            
            
            String plantilla = pantalla+"-export";
            
            String tmp=acciones.getTemplate(plantilla);
            
            //Del string tmp obtengo la ruta de la plantilla y el titulo del reporte.
            StringTokenizer stt = new StringTokenizer(tmp,";");
            
            f = new File(path+stt.nextToken());
            
            tituloReporte += stt.nextToken();
            
            
            if(pantalla.equals("ventanaDetalles")){//RUTINAS
            	
            	String idd = request.getParameter("idPrueba");
                if(idd == null || idd.equals(""))
                	idd = (String)request.getAttribute("idPrueba");
                tituloReporte += (idd != null?idd:"");
            }else if(pantalla.equals("efectividadPrueba")){
            	
            	parameters.put("A", (String)request.getAttribute("A"));
            	parameters.put("E", (String)request.getAttribute("E"));
            	parameters.put("F", (String)request.getAttribute("F"));
            	parameters.put("N", (String)request.getAttribute("N"));
            	parameters.put("tiempoPromedio", (String)request.getAttribute("tiempoPromedio"));
            	parameters.put("cantidadTotalRegistros", (String)request.getAttribute("cantidadTotalRegistros"));
            	
            }
            
            String tituloReporte2 = tituloReporte;
                        
            String classpath = (String)request.getSession().getServletContext().getAttribute("org.apache.catalina.jsp_classpath");

            System.setProperty("jasper.reports.compile.class.path", classpath);
            
            FileInputStream fis = new FileInputStream(f);
            
            jr = JasperCompileManager.compileReport(fis);
            
            if(formato != null && (formato.equals("xls")||formato.equals("csv"))){
            	tituloReporte = "";
            }
        	
            logs.debug("salios de todos los if!!!!");
            
            JRFileVirtualizer vv = new JRFileVirtualizer(2,"tmp");
            
            parameters.put("tituloReporte", tituloReporte);
            parameters.put("pathSape",path);
        	parameters.put(JRParameter.IS_IGNORE_PAGINATION,Boolean.TRUE);
        	parameters.put(JRParameter.REPORT_VIRTUALIZER, vv);
        	        	
        	logs.debug("Comienza el llenado del reporte");
        	long start = System.currentTimeMillis();
        	
        	JasperPrint jp=JasperFillManager.fillReport(jr,parameters, cd);
        	logs.debug("Tiempo de llenado : " + (System.currentTimeMillis() - start));
        	vv.setReadOnly(true);
        	
        	String path_archivo=path+"/reportes/tmp/";
        	
        	Calendar cal = Calendar.getInstance(); 
        	
        	cal.setTime(new Date());
        	
        	nombre_archivo=tituloReporte2;
        	
        	System.out.println("Va a exportar por tipo de archivo...");
        	
        	nombre_archivo = nombre_archivo.replace(' ','_');
        	
        	if(debug) logs.debug("Nombre del Archivo: ["+nombre_archivo+"]");
        	
        	if(formato.equals("pdf")){
        	    nombre_archivo +=".pdf";
        	    JasperExportManager.exportReportToPdfFile(jp,path_archivo+nombre_archivo);
        	    if (debug) logs.debug("Archivo PDF Generado.");
        	}else if(formato.equals("xls")){

        		nombre_archivo += ".xls";
        	    JRXlsExporter exporter = new JRXlsExporter();
        	    
        	    logs.debug("PARAMETROS JASPER: "+exporter.getParameters());
        	    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path_archivo+nombre_archivo);
        		//exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        		String sheet_names[]={"hoja","hoja1"};
        		exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheet_names);
        		exporter.exportReport();
        		//JasperExportManager.exportReportToPdfFile(jp,"PARECE_EL_TITULO_DEL_ARCHIVO");
        	    if (debug) logs.debug("Archivo XLS Generado.");
        	}else if(formato.equals("csv")){
        		nombre_archivo +=".csv";
        	    JRCsvExporter exporter = new JRCsvExporter();
        		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path_archivo+nombre_archivo);
        		exporter.exportReport();
        	    
        	    //JasperExportManager.exportReportToPdfFile(jp,"PARECE_EL_TITULO_DEL_ARCHIVO");
        	    if (debug) logs.debug("Archivo CVS Generado.");
        	}
        	
//        	System.out.println("Termino el proceso de exportacion de archivos.");
        } catch (JRException e1) {
//            e1.printStackTrace();
//            System.out.println("ERROR");
        	logs.error(e1);
        } 
        
        logs.debug("TERMINO DE GENERAR EL REPORTE!!!!!1");
        
        /*temporalReportes es la carpeta temporal donde se guardan los reportes
         * que se generan ya sea en pdf, xls o csv.
         */
        System.out.println("la ruta del archivo final: "+acciones.getTemplate("temporalReportes")+nombre_archivo);
        //request.getRequestDispatcher(acciones.getTemplate("temporalReportes")+nombre_archivo).forward(request,response);
        response.sendRedirect("/sape"+acciones.getTemplate("temporalReportes")+nombre_archivo);
        
        
    }
        
    
    /////////////////***************PROCEDIMIENTOS DE OPERACION****************///////////
    
    
    private void operacionMostrarGrafica(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String tick = request.getParameter("ticket");
        if(tick == null){
            error("Ticket no valido: ticket = NULL \n intente con una cifra valida!",request,response);
            return;
        }
        long ticket=-1;
        try{
            
        ticket = Long.parseLong(tick);
        }catch (NumberFormatException e) {
            error("Ticket no valido: ticket = "+tick+" \n intente con una cifra valida!",request,response);
            return;
        }
        
        
        if (debug) logs.debug("VARIABLE TICKET = "+tick);
        
        String grafica=null;
        
        try {
            grafica=pruebaBasicaDAO.getGrafica(ticket);
        } catch (SapeDataException e1) {
            error("No se encontro grafica para el  ticket = "+tick+" .",request,response);
            return;
        }
        if(grafica == null){
            error("No se encontro grafica para el  ticket = "+tick+" .",request,response);
            return;
        }
        
        request.setAttribute("datos",grafica);
        
        request.getRequestDispatcher(acciones.getTemplate("grafica/mostrarGrafica")).forward(request,response);

    }
    
    private List getListaVentanaDetallesEfectividad(HttpServletRequest request, HttpServletResponse response) {
        String opcion = request.getParameter("opcion");     
        String fIni=	request.getParameter("fechaIni");
        String fFin=	request.getParameter("fechaFin");
        
        //XXX SE DEBE CUIDAR LAS CONSULTAS POR FECHA DE EL JSP DE EFECTIVIDAD!!!!! 
        String pagActual = request.getParameter("pagActual");
        String regPorPagina =request.getParameter("regPorPagina"); 
        String orderBy =request.getParameter("orderBy");
        
        System.out.println("registros por pagina: "+regPorPagina);
        
        
        System.out.println("la opcion: "+opcion);
        
        String estado = request.getParameter("estado");
        
        
        if(fIni == null || fFin ==null){
        	fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        if(fIni.equals("") || fFin.equals("")){
            fIni = getFechaHoy();
        	fFin = getFechaHoy();            	
        }
        
        try{
        	int pagina = Integer.parseInt(pagActual);
        	if(pagina <=0)
        		pagActual = "1";
        }catch(NumberFormatException e){
        	pagActual = "1";
        }
        
        if(estado == null || estado.equals(""))
        	estado="todos";
        
        String offset ="";
        
        if(regPorPagina==null)
        	regPorPagina = "100";
        if(regPorPagina.equals(""))
        	regPorPagina = "100";
        
        if(pagActual==null)
        	pagActual = "1";
        if(pagActual.equals(""))
        	pagActual = "1";
                
        if(pagActual.equals("1"))
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regPorPagina));
        	
        if(orderBy==null || orderBy.equals(""))
        	orderBy = " site ASC";
        
        String usuario = request.getParameter("usuario");
        
        if(usuario != null && usuario.equals("todos"))
        	usuario = null;
        

        /**
         * Este codigo es para que el reporte saque todos los registros!!!!
         */
        
        String totalRegistros = request.getParameter("totalRegistros");
        try{
        	Integer.parseInt(totalRegistros);
        	//si no provoco ninguna excepcion coloco el offset en cero y los
        	//registros por pagina = totalRegistros!!!!
        	offset = "0";
        	regPorPagina = totalRegistros;
        }catch(NumberFormatException e){
        	// se deja el offset y la cantidad de registros igual!!!!
        }
        
        List l = null;
        try {
        	//cantidad total de registros que devolvio la consulta.
//            String cantReg = "0";
            //List l= eventoSapeDAO.getRegistrosLink(opcion,fIni,fFin,regPorPagina,offset,orderby2);
            String filtro = (opcion.equals("todos"))?"":"estado";
            
            if(opcion.equals("reportePorSite")){
            	filtro = opcion;
            	opcion = request.getParameter("site");
            }
        	
            List total= reportesDAO.getRegistros(filtro,opcion,usuario,fIni,fFin,regPorPagina,offset,orderBy);
            l = (List) total.get(1);
        }catch(SapeDataException e){
        	error(e,request,response);
        	return null;
        }
        logs.debug("TAMANO DE LISTA: "+l.size());
        return l;	
    }
       
    private String convertirFecha(Date d){
    
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        
        DecimalFormat df2 = new DecimalFormat("00");
        
        String day = df2.format(cal.get(Calendar.DAY_OF_MONTH));
        String month = df2.format(cal.get(Calendar.MONTH)+1);
        String year = cal.get(Calendar.YEAR)+"";
        
        String hour = df2.format(cal.get(Calendar.HOUR));
        String minutes = df2.format(cal.get(Calendar.MINUTE));
        String seconds = df2.format(cal.get(Calendar.SECOND));
        
        return  year+"-"+month+"-"+day+" "+hour+":"+minutes+":"+seconds;
    }
    
    private List getListaConsultaPruebas(HttpServletRequest request, HttpServletResponse response) {
    	
        String valor=request.getParameter("valor");
        String opcion = request.getParameter("opcion");     
        String fIni=request.getParameter("fechaIni");
        String fFin=request.getParameter("fechaFin");
        String orderBy =request.getParameter("orderBy");
        

        if(valor == null)
        	valor = "";


        if(opcion == null || opcion.equals(""))
        	opcion="ninguno";

        if(fIni == null || fIni.equals("") || fFin == null || fFin.equals("")) {
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }      
        request.setAttribute("fIni",fIni);
        request.setAttribute("fFin",fFin);
        
        if(orderBy==null || orderBy.equals(""))
        	orderBy = "ticket DESC";
        List l = null,total=null;
        try {
            //total= reportesDAO.getRegistros(opcion, valor,null, fIni, fFin, regPorPagina, offset, orderBy);
        	// se envia null en los parametros regPorPagina y offset para ke traiga todos los registros
        	total= reportesDAO.getRegistros(opcion, valor,null, fIni, fFin, null, null, orderBy);
            l = (List) total.get(1);
        }catch(SapeDataException e){
        	error(e,request,response);
        	return null;
        }
        logs.debug("TAMANO DE LISTA: "+l.size());
        return l;  	
    }
    
    
    private void operacionConsultaPruebas(HttpServletRequest request, HttpServletResponse response) {
        
        String valor=request.getParameter("valor");
        String opcion = request.getParameter("opcion");     
        String fIni=request.getParameter("fechaIni");
        String fFin=request.getParameter("fechaFin");
        
        String pagActual = request.getParameter("pagActual");
        //String offset =request.getParameter("offset");
        String regPorPagina =request.getParameter("regPorPagina"); 
        String orderBy =request.getParameter("orderBy");
        
        
        if(valor == null)
        	valor = "";
        if(pagActual==null || pagActual.equals("")) pagActual = "1";
        try{
        	int pagina = Integer.parseInt(pagActual);
        	if(pagina <=0) pagActual = "1";
        }catch(NumberFormatException e){
        	pagActual = "1";
        }

        if(opcion == null || opcion.equals(""))
        	opcion="ninguno";
        
        if(fIni == null || fFin == null || fIni.equals("") || fFin.equals("")){
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
        request.setAttribute("fIni",fIni);
        request.setAttribute("fFin",fFin); 
        
        String offset ="";
        
        if(regPorPagina==null || regPorPagina.equals(""))
        	regPorPagina = "100";
        
        if(pagActual.equals("1"))
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regPorPagina));
        
        if(orderBy==null || orderBy.equals(""))
        	orderBy = "ticket DESC";

        try {
        	//cantidad total de registros que devolvio la consulta.
//            String cantReg = "0";
            List l= reportesDAO.getRegistros(opcion, valor,null, fIni, fFin, regPorPagina, offset, orderBy);
            

            request.setAttribute("listaEventos",l.get(1));  
            

            double division = Double.parseDouble((String)l.get(0))/Double.parseDouble(regPorPagina);
//            System.out.println(" la division da: " + division + ". Ceil: " + Math.ceil(division) + ". toInt " + (int) Math.ceil(division));
            int totalPaginas = (int)Math.ceil(division);
            if(totalPaginas <=1) totalPaginas = 0;
            //TODO arreglar para que no se quede en la ultima pagina en casos cuando cambian las condiciones.
//            if (Integer.parseInt(offset) > (totalPaginas - 1)) offset = String.valueOf(totalPaginas -1);
            
            request.setAttribute("valorOpcion",valor);
            request.setAttribute("opcion",opcion);
            request.setAttribute("offset",offset);
            request.setAttribute("regPorPagina",regPorPagina);
            request.setAttribute("pagActual",pagActual);
            request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
            
            //para ke se vea o no el encabezado!!!!!
            String modoReporte = request.getParameter("modoReporte");
            
            String query = "&valor="+(valor.endsWith("%") ? valor + "25" : valor)+
            "&opcion="+opcion+"&fechaIni="+fIni+"&fechaFin="+fFin+
            (modoReporte == null || modoReporte.equals("")?"":"&modoReporte=popup");
            
            request.setAttribute("query",query);
            request.setAttribute("orderBy",orderBy);
            request.setAttribute("cantidadTotalRegistros",l.get(0));
            request.setAttribute("msgCentral","Numero de registros "+l.get(0)+", Rango de fechas: "+fIni+" a "+fFin+". Parametro de busqueda: "+opcion);
            
            if(modoReporte != null && !modoReporte.equals(""))
            	request.setAttribute("modoReporte",modoReporte);
            
            	redireccionarConPlantilla("consultaPruebas",request,response); 
            
        } catch (SapeDataException e) {
        	e.printStackTrace();
            error(e, request, response);
        }
        
    }
    
//    private void operacionInicioConsultaPruebas(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
//        
//        List l=null;
//        try {
//            l=eventoSapeDAO.getListadoInicial();
//            // TODO hay ke paginar los reportes aun no se esta haciendo!!!
//            for (int i=0;i<l.size();i++) {
//                EventoSape element = (EventoSape) l.get(i);
//                long tiket = element.getId();
//                PruebaBasica pb=null;
//                try{
//                    pb=pruebaBasicaDAO.getPruebaBasica(tiket);
//                }catch(Exception err){
//
//                	logs.error(err);
//                    element.setCodigoVer("SIN CODV");
//                    l.set(i,element);
//                    continue;
//                }
//                Date fIni = element.getFechaInicial();
//                Date fFin = element.getFechaFinal();
//                
//                if(fIni != null && fFin != null)
//                    element.setDuracion((fFin.getTime() - fIni.getTime())/1000.0);
//                else
//                    element.setDuracion(-1.0);
//                //XXX SE COLOCA EL VALOR DE -1 PARA DIFERENCIAR DE CUANDO ESTAS FECHAS
//                //SON NULL
//                
//                element.setCodigoVer(pb.getCodigoVer());
//                l.set(i,element);
//            }
//            
//        } catch (SapeDataException e) {
//            e.printStackTrace();
//            error(e,request,response);
//            return;
//        }
//        request.setAttribute("listaEventos",l);
//        request.setAttribute("msgCentral","100 Ultimos Registros");
//        Calendar cal = Calendar.getInstance();
//        int ano = cal.get(Calendar.YEAR);
//        int mes=cal.get(Calendar.MONTH)+1;
//        int day=cal.get(Calendar.DAY_OF_MONTH);
//        request.setAttribute("fIni",""+ano+"-"+mes+"-"+day);
//        request.setAttribute("fFin",""+ano+"-"+mes+"-"+day);
//        request.getRequestDispatcher(acciones.getTemplate("consultaPruebas")).forward(request,response);
//    }
    
      
    /////////////////////********OPERACIONES VARIAS*************/////////////////////
    
    
    private CustomDataSource getDataSourceObject(String pantalla, HttpServletRequest request, HttpServletResponse response) {
        List lista=null;
        
        Object data[][]= null;
        CustomDataSource cds = null;
        
        if ( pantalla.equals("usuariosSape") ) {
        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla USUARIOSAPE!!!!!");
	        try{
	            lista = usuarioDAO.getAllUsuarios();
	        }catch (SapeDataException e){
//	            e.printStackTrace();
	            logs.error(e.toString());
	            return null;
	        }
	        int size = lista.size();
	        
	        data= new Object[size][7];
	        
	        for(int i=0;i<size;i++){
	            UserSipe s = (UserSipe) lista.get(i);
	            data[i][0]=  new Integer(s.getId());
	            data[i][1]=  s.getNick();
	            data[i][2]=  s.getPasswd();
	            data[i][3]=  s.getActivo();
	            data[i][4]=  s.getNivel();
	            Date dat = s.getFechaAlta();
	            	            
	            data[i][5]=  convertirFecha(dat);
	            data[i][6]=  s.getNombre();
	            
	        }
	        
	        String names[]={"id","nick","password","activo","nivel","fechaAlta","nombre"};
	        cds = new CustomDataSource(data,names);
	        return cds;
        } else if ( pantalla.equals("codigosVer") ) {
            
        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla CODIGOSVER!!!!!");
            
	        try{
	            lista = codigosVerDAO.getAllCodigosVer();
	        }catch (SapeDataException e){
//	            e.printStackTrace();
	            logs.error(e.toString());
	            return null;
	        }
	        
	        int size = lista.size();
	        
	        data= new Object[size][3];
	        
	        for(int i=0;i<size;i++){
	            CodigoVer s = (CodigoVer) lista.get(i);
	            data[i][0]=  s.getCodigoVer();
	            data[i][1]=  s.getClasificacion();
	            data[i][2]=  s.getComentarios();   
	        }
	        
	        String names[]={"codigoVer","clasificacion","comentarios"};
	        cds = new CustomDataSource(data,names);
	        return cds;
        } else if ( pantalla.equals("consultaPruebas") ) {
        	
            if(debug)logs.debug("EXPORTAR INFORME de la pantalla consultaPruebas");
        	lista = getListaConsultaPruebas(request,response);
        	        	
        	if(lista == null)
        		return null;
        	
	        data= new Object[lista.size()][8];
	        int siz = lista.size();
	        for(int i=0;i<siz;i++){
	        	
	        	ViewReportePruebas s   = (ViewReportePruebas) lista.get(i);
	            data[i][0]=  String.valueOf(s.getTicket());
	            data[i][1]=  s.getTelefono();
	            data[i][2]=  s.getCodigoVer();   
	            data[i][3]=  s.getTipoPrueba();
	            data[i][4]=  s.getCentral();
	            data[i][5]=  s.getUsuario();
	            Date dat = s.getFecha();
	            
	            data[i][6]=  convertirFecha(dat);  
	            data[i][7]=  s.getDuracion();
	            
	        }
	        
	        String names[]={ "ticket", "telefono", "codv", "tipoprueba", "central", "usuario", "fecha", "duracion"};
	        cds = new CustomDataSource(data,names);
	        return cds;
        	
        	
        } else if ( pantalla.equals("ventanaDetallesEfectividad") ) {
        	
            if(debug)logs.debug("EXPORTAR INFORME de la pantalla ventanaDetallesEfectividadPrueba");
        	
            lista = getListaVentanaDetallesEfectividad(request,response);
        	        	
        	if(lista == null)
        		return null;
        	
	        data= new Object[lista.size()][8];
	        int siz = lista.size();
	        for(int i=0;i<siz;i++){
	        	
	        	ViewReportePruebas s   = (ViewReportePruebas) lista.get(i);
	            data[i][0]=  String.valueOf(s.getTicket());
	            data[i][1]=  s.getTelefono();
	            data[i][2]=  s.getCodigoVer();   
	            data[i][3]=  s.getTipoPrueba();
	            data[i][4]=  s.getCentral();
	            data[i][5]=  s.getUsuario();
	            Date dat = s.getFecha();
	            
	            data[i][6]=  convertirFecha(dat);  
	            data[i][7]=  s.getDuracion();
	            
	        }
	        
	        String names[]={ "ticket", "telefono", "codv", "tipoprueba", "central", "usuario", "fecha", "duracion"};
	        cds = new CustomDataSource(data,names);
	        return cds;
        	
        } else if ( pantalla.equals("consultaTelefonosTSTLI") ) {
        	//DESDE AHORA, EN EL REQUEST SE MANDA LA LISTA DE OBJETOS. 
        	
            if(debug)logs.debug("EXPORTAR INFORME de la pantalla consultaTelefonosTSTLI.jsp");
        	
            String names[]={ "telefono","tipoNodo","areaTrabajo","cable","strip","armario",
            		"caja", "sec", "subzona","producto","tipoCliente","est", "fecha", "enruta"};
            lista = (List)request.getAttribute("listaRequerimientos");
        	        	
        	if(lista == null)
        		return null;
        	
	        data= new Object[lista.size()][14];
	        int siz = lista.size();
	        for(int i=0;i<siz;i++){
	        	
	        	RequerimientosCola s  = (RequerimientosCola) lista.get(i);
	            data[i][0]=  s.getIdentificador();
	            data[i][1]=  s.getTipo_nodo();
	            data[i][2]=  s.getArea_trabajo_id();   
	            data[i][3]=  s.getCable();
	            data[i][4]=  s.getStrip_id()+"-"+s.getPar_primario_id();
	            data[i][5]=  s.getArmario_id();
	            data[i][6]=  s.getCaja_id()+"-"+s.getPar_secundario_id();
	            data[i][7]=  s.getSecuencia();
	            data[i][8]=  s.getSubzona_id();
	            data[i][9]=  s.getProducto();
	            data[i][10]=  s.getTipoCliente();
	            data[i][11]=  s.getEstado();

	            Date dat = s.getFecha();
	            
	            data[i][12]=  convertirFecha(dat);
	            
	            if(s.getColaEnruta() != null && s.getCodObservacion() != null)
	            	data[i][13]=  s.getColaEnruta().trim()+ " - "+ s.getCodObservacion();
	            else
	            	data[i][13] = "-";
	            
	            logs.debug("TELEFONO TSTLI: "+s.getIdentificador());
	            
	            //System.out.println("TAMANO DE LA COLA: '"+s.getCola()+"' = "+s.getCola().length());
	        }
	        
	        cds = new CustomDataSource(data,names);
	        return cds;
        	
        } else if ( pantalla.equals("umbrales") ) {
        	
        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla Umbrales.jsp");
        	
            String names[]={ "inicial", "final","central", "tipo", "site", "cabeza" };

            lista = (List)request.getAttribute("reportes");//lista de beans TipoNodo
        	        	
        	if(lista == null)
        		return null;
        	
        	int size = lista.size();
	        
        	data= new Object[size][6];
	        
	        for(int i=0;i<size;i++){
	        	
	        	SerieReportes s   = (SerieReportes) lista.get(i);
	            data[i][0]=  s.getInicial();
	            data[i][1]=  s.getFINAL();
	            data[i][2]=  s.getCentral();   
	            data[i][3]=  s.getTipoCentral();
	            data[i][4]=  s.getSite();
	            data[i][5]=  s.getNombre()+"-"+s.getProveedor();
	            s = null;
	        }
	        
	        cds = new CustomDataSource(data,names);
	        return cds;

        }else if(pantalla.equals("tipoNodo")){

        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla MantenimientoTipoNodo.jsp");
        	
            String names[]={ "id", "site", "cabezaPrueba", "ipServidor", "puertoServidor", "ipCabeza", "puertoCabeza", "ipEsclavo", "puertoEsclavo", "estado" };
            
            lista = (List)request.getAttribute("listaTipoNodo");//lista de beans TipoNodo
        	        	
        	if(lista == null)
        		return null;
        	
        	int size = lista.size();
	        
        	data= new Object[size][10];
	        
	        for(int i=0;i<size;i++){
	        	
	        	TipoNodo s   = (TipoNodo) lista.get(i);
	            data[i][0]=  String.valueOf(s.getId());
	            data[i][1]=  s.getSite();
	            data[i][2]=  s.getTipoCabeza().getNombre();   
	            data[i][3]=  s.getIpServidor();
	            data[i][4]=  s.getPuertoServidor();
	            data[i][5]=  s.getIpCabeza();
	            data[i][6]=  s.getPuertoCabeza();
	            data[i][7]=  s.getIpEsclavo();
	            data[i][8]=  s.getPuertoEsclavo();
	            data[i][9]=  (s.getEstado().equals("F"))?"Fuera de \n Servicio":"Operando";

	            s = null;
	        }
	        
	        cds = new CustomDataSource(data,names);
	        return cds;
        	
        }else if(pantalla.equals("series")){

        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla MantenimientoSeries.jsp");
        	
            String names[]={ "id","inicial","final","central","tipo","idCabeza" };
            
            lista = (List)request.getAttribute("listaSerie");//lista de beans Serie
        	        	
        	if(lista == null)
        		return null;
        	
        	int size = lista.size();
	        
        	data= new Object[size][6];
	        
	        for(int i=0;i<size;i++){
	        	
	        	Serie s   = (Serie) lista.get(i);
	        	
	            data[i][0]=  String.valueOf(s.getId());
	            data[i][1]=  String.valueOf(s.getSerieInicial());
	            data[i][2]=  String.valueOf(s.getSerieFinal());
	            data[i][3]=  s.getCentral();
	            data[i][4]=  s.getTipocentral();
	            data[i][5]=  String.valueOf(s.getCabezaId());
	            
	            s = null;
	        }
	        
	        cds = new CustomDataSource(data,names);
	        return cds;
        	
        } else if(pantalla.equals("cabezaPrueba")){

        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla MantenimientoCabezaPrueba.jsp");
        	
            String names[]={ "id","nombre","vendor"};
            
            lista = (List)request.getAttribute("listaCabezaPrueba");//lista de beans CabezaPrueba
        	        	
        	if(lista == null)
        		return null;
        	
        	int size = lista.size();	        
        	data= new Object[size][6];
	        
	        for(int i=0;i<size;i++){
	        	
	        	CabezaPrueba s   = (CabezaPrueba) lista.get(i);
	            data[i][0]=  String.valueOf(s.getId());
	            data[i][1]=  s.getNombre();
	            data[i][2]=  s.getProveedor();
	            s = null;
	        }
	        
	        cds = new CustomDataSource(data,names);
	        return cds;
        	
        } else if(pantalla.equals("ventanaDetalles")){
        	
        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla ventanaDetallesRutinaPruebas.jsp");
        	
            String names[]={ "id", "telefono","capacitancias","resistencias", "codigover", "clasificacion", "VelUp","VelDown","BPD"};
            
            lista = (List)request.getAttribute("listaDetalles");//lista de beans CabezaPrueba

        	if(lista == null)
        		return null;
        	
        	int size = lista.size();
	        
        	
        	data= new Object[size][9];
	        
	        for(int i=0;i<size;i++){
	        	

	        	PruebaSPP s  = (PruebaSPP) lista.get(i);
	        	String tik = s.getTransaccion_spp().toString();
	        	data[i][0]=  (tik == null || tik.equals("0") || tik.equals("")?"-":tik);
	            
	        	data[i][1]=  String.valueOf(s.getTelefono());
	            data[i][2]=  s.getCapacitancias();
	            data[i][3]=  s.getResistencias();
	            data[i][4]=  s.getCodigover();
	            data[i][5]=  s.getCalificacion();
	            data[i][6]=	 s.getVelocidadUP();
	            data[i][7]=	 s.getVelocidadDown();
	            data[i][8]=  (s.getCalificacionDatos()==null?"":s.getCalificacionDatos());
	            //System.out.println("datos: "+s.getIdpruebaprogramada()+"-"+
	            //String.valueOf(s.getTelefono())+"-"+s.getCodigover());
	            s = null;
	        }
	        
	        cds = new CustomDataSource(data,names);
	        return cds;
        	
        } else if(pantalla.equals("efectividadPrueba")){
        	
        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla consultaEfectividadPrueba.jsp");
        	
        	String names[]={ "site", "total","exitosas","noExitosas", "advertencia", "inesperados","tPromedio"};
        	List l = (List)request.getAttribute("listaCentrales");
        	int size = l.size();
        	data= new Object[size][7];
	        
	        for(int i=0;i<size;i++){
	        	
	        	String datos[] = (String[])l.get(i);
	            data[i][0]=  datos[5];
	            data[i][1]=  datos[0];
	            data[i][2]=  datos[1];   
	            data[i][3]=  datos[2];
	            data[i][4]=  datos[3];
	            data[i][5]=  datos[4];
	            data[i][6]=  datos[6];
	        	
	        }
	        cds = new CustomDataSource(data,names);
        } else if(pantalla.equals("estadisticoPorCentral")){

        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla estadisticoPorCentral.jsp");
        	
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
        		return null;
        	}
        	String names[]={ "central", "exito", "advertencia","fallo", "inesperado","efectividad","total","efectividadPERC"};
        	int size = listaEstadosCentral.size();
        	data= new Object[size][names.length];
        	
	        for(int i=0;i<size;i++){
	        	        	
	        	ViewEstadosCentral v = (ViewEstadosCentral)listaEstadosCentral.get(i);
	        	
	        	if(v.getTotal() <= 0)
	        		continue;
	        	
	        	data[i][0] = v.getCentral();
	        	data[i][1] = String.valueOf(v.getExito());
	        	data[i][2] = String.valueOf(v.getAdvertencia());
	        	data[i][3] = String.valueOf(v.getFallo());
	        	data[i][4] = String.valueOf(v.getInesperado());
	        	data[i][5] = String.valueOf(v.getExito()+v.getAdvertencia());
	        	data[i][6] = String.valueOf(v.getTotal());
	        	double exitt = v.getExito();
	        	double adver = v.getAdvertencia();
	        	
	        	DecimalFormat dc = new DecimalFormat("00.00");
	        	
	        	
	        	data[i][7] = dc.format(100*(exitt+adver)/v.getTotal());
	        	
	        	if(data[i][7].equals("100.00"))
	        		data[i][7] = "100";
	        	
	        }
	        cds = new CustomDataSource(data,names);
        }else if(pantalla.equals("detallesCentral")){
        	
        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla detallesCentral.jsp");
        	
           	String fechaIni = request.getParameter("fechaIni");
        	String fechaFin = request.getParameter("fechaFin");
        	String central = request.getParameter("central");
        	if (central == null || central.equals("")) {
        		request.setAttribute("tipo","popup");
        		error("Falta el parametro Central", request, response);
        		return null;
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
        		request.setAttribute("tipo","popup");
        		error(e, request, response);
        		return null;
        	}
        	
        	String names[]={"fecha", "central", "tiponodo", "codv","cantidad", "porcentaje"};
        	int size = listaDetalles.size();
        	data= new Object[size][names.length];
            	
	        for(int i=0;i<size;i++){
	        	
	        	ViewCodvCentral v = (ViewCodvCentral)listaDetalles.get(i);
	        	
	        	data[i][0] = v.getFecha().toString();
	        	data[i][1] = v.getCentral();
	        	data[i][2] = v.getSite();
	        	data[i][3] = v.getCodigoVer();
	        	
	        	data[i][4] = String.valueOf(v.getCantidad());
	        	double cant = v.getCantidad();
	        	double totalEv = totalEventos.intValue();
	        	
	        	DecimalFormat dc = new DecimalFormat("00.00");
	        	
	        	
	        	data[i][5] = dc.format(100*(cant/totalEv));
	        	
	        	if(data[i][5].equals("100.00"))
	        		data[i][5] = "100";
	        	
	        }
	        cds = new CustomDataSource(data,names);
        } else if(pantalla.equals("primeraPruebaTelefono")){
        	// por request viene ya la lista con la informacion.
        	if(debug)logs.debug("EXPORTAR INFORME de la pantalla primeraPruebaTelefono.jsp");
        	
        	List listaDatos = (List)request.getSession().getAttribute("listaDatos");
        	request.getSession().removeAttribute("listaDatos");
        	if(listaDatos == null)
        		return null;
        	String names[]={"prueba", "telefono", "cantidad", "tipoPrueba","codigoVer", "estado","site","usuario","fecha","duracion"};
        	int size = listaDatos.size();
        	data= new Object[size][names.length];
        	
        	for(int i=0;i<size;i++){
        		ViewPrimeraPruebaTelefonos v = (ViewPrimeraPruebaTelefonos)listaDatos.get(i);
        		
	            data[i][0]=  String.valueOf(v.getIdPrueba());
	            data[i][1]=  v.getTelefono();
	            data[i][2]=  String.valueOf(v.getCantidad());   
	            data[i][3]=  v.getTipoPrueba();
	            data[i][4]=  v.getCodigoVer();
	            data[i][5]=  v.getEstado();
	            data[i][6]=  v.getTipoNodo();
	            data[i][7]=  v.getCliente();
	            data[i][8]=  convertirFecha(v.getFechaInicial());
	            data[i][9]=  v.getDuracion();
        	}
        	cds = new CustomDataSource(data,names);
        }// else if(pantalla.equals("")){}// else if(pantalla.equals("")){}// else if(pantalla.equals("")){}// else if(pantalla.equals("")){}
        return cds;
    }
    
    private void cprsTipoNodo(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("cprsTipoNodo");
    	String tipoReporte = request.getParameter("tipoReporte");
    	if (tipoReporte == null || tipoReporte.equals("")) tipoReporte = "resumido";
    	String tipoN = request.getParameter("tipoNodo");
    	int idTipoNodo = 0;
    	try {
    		idTipoNodo = Integer.parseInt(tipoN);
    	} catch (NumberFormatException e) {
    		logs.error(e);
    		request.setAttribute("tipo","cerrar");
    		error("El tipo de nodo debe ser num&eacute;rico.", request, response);
    		return;
    	}
    	TipoNodo tipoNodo = null;
    	List listaCprs = null;
    	try {
			tipoNodo = tipoNodoDAO.getTipoNodo(idTipoNodo);
			if (tipoReporte.equals("resumido")) {
				listaCprs = tipoNodoDAO.getCprTipoNodoResumido(tipoNodo);
			} else {
				listaCprs = tipoNodoDAO.getCprTipoNodo(tipoNodo);
			}
		} catch (SapeDataException e) {
			request.setAttribute("tipo","cerrar");
			error(e, request, response);
			return;
		}
			//organizo el tipo de reporte para el JSP
		if (tipoReporte.equals("resumido")) tipoReporte = "detallado";
		else tipoReporte = "resumido";
		request.setAttribute("tipoNodo", tipoNodo);
		request.setAttribute("listaCprs", listaCprs);
		request.setAttribute("tipoReporte", tipoReporte);
		redireccionarConPlantilla("cprsTipoNodo", request, response);			
    }
    
    
    private void listaCabezas(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("listaCabezasCentral");
    	//CPRSiplexPro cpr = null;
    	List listaCabezas = null;
    	
    	String telefono = request.getParameter("telefono");
    	if (telefono == null) {
    		error("Debe definir el paramtero telefono", request, response);
    		return;
    	}
    	Serie serie = null;
    	try {
			
    		serie = serieDAO.buscarSerie(telefono);
    		
    		//cpr = tipoNodoDAO.getCPR(Long.parseLong(telefono));
			listaCabezas = tipoNodoDAO.getTipoNodosCentral(serie.getCentral());
		} catch (NumberFormatException e) {
			logs.error(e);
			error(e, request, response);
			return;
    	} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
    	request.setAttribute("central", serie.getCentral());
    	request.setAttribute("listaCabezas", listaCabezas);
		redireccionarConPlantilla("listaCabezasCentral", request, response);
    	
    }
    
    
    private void lisTipoNodo(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("lisTipoNodo");
    	int idTipoNodo = 0;
    	try {
    		idTipoNodo = Integer.parseInt(request.getParameter("tipoNodo"));
    	} catch (NumberFormatException e) {
    		logs.error(e);
    		request.setAttribute("tipo","cerrar");
    		error("Tipo de Nodo Inv&aacute;lido.", request, response);
    		return;
    	}
    	TipoNodo tipoNodo = null;
    	List listaLis = null;
    	try {
			tipoNodo = tipoNodoDAO.getTipoNodo(idTipoNodo);
			listaLis = tipoNodoDAO.getLIsTipoNodo(tipoNodo);
		} catch (SapeDataException e) {
			request.setAttribute("tipo","cerrar");
			error(e, request, response);
			return;
		}
			//organizo el tipo de reporte para el JSP
		request.setAttribute("tipoNodo", tipoNodo);
		request.setAttribute("listaLis", listaLis);
		redireccionarConPlantilla("lisTipoNodo", request, response);			
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doGet(request,response);
    }
    
   
}