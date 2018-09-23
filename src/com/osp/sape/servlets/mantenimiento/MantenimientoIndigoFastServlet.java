/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Aug 11, 2006 - 1:00:19 PM
 */

package com.osp.sape.servlets.mantenimiento;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.dialect.IngresDialect;

import org.apache.commons.io.FileUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.gc.acceso.GestorServlet;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.FastEpmDAO;
import com.osp.sape.data.IndigoEpmDAO;
import com.osp.sape.data.SerieDAO;
import com.osp.sape.data.TipoNodoDAO;
import com.osp.sape.maestros.FastEpm;
import com.osp.sape.maestros.IndigoEpm;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.utils.ConfiguracionClienteSape;
import com.osp.sape.utils.ServicioGUDE;

public class MantenimientoIndigoFastServlet extends GestorServlet {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private FastEpmDAO fastEpmDAO;
	private IndigoEpmDAO indigoEpmDAO;
	private SerieDAO serieDAO;
    private ConfiguracionClienteSape configuracionClienteSape;
    private TipoNodoDAO tipoNodoDAO;
	public void init() throws ServletException {
		super.init();
		DAOFactory factoria =DAOFactoryImpl.getInstance();
		fastEpmDAO = factoria.getFastEpmDAO();
		indigoEpmDAO = factoria.getIndigoEpmDAO();
		serieDAO = factoria.getSerieDAO();
		tipoNodoDAO = factoria.getTipoNodoDAO();
		configuracionClienteSape = ConfiguracionClienteSape.getInstance();
	}

