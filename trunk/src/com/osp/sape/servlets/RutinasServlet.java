/*
 * Created on Jun 13, 2005
 */
package com.osp.sape.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gc.acceso.GestorServlet;
import com.gc.data.AplicacionDAO.TipoRastros;
import com.osp.sape.SapeConfiguration;
import com.osp.sape.Exceptions.SapeAppException;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.data.CableDAO;
import com.osp.sape.data.DAOFactory;
import com.osp.sape.data.DAOFactoryImpl;
import com.osp.sape.data.HoraPruebaArmarioDAO;
import com.osp.sape.data.HoraPruebaCableDAO;
import com.osp.sape.data.HoraPruebaClienteDAO;
import com.osp.sape.data.HoraPruebaColaDAO;
import com.osp.sape.data.PruebaProgramadaDAO;
import com.osp.sape.data.RutinaArmarioDAO;
import com.osp.sape.data.RutinaCableDAO;
import com.osp.sape.data.RutinaClienteDAO;
import com.osp.sape.data.RutinasDAO;
import com.osp.sape.data.TipoPruebaDAO;
import com.osp.sape.maestros.CodigoFalla;
import com.osp.sape.maestros.CodigoFallaCola;
import com.osp.sape.maestros.CodigosFallaNaturaleza;
import com.osp.sape.maestros.NaturalezaReclamo;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.maestros.rutinas.HoraCliente;
import com.osp.sape.maestros.rutinas.HoraPruebaCliente;
import com.osp.sape.maestros.rutinas.RutinaArmario;
import com.osp.sape.maestros.rutinas.RutinaCable;
import com.osp.sape.maestros.rutinas.RutinaCliente;
import com.osp.sape.maestros.rutinas.Rutinas;
import com.osp.sape.servicios.CodigosFalla;
import com.osp.sape.utils.ConfiguracionClienteSape;
import com.osp.sape.utils.ServicioGUDE;

/**
 * 
 * @author Andres Aristizabal
 */

public class RutinasServlet extends GestorServlet {
    
    private RutinaClienteDAO rutinaClienteDAO;
    private RutinaArmarioDAO rutinaArmarioDAO;
    private RutinaCableDAO rutinaCableDAO;
    
    private CableDAO cableDAO;

    private HoraPruebaCableDAO horaPruebaCableDAO;
    private HoraPruebaColaDAO horaPruebaColaDAO;
    private HoraPruebaClienteDAO horaPruebaClienteDAO;
    private HoraPruebaArmarioDAO horaPruebaArmarioDAO;
    private CodigosFalla codigosFalla;
    private ConfiguracionClienteSape configCliente;
    private RutinasDAO rutinasDAO;
    private TipoPruebaDAO tipoPruebaDAO;
    private PruebaProgramadaDAO pruebaProgramadaDAO;
    
    public void init() throws ServletException {
    	super.init();
        
        DAOFactory factoria = DAOFactoryImpl.getInstance();
        
        rutinaClienteDAO = factoria.getRutinaClienteDAO();
        rutinaArmarioDAO = factoria.getRutinaArmarioDAO();
        rutinaCableDAO =factoria.getRutinaCableDAO();
        horaPruebaClienteDAO= factoria.getHoraPruebaClienteDAO();
        cableDAO = factoria.getCableDAO();
        horaPruebaArmarioDAO= factoria.getHoraPruebaArmarioDAO();
        horaPruebaCableDAO=factoria.getHoraPruebaCableDAO();
        horaPruebaColaDAO =factoria.getHoraPruebaColaDAO();
        configCliente = ConfiguracionClienteSape.getInstance();
        rutinasDAO=factoria.getRutinasDAO();
        tipoPruebaDAO = factoria.getTipoPruebaDAO();
        pruebaProgramadaDAO=factoria.getPruebaProgramadaDAO();
    }
    
   
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        
        String operacion = request.getParameter("operacion");
        if(logs.isDebugEnabled())logs.debug("doGet: operacion=["+operacion+"]");
        
        UserSipe usuario=(UserSipe)request.getSession().getAttribute("usuario");
        
        if(usuario == null){
        	redireccionar(acciones.getLoginPage(),request,response);
        	return;
        }
        
        String level = usuario.getNivel();
        
        if(level.equals("1")){
        	if(debug) logs.debug("Ingreso a las rutinas de un usuario de nivel "+level+". usuario: "+getUsuarioSession(request));
        	if (operacion == null || operacion.equals("")){
        		operacion = "inicioClientes";
        	}
        	
        	if(!operacion.equals("actualizarRutinaCliente")&&!operacion.equals("inicioClientes")&&
        			!operacion.equals("cargarHorario")&&!operacion.equals("eliminarHorarioCliente")&&
        			!operacion.equals("pantallaRutinaCliente")&&!operacion.equals("insertarRutinaClientes")&&
        			!operacion.equals("eliminarRangoRutinaClientes")&&!operacion.equals("procesarArchivo")&&
        			!operacion.equals("inicioImporteMasivos")&&!operacion.equals("procesarInformacionImporteMasivos")&&!operacion.equals("guardarInformacionImporteMasivos")&&
        			!operacion.equals("eliminarRutinaClientes")&&!operacion.equals("")){
        		operacion = "inicioClientes";
        	}
        	
        	//String tipoRutina = request.getParameter("tipoRutina");
        	/*if(tipoRutina==null || !tipoRutina.equals("Cliente")){
        		operacion = "inicioClientes";
        	}*/
           	
        }
        
        String plantilla=(String) request.getSession().getAttribute("plantillaForward");
        String varnames = (String) request.getSession().getAttribute("varsNames");
//        System.out.println("obtuvo: plantilla="+plantilla+",varnames="+varnames);
        
        
        // metodo para cambiar la url cuando se realiza una actualizacion en el sistema y no se kiere ke el
        // reload de la pagina afecte o genere transacciones iguales. Ver ejemplo inicioArmarios.
        if(plantilla!=null&&!plantilla.equals("")&&varnames!=null&&!varnames.equals("")){
        	
        	direccionarCondicional(true, request, response, plantilla, null);
        	return;
        }
        
