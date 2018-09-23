package com.osp.sape.servlets.reportes;

/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Jul 14, 2006 - 8:30:17 AM
 */

//<<<<<<< ReporteadorServlet.java
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import com.gc.acceso.GestorServlet;
import com.osp.sape.Exceptions.SapeAppException;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.ReporteadorDAO;
import com.osp.sape.reportes.CampoReporteador;
import com.osp.sape.reportes.ReporteadorPlantilla;
import com.osp.sape.utils.CustomDataSource;
import com.osp.sape.utils.GenPlantillaJasper;
import com.osp.sape.utils.ServicioGUDE;


public class ReporteadorServlet extends GestorServlet {
	
	private ReporteadorDAO reporteadorDAO;
	
    public void init() throws ServletException {
        super.init();
        DAOFactory daoFactory = DAOFactoryImpl.getInstance(); 
        reporteadorDAO = daoFactory.getReporteadorDAO();
        dfFecha = new DecimalFormat("00");
    }


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        // en la variable operacion se obtiene la solicitud a tramitar por este servlet        
        String operacion = request.getParameter("operacion");
        if (debug) logs.debug("Peticion realizada, operacion=["+operacion+"]");
        
        
        String plantilla=(String) request.getSession().getAttribute("plantillaForward");
        String varnames = (String) request.getSession().getAttribute("varsNames");
        if (debug) logs.debug("obtuvo: plantilla="+plantilla+",varnames="+varnames);
        
        
        // metodo para cambiar la url cuando se realiza una actualizacion en el sistema y no se kiere ke el
        // reload de la pagina afecte o genere transacciones iguales. Ver ejemplo inicioArmarios(RutinasServlet).
        if(plantilla!=null&&!plantilla.equals("")&&varnames!=null&&!varnames.equals("")) {
        	direccionarCondicional(true, request, response, plantilla, null);
        	return;
        }
        
        if(operacion==null||operacion.equals("") || operacion.equals("inicio")){
        	inicioReporteador(request, response);
        	return;
        }else if(operacion.equals("cargarReporteador")){
        	operacionCargarReporteador(request,response);
        	return;
        }else if(operacion.equals("guardarPantilla")){
        	guardarPantilla(request,response);
        	return;
        }else if(operacion.equals("eliminarReporteador")){
        	operacionEliminarReporteador(request,response);
        	return;
        }else if(operacion.equals("inicioRutinaReportes")){
        	// La idea es que este metodo sea el encargado de generar de manera rutinaria los reportes.
        	operacionInicioRutinaReportes(request,response);
        }
		
