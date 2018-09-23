/*
 * Created on Mar 23, 2005
 */
package com.osp.sape.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.ObjectNotFoundException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.gc.acceso.GestorServlet;
import com.gc.data.AplicacionDAO;
import com.gc.data.AplicacionDAO.TipoRastros;
import com.osp.sape.SapeConfiguration;
import com.osp.sape.Exceptions.SapeAppException;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.CabezaPruebaDAO;
import com.osp.sape.data.CodigoVerETBDAO;
import com.osp.sape.data.CodigosVerDAO;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.DistanciaArmariosDAO;
import com.osp.sape.data.PermitidosDAO;
import com.osp.sape.data.ProcesosSapeDAO;
import com.osp.sape.data.SerieDAO;
import com.osp.sape.data.TipoNodoDAO;
import com.osp.sape.data.UsuarioDAO;
import com.osp.sape.maestros.Armarios;
import com.osp.sape.maestros.CPRSiplexPro;
import com.osp.sape.maestros.CabezaPrueba;
import com.osp.sape.maestros.CodigoVer;
import com.osp.sape.maestros.CodigoVerETB;
import com.osp.sape.maestros.Permitidos;
import com.osp.sape.maestros.ProcesosSape;
import com.osp.sape.maestros.Serie;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.servicios.EvaluacionResultados;
import com.osp.sape.servicios.Secuencia;
import com.osp.sape.utils.ConfiguracionClienteSape;
import com.osp.sape.utils.ManejoUmbrales;
import com.osp.sape.utils.ServicioGUDE;



public class MantenimientoServlet extends GestorServlet {

    private UsuarioDAO usuarioDAO;
    private CodigosVerDAO codigosVerDAO;
    private TipoNodoDAO tipoNodoDAO;
    private SerieDAO serieDAO;
    private CabezaPruebaDAO cabezaPruebaDAO;
    private PermitidosDAO permitidosDAO;
    private DistanciaArmariosDAO distanciaArmariosDAO;
    private EvaluacionResultados evaluacionResultados;
    private ProcesosSapeDAO procesosSapeDAO;
    private ManejoUmbrales gudeConfTcl;
    private CodigoVerETBDAO codvETBDAO; 
    private AplicacionDAO aplicacionDAO;
    
    
    public void init() throws ServletException {

        super.init();

        usuarioDAO = DAOFactoryImpl.getInstance().getUsuarioDAO();
        codigosVerDAO = DAOFactoryImpl.getInstance().getCodigosVerDAO();
        tipoNodoDAO = DAOFactoryImpl.getInstance().getTipoNodoDAO();
        serieDAO = DAOFactoryImpl.getInstance().getSerieDAO();
        cabezaPruebaDAO = DAOFactoryImpl.getInstance().getCabezaPruebaDAO();
        permitidosDAO = DAOFactoryImpl.getInstance().getPermitidosDAO();
        distanciaArmariosDAO = DAOFactoryImpl.getInstance().getDistanciaArmariosDAO();
        procesosSapeDAO = DAOFactoryImpl.getInstance().getProcesosSapeDAO();
        codvETBDAO = DAOFactoryImpl.getInstance().getCodigoVerETBDAO();
        aplicacionDAO=DAOFactoryImpl.getInstance().getAplicacionDAO();
    }

    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operacion = request.getParameter("operacion");
        
        operacion = (operacion == null?"":operacion);
        
        UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
        
        
        if(usuario == null){
        	//redireccionar(acciones.getLoginPage(),request,response);
        	error("revisar MantenimientoServlet: el usuario no puede ser null",request,response);
        	return;
        }
        

        String level = usuario.getNivel();
        
        if(level.equals("1")) {
        	if(!operacion.equals("") && !operacion.equals("mantenimientoUsuarios") && !operacion.equals("realizarModificacionUsuario")
        			&& !operacion.equals("telnetInteractivaNoSiplex") && !operacion.equals("telnetInteractivaTipoNodo")
        			&& !operacion.equals("actualizarEstadoTipoNodo")){
        		
        		redireccionar("/"+acciones.getRestrictedPage(),request,response);
        		return;
        	}
        	/*
        	 * Si el usuario de nivel 1 (OPERADOR) ingresa a la vista de usuarios solo le es
        	 * permitido modificar datos acerca de su propia cuenta. Dentro del metodo
        	 * ModificarUsuario se verificara el nivel y si es uno se enviara al jsp de modificar
        	 * con la informacion del propio usuario.
        	 */
        	if(operacion.equals("mantenimientoUsuarios")) {
        		operacionModificarUsuario(request,response);
        		return;
        	}
        }
        
        
        if(logs.isDebugEnabled())logs.debug("doGet: operacion=["+operacion+"]");

        if (operacion == null || operacion.equals("")) {
        		//SE DESPLIEGA PANTALLA DE MANTENIMIENTO GENERAL
            redireccionarConPlantilla("MantenimientoGeneral", request, response);
            return;
        }

        ///////////////////////////////////////////////////////////////////////////////////
        //////////////// MANEJO DE TODAS LAS TRANSACCIONES CON USUARIOS////////////////////

        else if (operacion.equals("mantenimientoUsuarios")) {
            //DESPLIEGA LA LISTA COMPLETA DE USUARIOS
            mantenimientoUsuarios(request, response);
            return;

        } else if (operacion.equals("eliminarUsuario")) {
            //SE SOLICITO ELIMINAR UN USUARIO DE LA BD.

            operacionEliminarUsuario(request, response);
            return;
        } else if (operacion.equals("modificarUsuario")) {
            //SE DESPLIEGA LA INFO DEL USUARIO A MODIFICAR

            operacionModificarUsuario(request, response);
            return;
        } else if (operacion.equals("realizarModificacionUsuario")) {
            //SE RECIBE TODA LA INFORMACION Y SE HACE LA ACTUALIZACION DE LOS
            // DATOS EN LA BD.

            operacionRealizarModificacionUsuario(request, response);
            return;
        } else if (operacion.equals("agregarUsuario")) {
            //SE DESPLIEGA LA FORMA PARA INGRESAR LOS DATOS DE UN NUEVO USUARIO

            request.getRequestDispatcher(
                    acciones.getTemplate("MantenimientoNuevoUsuario")).forward(
                    request, response);
            return;
        } else if (operacion.equals("ejecutarAgregarUsuario")) {
            //SE CREA EL NUEVO USUARIO CON LA INFORMACION DEL JSP
            // MantenimientoNuevoUsuario.jsp

            operacionEjecutarAgregarUsuario(request, response);
            return;
        }

        /////////////////////////////////////////////////////////////////
        ///////////// MANEJO DE TODAS LAS TRANSACCIONES CON CODIGOSVER///

