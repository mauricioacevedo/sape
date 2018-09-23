package com.osp.sape.servlets.mantenimiento;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.ObjectNotFoundException;
import net.sf.hibernate.UnresolvableObjectException;

import com.gc.acceso.GestorServlet;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.ConfiguracionADSLDAO;
import com.osp.sape.data.ConfiguracionEWSDDAO;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.SerieDAO;
import com.osp.sape.data.SiplexproLIDAO;
import com.osp.sape.data.TipoNodoDAO;
import com.osp.sape.data.TipoPruebaDAO;
import com.osp.sape.maestros.ConfiguracionEWSD;
import com.osp.sape.maestros.Serie;
import com.osp.sape.maestros.SiplexproLI;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.maestros.TipoPrueba;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.maestros.siplexpro.CPE;
import com.osp.sape.maestros.siplexpro.ConfiguracionADSL;
import com.osp.sape.maestros.siplexpro.DSLAM;
import com.osp.sape.maestros.siplexpro.IP;
import com.osp.sape.maestros.siplexpro.PING;
import com.osp.sape.maestros.siplexpro.ParMatrizSiplexPro;
import com.osp.sape.utils.ServicioGUDE;

public class MantenimientoSiplexProServlet extends GestorServlet {
	 
	private TipoNodoDAO tipoNodoDAO;
    private SerieDAO serieDAO;
    private ConfiguracionADSLDAO configADSLDAO;
    private TipoPruebaDAO tipoPruebaDAO;
    private ConfiguracionEWSDDAO configuracionEWSDDAO;
    private SiplexproLIDAO siplexproLIDAO;

    
    public void init() throws ServletException {
		super.init();
		DAOFactory daoFactory = DAOFactoryImpl.getInstance();
		
		tipoNodoDAO = daoFactory.getTipoNodoDAO();
		serieDAO = daoFactory.getSerieDAO();
		configADSLDAO = daoFactory.getConfiguracionADSLDAO();
		tipoPruebaDAO = daoFactory.getTipoPruebaDAO();
		configuracionEWSDDAO=daoFactory.getConfiguracionEWSDDAO();
		siplexproLIDAO = daoFactory.getSiplexproLIDAO();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");	
		if (operacion.equals("guardarParMatriz")) {
			guardarPar(request, response);
			return;
		}
		
		doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		
		if(debug)logs.debug("Operacion=["+operacion+"]");
		
		if (operacion.equals("matrizSiplexPro")) {
			matrizSiplexPro(request, response);
			return;
		}
		if (operacion.equals("editarPar")) {
			editarPar(request, response);
			return;
		}
		if(operacion.equals("configADSL")){
			configADSL(request,response);
			return;
		}
		if(operacion.equals("configurarCabeza")){
			configurarCabeza(request,response);
			return;
		}
		if(operacion.equals("procesarConfigADSL")){
			procesarConfigADSL(request,response);
			return;
		}
		if(operacion.equals("modificarDescTipoPrueba")){
			modificarDescTipoPrueba(request,response);
			return;
		}
		if(operacion.equals("doModificarDescTipoPrueba")){
			doModificarDescTipoPrueba(request,response);
			return;
		}
		if(operacion.equals("mantenimientoDLU")){
			inicioDLU(request,response);
			
			return;
		}
		if(operacion.equals("buscarDLU")){
			buscarDLU(request,response);
			return;
		}
		if(operacion.equals("actualizarDLU")){
			actualizarDLU(request,response);
			return;
		}
		if(operacion.equals("eliminarDLU")){
			eliminarDLU(request,response);
			return;
		}
		if(operacion.equals("mantenimientoLI")){
			inicioLI(request,response);
			return;
		}
		if(operacion.equals("buscarLI")){
			buscarLI(request,response);
			return;
		}
		if(operacion.equals("actualizarLI")){
			actualizarLI(request,response);
			return;
		}
		if(operacion.equals("eliminarLI")){
			eliminarLI(request,response);
			return;
		} if(operacion.equals("procesarArchivo")){
			procesarArchivo(request,response);
			return;
		}
	}
	/**
	 * Metodo para procesar un archivo que halla sido montado al servidor.
	 * Configuracion del archivo para cada tipo:
	 * dlus: telefono;DLU;cabeza
	 * lis:  telefono;LI;cabeza
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void procesarArchivo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (debug) logs.debug("procesarArchivo");
		String tipo=request.getParameter("tipo");
		
    	String nombreArchivo = request.getParameter("archivo");
    	if (debug) logs.debug("Tipo: " + tipo + ", nombreArchivo: " + nombreArchivo);
    	
    	if (tipo == null || tipo.equals("")) {
    		error ("Parametro tipo Invalido: " + tipo, request, response);
    		return;
    	} else if (!tipo.equals("li") && !tipo.equals("dlu")) {
    		error ("Parametro tipo Invalido: " + tipo, request, response);
    		return;    		
    	}
    	
    	File f = new File(getServletContext().getRealPath("/")+acciones.getTemplate("temporalReportes")+nombreArchivo);
    	
    	if(!f.exists()){	
    		error("Archivo "+nombreArchivo+" No existe. Proceso cancelado",request,response);
    		return;
    	}
    	
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
//	    request.setAttribute("tipo","cerrar");
    	
    	out.println("<b>INICIO</b>");
    	out.println("<br>Procesando archivo de "+tipo.toUpperCase()+"'s.");
    	out.println("<pre>");
    	
		RandomAccessFile raf = new RandomAccessFile(f,"rw");
		int totalInsertados = 0;
		long tel = -1;
		int cabeza = -1;
		long dlu = -1;
		while(true){
			tel = -1;
			cabeza=-1;
			dlu = -1;
			String line = raf.readLine();
			if(line == null) break;
			StringTokenizer stt = new StringTokenizer(line,";");

				//1. Se supone ke la linea debe tener 3 tokens, si no es asi no la tomo.
			
			int canttoks= stt.countTokens();
			if(canttoks < 3){
				out.println("[ERROR] Linea debe tener 3 parametros: ["+line+"]");
				continue;
			}
			
				// 2. Obtengo el telefono, dlu o li segun sea el caso y el id de la cabeza.
			String telefono = stt.nextToken();// telefono
			String elemento = stt.nextToken(); // dlu o li
			String idCabeza = stt.nextToken();
			
			logs.debug("tokens: "+canttoks);
			String  bus="";
			if(canttoks == 4){
				// es una mta!
				bus=stt.nextToken();
				//continue;
			}
			
			
			if(telefono.length() != ((Integer)getServletContext().getAttribute("cantDigitosEntorno")).intValue()){
				out.println("[ERROR] Linea Ignorada, TELEFONO NO VALIDO: ["+line+"]");
				continue;
			}
			
			//3. Valido ke todos los elementos sean de tipo numerico
			//	 Por ahora no se validara el elemento(dlu o li).
			Serie ss = null;
			TipoNodo t = null;
			try {
				cabeza = Integer.parseInt(idCabeza);
				try{
					ss=serieDAO.buscarSerie(telefono);
					
					if(ss==null){
						// NO encontro una serie relacionada con este telefono
						out.println("[ERROR] Linea Ignorada, Telefono no tiene serie: ["+telefono+"]");
						continue;
					}
					
				}catch(SapeDataException e22){
					if(e22.getCause() instanceof ObjectNotFoundException){
						// NO encontro una serie relacionada con este telefono
						out.println("[ERROR] Linea Ignorada, Telefono no tiene serie: ["+line+"]");
						continue;
					}
				}

				t = tipoNodoDAO.getTipoNodo(cabeza);

								
				tel = Long.parseLong(telefono);
							
					//4. verifico en la base de datos si ya existe el dlu o li
					//	 si existe lo actualizo, sino lo inserto!!!!!
					//   tener en cuenta ke si el registro no existe va a lanzar una excepcion
						
				if (tipo.equals("dlu")){
					dlu = Integer.parseInt(elemento);
					ConfiguracionEWSD c=tipoNodoDAO.getDLU(tel);
						// si pasamos por aca el dlu ya existe. lo actualizo.
					c.setCentral(ss.getCentral());
					c.setTipoNodo(t);
					c.setDlu(dlu);
					
					if(canttoks==4){// se trata de una mta, incluye un bus de prueba
						logs.debug("encontro mta:  "+bus);
						c.setBus_mta(new Long(Integer.parseInt(bus)));
					}
					
					configuracionEWSDDAO.actualizarDLU(c);
					totalInsertados++;
					out.println("[INFO] telefono "+telefono+" actualizado. Central="+ss.getCentral()+", dlu="+elemento+", cabeza="+t.getSite());
					continue;
				} else if(tipo.equals("li")) {
					SiplexproLI s = tipoNodoDAO.getLI(tel);
						// si pasamos por aca el li ya existe. lo actualizo.
					s.setLi(elemento.toUpperCase());
					s.setCentral(ss.getCentral());
					s.setTipoNodo(t);
					siplexproLIDAO.actualizarLI(s);
					totalInsertados++;
					out.println("[INFO] telefono "+telefono+" actualizado. Central="+ss.getCentral()+", LI="+elemento+", cabeza="+t.getSite());
					continue;
				}
			}catch(NumberFormatException e){
				out.println("[ERROR] Linea Ignorada, Verifique los valores numericos: ["+line+"]");
				continue;
			} catch (SapeDataException e) {
					// debo verificar por que fue el error.
				if(e.getCause() instanceof ObjectNotFoundException){
					//no encontro un dlu para el telefono, toca insertarlo!
					
				}else if(e.getCause() instanceof UnresolvableObjectException){
					logs.error("Uno de los datos del registro en la bd con id="+idCabeza+" es invalido.");
					out.println("[ERROR] Datos incoherentes en la base de datos para tipo de nodo: "+idCabeza+", se ignora linea["+line+"]");
					continue;
				}else if(!(e.getCause() instanceof ObjectNotFoundException)){
					error(e,request,response);
					return;
				}
				
				//exception por no encontrar registro.
				//dejo seguir el programa y abajo creo los nuevos objetos.
			} 
			try{
					//5. Si paso por aca es que el elemento(dlu o li) no existe.

				if(tipo.equals("dlu")){
					ConfiguracionEWSD c=new ConfiguracionEWSD(tel,ss.getCentral(),dlu,t);
					if(canttoks==4){// se trata de una mta, incluye un bus de prueba
						c.setBus_mta(new Long(Integer.parseInt(bus)));
					}
					configuracionEWSDDAO.insertarDLU(c);
					totalInsertados++;
					out.println("[INFO] telefono "+telefono+" insertado. Central="+ss.getCentral()+", dlu="+elemento+", cabeza="+t.getSite());
					continue;
				}else if(tipo.equals("li")){
					SiplexproLI s=new SiplexproLI();
					s.setCentral(ss.getCentral());
					s.setLi(elemento);
					s.setTipoNodo(t);
					s.setTelefono(tel);
					siplexproLIDAO.insertarLI(s);
					totalInsertados++;
					out.println("[INFO] telefono "+telefono+" insertado. Central="+ss.getCentral()+", li="+elemento+", cabeza="+t.getSite());
					continue;
				}
			}catch(SapeDataException e){
				error(e,request,response);
				return;
			}
		}
		
        out.println("</pre>");
        
        out.println("<br>");
        out.println("<h3>Cantidad de registros insertados: " + totalInsertados + "</h3>");
        out.println("<br>");
        
        out.println("<p align=\"center\">");
        out.println("<a name=\"fin\" href=\"" + request.getContextPath() + "/actionSape?accion=mantenimientoSiplexPro&operacion=mantenimiento" + tipo.toUpperCase() + "\">CONTINUAR</a>");
        out.println("</p>");
        out.close();
	}
	
	
	private void inicioDLU(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("destino","actionSape?accion=mantenimientoSiplexPro&operacion=procesarArchivo&tipo=dlu");
		redireccionarConPlantilla("mantenimientoDLUs",request,response);
	}
	
	
	private void inicioLI(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("destino","actionSape?accion=mantenimientoSiplexPro&operacion=procesarArchivo&tipo=li");
		redireccionarConPlantilla("mantenimientoLIs",request,response);
	}
	
	
	
	private void eliminarLI(HttpServletRequest request, HttpServletResponse response) {
		String telefono = request.getParameter("telefono");
		
		try {
			int tel = Integer.parseInt(telefono);
			
			siplexproLIDAO.eliminarLI(new Long(tel));
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		mensaje("Se elimino el LI perteneciente al telefono "+telefono+" satisfactoriamente",null,request,response);
	}
	
	
	private void actualizarLI(HttpServletRequest request, HttpServletResponse response) {
		SiplexproLI li = null;
    	
    	String telefono = request.getParameter("telefono");
    	if (telefono == null) {
    		error("Debe definir el paramtero telefono", request, response);
    		return;
    	}
    	String tipoNodo = request.getParameter("cboCabeza");
    	if (tipoNodo == null) {
    		error("Debe definir el paramtero cboCabeza", request, response);
    		return;
    	}
    	String txtli = request.getParameter("txtli");
    	if (txtli == null) {
    		error("Debe definir el paramtero txtli", request, response);
    		return;
    	}
    	

    	String central = null;
		try {

	    	Serie serie = serieDAO.buscarSerie(telefono);
	    	
	    	if(serie == null){
	    		error("El telefono no esta definido en series",request,response);
	    		return;
	    	}
	    	central = serie.getCentral();
			try {
				li = tipoNodoDAO.getLI(Integer.parseInt(telefono));
			} catch(SapeDataException e) {
				if(e.getCause()  instanceof ObjectNotFoundException == false){
					error(e,request,response);
					return;
				}
				li = new SiplexproLI();
				li.setTelefono(Long.parseLong(telefono));
				li.setCentral(central);
			}
			
			li.setLi(txtli.toUpperCase());
			li.setTipoNodo(tipoNodoDAO.getTipoNodo(Integer.parseInt(tipoNodo)));
			
			//Metodo para actualizar un dlu en la tabla, si no lo encuentra lo inserta.
			
			siplexproLIDAO.actualizarLI(li);			
		} catch (NumberFormatException e) {
			logs.error(e);
			error(e, request, response);
			return;
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
		request.setAttribute("mensaje","LI actualizado con &eacute;xito.");
		redireccionarConPlantilla("MantenimientoMensaje",request,response);

	}
	
	
	private void buscarLI(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("buscarLI");
    	SiplexproLI li = null;
    	List listaCabezas = null;
    	
    	String telefono = request.getParameter("txtBuscarTelefono");
    	if (telefono == null) {
    		error("Debe definir el paramtero txtBuscarTelefono", request, response);
    		return;
    	}
    	
    	try {
			Serie serie = serieDAO.buscarSerie(telefono);
			if (serie == null) {
				error("El telefono no esta definido en Series.", request, response);
				return;
			}
			if (!serie.getTipocentral().equalsIgnoreCase("SIPLEXPRO-AXE")) {
				error("Imposible buscar LI. el T&eacute;lefono es del Tipo "+serie.getTipocentral()+".", request, response);
				return;				
			}
			
			try{
				li = tipoNodoDAO.getLI(Integer.parseInt(telefono));
			}catch(SapeDataException e){
				// dejo seguir el programa
				logs.error(e);
				
				if(e.getCause()  instanceof ObjectNotFoundException == false){
					error(e,request,response);
					return;
				}
				
				li = new SiplexproLI();
				li.setCentral(serie.getCentral());
				li.setTelefono(new Long(Long.parseLong(telefono)));
				li.setTipoNodo(null);
				
			}
			
			if (li != null&&!li.getCentral().equals(serie.getCentral())) {
				error("La central definida en series no corresponde a la central definida en el LI", request, response);
				return;
			}
			if(li == null)
				listaCabezas = tipoNodoDAO.getTipoNodosCentral(serie.getCentral());
			else
				listaCabezas = tipoNodoDAO.getTipoNodosCentral(li.getCentral());
		} catch (NumberFormatException e) {
			logs.error(e);
			error(e, request, response);
			return;
    	} catch (SapeDataException e) {
    		
    		if(e.getCause() instanceof net.sf.hibernate.ObjectNotFoundException){
    			System.out.println("No encontro el registro en la tabla. lo dejo seguir.");
    		}
    		
			error(e, request, response);
			return;
		}
    	request.setAttribute("li", li);
    	request.setAttribute("listaCabezas", listaCabezas);
    	request.setAttribute("destino","actionSape?accion=mantenimientoSiplexPro&operacion=procesarArchivo&tipo=li");
		redireccionarConPlantilla("mantenimientoLIs", request, response);
	}
	
	
	private void eliminarDLU(HttpServletRequest request, HttpServletResponse response) {
		String telefono = request.getParameter("telefono");
		
		try {
			int tel = Integer.parseInt(telefono);
			configuracionEWSDDAO.eliminarDLU(new Integer(tel));
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		mensaje("Se elimino el DLU perteneciente al telefono "+telefono+" satisfactoriamente",null,request,response);
	}
	
	private void actualizarDLU(HttpServletRequest request, HttpServletResponse response) {
		ConfiguracionEWSD dlu = null;
    	
    	String telefono = request.getParameter("telefono");
    	if (telefono == null) {
    		error("Debe definir el paramtero telefono", request, response);
    		return;
    	}
    	String tipoNodo = request.getParameter("cboCabeza");
    	if (tipoNodo == null) {
    		error("Debe definir el paramtero cboCabeza", request, response);
    		return;
    	}
    	String txtdlu = request.getParameter("txtdlu");
    	if (txtdlu == null) {
    		error("Debe definir el paramtero txtdlu", request, response);
    		return;
    	}
    	

    	String central = null;
		try {

	    	Serie serie = serieDAO.buscarSerie(telefono);
	    	
	    	if(serie == null){
	    		error("El telefono no esta definido en series",request,response);
	    		return;
	    	}
	    	central = serie.getCentral();
			try{
				dlu = tipoNodoDAO.getDLU(Long.parseLong(telefono));
			}catch(SapeDataException e){
				if(e.getCause()  instanceof ObjectNotFoundException == false){
					error(e,request,response);
					return;
				}
				dlu = new ConfiguracionEWSD();
				dlu.setTelefono(Long.parseLong(telefono));
				dlu.setCentral(central);
			}
			
			dlu.setDlu(Long.parseLong(txtdlu));
			dlu.setTipoNodo(tipoNodoDAO.getTipoNodo(Integer.parseInt(tipoNodo)));
			
			// Metodo para actualizar un dlu en la tabla, si no lo encuentra lo inserta.
			configuracionEWSDDAO.actualizarDLU(dlu);
			
		} catch (NumberFormatException e) {
			logs.error(e);
			error(e, request, response);
			return;
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
		request.setAttribute("mensaje","DLU actualizado con &eacute;xito.");
		redireccionarConPlantilla("MantenimientoMensaje",request,response);

	}
	
	private void buscarDLU(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("buscarDLU");
    	ConfiguracionEWSD dlu = null;
    	List listaCabezas = null;
    	
    	String telefono = request.getParameter("txtBuscarTelefono");
    	if (telefono == null) {
    		error("Debe definir el paramtero txtBuscarTelefono", request, response);
    		return;
    	}
    	
    	try {
			Serie serie = serieDAO.buscarSerie(telefono);
			if (serie == null) {
				error("El telefono no esta definido en Series.", request, response);
				return;
			}
			if (!serie.getTipocentral().equalsIgnoreCase("SIPLEXPRO-EWSD")) {
				error("Imposible buscar DLU. el T&eacute;elefono es del tipo "+serie.getTipocentral()+".", request, response);
				return;				
			}
			
			try{
				dlu = tipoNodoDAO.getDLU(Integer.parseInt(telefono));
			}catch(SapeDataException e){
				// dejo seguir el programa
				logs.error(e.getMessage());
				if(e.getCause()  instanceof ObjectNotFoundException == false){
					error(e,request,response);
					return;
				}
				
				dlu = new ConfiguracionEWSD();
				dlu.setCentral(serie.getCentral());
				dlu.setTelefono(new Long(Long.parseLong(telefono)));
				dlu.setTipoNodo(null);
				
			}
			
			if (dlu != null&&!dlu.getCentral().equals(serie.getCentral())) {
				error("La central definida en series no corresponde a la central definida en el DLU", request, response);
				return;
			}
			if(dlu == null)
				listaCabezas = tipoNodoDAO.getTipoNodosCentral(serie.getCentral());
			else
				listaCabezas = tipoNodoDAO.getTipoNodosCentral(dlu.getCentral());
		} catch (NumberFormatException e) {
			logs.error(e);
			error(e, request, response);
			return;
    	} catch (SapeDataException e) {
    		if(e.getCause() instanceof net.sf.hibernate.ObjectNotFoundException){
    			System.out.println("No encontro el registro en la tabla. lo dejo seguir.");
    		}
    		
			error(e, request, response);
			return;
		}
    	request.setAttribute("dlu", dlu);
    	request.setAttribute("listaCabezas", listaCabezas);
    	request.setAttribute("destino","actionSape?accion=mantenimientoSiplexPro&operacion=procesarArchivo&tipo=dlu");
		redireccionarConPlantilla("mantenimientoDLUs", request, response);
	}
	

	
	@SuppressWarnings("unchecked")
	private void procesarConfigADSL(HttpServletRequest request, HttpServletResponse response) {
		boolean log = logs.isDebugEnabled();
		
		String config = request.getParameter("config");	// Nombre de la configuracion a procesar
		String cabeza = request.getParameter("id"); 	//Id del tiponodo
		String proceso = request.getParameter("proceso");//proceso a aplicar a la configuracion(loadInfoCabeza,saveInfoCabeza,guardar)
		String central = request.getParameter("central"); 	//Central
		
		if(log)logs.debug("procesarConfigADSL: config="+config+" cabeza="+cabeza+" proceso:"+proceso+" central="+central);
		
		TipoNodo t;
		try {
			t = tipoNodoDAO.getTipoNodo(Integer.parseInt(cabeza));
			
			if(t.getEstado().equals("F")){
				error("No es posible llevar a cabo la operacion. La cabeza de prueba "+t.getSite()+" esta deshabilitada.",request,response);
				return;
			}
		} catch (NumberFormatException e) {
			logs.error(e);
			error(e,request,response);
			return;
		} catch (SapeDataException e) {
			logs.error(e);
			error(e,request,response);
			return;
			
		}
		
		if(config == null || config.equals("")|| proceso == null || cabeza == null){
			if(log) logs.debug("Variable vacia");
			error("Faltan parametros",request,response);
			return;
		}
		
		
        UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
        
        
        if(usuario == null){
        	redireccionar(acciones.getLoginPage(),request,response);
        	return;
        }
		
		ConfiguracionADSL c = configADSLDAO.getTipoConfiguracion(config);        
		
		if (proceso.equals("guardar")){
		
			List lista = c.getValues();
			
			int size = lista.size();
			for(int i=0;i<size;i++){
				String v[] = (String[])lista.get(i);
				
				String newValue = request.getParameter(v[0]);
				if(newValue == null) newValue = "";
				v[1] = newValue;
				lista.set(i,v);
			}
			
			try {
				configADSLDAO.guardarConfigADSL(lista,config,t);
				
			} catch (NumberFormatException e) {
				if(log) logs.debug(e);
				error(e,request,response);
				return;
			} catch (SapeDataException e) {
				if(log) logs.debug(e);
				error(e,request,response);
				return;
			}
			request.setAttribute("msg","Se guardo la Configuracion con exito.");
			logs.info("TERMINO DE GUARDAR INFO!!!!");
			configurarCabeza(request,response);
			return;
		}else if(proceso.equals("saveInfoCabeza")){
			
			String modo = null;
			String rta = null;
			if(config.equals("CPE")){
				modo = "atuc";
			} else if(config.equals("DSLAM")){
				modo = "atur";
			} else if(config.equals("PING")){
				modo = request.getParameter("tipo").toLowerCase();
			} else if(config.equals("IP")){
				modo = "ipconf";
			}
			
			List listaValores = c.getValues();// informacion acerca de cada variable.
			
			String query = "";	//llevara la parte de las variables a la hora de llamar el servicioGUDE
			
			if(!config.equals("PING")){
				
				int size = listaValores.size();
				for(int i=0;i<size;i++){
					String v[] = (String[])listaValores.get(i);
					
					String newValue = request.getParameter(v[0]);
					String nameVar = c.getVar(v[0]);
					if(newValue == null) newValue = "";
					
					query += nameVar+"="+newValue+";";
					
					logs.debug("Vble: "+nameVar+" valor=["+newValue+"]");
				}
				c.setValues(listaValores);

			}else{
				PING ping = (PING)c;
				
				String [] vars = ping.getVarsConfig(request.getParameter("tipo"));
				
				int size = vars.length;	
				
				for(int i=0;i<size;i++){
					
					String varName = ping.getVar(vars[i]);
					String value = request.getParameter(vars[i]);
					if(varName == null || value == null || varName.equals("")||value.equals(""))
						continue;
					query += varName+"="+value+";";
				}
				/*
				if(modo.equals("pppoe") ||modo.equals("pppoa")){// COnfiguracion
					String vpi = request.getParameter("vpi");
					ping.setVpi((String)stt.nextElement());
					ping.setVci((String)stt.nextElement());
					ping.setLocalIp((String)stt.nextElement());
					ping.setIp((String)stt.nextElement());
					ping.setLogin((String)stt.nextElement());
					ping.setDomain((String)stt.nextElement());
					
					if(modo.equals("pppoa")){
						ping.setEncapType((String)stt.nextElement());
					}
				}else if( modo.equals("ipoa")){
					ping.setVpi(stt.nextToken());
					ping.setVci(stt.nextToken());
					ping.setIp(stt.nextToken());
					ping.setGateway(stt.nextToken());
				                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   	ping.setSubMask(stt.nextToken());
				} else if(modo.equals("bridge")){
					ping.setVpi(stt.nextToken());
					ping.setVci(stt.nextToken());
					ping.setLocalIp(stt.nextToken());
					ping.setIp(stt.nextToken());
					ping.setGateway(stt.nextToken());
					ping.setSubMask(stt.nextToken());
					ping.setEncapType(stt.nextToken());
					ping.setLanType(stt.nextToken());
					ping.setFcsCont(stt.nextToken());
				}
				ping.setTipo(request.getParameter("tipo"));				
				listaConfigs.set(2,ping);*/
			}
			
			ServicioGUDE s = new ServicioGUDE();
			
			String comando = cabeza+" set "+modo+" '"+query+"'";
			logs.info("La variable comando quedo: "+comando);
			try {
				
				if(log) logs.debug("EJECUTARA servicio gude, servicio "+ServicioGUDE.SERVICIO_CONFIGURACION_ADSL+" con: ["+comando+"]");
				rta=s.ejecutarServicio(usuario.getNick(),ServicioGUDE.SERVICIO_CONFIGURACION_ADSL, comando);
			} catch (SapeDataException e) {
				error(e,request,response);
				return;
			}
			
			logs.info("RTA: [="+rta+"=]");
			request.setAttribute("msg","Se cargo en la cabeza la configuracion ingresada.<br>"+
					"Respuesta de la cabeza:<br>"+
					rta);
			
