package com.osp.sape.servlets.mantenimiento;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.gc.acceso.GestorServlet;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.SerieDAO;
import com.osp.sape.data.TipoNodoDAO;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.utils.Firmware;


public class FirmwareServlet extends GestorServlet {

	private final String COMANDO_FIRMWARE = "/opt/gude/bin/spu"; //TODO sacarlo de sape-config.xml
	private SerieDAO serieDAO;
	private TipoNodoDAO tipoNodoDAO;
	
	
	public void init() throws ServletException {
		super.init();
		DAOFactory daoFactory = DAOFactoryImpl.getInstance();
		
		serieDAO = daoFactory.getSerieDAO();
		tipoNodoDAO = daoFactory.getTipoNodoDAO();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		if (operacion == null || operacion.equals("")) {
			inicioCargarFirmware(request, response);			
			return;
		}
		if (operacion.equals("estadoProceso")) {
			estadoProceso(request, response);
			return;
		}
		if (operacion.equals("cancelarUpdate")) {
			cancelarUpdate(request, response);
			return;
		}	
		if (operacion.equals("progresoFirmware")) {
			progresoFirmware(request, response);
			return;
		}
		if(operacion.equals("cargarFirmware")){
			cargarFirmware(request,response);
			return;
		}
			//cuando pasa por aqui es que no entro por ninguna opcion.
		PrintWriter out = response.getWriter();
		out.println("[ERROR] Operacion Invalida.");
	}
	
	
	/**
	 * Por este Metodo se iniciar el proceso, ya es estado se hace por el doGet
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String operacion = request.getParameter("operacion");
		if (operacion == null || operacion.equals("")) {
			out.println("Operacion Invalida");
			return;
		}
		if (operacion.equals("cargarFirmware")) {
			cargarFirmware(request, response);
			return;
		}
		
	}
	
	
	private void cancelarUpdate(HttpServletRequest request, HttpServletResponse response) 
																throws IOException {
//TODO quitar las IOException
		if (debug) logs.debug("cancelarUpdate");
		PrintWriter out = response.getWriter();
		
		Firmware firmware = (Firmware) request.getSession().getAttribute("firmware");
		if (firmware == null) {
			out.print("[ERROR] No hay firmware iniciado");
			out.flush();
			return;
		}
		
		Socket socket = new Socket(firmware.getIpSocket(), firmware.getPuertoSocket());
		BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		DataOutputStream outSocket = new DataOutputStream(socket.getOutputStream());
		outSocket.write("END\n\r".getBytes());
		outSocket.flush();
		String lee = inSocket.readLine();	
		if (debug) logs.debug("Lee: " + lee);

		String respuesta = "[ERROR] No Esperado. "; 
		if (lee.equals("ENDING PROCESS")) {
			respuesta = "Cancelar OK";
		} else {
			respuesta += lee;
		}

		request.getSession().removeAttribute("firmware");
		out.print(respuesta);
		out.flush();
	}

	
	private void cargarFirmware(HttpServletRequest request, HttpServletResponse response) 
														throws IOException {
		if (debug)logs.debug("cargarFirmware");
		
		request.setAttribute("tipo", ""); //porque el uploadServlet lo trae con tipo = cerrar
		
				//valido que ya no este un firmware en session.
		Firmware firmware = (Firmware) request.getSession().getAttribute("firmware");
		if (firmware != null) {
			error("Ya hay una Actualizacion en proceso.", request, response); 
			return;
		}
		
		int idCabeza = 0;
		try {
			idCabeza = Integer.parseInt(request.getParameter("idCabeza"));
		} catch (NumberFormatException e) {
			logs.error(e);
			error("ID de la Cabeza Invalido", request, response);
			return;
		}

		TipoNodo cabeza = null;
		try {
			cabeza = tipoNodoDAO.getTipoNodo(idCabeza);
			if (!cabeza.getEstado().equals("F")) {
				logs.warn("La cabeza no esta Desactivada");
				error("La Cabeza Debe estar Desactivada", request, response);
				return;
			}
			if (cabeza.getTipoCabeza().getId() != 1) {
				logs.warn("La cabeza no es SIPLEXPRO");
				error("La Cabeza No es SIPLEXPRO", request, response);
				return;
			}
		} catch (SapeDataException e) {
			logs.error(e);
			error(e, request, response);
			return;
		}
		//TODO buscar la cabeza y validar que sea Siplexpro.
		String archFirmware = request.getParameter("archivo");
		String path="";
		if (archFirmware == null || archFirmware.equals("")) {
			logs.warn("Archivo de Firmware: " + archFirmware);
			error("Falta el Archivo del firmware", request, response);
			return;
		}
		path = getServletContext().getRealPath("/");
		
		if(path.endsWith("/")){
			path = path.substring(0, path.length()-1);
			if(debug) logs.debug("la ruta desde la raiz quedo: "+path);
		}
		
		archFirmware=path+acciones.getTemplate("temporalReportes") + archFirmware;
		
		firmware = new Firmware(cabeza.getSite(), cabeza.getIpEsclavo(), Integer.parseInt(cabeza.getPuertoEsclavo()), archFirmware);
		arrancarFirmware(firmware);
		request.getSession().setAttribute("firmware", firmware);

		response.sendRedirect(request.getContextPath() + "/actionSape?accion=firmware&operacion=progresoFirmware");
	}
		
	
	private void arrancarFirmware(Firmware firmware) throws IOException {
		String comando = COMANDO_FIRMWARE + " -i " + firmware.getIpCabeza() + " " + firmware.getPuertoCabeza() + " " + firmware.getArchivo() + " &";
		if (debug) logs.debug("Comando: " + comando);
		Process p = Runtime.getRuntime().exec(comando);
		DataInput input = new DataInputStream(p.getInputStream());
		String linea = null;
		linea = input.readLine(); 	//Linea en Blanco
		linea = input.readLine(); 	//inicia SPU.
		linea = input.readLine();		//Version
		if (debug) logs.debug("Lee: " + linea);
		firmware.setVersion(linea.substring(8));
//		if (debug) logs.debug("Version: " + firmware.getVersion());
		linea = input.readLine();		//Bin file
		if (debug) logs.debug("Lee: " + linea);
		firmware.setBinFile(linea.substring(10));
//		if (debug) logs.debug("BinFile: " + firmware.getBinFile());
		linea = input.readLine();		//Paquetes
		if (debug) logs.debug("Lee: " + linea);
		firmware.setPaquetes(Integer.parseInt(linea.substring(9)));
//		if (debug) logs.debug("Paquetes: " + firmware.getPaquetes());
		linea = input.readLine();		//Size
		if (debug) logs.debug("Lee: " + linea);
		firmware.setTamano(Integer.parseInt(linea.substring(5)));
//		if (debug) logs.debug("Tamano: " + firmware.getTamano());
		firmware.setIpSocket("localhost");
		firmware.setPuertoSocket(2323);
		if (debug) logs.debug("Firmware queda: " + firmware.toString());
		if (debug) logs.debug("Proceso Liberado!");
	}
	
	
	private void estadoProceso(HttpServletRequest request, HttpServletResponse response) 
														throws IOException {
		if (debug) logs.debug("estadoProceso");
		PrintWriter out = response.getWriter();
		int progreso = 0;
		String estado = "ND";
		long tiempoTranscurrido = 0;
		long tiempoRestante = 0;
		
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");

		Firmware firmware = (Firmware) request.getSession().getAttribute("firmware");
//		System.out.println("Firmware de session: " + firmware);
		if (firmware == null) {
			enviarRespuesta (out, "[ERROR] No hay firmware iniciado.", progreso, tiempoTranscurrido, tiempoRestante);
			return;
		}
		
		
		Socket socket = null;
		try {
			socket = new Socket(firmware.getIpSocket(), firmware.getPuertoSocket());
		} catch (IOException e) {
			String mensaje = "[ERROR] " + e + ". Informar al personal Tecnico.";
			if (e instanceof java.net.ConnectException) {
				mensaje = "[ERROR] No Socket.";
			} else {
				logs.error(e);
			}
			request.getSession().removeAttribute("firmware");
			enviarRespuesta (out, mensaje, progreso, tiempoTranscurrido, tiempoRestante);
			return;
		}
		BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		DataOutputStream outSocket = new DataOutputStream(socket.getOutputStream());
//		System.out.println("Voy a enviar");
		outSocket.write("PROGRESS\n\r".getBytes());
		outSocket.flush();
		String lee = inSocket.readLine();	
		if (debug) logs.debug("Lee: " + lee);
			//valido los diferentes mensajes
		if (lee.equals("ERASING_FLASH") || lee.equals("STARTING")) {
			estado = "Borrando Memoria Flash";
			progreso = 0;
 		} else if (lee.equals("DOWNLOAD_OK")) {
			estado = "Carga Completa.";
			progreso = 100;
 		} else if (lee.equals("CHECKING_DATA")) {
			estado = "Comprobando Archivo.";
			progreso = 100; 			
		} else if (lee.equals("UPGRADING")) {
			estado = "Actualizando Firmware.";
			progreso = 100;
		} else if (lee.equals("UPGRADE_OK")) {
			estado = "Firmware Cargado. Actualizacion Exitosa!";
			outSocket.writeBytes("END\n\r");
			outSocket.flush();
			progreso = 100;
			request.getSession().removeAttribute("firmware");
		} else if (lee.equals("ERROR")) {
			estado = "ERROR";
			progreso = 100;
			outSocket.writeBytes("END\n\r");
			outSocket.flush();
		} else {
			try {
				progreso = Integer.parseInt(lee);
				estado = "Cargando Archivo";
			} catch (NumberFormatException e) {
				estado = "[ERROR] " + e.toString();
			}
		}
		outSocket.close();
		socket.close();

		tiempoTranscurrido = (System.currentTimeMillis() - firmware.getFechaInicial()) / 1000;
		
		if (progreso > 0 && progreso < 100) { //cuando llego al 100% no sigo calculando el tiempo restante porque ya queda en 0.
			int paqTransferidos = (firmware.getPaquetes() * progreso) / 100;
//			int paqRestantes = firmware.getPaquetes() - paqTransferidos;
//			System.out.println("Total paq: " + firmware.getPaquetes() + " pagTranferidos: " + paqTransferidos + " retantes: " + paqRestantes);	
			tiempoRestante = ((tiempoTranscurrido * firmware.getPaquetes()) / paqTransferidos) - tiempoTranscurrido;
		}

		enviarRespuesta (out, estado, progreso, tiempoTranscurrido, tiempoRestante);		
 	}
	
	
	private void enviarRespuesta (PrintWriter out, String estado, int progreso, long tiempoTranscurrido, long tiempoRestante) 
																										throws IOException {
		if (debug) logs.debug("enviarRespuesta");
		Document documento = new Document();
		Element root = new Element("firmware");
		documento.setRootElement(root);
		Element hijo = new Element("estado");
		hijo.setText(estado);
		root.addContent(hijo);
		hijo = new Element("progreso");
		hijo.setText(String.valueOf(progreso));
		root.addContent(hijo);
		hijo = new Element("duracion");
		hijo.setText(String.valueOf(tiempoTranscurrido));
		root.addContent(hijo);
		hijo = new Element("restante");
		hijo.setText(String.valueOf(tiempoRestante));
		root.addContent(hijo);
		
		XMLOutputter serializer = new XMLOutputter();
		serializer.output(documento, out);
		out.flush();	
		out.close();
	}
	
	
	private void inicioCargarFirmware(HttpServletRequest request, HttpServletResponse response) {
		if (debug) logs.debug("inicioCargarFirmware");
    	List listaCentrales = null;
    	try {
			listaCentrales = serieDAO.getCentralesPorTecnologia("SIPLEXPRO");			
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		boolean firmwareIniciado = false;
		if (request.getSession().getAttribute("firmware") != null) {
			firmwareIniciado = true;
		}
		
		request.setAttribute("firmwareIniciado", new Boolean(firmwareIniciado)); //para informar al usuario si ya hay un firmware iniciado.
		request.setAttribute("listaCentrales",listaCentrales);
		request.setAttribute("destino","actionSape?accion=firmware&operacion=cargarFirmware");		
		redireccionarConPlantilla("cargarFirmware",request,response);

	}
	
	/**
	 * Muestra la pantalla de progreso del firmware
	 * @param request
	 * @param response
	 */
	private void progresoFirmware (HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("firmware", request.getSession().getAttribute("firmware"));
		redireccionarConPlantilla("progresoFirmware", request, response);
	}
	
		
}




	