        else if (operacion.equals("mantenimientoCodigosVer")) {
            mantenimientoCodigosVer(request, response);
            return;

        } else if (operacion.equals("modificarCodigoVer")) {
            operacionModificarCodigoVer(request, response);
            return;

        } else if (operacion.equals("realizarModificacionCodigoVer")) {

            guardarModificarCodigoVer(request, response);
            return;
        } else if (operacion.equals("eliminarCodigoVer")) {
            // SE RECIBE EL CODIGO VER Y SE ELIMINA

            eliminarCodigoVer(request, response);
            return;
        } else if (operacion.equals("agregarCodigoVer")) {
             redireccionarConPlantilla("MantenimientoNuevoCodigo", request, response);
            return;
        } else if (operacion.equals("ejecutarAgregarCodigoVer")) {
            guardarNuevoCodigoVer(request, response);
            return;

            ///////////////////////////////////////////////////////////////////
            ///////////// MANEJO DE TODAS LAS TRANSACCIONES CON TIPONODO/////

            //SE REPITE LA SECUENCIA DE ACCIONES PARA TODAS LAS OPCIONES DE
            // MANTENIMIENTO...

        } else if (operacion.equals("actualizarEstadoTipoNodo")){
        	
        	operacionActualizarEstadoTipoNodo(request,response);
            return;
        } else if (operacion.equals("telnetInteractivaNoSiplex")) {
        	
        	operacionTelnetInteractivaNoSiplex(request,response);
        	return;
        } else if (operacion.equals("telnetMantenimientoTipoNodo")){
        	
        	operacionTelnetMantenimientoTipoNodo(request,response);
        	return;
        } else if (operacion.equals("telnetInteractivaTipoNodo")){
        	
        	operacionTelnetInteractivaTipoNodo(request,response);
        	return;
        }else if (operacion.equals("mantenimientoTipoNodo")) {

            mantenimientoTipoNodo(request, response);
            return;
        } else if (operacion.equals("modificarTipoNodo")) {

            modificarTipoNodo(request, response);
            return;
        } else if (operacion.equals("realizarModificacionTipoNodo")) {
            operacionRealizarModificacionTipoNodo(request, response);
            return;
        } else if (operacion.equals("agregarTipoNodo")) {
            nuevoTipoNodo(request, response);
            return;
        } else if (operacion.equals("ejecutarAgregarTipoNodo")) {

            guardarNuevoTipoNodo(request, response);
            return;

            ///////////////////////////////////////////////////////////////////////
            // MANEJO DE TODAS LAS TRANSACCIONES CON SERIES NUMERICAS  ////////////
        } else if (operacion.equals("mantenimientoSeries")) {

            mantenimientoSeries(request, response);
            return;
        } else if (operacion.equals("eliminarSerie")) {

            eliminarSerie(request, response);
            return;

        } else if (operacion.equals("modificarSerie")) {
            
            operacionModificarSerie(request, response);
            return;

        } else if (operacion.equals("realizarModificacionSerie")) {

            guardarModificarSerie(request, response);
            return;
        } else if (operacion.equals("agregarSerie")) {
            
            List listaCodigosCentral = null;
            try {
                listaCodigosCentral = tipoNodoDAO.getListaCabezasId();
                System.out.println("tamano de la lista: "+listaCodigosCentral.size());
            } catch (SapeDataException e) {
                error(e,request,response);
                return;
            }
            request.setAttribute("listaCodigosCentral",listaCodigosCentral);
            request.getRequestDispatcher(acciones.getTemplate("MantenimientoNuevoSerie"))
                    .forward(request, response);
            return;
        } else if (operacion.equals("ejecutarAgregarSerie")) {

            guardarNuevaSerie(request, response);
            return;

            ////////////////////////////////////////////////////////////////////////
            /// MANEJO DE TODAS LAS TRANSACCIONES CON
            // CABEZAPRUEBA//////////////////

        } else if (operacion.equals("mantenimientoCabezaPrueba")) {

            mantenimientoCabezaPrueba(request, response);
            return;
        } else if (operacion.equals("eliminarCabezaPrueba")) {

            operacionEliminarCabezaPrueba(request, response);
            return;
        } else if (operacion.equals("modificarCabezaPrueba")) {

            operacionModificarCabezaPrueba(request, response);
            return;
        } else if (operacion.equals("realizarModificacionCabezaPrueba")) {

            operacionRealizarModificacionCabezaPrueba(request, response);
            return;
        } else if (operacion.equals("agregarCabezaPrueba")) {
        	request.getRequestDispatcher(acciones.getTemplate("MantenimientoNuevoCabezaPrueba")).forward(request, response);
            return;
        } else if (operacion.equals("ejecutarAgregarCabezaPrueba")) {

            operacionEjecutarAgregarCabezaPrueba(request, response);
            return;

            //////////////////////////////////////////////////////////////////
            /// MANEJO DE TODAS LAS TRANSACCIONES CON PERMITIDOS//////////////
        } else if (operacion.equals("mantenimientoPermitidos")) {

            mantenimientoPermitidos(request, response);
            return;
        } else if (operacion.equals("eliminarPermitidos")) {

            operacionEliminarPermitidos(request, response);
            return;
        } else if (operacion.equals("modificarPermitidos")) {

            operacionModificarPermitidos(request, response);
            return;
        } else if (operacion.equals("realizarModificacionPermitidos")) {

            operacionRealizarModificacionPermitidos(request, response);
            return;
        } else if (operacion.equals("agregarPermitidos")) {

            request.getRequestDispatcher(acciones.getTemplate("MantenimientoNuevoPermitidos"))
                    .forward(request, response);
            return;
        } else if (operacion.equals("ejecutarAgregarPermitidos")) {

            operacionEjecutarAgregarPermitidos(request, response);
            return;

            /// MANEJO DE TODAS LAS TRANSACCIONES CON PERMITIDOS//////////////
            //////////////////////////////////////////////////////////////////


        } else if (operacion.equals("mantenimientoBasedeDatos")) {
            request.setAttribute("fIni", getFechaHoy());
            request.setAttribute("fFin", getFechaHoy());
        	redireccionarConPlantilla("MantenimientoBasedeDatos", request, response);
            return;
        } else if (operacion.equals("Umbrales")) {
            mantenimientoUmbrales(request, response);
            return;
        } else if (operacion.equals("telnet")) {

            operacionTelnet(request, response);
            return;
        }else if(operacion.equals("evaluacionResultados")){
        	
        	List l =null;
        	try {
    			evaluacionResultados = new EvaluacionResultados(new File(SapeConfiguration.getInstance().getRutaEvalResultados()));
				l=evaluacionResultados.getAllSecuencias();
			} catch (SapeAppException e) {
				//e.printStackTrace();
				error(e,request,response);
				return;
			}
			request.setAttribute("listaSecuencias",l);		
			redireccionarConPlantilla("evaluacionResultados",request,response);
			
            return;
        }else if(operacion.equals("guardarPlantillaEvaluacion")){
        	//Se va a actualizar el xml de evaluacionResultados!!!!!!
        	
        	operacionGuardarPlantillaEvaluacion(request,response);
        	return;
        } else if (operacion.equals("mantenimientoArmarios")){
        	
        	operacionMantenimientoArmarios(request,response);
        	return;
        } else if(operacion.equals("mantenimientoBuscarArmario")){
        	
        	operacionMantenimientoBuscarArmario(request,response);
        	return;
        } else if(operacion.equals("agregarArmario")){
        	
        	redireccionarConPlantilla("nuevoArmario",request,response);
        	return;
        } else if(operacion.equals("doAgregarArmario")){
        	
        	doAgregarArmario(request,response);
        	return;
        } else if(operacion.equals("eliminarArmario")){
        	eliminarArmario(request,response);
        	return;
        }else if (operacion.equals("mantenimientoProcesos")){
        	operacionMantenimientoProcesos(request,response);
        	return;
        } else if (operacion.equals("ejecutarAccionProcesosSape")){
        	
        	operacionEjecutarAccionProcesosSape(request,response);
        	return;
        } else if(operacion.equals("modificarProceso")){
        	
        	operacionModificarProceso(request,response);
        	return;
        } else if(operacion.equals("realizarModificacionProceso")){
        	operacionRealizarModificacionProceso(request,response);
        	return;
        }
        
        if (operacion.equals("mantenimientoCPRS")) {
        	redireccionarConPlantilla("MantenimientoCprs", request, response);
        	return;
        }
        if (operacion.equals("buscarCPR")) {
        	buscarCPR(request, response);
        	return;
        } 
        if (operacion.equals("actualizarCPR")) {
        	actualizarCPR(request, response);
        	return;
        }
        if (operacion.equals("umbralesSAPE")) {
        	gudeConfTcl = new ManejoUmbrales(new File(SapeConfiguration.getInstance().getRutaArchivoUmbrales()));
        	List l = gudeConfTcl.getNameFields();
        	Properties p = gudeConfTcl.getProperties();
        	
        	request.setAttribute("nombresPropiedades",l);
        	request.setAttribute("propiedades",p);
        	
        	redireccionarConPlantilla("umbralesSAPE",request,response);
        	return;
        }
        if (operacion.equals("guardarPlantillaGudeConf")){
        	
        	operacionGuardarPlantillaGudeConf(request,response);
        	return;
        }
        
        if(operacion.equals("mantenimientoSiplexPRO")){
        	redireccionarConPlantilla("mantenimientoSiplexPRO",request,response);
        	return;
        }
        
        if(operacion.equals("actualizarCPRSerie")){	
        	operacionActualizarCPRSerie(request,response);
        	return;
        }
        
        if(operacion.equals("guardarSeriePrueba")){	
        	operacionGuardarSeriePrueba(request,response);
        	return;
        }
        
        if (operacion.equals("desbloqueoSiplexPRO")) {
        	desbloqueoSiplexPRO(request, response);
        	return;
        }
        
        if(operacion.equals("contingenciaSiplexPRO")){
        	
            List l;
            String hostCyclades="";
			try {
				l = tipoNodoDAO.getTipoNodosPorTecnologia("SIPLEXPRO");
				hostCyclades=ConfiguracionClienteSape.getInstance().getHostCyclades();
			} catch (SapeDataException e) {
				error(e,request,response);
				return;
			}
            request.setAttribute("listaTipoNodo", l);
            request.setAttribute("hostCyclades",hostCyclades);
        	redireccionarConPlantilla("contingenciaSiplexPRO",request,response);
        	return;
        }
        
        if(operacion.equals("contingenciaSiplexPROCambiar")){
        	
        	operacionContingenciaSiplexPROCambiar(request,response);
        	return;
        }
        
        if(operacion.equals("codvETB")){
        	operacionCodvETB(request,response);
        	return;
        }
        
        if(operacion.equals("agregarCodigoETB")){
        	guardarCodigoETB(request,response);
        	return;
        }
        