		//request.getRequestDispatcher("/reporteador.jsp").forward(request, response);
	}
	
	
	private void operacionInicioRutinaReportes(HttpServletRequest request, HttpServletResponse response)throws IOException {
		if (debug) logs.debug("operacionInicioRutinaReportes");
		
		String hora = request.getParameter("hora");
		String min = request.getParameter("min");
		String fecha = getFechaHoy();
		
		List l=null;
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		if(hora == null || hora.equals("")||min==null||min.equals("")){
			logs.error("los parametros enviados para el reporteador son invalidos");
			out.println("[ERROR] Parametros invalidos para el reporteador.");
			return;
		}
		
		
		//1. En la lista l obtengo otras 2 listas:
		// pos 0 = lista de las clases ReporteadorPLantilla.
		// pos 1 = los customdatasource para cada una de las plantillas de la lista de la pos 0.
		try {
			l=reporteadorDAO.getReportes(fecha+" "+hora+":"+min+":00");
		} catch (SapeDataException e) {
			out.println("[ERROR] mensaje: "+e.getLocalizedMessage());
			//error(e,request,response);
			return;
		}
		
		String path = getServletContext().getRealPath("/");
		String reporteBase = acciones.getTemplate("reporteDefault");
		File fileReporteBase = new File(path+reporteBase);
		
		
		//2. Elaboro un ciclo para exportar cada una de las plantillas.
		
		String rta = null;
		
		if(l==null || l.size()==0){
			rta = "[ERROR]: no se devolvieron registros.";
			out.println(rta);
			return;
		}
		
		List listaReporteadores = (List) l.get(0);
		List listaDataSources = (List) l.get(1);//TODO: validar ke las listas no sean vacias.
		int size = listaReporteadores.size();
		
		for(int i = 0; i < size; i++) {
			ReporteadorPlantilla r = (ReporteadorPlantilla) listaReporteadores.get(i);

				//Genero la plantilla
        	String campos[] = r.getCampos().split(",");
        	String nameFile = "Reporte_"+r.getId();
			if (debug) logs.debug("Plantilla: "+nameFile+" id="+r.getId());
        	try {
        		new GenPlantillaJasper(nameFile,campos,fileReporteBase);
        	} catch (SapeAppException e) {
        		rta = "[ERROR] Generando la plantilla '" +  r.getTitulo() + "': " + e;
        		continue;
        	}
			
			File fTemp = new File(fileReporteBase.getParent(),nameFile+".jrxml");
				//La validacion Sobra porque GenplantillaJasper lo esta Generando.
//			if(!fTemp.exists()){
//				logs.debug("[ERROR] No se encontro la plantilla del reporte "+r.getTitulo());
//				rta="[ERROR] No se encontro la plantilla del reporte "+r.getTitulo()+". No se exporta";
//				continue;
//			}
			
			JasperReport jr;
			Map parameters = new HashMap(); // para enviarle parametros (labels) al reporte!!!!			
			String classpath = (String)request.getSession().getServletContext().getAttribute("org.apache.catalina.jsp_classpath");
            System.setProperty("jasper.reports.compile.class.path", classpath);
            
            FileInputStream fis = new FileInputStream(fTemp);
            
            try {
				
            	jr = JasperCompileManager.compileReport(fis);

				parameters.put("tituloReporte", r.getTitulo());
				parameters.put(JRParameter.IS_IGNORE_PAGINATION,Boolean.TRUE);
				String nameLabels[] = reporteadorDAO.generarNombres(r,"-param");
				String labels[]=reporteadorDAO.generarLabels(r);
				
				for(int j=0;j<nameLabels.length;j++){
					parameters.put(nameLabels[j], labels[j]);
				}
			
				JasperPrint jp = null;

				jp=JasperFillManager.fillReport(jr,parameters, (CustomDataSource)listaDataSources.get(i));
			
				
				String path_archivo=path+(path.endsWith("/")?"reportes/tmp/":"/reportes/tmp/");
				
	        	if(r.getFormato().equals("pdf")){
	        		nameFile +=".pdf";
	        	    JasperExportManager.exportReportToPdfFile(jp,path_archivo+nameFile);
	        	    if (debug) logs.debug("Archivo PDF Generado.");
	        	}else if(r.getFormato().equals("xls")) {
	        		nameFile += ".xls";
	        	    JRXlsExporter exporter = new JRXlsExporter();
	        	    logs.debug("PARAMETROS JASPER: "+exporter.getParameters());
	        	    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
	        		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path_archivo+nameFile);
	        		//exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
	        		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	        		String sheet_names[]={"hoja","hoja1"};
	        		exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,sheet_names);
	        		exporter.exportReport();
	        		//JasperExportManager.exportReportToPdfFile(jp,"PARECE_EL_TITULO_DEL_ARCHIVO");
	        	    if (debug) logs.debug("Archivo XLS Generado.");
	        	}else if(r.getFormato().equals("csv")){
	        		nameFile +=".csv";
	        	    JRCsvExporter exporter = new JRCsvExporter();
	        		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
	        		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, path_archivo+nameFile);
	        		exporter.exportReport();
	        	    
	        	    //JasperExportManager.exportReportToPdfFile(jp,"PARECE_EL_TITULO_DEL_ARCHIVO");
	        	    if (debug) logs.debug("Archivo CVS Generado.");
        		}
	        	// trato de comprimir el archivo para ke kede mas liviano.
	        	
	        	ServicioGUDE ss = new ServicioGUDE();
	        	String newFileName = nameFile+".zip";
	        	try {
	        		// el parametro -j es para ke no almacene el path de directorios
	        		String cmd = "zip -r -j "+path_archivo+newFileName+" "+path_archivo+nameFile;
	        		if(debug)logs.debug("COMANDO ZIP: ["+cmd+"]");
					String rta2=ss.ejecutarComando(cmd);
					System.out.println("respuesta: "+rta2);
				} catch (SapeDataException e) {
					logs.error("[ERROR] tratando de comprimir "+nameFile+", tratara de enviarse sin comprimirse.");
					newFileName = nameFile;
				}
				if(debug)logs.debug("SENDING file: ["+nameFile+"]");
				reporteadorDAO.enviarMailReporte("RUTINA_REPORTEADOR",r.getValorMedio(),"Reporte: "+r.getTitulo(),"Este es un reporte automatico programado desde el SAPE.",path_archivo+newFileName);
	        	
	        	if(rta!=null)rta +="\n"+rta;
	        	

			} catch (JRException e) {
				logs.error(e);
				error(e,request,response);
				return;
			}

		}
		out.println(rta);

	}
	
	
	private void operacionEliminarReporteador(HttpServletRequest request, HttpServletResponse response)throws IOException{
		if (debug) logs.debug("operacionEliminarReporteador");
		
		String id = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		if(id == null || id.equals("")){
			error("Faltan parametros - id",request,response);
			return;
		}
		
		try{
			int id2 = Integer.parseInt(id);
			
			
			ReporteadorPlantilla r2 = reporteadorDAO.getReporteadorPlantilla(id2);
			String path = getServletContext().getRealPath("/");
			String reporteBase = acciones.getTemplate("reporteDefault");
			File file = new File(path+reporteBase);
			
			// toca borrar la plantilla pa ke no genere basurita.
			//String nameFile = r2.getTitulo().replace(' ','_')+".jrxml";
			String nameFile = "Reporte_"+r2.getId()+".jrxml";
			File fTemp = new File(file.getParent(),nameFile);
			boolean delete=fTemp.delete();
			logs.debug("BORRADO DE ARCHIVO : "+nameFile+"="+(delete?" satisfactorio ":" imposible"));
			
			
			// ahora si, hacemos el borrado de la plantilla!!!!!!!!
			
			reporteadorDAO.eliminarReporteadorPlantilla(id2);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("msg","El reporte '"+nombre+"' ha sido eliminado con exito.");
		inicioReporteador(request,response);
	}
	
	
	private void guardarPantilla(HttpServletRequest request, HttpServletResponse response)throws IOException{
		if (debug) logs.debug("operacionGuardarPantilla");
		
		String campos= request.getParameter("campos");
		String labels= request.getParameter("labels");
		String orderBy= request.getParameter("orderBy");
		String buscar= request.getParameter("buscar");
		String condicion= request.getParameter("condicion");
		String valorCondicion= request.getParameter("valorCondicion");
		String desde= request.getParameter("desde");// Fechas en string
		String hasta= request.getParameter("hasta");// Fechas en string
		String formato= request.getParameter("formato");
		String destino= request.getParameter("destino");
		String fechaMail= request.getParameter("fechaMail");
		String titulo= request.getParameter("titulo");
		
		ReporteadorPlantilla r = new ReporteadorPlantilla();
		
		r.setCampos(campos);
		r.setEtiquetas(labels);
		r.setColCondicion(buscar);
		r.setValorCondicion(valorCondicion);
		r.setDesdeFecha(desde);
		r.setHastaFecha(hasta);
		r.setFechaCambio(getFechaFormato(new Date(),"yyyy-mm-dd hh:MM:ss"));
		r.setFechaEnvio(fechaMail);
		r.setFormato(formato);
		r.setOrderBy(orderBy);
		r.setTipoCondicion(condicion);
		r.setTitulo(titulo);
		r.setUsuario(getUsuarioSession(request));
		r.setValorMedio(destino);

		//todavia no le coloco nada porke no tiene id
//		String nombrePlantillaNueva="Reporte_";
		// Para verificar si es una plantilla nueva o una actualizacion!!!!
		String id = request.getParameter("idPlantillaActual");
		int id2 = -1;
		if(id!= null && !id.equals("")){
			try {
				id2 = Integer.parseInt(id);
			} catch(NumberFormatException e) {
				id2 = -1;
			}
		}
		
		String path = getServletContext().getRealPath("/");
		String reporteBase = acciones.getTemplate("reporteDefault");
		File file = new File(path+reporteBase);
		
		try {
			//1. Guardar la plantilla en la base de datos.
			if(id2 != -1){// pucha, es una actualizacion chamo!!!!!
				r.setId(id2);
				// Borro la posible plantilla ke pudo haber generado.
				ReporteadorPlantilla r2 = reporteadorDAO.getReporteadorPlantilla(id2);
				
				// toca borrar la plantilla pa ke no genere basurita.
				//String nameFile = r2.getTitulo().replace(' ','_')+".jrxml";
				String nameFile = "Reporte_"+r2.getId()+".jrxml";
				File fTemp = new File(file.getParent(),nameFile);
				boolean delete=fTemp.delete();
				logs.debug("BORRADO DE ARCHIVO : "+nameFile+"="+(delete?" satisfactorio ":" imposible"));
				
				reporteadorDAO.actualizarReporteadorPlantilla(r);
			} else {
				reporteadorDAO.insertarReporteadorPlantilla(r);
//				List ll = reporteadorDAO.getAllReporteadorPlantillas();
//				ReporteadorPlantilla r3 = (ReporteadorPlantilla) ll.get(ll.size()-1); //Recupero el ultimo
//				nombrePlantillaNueva = "Reporte_"+r3.getId();
			}
			
			//2. Generar la plantilla			
//			System.out.println("file BASE: "+file.getAbsolutePath());
//			StringTokenizer stt = new StringTokenizer(campos,",");
//			String camps[] = new String[stt.countTokens()];			
//			int i=0;
//			while(stt.hasMoreTokens()){
//				camps[i]=stt.nextToken();
//				i++;
//			}
//			GenPlantillaJasper generador = new GenPlantillaJasper(nombrePlantillaNueva,camps,file);
			
			request.setAttribute("msg","Reporte '"+titulo+"' generado con exito.");
			
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
//		} catch (SapeAppException e) {
//			error(e,request,response);
//			return;
		}
		request.setAttribute("reporteador",r);
		inicioReporteador(request,response);
	}
	
	
	private void operacionCargarReporteador(HttpServletRequest request, HttpServletResponse response)throws IOException{
		if (debug) logs.debug("operacionCargarReporteador");
		
		String id2 = request.getParameter("id");
		ReporteadorPlantilla r = null;
		try{
			int id = Integer.parseInt(id2);
			r = reporteadorDAO.getReporteadorPlantilla(id);
		}catch (NumberFormatException e) {
			error(e,request,response);
			return;
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		request.setAttribute("reporteador",r);
		inicioReporteador(request,response);
	}
	
	private void inicioReporteador(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List listaCampos = getListaCampos();
		List rs = null;
		
		try {
			rs=reporteadorDAO.getAllReporteadorPlantillas();
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("listaCampos", listaCampos);
		request.setAttribute("listaReporteadores", rs);
		
		
        // Verifico si antes se hizo una actializacion en el sistema, si hubo esta actualiazacion
        // es mejor generar un nuevo request para evitar problemas con los reload de la pagina.
        String msg = (String) request.getAttribute("msg");
        if(msg != null && !msg.equals("")){// ocurrio una actualizacion en el sistema!!!
        	
        	// SI hubo una actualizacion utilizo este metodo, recibe como parametros
        	// un boolean ke en este caso debe ser false, request,response, la plantilla a la ke se le hara
        	// forward despues y la url a la ke se hara sendRedirect para inicializar un nuevo request.
            direccionarCondicional(false,request, response, "reporteador", "actionSape?accion=reporteador&operacion=");            
            return;
        }
		
		redireccionarConPlantilla("reporteador", request, response);

	}
	
	private List getListaCampos() {

		if (debug) logs.debug("getListaCampos");
		
		List listaCampos = new ArrayList();

//		listaCampos.add(new CampoReporteador("prueba", "Prueba", true));
//		listaCampos.add(new CampoReporteador("usuario", "Usuario", true));
//		listaCampos.add(new CampoReporteador("telefono", "Telefono", true));
//		listaCampos.add(new CampoReporteador("tipoprueba", "Tipoprueba", true));
//		listaCampos.add(new CampoReporteador("fecha", "Fecha Prueba", true));
//		listaCampos.add(new CampoReporteador("site", "Tipo Nodo", true));
//		listaCampos.add(new CampoReporteador("duracion", "Duracion", true));
//		listaCampos.add(new CampoReporteador("central", "Central", true));
//		listaCampos.add(new CampoReporteador("codv" , "Codigo Ver", true));
//		listaCampos.add(new CampoReporteador("estado" , "Estado", true));
//		listaCampos.add(new CampoReporteador("voltajeAC_AB", "Voltaje AC AB", false));
//		listaCampos.add(new CampoReporteador("voltajeAC_AT" , "Voltaje AC AT", false));
//		listaCampos.add(new CampoReporteador("voltajeAC_BT", "Voltaje AC BT", false));
//		listaCampos.add(new CampoReporteador("voltajeDC_AB" , "Voltaje DC AB", false));
//		listaCampos.add(new CampoReporteador("voltajeDC_AT", "Voltaje DC AT", false));
//		listaCampos.add(new CampoReporteador("voltajeDC_AB", "Voltaje DC AB", false));
//		listaCampos.add(new CampoReporteador("resAB", "Resistencia AB", false));
//		listaCampos.add(new CampoReporteador("resAT", "Resistencia AT", false));
//		listaCampos.add(new CampoReporteador("resBT", "Resistencia BT", false));
//		listaCampos.add(new CampoReporteador("capAB", "Capacitancia AB", false));
//		listaCampos.add(new CampoReporteador("capAT", "Capacitancia AT", false));
//		listaCampos.add(new CampoReporteador("capBT", "Capacitancia BT", false));
//		listaCampos.add(new CampoReporteador("calificacionPar", "Calificacion Par", true));
//		listaCampos.add(new CampoReporteador("distancia", "Distancia", true));
//		listaCampos.add(new CampoReporteador("velUp" , "Velocidad Up", false));
//		listaCampos.add(new CampoReporteador("velDown" , "Velocidad Down", false));
//		listaCampos.add(new CampoReporteador("calificacionDatos", "Calificacion Datos", true));
//		listaCampos.add(new CampoReporteador("numeroRutina", "Rutina", true));
//		listaCampos.add(new CampoReporteador("tipoRutina", "Tipo Rutina", true));
//		listaCampos.add(new CampoReporteador("usuarioRutina", "Usuario Rutina", true));
//		listaCampos.add(new CampoReporteador("fechaRutina", "Fecha Rutina", true));
//		listaCampos.add(new CampoReporteador("numPruebaProgramada", "Prueba Prog", true));
//		
		listaCampos.add(new CampoReporteador("ideventossape", "Prueba", true));
		listaCampos.add(new CampoReporteador("cliente", "Usuario", true));
		listaCampos.add(new CampoReporteador("telefono", "Telefono", true));
		listaCampos.add(new CampoReporteador("tipoprueba", "Tipoprueba", true));
		listaCampos.add(new CampoReporteador("fecha_inicial", "Fecha Prueba", true));
		listaCampos.add(new CampoReporteador("site", "Tipo Nodo", true));
		listaCampos.add(new CampoReporteador("duracion", "Duracion", true));
		listaCampos.add(new CampoReporteador("central", "Central", true));
		listaCampos.add(new CampoReporteador("estado" , "Estado", true));
		listaCampos.add(new CampoReporteador("codv" , "Codigo Ver", true));
		
		listaCampos.add(new CampoReporteador("acv_tiptoring", "Voltaje AC AB", false));
		listaCampos.add(new CampoReporteador("acv_tiptoground" , "Voltaje AC AT", false));
		listaCampos.add(new CampoReporteador("acv_ringtoground", "Voltaje AC BT", false));
		listaCampos.add(new CampoReporteador("dcv_tiptoring" , "Voltaje DC AB", false));
		listaCampos.add(new CampoReporteador("dcv_tiptoground", "Voltaje DC AT", false));
		listaCampos.add(new CampoReporteador("dcv_ringtoground", "Voltaje DC BT", false));
		listaCampos.add(new CampoReporteador("res_tiptoring", "Resistencia AB", false));
		listaCampos.add(new CampoReporteador("res_tiptoground", "Resistencia AT", false));
		listaCampos.add(new CampoReporteador("res_ringtoground", "Resistencia BT", false));
		listaCampos.add(new CampoReporteador("rea_tiptoring", "Capacitancia AB", false));
		listaCampos.add(new CampoReporteador("rea_tiptoground", "Capacitancia AT", false));
		listaCampos.add(new CampoReporteador("rea_ringtoground", "Capacitancia BT", false));
		listaCampos.add(new CampoReporteador("distancia", "Distancia", true));
		listaCampos.add(new CampoReporteador("velocidadup" , "Velocidad Up", false));
		listaCampos.add(new CampoReporteador("velocidaddown" , "Velocidad Down", false));
		listaCampos.add(new CampoReporteador("idpruebaprogramada", "Prueba Prog", true));
		listaCampos.add(new CampoReporteador("calificacion", "Calificacion Par", true));
	
		listaCampos.add(new CampoReporteador("calificaciondatos", "Calificacion Datos", true));
		listaCampos.add(new CampoReporteador("idrutina", "Rutina", true));
		listaCampos.add(new CampoReporteador("tipodepruebaprog", "Tipo Rutina", true));
		listaCampos.add(new CampoReporteador("usuario_rutina", "Usuario Rutina", true));
		listaCampos.add(new CampoReporteador("fechaprogramada_rutina", "Fecha Rutina", true));
		
		return listaCampos;
	}
	
	
	
	//TODO: verificar ke tan eficiente es este metodo y colocarlo en GestorServlet
    private void direccionarCondicional(boolean modo,HttpServletRequest request, HttpServletResponse response,String plantilla,String newUrl)throws IOException{

    	
    	HttpSession session = request.getSession();
    	if(!modo){// Se hara el sendRedirect primero
    		
    		String varsNames = "";
    		Enumeration enu = request.getAttributeNames();
    		//1. llevo a la session todos los elementos
    		
    		while(enu.hasMoreElements()){
    			String varName = (String) enu.nextElement();
    			session.setAttribute(varName, request.getAttribute(varName));
    			varsNames +=varName+";";
    		}
    		
    		//2. guardo en la session los nombres de todas las variables.
    		
    		session.setAttribute("varsNames", varsNames);
    		session.setAttribute("plantillaForward", plantilla);
    		//3. hago el sendRedirect
    		

			response.sendRedirect(newUrl);

    	} else {// se recuperan las variables, se setean en el request y se envia el forward.
    		String names = (String) session.getAttribute("varsNames");
    		
    		StringTokenizer stt = new StringTokenizer(names,";");
    		
    		while(stt.hasMoreTokens()){
    			String varName = stt.nextToken();
    			request.setAttribute(varName,session.getAttribute(varName));
    			session.removeAttribute(varName);
    		}
    		session.removeAttribute("plantillaForward");
    		session.removeAttribute("varsNames");

    		redireccionarConPlantilla(plantilla, request, response);
    	}
    	
    }
	
	
	
}