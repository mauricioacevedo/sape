package com.osp.sape.servlets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.ObjectNotFoundException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.gc.acceso.GestorServlet;
import com.osp.sape.SapeConfiguration;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.CategoriasDAO;
import com.osp.sape.data.CodigosVerDAO;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.EventoSapeDAO;
import com.osp.sape.data.PruebaAtuDAO;
import com.osp.sape.data.PruebaBasicaDAO;
import com.osp.sape.data.PruebaEstadisticaConexionDAO;
import com.osp.sape.data.PruebaPingDAO;
import com.osp.sape.data.SerieDAO;
import com.osp.sape.data.TipoNodoDAO;
import com.osp.sape.data.TipoPruebaDAO;
import com.osp.sape.maestros.Categorias;
import com.osp.sape.maestros.CodigoVer;
import com.osp.sape.maestros.EventoSape;
import com.osp.sape.maestros.PruebaAtu;
import com.osp.sape.maestros.PruebaBasica;
import com.osp.sape.maestros.PruebaSiplexPRO;
import com.osp.sape.maestros.Serie;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.maestros.siplexpro.PruebaEstadisticasConexion;
import com.osp.sape.maestros.siplexpro.PruebaPing;
import com.osp.sape.utils.ConfiguracionClienteSape;
import com.osp.sape.utils.ServicioGUDE;


public class PruebaTelefonoServlet extends GestorServlet {

	private SerieDAO serieDAO;
	private TipoNodoDAO tipoNodoDAO;
	private EventoSapeDAO eventoSapeDAO;
	private PruebaBasicaDAO pruebaBasicaDAO;
	private CodigosVerDAO codigosVerDAO;
	private CategoriasDAO categoriasDAO;
	private ServicioGUDE servicioGUDE;
	private PruebaAtuDAO pruebaAtuDAO;
	private PruebaBasicaDAO pruebaSiplexPRODAO;
	private PruebaEstadisticaConexionDAO pruebaEstadisticaDAO;
    private ConfiguracionClienteSape configuracionClienteSape;
	private TipoPruebaDAO tipoPruebaDAO;

	private PruebaPingDAO pruebaPingDAO;
	
	private List pruebasSiplexpro = null;

	
    public void init() throws ServletException {
    	super.init();
    	DAOFactory factory = DAOFactoryImpl.getInstance();
    	
    	serieDAO = factory.getSerieDAO();
    	tipoNodoDAO = factory.getTipoNodoDAO();
    	eventoSapeDAO = factory.getEventoSapeDAO();
    	pruebaBasicaDAO = factory.getPruebaBasicaDAO();
    	codigosVerDAO = factory.getCodigosVerDAO();
    	categoriasDAO = factory.getCategoriasDAO();
    	servicioGUDE = factory.getServicioGUDE();
    	pruebaAtuDAO = factory.getPruebaAtuDAO();
    	pruebaSiplexPRODAO = factory.getPruebaSiplexPRODAO();
    	pruebaEstadisticaDAO = factory.getPruebaEstadisticasConexionDAO();
    	tipoPruebaDAO = factory.getTipoPruebaDAO();

    	pruebaPingDAO = factory.getPruebaPingDAO();
    	
    	configuracionClienteSape = ConfiguracionClienteSape.getInstance();
    }
    

    public void doGet(HttpServletRequest request, HttpServletResponse  response) throws IOException, ServletException {
        
    	String operacion = request.getParameter("operacion");
        if(logs.isDebugEnabled())logs.debug("doGet: operacion=["+operacion+"]");

        if(operacion == null || operacion.equals("inicio")){
            redireccionarConPlantilla("PruebaTelefono",request,response);
            return;
        }
        
	     if(operacion.equals("buscarCentral")){
	        buscarCentral(request, response); 
	        return;
	     }
	     
	     if(operacion.equals("probarTelefono")) {
	    	 probarTelefono(request, response);
	    	 return;
	     }
	     
	     if (operacion.equals("historicoTelefonos")) {
	    	 historicoTelefonos(request, response);
	    	 return;
	     }
	     
	     if (operacion.equals("mostrarResultados")) {
	    	 mostrarResultados(request, response);
	    	 return;
	     }
	     if (operacion.equals("categorias")) {
	    	 operacionCategorias(request,response);
	    	 return;
	     }
	     
	     if(operacion.equals("telnetInteractiva")){
	    	 operacionTelnetInteractiva(request,response);
	    	 return;
	     }	     
	     
	     /*
	      *13-oct-2006 John David Gutierrez 
	      *Soporte para la intearctiva clip FTX
	      */
	     if(operacion.equals("telnetInteractivaClipFTX")){
	    	 operacionInteractivaClipFTX(request,response);
	    	 return;
	     }
	     
	     	//He decidido volver el sistema a estado de mantenimiento
	     if (operacion.equals("estadoMantenimiento")) {
	    	 	//si ya existe la variable la elimino
	    	 if (getServletContext().getAttribute("mantenimientoSistema") != null) 
	    		 getServletContext().removeAttribute("mantenimientoSistema"); 
	    	 else
	    		 getServletContext().setAttribute("mantenimientoSistema", "MANTENIMIENTO");
	    	 
	    	 return;
	     }
	     
	     if(operacion.equals("pruebasClip")) {
	    	 
	    	 operacionInicioPruebasClip(request,response);
	    	 return;
	     }
	     
	     //11-sep-2006 John David Gutierrez. A peticion de Adonilfo
	     //Prueba clip para los FTX
	     if(operacion.equals("pruebaClipFTX")) {//TODO Clip FTX
	    	 
	    	 probarClipFTX(request,response);
	    	 return;
	     }
	     
	     if(operacion.equals("buscarSite")) {
	    	 
	    	 operacionBuscarSite(request,response);
	    	 return;
	     }
	     
	     if(operacion.equals("pruebasPBX")){
	    	 redireccionarConPlantilla("pruebaPBX",request,response);
	    	 return;
	     }
	     if(operacion.equals("probarPBX")){
	    	 operacionProbarPBX(request,response);
	    	 return;
	     }
	     if(operacion.equals("listaTelefonosPBX")){
	    	 redireccionarConPlantilla("listaTelefonosPBX", request, response);
	    	 return;
	     }
	     if(operacion.equals("procesarListaTelefonosPBX")){
	    	 operacionProcesarListaTelefonosPBX(request,response);
	    	 return;
	     }
	     
    }
    
    
    private void operacionProcesarListaTelefonosPBX(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
    	
    	
    	String data = request.getParameter("data");
    	
    	request.setAttribute("tipo", "cerrar");
    	if(data == null || data.equals("")) {
    		error("No hay informacion para procesar!!!.",request,response);
    		return;
    	}

//    	System.out.println("DATOS: \n\n\n\n\n "+data);
    	
    	StringTokenizer st = new StringTokenizer(data,",");
    	
    	List buenos = new ArrayList();
    	List negados = new ArrayList();
    	
    	StringTokenizer l = null;
    	
    	String info[] = null;
    	
    	int i=0,j=0;
    	
//    	System.out.println("tokens grande: "+st.countTokens());
    	
    	while(st.hasMoreElements()) {
    		
    		String linea = st.nextToken();
    		
//    		System.out.println("Linea: ["+linea+"] ");
    		info = new String[2];
    		l = null;
    		
    		l = new StringTokenizer(linea,"*");
    		
    		
//    		System.out.println("contro de tokens: "+l.countTokens());
    		
    		if(l == null || l.countTokens() == 0)
    			continue;

    		String telefono = null;
    		String nombre = null;
    		
    		if(l.countTokens() == 1) {
    			telefono = l.nextToken();
    			nombre = "ND";
    		} else {
    			telefono = l.nextToken();
    			nombre = l.nextToken();
    		}

    		info[0] = telefono;
    		info[1] = nombre;
    		
    		if(telefono == null || telefono.equals("")) {
    			info[0] = "ND";
    			negados.add(j,info);
    			j++;
    			continue;
    			
    		}

    		if(telefono.length() != ((Integer)getServletContext().getAttribute("cantDigitosEntorno")).intValue()) {
    			negados.add(j,info);
    			j++;
    			continue;
    		}
    		
    		try {
    			Integer.parseInt(telefono);
    		}catch(NumberFormatException e) {
    			negados.add(j,info);
    			j++;
    			continue;
    		}
    		
    		buenos.add(i,info);
    		i++;
    		
    	}
    	
//    	System.out.println("buenos: "+buenos.size()+" negados: "+negados.size());
    	
    	//request.setAttribute("listaBuenos",buenos);
    	request.getSession().setAttribute("listaTelefonosPBX",buenos);
    	//request.setAttribute("listaNegados",negados);
    	
    	mensaje("Telefonos a probar: "+buenos.size()+", ignorados: "+negados.size()+".","cerrar",request,response);
    	
    	return;
    }

    
    