        if(operacion.equals("eliminarCodvETB")){
        	eliminarCodvETB(request,response);
        	return;
        }
        
        
        //En caso que pase por aqui muestro un mensaje de error
        error("La operacion " + operacion + " no esta definida.", request, response);
        
    }

    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String operacion = request.getParameter("operacion");
    	if (operacion == null) operacion = "";
    	
        if (operacion.equals("eliminarVisitas")) {
        	eliminarRastrosVisitas("visitas", request, response);
        	return;
        }
        if (operacion.equals("eliminarRastros")) {
        	eliminarRastrosVisitas("rastros", request, response);
        	return;
        }
    	
    		//cuando ya no hay opciones por doPost lo lleva a doGet
    	doGet(request, response);
    }


    
    private void eliminarArmario(HttpServletRequest request, HttpServletResponse response) {
    	String armario = request.getParameter("armario");
    	
    	if(armario == null || armario.equals("")){
    		error("Faltan parametros - 'armario'",request,response);
    		return;
    	}
    	
    	try {
			distanciaArmariosDAO.eliminarDistanciaArmario(armario);
		} catch (SapeDataException e) {
			
			if(e.getCause() instanceof ObjectNotFoundException){
				error("El armario no existe.",request,response);
			}else{
				error(e,request,response);
			}
			return;
		}
		
		mensaje("Se elimino el armario satisfactoriamente",null,request,response);
    	
    }
    
    private void doAgregarArmario(HttpServletRequest request, HttpServletResponse response){
    	
    	String armario = request.getParameter("armario");
    	String distancia = request.getParameter("distancia");
    	
    	Armarios arm = new Armarios();
    	
    	arm.setArmario(armario);
    	arm.setDistancia(distancia);
    	try {
			distanciaArmariosDAO.insertarDistanciaArmario(arm);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		mensaje("Se inserto el armario satisfactoriamente.",null,request,response);
    }
    
    private void eliminarCodvETB(HttpServletRequest request, HttpServletResponse response) {
    	
    	String codv = request.getParameter("codvSAPE");
    	
    	try {
			CodigoVerETB codOld = codvETBDAO.getCodvETB(codv);
    		codvETBDAO.eliminarCodvETB(codv);
			registrarRastro(request, TipoRastros.ELIMINACION, codOld, null);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("msg","La relacion de codigos se borro exitosamente.");
		operacionCodvETB(request,response);
    }
    
    private void guardarCodigoETB(HttpServletRequest request, HttpServletResponse response) {
    	if(debug) logs.debug("guardarCodigoETB");
    	
    	String codvETB = request.getParameter("codvETB");
    	String codvSAPE= request.getParameter("codvSAPE");
    	
    	CodigoVerETB cetb = new CodigoVerETB();
    	cetb.setCodvETB(codvETB);
    	cetb.setCodvSAPE(codvSAPE);
    	
    	try {
			codvETBDAO.actualizarObjetoETB(cetb);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("msg","Se almaceno la nueva relacion de codigos con exito.");
		operacionCodvETB(request,response);
    	
    }
    
    
    private void operacionCodvETB(HttpServletRequest request, HttpServletResponse response) {
    	
    	List<CodigoVerETB> listaCodigos = null;
    	List listaCodigosSape = null;
    	try {
			listaCodigos=codvETBDAO.getAllCodvETB();
			listaCodigosSape = codigosVerDAO.getCodigoVerNoETB();
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		

		request.setAttribute("listaCodvETB",listaCodigos);
		request.setAttribute("listaCodvSAPE",listaCodigosSape);
		
		redireccionarConPlantilla("codigosVerETB",request,response);
    }
    
    
    private void operacionContingenciaSiplexPROCambiar(HttpServletRequest request,
    		HttpServletResponse response) {
    	
    	String tNodos = request.getParameter("tipoNodos");
    	
		UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
    	
    	List tipoNodos = null;

		try {
    	
			if(tNodos.equals("todos")){
//				 TENGO UNA LISTA CON OBJETOS TIPONODO !!!!!
				tipoNodos = tipoNodoDAO.getTipoNodosPorTecnologia("SIPLEXPRO");

			} else {
				// TENGO UNA LISTA DE STRINGS CON LOS ID DE LOS TIPONODOS SELECCIONADOS
				StringTokenizer stt = new StringTokenizer(tNodos,",");
				tipoNodos = new ArrayList();
				int i = 0;
				while(stt.hasMoreTokens()){
					
					try{
						int tip = Integer.parseInt(stt.nextToken());
						tipoNodos.add(i,tipoNodoDAO.getTipoNodo(tip));
					}catch(NumberFormatException e){
						error(e,request,response);
						return;
					}
					i++;
				}
			}
			tipoNodoDAO.cambiarMetodoConexionSiplexPRO(tipoNodos,usuario);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		String mensaje = "Se actualizara el metodo de conexion de "+(tNodos.equals("todos")?"todas las cabezas SiplexPRO":"las cabezas con id: "+tNodos)+".";
        request.setAttribute("mensaje",mensaje);
        redireccionarConPlantilla( "MantenimientoMensaje",request,response);
    }
    
    
    /**
     * Esta es para la pantalla de actualizarCPRSERIE de los siplexpro.
     * @param request
     * @param response
     * @throws IOException
     */
    private void operacionGuardarSeriePrueba(HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	
    	String idd = request.getParameter("id");
    	String enabled = request.getParameter("enabled");
    	int id = -1;
    	
    	try{
    		id = Integer.parseInt(idd);
    	}catch(NumberFormatException e){
    		error(e,request,response);
    		return;
    	}
    	
    	Serie s = null;
    	try {
			s= serieDAO.getSerie(id);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		enabled = (enabled.equals("false")?"#":"");
		File f = new File("/opt/gude/sh/actualizarCPRSerie.sh");
		///opt/gude/sqltcl/SiplexPRO/actualizarCPRS.tcl
		RandomAccessFile raf = new RandomAccessFile(f,"rw");
		raf.setLength(0);
		if(s != null)
			raf.write((enabled+"/opt/gude/sqltcl/SiplexPRO/actualizarCPRS.tcl "+s.getCentral()+" "+s.getSerieInicial()+" "+s.getSerieFinal()+" > /opt/logs/actualizarCPRSerie.log 2>&1 &").getBytes());
		else
			raf.write(("/opt/gude/sqltcl/SiplexPRO/actualizarCPRS.tcl").getBytes());
		
		raf.close();
        request.setAttribute("mensaje","Se actualizo el archivo satisfactoriamente.");
        redireccionarConPlantilla( "MantenimientoMensaje",request,response);
		
    }
            
    private void operacionActualizarCPRSerie(HttpServletRequest request,
    		HttpServletResponse response) throws IOException {
    	
    	List listaCentrales = null;
    	
		String central = request.getParameter("central");
		
		if(central != null && !central.equals("")){
			List l = null;
			try {
				l = serieDAO.buscarSeriesPorCentralTecnologia(central,"SIPLEXPRO-CPR");
			} catch (SapeDataException e) {
				error(e,request,response);
				return;
			}
			
			
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			
	    	Document documento = new Document();
	    	Element root = new Element("series");
	    	documento.setRootElement(root);
	    	
	    	Element element = null;
	    	
	    	Element ePruebas = new Element("pruebas");
	    	
	    	for (int i = 0; i < l.size(); i++) {
				
	    		String tels = ((Serie)l.get(i)).getSerieInicial()+" - "+((Serie)l.get(i)).getSerieFinal();
	    		String id = String.valueOf(((Serie)l.get(i)).getId());
	    		
	    		//System.out.println("tels="+tels+" --- id="+id);
	    		
	    		element = new Element("option");
				Element e = new Element("serie");
				e.setText(tels);
				element.addContent(e);
				e = new Element("serie");
				e.setText(id);
				element.addContent(e);
				ePruebas.addContent(element);
			}
	    	root.addContent(ePruebas);
	        
	    	PrintWriter out = response.getWriter();
			XMLOutputter serializer = new XMLOutputter();
			serializer.output(documento, out);
			out.flush();	
			out.close();
			
			return;
		}
		
    	
    	try {
			listaCentrales = serieDAO.getCentralesPorTecnologia("SIPLEXPRO-CPR");
			
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
    	
    	request.setAttribute("listaCentrales",listaCentrales);
    	redireccionarConPlantilla("actualizarCPRSerie",request,response);
    }
    
    private void operacionGuardarPlantillaGudeConf(HttpServletRequest request,
    		HttpServletResponse response) {
    	gudeConfTcl = new ManejoUmbrales(new File(SapeConfiguration.getInstance().getRutaArchivoUmbrales()));
    	
    	
    	String nombres = request.getParameter("nombres");
//    	String comentario = request.getParameter("comentario");
    	
    	StringTokenizer stt = new StringTokenizer(nombres,",");
    	
    	Properties p = gudeConfTcl.getProperties();
    	
    	while(stt.hasMoreTokens()){
    		
    		String name = stt.nextToken();
    		String value = request.getParameter(name);
    		p.setProperty(name,value);
    		
    	}
    	
    	
    	try {
			gudeConfTcl.setProperties(p);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
        request.setAttribute("mensaje","Se actualizaron los umbrales satisfactoriamente.");
        redireccionarConPlantilla( "MantenimientoMensaje",request,response);
		
    }
    
    /*
     *  METODO VIEJO!!!!!!
     *     private void operacionGuardarPlantillaGudeConf(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	gudeConfTcl = new ManejoUmbrales(new File(SapeConfiguration.getInstance().getRutaArchivoUmbrales()));
    	List l = gudeConfTcl.getNameFields();
    	int size = l.size();
    		
    	Properties p = new Properties();
    	for (int i = 0; i < size; i++) {
    		
    		String name = (String) l.get(i);
    		String value = request.getParameter(name);
    		p.setProperty(name,value);
    	}
    	
    	try {
			gudeConfTcl.setProperties(p);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
        request.setAttribute("mensaje","Se actualizaron los umbrales satisfactoriamente.");
        redireccionarConPlantilla( "MantenimientoMensaje",request,response);
		
    }
    
     * 
     */
    
    
    
    
    
    
    private void operacionRealizarModificacionProceso(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	
    	String id=request.getParameter("id");
    	String nombre=request.getParameter("nombre");
    	String comando=request.getParameter("comando");
    	String expresion=request.getParameter("expresion");
    	String host=request.getParameter("host");
    	String activo=request.getParameter("activo");
    	
    	ProcesosSape p = null;
    	
    	
    	if(comando.indexOf("AMPERSANT") != -1)
    		comando = comando.replaceAll("AMPERSANT","&");
    	
    	try {
			p = procesosSapeDAO.getProcesosSape(Integer.parseInt(id));
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		p.setActivo(activo);
		p.setComando(comando);
		p.setExpresion_verificar(expresion);
		p.setHost(host);
		p.setNombre(nombre);
		
		try {
			procesosSapeDAO.actualizarProcesosSape(p);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("mensaje","Se actualizo el Proceso Satisfactoriamente");
		request.setAttribute("tipo","popup");
		request.getRequestDispatcher(acciones.getTemplate("MantenimientoMensaje")).forward(request,response);
		
    }
    
    private void operacionModificarProceso(HttpServletRequest request,
    		HttpServletResponse response) {
    	
    	String idProcess = request.getParameter("idProceso");
    	int id=0;
    	try{
    		id=Integer.parseInt(idProcess);
    	}catch(NumberFormatException e){error(e,request,response);return;}
    	
    	ProcesosSape ps = null;
    	
    	try {
			ps=procesosSapeDAO.getProcesosSape(id);
    	}catch (SapeDataException e) {
    		error(e,request,response);
    		return;
		}
    	
    	request.setAttribute("proceso",ps);
    	redireccionarConPlantilla("modificarProceso",request,response);
    }
    
    private void operacionEjecutarAccionProcesosSape(HttpServletRequest request,
    		HttpServletResponse response) {
    	
    	String id = request.getParameter("id");
    	String query = request.getParameter("query");
    	
    	String nickName = ((UserSipe)request.getSession().getAttribute("usuario")).getNick();
    	
    	if(nickName == null){
    		redireccionar(acciones.getLoginPage(),request,response);
    		return;
    	}
    	String msg = "";
    	try {
			msg=procesosSapeDAO.ejecutarAccion(id,query,nickName);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
    	
		request.setAttribute("msg",msg);
		operacionMantenimientoProcesos(request,response);
    }
    
    
    
    
    private void operacionMantenimientoProcesos(HttpServletRequest request,	HttpServletResponse response) {
    	if (logs.isDebugEnabled()) logs.debug("operacionMantenimientoProcesos");
    	List l = null;
    	
    	String orderBy = request.getParameter("orderBy");
    	
    	if(orderBy == null || orderBy.equals(""))
    		orderBy = "id DESC";
    	
    	String filtro = request.getParameter("filtro");
    	String valorFiltro = request.getParameter("valorFiltro");
    	
    	if(filtro == null || filtro.equals("") || filtro.equals("ninguno")){
    		valorFiltro = "";
    		filtro = "";
    	}
    	
    	try{
    		l = procesosSapeDAO.getProcesosSape(orderBy,filtro,valorFiltro);
    	}catch(SapeDataException e){
    		error(e,request,response);
    		return;
    	}
    	
    	request.setAttribute("filtro",filtro);
    	request.setAttribute("valorFiltro",valorFiltro);
    	
    	request.setAttribute("query","&filtro="+filtro+"&valorFiltro="+valorFiltro);
    	
    	request.setAttribute("orderBy",orderBy);
    	request.setAttribute("listaProcesos",l);
    	
    	redireccionarConPlantilla("pantallaProcesosSape",request,response);
    	
    }
    
    private void operacionMantenimientoBuscarArmario(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	
    	String armario = request.getParameter("armario");
    	
    	if(armario == null || armario.equals("")){
    		operacionMantenimientoArmarios(request,response);
    		return;
    	}
    	List l = new ArrayList();
    	
    	try {
			l = distanciaArmariosDAO.getDistanciaArmarioLike(armario);
		} catch (SapeDataException e) {
			
			if(e.getCause() instanceof ObjectNotFoundException){
				error("No se Encontraron registros para el armario: "+armario+".",request,response);
				return;
			}
			
			error(e,request,response);
			return;
		}
		
		request.setAttribute("listaEventos",l);
		
		request.getRequestDispatcher(acciones.getTemplate("mantenimientoArmarios")).forward(request,response);
    }
    
    private void operacionMantenimientoArmarios(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    
    	List l = null;
    	
    	String orderBy = request.getParameter("orderBy");
    	
    	if(orderBy == null || orderBy.equals(""))
    		orderBy = "armario";
    	
    	String regXpag = request.getParameter("regPorPagina");
    	
    	if(regXpag == null || regXpag.equals(""))
    		regXpag = "100";
    	
    	String pagActual = request.getParameter("pagActual");
    	
    	if(pagActual == null || pagActual.equals(""))
    		pagActual = "1";
    	
    	String offset = "0";
    	
    	if(!pagActual.equals("1"))
    		offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regXpag));
    	
		try {
			l = distanciaArmariosDAO.getDistanciaArmario(orderBy,regXpag,offset);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		
        double division = Double.parseDouble((String)l.get(0))/Double.parseDouble(regXpag);
//      System.out.println(" la division da: " + division + ". Ceil: " + Math.ceil(division) + ". toInt " + (int) Math.ceil(division));
        int totalPaginas = (int)Math.ceil(division);
        if(totalPaginas <=1) totalPaginas = 0;
		
		
    	request.setAttribute("listaEventos",l.get(1));
    	request.setAttribute("orderBy",orderBy);
    	request.setAttribute("regPorPagina",regXpag);
    	request.setAttribute("pagActual",pagActual);
    	request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
    	request.getRequestDispatcher(acciones.getTemplate("mantenimientoArmarios")).forward(request,response);
    	
    }

    private void operacionGuardarPlantillaEvaluacion(HttpServletRequest request,
    		HttpServletResponse response) {
    	List listaSecuencias = new ArrayList();
    	
    	int k = 6;  // OJO: variable para llevar un control de la cantidad de codigos ver a evaluar
    				// No borrar.
    	
    	String usuarioFenix = request.getParameter("usuarioFenix");
    	
    	String colaError = request.getParameter("colaError");
    	String codigoError = request.getParameter("codigoError");
    	
    	String clienteRepetido = request.getParameter("clienteRepetido");
    	String siCola = request.getParameter("siCola");
    	String siCodigo = request.getParameter("siCodigo");
		String noCodigo = null;
		String noUsuario = null;
    	
    	String colaDia = request.getParameter("colaDia");
    	String colaNoche = request.getParameter("colaNoche");
    	String codigoNoche = request.getParameter("codigoNoche");
    	String codigoDia = request.getParameter("codigoDia");
    	String horaNoche = request.getParameter("horaNoche");
    	
    	String colaND = request.getParameter("colaND");
    	String codigoND = request.getParameter("codigoND");
    	
    	for (int i=0;i<28+k;i++) {
    		
    		String cola = request.getParameter("cola"+i);
    		String codv = request.getParameter("codv"+i);
    		String codigo = request.getParameter("codigo"+i);
    		
    		Secuencia sec = new Secuencia(codv,cola,codigo,i);
    		listaSecuencias.add(i,sec);
    	}
		
    	Secuencia secc = new Secuencia(clienteRepetido,siCola,siCodigo,noCodigo,noUsuario,28+k);
		listaSecuencias.add(28+k,secc);
    	
		listaSecuencias.add(29+k,usuarioFenix);
		
		secc = new Secuencia(null,colaError,codigoError,30+k);
	    
		listaSecuencias.add(30+k,secc);
    	
		
		secc = new Secuencia(31+k,colaDia, codigoDia, colaNoche, codigoNoche, horaNoche);
		
		listaSecuencias.add(31+k,secc);
		
		secc = new Secuencia(null,colaND,codigoND,32+k);
		
		listaSecuencias.add(32+k,secc);
    	 
    	try {
			evaluacionResultados = new EvaluacionResultados(new File(SapeConfiguration.getInstance().getRutaEvalResultados()));
			evaluacionResultados.actualizarSecuencias(listaSecuencias);
			ServicioGUDE s= new ServicioGUDE();
			
			String cmd[]= new String[5];
			cmd[0]=getUsuarioSession(request);
			cmd[1]="14";
			cmd[2]="correo";
			cmd[3]="ALARMA";
			cmd[4]="El usuario "+cmd[0]+" modifico el archivo de configuracion evaluacion de Resultados. Tiempo: "+(new Date());
			s.ejecutarServicio(cmd);
		} catch (SapeAppException e) {
			error(e,request,response);
			return;
		} catch (IOException e) {
			error(e,request,response);
			return;
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		request.setAttribute("mensaje","<font size='+1'>La actualizacion de la informacion se realizo con EXITO."+
				"<br>Para que los cambios surtan efecto debe volver a iniciar los</font>"+
				"<br> <font size='+2'> <b> SERVICIO DE PRUEBAS AUTOMATICAS </b></font>");
		redireccionarConPlantilla("MantenimientoMensaje",request,response);

		//request.getRequestDispatcher(acciones.getTemplate("MantenimientoMensaje")).forward(request,response);
		
    	/*
    	// esta variable es para guardar en la lista de secuencias
    	//y para controlar la secuencia 10 que desde el 27 de agosto se suprimio.
    	int save = 0;
    	for(int i = 0; i < 14;i++){
    		
    		
    		if(i == 9)
    			continue;
    		String cadena = request.getParameter("sec"+(i+1));
    		
    		
			StringTokenizer st = new StringTokenizer(cadena,"**");
			int size = st.countTokens();
			String condicion = st.nextToken();
			List ll = new ArrayList();
			int k =0;
			//System.out.println("[i="+i+"]-[secuencia="+(i+1)+"]");
			//System.out.println("[cadena='"+cadena+"']-[tokens="+size+"]");
			int resto=0;
			
			if (i == 8 || i==9) {
				resto=5;
			} else {
				resto = 2;
			}
			
			for(int j=1;j<size-resto;j++){
				String val = st.nextToken();
				ll.add(k,val);
				k++;
			}
			
			Secuencia sec = null;
			
			if(i == 8 || i==9){

				String colaSi = st.nextToken();
				String colaNo = st.nextToken();
				String codSi = st.nextToken();
				String codNo = st.nextToken();
				String usuario = st.nextToken();
				
				
				String valueExtra=(String)ll.remove(ll.size()-1);
				
				sec = new Secuencia((i+1),condicion,ll,"",valueExtra,colaSi,colaNo,codSi,codNo,usuario,"");
			}else{
				String col = st.nextToken();
				String cod = st.nextToken();
				sec = new Secuencia((i+1),condicion,ll,"",col,cod);
			}
			
			//XXX Ojo, las secuencias se estan guardando sin unidad de medida y sin condiciones!!!!
			listaSecuencias.add(save,sec);
			save++;
    	}
    	try {
			evaluacionResultados.actualizarSecuencias(listaSecuencias);
		} catch (SapeAppException e) {
			e.printStackTrace();
			error(e,request,response);
			return;
		}
    	
		request.setAttribute("mensaje","<font size='+1'>La actualizacion de la informacion se realizo con EXITO."+
				"<br>Para que los cambios surtan efecto debe volver a iniciar los</font>"+
				"<br> <font size='+2'> <b> SERVICIO DE PRUEBAS AUTOMATICAS </b></font>");
		request.getRequestDispatcher(acciones.getTemplate("MantenimientoMensaje")).forward(request,response);
		return;*/
    }
    
    
    private void operacionTelnet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        request.setAttribute("ip", ip);
        request.setAttribute("port", port);
        request.getRequestDispatcher(acciones.getTemplate("telnet")).forward(request, response);
        return;
    }

    private void mantenimientoUmbrales(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        Vector v;
        try {
            v = serieDAO.getUmbrales();
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        
        request.setAttribute("reportes", v);
        
        String exportar = request.getParameter("exportar");
        if(exportar != null && exportar.equals("yes")){
        	request.getRequestDispatcher("/actionSape?accion=reportes&operacion=exportarInforme&pantalla=umbrales").forward(
                    request, response);
        	return;
        }
        request.getRequestDispatcher(acciones.getTemplate("Umbrales")).forward(
                request, response);
        return;
    }

    private void operacionEjecutarAgregarPermitidos(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String id2 = request.getParameter("ip");
        String cliente = request.getParameter("cliente");

        Permitidos cp = null;

        try {
            cp = permitidosDAO.getPermitidos(id2);
        } catch (SapeDataException e) {
            cp = null;
        }
        if (cp != null) {
            error("ya existe un registro con el ip = " + id2
                    + "\nIntente con un ip distinto.", request, response);
            return;
        }

        cp = new Permitidos();
        cp.setIp(id2);
        cp.setCliente(cliente);

        try {
            permitidosDAO.insertarPermitidos(cp);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje",
                "El ip fue guardado satisfactoriamente.");
        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoMensaje")).forward(request,
                response);
        return;
    }

    private void operacionRealizarModificacionPermitidos(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id1 = request.getParameter("ip_anterior");
        String id2 = request.getParameter("ip");
        String cliente = request.getParameter("cliente");

        Permitidos cp = null;

        try {
            cp = permitidosDAO.getPermitidos(id2);
        } catch (SapeDataException e) {
            cp = null;
        }
        if (cp != null && id1.equals(id2) == false) {
            error("ya existe un registro con el ip = " + id2
                    + "\nIntente con un ip distinto.", request, response);

            return;
        }

        cp = new Permitidos();
        cp.setIp(id2);
        cp.setCliente(cliente);

        try {
            permitidosDAO.eliminarPermitidos(id1);
            permitidosDAO.insertarPermitidos(cp);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje",
                "El ip fue modificado satisfactoriamente.");
        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoMensaje")).forward(request,
                response);
        return;
    }

    private void operacionModificarPermitidos(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String id2 = request.getParameter("ip");

        Permitidos us = null;
        try {
            us = permitidosDAO.getPermitidos(id2);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("permitidos", us);
        request.getRequestDispatcher(acciones.getTemplate("MantenimientoModificarPermitidos"))
                .forward(request, response);
        return;
    }

    private void operacionEliminarPermitidos(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String id2 = request.getParameter("ip");

        try {

            permitidosDAO.eliminarPermitidos(id2);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje",
                "El ip fue eliminado satisfactoriamente.");
        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoMensaje")).forward(request,
                response);
        return;
    }

    private void mantenimientoPermitidos(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {
            List l = permitidosDAO.getAllPermitidos();
            request.setAttribute("listaPermitidos", l);
            request.getRequestDispatcher(
                    acciones.getTemplate("MantenimientoPermitidos")).forward(
                    request, response);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
    }

    private void operacionEjecutarAgregarCabezaPrueba(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String proveedor = request.getParameter("proveedor");

        CabezaPrueba cp = new CabezaPrueba();
        cp.setNombre(nombre);
        cp.setProveedor(proveedor);

        try {
            cabezaPruebaDAO.insertarCabezaPrueba(cp);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje",
                "La Clase fue guardada satisfactoriamente.");

        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoMensaje")).forward(request,
                response);
        return;
    }

    private void operacionRealizarModificacionCabezaPrueba(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id2 = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        String nombre = request.getParameter("nombre");
        String proveedor = request.getParameter("proveedor");

        CabezaPrueba cp = null;

        try {
            cp = cabezaPruebaDAO.getCabezaPrueba(id);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }

        cp.setNombre(nombre);
        cp.setProveedor(proveedor);

        try {
            cabezaPruebaDAO.actualizarCabezaPrueba(cp);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje",
                "La Clase de Nodo fue modificada satisfactoriamente.");
        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoMensaje")).forward(request,
                response);
        return;

    }

    private void operacionModificarCabezaPrueba(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String id2 = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        CabezaPrueba us = null;
        try {
            us = cabezaPruebaDAO.getCabezaPrueba(id);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("cabezaPrueba", us);
        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoModificarCabezaPrueba"))
                .forward(request, response);
        return;

    }

    private void operacionEliminarCabezaPrueba(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String id2 = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        try {//ELIMINAR FUNCIONANDO!!!!

            cabezaPruebaDAO.eliminarCabezaPrueba(id);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje",
                "La Clase de Nodo fue eliminada satisfactoriamente.");
        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoMensaje")).forward(request,
                response);
        return;
    }

    private void mantenimientoCabezaPrueba(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {
            List l = cabezaPruebaDAO.getAllCabezaPrueba();
            request.setAttribute("listaCabezaPrueba", l);
            
            String exportar = request.getParameter("exportar");
            if ( exportar != null && exportar.equals("yes") ) {
            	request.getRequestDispatcher("/actionSape?accion=reportes&operacion=exportarInforme&pantalla=cabezaPrueba").forward(
                        request, response);
            	return;
            }
            
            request.getRequestDispatcher(
                    acciones.getTemplate("MantenimientoCabezaPrueba")).forward(
                    request, response);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }

    }

    
    private void guardarNuevaSerie(HttpServletRequest request, HttpServletResponse response) {

        String inicial = request.getParameter("inicial");
        String FINAL = request.getParameter("FINAL");
        String cabezaid = request.getParameter("cabezaid");
        String central = request.getParameter("central");
        String tipocentral = request.getParameter("tipocentral");
        String codCentral = request.getParameter("codCentral");
        Serie se = new Serie();
 
        se.setCentral(central);
        se.setCabezaId(new Integer(cabezaid));
        try {
        	Long.parseLong(inicial);
        	Long.parseLong(FINAL);
        	Long.parseLong(codCentral);
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        se.setSerieFinal(Long.parseLong(FINAL));
        se.setSerieInicial(Long.parseLong(inicial));
        se.setTipocentral(tipocentral);
        se.setCodCentral(new Integer(codCentral));

        try {	
        			//busco que la serie no se monte con alguna existente
        	String mensaje = "La serie entra en conflicto con otra serie existente. <br>";
        	Serie s = serieDAO.buscarSerie(String.valueOf(se.getSerieInicial()));
        	if (s != null) {
        		error(mensaje + s.getSerieInicial() + "-" + s.getSerieFinal(), request, response);
        		return;
        	}
        	s = serieDAO.buscarSerie(String.valueOf(se.getSerieFinal()));
        	if (s != null) {
        		error(mensaje + s.getSerieInicial() + " - " + s.getSerieFinal(), request, response);
        		return;
        	}
            serieDAO.insertarSerie(se);
            registrarRastro(request, TipoRastros.NUEVO, se, null);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje", "La Serie fue guardada satisfactoriamente.");
        redireccionarConPlantilla("MantenimientoMensaje", request, response);
        return;
    }

    private void guardarModificarSerie(HttpServletRequest request, HttpServletResponse response) {

        String id2 = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        String inicial = request.getParameter("inicial");
        String FINAL = request.getParameter("FINAL");
        String cabezaid = request.getParameter("cabezaid");
        String central = request.getParameter("central");
        String tipocentral = request.getParameter("tipocentral");
        String codCentral = request.getParameter("codCentral");

        Serie se = null,seOld=null;
        Integer idCabeza = null;
        Integer iCodCentral = null;
        Long serieInicial = null;
        Long serieFinal = null;
        try {
            idCabeza = new Integer(cabezaid);
            iCodCentral = new Integer(codCentral);
            serieInicial = new Long(inicial);
            serieFinal = new Long(FINAL);
        } catch (NumberFormatException e1) {
            error(e1,request,response);
            return;
        }
                
        se = new Serie();
        se.setId(id);
        se.setCentral(central);
        se.setCabezaId(idCabeza);
        se.setSerieInicial(serieInicial.longValue());
        se.setSerieFinal(serieFinal.longValue());
        se.setTipocentral(tipocentral);
        se.setCodCentral(iCodCentral);

        try {
        		//busco que la serie no se monte con alguna existente
        	String mensaje = "La serie entra en conflicto con otra serie existente. <br>";
        	Serie s = serieDAO.buscarSerie(String.valueOf(se.getSerieInicial()));
        	if (s != null && s.getId() != id) {
        		error(mensaje + s.getSerieInicial() + "-" + s.getSerieFinal(), request, response);
        		return;
        	}
        	s = serieDAO.buscarSerie(String.valueOf(se.getSerieFinal()));
        	if (s != null && s.getId() != id) {
        		error(mensaje + s.getSerieInicial() + " - " + s.getSerieFinal(), request, response);
        		return;
        	}
        		//recupero la serie anterior para guardarla en rastros. 
        	seOld = serieDAO.getSerie(id);
            serieDAO.actualizarSerie(se);
            registrarRastro(request, TipoRastros.ACTUALIZACION, se, seOld);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
                
        request.setAttribute("mensaje", "La Serie fue modificada satisfactoriamente.");
        redireccionarConPlantilla("MantenimientoMensaje", request, response);
        return;
    }

    
    private void operacionModificarSerie(HttpServletRequest request,
            HttpServletResponse response) {

        String id2 = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        Serie us = null;
        try {
            us = serieDAO.getSerie(id);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        
        List listaCodigosCentral = null;
        try {
            
            listaCodigosCentral =tipoNodoDAO.getListaCabezasId(); 
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        request.setAttribute("listaCodigosCentral",listaCodigosCentral);
        request.setAttribute("serie", us);
        redireccionarConPlantilla("MantenimientoModificarSerie", request, response);
        return;
    }

    
    private void eliminarSerie(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("eliminarSerie");
    	
        String id2 = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        try {
        	Serie seOld = serieDAO.getSerie(id);
        	serieDAO.eliminarSerie(id);
            registrarRastro(request, TipoRastros.ELIMINACION, seOld, null);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje", "Se ha eliminado la serie satisfactoriamente.");
        redireccionarConPlantilla("MantenimientoMensaje", request, response);
        return;
    }

    private void mantenimientoSeries(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {
            List l = serieDAO.getAllSerie();
            request.setAttribute("listaSerie", l);
            
            String exportar = request.getParameter("exportar");
            if ( exportar != null && exportar.equals("yes") ) {
            	request.getRequestDispatcher("/actionSape?accion=reportes&operacion=exportarInforme&pantalla=series").forward(
                        request, response);
            	return;
            }
            
            request.getRequestDispatcher(acciones.getTemplate("MantenimientoSerie")).forward(
                    request, response);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }

    }

    private void guardarNuevoTipoNodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String ipservidor = request.getParameter("ipservidor");
        String puertoservidor = request.getParameter("puertoservidor");
        String ipcabeza = request.getParameter("ipcabeza");
        String puertocabeza = request.getParameter("puertocabeza");
        String tipocabeza = request.getParameter("tipocabeza");
        String estado = request.getParameter("estado");

        TipoNodo tp = new TipoNodo();

        tp.setIpCabeza(ipcabeza);
        tp.setIpServidor(ipservidor);
        tp.setPuertoCabeza(puertocabeza);
        tp.setPuertoServidor(puertoservidor);
        tp.setSite(nombre);
        tp.setEstado(estado);

        try {
        	tp.setTipoCabeza(cabezaPruebaDAO.getCabezaPrueba(Integer.parseInt(tipocabeza)));
            tipoNodoDAO.insertarTipoNodo(tp);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje",
                "El Tipo de Nodo fue guardado satisfactoriamente.");
        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoMensaje")).forward(request,
                response);
        return;

    }
    
    /**
     * Este metodo se utiliza para cambiar el estado del tiponodo que se este modificando
     * en el momento si se cierra el browser.
     * 
     * @param request
     * @param response
     */
    private void operacionActualizarEstadoTipoNodo(HttpServletRequest request, HttpServletResponse response) {
    	
    	String lastState = request.getParameter("lastState");
    	
    	if(lastState == null || lastState.equals("")){
    		error("No fue posible actualizar el estado del actual Tipo Nodo.",request,response);
    		return;
    	}
    	
    	String id = request.getParameter("id");
    	int id2=0;
    	try{
    		id2 =  Integer.parseInt(id);
    	}catch(NumberFormatException e){
    		error(e,request,response);
    		return;
    	}
    	
    	try{
    		TipoNodo t = tipoNodoDAO.getTipoNodo(id2);
    		t.setEstado(lastState);
    		tipoNodoDAO.actualizarTipoNodo(t);
    	}catch(SapeDataException e){
    		error(e,request,response);
    		return;
    	}
    	
    }
    
    /**
     * Metodo para realizar las pruebas interactivas con telefono que no son Siplex
     * TODO: revisar si este metodo se esta utilizando actualmente.
     */
    
	private void operacionTelnetInteractivaNoSiplex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ids = request.getParameter("id");
    	String telCliente = request.getParameter("telCliente");
    	String telOperador = request.getParameter("telOperador");
    	
    	String nombreCabeza = request.getParameter("nombreCabeza");
    	
    	int id = 0;
    	
    	try{
    		id = Integer.parseInt(ids);
    	}catch(NumberFormatException e){
    		request.setAttribute("tipo","cerrar");
    		error(e,request,response);
    		return;
    	}
    
    	TipoNodo t = null;
    	
    	try {
			t=tipoNodoDAO.getTipoNodo(id);
    	}catch (SapeDataException e) {
    		request.setAttribute("tipo","cerrar");
			error(e,request,response);
			return;
    	}
    	
        Serie serie = null;
        String central, tipoCentral;
        try{
            serie=serieDAO.buscarSerie(telCliente);
        }catch(SapeDataException e){
            error(e,request,response);
            return;
        }
        
        if(serie == null) {
        	//este telefono no esta en series, esto es un error
    		request.setAttribute("tipo","cerrar");
			error("El telefono <b>"+telCliente+"</b> No se encuentra en ninguna serie.",request,response);
			return;
        } else {
        	central = serie.getCentral();
        	tipoCentral = serie.getTipocentral();
        }
       
        /* Para comparar si el telefono si pertenece a la tecnologia que se mando por
         * parametro se compara esta tecnologia con la variable tipoCentral
         */
        
        if(nombreCabeza == null || nombreCabeza.equals("")) {
    		request.setAttribute("tipo","cerrar");
			error("Hay inconsistencia en los datos.  La tecnologia solicitada no es valida:  '"+nombreCabeza+"'",request,response);
			return;
        }
        
        if(central == null || tipoCentral==null) {
    		request.setAttribute("tipo","cerrar");
			error("Hay inconsistencia en los datos de la Serie con id = "+serie.getId(),request,response);
			return;
        }
        
        if(tipoCentral.equals(nombreCabeza)) {
        	//Se puede realizar la prueba interactiva a esa tecnologia!!!
        	
    		request.setAttribute("id",String.valueOf(t.getId()));
    		
    		
    		if (nombreCabeza.equals("NEC")) {
    			request.setAttribute("ip",t.getIpEsclavo());
    			request.setAttribute("port",t.getPuertoEsclavo());
    		} else {
    			request.setAttribute("ip",t.getIpCabeza());
    			request.setAttribute("port",t.getPuertoCabeza());
    		}
    		
    		request.setAttribute("tipoPrueba","Interactiva");
    		request.setAttribute("cabezaPrueba",t.getTipoCabeza().getNombre());
    		request.setAttribute("telCliente",telCliente);
    		request.setAttribute("central",central);
    		request.setAttribute("telOperador",telOperador);
    		request.getRequestDispatcher(acciones.getTemplate("telnet")).forward(request, response);
    		
        } else {
    		request.setAttribute("tipo","cerrar");
			error("El telefono "+telCliente+" no pertenece a la tecnologia "+nombreCabeza+
					"<br>La tecnologia de este telefono es "+tipoCentral,request,response);
			return;
        }

	}
    
    
    
    /**
     * METODO PARA INTERACTUAR CON LOS SIPLEX!!!!
     * 
     */
	private void operacionTelnetInteractivaTipoNodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String telCliente = request.getParameter("telCliente");
    	String telOperador = request.getParameter("telOperador");
    	
    
    	TipoNodo t = null;
    	
    	try {

			CPRSiplexPro cpr = null;
					

			cpr=tipoNodoDAO.getCPR(Long.parseLong(telCliente));

			
			if(cpr == null){
				request.setAttribute("tipo","pop-up");
				error("El telefono "+telCliente+" No tiene asociado ningun CPR."+
						"<br>No es posible realizar la prueba sobre este telefono en esta central.",request,response);
				return;
			}
			
			if(cpr.getTipoNodo() == null){
				request.setAttribute("tipo","pop-up");
				error("No es posible realizar Prueba Interactiva sobre el telefono "+telCliente+"."+
						"<br>Este telefono no tiene CPR asignado.",request,response);
			}
						
			t = cpr.getTipoNodo();
			
			String lastState = t.getEstado();
			
			//Significa que ya hay otro usuario utilizando este tiponodo!!!
			if(lastState.equals("M") || lastState.equals("I")){
				
				request.setAttribute("msg","Precaucion: Este Tipo de Nodo Ya esta siendo operado por otro usuario."+
						"<br>Por favor intente en otro momento");
				request.setAttribute("tipo","pop-upTelnetMantenimientoClose");
				request.setAttribute("url","/sape/actionSape?accion=mantenimiento&operacion=mantenimientoTipoNodo");
				redireccionarConPlantilla("msgGeneral",request,response);
				return;
			}
			
			t.setEstado("I");
			tipoNodoDAO.actualizarTipoNodo(t);
			t.setEstado(lastState);
		} catch (SapeDataException e) {
			request.setAttribute("tipo","cerrar");
			error(e,request,response);
			return;
		}
		request.setAttribute("lastState",t.getEstado());
		request.setAttribute("id",String.valueOf(t.getId()));
		request.setAttribute("ip",t.getIpCabeza());
		request.setAttribute("port",t.getPuertoCabeza());
		request.setAttribute("tipoPrueba","Interactiva");
		request.setAttribute("cabezaPrueba",t.getTipoCabeza().getNombre());
		request.setAttribute("telCliente",telCliente);
		request.setAttribute("telOperador",telOperador);
		
		
		request.getRequestDispatcher(acciones.getTemplate("telnet")).forward(request, response);
	
    	
	}
    
    /**
     * METODO PARA EL MANTENIMIENTO DE LOS SIPLEX
     * 
     */
	
	
	
	
    private void operacionTelnetMantenimientoTipoNodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String ids = request.getParameter("id");
    	
//    	String nombreNodo = request.getParameter("nombreNodo");

    	int id = 0;
    	
    	try{
    		id = Integer.parseInt(ids);
    	}catch(NumberFormatException e){
    		request.setAttribute("tipo","cerrar");
    		error(e,request,response);
    		return;
    	}
    	
    	
    	TipoNodo t = null;
    	
    	try {
			t=tipoNodoDAO.getTipoNodo(id);
			String lastState = t.getEstado();
			
			//Significa que ya hay otro usuario utilizando este tiponodo!!!
			if(lastState.equals("M") || lastState.equals("I")){
				
				request.setAttribute("msg","Precaucion: Este Tipo de Nodo Ya esta siendo modificado por otro usuario."+
						"<br>Por favor intente en otro momento");
				request.setAttribute("tipo","pop-upTelnetMantenimientoClose");
				request.setAttribute("url","/sape/actionSape?accion=mantenimiento&operacion=mantenimientoTipoNodo");
				redireccionarConPlantilla("msgGeneral",request,response);
				return;
			}
			
			t.setEstado("M");
			UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
			t.setUsuarioUltimoCambio(usuario.getNick());
			tipoNodoDAO.actualizarTipoNodo(t);
			t.setEstado(lastState);
		} catch (SapeDataException e) {
			request.setAttribute("tipo","cerrar");
			error(e,request,response);
			return;
		}
		request.setAttribute("lastState",t.getEstado());
		request.setAttribute("id",String.valueOf(t.getId()));
		request.setAttribute("ip",t.getIpCabeza());
		request.setAttribute("port",t.getPuertoCabeza());
		request.setAttribute("tipoPrueba","Mantenimiento");
		request.getRequestDispatcher(acciones.getTemplate("telnet")).forward(request, response);
		
    }
    
    
    private void operacionRealizarModificacionTipoNodo(
            HttpServletRequest request, HttpServletResponse response) {

        String id2 = request.getParameter("id");
        
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
        	
            error(e, request, response);
            return;
        }
        String nombre = request.getParameter("nombre");
        String ipservidor = request.getParameter("ipservidor");
        String puertoservidor = request.getParameter("puertoservidor");
        String ipcabeza = request.getParameter("ipcabeza");
        String puertocabeza = request.getParameter("puertocabeza");
//        String tipocabeza = request.getParameter("tipocabeza");
        String ipEsclavo = request.getParameter("ipEsclavo");
        String puertoEsclavo = request.getParameter("puertoEsclavo");
        
        String estado = request.getParameter("estado");

        TipoNodo tpOld = null,tpNew;

        try {
            tpOld = tipoNodoDAO.getTipoNodo(id);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        tpNew = new TipoNodo();
        tpNew.setId(tpOld.getId());
        tpNew.setIpCabeza(ipcabeza);
        tpNew.setIpServidor(ipservidor);
        	//ya cargo la cabeza completa con hibernate.
//        tpNew.setTipoCabeza(Integer.parseInt(tipocabeza));
        tpNew.setPuertoCabeza(puertocabeza);
        tpNew.setPuertoServidor(puertoservidor);
        tpNew.setSite(nombre);
        tpNew.setIpEsclavo(ipEsclavo);
        tpNew.setPuertoEsclavo(puertoEsclavo);
        tpNew.setEstado(estado);
        
		UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
		tpNew.setUsuarioUltimoCambio(usuario.getNick()); 
        tpNew.setTipoCabeza(tpOld.getTipoCabeza());
        
		tpNew.setTipoCentral(tpOld.getTipoCentral());
        
        try {
            tipoNodoDAO.actualizarTipoNodo(tpNew);
            registrarRastro(request, TipoRastros.ACTUALIZACION, tpNew, tpOld);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
      
        //17-08-2006: Sugerencia de chang!!!!!
        if(tpNew.getSite().indexOf("INDIGO")!=-1){
        	cambiarEstadoIndigoProxy(tpNew.getSite(), tpNew.getEstado(),getUsuarioSession(request));
        }
        
        mensaje("El tipo de Nodo fue actualizado satisfactoriamente","cerrar",request,response);
        
        return;
    }
    
    private void cambiarEstadoIndigoProxy(String site,String estado,String user){
    	// Para arrancar o parar un indigoproxy segun sea necesario
    	
    	//1. obtengo el numero del indigo que se va a cambiar de estado.
    	String numeroIndigo = site.substring(7);
    	
    	try {
    		//2. Busco el indigo en la base de datos
			List l =procesosSapeDAO.getProcesosSape("nombre", "nombre", "Indigo%"+numeroIndigo);
			if(l==null||l.size()<=0 || l.size() > 1){
				//3. Si no encotro nada o encontro mas de uno no paro ningun proceso.
				logs.warn("NO SE ENCONTRO EL PROCESO.No es posible cambiar de estado el indigo "+numeroIndigo);
				return;
			}
			
			ProcesosSape p = (ProcesosSape) l.get(0);
			String rta = "",operacion="";
			//4. dependiendo del estado arranco o paro el proceso.
			
			if(estado.indexOf("O")!= -1){
				operacion="start";
				//rta=procesosSapeDAO.ejecutarAccion(String.valueOf(p.getId()), "start", user);
			}else{
				operacion="stop";
			}
			rta=procesosSapeDAO.ejecutarAccion(String.valueOf(p.getId()),operacion, user);
			logs.debug("RESPUESTA_PROCESO("+operacion+") Indigo "+numeroIndigo+" : "+rta);
		} catch (SapeDataException e) {
			e.printStackTrace();
		}
    }
    private void guardarModificarCodigoVer (HttpServletRequest request, HttpServletResponse response) {

        String codigoAnterior = request.getParameter("codigoAnterior");

        CodigoVer cvOld = null, cv2 = null;
        try {
            cvOld = codigosVerDAO.getCodigoVer(codigoAnterior);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }

        String descripcion = request.getParameter("descripcion");
        String comentario = request.getParameter("comentario");
        cv2= new CodigoVer();
        cv2.setClasificacion(descripcion);
        cv2.setComentarios(comentario);
        cv2.setCodigoVer(cvOld.getCodigoVer());
        try {
            codigosVerDAO.actualizarCodigoVer(cv2);
            registrarRastro(request, TipoRastros.ACTUALIZACION, cv2, cvOld);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje","El Codigo fue modificado satisfactoriamente.");
        redireccionarConPlantilla("MantenimientoMensaje",request, response);
        return;
    }

    private void eliminarCodigoVer(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("eliminarCodigoVer");

        String codigover = request.getParameter("codigoVer");

        try {
            CodigoVer codOld = codigosVerDAO.getCodigoVer(codigover);
        	codigosVerDAO.eliminarCodigoVer(codigover);
            registrarRastro(request, TipoRastros.ELIMINACION, codOld, null);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje", "El Codigo fue eliminado satisfactoriamente.");
        redireccionarConPlantilla ("MantenimientoMensaje", request, response);
        return;

    }

    
    private void guardarNuevoCodigoVer(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("guardarNuevoCodigoVer");
    	
        String codigover = request.getParameter("codigover");
        String descripcion = request.getParameter("descripcion");
        String comentario = request.getParameter("comentario");
        CodigoVer cv = null;
        try {
            cv = codigosVerDAO.getCodigoVer(codigover);
        } catch (SapeDataException e) {
            cv = null;	//TODO capturar la objectnotfoundexceptin
        }
        if (cv != null) {
            error("ya existe un registro con el campo codigover = " + codigover
                    + "\nIntente con un valor de codigover distinto.", request,
                    response);

            return;
        }
        cv = new CodigoVer();
        cv.setCodigoVer(codigover);
        cv.setClasificacion(descripcion);
        cv.setComentarios(comentario);
        try {
            codigosVerDAO.insertarCodigoVer(cv);
            registrarRastro(request, TipoRastros.NUEVO, cv, null);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje", "El nuevo Codigo se agrego satisfactoriamente.");
        redireccionarConPlantilla("MantenimientoMensaje", request, response);
        return;

    }

    private void mantenimientoTipoNodo(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        try {
            List l = tipoNodoDAO.getAllTipoNodo();
            request.setAttribute("listaTipoNodo", l);
            
            String exportar = request.getParameter("exportar");
            if ( exportar != null && exportar.equals("yes") ) {
            	request.getRequestDispatcher("/actionSape?accion=reportes&operacion=exportarInforme&pantalla=tipoNodo").forward(
                        request, response);
            	return;
            }
            //con este estado se verifica si se realiza una prueba interactiva o un
            //mantenimiento!!!!!!
            String estado = request.getParameter("estadoActual");
            
            if(estado != null){
            	if (estado.equals("Mantenimiento")) {
            		//esto le indicara al jsp que esta en un mantenimiento y deshabilitara
            		//la funcionalidad del boton Mantenimiento.
            		request.setAttribute("state","M");
            	} else if (estado.equals("Interactiva")){
            		
            		//XXX para controlar cuando un OPERADOR hace una prueba interactiva
            		
            		request.setAttribute("state","I");
            		
            		if(((UserSipe)request.getSession().getAttribute("usuario")).getNivel().equals("1"))
            			return;
            	}
            }
            
            request.getRequestDispatcher(acciones.getTemplate("MantenimientoTipoNodo")).forward(
                    request, response);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }

    }

    private void modificarTipoNodo(HttpServletRequest request, HttpServletResponse response) 
    																		throws ServletException, IOException {

        String id2 = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
        	request.setAttribute("tipo","cerrar");
            error(e, request, response);
            return;
        }
        TipoNodo us = null;
        List listaCabezas = null;

        try {
            us = tipoNodoDAO.getTipoNodo(id);
            listaCabezas = cabezaPruebaDAO.getAllCabezaPrueba();          
        } catch (SapeDataException e) {
        	request.setAttribute("tipo","cerrar");
            error(e, request, response);
            return;
        }
        
        //PARA CONTROLAR SI ESTE TIPONODO ESTA SIENDO UTILIZADO POR OTRA PERSONA!
        String state = us.getEstado();
        // se saco del if esto state.equals("M")|| el 25-10-2005
        if(state.equals("I")){
        	request.setAttribute("tipo","popup");
        	String modo = (state.equals("M")?"Mantenimiento":"Interactivas");
        	error("Este TipoNodo esta siendo utilizado por otro usuario,<br>"+
        			"en una operacion de <font color=\"red\" size=\"+1\">"+modo+"</font>",
        			request,response);
        	return;
        }

        request.setAttribute("tiponodo", us);
		request.setAttribute("listaCabezas", listaCabezas);
        request.getRequestDispatcher(acciones.getTemplate("MantenimientoModificarTipoNodo")).forward(request, response);
        return;
    }

    private void operacionModificarCodigoVer(HttpServletRequest request,
            HttpServletResponse response) {

        String codigoVer = request.getParameter("codigoVer");
        CodigoVer cv = null;
        try {
            cv = codigosVerDAO.getCodigoVer(codigoVer);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("codigo", cv);
        redireccionarConPlantilla("MantenimientoModificarCodigoVer", request, response);

    }

    private void mantenimientoUsuarios(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    	//1. Obtengo los parametros para paginacion y busquedas.
    	
    	String orderBy = request.getParameter("orderBy");
        String pagActual = request.getParameter("pagActual");
        String regPorPagina =request.getParameter("regPorPagina");
    	
        String opcion = request.getParameter("opcion");
        String valorOpcion= request.getParameter("valorOpcion");
        
        //2. validaciones sobre los datos obtenidos
        
        if(orderBy == null || orderBy.equals("")) orderBy = "id ASC";
        
        if(pagActual==null || pagActual.equals("")) pagActual = "1";
        
        if(opcion!=null&&!opcion.equals("")){
        	if(opcion.equals("ninguno")){
        		opcion=null;
        		valorOpcion="";
        	}
        }
        
        if(valorOpcion == null) valorOpcion = "";
        
        try{
        	int pagina = Integer.parseInt(pagActual);
        	if(pagina <=0) pagActual = "1";
        }catch(NumberFormatException e){
        	pagActual = "1";
        }
        
        String offset = "";
        if(regPorPagina==null || regPorPagina.equals("")) regPorPagina = "100";
        
        if(pagActual.equals("1")) 
        	offset="0";
        else
        	offset = String.valueOf((Integer.parseInt(pagActual)-1)*Integer.parseInt(regPorPagina));
        
        List l = null;
    	
        try {
        	
        	l = usuarioDAO.getRegistros(opcion, valorOpcion, regPorPagina, offset, orderBy);
            
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        
        request.setAttribute("listaUsuarios", l.get(0));
        double division = Double.parseDouble((String)l.get(1))/Double.parseDouble(regPorPagina);
        int totalPaginas = (int)Math.ceil(division);
        if(totalPaginas <=1) totalPaginas = 0;
        
        request.setAttribute("valorOpcion",valorOpcion);
        request.setAttribute("opcion",opcion);
        request.setAttribute("offset",offset);
        request.setAttribute("regPorPagina",regPorPagina);
        request.setAttribute("pagActual",pagActual);
        request.setAttribute("totalPaginas",String.valueOf(totalPaginas));
        
        String query = "&opcion="+(opcion!=null?opcion:"")+
        "&valorOpcion="+valorOpcion;
        request.setAttribute("query",query);
        request.setAttribute("orderBy",orderBy);
        request.setAttribute("cantidadTotalRegistros",l.get(1));
        redireccionarConPlantilla("MantenimientoUsuarios",request, response);
    }

    private void operacionEliminarUsuario(HttpServletRequest request,
            HttpServletResponse response) {
        String id2 = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
//        UserSipe user2delete = null;
        try {
        	//user2delete=usuarioDAO.getUsuario(id);
            usuarioDAO.eliminarUsuario(id);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        
//        UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
        
        //aplicacionDAO.registrarActualizacion(new Date(),usuario.getNick(),user2delete,"EL USUARIO FUE ELIMINADO.");
        request.setAttribute("mensaje","El usuario fue eliminado satisfactoriamente.");
        redireccionarConPlantilla("MantenimientoMensaje", request, response);
        return;
    }

    private void operacionModificarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String id2 = request.getParameter("id");
        
        /*
         * Esto controla si se hace un llamado a esta funcion sin parametro.
         */
        if(id2 == null)
        	id2= String.valueOf(((UserSipe)request.getSession().getAttribute("usuario")).getId());
        
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        UserSipe us = null;
        try {
            us = usuarioDAO.getUsuario(id);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("user", us);
        
        if(((UserSipe)request.getSession().getAttribute("usuario")).getNivel().equals("1")){
        	request.getRequestDispatcher(
                    acciones.getTemplate("MantenimientoModificarUsuarioNivel")).forward(
                    request, response);
        	return;
        }
        
        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoModificarUsuario")).forward(
                request, response);
        return;

    }

    private void operacionRealizarModificacionUsuario(
            HttpServletRequest request, HttpServletResponse response) {

        String id2 = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id2);//SE VALIDA QUE EL ARGUMENTO SEA
                                       // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        String nick = request.getParameter("nick");
        String contrasena = request.getParameter("contrasena");
        String nivel = request.getParameter("nivel");
        String lenguaje = request.getParameter("lenguaje");
        String grupo = request.getParameter("grupo");
        String nombre = request.getParameter("nombre");
        String estado = request.getParameter("estado");
        String contacto = request.getParameter("contacto");

        nick = nick.toLowerCase();
        UserSipe us = null;
        try {
            us = usuarioDAO.getUsuario(id);
            UserSipe uss = usuarioDAO.getUserByNick(nick);
            // para validar que el nuevo nick no se encuentre ya en la base de datos
            if(uss != null && us != null && us.getId() != uss.getId()){
            	// a esta altura ya sabemos ke ya existe un usuario con el nick ke desea ingresar!!!!
            	
            	error("El nick que intenta ingresar ya existe.<br>"+
            			"Por favor intente con uno diferente.",request,response);
            	return;
            }
            
        } catch (SapeDataException e) {

            error(e, request, response);
            return;
        }
        
        String oldObject = us.toString();
        
        us.setNick(nick);
        us.setPasswd(contrasena);
        us.setNivel(nivel);
        us.setLenguaje(lenguaje);
        us.setContacto(contacto);
        int grupo2 = 0;
        try {
            grupo2 = Integer.parseInt(grupo);//SE VALIDA QUE EL ARGUMENTO SEA
                                             // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        us.setGrupo(new Integer(grupo2));
        us.setNombre(nombre);
        if (estado.equals("Activo"))
            estado = "S";
        else
            estado = "N";

        us.setActivo(estado);
        try {
            usuarioDAO.actualizarUsuario(us);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        
        UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
        
        aplicacionDAO.registrarActualizacion(new Date(),usuario.getNick(),oldObject,us);
        
        request.setAttribute("mensaje","El usuario fue modificado satisfactoriamente.");
        redireccionarConPlantilla("MantenimientoMensaje",request,response);
        return;

    }

    private void operacionEjecutarAgregarUsuario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String nick = request.getParameter("nick");
        String contrasena = request.getParameter("contrasena");
        String nivel = request.getParameter("nivel");
        String lenguaje = request.getParameter("lenguaje");
        String grupo = request.getParameter("grupo");
        String nombre = request.getParameter("nombre");
        String contacto = request.getParameter("contacto");

        nick = nick.toLowerCase();
        UserSipe us = new UserSipe();

        try{
        	UserSipe uss = usuarioDAO.getUserByNick(nick);
        	
        	if(uss != null){//ya existe un usuario con este nickname
            	error("El nick que intenta ingresar ya existe.<br>"+
            			"Por favor intente con uno diferente.",request,response);
            	return;
        	}
        }catch(SapeDataException e){
        	error(e,request,response);
        	return;
        }
        
        us.setNick(nick);
        us.setPasswd(contrasena);
        us.setNivel(nivel);
        us.setLenguaje(lenguaje);
        us.setContacto(contacto);
        int grupo2 = 0;
        try {
            grupo2 = Integer.parseInt(grupo);//SE VALIDA QUE EL ARGUMENTO SEA
                                             // CORRECTO
        } catch (NumberFormatException e) {
            error(e, request, response);
            return;
        }
        us.setGrupo(new Integer(grupo2));
        us.setNombre(nombre);
        String estado = "S";
        us.setActivo(estado);
        us.setFechaAlta(new Date());
        try {
            usuarioDAO.insertarUsuario(us);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }
        request.setAttribute("mensaje",
                "El usuario se agrego satisfactoriamente.");
        request.getRequestDispatcher(
                acciones.getTemplate("MantenimientoMensaje")).forward(request,
                response);
        return;

    }

    private void mantenimientoCodigosVer(HttpServletRequest request, HttpServletResponse response) {

        try {
            List l = codigosVerDAO.getAllCodigosVer();
            request.setAttribute("listaCodigosVer", l);
            redireccionarConPlantilla("MantenimientoCodigosVer", request, response);
        } catch (SapeDataException e) {
            error(e, request, response);
            return;
        }

    }


    private void nuevoTipoNodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
    	List listaCabezas = null;
    	try {
			listaCabezas = cabezaPruebaDAO.getAllCabezaPrueba();
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
		request.setAttribute("listaCabezas", listaCabezas);
		request.getRequestDispatcher(acciones.getTemplate("MantenimientoNuevoTipoNodo")).forward(request, response);	
    }

    
    private void buscarCPR(HttpServletRequest request, HttpServletResponse response) {
    	if (logs.isDebugEnabled()) logs.debug("buscarCPR");
    	CPRSiplexPro cpr = null;
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
			if (!serie.getTipocentral().equalsIgnoreCase("SIPLEXPRO-CPR")) {
			//if (!serie.getTipocentral().equalsIgnoreCase("SIPLEXPRO")) {
				error("Imposible actualizar CPR. el telefono es del tipo "+serie.getTipocentral()+".", request, response);
				return;				
			}
			
			cpr = tipoNodoDAO.getCPR(Long.parseLong(telefono));
			if (!cpr.getCentral().equals(serie.getCentral())) {
				error("La central definida en series no corresponde a la central definida en el CPR", request, response);
				return;
			}
			listaCabezas = tipoNodoDAO.getTipoNodosCPR(cpr);
		} catch (NumberFormatException e) {
			logs.error(e);
			error(e, request, response);
			return;
    	} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
    	request.setAttribute("cpr", cpr);
    	request.setAttribute("listaCabezas", listaCabezas);
		redireccionarConPlantilla("MantenimientoCprs", request, response);
    }
    
    private void actualizarCPR(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	CPRSiplexPro cpr = null;
    	
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
    	String txtCPR = request.getParameter("txtCPR");
    	if (txtCPR == null) {
    		error("Debe definir el paramtero txtCPR", request, response);
    		return;
    	}    	
		try {
			cpr = tipoNodoDAO.getCPR(Long.parseLong(telefono));
			cpr.setCpr(Integer.parseInt(txtCPR));
			cpr.setTipoNodo(tipoNodoDAO.getTipoNodo(Integer.parseInt(tipoNodo)));
			tipoNodoDAO.actualizarCPR(cpr);
		} catch (NumberFormatException e) {
			logs.error(e);
			error(e, request, response);
			return;
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}
		request.setAttribute("mensaje","CPR actualizado con &eacute;xito.");
		request.getRequestDispatcher(acciones.getTemplate("MantenimientoMensaje")).forward(request,response);

    }

    private void desbloqueoSiplexPRO(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String desbloquear = request.getParameter("desbloquear");
    	
    	if (desbloquear != null && !desbloquear.equals("")) {
    		if (logs.isDebugEnabled()) logs.debug("Desbloquear Cabeza: " + desbloquear);
        	try {
    			ServicioGUDE servicioGUDE = new ServicioGUDE();
    			String usuario = ((UserSipe)request.getSession().getAttribute("usuario")).getNick();
    			// TODO : no es mas correcto si sacamos el servicio como una vble estatica en la clase ServicioGUDE.java ?????
        		String rta = servicioGUDE.ejecutarServicio(usuario,SapeConfiguration.getInstance().getServicio("desbloqueo"), desbloquear);
        		rta =rta.replace(',',' ');
        		response.setContentType("text/html");
        		PrintWriter out = response.getWriter();
        		out.println("<script language=\"javascript\">\r\n");
        		out.println("alert('RESPUESTA: "+rta+"');");
        		out.println("location.href = \"/sape/actionSape?accion=mantenimiento&operacion=desbloqueoSiplexPRO\"");
        		out.println("</script>\r\n");
    		} catch (SapeDataException e) {
    			error(e,request,response);
    		}
    		return;
    	}
    	
    	List listaCentrales = null;
    	try {
			listaCentrales = serieDAO.getCentralesPorTecnologia("SIPLEXPRO");			
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}

    	request.setAttribute("listaCentrales",listaCentrales);
		redireccionarConPlantilla("desbloqueoSiplexPRO", request, response);
    }
    
    
    private void eliminarRastrosVisitas(String tipo, HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("eliminarRastrosVisitas: " + tipo);
    	String desdeFecha = request.getParameter("fechaIni");
    	String hastaFecha = request.getParameter("fechaFin");
    	
        if(desdeFecha == null || hastaFecha == null || desdeFecha.equals("") || hastaFecha.equals("")){
            error("Fecha Invalida", request, response);
            return;
        }
        
        int eliminados = 0; 
        try {
        	String mensaje = null;
        	if (tipo.equals("visitas")) {
        		eliminados = aplicacionDAO.eliminarVisitas(desdeFecha, hastaFecha);
        	}
        	else if (tipo.equals("rastros")) {
        		eliminados = aplicacionDAO.eliminarRastros(desdeFecha, hastaFecha);
        	}
        	mensaje = tipo + ". Desde: " + desdeFecha + " hasta: " + hastaFecha + ". Eliminados " + eliminados + " registros";
        	registrarRastro(request, AplicacionDAO.TipoRastros.ELIMINACION, mensaje, null);
        } catch (SapeDataException e) {
        	error(e, request, response);
        	return;
        }
        
        request.setAttribute("mensaje", "Eliminados " + eliminados + " registros.");
        request.setAttribute("fIni", getFechaHoy());
        request.setAttribute("fFin", getFechaHoy());
//        response.sendRedirect(request.getContextPath() + "/actionSape?accion=" + request.getParameter("accion") + "&operacion=mantenimientoBasedeDatos");
        redireccionarConPlantilla("MantenimientoBasedeDatos", request, response);
    }
    
    /**
     * Si es eliminacion solo se manda el nuevo.
     * @param tipoRastro
     * @param nuevo
     * @param viejo
     */
//    private void registrarRastro (HttpServletRequest request, AplicacionDAO.TipoRastros tipoRastro, Object nuevo, Object viejo) {
//    	if (debug) logs.debug("registrarRastro: " + tipoRastro); //no se muestran los otros porque quedaria muy largo el log.
//    	
//    	UserSipe usu = (UserSipe)request.getSession().getAttribute("usuario");
//    	String usuario = null;
//    	if (usu != null) usuario = usu.getNick();
//    	
//    	switch (tipoRastro) {
//    	case ACTUALIZACION: aplicacionDAO.registrarActualizacion(new java.util.Date(), usuario, nuevo, viejo);
//    		break;
//    	case ELIMINACION: aplicacionDAO.registrarEliminacion(new java.util.Date(), usuario, nuevo);
//    		break;
//   		}
//    }
}