///////////////////////////////////////////////////////////////////////////////////////////////////
		} else if (proceso.equals("loadInfoCabeza")){
			
			String modo = null;
			String rta = null;
			if(config.equals("CPE")){
				modo = "atuc";
				//rta = ",G_DMT,0dB,6dB,6dB,DISABLE,DISABLE,ENABLE,DISABLE,DISABLE,ENABLE,ENABLE,AUTO,FAST,FAST,252,32,1,1,300,";
			} else if(config.equals("DSLAM")){
				modo = "atur";
				//rta = ",G_DMT,0,DISABLE,60,";
			} else if(config.equals("PING")){
				modo = request.getParameter("tipo").toLowerCase();
				/*if(modo.equals("pppoe"))
					rta = ",0,35,DYNAMIC,000.000.000.000,test,test,";
				else if(modo.equals("pppoa"))
					rta = ",0,35,DYNAMIC,000.000.000.000,dcgi,,LLC,";
				else if(modo.equals("ipoa"))
					rta = ",0,35,000.000.000.000,000.000.000.000,255.000.000.000,";
				else if(modo.equals("bridge"))
					rta = ",0,35,DYNAMIC,000.000.000.000,000.000.000.000,255.000.000.000,LLC,ETHERNET V2,DISABLE,";
				//modo = ((PING) c).getTipo().toLowerCase();*/
			} else if(config.equals("IP")){
				//rta = ",10.0.0.1,3,3,32,30,";
				modo = "ipconf";
			}
			
			ServicioGUDE s = new ServicioGUDE();
			
			try {
				String comando = cabeza+" show "+modo;
				if(log) logs.debug("EJECUTARA servicio gude, servicio "+ServicioGUDE.SERVICIO_CONFIGURACION_ADSL+" con: ["+comando+"]");
				rta=s.ejecutarServicio(usuario.getNick(),ServicioGUDE.SERVICIO_CONFIGURACION_ADSL, comando);
			} catch (SapeDataException e) {
				error(e,request,response);
				return;
			}
			
			// para controlar cuando la cabeza me devuelva valores nullos
			rta=rta.replace(",,",",+,");
			
			logs.info("DESPUES DEL 1 REPLACE: "+rta);
			//utilizamos el caracter '+' como elemento delimitador
			rta=rta.replace(',','+');
			logs.info("DESPUES DEL 2 REPLACE: "+rta);
			if(rta.startsWith("+"))
				rta = rta.substring(1,rta.length());
			if(rta.endsWith("+"))
				rta = rta.substring(0,rta.length()-1);
			
			logs.info("DESPUES DEL REPLACE IF: "+rta);
			
			rta =rta.replaceAll("dB","");
			
			List listaValores = c.getValues();	// los nombres de las variables de cada configuracion!
			// Vamos a asumir que la lista de variables tiene EXACTAMENTE el mismo orden de las variables
			// que nos devuelve el servicioGUDE.
			
			StringTokenizer stt = new StringTokenizer(rta,"+");
			
			logs.info("DESPUES DEL stt: "+stt.countTokens());
			
			// OJO con esta condicion: si es configuracion PING dispara este error asi este bueno!!!
			/*if(stt.countTokens() != listaValores.size()){
				error("Problema con los parametros, estos no coinciden.",request,response);
				return;
			}*/
			
			List listaConfigs = null;
			String site = null;
				
				//t = tipoNodoDAO.getTipoNodo(Integer.parseInt(cabeza));
				site=t.getSite();
				try {
					listaConfigs=configADSLDAO.getConfiguracionADSL(t);
				} catch (SapeDataException e) {
					logs.error(e);
					error(e,request,response);
					return;
				}
				
	
			
			request.setAttribute("site",site);
			request.setAttribute("mostrarConfiguracion","si");
			request.setAttribute("central",central);
			request.setAttribute("cabezaId",cabeza);
			
			if(!config.equals("PING")){
				
				int size = listaValores.size();
				for(int i=0;i<size;i++){
					String v[] = (String[])listaValores.get(i);
					
					String newValue = stt.nextToken();
					if(newValue == null) newValue = "";
					v[1] = newValue;
					listaValores.set(i,v);
					logs.debug("Vble: "+v[0]+" valor=["+newValue+"]");
				}
				c.setValues(listaValores);
				
				if(config.equals("CPE"))
					listaConfigs.set(0,(CPE)c);
				else if(config.equals("DSLAM"))
					listaConfigs.set(1,(DSLAM)c);
				else if(config.equals("IP"))
					listaConfigs.set(3,(IP)c);
				
				
			}else{
				PING ping = (PING)c;
								
				if(modo.equals("pppoe") ||modo.equals("pppoa")){// COnfiguracion
					ping.setVpi((String)stt.nextElement());
					ping.setVci((String)stt.nextElement());
					ping.setLocalIp((String)stt.nextElement());
					ping.setIp((String)stt.nextElement());
					ping.setLogin((String)stt.nextElement());
					ping.setDomain((String)stt.nextElement());
					
					if(modo.equals("pppoa")){
						ping.setEncapType((String)stt.nextElement());
					}
				}else if( modo.equals("ipoa")){
					ping.setVpi(stt.nextToken());
					ping.setVci(stt.nextToken());
					ping.setIp(stt.nextToken());
					ping.setGateway(stt.nextToken());
					ping.setSubMask(stt.nextToken());
				} else if(modo.equals("bridge")){
					ping.setVpi(stt.nextToken());
					ping.setVci(stt.nextToken());
					ping.setLocalIp(stt.nextToken());
					ping.setIp(stt.nextToken());
					ping.setGateway(stt.nextToken());
					ping.setSubMask(stt.nextToken());
					ping.setEncapType(stt.nextToken());
					ping.setLanType(stt.nextToken());
					ping.setFcsCont(stt.nextToken());
				}
				ping.setTipo(request.getParameter("tipo"));				
				listaConfigs.set(2,ping);
			}
			request.setAttribute("listaConfiguraciones",listaConfigs);
			request.setAttribute("msg","Se cargo la Configuracion con exito.");
			System.out.println("La lista tiene una longitud de :"+listaConfigs.size());
		}
		
		configADSL(request,response);
		//configurarCabeza(request,response);
		
	}
	
	
	/**
	 * Metodo encargado de solicitar las respectivas configuraciones de adsl para determinada cabeza SiplexPRO.
	 * 
	 * @param request
	 * @param response
	 */
	private void configurarCabeza(HttpServletRequest request, HttpServletResponse response) {
		boolean log = logs.isDebugEnabled();
		
		if(log) logs.debug("configurarCabeza");
		
		String cabezaId = request.getParameter("id");
		String central = request.getParameter("central");
		String site = null;
		List l = null;
		
		try {
			
			TipoNodo t = tipoNodoDAO.getTipoNodo(Integer.parseInt(cabezaId));
			site=t.getSite();
			
			l=configADSLDAO.getConfiguracionADSL(t); 
			
		} catch (NumberFormatException e) { 
			logs.error(e);
			error(e,request,response);
			return;
		} catch (SapeDataException e) {
			logs.error(e);
			error(e,request,response);
			return;
		}
		
		request.setAttribute("listaConfiguraciones",l);
		request.setAttribute("site",site);
		request.setAttribute("mostrarConfiguracion","si");
		request.setAttribute("central",central);
		request.setAttribute("cabezaId",cabezaId);
		
		configADSL(request,response);
	}
	
	private void configADSL(HttpServletRequest request, HttpServletResponse response) {
		
		List listaCentrales = null;
		
		try {
			listaCentrales=serieDAO.getCentralesPorTecnologia("SIPLEXPRO");
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
		
		request.setAttribute("listaCentrales",listaCentrales);
		redireccionarConPlantilla("configuracionADSL",request,response);
		
	}
	
	private void doModificarDescTipoPrueba(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String nuevaDesc = request.getParameter("nuevaDesc");
		
		int id2 = -1;
		try{
			
			id2 = Integer.parseInt(id);
		}catch(NumberFormatException e){
			error("Error en los parametros. Campo Id no es valido:"+id,request,response);
			return;
		}
		TipoPrueba tp = null;
		try {
			tp=tipoPruebaDAO.getTipoPrueba(id2);
			
			tp.setDescripcion(nuevaDesc);
			
			tipoPruebaDAO.actualizarTipoPrueba(tp);
			
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		String msg = (tp != null?"Se actualizo la descripcion de la prueba "+tp.getTipo():"Ocurrio un error, revisar.");
        request.setAttribute("mensaje",msg);
        redireccionarConPlantilla( "MantenimientoMensaje",request,response);
	}
	
	private void modificarDescTipoPrueba(HttpServletRequest request, HttpServletResponse response) {
		
		List l = null;
		try {
			l=tipoPruebaDAO.getAllTipoPrueba();
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("listaTipoPrueba",l);
		
		redireccionarConPlantilla("modificarTipoPrueba",request,response);
	}
	
	
	private void editarPar(HttpServletRequest request, HttpServletResponse response) {
		if (logs.isDebugEnabled()) logs.debug("editarPar");
		
		String par = request.getParameter("par");
		int parMatriz = 0;
		try {
			parMatriz = Integer.parseInt(par);
		} catch (NumberFormatException e) {	}
		if (parMatriz == 0) {
			error("Par No valido: " + par, request, response);
			return;
		}
		ParMatrizSiplexPro parEditar = null;
		try {
			parEditar = tipoNodoDAO.getParMatriz(parMatriz);
		} catch (SapeDataException e) {
			if (e.getCause() instanceof net.sf.hibernate.ObjectNotFoundException) {
				parEditar = new ParMatrizSiplexPro(parMatriz, 0);
			} else {
				error(e, request, response);
				return;
			}
		}
		request.setAttribute("par", parEditar);
		redireccionarConPlantilla("editarParMatriz", request, response);
	}
	
	
	private void guardarPar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (logs.isDebugEnabled()) logs.debug("guardarPar");
		
		String par = request.getParameter("par");
		String telefono = request.getParameter("txtTelefono");
		int parMatriz = 0;
		int telefonoMatriz = 0;
		try {
			parMatriz = Integer.parseInt(par);
			telefonoMatriz = Integer.parseInt(telefono);
		} catch (NumberFormatException e) {	}
		if (parMatriz == 0 || telefonoMatriz == 0) {
			error("Datos Invalidos", request, response);
			return;
		}
		
		ParMatrizSiplexPro parMatrizSiplexPro = new ParMatrizSiplexPro(parMatriz, telefonoMatriz);
		try {
			tipoNodoDAO.guardarPar(parMatrizSiplexPro);
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('Par Actualizado!');");
		out.println("window.close();");
		out.println("opener.location.reload();");
		out.println("</script>");
		out.flush();
	}
	
	
	private void matrizSiplexPro(HttpServletRequest request, HttpServletResponse response) {
		if (logs.isDebugEnabled()) logs.debug("matrizSiplexPro");
		Map telefonosMatriz = null;
		try {
			telefonosMatriz = tipoNodoDAO.getTelefonosMatriz();
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
		request.setAttribute("telefonosMatriz", telefonosMatriz);
		redireccionarConPlantilla("matrizSiplexPro", request, response);
	}
	
}