	/**
	 * TENER MUCHO CUIDADO:
	 * por ahora los archivos de configuracion de los Indigos y Fast se estan guardando
	 * unicamente en el servidor tomcat del proyecto, en la ruta especificada por
	 * la propiedad rutaArchivosIndigoFast de la clase ConfiguracionClienteSape
	 * cuando el cliente es EPM
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
		request=req;
		response=res;
		
		String operacion = request.getParameter("operacion");
        operacion = (operacion == null?"":operacion);
        
        if(debug)logs.debug("doGet: operacion=["+operacion+"]");
        
        
        if (operacion == null || operacion.equals("")) {
    		//SE DESPLIEGA PANTALLA INICIAL DE CONFIGURACION DE INDIGOS Y FAST.
        	redireccionarConPlantilla("inicioIndigoFast", request, response);
        	return;
        	/**
        	 * OPERACIONES BASICAS SOBRE FAST.
        	 */
        } else if(operacion.equals("inicioFast")){
        	
        	inicioFast();
        } else if(operacion.equals("listaFast")) {
        	// pantalla donde se muestran en una lista todos los Fast y sus configuraciones
        	listaFast();
        } else if (operacion.equals("eliminarFast")){
        	eliminarFast();
        } else if(operacion.equals("agregarFast")){
        	agregarFast();
        }else if(operacion.equals("doAgregarFast")){
        	doAgregarFast();
        }else if(operacion.equals("modificarFast")){
        	modificarFast();
        }else if(operacion.equals("doModificarFast")){
        	doModificarFast();
        }else if(operacion.equals("cargarArchivoFast")){
        	cargarArchivoFast();
        }else if(operacion.equals("seleccionarFast")){
        	seleccionarFast();
        }else if(operacion.equals("obtenerArchivoFast")){
        	obtenerArchivoFast();
        }else if(operacion.equals("guardarArchivoFast")){
        	guardarArchivoFast();
        	
        	/**
        	 * OPERACIONES BASICAS SOBRE INDIGOS
        	 */
        } else if(operacion.equals("inicioIndigos")){
        	inicioIndigos();
        } else if (operacion.equals("eliminarIndigo")){
        	eliminarIndigo();
        } else if(operacion.equals("agregarIndigo")){
        	agregarIndigo();
        }else if(operacion.equals("doAgregarIndigo")){
        	doAgregarIndigo();
        }else if(operacion.equals("modificarIndigo")){
        	modificarIndigo();
        }else if(operacion.equals("doModificarIndigo")){
        	doModificarIndigo();
        }else if(operacion.equals("cargarArchivoIndigo")){
        	cargarArchivoIndigo();
        }else if(operacion.equals("seleccionarIndigo")){
        	seleccionarIndigo();
        }else if(operacion.equals("obtenerArchivoIndigo")){
        	obtenerArchivoIndigo();
        }else if(operacion.equals("guardarArchivoIndigo")){
        	guardarArchivoIndigo();
        }else if(operacion.equals("operacionesBasicas")){
        	
        	//OPERACIONES DE TRANSFERENCIA RESETEO Y DEMAS SOBRE INDIGOS Y FAST
        	
        	operacionesBasicas();
        } else if(operacion.equals("transferenciaFast")){
        	transferenciaFast();
        }else if(operacion.equals("transferenciaIndigo")){
        	transferenciaIndigo();
        }else if(operacion.equals("abortarIndigo")){
        	abortarIndigo();
        }else if(operacion.equals("resetIndigo")){
        	resetIndigo();
        }else if(operacion.equals("resetFast")){
        	resetFast();
        }else if(operacion.equals("ingresoFast")){
        	ingresoFast();
        }
        
        //11-sep-2006 John David Gutierrez
        //Prueba clip sobre fast
        else if(operacion.equals("pruebaClip")){
        	pruebaClip();
        }else if(operacion.equals("buscarFastSerie")){
        	buscarFastSerie(request, response);
        }
        
        
	}
	
	private void listaFast(){
		List l = null;
		
		try {
			l=fastEpmDAO.getAllFast();
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("listaFast", l);
		redireccionarConPlantilla("listaFast", request, response);
	}
	
	private void ingresoFast(){
		List l = null,l2=null;
		
		try {
			l=fastEpmDAO.getAllFast();
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		logs.debug("la lista tiene: "+l.size());
		request.setAttribute("listaFast", l);
		redireccionarConPlantilla("inicioFast", request, response);
	}
	
	private void resetFast() throws IOException{
		String id2 = request.getParameter("indigo");// id del TipoNodo a utilizar
		String telFast = request.getParameter("fast");// telefono del fast a transferir
		
		request.setAttribute("tipo", "popup");
		
		int id =-1;
		try{
			id=Integer.parseInt(id2);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		
		TipoNodo indigo = null;
		FastEpm fast = null;

		// 1. obtengo las propiedades del indigo con el que voy a trabajar
		try {
			indigo = tipoNodoDAO.getTipoNodo(id);
			fast=fastEpmDAO.getFastPorTelefono(telFast);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		if(fast == null){
			error("El Fast que esta tratando de acceder no se encuentra en el sistema. Por favor ingrese primero un registro para este.",request,response);
			return;
		}
		
		String ip = indigo.getIpEsclavo();
		String puerto=indigo.getPuertoEsclavo();
		
		
		//String nameFile = "/opt/gude/sqltcl/Indigos/conf_fast/parf"+fast.getNumeroFast();
		

		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.print("<html><body><pre>");
		
		ServicioGUDE servicio = new ServicioGUDE();
		
		String parametros[] = new String[4];
		
		parametros[0]=getUsuarioSession(request);
		parametros[1]="46";
		parametros[2]=ip;
		parametros[3]=fast.getTelefonoFast().toString();

		//parametros[]=;
		
		 //mauricio 43 transferirFast telefono ip puerto archivopath
		String respuesta = "";
		try {
			respuesta=servicio.ejecutarServicio(parametros, out);
		} catch (SapeDataException e) {
			error("ERROR ejecutando el Servicio: "+e,request,response);
			return;			
		}
		
		out.println("<b>RESPUESTA</b>: "+respuesta);
		out.println("<br><br><center><a href='javascript:window.close();'>Cerrar</a></center>");
		out.println("</pre></body></html>");

	}
	
	private void resetIndigo() throws IOException {
		String id2 = request.getParameter("indigo");// id del indigo a utilizar
		
		request.setAttribute("tipo", "popup");
		
		int id =-1;
		try{
			id=Integer.parseInt(id2);
		}catch(NumberFormatException e){
			
			error(e,request,response);
			return;
		}
		TipoNodo indigo = null;
		// 1. obtengo las propiedades del indigo con el que voy a trabajar
		try {
			indigo = tipoNodoDAO.getTipoNodo(id);
		} catch (SapeDataException e) {			
			error(e,request,response);
			return;
		}
		
		String ip = indigo.getIpEsclavo();
		//String puerto=indigo.getPuertoEsclavo();
	
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.print("<html><body><pre>");
		
		ServicioGUDE servicio = new ServicioGUDE();
		
		String parametros[] = new String[3];
		
		parametros[0]=getUsuarioSession(request);
		parametros[1]="44";
		parametros[2]=ip;
		/*parametros[3]="9999999";
		parametros[4]=indigo.getIp();
		parametros[5]=indigo.getPort();
		parametros[6]=f1.getPath();
		//parametros[]=;
		*/
		 //mauricio 43 transferirFast telefono ip puerto archivopath
		String respuesta = "";
		try {
			respuesta=servicio.ejecutarServicio(parametros, out);
		} catch (SapeDataException e) {
			error("ERROR ejecutando el Servicio: "+e,request,response);
			return;			
		}
		
		out.println("<b>RESPUESTA</b>: "+respuesta);
		out.println("<br><br><center><a href='javascript:window.close();'>Cerrar</a></center>");
		out.println("</pre></body></html>");
	}
	
	private void abortarIndigo() throws IOException{
		String id2 = request.getParameter("indigo");// id del indigo a utilizar
		
		request.setAttribute("tipo", "popup");
		
		int id =-1;
		try{
			id=Integer.parseInt(id2);
		}catch(NumberFormatException e){
			
			error(e,request,response);
			return;
		}
		TipoNodo indigo = null;
		// 1. obtengo las propiedades del indigo con el que voy a trabajar
		try {
			indigo = tipoNodoDAO.getTipoNodo(id);
		} catch (SapeDataException e) {			
			error(e,request,response);
			return;
		}
		
		String ip = indigo.getIpEsclavo();
		//String puerto=indigo.getPuertoEsclavo();
	
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.print("<html><body><pre>");
		
		ServicioGUDE servicio = new ServicioGUDE();
		
		String parametros[] = new String[3];
		
		parametros[0]=getUsuarioSession(request);
		parametros[1]="45";
		parametros[2]=ip;
		/*parametros[3]="9999999";
		parametros[4]=indigo.getIp();
		parametros[5]=indigo.getPort();
		parametros[6]=f1.getPath();
		//parametros[]=;
		*/
		 //mauricio 43 transferirFast telefono ip puerto archivopath
		String respuesta = "";
		try {
			respuesta=servicio.ejecutarServicio(parametros, out);
		} catch (SapeDataException e) {
			error("ERROR ejecutando el Servicio: "+e,request,response);
			return;			
		}
		
		out.println("<b>RESPUESTA</b>: "+respuesta);
		out.println("<br><br><center><a href='javascript:window.close();'>Cerrar</a></center>");
		out.println("</pre></body></html>");
	}
	
	private void transferenciaIndigo() throws IOException{
		String id2 = request.getParameter("indigo");// id del indigo a utilizar
		
		request.setAttribute("tipo", "popup");
		
		int id =-1;
		try{
			id=Integer.parseInt(id2);
		}catch(NumberFormatException e){
			
			error(e,request,response);
			return;
		}
		TipoNodo indigo = null;
		// 1. obtengo las propiedades del indigo con el que voy a trabajar
		try {
			indigo = tipoNodoDAO.getTipoNodo(id);
		} catch (SapeDataException e) {			
			error(e,request,response);
			return;
		}
		
		String ip = indigo.getIpEsclavo();
		//String puerto=indigo.getPuertoEsclavo();
	
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.print("<html><body><pre>");
		
		ServicioGUDE servicio = new ServicioGUDE();
		
		String parametros[] = new String[7];
		
		parametros[0]=getUsuarioSession(request);
		parametros[1]="43";
		parametros[2]="transferirIndigo";
		parametros[3]="9999999";
		parametros[4]=indigo.getIpEsclavo();
		parametros[5]=indigo.getPuertoEsclavo();
		parametros[6]="/opt/gude/sqltcl/Indigos/conf_indigo/"+indigo.getSite();
		
		
		 //mauricio 43 transferirFast telefono ip puerto archivopath
		String respuesta = "";
		try {
			respuesta=servicio.ejecutarServicio(parametros, out);
		} catch (SapeDataException e) {
			error("ERROR ejecutando el Servicio: "+e,request,response);
			return;			
		}
		
		out.println("<b>RESPUESTA</b>: "+respuesta);
		out.println("<br><br><center><a href='javascript:window.close();'>Cerrar</a></center>");
		out.println("</pre></body></html>");
	}
	
	private void transferenciaFast() throws IOException{
		
		String id2 = request.getParameter("indigo");// id del TipoNodo a utilizar
		String telFast = request.getParameter("fast");// telefono del fast a transferir
		
		request.setAttribute("tipo", "popup");
		
		int id =-1;
		try{
			id=Integer.parseInt(id2);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		
		TipoNodo indigo = null;
		FastEpm fast = null;

		// 1. obtengo las propiedades del indigo con el que voy a trabajar
		try {
			indigo = tipoNodoDAO.getTipoNodo(id);
			fast=fastEpmDAO.getFastPorTelefono(telFast);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		if(fast == null){
			error("El Fast que esta tratando de acceder no se encuentra en el sistema. Por favor ingrese primero un registro para este.",request,response);
			return;
		}
		
		String ip = indigo.getIpEsclavo();
		String puerto=indigo.getPuertoEsclavo();
		
		//2. obtengo el archivo que se va a transferir al Fast
		
		String nameFile = "/opt/gude/sqltcl/Indigos/conf_fast/parf"+fast.getNumeroFast();
		
		String path = configuracionClienteSape.getRutaArchivosIndigoFast();
		if(path==null){
			error("Ocurrio un error en los parametros",request,response);
			return;
		}		
		//File f1 = new File(path+"/Fast/"+nameFile);
		
		/*if(!f1.exists()){
			
			error("El archivo de configuracion para este Fast aun no esta en el sistema, por favor ingreselo primero",request,response);
			return;
		}*/
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		out.print("<html><body><pre>");
		
		ServicioGUDE servicio = new ServicioGUDE();
		
		String parametros[] = new String[7];
		
		parametros[0]=getUsuarioSession(request);
		parametros[1]=ServicioGUDE.SERVICIO_OPERACIONES_INDIGO_FAST;
		parametros[2]="transferirFast";
		parametros[3]=fast.getTelefonoFast().toString();
		parametros[4]=indigo.getIpEsclavo();
		parametros[5]=indigo.getPuertoEsclavo();
		parametros[6]=nameFile;
		//parametros[]=;
		
		 //mauricio 43 transferirFast telefono ip puerto archivopath
		String respuesta = "";
		try {
			respuesta=servicio.ejecutarServicio(parametros, out);
		} catch (SapeDataException e) {
			error("ERROR ejecutando el Servicio: "+e,request,response);
			return;			
		}
		
		out.println("<b>RESPUESTA</b>: "+respuesta);
		out.println("<br><br><center><a href='javascript:window.close();'>Cerrar</a></center>");
		out.println("</pre></body></html>");
			
	}
	
	private void operacionesBasicas(){
		List listaIndigo = null;
		List listaFast=null;
		
		try {
			//listaFast=fastEpmDAO.getAllFast();
			//listaIndigo=indigoEpmDAO.getAllIndigo();
			listaIndigo = tipoNodoDAO.getTipoNodosPorTecnologia(9,"F");
					
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("listaFast",listaFast);
		request.setAttribute("listaIndigo",listaIndigo);
		
		redireccionarConPlantilla("operacionesBasicas",request,response);
	}

	private void guardarArchivoIndigo(){
		String archivo = request.getParameter("archivo");
		String id2 = (String) request.getSession().getAttribute("indigoSeleccionado");
		
		int id = -1;
		try{
			id = Integer.parseInt(id2);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		
		TipoNodo f = null;
		try {
			f=tipoNodoDAO.getTipoNodo(id);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		String nameFile = f.getSite();
		String path = configuracionClienteSape.getRutaArchivosIndigoFast();
		if(path==null){
			error("Ocurrio un error en los parametros",request,response);
			return;
		}		
		File f1 = new File(path+"/conf_indigo/"+nameFile);
		
		String pathTmp =getServletContext().getRealPath("/")+acciones.getTemplate("temporalReportes");
		File f2 = new File(pathTmp+archivo);
		
		logs.debug("f1: "+f1.getAbsolutePath());
		logs.debug("f2: "+f2.getAbsolutePath());

		try {
			FileUtils.copyFile(f2,f1);
		} catch (IOException e) {
			error(e,request,response);
			return;
		}
		
		//
		request.setAttribute("msg", "El archivo ha sido cargado con exito para el "+f.getSite()+".");
		request.setAttribute("indigoSeleccionado",id2);
		request.setAttribute("destino","actionSape?accion=mantenimientoIndigoFast&operacion=guardarArchivoIndigo");
		cargarArchivoIndigo();
		//mensaje(,"normal",request,response);
		
	}

	
	private void guardarArchivoFast(){
		String archivo = request.getParameter("archivo");
		String tel = (String) request.getSession().getAttribute("fastSeleccionado");
				
		FastEpm f = null;
		try {
			f=fastEpmDAO.getFastPorTelefono(tel);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		String nameFile = "parf"+f.getNumeroFast();
		String path = configuracionClienteSape.getRutaArchivosIndigoFast();
		if(path==null){
			error("Ocurrio un error en los parametros",request,response);
			return;
		}		
		File f1 = new File(path+"/conf_fast/"+nameFile);
		
		String pathTmp =getServletContext().getRealPath("/")+acciones.getTemplate("temporalReportes");
		File f2 = new File(pathTmp+archivo);
		
		logs.debug("f1: "+f1.getAbsolutePath());
		logs.debug("f2: "+f2.getAbsolutePath());
		

		
		try {
			FileUtils.copyFile(f2,f1);
		} catch (IOException e) {
			error(e,request,response);
			return;
		}
		
		//
		request.setAttribute("msg", "El archivo ha sido cargado con exito para el Fast "+f.getNumeroFast()+".");
		request.setAttribute("fastSeleccionado",tel);
		request.setAttribute("destino","actionSape?accion=mantenimientoIndigoFast&operacion=guardarArchivoFast");
		cargarArchivoFast();
		//mensaje(,"normal",request,response);
		
	}

	private void obtenerArchivoIndigo() throws IOException{
		String id2 = request.getParameter("id");
		int id = -1;
		try{
			id = Integer.parseInt(id2);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		
		TipoNodo f = null;
		try {
			f=tipoNodoDAO.getTipoNodo(id);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		String nameFile = f.getSite();
		
		String path = configuracionClienteSape.getRutaArchivosIndigoFast();
		if(path==null){
			error("Ocurrio un error en los parametros",request,response);
			return;
		}		
		File f1 = new File(path+"/conf_indigo/"+nameFile);
		
		logs.debug("f1: "+f1.getAbsolutePath());
		
		String pathTmp =getServletContext().getRealPath("/")+acciones.getTemplate("temporalReportes");
		File f2 = new File(pathTmp+nameFile);//Se le agrega el sufijo .txt
		
		logs.debug("f2: "+f2.getAbsolutePath());
		
		//logs.debug("a puntito de copiar el dichoso archivo");
		
		try {
			FileUtils.copyFile(f1,f2);
		} catch (IOException e) {
			error(e,request,response);
			return;
		}
		//response.setCharacterEncoding("application/txt");
		//logs.debug("TIPO de contenido: "+response.getContentType());
		//logs.debug("voy a enviar un sendredirect");
		response.setContentType("text/plain");
		response.sendRedirect("/sape"+acciones.getTemplate("temporalReportes")+f1.getName());
	}

	
	private void obtenerArchivoFast() throws IOException{
		
		
		String telefono = request.getParameter("telefono");
		
		FastEpm f = null;
		try {
			f=fastEpmDAO.getFastPorTelefono(telefono);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		String nameFile = "parf"+f.getNumeroFast();
		
		String path = configuracionClienteSape.getRutaArchivosIndigoFast();
		if(path==null){
			error("Ocurrio un error en los parametros",request,response);
			return;
		}		
		File f1 = new File(path+"/conf_fast/"+nameFile);
		
		logs.debug("f1: "+f1.getAbsolutePath());
		
		String pathTmp =getServletContext().getRealPath("/")+acciones.getTemplate("temporalReportes");
		File f2 = new File(pathTmp+nameFile);
		
		logs.debug("f2: "+f2.getAbsolutePath());
		
		logs.debug("a puntito de copiar el dichoso archivo");
		
		
		try {
			FileUtils.copyFile(f1,f2);
		} catch (IOException e) {
			error(e,request,response);
			return;
		}
		//response.setCharacterEncoding("file/txt");
		logs.debug("voy a enviar un sendredirect");
		response.setContentType("text/plain");
		response.sendRedirect("/sape"+acciones.getTemplate("temporalReportes")+f1.getName());
				
		//String path = ;
	}

	/*
	 * Metodo para mostrar adecuadamente la informacion con respecto a los archivos de
	 * configuracion de un indigo.
	 */
	private void seleccionarIndigo(){
		//1. obtengo el id del indigo(TipoNodo) actual
		String id = request.getParameter("id");
		//2. guardo el id en la session y en el request (OJO debe ser en los 2)
		request.setAttribute("indigoSeleccionado",id);
		request.getSession().removeAttribute("indigoSeleccionado");//por si existe
		request.getSession().setAttribute("indigoSeleccionado",id);
		request.setAttribute("destino","actionSape?accion=mantenimientoIndigoFast&operacion=guardarArchivoIndigo");
		
		TipoNodo f = null;
		try {
			f=tipoNodoDAO.getTipoNodo(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			error(e,request,response);
			return;
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		//3. creo el archivo de configuracion y verifico si existe.
		// OJO: los archivos de configuracion de los fast no tienen extension.
		String nameFile = f.getSite();
		//4. obtengo el path donde se encuentran los archivos de configuracion de Indigos y Fast 
		String path = configuracionClienteSape.getRutaArchivosIndigoFast();
		
		File file = new File(path+"/conf_indigo/"+nameFile);
		
		//5. vertifico si existe, si no existe mando por el request una vble que indique esto.
		
		if(!file.exists()){
			request.setAttribute("existeArchivo", "false");
		}
		
		cargarArchivoIndigo();
	}

	
	
	/*
	 * Metodo para mostrar adecuadamente la informacion con respecto a los archivos de
	 * configuracion de un fast.
	 */
	private void seleccionarFast(){
		//1. obtengo el id del fast
		String tel = request.getParameter("telFast");
		//2. guardo el id en la session y en el request (OJO debe ser en los 2)
		request.setAttribute("fastSeleccionado",tel);
		request.getSession().removeAttribute("fastSeleccionado");//por si existe
		request.getSession().setAttribute("fastSeleccionado",tel);
		request.setAttribute("destino","actionSape?accion=mantenimientoIndigoFast&operacion=guardarArchivoFast");
		
		FastEpm f = null;
		try {
			f=fastEpmDAO.getFastPorTelefono(tel);
		} catch (NumberFormatException e) {
			error(e,request,response);
			return;
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		//3. creo el archivo de configuracion y verifico si existe.
		// OJO: los archivos de configuracion de los fast no tienen extension.
		String nameFile = "parf"+f.getNumeroFast();
		//4. obtengo el path donde se encuentran los archivos de configuracion de Indigos y Fast 
		String path = configuracionClienteSape.getRutaArchivosIndigoFast();
		
		File file = new File(path+"/conf_fast/"+nameFile);
		
		//5. vertifico si existe, si no existe mando por el request una vble que indique esto.
		
		if(!file.exists()){
			request.setAttribute("existeArchivo", "false");
		}
		
		cargarArchivoFast();
	}
	
	
	private void cargarArchivoIndigo(){
		List l = null;
		
		try {
			//l = indigoEpmDAO.getAllIndigo();
			l = tipoNodoDAO.getTipoNodosPorTecnologia(9,"F");
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		request.setAttribute("listaIndigo",l);
		redireccionarConPlantilla("cargarArchivoIndigo",request,response);
	}

	
	private void cargarArchivoFast(){
		List l = null;
		
		try {
			l = fastEpmDAO.getAllFast();
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		request.setAttribute("listaFast",l);
		redireccionarConPlantilla("cargarArchivoFast",request,response);
	}
	
	

	private void doModificarIndigo(){
		String id2 = request.getParameter("id");

		String numero  =request.getParameter("numero");
		String ip =request.getParameter("ip");
		String puerto =request.getParameter("puerto");

		int numero2=-1;
		long id = -1;		
		int puerto2=-1;
		try{
			id = Long.parseLong(id2);
			numero2=Integer.parseInt(numero);
			puerto2=Integer.parseInt(puerto);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		
		IndigoEpm f = new IndigoEpm();
		f.setId(new Long(id));
		f.setIp(ip);
		f.setNumeroIndigo(numero);
		f.setPort(puerto);
		
		try {
			indigoEpmDAO.actualizarIndigo(f);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}

		mensaje("Se actualizo el Indigo numero "+f.getNumeroIndigo()+" con exito.","normal",request,response);

	}


	private void doModificarFast(){
		String central = request.getParameter("central");
		String id2 = request.getParameter("cabeza2");
		String id1 = request.getParameter("cabeza1");
		String numero  =request.getParameter("numero");
		String telefono =request.getParameter("telefono");
		String ll =request.getParameter("ll");
		String vertical =request.getParameter("vertical");
		
		
		int numero2=-1;
		int telefono2=-1;
		int id = -1,idd2=-1;
		try{
			id = Integer.parseInt(id1);
			idd2 = Integer.parseInt(id2);
			numero2 = Integer.parseInt(numero);
			telefono2 = Integer.parseInt(telefono);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		//List l=null;

		FastEpm f = new FastEpm();
		
		f.setLl(ll);
		f.setNumeroFast(new Integer(numero2));
		f.setTelefonoFast(telefono);
		f.setVertical(vertical);
		f.setCentral(central);
		f.setId(id);
		f.setId2(idd2);
		try {
			fastEpmDAO.actualizarFast(f);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}

		mensaje("Se actualizo el Fast numero "+f.getNumeroFast()+" con exito.","normal",request,response);

	}

	private void modificarIndigo(){
		String id2 = request.getParameter("id");
		long id =-1;
		try{
			id = Long.parseLong(id2);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;			
		}
		IndigoEpm f=null;
		List l = null;
		try {
			f=indigoEpmDAO.getIndigo(id);
			//l = serieDAO.getAllCentrales();
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("indigo",f);
		//request.setAttribute("listaCentrales",l);
		request.setAttribute("tipoOperacion","modificar");
		redireccionarConPlantilla("nuevoIndigo",request,response);		
	}

	
	private void modificarFast(){
		String tel = request.getParameter("telefono");
		try{
			Long.parseLong(tel);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;			
		}
		FastEpm f=null;
		List l = null;
		try {
			f=fastEpmDAO.getFastPorTelefono(tel);
			l = tipoNodoDAO.getTipoNodosPorTecnologia(9, null);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("fast",f);
		request.setAttribute("listaIndigos",l);
		request.setAttribute("tipoOperacion","modificar");
		redireccionarConPlantilla("nuevoFast",request,response);
		
		
	}
	
	private void doAgregarIndigo(){
		String numero  =request.getParameter("numero");
		String ip =request.getParameter("ip");
		String puerto =request.getParameter("puerto");

		
		int numero2=-1;
		int puerto2=-1;
		try{
			numero2=Integer.parseInt(numero);
			puerto2=Integer.parseInt(puerto);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		List l=null;
		try {
			l=indigoEpmDAO.getIndigoPorNumero(numero);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		if(l!=null&&l.size()>0){
			error("No se puede almacenar este Indigo, el numero de Indigo "+numero+" ya esta siendo utilizado por otro Indigo.",request,response);
			return;
		}
		
		IndigoEpm f = new IndigoEpm();
		f.setIp(ip);
		f.setNumeroIndigo(numero);
		f.setPort(puerto);
		
		try {
			indigoEpmDAO.insertarIndigo(f);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}

		mensaje("Se inserto el Indigo numero "+f.getNumeroIndigo()+" con exito.","normal",request,response);
	}

	
	private void doAgregarFast(){
		String central = request.getParameter("central");
		String numero  = request.getParameter("numero");
		String telefono = request.getParameter("telefono");
		String cabeza1 = request.getParameter("cabeza1");
		String cabeza2 = request.getParameter("cabeza2");
		String ll = request.getParameter("ll");
		String vertical = request.getParameter("vertical");
		
		
		int numero2=-1;
		int telefono2=-1;
		int cabez1 = -1;
		int cabez2 = -1;
		try{
			numero2=Integer.parseInt(numero);
			telefono2=Integer.parseInt(telefono);
			cabez1=Integer.parseInt(cabeza1);
			cabez2=Integer.parseInt(cabeza2);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		List l=null;
		try {
			l=fastEpmDAO.getFastPorNumero(numero2);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		if(l!=null&&l.size()>0){
			error("No se puede almacenar este Fast, el numero de Fast "+numero+" ya esta siendo utilizado por otro Fast.",request,response);
			return;
		}
		
		FastEpm f = new FastEpm();
		f.setId(cabez1);
		f.setId2(cabez2);
		f.setLl(ll);
		f.setNumeroFast(new Integer(numero2));
		f.setTelefonoFast(telefono);
		f.setCentral(central);
		f.setVertical(vertical);
		
		try {
			fastEpmDAO.insertarFast(f);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}

		mensaje("Se inserto el Fast numero "+f.getNumeroFast()+" con exito.","normal",request,response);
	}
	
	private void agregarIndigo(){
		
//		List l = null;
//		
//		try {
//			l = serieDAO.getAllCentrales();
//		} catch (SapeDataException e) {
//			error(e,request,response);
//			return;
//		}
		//request.setAttribute("listaCentrales",l);
		request.setAttribute("tipoOperacion","nuevo");
		redireccionarConPlantilla("nuevoIndigo",request,response);
	}

	private void agregarFast(){
		
		List l = null;
		
		try {
			//l = serieDAO.getAllCentrales();
			l=tipoNodoDAO.getTipoNodosPorTecnologia(9, null);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		request.setAttribute("listaIndigos",l);
		request.setAttribute("tipoOperacion","nuevo");
		redireccionarConPlantilla("nuevoFast",request,response);
	}

	
	private void eliminarFast(){
		String tel1= request.getParameter("id");
		long tel = -1;
		try{
			tel=Long.parseLong(tel1);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		
		try {
			fastEpmDAO.eliminarFast(tel1);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		//request.setAttribute("msg","");
		mensaje("EL Fast fue eliminado con exito.","normal",request,response);
		return;
	}
	
	private void eliminarIndigo(){
		String id2= request.getParameter("id");
		long id = -1;
		try{
			id=Long.parseLong(id2);
		}catch(NumberFormatException e){
			error(e,request,response);
			return;
		}
		
		try {
			indigoEpmDAO.eliminarIndigo(id);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		//request.setAttribute("msg","");
		mensaje("EL Indigo  fue eliminado con exito.","normal",request,response);
		return;
	}
	
	private void inicioFast(){
		List l = null,l2=null;
		
		try {
			l=fastEpmDAO.getAllFast();
			l2 = tipoNodoDAO.getTipoNodosPorTecnologia(9,"F");
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		logs.debug("la lista tiene: "+l.size());
		request.setAttribute("listaFast", l);
		request.setAttribute("listaIndigo", l2);
		redireccionarConPlantilla("Fast", request, response);
		
	}
	
	private void inicioIndigos(){
		List l = null;
		
		try {
			l=indigoEpmDAO.getAllIndigo();
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		//logs.debug("la lista tiene: "+l.size());
		request.setAttribute("listaIndigo", l);
		redireccionarConPlantilla("inicioIndigo", request, response);		
	}
	
	//11-sep-2006 John David Gutierrez
	//Para mostrar central y telefono del fast a probar, ademas de todos los indigos
	//en operacion
	private void pruebaClip(){
		List central = null;
		List fast = null;
		List indigo = null;
		HashMap aux = null;
		FastEpm f = null;
		Iterator it = null;
		
		boolean primero = true;
		
		try {
			//Se le da este nombre porque es para la asignacion de las centrales
			central = fastEpmDAO.getAllFast();
			indigo = tipoNodoDAO.getTipoNodosPorTecnologia(9,"O");
			aux = new HashMap();
			for(int i = 0; i < central.size(); i++){
				f = (FastEpm)central.get(i);
				//Para tomar la primera central y extraer sus fast
				if(primero){
					fast = fastEpmDAO.getFastPorCentral(f.getCentral());
					primero = false;
				}
				if(!aux.containsKey(f.getCentral())){
					aux.put(f.getCentral(),f);
				}
			}
			
			central.clear();
			it = aux.values().iterator();
			while(it.hasNext()){
				central.add(it.next());
			}
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("listaCentral", central);
		request.setAttribute("listaFast", fast);
		request.setAttribute("listaIndigo", indigo);
		redireccionarConPlantilla("pruebaClip", request, response);
	}
	
//	12-sep-2006 John David Gutierrez
	//Para cuando se realiza un cambio de central en la prueba clip
	//17-oct-2006 John David Gutierrez
	//Se realiza una actualizacion para que muestre todos los indigos, y señale
	//los que estan asociados al fast
	private void buscarFastSerie(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	String central = request.getParameter("central");
    	String nuevoIndigo = request.getParameter("indigo");
    	String nuevoFast = request.getParameter("nuevoFast");
    	
    	System.out.println("EL VALOR DE LA CENTRAL ES: " + central);
    	System.out.println("EL VALOR DEL INDIGO ES: " + nuevoIndigo);
    	List fast = null;
    	TipoNodo t = null;
    	List nodos = null;
    	
    	String id = null;
    	String ip = null;
    	String puerto = null;
    	String ipCabeza = null;
    	String puertoCabeza = null;
    	try {
			fast = fastEpmDAO.getFastPorCentral(central);
			nodos = tipoNodoDAO.getTipoNodosPorTecnologia(9,"O");
			System.out.println("Numero de indigos en operacion: " + nodos.size());
			
			//Esta seccion de codigo se encarga de buscar el objeto que posee
			//como site al indigo que se ingresa como parametro
			for(int i = 0; i < nodos.size(); i++){
				t = (TipoNodo)nodos.get(i);
				if(nuevoIndigo.equals(t.getSite())){
					break;
				}
			}
			 
			id = Integer.toString(t.getId());
			ip = t.getIpServidor();
			puerto = t.getPuertoServidor();
			ipCabeza = t.getIpCabeza();
			puertoCabeza = t.getPuertoCabeza();
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		response.setContentType("text/xml");
		response.setHeader("Cache-Control", "no-cache");
		
    	Document documento = new Document();
    	Element root = new Element("central");
    	documento.setRootElement(root);    	

    	Element element;
    	
    	element = new Element("fast");
    	element.setText(Integer.toString(((FastEpm)fast.get(0)).getNumeroFast()));
    	root.addContent(element);
    	
    	element = new Element("telefono");
    	element.setText(((FastEpm)fast.get(0)).getTelefonoFast());
        root.addContent(element);
        
        element = new Element("id");
    	element.setText(id);
        root.addContent(element);
        
        element = new Element("ip");
    	element.setText(ip);
        root.addContent(element);
        
        element = new Element("puerto");
    	element.setText(puerto);
        root.addContent(element);
        
        element = new Element("ipCabeza");
    	element.setText(ipCabeza);
    	root.addContent(element);
    	
    	element = new Element("puertoCabeza");
    	element.setText(puertoCabeza);
    	root.addContent(element);
    
    	PrintWriter out = response.getWriter();
		XMLOutputter serializer = new XMLOutputter();
		serializer.output(documento, out);
				
		out.flush();
		out.close();
    }
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	
}
