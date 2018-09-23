/*
 * Created on Apr 18, 2005
 */
package com.osp.sape.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.HibernateException;

import com.gc.acceso.GestorServlet;
import com.osp.sape.SapeConfiguration;
import com.osp.sape.Exceptions.SapeAppException;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.EventoSapeDAO;
import com.osp.sape.data.PruebaBasicaDAO;
import com.osp.sape.data.ReportesDAO;
import com.osp.sape.data.RequerimientosColaDAO;
import com.osp.sape.data.SerieDAO;
import com.osp.sape.data.ServicioIndicadoresOSSDAO;
import com.osp.sape.data.SiplexPROAutotestDAO;
import com.osp.sape.maestros.ServicioIndicadoresOSS;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.servicios.Alarma;
import com.osp.sape.servicios.Cola;
import com.osp.sape.servicios.ConfiguracionServicios;
import com.osp.sape.servicios.MedioBeeper;
import com.osp.sape.servicios.MedioCorreo;
import com.osp.sape.servicios.MedioInformar;
import com.osp.sape.servicios.MedioSmsOLA;
import com.osp.sape.utils.ServicioGUDE;

/**
 * 
 * @author Andres Aristizabal
 */
public class IndicadoresServlet extends GestorServlet {

    private PruebaBasicaDAO pruebaBasicaDAO;    
    private ServicioIndicadoresOSSDAO servicioIndicadoresOSSDAO;
    private ConfiguracionServicios configuracionServicios;
    private RequerimientosColaDAO requerimientosColaDAO;
    private EventoSapeDAO eventoSapeDAO;
    private SiplexPROAutotestDAO siplexPROAutotest;
    private SerieDAO serieDAO;
    private ServicioGUDE servicioGUDE;
    private ReportesDAO reportesDAO;
    
    // esto es para agregarlo a las fechas y optimizar un poco la consulta
    private String horaIni = " 00:00:00", horaFin = " 23:59:59";
    
    
    // 2006-06-08: la vble dfFecha y el metodo getFechaHoy se pasaron para gestorServlet!!!!
    
    public void init() throws ServletException {
        
        super.init();
        DAOFactory factory = DAOFactoryImpl.getInstance();
        
        servicioIndicadoresOSSDAO=factory.getServicioIndicadoresOSSDAO();
        pruebaBasicaDAO= factory.getPruebaBasicaDAO();
        requerimientosColaDAO=factory.getRequerimientosColaDAO();
        eventoSapeDAO=factory.getEventoSapeDAO();
        siplexPROAutotest = factory.getSiplexPROAutotestDAO();
        serieDAO = factory.getSerieDAO();
        reportesDAO = DAOFactoryImpl.getInstance().getReportesDAO();
        try {
            configuracionServicios = new ConfiguracionServicios(new File(SapeConfiguration.getInstance().getRutaServicios()));
        } catch (SapeAppException e) {
            logs.error(e);
        }

    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String operacion = request.getParameter("operacion");
        
        if(operacion==null)
        	operacion="";
        
        UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
        
        if(usuario == null){
        	redireccionar(acciones.getLoginPage(),request,response);
        	return;
        }
        

        String level = usuario.getNivel();
        
        if(level.equals("1")||level.equals("2")) {
        	if(!operacion.equals("") && !operacion.equals("detallePruebas")){
        		redireccionar("/"+acciones.getRestrictedPage(),request,response);
        		return;
        	}
        }
        
        
        
        if(logs.isDebugEnabled())logs.debug("doGet: operacion=["+operacion+"]");

        if(operacion == null ||operacion.equals("") || operacion.equals("inicio")){
            redireccionarConPlantilla("inicioIndicadores", request, response);
            return;
        } else if(operacion.equals("indicadoresColas")) {
            
            operacionIndicadoresColas(request,response);
            return;
        } else if(operacion.equals("graficaTSTLI")){
            //se genera la grafica dependiendo del tipo(cable,area_trabajo, etc)           
            operacionGraficaTSTLI(request,response);
            return;
        } else if(operacion.equals("graficaIndicadoresColas")) {
//            System.out.println("ENTRO AL METODO DE GRAFICAS DE COLAS!!!!");
            operacionGraficaIndicadores(request, response);
            return;
        }else if(operacion.equals("mantenimiento")){
            operacionMantenimiento(request,response);
            return;
        }else if(operacion.equals("eliminarCola")){
            
            operacionEliminarCola(request,response);
            return;
        }else if(operacion.equals("eliminarAlarma")){
            
            operacionEliminarAlarma(request,response);
            return;            
        }else if(operacion.equals("agregarAlarma")){
            redireccionarConPlantilla("agregarAlarma",request,response);
            return;
        }
        else if(operacion.equals("ejecuteAgregarAlarma")){
            operacionEjecuteAgregarAlarma(request,response);
            return;
        }else if(operacion.equals("ejecuteModificarAlarma")){

            operacionEjecuteModificarAlarma(request,response);
            return;
        }else if(operacion.equals("agregarCola")){
            
            redireccionarConPlantilla("agregarCola", request, response);
            return;
        }else if(operacion.equals("ejecuteAgregarCola")){
            operacionEjecuteAgregarCola(request,response);
            return;
        
        }else if(operacion.equals("mostrarAlarma")){
            operacionMostrarAlarma(request,response);
            return;
        } else if (operacion.equals("estadisticaUsuarios")) {
        	operacionEstadisticaUsuarios(request,response);
        	return;
        }  else if ( operacion.equals("estadisticaTelefonos") ) {
        	operacionEstadisticaTelefonos(request,response);
        	return;
        } else if (operacion.equals("ventanaEstadisticaUsuario")) {
        	operacionVentanaEstadisticaUsuario(request,response);
        	return;
        } else if ( operacion.equals("estadisticaHoras") ) {
        	operacionEstadisticaHoras(request,response);
        	return;
        } else if (operacion.equals("indicadoresCabezales")) {
        	operacionIndicadoresCabezales(request,response);
        	return;
        } else if(operacion.equals("estadisticaCodigosVer")){
        	operacionEstadisticaCodigosVer(request,response);
        	return;
        } else if(operacion.equals("probarCabezal")){
        	operacionProbarCabezal(request,response);
        	return;
        } else if (operacion.equals("detallePruebas")) {
        	detallePruebas(request, response);
        	return;
        } else if(operacion.equals("indicadoresTecnologia")){
        	indicadoresTecnologia(request,response);
        	return;
        }
    }
    