    private void operacionProbarPBX(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	String tels = request.getParameter("telefonos");
    	
    	//por si se monto una lista de telefonos desde el pegado de excel
    	request.getSession().removeAttribute("listaTelefonosPBX");
    	
    	
    	StringTokenizer stt = new StringTokenizer(tels,",");
    	
    	String queryTelefono = "";
    	int cantMalos=0,cantBuenos=0;
    	while(stt.hasMoreTokens()){
    		
    		String tmp = stt.nextToken();
    		
    		try{
    			Integer.parseInt(tmp);
    			if(tmp.length() != ((Integer)getServletContext().getAttribute("cantDigitosEntorno")).intValue()){
        			cantMalos++;
        			continue;
    			}
    		}catch(NumberFormatException e){
    			cantMalos++;
    			continue;
    		}
    		
    		queryTelefono += tmp+" ";
    		cantBuenos++;
    		
    	}
//    	System.out.println("queryTelefono: "+queryTelefono);
    	// para eliminar el ultimo espacio en blanco!!!
    	if(queryTelefono!=null&&queryTelefono.length()>1)
    		queryTelefono = queryTelefono.substring(0,queryTelefono.length()-1);
    	
    	response.setBufferSize(256);
    	response.resetBuffer();
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	
    	out.println("<html><body bgcolor=\"black\" text=\"white\" link=\"WHITE\"><pre>\n");
    	    	
    	String user = "PBX_"+((UserSipe)request.getSession().getAttribute("usuario")).getNick();
    	String salida= "";
    	String mensaje = "";
    	try {

			salida=servicioGUDE.ejecutarServicio(user,"4",user+" "+queryTelefono,out);
		} catch (SapeDataException e) {
			mensaje = "Ocurrio un error: "+e.getMessage();
			out.println("<script>window.parent.pruebaTerminada(" + mensaje+"');</script>");
	        if (logs.isDebugEnabled()) logs.debug("Termino la prueba.");
	        
	        out.println("</pre>");
	        out.println("</body></html>");
	        
	        out.flush();
	        out.close();
	    	return;
		}   	
		if (logs.isDebugEnabled()) logs.debug("Resultado de Prueba PBX: "+salida);
		
		if(salida.startsWith(" [Ok]."))
			salida = salida.substring(6,salida.length());
		mensaje = salida;
		/*if(stt.countTokens() == 1)
			mensaje = "Se probara el PBX "+queryTelefono+" ."+cantMalos;
		mensaje = "Se probaran "+cantBuenos+" telefonos, ignorados "+cantMalos;*/
    	out.println("<script>window.parent.pruebaTerminada('" + mensaje+"');</script>");
        if (logs.isDebugEnabled()) logs.debug("Termino la prueba.");
        
        out.println("</pre>");
        out.println("</body></html>");
        out.flush();
        out.close();
    	return;
    }
    
    
    private void operacionBuscarSite(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	String central = request.getParameter("central");
    	List l = null;
    	try {
			l=tipoNodoDAO.getTipoNodosCentral(central);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		
    	Document documento = new Document();
    	Element root = new Element("cabezas");
    	documento.setRootElement(root);
    	
//    	System.out.println("cabezas para central: "+central+" size:"+l.size());
    	for(int i=0;i<l.size();i++){
    		Element element = new Element("site");
        	TipoNodo tmp = (TipoNodo)l.get(i);
    		String valor = tmp.getId()+","+tmp.getSite();
    		
    		element.setText(valor);
        	
        	root.addContent(element);	
    	}
    	
    	
    	/*
    	element = new Element("tipoCentral");
    	element.setText(tipoCentral);
    	root.addContent(element);
    	element = new Element("cabezaId");
    	element.setText(String.valueOf(cabezaId));
    	root.addContent(element);
   
    	String pruebas[] = null;
        if(tipoCentral.equalsIgnoreCase("SIPLEXPRO")){
            pruebas = getPruebasSiplexPRO();
        }else if (tipoCentral.equals("AXE") || tipoCentral.equals("NEC")){
            pruebas = new String [] {"basica", "categorias"};
        } else {
        	pruebas = new String [] {"basica"};
        }
      
   */ 
    
    	PrintWriter out = response.getWriter();
		XMLOutputter serializer = new XMLOutputter();
		serializer.output(documento, out);
				
		out.flush();	
		out.close();
		
    }
    
	private void operacionInicioPruebasClip(HttpServletRequest request, HttpServletResponse response) {
    	
   	 	List l = null;
   	 	try {
   	 		l = serieDAO.getCentralesPorTecnologia("SIPLEXPRO");
   	 		l.addAll(serieDAO.getCentralesPorTecnologia("SIPLEXPRO-MAT"));
   	 	} catch (SapeDataException e) {
   	 		error(e,request,response);
   	 		return;
   	 	}
		request.setAttribute("listaCentrales",l);
		
		l = getPruebasSiplexPRO();
		String pruebas[]= (String[]) l.get(0); 
		l = new ArrayList();
		
		
		for (int i = 0; i< pruebas.length;i++){
			l.add(i,pruebas[i]);
		}
		
		request.setAttribute("pruebas",l);
	 	redireccionarConPlantilla("pruebasClip",request,response);
	 	return;
    }
    
	/**
	 * 13-oct-2006 John David Gutierrez
	 * Soporte para la prueba clip FTX
	 */
	private void operacionInteractivaClipFTX(HttpServletRequest request, HttpServletResponse response){
		String telOperador=(String)request.getSession().getAttribute("telOperador");
    	
    	if(telOperador == null || telOperador.equals("")){
    		telOperador = request.getParameter("telOperador");
    		
    		if(telOperador != null && !telOperador.equals(""))
    			request.getSession().setAttribute("telOperador",telOperador);
    	}
    	
    	String telCliente =  request.getParameter("telCliente");
    	String ipInd = request.getParameter("ip");
    	String puertoInd = request.getParameter("puerto");
    	String tipoPrueba = request.getParameter("tipoPrueba");
    	
		String ip =configuracionClienteSape.getServerInteractivas();
    	String port=configuracionClienteSape.getPuertoInteractivas();
    	
    	System.out.println("||||ip cliente: " + ip);
    	System.out.println("||||puerto cliente: " + port);

    	Serie serie = null;
    	try {
			serie = serieDAO.buscarSerie(telCliente);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}

		if(serie == null){
			request.setAttribute("tipo","cerrar");
			error("No se encontro ninguna serie para el telefono: '"+telCliente+"'.",request,response);
			return;
		}
				
		request.setAttribute("tecnologia",serie.getTipocentral());
		request.setAttribute("telCliente",telCliente);
		request.setAttribute("telOperador",telOperador);
		request.setAttribute("ip",ip);
		request.setAttribute("port",port);
		request.setAttribute("ipInd",ipInd);
		request.setAttribute("puertoInd",puertoInd);
		request.setAttribute("tipoPrueba",tipoPrueba);

		redireccionarConPlantilla("telnetInteractivas",request,response);
	}
	
    private void operacionTelnetInteractiva(HttpServletRequest request, HttpServletResponse response) {
    	
    	String telOperador=(String)request.getSession().getAttribute("telOperador");
    	
    	if(telOperador == null || telOperador.equals("")){
    		telOperador = request.getParameter("telOperador");
    		
    		if(telOperador != null && !telOperador.equals(""))
    			request.getSession().setAttribute("telOperador",telOperador);
    	}
    	
    	String telCliente =  request.getParameter("telCliente");
    	
    	/*
    	if(telOperador == null || telOperador.equals("")){
    		//telOperador = telOperador;
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		out.println("<script  language=\"JavaScript\">"+
    				"var telOper = prompt('Ingrese el telefono del Operador');"+
    				"location.href = \"/sape/actionSape?accion=pruebaTelefono&operacion=telnetInteractiva&telCliente="+telCliente+"&telOperador=\"+telOper"+
    				"</script>"
    		);
    		return;
    	}*/
    	

    	/*List l = null;
    	try {
			l=procesosSapeDAO.getProcesosSape("nombre","nombre","interactiva");
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		if(l==null || l.size() == 0){
			logs.debug("NO ENCONTRO NINGUN HOST PARA LAS INTERACTIVAS!!!!");
			error("No se encontro Ningun Host configurado para las Pruebas Interactivas.",request,response);
			return;
		}else
			logs.debug("BEAN: "+((ProcesosSape)l.get(0)).getHost());
    	*/
		String ip =configuracionClienteSape.getServerInteractivas();
    	String port=configuracionClienteSape.getPuertoInteractivas();

    	Serie serie = null;
    	try {
			serie = serieDAO.buscarSerie(telCliente);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}

		if(serie == null){
			request.setAttribute("tipo","cerrar");
			error("No se encontro ninguna serie para el telefono: '"+telCliente+"'.",request,response);
			return;
		}
		
		/*if(serie.getTipocentral().equals("FTX") ){
			
			request.setAttribute("tipo","cerrar");
			error("Monitoreo para tecnologia '"+serie.getTipocentral()+"' aun no esta en servicio.",request,response);
			return;
		}*/
				
		request.setAttribute("tecnologia",serie.getTipocentral());
		request.setAttribute("telCliente",telCliente);
		request.setAttribute("telOperador",telOperador);
		request.setAttribute("ip",ip);
		request.setAttribute("port",port);
		

		redireccionarConPlantilla("telnetInteractivas",request,response);
    }
    
    private void buscarCentral (HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if (logs.isDebugEnabled()) logs.debug("buscarCentral");
        
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
    	
        String telefono = request.getParameter("telefono");
        if (logs.isDebugEnabled()) logs.debug("Buscar Central. telefono= "+telefono);
//        List l=null;
        Serie serie = null;
        
        //TODO : planear bien como controlar esta exception!!!
        
        String mantenimiento = null;
        if(telefono == null || telefono.equals("")) {
        	mantenimiento = "";
        	// esto es para ke no entre al if siguiente!!!!
        	// ojo, cuidado, no borrar!!!!
        }
        

        String central = "NOT FOUND";
        String tipoCentral = "NOT FOUND";
        int cabezaId = 0;
        	///XXX Sistema en mantenimiento.
        	//Verifico si el sistema esta en estado de mantenimiento para no buscar la serie.
        	//cuando el sistema pasa a mantenimiento ningun usuario via WEB podra hacer pruebas.
        mantenimiento = (String)getServletContext().getAttribute("mantenimientoSistema");
        if (mantenimiento == null) {        
	        try{
	            serie=serieDAO.buscarSerie(telefono);
	        }catch(SapeDataException e){
	        	central = e.toString();
	        	tipoCentral = central;
	        }
	        if(serie != null) {
	        	central = serie.getCentral();
	        	tipoCentral = serie.getTipocentral();
	        	cabezaId = serie.getCabezaId().intValue();
	        }
        } else {
        	central = mantenimiento;
        	tipoCentral = mantenimiento;
        }
        
        /**
         * EL siguiente codigo se encarga de validar si un numero, dependiendo si es tecnologia
         * EWSD hace la validacion en la tabla siplexpro_ewsd para ver si el sape lo maneja.
         */
         /*
        logs.debug("TIPO CENTRAL:"+(serie != null?serie.getTipocentral():"[NULL]"));
        if(serie != null && serie.getTipocentral().equals("SIPLEXPRO-EWSD") && telefono != null){
        	logs.debug("Se Busca si esta en la tabla de EWSD");
        	ConfiguracionEWSD con=null;
        	try {
				con=configuracionEWSDDAO.getConfiguracionEWSD(telefono);
			} catch (SapeDataException e) {
	        	central = e.toString();
	        	tipoCentral = central;
			}
			
			if(con==null){
				central="NOT FOUND";
				tipoCentral = central;
			}
				
        }*/
        
        
        
        if (debug) logs.debug("Creo el documento: " + central + "," + tipoCentral + "," + cabezaId);
    	Document documento = new Document();
    	Element root = new Element("serie");
    	documento.setRootElement(root); 
    	Element element = new Element("central");
    	element.setText(central);
    	root.addContent(element);
    	element = new Element("tipoCentral");
    	element.setText(tipoCentral);
    	root.addContent(element);
    	element = new Element("cabezaId");
    	element.setText(String.valueOf(cabezaId));
    	root.addContent(element);
   
    	String pruebas[] = null;
    	String descripciones[] = null;
        if(tipoCentral.indexOf("SIPLEXPRO") != -1) {
            List l = getPruebasSiplexPRO();
            pruebas = (String[]) l.get(0);
            descripciones = (String[]) l.get(1);
        }else if (tipoCentral.equals("AXE") || tipoCentral.equals("NEC")){
            pruebas = new String [] {"basica", "categorias"};
            descripciones = new String [] {"Prueba Basica", "Por Categorias"};
        } else {
        	// Se supone ke si llego hasta aca es una FTX!!!
        	pruebas = new String [] {"basica","basica_force"};
        	descripciones = new String [] {"Prueba Basica", "Basica Forzada"};
        }
        
        // estos elementos iran visibles en el combobox de tipos de prueba
    	Element ePruebas = new Element("pruebas");
    	// estas descripciones se mostraran en un div al lado de su respectivo tipo de prueba.
    	Element eDescripciones = new Element("descripciones");
    	for (int i = 0; i < pruebas.length; i++) {
			element = new Element("option");
			element.setText(pruebas[i]);
			ePruebas.addContent(element);
			
			element = new Element("desc");
			element.setText(descripciones[i]);
			eDescripciones.addContent(element);
		}
    	root.addContent(ePruebas);
    	
    	root.addContent(eDescripciones);
        
    	PrintWriter out = response.getWriter();
		XMLOutputter serializer = new XMLOutputter();
		serializer.output(documento, out);
		out.flush();	
		out.close();
    	
        if (logs.isInfoEnabled()) logs.info("buscarCentral: termino el script");
    } //fin de buscarCentral
    
    private void probarTelefono(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (logs.isDebugEnabled()) logs.debug("probarTelefono");
        
        		//XXX Hago la prueba de los telefonos.
        String telefono = request.getParameter("tel1");
        String tipoPrueba = request.getParameter("tipoPrueba1");
        if (logs.isDebugEnabled()) logs.debug("Tipo de prueba: ["+tipoPrueba+"]");
        			//en que posicion de la pantalla se hace la prueba.
        String numeroPrueba = request.getParameter("numeroPrueba");
        String usuario = ((UserSipe)request.getSession().getAttribute("usuario")).getNick();
        SapeConfiguration config = SapeConfiguration.getInstance();
        String servicio = null;
        if(tipoPrueba.equals("categorias")) {
        	servicio = config.getServicio("categorias");
        } else if (tipoPrueba.equals("atu_c") || tipoPrueba.equals("atu_r")) {
        	servicio = config.getServicio("atu");
        } else {
        	servicio = config.getServicio("prueba");
        }
        
        
        String ipGude = null;
    	int puertoGude = -1;
    	
    	if(telefono.indexOf("clip") != -1) {// es una prueba clip!!!!!
    		
    		ipGude = config.getIpGUDE();
    		puertoGude = config.getPuertoGUDE();
    		servicio = config.getServicio("clip");
    		//Para hacer pruebas clip por medio de los cabezales SIPLEXPRO. los parametros son: cliente, tipoClip (clip, clip1, clip2), tipoPrueba, nodo y central.
    		
    		String cabeza =	request.getParameter("cabeza");
    		String central = request.getParameter("central");
    		
    		tipoPrueba += " "+cabeza+" "+central;  
    		 
    	} else {
    		TipoNodo tipoNodo = null;
	        try {
	        	Serie serie = serieDAO.buscarSerie(telefono);
	        	tipoNodo = tipoNodoDAO.getTipoNodo(serie.getCabezaId().intValue());
	        } catch (SapeDataException e) {
	        	error(e, request, response);
	        	return;
	        }
	        ipGude = tipoNodo.getIpServidor();
	    	puertoGude = Integer.parseInt(tipoNodo.getPuertoServidor());	        
    	}

    	if (logs.isDebugEnabled()) logs.debug("El servidor es: " + ipGude + ":" + puertoGude);

    	response.setBufferSize(256);
    	response.resetBuffer();
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	
    	out.println("<html><body bgcolor=\"black\" text=\"white\" link=\"WHITE\"><pre>\n");
    	
    	Socket socketGude = null;
    	try {
    		logs.debug("ANTES DE INICIAR EL SOCKET");
			socketGude = new Socket(ipGude, puertoGude);
			logs.debug("TERMINO DE INICIAR EL SOCKET SOCKET");
		} catch (UnknownHostException e) {
			out.println("<h1><center>UnknownHostException: " + e + "</center></h1>");
			out.println("</body></html>");
			return;
		} catch (IOException e) {
			out.println("<h1><center>No hay conexion con el servidor!!!!!</center></h1>");
			out.println("</body></html>");
			return;
		}
		
        out.println("Conectado al ServicioGude via " + ipGude + ":" + puertoGude);
		if (logs.isInfoEnabled()) logs.info("Conectado al ServicioGude via " + ipGude + ":" + puertoGude);
        BufferedReader bf = new BufferedReader(new InputStreamReader(socketGude.getInputStream()));
        DataOutputStream outSocket = new DataOutputStream(socketGude.getOutputStream());
        		//Habilito el socket para que me muestre todo.
        
        String flujoInicial=bf.readLine();
        if(flujoInicial != null){
        	if(flujoInicial.startsWith("Este IP")){
    			out.println("<h1><center>"+flujoInicial+"</center></h1>");
    			out.println("</body></html>");
    			out.close();
    			bf.close();
    			socketGude.close();
    			return;
        	}
        }
//        outSocket.writeBytes("set log=on\r");
        outSocket.writeBytes("G " + servicio + " "+ usuario+" " + telefono + " " + tipoPrueba + "\r");
        String rta="";
        String lineaAnterior = "";
        if (logs.isInfoEnabled()) logs.info("COMIENZA LA PRUEBA: " + telefono);

        String ticketPrueba=null;
        String evento = null;
        while((rta=bf.readLine())!= null){
            if(rta.equals("")){
                continue;
            }
            int o = -1; ///TODO eliminar esta variable.
            if ((o = rta.indexOf("NumeroEvento-")) != -1) {
                evento = rta.substring(o+13,rta.length());
                if (logs.isInfoEnabled()) logs.info("Ticket en Gude: " + evento);
                continue;
            }
            if(rta.indexOf("ENVIAR_COMANDO")!=-1){
                out.println("<font color=\"blue\">"+rta+"</font>");
            } else if(rta.indexOf("TicketPrueba=[")!=-1){
                ticketPrueba=rta.substring(14,rta.length()-1);
                out.println("<font color=\"red\"><b>"+rta+"</b></font>");
                if (logs.isInfoEnabled()) logs.info("Ticket de Prueba: " + ticketPrueba);
            } else if(rta.indexOf("[WARNING]")!=-1){
            	out.println("<font color=\"yellow\">"+rta+"</font>");
            }else {
                out.println(rta);
            }
            response.flushBuffer();
            if(rta.indexOf("%FIN") != -1){
                	break;
            }
            lineaAnterior = rta;	//para ir conservando la linea Anterior
        } //fin del ciclo.

        if(!rta.equals("%FIN")){
           logs.warn("La prueba no termino bien: " + rta);
        }
        out.println("</pre>");
        
        if (logs.isInfoEnabled()) logs.info("La respuesta de la prueba es: " + lineaAnterior);
        
        outSocket.writeBytes("~\r"); //Con este se cierra la comunicacion.
        outSocket.flush();
        socketGude.close();
        /* TODO :
        14 de abril de 2005:
        * se implementa la clase PruebaBasica con interfaz y clase DAO.
        * la idea es que se capture un # de evento, que es el id de
        * la tabla prueba_basica y atraves de este id obtengo la demas
        * informacion que tambien esta en esa tabla y asi mostrar
        * la informacion completa en DetallePruebaBasica.jsp.
        * Aun no se esta enviado el # de evento para obtener la info.
        
        en eventossape esta la informacion del cliente al ke se le hizo
        la prueba. prueba_basica y eventossape se relacionan con
        el campo idprueba_basica e ideventossape.
        
        tenemos:
        event : evento del proceso en gude.
        event2 : el id para obtener la info desde al tabla
        prueba_basica.
        */
      
        out.println("</body></html>");
        CodigoVer cv = null;
        
        String codiv="";
        try {
			if (tipoPrueba.indexOf("atu") != -1) {
				PruebaAtu pv = pruebaAtuDAO.getPruebaAtu(Integer.parseInt(ticketPrueba));
				codiv = pv.getCodv();
			} else if (tipoPrueba.indexOf("stat_") != -1) {
				PruebaEstadisticasConexion pv = pruebaEstadisticaDAO.getPruebaEstadistica(Integer.parseInt(ticketPrueba));
				codiv = pv.getCodv();
			} else if (tipoPrueba.indexOf("login") != -1) {
				PruebaPing pv = pruebaPingDAO.getPruebaPing(Integer.parseInt(ticketPrueba));
				codiv = pv.getCodv();
			} if (tipoPrueba.equals("categorias")){
				Categorias cat=categoriasDAO.getCategorias(ticketPrueba);
				codiv=cat.getCodigoVer();
			} else {
	        	PruebaBasica pv = pruebaBasicaDAO.getPruebaBasica(Long.parseLong(ticketPrueba));
				codiv = pv.getCodigoVer();
			}
			cv = getCodigoVer(codiv);
        } catch (SapeDataException e) {
        	e.printStackTrace();
        	cv = new CodigoVer();
        	cv.setCodigoVer(codiv);
    		cv.setClasificacion("Error no capturado. Por favor informar a Soporte (" + e.toString() + ").");
    		
    	}
       
        out.println("<script>window.parent.pruebaTerminada(" + numeroPrueba + ", " + ticketPrueba + ",'"+cv.getCodigoVer()+"','"+cv.getClasificacion()+"');</script>");
        if (logs.isDebugEnabled()) logs.debug("Termino la prueba.");
        out.flush();
        out.close();
    } //Fin de probarTelefonos
    
    //John David Gutierrez
    //20-sep-2006
    //TODO probarClipFTX
    private void probarClipFTX(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	if (logs.isDebugEnabled()) logs.debug("probarClipFTX");
        
    	//Central en la que se encuentra el fast
    	String central =  request.getParameter("central");
    	//Numero del fast aprobar
    	//String fast = request.getParameter("fast");
    	//Nombre del indigo con el que se prueba
    	String indigo = request.getParameter("indigo");
    	//Numero telefonico del fast
    	String telefono = request.getParameter("tel");
    	//Tipo de prueba a realizar
    	String tipoPrueba = request.getParameter("tipoPrueba");
    	//idtiponodo del indigo
    	String id = request.getParameter("id");
    	//Ip del servicio gude
    	String ipGude = request.getParameter("ip");
    	//Puerto del servicio gude
    	String strPuertoGude = request.getParameter("puerto");
    	//Ip del equipo de prueba
    	String ipCabeza = request.getParameter("ipCabeza");
    	//Puerto del equipo de prueba
    	String strPuertoCabeza = request.getParameter("puertoCabeza");
    	int puertoGude = 0;
    	int puertoCabeza = 0;
    	
    	
    	if (logs.isDebugEnabled()) logs.debug("Tipo de prueba: ["+tipoPrueba+"]");
		
    	String usuario = ((UserSipe)request.getSession().getAttribute("usuario")).getNick();
    	SapeConfiguration config = SapeConfiguration.getInstance();
    	
    	String servicio = config.getServicio("clipFTX");
    	
    	//Parece que hay que separar entre tipo de prueba clip
    	//y monitoreo

    	if (logs.isDebugEnabled()) logs.debug("El servidor es: " + ipGude + ":" + puertoGude);

    	response.setBufferSize(256);
    	response.resetBuffer();
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();

    	out.println("<html><body bgcolor=\"black\" text=\"white\" link=\"WHITE\"><pre>\n");

    	Socket socketGude = null;
    	try {
    		if(ipGude != null && ipGude.equals("0.0.0.0")){
    			out.println("<h1><center>IP Servidor Invalida " + ipGude + "!!!!!</center></h1>");
        		out.println("</body></html>");
    			return;
    		}
    		
    		if(strPuertoGude != null && strPuertoGude.equals("0")){
    			out.println("<h1><center>Puerto Servidor Invalido " + strPuertoGude + "!!!!!</center></h1>");
        		out.println("</body></html>");
    			return;
    		}
    		
    		if(ipCabeza != null && ipCabeza.equals("0.0.0.0")){
    			out.println("<h1><center>IP Cabeza Invalida " + ipGude + "!!!!!</center></h1>");
        		out.println("</body></html>");
    			return;
    		}
    		
    		if(strPuertoCabeza != null && strPuertoCabeza.equals("0")){
    			out.println("<h1><center>Puerto Cabeza Invalido " + strPuertoGude + "!!!!!</center></h1>");
        		out.println("</body></html>");
    			return;
    		}
    		
    		puertoGude = Integer.parseInt(strPuertoGude);
    		puertoCabeza = Integer.parseInt(strPuertoCabeza);
    		
    		socketGude = new Socket(ipGude, puertoGude);
    	/*} catch (Exception e){
    		out.println("Excepcion extraña" + e);
    	}*/
    	
    	} catch (UnknownHostException e) {
    		out.println("<h1><center>UnknownHostException: " + e + "</center></h1>");
    		out.println("</body></html>");
    		return;
    	} catch (IOException e) {
    		out.println("<h1><center>No hay conexion con el servidor!!!!!</center></h1>");
    		out.println("</body></html>");
    		return;
    	}

    	out.println("Conectado al ServicioGude via " + ipGude + ":" + puertoGude);
    	if (logs.isInfoEnabled()) logs.info("Conectado al ServicioGude via " + ipGude + ":" + puertoGude);
    	BufferedReader bf = new BufferedReader(new InputStreamReader(socketGude.getInputStream()));
    	DataOutputStream outSocket = new DataOutputStream(socketGude.getOutputStream());
		//Habilito el socket para que me muestre todo.

    	String flujoInicial=bf.readLine();
    	if(flujoInicial != null){
    		if(flujoInicial.startsWith("Este IP")){
				out.println("<h1><center>"+flujoInicial+"</center></h1>");
				out.println("</body></html>");
				out.close();
				bf.close();
				socketGude.close();
				return;
    		}
    	}

    	outSocket.writeBytes("G " + servicio + " " + usuario+ " " + central + " " + telefono + " " + tipoPrueba + " " + id + " " + ipCabeza + " " + puertoCabeza + " " + indigo + "\r");
    	String rta="";
    	String lineaAnterior = "";
    	if (logs.isInfoEnabled()) logs.info("COMIENZA LA PRUEBA: " + telefono);

    	String ticketPrueba=null;
    	String evento = null;
    	while((rta=bf.readLine())!= null){
    		if(rta.equals("")){
    			continue;
    		}
		    int o = -1; ///TODO eliminar esta variable.
		    if ((o = rta.indexOf("NumeroEvento-")) != -1) {
		        evento = rta.substring(o+13,rta.length());
		        if (logs.isInfoEnabled()) logs.info("Ticket en Gude: " + evento);
		        continue;
		    }
		    if(rta.indexOf("ENVIAR_COMANDO")!=-1){
		        out.println("<font color=\"blue\">"+rta+"</font>");
		    } else if(rta.indexOf("TicketPrueba=[")!=-1){
		        ticketPrueba=rta.substring(14,rta.length()-1);
		        out.println("<font color=\"red\"><b>"+rta+"</b></font>");
		        if (logs.isInfoEnabled()) logs.info("Ticket de Prueba: " + ticketPrueba);
		    } else if(rta.indexOf("[WARNING]")!=-1){
		    	out.println("<font color=\"yellow\">"+rta+"</font>");
		    }else {
		        out.println(rta);
		    }
		    response.flushBuffer();
		    if(rta.indexOf("%FIN") != -1){
		        	break;
		    }
		    lineaAnterior = rta;	//para ir conservando la linea Anterior
    	} //fin del ciclo.

		if(!rta.equals("%FIN")){
		   logs.warn("La prueba no termino bien: " + rta);
		}
		out.println("</pre>");
	
		if (logs.isInfoEnabled()) logs.info("La respuesta de la prueba es: " + lineaAnterior);
		
		outSocket.writeBytes("~\r"); //Con este se cierra la comunicacion.
		outSocket.flush();
		socketGude.close();
	
		out.println("</body></html>");
		CodigoVer cv = null;
	
		String codiv="";
		try {
			PruebaBasica pv = pruebaBasicaDAO.getPruebaBasica(Long.parseLong(ticketPrueba));
			codiv = pv.getCodigoVer();
			
			cv = getCodigoVer(codiv);
		} catch (SapeDataException e) {
			e.printStackTrace();
			cv = new CodigoVer();
			cv.setCodigoVer(codiv);
			cv.setClasificacion("Error no capturado. Por favor informar a Soporte (" + e.toString() + ").");
			
		}
	
		out.println("<script>window.parent.pruebaTerminada( 1, " + ticketPrueba + ",'"+cv.getCodigoVer()+"','"+cv.getClasificacion()+"');</script>");
		if (logs.isDebugEnabled()) logs.debug("Termino la prueba.");
		out.flush();
		out.close();
    }

    
    /**
     * Busca todos los telefonos que ha probado el usuario el dia de hoy.
     * @param request
     * @param response
     * @throws IOException
     */
    private void historicoTelefonos(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if (logs.isDebugEnabled()) logs.debug("historicoTelefonos");	
    	
//    	UserSipe usuario = null;
    	
//    	String nickUsuario = request.getParameter("usuario");
   	
    	try {
//			usuario = usuarioDAO.getUsuario(Integer.parseInt(nickUsuario));

    			//XXX antes el hstorico era con el usuario pasado por parametro. 
    			//Ahora es con el usuario de la sesion por la validacion via LDAP
    		UserSipe usuarioSession = (UserSipe)request.getSession().getAttribute("usuario");
    		if (usuarioSession == null) {
    			logs.warn("El usuario es null. No puedo cargar el hist�rico.");
    			return;
    		}

            String fIni = getFechaHoy();
        	String fFin = getFechaHoy();
			
			List listaTelefonos = eventoSapeDAO.getEventosUsuario(usuarioSession, fIni, fFin);
//			System.out.println("lista telefonos es: " + listaTelefonos.size());
			
	    	Document documento = new Document();
	    	Element root = new Element("telefonos");
	    	documento.setRootElement(root);
	    	
	    	for (Iterator iter = listaTelefonos.iterator(); iter.hasNext();) {
				EventoSape evento = (EventoSape) iter.next();
				Element element = new Element("telefono");
		    	element.setAttribute("idPrueba", String.valueOf(evento.getId()));
				element.setText(evento.getTelefono());
				root.addContent(element);
			}
	    	
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");

	    	PrintWriter out = response.getWriter();
			XMLOutputter serializer = new XMLOutputter();
			serializer.output(documento, out);
			out.flush();
			out.close();
			
		} catch (NumberFormatException e) {
			error(e, request, response);
			return;
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
    	
    }
    
    
    private void mostrarPruebaAtu (EventoSape evento, HttpServletRequest request, HttpServletResponse response) {
		if (logs.isDebugEnabled()) logs.debug("mostrarPruebaAtu: " + evento);
		try {
			
			PruebaAtu prueba = pruebaAtuDAO.getPruebaAtu((int)evento.getId());
	
			String descCodigoVer=null;
	    	try {
	    		descCodigoVer = getCodigoVer(prueba.getCodv()).getClasificacion();
	    	} catch (SapeDataException e) {
				error(e, request, response);
				return;
	    	}
			
	    	request.setAttribute("evento",evento);
			request.setAttribute("pruebaAtu", prueba);
			request.setAttribute("descCodigoVer",descCodigoVer);
			redireccionarConPlantilla("pruebaAtu", request, response);
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
	}
    

	private void mostrarPruebaPing (EventoSape evento, HttpServletRequest request, HttpServletResponse response) {
    	if (logs.isDebugEnabled()) logs.debug("mostrarPruebaPing: " + evento);
    	try {
			
    		PruebaPing prueba = pruebaPingDAO.getPruebaPing((int)evento.getId());

    		String descCodigoVer=null;
        	try {
        		descCodigoVer = getCodigoVer(prueba.getCodv()).getClasificacion();
        	} catch (SapeDataException e) {
    			error(e, request, response);
    			return;
        	}
			
        	request.setAttribute("evento",evento);
			request.setAttribute("pruebaPing", prueba);
			request.setAttribute("descCodigoVer",descCodigoVer);
			redireccionarConPlantilla("pruebaPing", request, response);
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
    }


	private void mostrarPruebaStat (EventoSape evento, HttpServletRequest request, HttpServletResponse response) {
		if (logs.isDebugEnabled()) logs.debug("mostrarPruebaStat: " + evento);
		try {
			
			PruebaEstadisticasConexion prueba = pruebaEstadisticaDAO.getPruebaEstadistica((int)evento.getId());
	
			String descCodigoVer=null;
	    	try {
	    		descCodigoVer = getCodigoVer(prueba.getCodv()).getClasificacion();
	    	} catch (SapeDataException e) {
				error(e, request, response);
				return;
	    	}
			
	    	request.setAttribute("evento",evento);
			request.setAttribute("pruebaStat", prueba);
			request.setAttribute("descCodigoVer",descCodigoVer);
			redireccionarConPlantilla("pruebaStat", request, response);
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
	}

    
    
    
    private void mostrarResultados(HttpServletRequest request, HttpServletResponse response) 
    														throws IOException, ServletException {
		/*
		* para esta obtengo el codigo del evento, para buscarlo en la
		* base de datos y obtener el objeto PruebaBasica, este a su vez
		* se envia por el request al jsp DetallePruebaBasica para
		* que lo despliegue.
		*/

//  	esto es para ke si saca algun error coloque un boton de cerrar a la ventana
    	request.setAttribute("tipo","cerrar");  
    	
    	
    	String tmp =(String)request.getParameter("ticket");
    	
    	if (logs.isDebugEnabled()) logs.debug("mostrarResultados: el ticket es ="+tmp);
    	
    	PruebaBasica pb = null;

    	EventoSape evento = null;
    	long ticket = 0;
    	try {
    		ticket=Long.parseLong(tmp);
    		
    		evento=eventoSapeDAO.getEventoSape(ticket);
    		
    		String tipoPrueba = evento.getTipoPrueba();
    		if(tipoPrueba.indexOf("bas2") != -1 || tipoPrueba.indexOf("ext4") != -1) {
    			pb = (PruebaBasica) pruebaSiplexPRODAO.getPruebaBasica(ticket);
    		} else if (tipoPrueba.equals("categorias")) {
    			operacionCategorias (request, response);
    			return;
    		} else if (tipoPrueba.indexOf("atu") != -1) {
    			mostrarPruebaAtu(evento, request, response);
    			return;
    		} else if (tipoPrueba.indexOf("stat_") != -1) {
    			mostrarPruebaStat(evento, request, response);
    			return;
    		} else if (tipoPrueba.indexOf("login") != -1) {
    			mostrarPruebaPing(evento, request, response);
    			return;
    		} else {
    			pb=pruebaBasicaDAO.getPruebaBasica(ticket);
    		}
    		
    	}catch(NumberFormatException e) {
    		error(e,request,response);
    		return;
    	}catch(SapeDataException e) {
    		if(e.getCause() instanceof ObjectNotFoundException){
    			error("El ticket: '"+ticket+"' no se encuentra en nuestros archivos.",request,response);
    			return;
    		}
    		return;
    	}
//  	Verifico si tiene grafica.
    	String haveGraph=null;
    	try {
    		haveGraph = pruebaBasicaDAO.getGrafica(ticket);//VERIFICAMOS SI TIENE O NO GRAFICA1!!!
    	} catch (SapeDataException e) {
    		
    		if(e.getCause() instanceof ObjectNotFoundException){
    			error("El ticket: '"+ticket+"' no se encuentra en nuestros archivos.",request,response);
    			return;
    		}else{
    			error(e, request, response);
    			return;
    		}
    	} 	
    	
    	if (haveGraph == null) haveGraph = "noTieneGrafica";
    	
    	if(evento.getTipoPrueba().equals("bas2") || evento.getTipoPrueba().equals("bas2_force"))
    		request.setAttribute("pruebaSiplex",(PruebaSiplexPRO)pb);
    	
   		request.setAttribute("prueba",pb);
    	
    	request.setAttribute("haveGraph",haveGraph);
    	
    	if(logs.isDebugEnabled()) logs.debug("Tiene grafica: " + haveGraph.substring(0,12));
    	
    	String cv=pb.getCodigoVer();
    	String descCodigoVer=null;
    	
    	try {
    		descCodigoVer = getCodigoVer(cv).getClasificacion();
    	} catch (SapeDataException e2) {
			error(e2, request, response);
			return;
    	}
    	request.setAttribute("descCodigoVer",descCodigoVer);
    	request.setAttribute("evento",evento);
    	Serie serie = null;
    	String tipocentral=null;
    	try {
//  		TODO yo creo que esto se puede sacar de otro lado.
    		serie=serieDAO.buscarSerie(String.valueOf(pb.getTelefono()));
//  		s=(Serie) l.get(0);
    		if(serie == null)
    			tipocentral = "ND";
    		else
    			tipocentral=serie.getTipocentral();
    	} catch (SapeDataException e1) {
    		tipocentral="-";
    	}
    	request.setAttribute("tipocentral",tipocentral);
    
    	
    	if(!SapeConfiguration.getInstance().getClienteSistema().equals("EPM")){
    		redireccionarConPlantilla("DetallePruebaBasica", request, response);
    		return;
    	}
    
    	
    	/*
    	 * Se realizara la comparacion de las capacitancias para obtener la menor de todas
    	 */
    	
    	if(tipocentral.equals("SIPLEXPRO")){
    		String res1=pb.getRes_tiptoground();
    		String res2=pb.getRes_ringtoground();
    		String res3=pb.getRes_tiptoring();
    		
    		StringTokenizer ress1 = new StringTokenizer(res1," ");
    		StringTokenizer ress2 = new StringTokenizer(res2," ");
    		StringTokenizer ress3 = new StringTokenizer(res3," ");
    		
//    		System.out.println("res1=["+res1+"],res2=["+res2+"],res3=["+res3+"]");
    		
    		//para verificar si la informacion esta completa!!!
    		if(ress1.countTokens() != 2 || ress2.countTokens() != 2 || ress3.countTokens() != 2){
    			System.out.println("Uno de los tokens esta imcompleto");
    			redireccionarConPlantilla("DetallePruebaBasica", request, response);
    			return;
    		}
   		    		
    		try{
	    		double re1 = Double.parseDouble(ress1.nextToken());
	    		String tipo1 = ress1.nextToken();
	    		
	    		double re2 = Double.parseDouble(ress2.nextToken());
	    		String tipo2 = ress2.nextToken();
	    		
	    		double re3 = Double.parseDouble(ress3.nextToken());
	    		String tipo3 = ress3.nextToken();
	    		
	    		//para que todos los datos keden en KOhms
	    		
	    		System.out.println("tipo1: "+tipo1+" tipo2: "+tipo2+" tipo3: "+tipo3);
	    		
	    		if (tipo1.indexOf("MOhm") != -1)
	    			re1 = re1*1000;
	    		else if (tipo1.indexOf("KOhm") == -1)// esto pasa el valor de la resistencia
	    			re1 = re1/1000;					/// de Ohm a KOhm
	    		
	    		if(tipo2.indexOf("MOhm") != -1)
	    			re2 = re2*1000;
	    		else if (tipo2.indexOf("KOhm") == -1)// esto pasa el valor de la resistencia
	    			re2 = re2/1000;					/// de Ohm a KOhm
	    		
	    		if(tipo3.indexOf("MOhm") != -1)
	    			re3 = re3*1000;
	    		else if (tipo3.indexOf("KOhm") == -1)// esto pasa el valor de la resistencia
	    			re3 = re3/1000;					/// de Ohm a KOhm
	    		
	    		//TODO testear como esta comprobando las resistencias!!!!
	    		System.out.println("numeros: res1="+re1+" res2="+re2+" res3="+re3);
	    		
	    		if((re1 == 30000 && re2 == 30000) || (re1 == 300000 && re3 == 30000) || (re2 == 300000 && re3 == 30000))
	    			System.out.println("par bueno!");
	    		else{
	    		
		    		String menor = null;
		    		//en r1 voi a guardar el dato del mayor
	    		
		    		if(re1 < re2){
		    			menor = "1";
		    		} else {
		    			menor = "2";
		    			re1 = re2;
		    		}
	    		
	    			if(re3 < re1)
	    				menor = "3";
	    		
	    			
	    			if(re1 == re2 && re2== re3)
	    				menor = "todos";
	    		
	    			System.out.println("la menor es la : "+menor);
	    		
	    			request.setAttribute("resistenciaMenorSIPLEXPRO",menor);
	    		}
    		}catch(NumberFormatException e){
    			if(logs.isDebugEnabled())logs.debug("Uno de los parametros no era numerico.");
    		}
    		
    	}

    	redireccionarConPlantilla("DetallePruebaBasica", request, response);
//    	request.getRequestDispatcher(acciones.getTemplate("DetallePruebaBasica")).forward(request,response);        
    } //fin de mostrarResultados

    
    private void operacionCategorias(HttpServletRequest request, HttpServletResponse response) 
																throws IOException, ServletException {

    	String ticket= request.getParameter("ticket");
    	
    	Categorias c = null;
    	try {
    		c=categoriasDAO.getCategorias(ticket);
    	} catch (SapeDataException e) {
    		error(e,request,response);
    		return;
    	}
    	
    	if (c == null) {
    		error("Ocurrio un error al consultar Categoria con id "+ticket+" \nValor : null",request,response);
    		return;
    	}
    	
    	String cats = c.getCategorias();
    	StringTokenizer st = new StringTokenizer(cats,"**");
    	
    	List l = new ArrayList();
    	
    	int i= 0;
    	while (st.hasMoreTokens()) {
    		l.add(i,st.nextToken());
    		i++;
    	}
    	c.setCategoriasList(l);
    	
    	request.setAttribute("categoria",c);
    	request.getRequestDispatcher(acciones.getTemplate("categorias")).forward(request,response);
    }

    
    private List getPruebasSiplexPRO() {
    	if (debug) logs.debug("getPruebasSiplexPRO");
    	if (pruebasSiplexpro == null)
    		pruebasSiplexpro = tipoPruebaDAO.getTiposPrueba();
    	
    	
    	/*
    	String pruebas[] = new String [] {"bas2","bas2_force","basica", "completa", "xdsl", "ecometro", "psd", "shdsl",
				"frec", "balance", "bobina","circuit","basica_force", "aislamiento", "aislamientoAB", "aislamientoAT", 
				"aislamientoBT", "atu_c", "atu_r", "bits_c", "bits_r", "stat_c", "stat_r", "login"};
    	
    	return pruebas; */
    	return pruebasSiplexpro;
    }
    
    
    /**
     * Este metodo es comun para cargar el codigo ver 
     * en las diferentes pantallas de resultados. 
     * Si da la SapeDataException es porque es un error no determinado. 
     * @return
     */
    private CodigoVer getCodigoVer(String codigo) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("getCodigoVer: " + codigo);
    	CodigoVer retorno = null;
    	try {
    		retorno = codigosVerDAO.getCodigoVer(codigo);
    	} catch (SapeDataException e2) {
    			//Si el error es diferente a que no encuentra e codigover, disparo la exception.
    		if (e2.getCause() instanceof net.sf.hibernate.ObjectNotFoundException) {
    			retorno =  new CodigoVer();
    			retorno.setCodigoVer(codigo);
    			retorno.setClasificacion("El codigo: \""+ codigo +"\" no esta registrado en codigosVer.");
    		} else {
    			logs.error(e2);
    			throw e2;
    		}
    	}
    	return retorno;
    }
    
}