        if(operacion == null){
            operacionInicioRutinas(request,response);
            return;
        }else if(operacion.equals("inicio")){
            operacionInicioRutinas(request,response);
            return;
        }else if(operacion.equals("inicioClientes")){
            operacionInicioClientes(request,response);
            return;
        }else if(operacion.equals("inicioSeries")){
        	operacionInicioSeries(request,response);
        	return;
        }else if(operacion.equals("inicioCables")){
            operacionInicioCables(request,response);
            return;
        }else if(operacion.equals("inicioArmarios")){
            operacionInicioArmarios(request,response);
            return;
        }else if(operacion.equals("pantallaRutinaCliente")){
            operacionPantallaRutinaClientes(request,response);
            return;
        }else if(operacion.equals("insertarRutinaClientes")){
            //ESTE METODO INSERTA UN REGISTRO EN LA TABLA rutina_cliente
            operacionInsertarRutinaClientes(request,response);
            return;
        }else if(operacion.equals("eliminarRutinaClientes")){
            //ESTE METODO ELIMINA UN REGISTRO DE LA TABLA rutina_cliente
            operacionEliminarRutinaClientes(request,response);
            return;
        }else if(operacion.equals("consultaHorarioClientes")){
            //CONSULTA LOS HORARIOS LOS HORARIOS DE PRUEBAS
            //ESTO SE HACE EN LA TABLA hrprueba_clientes
            operacionConsultaHorarioClientes(request,response);
            return;
        }/*else if(operacion.equals("actualizarRutinaClientes")){
            //ACTUALIZA LOS HORARIOS DE PRUEBAS
            //ESTO SE HACE EN LA TABLA hrprueba_clientes
            //operacionActualizarRutinaClientes(request,response);
            return;
        }*/else if(operacion.equals("actualizarCombos")){
            //ACTUALIZA LOS COMBOBOX DE LA FORMA Y LOS CARGA, DEPENDIENDO DE LA CENTRAL
            //QUE SE SELECCIONE TRAE LOS ARMARIOS Y LOS CABLES DE ESA CENTRAL
            operacionActualizarCombos(request,response);
            return;
        }else if(operacion.equals("pantallaRutinaArmario")){
            //PANTALLAZO PARA EL BOTON EDITAR DE ARMARIOS
            
            operacionPantallaRutinaArmarios(request,response);
            return;
        }else if(operacion.equals("consultaHorarioArmarios")){
            operacionConsultaHorarioArmarios(request,response);
            return;
        }else if(operacion.equals("eliminarRutinaArmarios")){
            operacionEliminarRutinaArmarios(request,response);
            return;
        }else if(operacion.equals("pantallaRutinaCable")){
            operacionPantallaRutinaCables(request,response);
            return;
        }else if(operacion.equals("consultaHorarioCables")){
            
            operacionConsultaHorarioCables(request,response);
            return;
        }else if(operacion.equals("eliminarRutinaCables")){
            operacionEliminarRutinaCables(request,response);
            return;
        }else if(operacion.equals("actualizarRutinaCableArmario")){
            //TODO revisar: este ya no sirve!!!!
            operacionActualizarRutinaCableArmario(request,response);
            return;
        } else if(operacion.equals("cargarHorario")){
        	
        	operacionCargarHorario(request,response);
        	return;
        } else if(operacion.equals("cargarHorarioArmario")){
            operacionCargarHorarioArmario(request,response);
            return;
        }else if(operacion.equals("cargarHorarioCable")){
            operacionCargarHorarioCable(request,response);
            return;
        }else if(operacion.equals("eliminarHorarioCable")){
            
            operacionEliminarHorarioCable(request,response);
            return;
        }else if(operacion.equals("eliminarHorarioArmario")){
            operacionEliminarHorarioArmario(request,response);
            return;
        }else if(operacion.equals("eliminarHorarioSerie")){
            operacionEliminarHorarioSerie(request,response);
            return;
        } else if(operacion.equals("eliminarHorarioCliente")){
        	operacionEliminarHorarioCliente(request,response);
        	return;
        }else if(operacion.equals("actualizarRutinaCable")){
            operacionActualizarRutinaCable(request,response);
            return;
        } else if(operacion.equals("actualizarRutinaCliente")){
        	operacionActualizarRutinaCliente(request,response);
        	return;
        } else if(operacion.equals("actualizarRutinaArmario")){
            
            operacionActualizarRutinaArmario(request,response);
            return;
        }else if(operacion.equals("actualizarRutinaSerie")){

        	operacionActualizarRutinaSerie(request,response);
            return;
        }else if(operacion.equals("inicioColas")){
            
            operacionInicioColas(request,response);
        }else if(operacion.equals("actualizarRutinaCola")){
            
            actualizarRutinaCola(request,response);
            return;
        }else if(operacion.equals("eliminarProgramacionCola")){
            
            operacionEliminarProgramacionCola(request,response);
        }else if(operacion.equals("inicioImporteMasivos")){
        	
        	request.getRequestDispatcher(acciones.getTemplate("importeMasivos")).forward(request,response);
        }else if(operacion.equals("procesarInformacionImporteMasivos")){
        	
        	operacionProcesarInformacionImporteMasivos(request,response);
        	return;
        }else if(operacion.equals("guardarInformacionImporteMasivos")){
        	
        	operacionGuardarInformacionImporteMasivos(request,response);
        } else if(operacion.equals("procesarArchivo")){
        	
        	operacionProcesarArchivo(request,response);
        	return;
        } else if(operacion.equals("inicioCodigosFalla")){
        	
        	operacionInicioCodigosFalla(request,response);
        	return;
        } else if(operacion.equals("agregarCodigoFalla")){
        	
        	redireccionarConPlantilla("nuevoCodigoFalla",request,response);
        	return;
        } else if(operacion.equals("modificarCodigoFalla")){
        	
        	operacionModificarCodigoFalla(request,response);
        	return;
        } else if(operacion.equals("eliminarCodigoFalla")){
        	
        	operacionEliminarCodigoFalla(request,response);
        	return;
        } else if(operacion.equals("hacerInsertCodigoFalla")){
        	
        	operacionInsertarCodigoFalla(request,response);
        	return;
        } else if(operacion.equals("hacerModificacionCodigoFalla")){
        	
        	operacionHacerModificacionCodigoFalla(request,response);
        	return;
        } else if(operacion.equals("editarColas")){
        	editarColas(request,response);
        	return;
        } else if(operacion.equals("adicionarCodigosCola")){
        	operacionAdicionarCodigosCola(request,response);
        	return;
        } else if(operacion.equals("eliminarRangoRutinaClientes")){
        	operacionEliminarRangoRutinaClientes(request,response);
        	return;
        } else if (operacion.equals("cargarCola")) {
        	cagarCola(request, response);
        	return;
        }else if(operacion.equals("habilitarRutina")){
        	habilitarRutina(request,response);
        	return;
        }
    }
    
    // metodo utilizado para habilitar de nuevo una rutina.
    private void habilitarRutina(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	
    	String idRutina = request.getParameter("idRutina");
    	int id=-1;
    	try{
    		id=Integer.parseInt(idRutina);
    	}catch(NumberFormatException e){
    		error("El id de la rutina debe ser numerio",request,response);
    		return;
    	}
    	
    	Rutinas r = null;
    	String tipo = "";
    	try {
			r=rutinasDAO.getRutina(id);
			tipo= (r.getTipo().equals("SE")?"Serie":tipo);
			tipo= (r.getTipo().equals("CL")?"Cliente":tipo);
			tipo= (r.getTipo().equals("CA")?"Cable":tipo);
			tipo= (r.getTipo().equals("AR")?"Armario":tipo);
			int limite = configCliente.getNumeroRutinas();
			int rutinasActuales = rutinasDAO.getElementosActivos(r.getTipo(),null,true).size();
			
			if(rutinasActuales >= limite){
				error("Limite de rutinas de "+tipo+" alcanzada. Se sugiere que elimine una de las rutinas Actuales.",request,response);
				return;
			}
					
			// debo validar que no halla una rutina activa con el mismo nombre.
			
			//No es necesario si viene por aca es porke ya estaba deshabilitada
			//List l2=rutinasDAO.getElemento(r.getValorTipo(), true);
			
			
//			if(l2 != null && l2.size()>0){
//				error("Ya existe activa una rutina de "+tipo+" "+r.getValorTipo()+" Activa, por favor trate con uno distinto.",request,response);
//				return;
//			}

			//r.setHabilitado(new Boolean(true));
			//rutinasDAO.actualizarRutina(r);
			
			
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
    	
		
		request.setAttribute("idElementoActual",r.getId().toString());
		request.setAttribute("habilitandoRutina","true");
		request.setAttribute("msg","Ahora debe dar un horario futuro a la rutina y guardar los cambios");
		
		if(r.getTipo().equals("CA")){
			operacionInicioCables(request,response);
		}else if(r.getTipo().equals("AR")){
			operacionInicioArmarios(request,response);
		}else if(r.getTipo().equals("CL")){
			operacionInicioClientes(request,response);
		}else if(r.getTipo().equals("SE")){
			operacionInicioSeries(request,response);
		}
		
		//1. Como voy a habilitar esta rutina borro el reporte de pruebasprogramadas y pruebaspp
		//	 OJO, solo el reporte, no las pruebas ni la rutina.
		
		try {
			int borrados=pruebaProgramadaDAO.eliminarEstadisticoCodigosVer(r.getId().toString());
			logs.debug("elementos borrados: "+borrados);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
		return;
    }
    
    private void operacionEliminarHorarioCliente(HttpServletRequest request, HttpServletResponse response) {
        String cliente = request.getParameter("idCliente");
        String nombre = request.getParameter("nombre");
                
        try {
        	int id = Integer.parseInt(cliente);
        	Rutinas rut = rutinasDAO.getRutina(id);
        	Rutinas rut2 = rutinasDAO.getRutina(id);
        	rut2.setHabilitado(new Boolean(false));
        	rut2.setFechaProgramada(new Date());
        	registrarRastro(request, TipoRastros.ACTUALIZACION, rut, rut2);
        	rutinasDAO.actualizarRutina(rut2);
        	
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        request.setAttribute("msg","Se retiro la rutina por cliente "+nombre+" con exito.");
        operacionInicioClientes(request,response);
    }
    
    private void operacionEliminarHorarioSerie(HttpServletRequest request, HttpServletResponse response) {
        String serie = request.getParameter("idSerie");
        String nombre = request.getParameter("nombre");
                
        try {
        	int id = Integer.parseInt(serie);
        	Rutinas rut = rutinasDAO.getRutina(id);
        	Rutinas rut2 = rutinasDAO.getRutina(id);
        	rut2.setHabilitado(new Boolean(false));
        	rut2.setFechaProgramada(new Date());
        	registrarRastro(request, TipoRastros.ACTUALIZACION, rut, rut2);
        	rutinasDAO.actualizarRutina(rut2);
        	
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        request.setAttribute("msg","Se retiro la serie "+nombre+" con exito.");
        operacionInicioSeries(request,response);
    }
    
    private void operacionActualizarRutinaSerie(HttpServletRequest request, HttpServletResponse response) {
    	String serie = request.getParameter("serie");
    	String tipoRutina = request.getParameter("tipoRutina");
    	String tipoPrueba = request.getParameter("tipoPrueba");
    	//String habilitado = request.getParameter("habilitado");
    	Rutinas rut = new Rutinas();
    	
    	rut.setTipo("SE");
    	rut.setValorTipo(serie);
    	rut.setTipoDePrueba(tipoPrueba);
		rut.setHabilitado(new Boolean(true));
		rut.setFechaProgramada(new Date());
		rut.setUsuario(getUsuarioSession(request));
		rut.setHabilitado(new Boolean(true));
		
		StringTokenizer stt = new StringTokenizer(serie,"-");
		String datoMensaje = serie;
		
		if(stt != null && stt.countTokens()>=2)
			datoMensaje=stt.nextToken()+"-"+stt.nextToken();
		
		
    	if(tipoRutina.equals("H")){
    		String dias = request.getParameter("dias");
    		String horas = request.getParameter("horas");
    		
    		if(dias != null && dias.equals(""))
    			dias = null;
    		
    		rut.setDias(dias);
    		rut.setHoras(horas);
    		rut.setTipoHorario("H");
    	} else {
    		String fechaIni = request.getParameter("fechaIni");
    		String fechaFin = request.getParameter("fechaFin");
    		String horaIni = request.getParameter("horaIni");
    		String horaFin = request.getParameter("horaFin");
    		String minIni =  request.getParameter("minIni");
    		String minFin =  request.getParameter("minFin");
    		
    		String horai = (horaIni == null||horaIni.equals("--")||minIni==null||minIni.equals("--")?null:horaIni+":"+minIni+":00");
    		String horaf = (horaFin == null||horaFin.equals("--")||minFin==null||minFin.equals("--")?null:horaFin+":"+minFin+":00");
    		
    		rut.setFechaInicial(getDate(fechaIni));
    		rut.setFechaFinal(getDate(fechaFin));
    		rut.setHoraInicial(horai);
    		rut.setHoraFinal(horaf);
    		rut.setTipoHorario("D");  
    	}
    	
    	try {
    		
    		String idRut = request.getParameter("idRutina");
    		Rutinas r2 = null;
    		if(idRut==null || idRut.equals("-1")){
    			r2=null;
    		}else{
    			r2=rutinasDAO.getRutina(Integer.parseInt(idRut));
    		}
    		//Rutinas r2 = (Rutinas) lista.get(0);
    		if(r2 != null){// es una actualizacion
    		// debo controlar que la serie se reinicie!!!!
    			//1. controlar ke solo sea el tel ini y el tel fin
    			StringTokenizer sttt= new StringTokenizer(rut.getValorTipo(),"-");
    			String nuevaSerie=sttt.nextToken()+"-"+sttt.nextToken();
    			rut.setValorTipo(nuevaSerie);
    			rut.setId(r2.getId());
    			
    			rutinasDAO.actualizarRutina(rut);
    			registrarRastro(request, TipoRastros.ACTUALIZACION, rut, r2);
    			request.setAttribute("msg","Rutina de la serie "+datoMensaje+" actualizada con exito.");
    		}else{// es una insercion nueva. Debo validar ke no este completo el limite de rutinas.
    			
    			int limite = configCliente.getNumeroRutinas();
    			int rutinasActuales = rutinasDAO.getElementosActivos("SE",null,true).size();
    			
    			if(rutinasActuales >= limite){
    				error("Limite de rutinas para Series alcanzada. Se sugiere que elimine una de las rutinas Actuales.",request,response);
    				return;
    			}
    			
    			
    			// debo validar ke ya no haya un elemento con este serie
    			
    			List l2=rutinasDAO.getElemento(rut.getValorTipo(), true);
    			
    			
    			if(l2 != null && l2.size()>0){
    				error("Ya existe activa una rutina de Serie "+rut.getValorTipo()+" por favor trate con una Serie distinta.",request,response);
    				return;
    			}
    			
    			
    			rutinasDAO.insertarRutina(rut);
    			request.setAttribute("msg","Rutina de la serie "+datoMensaje+" insertada con exito.");
    		}
    		
		} catch (SapeDataException e) {
			e.printStackTrace();
			error(e,request,response);
			return;
		}
		request.setAttribute("idElementoActual",rut.getId().toString());
		operacionInicioSeries(request, response);
    }
    
    private void operacionInicioSeries(HttpServletRequest request, HttpServletResponse response) {
    	List listaSeries = null,listaPruebasSiplex=null;
    	String elementosActivos="0";
        try {
            listaSeries = rutinasDAO.getElementosActivos("SE",null,true);
            elementosActivos = String.valueOf(rutinasDAO.getElementosActivos("SE",null,true).size());
            listaPruebasSiplex = tipoPruebaDAO.getTiposPrueba();
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        for(int i=0;i<listaSeries.size();i++){
        	Object tmp[]= (Object[])listaSeries.get(i);
        	String valor = (String)tmp[0];
        	StringTokenizer stt = new StringTokenizer(valor,"-");
        	if(stt.countTokens()==2){
        		continue;
        	}else{
        		String value2 = stt.nextToken()+"-"+stt.nextToken();
        		tmp[0]=value2;
        		listaSeries.set(i,tmp);
        	}
        }
        
        request.setAttribute("listaElementos",listaSeries);

        request.setAttribute("listaPruebasSiplexpro",listaPruebasSiplex);
        
        int maxPruebas = configCliente.getNumeroRutinas();
        request.setAttribute("maxPruebas",String.valueOf(maxPruebas));
        request.setAttribute("tipoRutina","Serie");
        request.setAttribute("rutinasActuales",elementosActivos);
        redireccionarConPlantilla("rutinas",request,response);
    }
    
    private void operacionCargarHorarioSerie(HttpServletRequest request, HttpServletResponse response)throws IOException {
    	
        String serie = request.getParameter("serie");
        Rutinas l1 = null;
        try {
            l1=rutinasDAO.getRutina(Integer.parseInt(serie));
        } catch (SapeDataException e) {
        	e.printStackTrace();
            error(e,request,response);
            return;
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //TODO: 1. identificar ke tipo de rutina es
        //		2. si es horas rellenar los combos con la info
        //		3. si es definida rellenar los campos de fechas y horas.
        //		4. ojo ke falta la prueba
        
        
        out.print("<script languaje=\"text/javascript\" src=\"javascript/varios.js\"> </script> \n");
        out.print("<script type=\"text/javascript\">\n"); 
        out.print("<!--\n");
        
        
        if(l1.getTipoHorario().equals("H")){//la rutina es por horas!!!

        	out.println("window.parent.cargarDatosHorarioPorHoras("+
        			"'"+l1.getValorTipo()+"','"+l1.getTipoDePrueba()+"','"+l1.getUsuario()+"','"+l1.getFechaProgramada()+
        			"','"+l1.getHoras()+"','"+(l1.getDias()==null||l1.getDias().equals("")?"":l1.getDias())+"','"+l1.getId()+"');");

        } else if(l1.getTipoHorario().equals("D")){//la rutina esta definida por el usuario.
        	
        	String horaini = l1.getHoraInicial();
        	String horafin = l1.getHoraFinal();
        	
        	out.println("window.parent.cargarDatosHorarioPorFechas("+
        			"'"+l1.getValorTipo()+"','"+l1.getTipoDePrueba()+"','"+l1.getUsuario()+"','"+l1.getFechaProgramada()+
        			"','"+(l1.getFechaInicial()!=null?getFechaFormato(l1.getFechaInicial(),"yyyy-mm-dd"):"")+"','"+
        			(l1.getFechaFinal()!=null?getFechaFormato(l1.getFechaFinal(),"yyyy-mm-dd"):"")+"','"+
        			(l1.getHoraInicial()!= null?horaini.substring(0,2)+"','"+horaini.substring(3,5)+"','":"','','")+
        			(l1.getHoraFinal()!= null?horafin.substring(0,2)+"','"+horafin.substring(3,5)+"'":"',''")+",'"+l1.getId()+"');");

        } else {
        	error("Error en los parametros",request,response);
        	logs.error("Algo inesperado paso tratando de cargar la rutina: "+l1);
        	return;
        }

        out.print("//-->\n");
        out.print("</script>\n");
    	
    }
    
    
    private void operacionCargarHorario(HttpServletRequest request, HttpServletResponse response)throws IOException {
    	String tipo=request.getParameter("tipoRutina");
    	
    	if(tipo != null && !tipo.equals("")){
    		if(tipo.equalsIgnoreCase("Armario")){
    			operacionCargarHorarioArmario(request,response);
    		}else if(tipo.equalsIgnoreCase("cable")){
    			operacionCargarHorarioCable(request,response);
    		} else if(tipo.equalsIgnoreCase("Serie")){
    			operacionCargarHorarioSerie(request,response);
    		} else if(tipo.equals("Cliente")){
    			operacionCargarHorarioCliente(request,response);
    		}
    	}
    }
    
    
    private void operacionEliminarRangoRutinaClientes(HttpServletRequest request, HttpServletResponse response) {
    	
    	String inicio = request.getParameter("inicio");
    	String fin = request.getParameter("fin");
    	String cliente = request.getParameter("cliente"); 
    	// el jsp ya valido la naturaleza de los datos!!!
    	// son datos de tipo numerico de 7 digitos!!!
    	int rowsAffected = 0;
    	request.setAttribute("tipo","popup");
    	try {
    		rowsAffected=rutinaClienteDAO.eliminarRangoRutinaCliente(inicio, fin,cliente);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
		
        request.setAttribute("msg","Se eliminaron "+rowsAffected+" registros para el rango: "+inicio+" - "+fin+".");
        operacionPantallaRutinaClientes(request,response);
    }
    
    private void operacionAdicionarCodigosCola(HttpServletRequest request, HttpServletResponse response) {
    	
    	String codigosFalla2 = request.getParameter("codigosFalla");
    	String naturalezasReclamo = request.getParameter("naturalezasReclamo");
    	String cola = request.getParameter("cola");
    	
    	request.setAttribute("tipo","popup");// atributo para ke el jsp de error cambie el boton de regresar por uno de cerrar!!
    	
    	
    	if(cola == null || cola.equals("")){
    		
    		error("Cola no valida. Intente nuevamente",request,response);
    		return;
    	}
    	
    	
    	
    	List listaCodigosFalla = new ArrayList(),listaNaturalezas=new ArrayList();
    	
    	// solicito asociar a esta cola todos los codigos de falla
    	
    	try {
			codigosFalla = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
			
        	if(codigosFalla2.equalsIgnoreCase("todos")){
        		List array = codigosFalla.getAllCodigosFalla();
        		int size = array.size();
        		for(int i=0;i<size;i++){
        			CodigoFalla cf = (CodigoFalla)array.get(i);
        			listaCodigosFalla.add(cf.getFallaId());
        		}
        	} else {
        	
	        	StringTokenizer stt = new StringTokenizer(codigosFalla2,",");
	        	while(stt.hasMoreTokens()){
	        		listaCodigosFalla.add(stt.nextToken());
	        	}
        	}
        	
        	
        	if(naturalezasReclamo.equalsIgnoreCase("todos")) {
        		List array = codigosFalla.getAllNaturalezasReclamo();
        		int size = array.size();
        		for(int i=0;i<size;i++){
        			NaturalezaReclamo cf = (NaturalezaReclamo)array.get(i);
        			listaNaturalezas.add(cf.getFallaId());
        		}
        	} else {
        	
	        	StringTokenizer stt = new StringTokenizer(naturalezasReclamo,",");
	        	while(stt.hasMoreTokens()){
	        		listaNaturalezas.add(stt.nextToken());
	        	}
        	}
        	
        	
        	CodigoFallaCola cfc = new CodigoFallaCola(listaCodigosFalla,cola,listaNaturalezas);
    		
			try{
				codigosFalla.eliminarCodigoFallaCola(cola);
			}catch(SapeAppException e){
				logs.debug("NO ENCONTRO LA COLA: "+cola);
			}
			codigosFalla.adicionarCodigoFallaCola(cfc);
				//Registro el rastro de la ultima persona que modifico la cola.
			String usuario = ((UserSipe)request.getSession().getAttribute("usuario")).getNick();
			horaPruebaColaDAO.actualizarRastroCambio(cola, usuario, new Date());
		} catch (SapeAppException e) {
			error(e,request,response);
    		return;
		} catch (SapeDataException e) {
			error(e,request,response);
    		return;			
		}
		request.setAttribute("msg","La cola "+cola+" fue modificada satisfactoriamente.");
		request.setAttribute("cola",cola);
		editarColas(request,response);
    	
		
    }
    
    private void editarColas(HttpServletRequest request, HttpServletResponse response) {
    	if (debug) logs.debug("editarColas");
    	
    	String cola = request.getParameter("cola");
    	
    	if(cola == null || cola.equals("")){
    		cola = (String)request.getAttribute("cola");
    		if(cola == null || cola.equals("")){
    			request.setAttribute("tipo","popup");
    			error("Cola invalida. Intente nuevamente con otra cola.",request,response);
    			return;
    		}
    	}
    	
    	// dentro de este bean hay una lista de codigos de falla y de naturalezas(solo los id)
    	CodigoFallaCola cfc = null;
    	List listaCodigos=null, listaAsignados = null,listaNaturalezas=null,listaNaturalezasAsignadas=null;
    	try {
			codigosFalla = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
			
			cfc=codigosFalla.getCodigoFallaCola(cola);
			
			/*if(cfc == null){
				request.setAttribute("tipo","popup");
				error("La cola "+cola+" no tiene informacion asociada. Intente nuevamente con otra cola.",request,response);
				return;
			}*/
			
			listaCodigos = codigosFalla.getAllCodigosFalla();
			listaNaturalezas = codigosFalla.getAllNaturalezasReclamo();
		} catch (SapeAppException e) {
			e.printStackTrace();
		}
		
		if(cfc != null){
			listaAsignados = cfc.getFallasId();
			listaNaturalezasAsignadas = cfc.getNaturalezasId();
		}
		
		request.setAttribute("cola",cola);
		request.setAttribute("listaAsignados",listaAsignados);	
		request.setAttribute("listaNaturalezasAsignadas",listaNaturalezasAsignadas);
		request.setAttribute("listaCodigos",listaCodigos);
		request.setAttribute("listaNaturalezas",listaNaturalezas);
		
		redireccionarConPlantilla("pantallaRutinaColas",request,response);
    }
    
    private void operacionHacerModificacionCodigoFalla(HttpServletRequest request, HttpServletResponse response) {
    	
    	String fallaId = request.getParameter("fallaId");
    	String nombre = request.getParameter("nombre");
    	String fallaIdViejo = request.getParameter("fallaIdViejo");
    	String tipo = request.getParameter("tipo");
    	if(fallaId == null || fallaId.equals("") || nombre == null || nombre.equals("")){
    		error("Los parametros son incorrectos: Id de falla ="+fallaId+" nombre="+nombre,request,response);
    		return;
    	}
    	
    	if( fallaIdViejo == null || fallaIdViejo.equals("") ){
    		error("No se encontro ningun Codigo de Falla con identificador: "+fallaIdViejo,request,response);
    		return;
    	}
    	
    	if(fallaId.indexOf(" ") != -1){
    		error("Id de Falla incorrecto. Debe especificar un identificador de una sola palabra.",request,response);
    		return;
    	}
    	
    	if(tipo==null||tipo.equals("")){
    		error("Faltan parametros - 'tipo'",request,response);
    		return;
    	}
    	
    	CodigosFallaNaturaleza cf = null;
    	

    	try {
			codigosFalla = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
			
	    	if(tipo.equals("falla")){
	    		cf = new CodigoFalla(fallaId,nombre);
	    		codigosFalla.eliminarCodigoFalla(fallaIdViejo);
				codigosFalla.adicionarCodigoFalla((CodigoFalla)cf);
				mensaje("Se modifico el Codigo de Falla con exito.",null,request,response);
	    	}else{//asumire ke es una naturaleza
	    		cf = new NaturalezaReclamo(fallaId,nombre);
	    		codigosFalla.eliminarNaturaleza(fallaIdViejo);
	    		codigosFalla.adicionarNaturalezaReclamo((NaturalezaReclamo) cf);
	    		mensaje("Se modifico la Naturaleza con exito.",null,request,response);
	    	}
			//codigosFalla.eliminarCodigoFalla(fallaIdViejo);
			//codigosFalla.adicionarCodigoFalla(cf);
		} catch (SapeAppException e) {
			error(e,request,response);
			return;
		}
    	
		//mensaje("Se modifico el Codigo de Falla con exito.",null,request,response);
		return;
    	
    }
    
    private void operacionModificarCodigoFalla (HttpServletRequest request, HttpServletResponse response) {
    	
    	String fallaId=request.getParameter("fallaId");
    	String tipo = request.getParameter("tipo");
    	
    	if(tipo == null){
    		error("Faltan parametros - 'tipo'",request,response);
    		return;
    	}
    		
    	
    	try {
			codigosFalla = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
			
			CodigosFallaNaturaleza cf = null;
			
			if(tipo.equals("falla")){
				cf =codigosFalla.getCodigoFalla(fallaId);
			}else{
				cf = codigosFalla.getNaturalezaReclamo(fallaId);
			}
			
			request.setAttribute("fallaIdViejo",cf.getFallaId());
			request.setAttribute("nombreViejo",cf.getNombre());
			request.setAttribute("tipo",tipo);
		} catch (SapeAppException e) {
			error(e,request,response);
			return;
		}
		redireccionarConPlantilla("nuevoCodigoFalla",request,response);
		return;
    	
    }
    
    private void operacionEliminarCodigoFalla(HttpServletRequest request, HttpServletResponse response) {
    	
    	String fallaId=request.getParameter("fallaId");
    	String tipo = request.getParameter("tipo");
    	
    	if(tipo == null){
    		error("Faltan parametros - 'tipo'",request,response);
    		return;
    	}
    	
    	try {
			codigosFalla = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
			
			if(tipo.equals("falla"))
				codigosFalla.eliminarCodigoFalla(fallaId);
			else if(tipo.equals("naturaleza"))
				codigosFalla.eliminarNaturaleza(fallaId);
			else
				error("Parametros incorrectos - 'tipo'",request,response);
		} catch (SapeAppException e) {
			error(e,request,response);
			return;
		}
    	
		mensaje("Se elimino con exito el codigo de falla "+fallaId+".",null,request,response);
    }
    
    private void operacionInsertarCodigoFalla(HttpServletRequest request, HttpServletResponse response) {
    	
    	String fallaId = request.getParameter("fallaId");
    	String nombre = request.getParameter("nombre");
    	String tipo = request.getParameter("tipo");
    	
    	if(fallaId == null || fallaId.equals("") || nombre == null || nombre.equals("") || tipo == null || tipo.equals("")){
    		error("Los parametros son incorrectos: Id de falla ="+fallaId+" nombre="+nombre,request,response);
    		return;
    	}
    	
    	if(fallaId.indexOf(" ") != -1){
    		
    		error("Id de Falla incorrecto. Debe especificar un identificador de una sola palabra.",request,response);
    		return;
    	}
    	
    	
    	if(tipo.equals("falla")){// es un codigo de falla!!!!
        	CodigoFalla cf = new CodigoFalla(fallaId,nombre);
        	
        	try {
    			codigosFalla = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
    			codigosFalla.adicionarCodigoFalla(cf);
    		} catch (SapeAppException e) {
    			error(e,request,response);
    			return;
    		}
        	
    		mensaje("Se inserto el Codigo de Falla con exito.",null,request,response);
    		return;    		
    	} else { // es una naturaleza de reclamo.
    		NaturalezaReclamo nat = new NaturalezaReclamo(fallaId,nombre);
        	try {
    			codigosFalla = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
    			//codigosFalla.adicionarCodigoFalla(cf);
    			codigosFalla.adicionarNaturalezaReclamo(nat);
    		} catch (SapeAppException e) {
    			error(e,request,response);
    			return;
    		}
        	
    		mensaje("Se inserto la Naturaleza de Reclamo con exito.",null,request,response);
    		return;
    	}
    	

    }
    
    private void operacionInicioCodigosFalla(HttpServletRequest request, HttpServletResponse response) {
    	/*
    	 * EN el bean CodigosFalla se manejan tambien las naturalezas de reclamo.
    	 */
    	List listaCodigos =null,listaNaturalezas=null;
    	try {
    		codigosFalla = new CodigosFalla(new File(SapeConfiguration.getInstance().getRutaCodigosFalla()));
			listaCodigos=codigosFalla.getAllCodigosFalla();
			listaNaturalezas=codigosFalla.getAllNaturalezasReclamo();
			
		} catch (SapeAppException e) {
			error(e,request,response);
			return;
		}
		request.setAttribute("listaCodigos",listaCodigos);
		request.setAttribute("listaNaturalezas",listaNaturalezas);
		redireccionarConPlantilla("codigosFalla",request,response);
		
        return;
    }
    
    private void operacionProcesarArchivo(HttpServletRequest request, HttpServletResponse response)throws IOException {
    	
    	
        String cable=(String)request.getSession().getAttribute("cable");
        String armario = null;
        String cliente = null;
        String tabla = null;
        String valorTabla = null;
        
        if(cable == null){
        	armario=(String)request.getSession().getAttribute("armario");
        	if(armario == null){
        		
        		cliente = (String)request.getSession().getAttribute("cliente");
        		
        		if(cliente == null){
            		request.setAttribute("tipo","cerrar");
            		error("Faltan parametros para la operacion.",request,response);
            		return;	
        		}
        		
        		tabla = "cliente";
        		valorTabla = cliente;
        		
        	} else {
        		
        		tabla = "armario";
        		valorTabla = armario;
        	}
        } else {
        	
        	tabla = "cable";
        	valorTabla = cable;
        }
    	
        request.getSession().removeAttribute("cable");
        request.getSession().removeAttribute("armario");
        request.getSession().removeAttribute("cliente");
    	    	
//    	String tipo = request.getParameter("tipo");
    	String nombreArchivo = request.getParameter("archivo");
    	    	
    	File f = new File(getServletContext().getRealPath("/")+acciones.getTemplate("temporalReportes")+nombreArchivo);
    	
    	
    	if(!f.exists()){	
    		error("Archivo "+nombreArchivo+" No existe. Proceso cancelado",request,response);
    		return;
    	}

		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    request.setAttribute("tipo","cerrar");
    	
    	out.println("<b>INICIO</b>");
    	out.println("<br>Insertando Informacion del "+tabla+" "+valorTabla+", un momento por favor...");
    	out.println("<pre>");
    	    	
    	
		RandomAccessFile raf = new RandomAccessFile(f,"rw");
		int totalInsertados = 0;
		if(tabla.equals("cliente")){
			while(true){
				String line = raf.readLine();
				if(line == null) break;
				
				StringTokenizer stt = new StringTokenizer(line,";");

				if (stt.countTokens() > 2 || stt.countTokens() < 1){
					if(debug) logs.debug("Linea Ignorada, FALTAN PARAMETROS: ["+line+"]");
					out.println("[ERROR] Linea Ignorada, FALTAN PARAMETROS: ["+line+"]");
					continue;
				}

				String tel = stt.nextToken();
				if(tel.length() != ((Integer)getServletContext().getAttribute("cantDigitosEntorno")).intValue()){
					if(debug) logs.debug("Linea Ignorada, TELEFONO NO VALIDO: ["+line+"]");
					out.println("[ERROR] Linea Ignorada, TELEFONO NO VALIDO: ["+line+"]");
					continue;
				}
				long telefono = -1;
				try{
					telefono = Long.parseLong(tel);
				}catch(NumberFormatException e){
					if(debug) logs.debug("Linea Ignorada TELEFONO NO VALIDO: ["+line+"]");
					out.println("[ERROR] Linea Ignorada, TELEFONO NO VALIDO: ["+line+"]");
					continue;
				}

				String nombre = "ND";

				if(stt.countTokens() >=2 ){
					nombre = stt.nextToken();
				}
				
				RutinaCliente rc = new RutinaCliente();
				rc.setNombre(nombre);
				rc.setTelefono(telefono);
				rc.setEstatus("II");
				rc.setUsuario(cliente);
				try {
					
					List lll=rutinaClienteDAO.getRutinasPorCliente(cliente, telefono);

					if(lll != null && lll.size()>0){
						if(debug) logs.debug("Linea Ignorada TELEFONO YA EXISTE: ["+line+"]");
						out.println("[ERROR] Linea Ignorada, TELEFONO YA EXISTE: ["+line+"]");
						continue;
					}else{
						rutinaClienteDAO.insertarRutinaCliente(rc);
					}
					totalInsertados++;
				} catch (SapeDataException e) {
					if(debug) logs.error(e.toString());
					out.println( "[ERROR] "+e.toString());
				}
				
			}
		}else{
				
			while(true){
				String line = raf.readLine();
				if(line == null)
					break;
				
				StringTokenizer stt = new StringTokenizer(line,";");
				
				if (stt.countTokens() != 4){
					if(debug) logs.debug("Linea Ignorada, FALTAN PARAMETROS: ["+line+"]");
					out.println("[ERROR] Linea Ignorada, FALTAN PARAMETROS: ["+line+"]");
					continue;
				}
				// configuracion del archivo: cable,telefono,direccion,tipocliente
				
				String tablaRow = stt.nextToken();
				tablaRow=tablaRow.trim();
				// el registro actual no pertenece al cable o armario ke se esta tratando en el momento
				if(!tablaRow.equals(valorTabla)){
					if(debug) logs.debug("Linea Ignorada, NO PERTENECE AL "+tabla.toUpperCase()+" "+valorTabla+" : ["+line+"]");
					out.println("[ERROR] Linea Ignorada, NO PERTENECE AL "+tabla.toUpperCase()+" "+valorTabla+" : ["+line+"]");
					continue;
				}
				
				String tel = stt.nextToken();
				if(tel.length() != ((Integer)getServletContext().getAttribute("cantDigitosEntorno")).intValue()){
					if(debug) logs.debug("Linea Ignorada, TELEFONO NO VALIDO: ["+line+"]");
					out.println("[ERROR] Linea Ignorada, TELEFONO NO VALIDO: ["+line+"]");
					continue;
				}
				int telefono = -1;
				try{
					telefono = Integer.parseInt(tel);
				}catch(NumberFormatException e){
					if(debug) logs.debug("Linea Ignorada TELEFONO NO VALIDO: ["+line+"]");
					out.println("[ERROR] Linea Ignorada, TELEFONO NO VALIDO: ["+line+"]");
					continue;
				}
	
				String direccion = stt.nextToken();
				String tipocliente = stt.nextToken();
				
				if(tabla.equals("cable")){
					RutinaCable rc = new RutinaCable();
					rc.setCable(valorTabla);
					rc.setDireccion(direccion);
					rc.setEstatus("II");
					rc.setTelefono(telefono);
					rc.setTipoCliente(tipocliente);
				
					try {
						
						if(rutinaCableDAO.getRutinaCable((long)telefono).size()>0){
							out.println("[ERROR] Linea Ignorada, TELEFONO REPETIDO: ["+line+"]");
							continue;
						}
						
						rutinaCableDAO.insertarRutinaCable(rc);
						totalInsertados++;
					} catch (SapeDataException e) {
						if(debug) logs.error(e.toString());
						out.println( "[ERROR] "+e.toString());
					}
				} else {
					RutinaArmario ra = new RutinaArmario();
					ra.setArmario(valorTabla);
					ra.setDireccion(direccion);
					ra.setEstatus("II");
					ra.setTelefono(telefono);
					ra.setTipoCliente(tipocliente);
				
					try {
						if(rutinaArmarioDAO.getRutinaArmario((long)telefono).size()>0){
							out.println("[ERROR] Linea Ignorada, TELEFONO REPETIDO: ["+line+"]");
							continue;
						}
						rutinaArmarioDAO.insertarRutinaArmario(ra);
						totalInsertados++;
					} catch (SapeDataException e) {
						if(debug) logs.error(e.toString());
						out.println( "[ERROR] "+e.toString());
					}
				}
				
			}
		}
    		
            out.println("</pre>");
            
            out.println("<br>");
            out.println("<h2>Cantidad de registros insertados: "+totalInsertados+"</h2>");
            out.println("<br>");
            
            out.println("<p align=\"center\">");
            
            
            
            
            if(tabla.equals("cable")){
            	out.println("<a name=\"fin\" href=\"" + request.getContextPath() + "/actionSape?accion=rutinas&operacion=pantallaRutinaCable&cable=" + cable + "\">CONTINUAR</a>");
            } else if(tabla.equals("armario")){
            	out.println("<a name=\"fin\" href=\"" + request.getContextPath() + "/actionSape?accion=rutinas&operacion=pantallaRutinaArmario&armario=" + armario + "\">CONTINUAR</a>");
            }else{
            	out.println("<a name=\"fin\" href=\"" + request.getContextPath() + "/actionSape?accion=rutinas&operacion=pantallaRutinaCliente&cliente=" + cliente + "\">CONTINUAR</a>");
            }
            out.println("</p>");
    		 		
    
    }
    
    private void operacionGuardarInformacionImporteMasivos(HttpServletRequest request, HttpServletResponse response) {
    	List buenos = (List)request.getSession().getAttribute("listaBuenos");
    	String cliente = (String) request.getSession().getAttribute("cliente");
    	if(buenos == null || buenos.size() == 0) {
    		error("No hay informacion para guardar!!!.",request,response);
    		return;
    	}
    	
    	int size = buenos.size();
    	
    	RutinaCliente rc = null ;
    	int i =0, contador=0,ignorados=0;
    	for(i=0;i<size;i++) {
    		
    		String data[] = (String[])buenos.get(i);
    		
    		String telefono = data[0];
    		String nombre = data[1];
    		
    		rc = new RutinaCliente();
    		
    		rc.setNombre(nombre);
    		rc.setTelefono(Long.parseLong(telefono));
    		rc.setEstatus("II");
    		rc.setUsuario(cliente);
    		try {
    			
                List rcc=rutinaClienteDAO.getRutinasPorCliente(cliente,rc.getTelefono());
                if(rcc != null && rcc.size() > 0){
                	// verifico ke sea del mismo usuario!!!!	
                	ignorados++;
                }else{
                	rutinaClienteDAO.insertarRutinaCliente(rc);
                	contador++;
                }
			} catch (SapeDataException e) {
				error(e,request,response);
				return;
			}
    	}
    	
    	request.setAttribute("msg","Se actualizaron <font color=\"red\">"+contador+"</font> registros."+(ignorados > 0?" Ignorados "+ignorados+".":""));
    	operacionPantallaRutinaClientes(request,response);
    }
    
    
    private void operacionProcesarInformacionImporteMasivos(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
    	
    	
    	String data = request.getParameter("data");
    	
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
    	
    	request.setAttribute("listaBuenos",buenos);
    	request.getSession().setAttribute("listaBuenos",buenos);
    	request.setAttribute("listaNegados",negados);
    	
    	request.getRequestDispatcher(acciones.getTemplate("resultadoImporteMasivos")).forward(request,response);
    	
    	return;
    }
    
    private void operacionEliminarProgramacionCola(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        String cola = request.getParameter("cola");
        
        try{
            horaPruebaColaDAO.eliminarHorarioCola(cola);
            String usuario = getUsuarioSession(request);
            horaPruebaColaDAO.actualizarRastroCambio(cola, usuario, new Date());
        }catch(SapeDataException e){
            e.printStackTrace();
            error(e,request,response);
            return;
        }
        request.setAttribute("msgColas","Se elimino el horario de la cola: "+cola+".");
        operacionInicioColas(request,response);
    }
    
    private void actualizarRutinaCola(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        
        String h00 = request.getParameter("h0");
        String h01 = request.getParameter("h1");
        String h02 = request.getParameter("h2");
        String h03 = request.getParameter("h3");
        String h04 = request.getParameter("h4");
        String h05 = request.getParameter("h5");
        String h06 = request.getParameter("h6");
        String h07 = request.getParameter("h7");
        String h08 = request.getParameter("h8");
        String h09 = request.getParameter("h9");
        String h10 = request.getParameter("h10");
        String h11 = request.getParameter("h11");
        String h12 = request.getParameter("h12");
        String h13 = request.getParameter("h13");
        String h14 = request.getParameter("h14");
        String h15 = request.getParameter("h15");
        String h16 = request.getParameter("h16");
        String h17 = request.getParameter("h17");
        String h18 = request.getParameter("h18");
        
        String h19 = request.getParameter("h19");
        String h20 = request.getParameter("h20");
        String h21 = request.getParameter("h21");
        String h22 = request.getParameter("h22");
        String h23 = request.getParameter("h23");

        String lunes = request.getParameter("lunes");
        String martes = request.getParameter("martes");
        String miercoles = request.getParameter("miercoles");
        String jueves = request.getParameter("jueves");
        String viernes = request.getParameter("viernes");
        String sabado = request.getParameter("sabado");
        String domingo = request.getParameter("domingo");
        
        String cola = request.getParameter("cola");
        
        try {
        	String usuario = ((UserSipe)request.getSession().getAttribute("usuario")).getNick();
            String ret=horaPruebaColaDAO.actualizarHorarios(h00,h01,h02,h03,h04,h05,h06,
                    h07,h08,h09,h10,h11,h12,h13,
                    h14,h15,h16,h17,h18,h19,h20,
                    h21,h22,h23,lunes,martes,miercoles,
                    jueves,viernes, sabado,domingo,cola,usuario, new Date());
            
            if(ret == null)
                request.setAttribute("msgColas","Rutina insertada con exito.");
            else
                request.setAttribute("msgColas",ret);
            
            operacionInicioColas(request,response);
        } catch (SapeDataException e) {
            error(e.toString(),request,response);
            return;
        }
        
    }
    
    private void operacionInicioColas(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        List l = null, listaHorarios=null;
        try {
            l =horaPruebaColaDAO.getListaNombreColas();
            listaHorarios =horaPruebaColaDAO.getListaAllHorarios();
        } catch (SapeDataException e) {
        	logs.error(e);
            error(e.toString(),request,response);
            return;
        }
//        System.out.println("tamano de listahorarios: "+listaHorarios.size());
        request.setAttribute("listaColas",l);
        request.setAttribute("listaHorarios",listaHorarios);
        request.getRequestDispatcher(acciones.getTemplate("inicioColas")).forward(request,response);
        return;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        doGet(request,response);
        return;
    }
    
    private void operacionActualizarRutinaCliente(HttpServletRequest request, HttpServletResponse response) {
    	String cliente = request.getParameter("cliente");
    	String tipoRutina = request.getParameter("tipoRutina");
    	String tipoPrueba = request.getParameter("tipoPrueba");
    	//String habilitado = request.getParameter("habilitado");
    	Rutinas rut = new Rutinas();
    	
    	rut.setTipo("CL");
    	rut.setValorTipo(cliente);
    	rut.setTipoDePrueba(tipoPrueba);
		rut.setHabilitado(new Boolean(true));
		rut.setFechaProgramada(new Date());
		rut.setUsuario(getUsuarioSession(request));
		rut.setHabilitado(new Boolean(true));
		
    	if(tipoRutina.equals("H")){
    		String dias = request.getParameter("dias");
    		String horas = request.getParameter("horas");
    		
    		if(dias != null && dias.equals(""))
    			dias = null;
    		
    		rut.setDias(dias);
    		rut.setHoras(horas);
    		rut.setTipoHorario("H");
    	} else {
    		String fechaIni = request.getParameter("fechaIni");
    		String fechaFin = request.getParameter("fechaFin");
    		String horaIni = request.getParameter("horaIni");
    		String horaFin = request.getParameter("horaFin");
    		String minIni =  request.getParameter("minIni");
    		String minFin =  request.getParameter("minFin");
    		
    		String horai = (horaIni == null||horaIni.equals("--")||minIni==null||minIni.equals("--")?null:horaIni+":"+minIni+":00");
    		String horaf = (horaFin == null||horaFin.equals("--")||minFin==null||minFin.equals("--")?null:horaFin+":"+minFin+":00");
    		
    		rut.setFechaInicial(getDate(fechaIni));
    		rut.setFechaFinal(getDate(fechaFin));
    		rut.setHoraInicial(horai);
    		rut.setHoraFinal(horaf);
    		rut.setTipoHorario("D");  
    	}
    	
    	try {
    		String idRut = request.getParameter("idRutina");
    		Rutinas r2 = null;
    		if(idRut==null || idRut.equals("-1")){
    			r2=null;
    		}else{
    			r2=rutinasDAO.getRutina(Integer.parseInt(idRut));
    		}
    		//Rutinas r2 = (Rutinas) lista.get(0);
    		if(r2 != null){// es una actualizacion
    			
    			rut.setId(r2.getId());
    			rutinasDAO.actualizarRutina(rut);
    			registrarRastro(request, TipoRastros.ACTUALIZACION, rut, r2);
    			request.setAttribute("msg","Rutina "+rut.getValorTipo()+" actualizada con exito.");
    		}else{// es una insercion nueva. Debo validar ke no este completo el limite de rutinas.
    			
    			int limite = configCliente.getNumeroRutinas();
    			int rutinasActuales = rutinasDAO.getElementosActivos("CL",null,true).size();
    			
    			if(rutinasActuales >= limite){
    				error("Limite de rutinas de Cliente alcanzada. Se sugiere que elimine una de las rutinas Actuales.",request,response);
    				return;
    			}
    			// debo validar que no halla una rutina activa con el mismo nombre.
    			
    			List l2=rutinasDAO.getElemento(rut.getValorTipo(), true);
    			
    			
    			if(l2 != null && l2.size()>0){
    				error("Ya existe activa una rutina de Cliente "+rut.getValorTipo()+" por favor trate con uno distinto.",request,response);
    				return;
    			}
    			
    			// Para verificar si el cliente ya tenia una anterior rutina, si la tenia se pide que se habilite de nuevo.
    			l2=rutinasDAO.getElemento(rut.getValorTipo(), false);
    			
    			if(l2 != null && l2.size()>0){
    				error("Ya hay un registro en nuetra base de datos para el cliente "+rut.getValorTipo()+" si desea probar de nuevo este armario por favor habilitelo en PruebasProgramadas.",request,response);
    				return;
    			}

    			
    			rutinasDAO.insertarRutina(rut);
    			request.setAttribute("msg","Rutina "+rut.getValorTipo()+" insertada con exito.");
    		}
    		
		} catch (SapeDataException e) {
			e.printStackTrace();
			error(e,request,response);
			return;
		}
		request.setAttribute("idElementoActual",rut.getId().toString());
		operacionInicioClientes(request,response);
    }
    
    private void operacionActualizarRutinaArmario(HttpServletRequest request, HttpServletResponse response)throws IOException {
        
    	
    	String armario = request.getParameter("armario");
    	String tipoRutina = request.getParameter("tipoRutina");
    	String tipoPrueba = request.getParameter("tipoPrueba");
    	//String habilitado = request.getParameter("habilitado");
    	Rutinas rut = new Rutinas();
    	
    	rut.setTipo("AR");
    	rut.setValorTipo(armario);
    	rut.setTipoDePrueba(tipoPrueba);
		rut.setHabilitado(new Boolean(true));
		rut.setFechaProgramada(new Date());
		rut.setUsuario(getUsuarioSession(request));
		rut.setHabilitado(new Boolean(true));
		
    	if(tipoRutina.equals("H")){
    		String dias = request.getParameter("dias");
    		String horas = request.getParameter("horas");
    		
    		if(dias != null && dias.equals(""))
    			dias = null;
    		
    		rut.setDias(dias);
    		rut.setHoras(horas);
    		rut.setTipoHorario("H");
    	} else {
    		String fechaIni = request.getParameter("fechaIni");
    		String fechaFin = request.getParameter("fechaFin");
    		String horaIni = request.getParameter("horaIni");
    		String horaFin = request.getParameter("horaFin");
    		String minIni =  request.getParameter("minIni");
    		String minFin =  request.getParameter("minFin");
    		
    		String horai = (horaIni == null||horaIni.equals("--")||minIni==null||minIni.equals("--")?null:horaIni+":"+minIni+":00");
    		String horaf = (horaFin == null||horaFin.equals("--")||minFin==null||minFin.equals("--")?null:horaFin+":"+minFin+":00");
    		
    		rut.setFechaInicial(getDate(fechaIni));
    		rut.setFechaFinal(getDate(fechaFin));
    		rut.setHoraInicial(horai);
    		rut.setHoraFinal(horaf);
    		rut.setTipoHorario("D");  
    	}
    	
    	try {
    		String idRut = request.getParameter("idRutina");
    		//List lista = rutinasDAO.getElemento(armario);
    		Rutinas r2 = null;
    		if(idRut==null || idRut.equals("-1")){
    			r2=null;
    		}else{
    			r2=rutinasDAO.getRutina(Integer.parseInt(idRut));
    		}
    		//Rutinas r2 = (Rutinas) lista.get(0);
    		if(r2 != null){// es una actualizacion
    			
    			rut.setId(r2.getId());
    			rutinasDAO.actualizarRutina(rut);
    			registrarRastro(request, TipoRastros.ACTUALIZACION, rut, r2);
    			request.setAttribute("msg","rutina "+rut.getValorTipo()+" actualizada con exito.");
    		}else{// es una insercion nueva. Debo validar ke no este completo el limite de rutinas.
    			
    			int limite = configCliente.getNumeroRutinas();
    			int rutinasActuales = rutinasDAO.getElementosActivos("AR",null,true).size();
    			
    			if(rutinasActuales >= limite){
    				error("Limite de rutinas para Armarios alcanzada. Se sugiere que elimine una de las rutinas Actuales.",request,response);
    				return;
    			}
    			// debo validar ke ya no haya un elemento con este armario.
    			
    			List l2=rutinasDAO.getElemento(rut.getValorTipo(), true);
    			
    			
    			if(l2 != null && l2.size()>0){
    				error("Ya existe una rutina activa con el Armario "+rut.getValorTipo()+" por favor trate con otro armario.",request,response);
    				return;
    			}
    			
    			l2 = null;
    			// Para verificar si el armario ya tenia una anterior rutina, si la tenia se pide que se habilite de nuevo.
    			l2=rutinasDAO.getElemento(rut.getValorTipo(), false);
    			
    			if(l2 != null && l2.size()>0){
    				for(int k=0;k<l2.size();k++){
    					Rutinas rr = (Rutinas) l2.get(k);
    					if(rr.getTipo().equals(rut.getTipo())){// ooh i got it, esta repetido!
    	    				error("Ya hay un registro en nuetra base de datos con el Armario "+rut.getValorTipo()+" si desea probar de nuevo este armario por favor habilitelo en PruebasProgramadas.",request,response);
    	    				return;		
    					}
    				}
    			}
    			
    			
    			rutinasDAO.insertarRutina(rut);
    			request.setAttribute("msg","rutina "+rut.getValorTipo()+" insertada con exito.");
    		}
    		
		} catch (SapeDataException e) {
			e.printStackTrace();
			error(e,request,response);
			return;
		}
		request.setAttribute("idElementoActual",rut.getId().toString());
		operacionInicioArmarios(request,response);
    }
    
    private void operacionActualizarRutinaCable(HttpServletRequest request, HttpServletResponse response) {      

    	String cable = request.getParameter("cable");
    	String tipoRutina = request.getParameter("tipoRutina");
    	String tipoPrueba = request.getParameter("tipoPrueba");
    	//String habilitado = request.getParameter("habilitado");
    	Rutinas rut = new Rutinas();
    	
    	rut.setTipo("CA");
    	rut.setValorTipo(cable);
    	rut.setTipoDePrueba(tipoPrueba);
		rut.setHabilitado(new Boolean(true));
		rut.setFechaProgramada(new Date());
		rut.setUsuario(getUsuarioSession(request));
		rut.setHabilitado(new Boolean(true));
		
    	if(tipoRutina.equals("H")){
    		String dias = request.getParameter("dias");
    		String horas = request.getParameter("horas");
    		
    		if(dias != null && dias.equals(""))
    			dias = null;
    		
    		rut.setDias(dias);
    		rut.setHoras(horas);
    		rut.setTipoHorario("H");
    	} else {
    		String fechaIni = request.getParameter("fechaIni");
    		String fechaFin = request.getParameter("fechaFin");
    		String horaIni = request.getParameter("horaIni");
    		String horaFin = request.getParameter("horaFin");
    		String minIni =  request.getParameter("minIni");
    		String minFin =  request.getParameter("minFin");
    		
    		String horai = (horaIni == null||horaIni.equals("--")||minIni==null||minIni.equals("--")?null:horaIni+":"+minIni+":00");
    		String horaf = (horaFin == null||horaFin.equals("--")||minFin==null||minFin.equals("--")?null:horaFin+":"+minFin+":00");
    		
    		rut.setFechaInicial(getDate(fechaIni));
    		rut.setFechaFinal(getDate(fechaFin));
    		rut.setHoraInicial(horai);
    		rut.setHoraFinal(horaf);
    		rut.setTipoHorario("D");  
    	}
    	
    	try {

    		
    		String idRut = request.getParameter("idRutina");
    		Rutinas r2 = null;
    		if(idRut==null || idRut.equals("-1")){
    			r2=null;
    		}else{
    			r2=rutinasDAO.getRutina(Integer.parseInt(idRut));
    		}
    		//Rutinas r2 = (Rutinas) lista.get(0);
    		if(r2 != null){// es una actualizacion
    		
    			rut.setId(r2.getId());
    			rutinasDAO.actualizarRutina(rut);
    			registrarRastro(request, TipoRastros.ACTUALIZACION, rut, r2);
    			request.setAttribute("msg","Rutina "+rut.getValorTipo()+" actualizada con exito.");
    		}else{// es una insercion nueva. Debo validar ke no este completo el limite de rutinas.
    			
    			int limite = configCliente.getNumeroRutinas();
    			int rutinasActuales = rutinasDAO.getElementosActivos("CA",null,true).size();
    			
    			if(rutinasActuales >= limite){
    				error("Limite de rutinas para Cables alcanzada. Se sugiere que elimine una de las rutinas Actuales.",request,response);
    				return;
    			}
    			
    			// valido que el cable no tenga registros de rutina activos
    			List l2=rutinasDAO.getElemento(rut.getValorTipo(), true);
    			
    			
    			if(l2 != null && l2.size()>0){
    				for(int k=0;k<l2.size();k++){
    					Rutinas rr = (Rutinas) l2.get(k);
    					if(rr.getTipo().equals(rut.getTipo())){// ooh i got it, esta repetido!
    	    				error("Ya hay un registro en nuetra base de datos con el Cable "+rut.getValorTipo()+" si desea probar de nuevo este cable por favor habilitelo en PruebasProgramadas.",request,response);
    	    				return;		
    					}
    				}
    			}			
    			
    			// Para verificar si el cable ya tenia una anterior rutina, si la tenia se pide que se habilite de nuevo.
    			l2=rutinasDAO.getElemento(rut.getValorTipo(), false);
    			
    			if(l2 != null && l2.size()>0){
    				error("Ya hay un registro en nuetra base de datos con el Cable "+rut.getValorTipo()+" si desea probar de nuevo este armario por favor habilitelo en PruebasProgramadas.",request,response);
    				return;
    			}
    			
    			rutinasDAO.insertarRutina(rut);
    			request.setAttribute("msg","rutina "+rut.getValorTipo()+" insertada con exito.");
    		}
    		
		} catch (SapeDataException e) {
			e.printStackTrace();
			error(e,request,response);
			return;
		}
		request.setAttribute("idElementoActual",rut.getId().toString());
		operacionInicioCables(request,response);
    	
    	
        /*
    	String h19 = request.getParameter("c19");
        String h20 = request.getParameter("c20");
        String h21 = request.getParameter("c21");
        String h22 = request.getParameter("c22");
        String h23 = request.getParameter("c23");
        String h00 = request.getParameter("c00");
        String h01 = request.getParameter("c01");
        String h02 = request.getParameter("c02");
        String h03 = request.getParameter("c03");
        String h04 = request.getParameter("c04");
        String h05 = request.getParameter("c05");
        String h06 = request.getParameter("c06");
        
        String cable = request.getParameter("cable");
        
        //horaPruebaCableDAO.
        String box = null;
        try {
            box=horaPruebaCableDAO.actualizarHorarioPruebaCables(cable,h19,h20,h21,h22,h23,h00,h01,h02,h03,h04,h05,h06);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        if(box != null){
            //no se pudo realizar la actualizacion por algun motivo
            request.setAttribute("tipo","cerrar");
            error(box,request,response);
            return;
        }
        request.setAttribute("msgCables","Horario Preparado!");
        operacionConsultaHorarioCables(request, response);
        */
    }
    
    private void operacionEliminarHorarioArmario(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String armario =request.getParameter("idArmario");
        String nombre = request.getParameter("nombre");
                
        try {
        	int id = Integer.parseInt(armario);
        	Rutinas rut = rutinasDAO.getRutina(id);
        	//XXX: 2006-07-05: de ahora en adelante no se borran las rutinas, solo cambian de estado habilitado a deshabilitado!!!!
        	//rutinasDAO.eliminarRutina(id);
        	
        	Rutinas rut2 = rutinasDAO.getRutina(id);
        	rut2.setHabilitado(new Boolean(false));
        	rut2.setFechaProgramada(new Date());
        	registrarRastro(request, TipoRastros.ACTUALIZACION, rut, rut2);
        	rutinasDAO.actualizarRutina(rut2);
            //rutinaArmarioDAO.eliminarAllArmario(armario);
            //horaPruebaArmarioDAO.eliminarArmario(armario);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        request.setAttribute("msg","Se retiro el armario "+nombre+" con exito.");
        operacionInicioArmarios(request,response);
    }
    
    private void operacionEliminarHorarioCable(HttpServletRequest request, HttpServletResponse response) {
        String cable = request.getParameter("idCable");
        String nombre = request.getParameter("nombre");
                
        try {
        	int id = Integer.parseInt(cable);
        	Rutinas rut = rutinasDAO.getRutina(id);
        	Rutinas rut2 = rutinasDAO.getRutina(id);
        	rut2.setHabilitado(new Boolean(false));
        	rut2.setFechaProgramada(new Date());
        	registrarRastro(request, TipoRastros.ACTUALIZACION, rut, rut2);
        	rutinasDAO.actualizarRutina(rut2);
        	
        	//rutinasDAO.eliminarRutina(id);
        	//registrarRastro(request, TipoRastros.ELIMINACION, rut, null);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        request.setAttribute("msg","Se retiro el cable "+nombre+" con exito.");
        operacionInicioCables(request,response);
        
        /*
        try {
            rutinaCableDAO.eliminarAllCable(cable);
            horaPruebaCableDAO.eliminarCable(cable);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        request.setAttribute("msgCables","Se retiro el cable "+cable+".");
        operacionInicioCables(request,response);*/
        
    }
    
    private void operacionCargarHorarioCable(HttpServletRequest request, HttpServletResponse response)throws IOException {
        
        String cable = request.getParameter("cable");
        Rutinas l1 = null;
        try {
            l1=rutinasDAO.getRutina(Integer.parseInt(cable));
        } catch (SapeDataException e) {
        	e.printStackTrace();
            error(e,request,response);
            return;
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //TODO: 1. identificar ke tipo de rutina es
        //		2. si es horas rellenar los combos con la info
        //		3. si es definida rellenar los campos de fechas y horas.
        //		4. ojo ke falta la prueba
        
        
        out.print("<script languaje=\"text/javascript\" src=\"javascript/varios.js\"> </script> \n");
        out.print("<script type=\"text/javascript\">\n"); 
        out.print("<!--\n");
        
        
        /*
        out.print("var armar = window.parent.document.getElementById(\"campoArmario\");\n");
        out.print("armar.disabled = true;\n");
        out.print("armar.value = '"+l1.getValorTipo()+"';\n");
        
        out.print("seleccionarComboValue('selectTipoPruebas','"+l1.getTipoDePrueba()+"');");
        
        out.print("var user = window.parent.document.getElementById(\"usuario\");\n");
        out.print("user.value='"+l1.getUsuario()+"';\n");
        
        out.print("seleccionarComboValue('selectHabilitado','"+l1.getHabilitado()+"');");
        
        out.print("var fechaProgramado = window.parent.document.getElementById(\"fechaProgramado\");\n");
        out.print("fechaProgramado.value='"+l1.getFechaProgramada()+"';\n");
        
        // Identifico que tipo de rutina es y dependiendo de eso llevo la info a la forma correspondiente
        out.print("var hora1 = window.parent.document.getElementById(\"horas\");\n");
        out.print("var fecha1 = window.parent.document.getElementById(\"fechas\");\n");
        */
        
        
        if(l1.getTipoHorario().equals("H")){//la rutina es por horas!!!

        	out.println("window.parent.cargarDatosHorarioPorHoras("+
        			"'"+l1.getValorTipo()+"','"+l1.getTipoDePrueba()+"','"+l1.getUsuario()+"','"+l1.getFechaProgramada()+
        			"','"+l1.getHoras()+"','"+(l1.getDias()==null||l1.getDias().equals("")?"":l1.getDias())+"','"+l1.getId()+"');");
        	
        	/*
            out.print("fecha1.checked=false;\n");
            out.print("hora1.checked=true;\n");
            
            StringTokenizer stt = new StringTokenizer(l1.getHoras(),",");
            
            while(stt.hasMoreTokens()){
            	out.print("var checkHora = window.parent.document.getElementById(\"check"+stt.nextToken()+"\");\n");
            	out.print("checkHora.checked=true;");
            }            

            if(l1.getDias()!=null&&!l1.getDias().equals("")){

	            stt = new StringTokenizer(l1.getDias(),",");
	            
	            while(stt.hasMoreTokens()){
	            	out.print("var diacheck = window.parent.document.getElementById(\""+stt.nextToken()+"\");\n");
	            	out.print("diacheck.checked=true;");
	            }
            }
            */
        } else if(l1.getTipoHorario().equals("D")){//la rutina esta definida por el usuario.
        	
        	String horaini = l1.getHoraInicial();
        	String horafin = l1.getHoraFinal();
        	
        	out.println("window.parent.cargarDatosHorarioPorFechas("+
        			"'"+l1.getValorTipo()+"','"+l1.getTipoDePrueba()+"','"+l1.getUsuario()+"','"+l1.getFechaProgramada()+
        			"','"+(l1.getFechaInicial()!=null?getFechaFormato(l1.getFechaInicial(),"yyyy-mm-dd"):"")+"','"+
        			(l1.getFechaFinal()!=null?getFechaFormato(l1.getFechaFinal(),"yyyy-mm-dd"):"")+"','"+
        			(l1.getHoraInicial()!= null?horaini.substring(0,2)+"','"+horaini.substring(3,5)+"','":"','','")+
        			(l1.getHoraFinal()!= null?horafin.substring(0,2)+"','"+horafin.substring(3,5)+"'":"',''")+",'"+l1.getId()+"');");

        	/*
            out.print("fecha1.checked=true;\n");
            out.print("hora1.checked=false;\n");
            
            if(l1.getFechaInicial()!=null){
            	out.print("var checkFechaInicial = window.parent.document.getElementById(\"checkFechaInicial\");\n");
            	out.print("var fechai = window.parent.document.getElementById(\"fechaIni\");\n");
            	out.print("checkFechaInicial.checked=true;fechai.value='"+getFechaFormato(l1.getFechaInicial(),"yyyy-mm-dd")+"';");
            }
        	
            if(l1.getFechaFinal()!=null){
            	out.print("var checkFechaFinal = window.parent.document.getElementById(\"checkFechaFinal\");\n");
            	out.print("var fechaf = window.parent.document.getElementById(\"fechaFin\");\n");
            	out.print("checkFechaFinal.checked=true;fechaf.value='"+getFechaFormato(l1.getFechaFinal(),"yyyy-mm-dd")+"';");
            }
            
            if(l1.getHoraInicial()!= null){
            	String horaini = l1.getHoraInicial();
            	out.print("seleccionarCombo('selectHorasInicial','"+horaini.substring(0,2)+"');");
            	out.print("seleccionarCombo('selectMinutosInicial','"+horaini.substring(3,5)+"');");
            }
            if(l1.getHoraFinal()!=null){
            	String horafin = l1.getHoraFinal();            	
            	out.print("seleccionarCombo('selectHorasFinal','"+horafin.substring(0,2)+"');");
            	out.print("seleccionarCombo('selectMinutosFinal','"+horafin.substring(3,5)+"');");
            }
            */
        } else {
        	error("Error en los parametros",request,response);
        	logs.error("Algo inesperado paso tratando de cargar la rutina: "+l1);
        	return;
        }
        // habilito los botones de guardar y eliminar!!!!!!!
        /*
        out.print("var botonGuardar = window.parent.document.getElementById(\"botonGuardar\");\n");
        out.print("var botonEliminar = window.parent.document.getElementById(\"botonEliminar\");\n");
        out.print("botonGuardar.disabled=false;botonEliminar.disabled=false;\n");
        */
        out.print("//-->\n");
        out.print("</script>\n");

    	
    	
    	
    	
    	/*
    	String cable = request.getParameter("cable");
        List l1 = null;
        try {
            l1=horaPruebaCableDAO.getHorariosCable(cable);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        System.out.println("TAMANO DE LA LISTA: "+l1.size());
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<script languaje=\"text/javascript\" src=\"javascript/varios.js\"> </script> \n");
        out.print("<script type=\"text/javascript\">\n"); 
        out.print("<!--\n");
        for(int i=1;i<13;i++){
            out.print("var check = window.parent.document.getElementById(\"cab"+i+"\");\n");
            out.print( "check.checked ="+l1.get(i-1)+";\n");
        }
        out.print("var inputCable = window.parent.document.getElementById(\"campoCable\");\n");
        out.print("inputCable.value ='"+cable+"'");
        
        out.print("//-->\n");
        out.print("</script>\n");*/        
    }
    
    private void operacionCargarHorarioCliente(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String cliente = request.getParameter("cliente");
        Rutinas l1 = null;
        try {
            l1=rutinasDAO.getRutina(Integer.parseInt(cliente));
        } catch (SapeDataException e) {
        	e.printStackTrace();
            error(e,request,response);
            return;
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.print("<script languaje=\"text/javascript\" src=\"javascript/varios.js\"> </script> \n");
        out.print("<script type=\"text/javascript\">\n"); 
        out.print("<!--\n");
        
        if(l1.getTipoHorario().equals("H")){//la rutina es por horas!!!

        	out.println("window.parent.cargarDatosHorarioPorHoras("+
        			"'"+l1.getValorTipo()+"','"+l1.getTipoDePrueba()+"','"+l1.getUsuario()+"','"+l1.getFechaProgramada()+
        			"','"+l1.getHoras()+"','"+(l1.getDias()==null||l1.getDias().equals("")?"":l1.getDias())+"','"+l1.getId()+"');");
        	
        } else if(l1.getTipoHorario().equals("D")){//la rutina esta definida por el usuario.
        	
        	String horaini = l1.getHoraInicial();
        	String horafin = l1.getHoraFinal();
        	
        	out.println("window.parent.cargarDatosHorarioPorFechas("+
        			"'"+l1.getValorTipo()+"','"+l1.getTipoDePrueba()+"','"+l1.getUsuario()+"','"+l1.getFechaProgramada()+
        			"','"+(l1.getFechaInicial()!=null?getFechaFormato(l1.getFechaInicial(),"yyyy-mm-dd"):"")+"','"+
        			(l1.getFechaFinal()!=null?getFechaFormato(l1.getFechaFinal(),"yyyy-mm-dd"):"")+"','"+
        			(l1.getHoraInicial()!= null?horaini.substring(0,2)+"','"+horaini.substring(3,5)+"','":"','','")+
        			(l1.getHoraFinal()!= null?horafin.substring(0,2)+"','"+horafin.substring(3,5)+"'":"',''")+",'"+l1.getId()+"');");


        } else {
        	error("Error en los parametros",request,response);
        	logs.error("Algo inesperado paso tratando de cargar la rutina: "+l1);
        	return;
        }
        out.print("//-->\n");
        out.print("</script>\n");

    }
    
    private void operacionCargarHorarioArmario(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String armario = request.getParameter("armario");
        Rutinas l1 = null;
        try {
            l1=rutinasDAO.getRutina(Integer.parseInt(armario));
        } catch (SapeDataException e) {
        	e.printStackTrace();
            error(e,request,response);
            return;
        }
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //TODO: 1. identificar ke tipo de rutina es
        //		2. si es horas rellenar los combos con la info
        //		3. si es definida rellenar los campos de fechas y horas.
        //		4. ojo ke falta la prueba
        
        
        out.print("<script languaje=\"text/javascript\" src=\"javascript/varios.js\"> </script> \n");
        out.print("<script type=\"text/javascript\">\n"); 
        out.print("<!--\n");
        
        
        /*
        out.print("var armar = window.parent.document.getElementById(\"campoArmario\");\n");
        out.print("armar.disabled = true;\n");
        out.print("armar.value = '"+l1.getValorTipo()+"';\n");
        
        out.print("seleccionarComboValue('selectTipoPruebas','"+l1.getTipoDePrueba()+"');");
        
        out.print("var user = window.parent.document.getElementById(\"usuario\");\n");
        out.print("user.value='"+l1.getUsuario()+"';\n");
        
        out.print("seleccionarComboValue('selectHabilitado','"+l1.getHabilitado()+"');");
        
        out.print("var fechaProgramado = window.parent.document.getElementById(\"fechaProgramado\");\n");
        out.print("fechaProgramado.value='"+l1.getFechaProgramada()+"';\n");
        
        // Identifico que tipo de rutina es y dependiendo de eso llevo la info a la forma correspondiente
        out.print("var hora1 = window.parent.document.getElementById(\"horas\");\n");
        out.print("var fecha1 = window.parent.document.getElementById(\"fechas\");\n");
        */
        
        
        if(l1.getTipoHorario().equals("H")){//la rutina es por horas!!!

        	out.println("window.parent.cargarDatosHorarioPorHoras("+
        			"'"+l1.getValorTipo()+"','"+l1.getTipoDePrueba()+"','"+l1.getUsuario()+"','"+l1.getFechaProgramada()+
        			"','"+l1.getHoras()+"','"+(l1.getDias()==null||l1.getDias().equals("")?"":l1.getDias())+"', '"+l1.getId()+"');");
        	
        	/*
            out.print("fecha1.checked=false;\n");
            out.print("hora1.checked=true;\n");
            
            StringTokenizer stt = new StringTokenizer(l1.getHoras(),",");
            
            while(stt.hasMoreTokens()){
            	out.print("var checkHora = window.parent.document.getElementById(\"check"+stt.nextToken()+"\");\n");
            	out.print("checkHora.checked=true;");
            }            

            if(l1.getDias()!=null&&!l1.getDias().equals("")){

	            stt = new StringTokenizer(l1.getDias(),",");
	            
	            while(stt.hasMoreTokens()){
	            	out.print("var diacheck = window.parent.document.getElementById(\""+stt.nextToken()+"\");\n");
	            	out.print("diacheck.checked=true;");
	            }
            }
            */
        } else if(l1.getTipoHorario().equals("D")){//la rutina esta definida por el usuario.
        	
        	String horaini = l1.getHoraInicial();
        	String horafin = l1.getHoraFinal();
        	
        	out.println("window.parent.cargarDatosHorarioPorFechas("+
        			"'"+l1.getValorTipo()+"','"+l1.getTipoDePrueba()+"','"+l1.getUsuario()+"','"+l1.getFechaProgramada()+
        			"','"+(l1.getFechaInicial()!=null?getFechaFormato(l1.getFechaInicial(),"yyyy-mm-dd"):"")+"','"+
        			(l1.getFechaFinal()!=null?getFechaFormato(l1.getFechaFinal(),"yyyy-mm-dd"):"")+"','"+
        			(l1.getHoraInicial()!= null?horaini.substring(0,2)+"','"+horaini.substring(3,5)+"','":"','','")+
        			(l1.getHoraFinal()!= null?horafin.substring(0,2)+"','"+horafin.substring(3,5)+"'":"',''")+",'"+l1.getId()+"');");

        	/*
            out.print("fecha1.checked=true;\n");
            out.print("hora1.checked=false;\n");
            
            if(l1.getFechaInicial()!=null){
            	out.print("var checkFechaInicial = window.parent.document.getElementById(\"checkFechaInicial\");\n");
            	out.print("var fechai = window.parent.document.getElementById(\"fechaIni\");\n");
            	out.print("checkFechaInicial.checked=true;fechai.value='"+getFechaFormato(l1.getFechaInicial(),"yyyy-mm-dd")+"';");
            }
        	
            if(l1.getFechaFinal()!=null){
            	out.print("var checkFechaFinal = window.parent.document.getElementById(\"checkFechaFinal\");\n");
            	out.print("var fechaf = window.parent.document.getElementById(\"fechaFin\");\n");
            	out.print("checkFechaFinal.checked=true;fechaf.value='"+getFechaFormato(l1.getFechaFinal(),"yyyy-mm-dd")+"';");
            }
            
            if(l1.getHoraInicial()!= null){
            	String horaini = l1.getHoraInicial();
            	out.print("seleccionarCombo('selectHorasInicial','"+horaini.substring(0,2)+"');");
            	out.print("seleccionarCombo('selectMinutosInicial','"+horaini.substring(3,5)+"');");
            }
            if(l1.getHoraFinal()!=null){
            	String horafin = l1.getHoraFinal();            	
            	out.print("seleccionarCombo('selectHorasFinal','"+horafin.substring(0,2)+"');");
            	out.print("seleccionarCombo('selectMinutosFinal','"+horafin.substring(3,5)+"');");
            }
            */
        } else {
        	error("Error en los parametros",request,response);
        	logs.error("Algo inesperado paso tratando de cargar la rutina: "+l1);
        	return;
        }
        // habilito los botones de guardar y eliminar!!!!!!!
        /*
        out.print("var botonGuardar = window.parent.document.getElementById(\"botonGuardar\");\n");
        out.print("var botonEliminar = window.parent.document.getElementById(\"botonEliminar\");\n");
        out.print("botonGuardar.disabled=false;botonEliminar.disabled=false;\n");
        */
        out.print("//-->\n");
        out.print("</script>\n");
        
        
        //logs.debug("TERMINO LA RUTINA!!!!!!!");
    }
    
    private void operacionActualizarRutinaCableArmario(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        
        String horas[]=new String[12];
        horas[0] = request.getParameter("r19");
        horas[1] = request.getParameter("r20");
        horas[2] = request.getParameter("r21");
        horas[3] = request.getParameter("r22");
        horas[4] = request.getParameter("r23");
        horas[5] = request.getParameter("r00");
        horas[6] = request.getParameter("r01");
        horas[7] = request.getParameter("r02");
        horas[8] = request.getParameter("r03");
        horas[9] = request.getParameter("r04");
        horas[10] = request.getParameter("r05");
        horas[11] = request.getParameter("r06");
        
        String tecnologia = request.getParameter("tecnologia");
        String armario= request.getParameter("armario");
        String cable= request.getParameter("cable");
        String central=request.getParameter("central");
        System.out.println("tecnologia: "+tecnologia+" cable="+cable+" armario="+armario+" central="+central);
        String cables[]=new String[12];
        String armarios[]=new String[12];
        
        for(int i=0;i<12;i++){
            String h = horas[i];
            if(h.equalsIgnoreCase("CABLE")){
                cables[i]="1";
                armarios[i]="0";
            }else if(h.equalsIgnoreCase("ARMARIO")){
                cables[i]  ="0";
                armarios[i]="1";
            }else{
                cables[i]="0";
                armarios[i]="0";
            }
        }

        request.getRequestDispatcher(acciones.getTemplate("programacionCableArmario")).forward(request,response);
        return;
    }
    
    private void operacionEliminarRutinaCables(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String tel = request.getParameter("telefono");
        int total = 0;
        String cable = request.getParameter("cable");
        try {
        
	        if(tel.equals("todos")){
//	        	cable = request.getParameter("cable");
	        	rutinaCableDAO.eliminarAllCable(cable);
	        }else{
	        
	        	StringTokenizer st = new StringTokenizer(tel,",");
	        	total =st.countTokens();
	        	
	        	while(st.hasMoreTokens()){
	        		int tele = Integer.parseInt(st.nextToken());
	        		rutinaCableDAO.eliminarRutinaCable(tele);
	        	}
	        }
	        
        } catch (SapeDataException e1) {
            error("Ocurrio un error al intentar eliminar una rutina,\ncausado por:\n"+e1.toString(),request,response);
            return;
        }
        
        String result = (tel.equals("todos") ? " todo el cable: "+ cable : String.valueOf(total) +" rutina(s)");
        
        request.setAttribute("msg","Se elimino "+result+" con exito.");
        
        request.getSession().setAttribute("msgTemporal","Se elimin&oacute; "+result+" con &eacute;xito.");
        response.sendRedirect(request.getContextPath() + "/actionSape?accion=rutinas&operacion=pantallaRutinaCable&cable=" + cable);
    }
    
    
    private void operacionConsultaHorarioCables(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        List l = null, l2 = new ArrayList();

        String cable =request.getParameter("cable");
        
        System.out.println("el cable a consultar es: "+cable);
        
        try {
            l=horaPruebaCableDAO.getHorariosCable(cable);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        String v[] = { "19" , "20" , "21" , "22" , "23" , "00" , "01" , "02" , "03" , "04" , "05" , "06"};

        for(int i=0;i<v.length;i++){
            String s =(String)l.get(i);
            HoraCliente hc = new HoraCliente(v[i],s);
            l2.add(hc);
            hc =null;
        }
        
        request.setAttribute("listaHoraCable",l2);
        request.setAttribute("cable",cable);
        request.getRequestDispatcher(acciones.getTemplate("programacionCables")).forward(request,response);
    }
    
    private void operacionPantallaRutinaCables(HttpServletRequest request, HttpServletResponse response) {

        String cable = request.getParameter("cable");
        
        List l = null;
        
        //PRIMERO SE VALIDA SI ESTE ARMARIO ESTA EN LA TABLA DE HORARIOS DE ARMARIOS.
        String estado = request.getParameter("estado");        
    	if(estado != null && estado.equals(""))
    		estado = null;
    	
    	try {
            /*if(!horaPruebaCableDAO.existsCable(cable)){
                request.setAttribute("tipo","cerrar");
                error("El cable <b>"+cable+"</b> no tiene un horario asignado de pruebas.\n"+
                        "Regrese a la pantalla anterior e ingrese un horario de pruebas para este.",request,response);
                return;
            } */  
        
            l= rutinaCableDAO.getTelefonosPorCable(cable,estado);
        } catch (SapeDataException e) {
            logs.error(e);
            error(e,request,response);
            return;
        }
        
        if(((List)l.get(0)).size() < 1) {
        	//request.setAttribute("msg","No se encontraron registros para este cable.<br>Se sugiere importar informacion desde fenix.");
        	request.setAttribute("msg","No se encontraron registros para este cable.");
        }
        
        String mensajeTemporal = (String) request.getSession().getAttribute("msgTemporal");
        if (mensajeTemporal != null) {
        	request.setAttribute("msg",mensajeTemporal);
        	request.getSession().removeAttribute("msgTemporal");
        }
               
        request.getSession().setAttribute("cable",cable);
        
        request.setAttribute("listaRutinas",l.get(0));
        request.setAttribute("estadistica",l.get(1));
        request.setAttribute("estado",estado);
        request.setAttribute("cable",cable);
        
        //atributo para fijar un destino a la forma de subir archivos!!!!
        request.setAttribute("destino","actionSape?accion=rutinas&operacion=procesarArchivo&tipo=cable");
        
        String size = null;
        size = "'"+((List)l.get(0)).size()+"'";
        request.setAttribute("totalRutinas",size);
        redireccionarConPlantilla("pantallaRutinaCables", request, response);
    }
    
    
    private void operacionEliminarRutinaArmarios(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String tel = request.getParameter("telefono");
        String armario = request.getParameter("armario");
        int total = 0;
        try {
			if(tel.equals("todos")){
				rutinaArmarioDAO.eliminarAllArmario(armario);
			}else{
			
				StringTokenizer st = new StringTokenizer(tel,",");
				total =st.countTokens();
			
				while(st.hasMoreTokens()){
			 
					int tele = Integer.parseInt(st.nextToken());
					rutinaArmarioDAO.eliminarRutinaArmario(tele);		
				}
			}				
        } catch (SapeDataException e1) {
            error("Ocurrio un error al intentar eliminar una rutina,\ncausado por:\n"+e1.toString(),request,response);
            return;
        }
        
        
        	//Mando un mensaje temporal al servlet que lista los telefonos.
	    String result = (tel.equals("todos")?" todo el armario: "+armario:String.valueOf(total)+" registro(s)");
        request.getSession().setAttribute("msgTemporal","Se elimin&oacute; "+result+" con &eacute;xito.");
//        operacionPantallaRutinaArmarios(request,response);
        response.sendRedirect(request.getContextPath() + "/actionSape?accion=rutinas&operacion=pantallaRutinaArmario&armario=" + armario);
    }
    
    private void operacionConsultaHorarioArmarios(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        List l = null, l2 = new ArrayList();

        String armario =request.getParameter("armario");
        System.out.println("horario para el armario: "+armario);
        try {
            l = horaPruebaArmarioDAO.getHorariosArmario(armario);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        String v[] = { "19" , "20" , "21" , "22" , "23" , "00" , "01" , "02" , "03" , "04" , "05" , "06"};

        for(int i=0;i<v.length;i++){
            String s =(String)l.get(i);
            System.out.println("tam "+s.length());
            HoraCliente hc = new HoraCliente(v[i],s);
            l2.add(hc);
            hc =null;
        }
        
        request.setAttribute("listaHoraArmario",l2);
        request.setAttribute("armario",armario);	
        request.getRequestDispatcher(acciones.getTemplate("programacionArmarios")).forward(request,response);
           
    }
    
    private void operacionPantallaRutinaArmarios(HttpServletRequest request, HttpServletResponse response) {

        String armario = request.getParameter("armario");

        List l = null;
        
    	String estado = request.getParameter("estado");        
        try {
            //ESTE METODO DEBE TRAER LOS TELEFONOS DE ESTE ARMARIO DESDE DE LA
            //TABLA rutina_armario!!!! el jsp pantallaRutinaArmarios tiene un
            //BOTON PARA IMPORTAR INFORMACION DESDE FENIX
       
        	if(estado != null && estado.equals(""))
        		estado = null;
        	
            l= rutinaArmarioDAO.getTelefonosPorArmario(armario,estado);
        } catch (SapeDataException e) {
            e.printStackTrace();
            request.setAttribute("tipo","cerrar");
            error(e,request,response);
            return;
        }
        if(((List)l.get(0)).size() < 1){
            //request.setAttribute("msg","No se encontraron registros para este armario.<br>Se sugiere importar informacion desde fenix.");
        	request.setAttribute("msg","No se encontraron registros para este armario.");
        }
        
        	//verifico si me llego el mensaje temporal cuando se elimina
        String mensajeTemporal = (String) request.getSession().getAttribute("msgTemporal");
        if (mensajeTemporal != null) {
        	request.setAttribute("msg",mensajeTemporal);
        	request.getSession().removeAttribute("msgTemporal");
        }
        
        request.setAttribute("listaRutinas",l.get(0));
        request.setAttribute("armario",armario);
        request.setAttribute("estado",estado);

        
        
        request.getSession().setAttribute("armario",armario);
        
        
        //atributo para fijar un destino a la forma de subir archivos!!!!
        request.setAttribute("destino","actionSape?accion=rutinas&operacion=procesarArchivo&tipo=armario");
        
        String size = null;
        size = "'"+((List)l.get(0)).size()+"'";
        
        request.setAttribute("totalRutinas",size);
        request.setAttribute("estadistica",l.get(1));
        
        redireccionarConPlantilla("pantallaRutinaArmarios",request,response);
        //request.getRequestDispatcher(acciones.getTemplate("pantallaRutinaArmarios")).forward(request,response);
    }
    
    private void operacionActualizarCombos(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String central = request.getParameter("central");
        String tecnologia = request.getParameter("tecnologia");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<script languaje=\"text/javascript\" src=\"javascript/common.js\"> </script> \n");
        out.print("<script type=\"text/javascript\">\n"); 
        out.print("<!--\n");
        out.print("var combo = window.parent.document.getElementById(\"selectCables"+tecnologia+"\");\n");
        out.print( "combo.options.length = 1;\n");
        
        out.print("var combo2 = window.parent.document.getElementById(\"selectArmarios"+tecnologia+"\");\n");
        out.print( "combo2.options.length = 1;\n");
        
//        System.out.println("LA CENTRAL KE SE AGARRO ES( con upper case): "+central.toUpperCase());
        
        List l = null;
        try {
            String centr = central.toUpperCase();
            l = cableDAO.getListadoCablesPorCentral(centr);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        for(int i=0;i<l.size();i++){
            String cc =(String) l.get(i);
            out.print( "addOpcion(combo,\""+cc+"\",\""+cc+"\");\n");
        }
        l = null;
        try {
            String centr = central.toUpperCase();
            l = cableDAO.getListadoArmariosPorCentral(centr);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        for(int i=0;i<l.size();i++){
            String cc =(String) l.get(i);
            out.print( "addOpcion(combo2,\""+cc+"\",\""+cc+"\");\n");
        }
        
        out.print("//-->\n");
        out.print("</script>\n");
    }
    /*
    private void operacionActualizarRutinaClientes(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        
        String array[] = new String[24];
        
        for(int i=0; i<24;i++){
        	array[i]=request.getParameter("c"+i);
        	
        	if(array[i] == null)
        		array[i] = "0";
        }
        
        HoraPruebaCliente hp = new HoraPruebaCliente();
        try {
			hp.setAllValues(array);
		} catch (SapeDataException e) {
			error(e,request,response);
			return;
		}
        try {

            horaPruebaClienteDAO.actualizarHorarioPruebaClientes(hp);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        operacionConsultaHorarioClientes(request,response);
        return;
    }
    */
    private void operacionConsultaHorarioClientes(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
        List l = null;
        
        try {
            l = horaPruebaClienteDAO.getHorariosCliente();
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        /*String v[] = { "19" , "20" , "21" , "22" , "23" , "00" , "01" , "02" , "03" , "04" , "05" , "06"};
        System.out.println("LLLEGOOOOOO");
        for(int i=0;i<v.length;i++){
            String s =(String)l.get(i);
            System.out.println("tam "+s.length());
            HoraCliente hc = new HoraCliente(v[i],s);
            l2.add(hc);
            hc =null;
        }*/
        
        HoraPruebaCliente hp = (HoraPruebaCliente)l.get(0);
        
        l = new ArrayList();
        l.add(0,hp.toArray());
        request.setAttribute("lista",l);
        request.getRequestDispatcher(acciones.getTemplate("programacionClientes")).forward(request,response);
        
    }
    
    private void operacionEliminarRutinaClientes(HttpServletRequest request, HttpServletResponse response) {
        String tel = request.getParameter("telefono");
        String cliente = request.getParameter("cliente");
        long telefono = 0;
        try {
            telefono = Long.parseLong(tel);
        } catch (NumberFormatException e) {
            error("El telefono debe ser una cantidad numerica.",request,response);
            return;
        }
        
        try {
            rutinaClienteDAO.eliminarRutinaPorCliente(cliente,telefono);
        } catch (SapeDataException e1) {
            error("Ocurrio un error al intentar eliminar una rutina,\ncausado por:\n"+e1.toString(),request,response);
            return;
        }
        
        request.setAttribute("msg","Se eliminaron todos los registros para el telefono "+telefono+".");
        operacionPantallaRutinaClientes(request,response);
    }
    
    private void operacionInsertarRutinaClientes(HttpServletRequest request, HttpServletResponse response) {
        String tel = request.getParameter("telefono");
        String nombre = request.getParameter("nombre");
        long telefono = 0;
        
        try {
            telefono = Long.parseLong(tel);
        } catch (NumberFormatException e) {
            error("El telefono debe ser una cantidad numerica.",request,response);
            return;
        }
        
        RutinaCliente rc = new RutinaCliente();
        rc.setTelefono(telefono);
        rc.setNombre(nombre);
        rc.setEstatus("II");
        String cliente = (String) request.getSession().getAttribute("cliente");
        
        if(cliente==null||cliente.equals("")){
        	error("Faltan parametros para la operacion",request,response);
        	return;
        }
        rc.setUsuario(cliente);
        try {
            List rcc=rutinaClienteDAO.getRutinasPorCliente(cliente,telefono);
            if(rcc != null && rcc.size() > 0){
            	// verifico ke sea del mismo usuario!!!!
            	
            	request.setAttribute("msg","Ya existe un registro con el telefono: <font color=\"red\">"+telefono+".</font>\nPrueba <font color=\"red\">NO ALMACENADA.</font>");
            }else{
            	rutinaClienteDAO.insertarRutinaCliente(rc);
            	request.setAttribute("msg","La rutina ha sido <font color=\"red\">ALMACENADA.</font>");
            }
        } catch (SapeDataException e1) {
            if(e1.getCause() instanceof net.sf.hibernate.ObjectNotFoundException){
				try {
				   rutinaClienteDAO.insertarRutinaCliente(rc);
				   //System.out.println("EL REGISTRO ES UNO NUEVO!!!");
				} catch (SapeDataException e2) {
				   error(e2,request,response);
				   return;
				}
				request.setAttribute("msg","La rutina ha sido <font color=\"red\">ALMACENADA.</font>");
            }else{
            	error("Ocurrio un error al intentar insertar una rutina,\ncausado por:\n"+e1.toString(),request,response);
            	return;
            }
        }
        
        operacionPantallaRutinaClientes(request,response);
        return;
    }
    
    private void operacionPantallaRutinaClientes(HttpServletRequest request, HttpServletResponse response) {
        List l = null;
        String cliente = request.getParameter("cliente");
        String estado = request.getParameter("estado");
    	if(estado != null && estado.equals(""))
    		estado = null;
    	
    	// esto es para cuando se llame el metodo desde otro (p.e. cuando se llama desde operacionInsertarRutinaClientes )
    	if(cliente == null){
    		cliente = (String) request.getSession().getAttribute("cliente");
    	}
    	    	
        try {
            //l = rutinaClienteDAO.getAllRutinaCliente();
            l = rutinaClienteDAO.getRutinasClientePorEstado(cliente,estado);
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        //atributo para fijar un destino a la forma de subir archivos!!!!
        
        request.getSession().setAttribute("cliente",cliente);
        
        request.setAttribute("destino","actionSape?accion=rutinas&operacion=procesarArchivo&tipo=cliente");
        request.setAttribute("listaClientes",l.get(0));
        request.setAttribute("totalClientes",String.valueOf(((List)l.get(0)).size()));
        request.setAttribute("estadistica",l.get(1));
        request.setAttribute("cliente",cliente);
        request.setAttribute("estado",estado);
        redireccionarConPlantilla("pantallaRutinaClientes", request, response);
        //request.getRequestDispatcher(acciones.getTemplate("pantallaRutinaClientes")).forward(request,response);
        return;
    }
    
    
    private void operacionInicioClientes(HttpServletRequest request, HttpServletResponse response) {
     
    	List listaClientes = null,listaPruebasSiplex=null;
    	String elementosActivos="0";
        try {
            listaClientes = rutinasDAO.getElementosActivos("CL",null,true);
            //elementosActivos = String.valueOf(rutinasDAO.getElementosActivos("CL",null,true).size());
            elementosActivos = String.valueOf(listaClientes.size());
            listaPruebasSiplex = tipoPruebaDAO.getTiposPrueba();
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        request.setAttribute("listaElementos",listaClientes);

        request.setAttribute("listaPruebasSiplexpro",listaPruebasSiplex);
        
        int maxPruebas = configCliente.getNumeroRutinas();
        request.setAttribute("maxPruebas",String.valueOf(maxPruebas));
        request.setAttribute("tipoRutina","Cliente");
        request.setAttribute("rutinasActuales",elementosActivos);
        
        
        
        
        
        redireccionarConPlantilla("rutinas",request,response);

    	
    	
    	/*
        int totalClientes = 0;
        
        List l = null;
        try {
            totalClientes = rutinaClienteDAO.getAllRutinaCliente().size();
            
            l = horaPruebaClienteDAO.getHorariosCliente();
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        HoraPruebaCliente hp = (HoraPruebaCliente)l.get(0);
        
        l = new ArrayList();
        l.add(0,hp.toArray());
        request.setAttribute("vectorClientes",l);
        request.setAttribute("totalClientes",""+totalClientes);       
        request.getRequestDispatcher(acciones.getTemplate("inicioClientes")).forward(request,response);
        return;*/
    }
    
    private void operacionInicioArmarios(HttpServletRequest request, HttpServletResponse response)throws IOException {
        
    	List listaArmarios = null,listaPruebasSiplex=null;
    	String elementosActivos="0";
        try {
            listaArmarios = rutinasDAO.getElementosActivos("AR",null,true);
            elementosActivos = String.valueOf(rutinasDAO.getElementosActivos("AR",null,true).size());
            listaPruebasSiplex = tipoPruebaDAO.getTiposPrueba();
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        int maxPruebas = configCliente.getNumeroRutinas();
        
        request.setAttribute("listaElementos",listaArmarios);
        request.setAttribute("listaPruebasSiplexpro",listaPruebasSiplex);
        request.setAttribute("maxPruebas",String.valueOf(maxPruebas));
        request.setAttribute("tipoRutina","Armario");
        request.setAttribute("rutinasActuales",elementosActivos);

        // Verifico si antes se hizo una actializacion en el sistema, si hubo esta actualiazacion
        // es mejor generar un nuevo request para evitar problemas con los reload de la pagina.
        String msg = (String) request.getAttribute("msg");
        if(msg != null && !msg.equals("")){// ocurrio una actualizacion en el sistema!!!
        	
        	// SI hubo una actualizacion utilizo este metodo, recibe como parametros
        	// un boolean ke en este caso debe ser false, request,response, la plantilla a la ke se le hara
        	// forward despues y la url a la ke se hara sendRedirect para inicializar un nuevo request.
            direccionarCondicional(false,request, response, "rutinas", "actionSape?accion=rutinas&operacion=inicioArmarios");            
            return;
        }

        redireccionarConPlantilla("rutinas", request, response);

    }
    
    private void operacionInicioCables(HttpServletRequest request, HttpServletResponse response) {
    	
    	List listaCables = null,listaPruebasSiplex=null;
    	String elementosActivos="0";
        try {
            listaCables = rutinasDAO.getElementosActivos("CA",null,true);
            elementosActivos = String.valueOf(rutinasDAO.getElementosActivos("CA",null,true).size());
            listaPruebasSiplex = tipoPruebaDAO.getTiposPrueba();
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        request.setAttribute("listaElementos",listaCables);

        request.setAttribute("listaPruebasSiplexpro",listaPruebasSiplex);
        
        int maxPruebas = configCliente.getNumeroRutinas();
        request.setAttribute("maxPruebas",String.valueOf(maxPruebas));
        request.setAttribute("tipoRutina","Cable");
        request.setAttribute("rutinasActuales",elementosActivos);
        redireccionarConPlantilla("rutinas",request,response);
        
    	
    	/*
        List listaCables = null;
        try {

            listaCables = horaPruebaCableDAO.getAllCables();
        } catch (SapeDataException e) {
            error(e,request,response);
            return;
        }
        
        request.setAttribute("listaCables",listaCables);
        int maxPruebas = configCliente.getNumeroRutinas();
        request.setAttribute("maxPruebas",String.valueOf(maxPruebas));
        redireccionarConPlantilla("inicioCables",request,response);
        //request.getRequestDispatcher(acciones.getTemplate("inicioCables")).forward(request,response);
        return;
        */
    }
    
    private void operacionInicioRutinas(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {

        request.getRequestDispatcher(acciones.getTemplate("inicioRutinas")).forward(request,response);
        return;
    }

    // para obtener el objeto date de una fecha en formato yyyy-mm-dd
    private Date getDate(String formato){
    	
    	if(formato==null ||formato.equals(""))
    		return null;
    	
    	Calendar cal = Calendar.getInstance();
    	StringTokenizer stt = new StringTokenizer(formato,"-");
    	
    	cal.set(Calendar.YEAR,Integer.parseInt(stt.nextToken()));
    	cal.set(Calendar.MONTH,Integer.parseInt(stt.nextToken())-1);
    	cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(stt.nextToken()));
    	
    	return cal.getTime();
    }
    
    
    private void cagarCola(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if (debug) logs.debug("cargarCola");
    	
    	String cola = request.getParameter("cola");
    	if (cola == null || cola.equals("")) {
    		error("Falta definir la cola", request, response);
    		return;
    	}
    	
    	ServicioGUDE servicioGude = new ServicioGUDE();
    	String usuario = ((UserSipe)request.getSession().getAttribute("usuario")).getNick();
    	String servicio = "17"; 	//TODO sacarlo del XML
    	String params = "ANALI";
    	String respuesta;
		try {
			respuesta = servicioGude.ejecutarServicio(usuario, servicio, params);
		} catch (SapeDataException e) {
			error(e, request, response);
			return;
		}

    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<script language=\"javascript\">");
		out.println("alert('RESPUESTA: "+ respuesta +"');");
		out.println("location.href = \"" + request.getContextPath() + "/actionSape?accion=" + request.getParameter("accion") + "&operacion=inicioColas\"");
		out.println("</script>");
		out.flush();
    }
    
    
    private void direccionarCondicional(boolean modo,HttpServletRequest request, HttpServletResponse response,String plantilla,String newUrl) throws IOException{

    	
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

    	}else{// se recuperan las variables, se setean en el request y se envia el forward.
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