    private void indicadoresTecnologia( HttpServletRequest request, HttpServletResponse response) {
    	
    	String fIni = request.getParameter("fIni");
        String fFin = request.getParameter("fFin");
        
        if(fIni == null || fFin==null || fIni.equals("")||fFin.equals(""))
        	fIni=fFin=getFechaHoy();
        
        List listaVistas=null;
        
        try {
			listaVistas=eventoSapeDAO.getEstadisticoTecnologia(fIni,fFin);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("listaVistas",listaVistas);
		request.setAttribute("fIni",fIni);
		request.setAttribute("fFin",fFin);
		redireccionarConPlantilla("estadisticoTecnologia",request,response);
    }
    
    
    private void operacionEstadisticaCodigosVer( HttpServletRequest request, HttpServletResponse response) {
    	
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionEstadisticaCodigosVer");
    	
    	String fIni = request.getParameter("fIni");
        String fFin = request.getParameter("fFin");
    	
        if(fIni == null || fIni.equals("") || fFin ==null || fFin.equals("")){
        	fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        String central = request.getParameter("central");
        String tech = request.getParameter("tech");
    	List l = null;
        
    	
    	if(tech!=null&&(tech.equals("FTX")||tech.indexOf("Indigo")!=-1))
    		tech = "INDIGO";
    	else if(tech != null && tech.indexOf("SiplexPRO")!= -1)
    		tech = "SIPLEXPRO";
    	
    	try {
			l=pruebaBasicaDAO.getEstadisticoPorCodv(fIni+horaIni,fFin+horaFin,central,tech);
		} catch (SapeDataException e) {
			if(log) logs.debug(e);
			error(e,request,response);
			return;
		}
        
		request.setAttribute("fIni",fIni);
		request.setAttribute("fFin",fFin);
		request.setAttribute("lista",l);
		request.setAttribute("central",central);	
		
    	redireccionarConPlantilla("estadisticoCodigosVer",request,response);
    }
    
    
    private void operacionEstadisticaHoras( HttpServletRequest request, HttpServletResponse response) {
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionEstadisticaHoras");
    	
    	
    	String fIni=	request.getParameter("fIni");
        String fFin=	request.getParameter("fFin");    	
    	
        if(fIni == null || fFin == null || fIni.equals("") || fFin.equals("")){
        	fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
           	
        List l = null;
        
        try {
			l = eventoSapeDAO.estadisticoPorHora(fIni+horaIni,fFin+horaFin);
		} catch (SapeDataException e) {
			if(log) logs.debug(e);
			error(e,request,response);
			return;
		}
		
		request.setAttribute("lista",l);
    	request.setAttribute("fIni",fIni);
		request.setAttribute("fFin",fFin);
		
		redireccionarConPlantilla("estadisticoHoras",request,response);
    }
    
    private void operacionProbarCabezal( HttpServletRequest request, HttpServletResponse response) throws IOException {
   	   	
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionProbarCabezal");
    	
    	String id = request.getParameter("id");
    	UserSipe us = (UserSipe)request.getSession().getAttribute("usuario");
    	
    	if(us == null){
    		redireccionar(acciones.getLoginPage(),request,response);
    		return;
    	}
    	
    	String nickName = us.getNick();
    	
    	if(nickName == null){
    		redireccionar(acciones.getLoginPage(),request,response);
    		return;
    	}
    	
    	String rta = null;
    	
    	try {
    		//TODO sacarlo dinamicamente del xml | listo.
    		servicioGUDE = new ServicioGUDE(); 
			rta = servicioGUDE.ejecutarServicio(nickName,ServicioGUDE.VERIFICAR_ALARMA_CABEZAS,id+" "+nickName);
			rta = rta.replace(',',' ');
		} catch (SapeDataException e) {
			if(log) logs.debug(e);
			error(e,request,response);
			return;
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<script language=\"javascript\">");
		out.println("alert('RESPUESTA: "+rta+"');");
		out.println("location.href = \"/sape/actionSape?accion=indicadores&operacion=indicadoresCabezales\"");
		out.println("</script>");
    	return;
    }
    
    private void operacionIndicadoresCabezales( HttpServletRequest request, HttpServletResponse response) {
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionProbarCabezal");
    	
        String filtro = request.getParameter("filtro");
        String valorFiltro = request.getParameter("valorFiltro");
        String fIni=	request.getParameter("fIni");
        String fFin=	request.getParameter("fFin");
        
        String pagActual = request.getParameter("pagActual");
        String regPorPagina =request.getParameter("regPorPagina"); 
        String orderBy =request.getParameter("orderBy");
        
        if(regPorPagina==null || regPorPagina.equals(""))
        	regPorPagina = "100";
        
        String offset ="0";
        if(pagActual==null || pagActual.equals("")) {
        	pagActual = "1";
        } else {
	        try {
//	        	Integer.parseInt(pagActual);
	        	offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regPorPagina));
	        } catch(NumberFormatException e) {
	        	pagActual = "1";
//	        	offset="0";
	        }
        }
	    
        if(orderBy==null || orderBy.equals(""))
        	orderBy = "id DESC";

        
        if(fIni == null || fFin ==null ||fIni.equals("") || fFin.equals("")){
            
        	fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }        
    	
    	List l = null,listaCentrales = null;
    	try {
			l = siplexPROAutotest.getRegistros(filtro,valorFiltro,fIni+horaIni,fFin+horaFin,regPorPagina,offset,orderBy);
			listaCentrales = serieDAO.getCentralesPorTecnologia("SIPLEXPRO");
		} catch (SapeDataException e) {
			if(log) logs.debug(e);
			error(e,request,response);
			return;
		}
		
		double division = (Long.parseLong((String)l.get(0))/Double.parseDouble(regPorPagina));
        int totalPaginas = (int)Math.ceil(division);
        if(totalPaginas <=1) totalPaginas = 0;
			
		request.setAttribute("orderBy",orderBy);
		request.setAttribute("fIni",fIni);
		request.setAttribute("fFin",fFin);
		request.setAttribute("regPorPagina",regPorPagina);
		request.setAttribute("pagActual",pagActual);
		request.setAttribute("filtro",filtro);
		request.setAttribute("valorFiltro",valorFiltro);
		request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
		
		String query = "&fIni="+fIni+"&fFin="+fFin+(filtro != null && !filtro.equals("")?"&filtro="+filtro+"&valorFiltro="+valorFiltro:"");
        request.setAttribute("query",query);
        request.setAttribute("cantidadTotalRegistros",l.get(0));
		
		request.setAttribute("listaAutotests",l.get(1));
		request.setAttribute("listaSites",l.get(2));
		request.setAttribute("listaCentrales",listaCentrales);
    	redireccionarConPlantilla("indicadoresCabezales",request,response);
    }

    private void operacionEstadisticaTelefonos ( HttpServletRequest request, HttpServletResponse response) {

    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionEstadisticaTelefonos");
    	
        String fIni=	request.getParameter("fIni");
        String fFin=	request.getParameter("fFin");    	
    	
        if(fIni == null || fFin ==null || fIni.equals("") || fFin.equals("")){
                        
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
    	
        String telefono = request.getParameter("telefono");
        
        if(telefono != null && !telefono.equals("")) {
        	List l = null;
        	
        	
        	try {
				l = pruebaBasicaDAO.getEstadisticoTelefonos(telefono,fIni+horaIni,fFin+horaFin);
			} catch (SapeDataException e) {
				if(log) logs.debug(e);
				error(e,request,response);
				return;
			}
			
			request.setAttribute("listaCodigos",l.get(0));
			request.setAttribute("listaEstados",l.get(1));
			request.setAttribute("listaClientes",l.get(2));
			if(l.get(3) != null){
				request.setAttribute("listaPrimeraUltima",l.get(3));
			}

			request.setAttribute("telefono",telefono);
        }
    	request.setAttribute("fIni",fIni);
    	request.setAttribute("fFin",fFin);
    	redireccionarConPlantilla("estadisticoTelefonos",request,response);
    }

    
    private void operacionVentanaEstadisticaUsuario(HttpServletRequest request, HttpServletResponse response) {
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionVentanaEstadisticaUsuario");
    	
    	String fIni=request.getParameter("fIni");
        String fFin=request.getParameter("fFin");
        
        String usuario = request.getParameter("usuario");
    	
        if(fIni == null || fFin == null || fIni.equals("") || fFin.equals("")){
            
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
        
    	String datos = null;
    	try {
			datos = eventoSapeDAO.getInfoPorUsuario(usuario,fIni,fFin);
		} catch (SapeDataException e) {
			if(log) logs.debug(e);
			error(e,request,response);
			return;
		}
		
		request.setAttribute("datos",datos);
        redireccionarConPlantilla("graficaEfectividadPruebas",request,response);
		
    }
    
    private void operacionEstadisticaUsuarios (HttpServletRequest request, HttpServletResponse response) {
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionEstadisticaUsuarios");
    	
        String fIni=request.getParameter("fIni");
        String fFin=request.getParameter("fFin");
        
        String usuario = request.getParameter("usuario");
    	
        String orderBy = request.getParameter("orderBy");
        
        if(fIni == null || fFin == null || fIni.equals("") || fFin.equals("")){
            
            fIni = getFechaHoy();
        	fFin = getFechaHoy();
        }
    	
        if(orderBy==null || orderBy.equals(""))
        	orderBy="cliente ASC";
        
    	List l = null;
    	
    	try {
			l = eventoSapeDAO.getPruebasPorUsuarios(fIni,fFin,usuario,orderBy);
		} catch (SapeDataException e) {
			if(log) logs.debug(e);
			error(e,request,response);
			return;
		}
		
		request.setAttribute("fIni",fIni);
		request.setAttribute("fFin",fFin);
		request.setAttribute("orderBy",orderBy);
		request.setAttribute("listaEventos",l);
		redireccionarConPlantilla("pruebasPorUsuario",request,response);
    }

    private void operacionMostrarAlarma (HttpServletRequest request, HttpServletResponse response) {
	    
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionMostrarAlarma");
    	
    	request.setAttribute("tipo","popup");
    	
    	String cola=request.getParameter("cola");
	    Alarma al=null;
	    try {
	        al=configuracionServicios.getAlarma(cola);
	    } catch (SapeAppException e) {
	    	if(log) logs.debug(e);
	        error(e,request,response);
	        return;
	    }
	    
	    if(al == null){
	        error("Ocurrio un error en la carga de la alarma!!!!",request,response);
	        return;
	    }
	    
	
	    MedioInformar mi = al.getMedio();
	    
	    String medio= "";
	    String valorMedio="";
	    
	    if(mi instanceof MedioBeeper){
	        
	        MedioBeeper mb = (MedioBeeper)mi;
	        medio=mb.getMedio();
	        valorMedio=mb.getCodigo();
	    }else if(mi instanceof MedioSmsOLA){
	        
	        MedioSmsOLA ms = (MedioSmsOLA)mi;
	        medio=ms.getMedio();
	        valorMedio=ms.getTelefono();
	    }else{
	    
	        MedioCorreo mc =(MedioCorreo)mi;
	        medio=mc.getMedio();
	        valorMedio=mc.getDireccion();                
	    }
	    request.setAttribute("medio",medio);
	    request.setAttribute("valorMedio",valorMedio);
	    request.setAttribute("alarma",al);            
	    redireccionarConPlantilla("mostrarAlarma", request,response);
	    return;
	}
    
    private void operacionEjecuteAgregarCola(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
	    
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionEjecuteAgregarCola");
    	
    	String nombreCola=request.getParameter("nombreCola");
	    String descripcion=request.getParameter("descripcion");
	    
	    try {
	        configuracionServicios.adicionarCola(new Cola(nombreCola,descripcion));
	    } catch (SapeAppException e) {
	    	if(log) logs.debug(e);
	        error(e,request,response);
	        return;
	    }
	    
	    if(log) logs.info("Se llamara la funcion interna [operacionMantenimiento]:");
	    operacionMantenimiento(request,response);
	    return;
	}
    
    private void operacionEliminarAlarma(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
    
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionEliminarAlarma");
    	
	    String cola = request.getParameter("nombreCola");
	    
	    try {
	        configuracionServicios.eliminarAlarma(cola);
	    } catch (SapeAppException e) {
	    	if(log) logs.debug(e);
	        error("No fue posible eliminar esta alarma de la cola:"+cola+".\n"+e.toString(),request,response);
	        return;
	    }
	    if(log) logs.info("Se llamara la funcion interna [operacionMantenimiento]:");
	    operacionMantenimiento(request,response);
	    return;
	}
    
    private void operacionEjecuteModificarAlarma(HttpServletRequest request, HttpServletResponse response)throws IOException {
    	
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionEjecuteModificarAlarma");
    	
        String nombreCola=request.getParameter("nombreCola");
        String limite=request.getParameter("limite");
        String avisar=request.getParameter("avisar");
        String mensaje=request.getParameter("mensaje");
        String medio=request.getParameter("medio");
        String valorMedio=request.getParameter("valorMedio");
        //String recordar=request.getParameter("recordar");

        int limit=0;
        
        try{
            //rec=Integer.parseInt(recordar);
            limit=Integer.parseInt(limite);
        }catch (NumberFormatException e) {
        	if(log) logs.debug(e);
            error(e,request,response);
            return;
        }
        
        Alarma ali=null;
        
        if(medio.equals("correo")){
            
            MedioCorreo mc = new MedioCorreo(valorMedio);
            //XXX El tiempo de recordacion no se aplica por ahora(se dejo de 20 por default
            ali=new Alarma(nombreCola,limit,avisar,mensaje,mc,20); 

        }else if(medio.equals("smsOla")){
            
            MedioSmsOLA ms = new MedioSmsOLA(valorMedio);
            ali=new Alarma(nombreCola,limit,avisar,mensaje,ms,20);
        }else{
            MedioBeeper mb = new MedioBeeper(null,valorMedio);
            ali=new Alarma(nombreCola,limit,avisar,mensaje,mb,20);                
        }

        try {
            configuracionServicios.eliminarAlarma(nombreCola);
            configuracionServicios.adicionarAlarma(ali);
        } catch (SapeAppException e) {
        	if(log) logs.debug(e);
            error(e,request,response);
            return;
        }
        
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        out.print("<script type=\"text/javascript\">\n");
        out.print("window.close(); </script>\n");

        return;
    
    }
    
    
    private void detallePruebas(HttpServletRequest request, HttpServletResponse response) {
        
    	boolean log = logs.isDebugEnabled();
    	
    	if (log) logs.debug("detallePruebas");
    	
    	String opcion = request.getParameter("opcion");
    	if (opcion == null || opcion.equals("")) {
    		opcion = "";
    	}
        
        String usuario = request.getParameter("usuario");
    	    
    	String valorFiltro = request.getParameter("valor");
    	String fIni=	request.getParameter("fechaIni");
        String fFin=	request.getParameter("fechaFin");
         
        String pagActual = request.getParameter("pagActual");
        String regPorPagina =request.getParameter("regPorPagina"); 
        String orderBy =request.getParameter("orderBy");
        
        if(fIni == null || fFin == null || fIni.equals("") || fFin.equals("")) {
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
              
            	
            List l = null;
            try {
            	l = reportesDAO.getRegistros(opcion, valorFiltro,usuario,fIni,fFin,regPorPagina,offset,orderBy);
            } catch (SapeDataException e) {
            	if(log) logs.debug(e);
            	error(e, request, response);
            	return;
            }
            
            request.setAttribute("listaEventos",l.get(1));
            
            request.setAttribute("fIni",fIni);
            request.setAttribute("fFin",fFin);
            
            request.setAttribute("offset",offset);
            request.setAttribute("regPorPagina",regPorPagina);
            request.setAttribute("pagActual",pagActual);

            double division = Double.parseDouble((String)l.get(0))/Double.parseDouble(regPorPagina);
            int totalPaginas = (int)Math.ceil(division);
            if(totalPaginas <=1) totalPaginas = 0;
            
            
            	//para pasarle a la opcion de exportar.
            String query = "&opcion=" + opcion + "&valor=" + valorFiltro + "&fechaIni=" + fIni + "&fechaFin=" + fFin+"&usuario="+usuario;
            request.setAttribute("query", query);
            
            request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
                       
            request.setAttribute("orderBy",orderBy);
            request.setAttribute("cantidadTotalRegistros",l.get(0));
            
            String mensajeCentral = "Numero de registros "+l.get(0) + ". Par&aacute;metro de B&uacute;squeda: " + opcion;
            if (usuario != null && !usuario.equals("")) mensajeCentral += ". Usuario: " + usuario;
            request.setAttribute("msgCentral", mensajeCentral);
            redireccionarConPlantilla("ventanaConsultaPruebas", request,response);
    }
    
    
    private void operacionEjecuteAgregarAlarma(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("");
    	
        String nombreCola=request.getParameter("nombreCola");
        String limite=request.getParameter("limite");
        String avisar=request.getParameter("avisar");
        String mensaje=request.getParameter("mensaje");
        String medio=request.getParameter("medio");
        String valorMedio=request.getParameter("valorMedio");
    
        int limit=0;
        
        try{
            limit=Integer.parseInt(limite);
        }catch (NumberFormatException e) {
        	if(log) logs.debug(e);
            error(e,request,response);
            return;
        }
        
        Alarma ali=null;
        
        if(medio.equals("correo")){
            
            MedioCorreo mc = new MedioCorreo(valorMedio);
            //XXX El tiempo de recordacion no se aplica por ahora(se dejo de 20 por default
            ali=new Alarma(nombreCola,limit,avisar,mensaje,mc,20); 
            
        }if(medio.equals("smsOla")){
            
            MedioSmsOLA ms = new MedioSmsOLA(valorMedio);
            ali=new Alarma(nombreCola,limit,avisar,mensaje,ms,20);
        }else{
            MedioBeeper mb = new MedioBeeper(null,valorMedio);
            ali=new Alarma(nombreCola,limit,avisar,mensaje,mb,20);                
        }
        
        try {
            configuracionServicios.adicionarAlarma(ali);
        } catch (SapeAppException e) {
        	if(log) logs.debug(e);
            error(e,request,response);
            return;
        }
        
        if(log) logs.info("Se llamara la funcion interna de IndicadoresServlet [operacionMantenimiento]:");
        operacionMantenimiento(request,response);
        return;

    }
    
    
    
    private void operacionIndicadoresColas(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionIndicadoresColas");
    	
    	try {
		    ArrayList l=configuracionServicios.getAllColas();
		    request.setAttribute("listaColas",l);
		} catch (SapeAppException e) {
			if(log) logs.debug(e);
		    error(e,request,response);
		    return;
		}
		request.getRequestDispatcher(acciones.getTemplate("inicioIndicadoresColas")).forward(request,response);
		return;
    }
    
    
    private void operacionGraficaTSTLI(HttpServletRequest request, HttpServletResponse response) {
        
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionGraficaTSTLI");
    	
    	String tipo = request.getParameter("tipo");
        String data=null;
        List listaLinks = null;
        int par = -1;
        if(tipo != null){
            
            if(tipo.equals("armario_id") || tipo.equals("cable")){
                String tope = request.getParameter("tope");
                request.setAttribute("opcionGraficaArmarios","true");
                if(tope != null){

                    int top = 0;
                    try{
                        top = Integer.parseInt(tope);
                    }catch(NumberFormatException err){
                    	if(log) logs.debug(err);
                        error("El valor debe ser numerico.\nERROR: "+err.toString(),request,response);
                        return;
                    }
                    par=top;
                }
            }
            Long count = null;
	        try {
	        	
	        	if(par <= 0) par = 1;
	        	List l =requerimientosColaDAO.getIndicadoresTSTLI(tipo, par);
	            if(l == null)
	            	data = null;
	            else {
	            	data = (String)l.get(0);
	            	count = (Long)l.get(1);
	            	listaLinks = (List)l.get(2);
	            	
	            }
	            
	        } catch (HibernateException e) {
	        	if(log) logs.debug(e);
	        	error(e,request,response);
	        	return;
	        } catch (SapeDataException e) {
	        	if(log) logs.debug(e);
	        	error(e,request,response);
	        	return;
	    	}
	    	
	    	if(data == null){
	    		if(log) logs.debug("No se han encontrado datos para mostrar.");
	    	    error("No se han encontrado datos para mostrar.",request,response);
	    	    return;
	    	}

	    	
	    	if(data.startsWith("Ocurrio una exepcion")){
	    		if(log) logs.debug(data);
	    	    error(data,request,response);
	    	    return;
	    	}
	    	
	    	String dt = null;
	    	
	    	if(tipo.equals("cable")) dt ="Cable";
	    	else if(tipo.equals("tipo_nodo")) dt ="Tipo de Nodo";
	    	else if(tipo.equals("area_trabajo_id")) dt ="Area de Trabajo";
	    	else if(tipo.equals("armario_id")) dt ="Armario";
	    	else if(tipo.equals("subzona_id")) dt ="Subzona";
	    	data = "Grafica de TSTLI por "+dt+", "+count.toString()+" registros.*"+dt+"*Cantidad*"+data;
            request.setAttribute("datos",data);
            request.setAttribute("tipo",tipo);
            request.setAttribute("tope",String.valueOf(par));
            request.setAttribute("listaLinks",listaLinks);
            redireccionarConPlantilla("graficaTSTLI", request,response);
    	}else{
    		if(log) logs.debug("Falta el parametro tipo de grafica!!");
    	    error("Falta el parametro tipo de grafica!!",request,response);
    	    return;
    	}
        
    }
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
  	
    	
    	if(logs.isDebugEnabled()) logs.debug("Peticion hecha por POST!!!!");
    	doGet(request,response);
    }    
       
    private void operacionEliminarCola(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	   
    	boolean log = logs.isDebugEnabled();
    	
    	if(log) logs.debug("operacionEliminarCola");
    	
    	String cola = request.getParameter("nombreCola");
	    
	    try {
	        configuracionServicios.eliminarCola(cola);
	    } catch (SapeAppException e) {
	    	if(log) logs.debug(e);
	        error("No fue posible eliminar esta cola de la lista:\n"+e.toString(),request,response);
	        return;
	    }
	    operacionMantenimiento(request,response);
	    return;
    }
    
    
    private void operacionMantenimiento(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    List listaColas=null,listaAlarmas=null;
	    try {
	        listaColas = configuracionServicios.getAllColas();
	        listaAlarmas = configuracionServicios.getAllAlarmas();
	    } catch (SapeAppException e) {
	        error(e,request,response);
	        return;//buzongestion@eeppm.com //sivarr
	    }
	    
	    request.setAttribute("listaColas",listaColas);
	    request.setAttribute("listaAlarmas",listaAlarmas);
	    request.getRequestDispatcher(acciones.getTemplate("mantenimientoColas")).forward(request,response);
    }
    
    private void operacionGraficaIndicadores(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println("indicador COLAS LLEGO HASTA ACA!!!");
        StringBuffer parametrosP = new StringBuffer(10);//ESTOS DOS SE IMPLEMENTAN SI SOLICITA UNA GRAFICA CON
        StringBuffer parametrosC = new StringBuffer(10);//LOS 2 ESTADOS.
        
        Date fechaUltimaCarga = null;
        boolean firtLast = false;//para ke solo tome la ultima fecha en fecha de carga.
        String estado = request.getParameter("radioEstado");
  
        String co[] = request.getParameterValues("chkCola");
        System.out.println("valor del vector es="+co.length);

        ServicioIndicadoresOSS p =null;
        ServicioIndicadoresOSS c=null;
        long inicioTime=System.currentTimeMillis();
        for (int i = 0; i < co.length; i++) {
            try {
                if (estado.equals("AMBAS") || estado.equals("PENDI")) {
                    p = servicioIndicadoresOSSDAO.getServicio(co[i],"PENDI");
                }
                if (estado.equals("AMBAS") || estado.equals("CUMPL")) {
                    c = servicioIndicadoresOSSDAO.getServicio(co[i],"CUMPL");
                }
                
            }catch(SapeDataException e){
//                e.printStackTrace();
                error(e,request,response);
                return;
            }
            if(p == null){
                parametrosP.append(co[i]+"*0*");
            }else{
                parametrosP.append(co[i]+"*"+ p.getCantidad() + "*");
                
                if(!firtLast){
                	fechaUltimaCarga = p.getFechaCarga();
                	firtLast = true;
                }
                
                //System.out.println("Ultima fecha de carga P="+fechaUltimaCarga);
            }
            
            if(c ==null){
                parametrosC.append(co[i]+"*0*");
            }else{
                parametrosC.append(co[i]+"*"+ c.getCantidad() + "*");
                	//actualizo fechaUltimaCarga con la del ultimo registro que voy encontrando.
                //fechaUltimaCarga = c.getFechaCarga();
                //System.out.println("Ultima fecha de carga C="+fechaUltimaCarga);
            }                   
//                System.out.println("fecha: " + fechaUltimaCarga);
        } //fin del for
            System.out.println("TIEMPO TOTAL DE CONSULTAS: "+(System.currentTimeMillis()-inicioTime));
        
            StringBuffer pp = new StringBuffer(10);
            StringBuffer pc = new StringBuffer(10);
            
            	//cuando se solicita solamente una cola de ambos estados
            	//se muestran las dos barras en una sola grafica.
            if(estado.equals("AMBAS")) {
//              ESTE CODIGO FUNCIONA CON LAS VERSIONES VIEJAS DE JFREECHART
//                pp.append("COLAS PENDIENTES Y CUMPLIDAS*ESTADO*CANTIDAD*");
//                pp.append("PENDIENTE_"+parametrosP.toString());
//                pp.append("CUMPLIDO_"+parametrosC.toString());
//                estado = "PENDI";// SE LE CAMBIO EL ESTADO PARA QUE MUESTRE
//                				///SOLO UNA GRAFICA Y NO PARTA LA PANTALLA
                //ESTE CODIGO FUNCIONA CON LAS VERSIONES VIEJAS DE JFREECHART
                if(co.length == 1){//pidio los dos estados de una sola cola
                    
                    pp.append("1-2");
                    pc.append("AMBAS*COLA*CANTIDAD*");
                    pc.append(parametrosP);
                    pc.append(parametrosC);
                    Alarma alo=null;
                    try {
//                        System.out.println("configuracion servicios es: " + configuracionServicios);
//                        System.out.println("p es> " + p);
                        alo=configuracionServicios.getAlarma(p.getCola());
//                        System.out.println("ali queda: " + alo);
                    } catch (SapeAppException e) {
                        error(e,request,response);
                        return;
                    }
                    
                    if(alo != null){//TENIA ALARMA ESA COLA
                        //esto se hara para obtener el limite de desbordamiento de la
                        //cola y graficar este en el applet!!!!
                        System.out.println("TENIA ALARMA ASOCIADA!!!");
                        request.setAttribute("limite",String.valueOf(alo.getLimite()));
                    }
                    
                } else {
	                pp.append("PENDIENTE.*COLA*CANTIDAD*");
	                pp.append(parametrosP);
	                pc.append("CUMPLIDO.*COLA*CANTIDAD*");
	                pc.append(parametrosC);                    
                }
            }else if( estado.equals("PENDI")){
                
                pp.append("COLAS PENDIENTES.*COLA*CANTIDAD*");
                pp.append(parametrosP);
                
                pc.append("VACIO");
//                pp.append("COLAS PENDIENTES.*ESTADO*CANTIDAD*");
//                pp.append(parametrosP);
//                pc.append("COLAS CUMPLIDAS.*ESTADO*CANTIDAD*");
//                pc.append(parametrosC);
            }else{
                pc.append("COLAS CUMPLIDAS.*ESTADO*CANTIDAD*");
                pc.append(parametrosC);
                pp.append("VACIO");
            }
            
            request.setAttribute("fechaUltimaCarga", fechaUltimaCarga);
            request.setAttribute("graficasMostrar", estado);
            request.setAttribute("parametrosP",pp.toString());
            request.setAttribute("parametrosC",pc.toString());
            
            request.getRequestDispatcher(acciones.getTemplate("mostrarIndicadorDoble")).forward(request,response);
        
    } //fin de graficaIndicadores
}
