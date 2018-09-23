package com.osp.sape.servlets.indicadores;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gc.acceso.GestorServlet;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.utils.ChannelCyclades;
import com.osp.sape.utils.EstadoCyclades;
import com.osp.sape.utils.Modem;
import com.osp.sape.utils.ServicioGUDE;

public class EstadoCycladesServlet extends GestorServlet{
	/**
	 * Variable que se utiliza para almacenar la informacion que se obtiene
	 * del servicio gude.
	 * */
	private String captura;
	
	/**
	 * Objeto que contiene la informacio de la variable captura, pero ya formateada
	 * y asignada a las variables privadas correspondientes.
	 * */
	private EstadoCyclades ec;
	
	public void init() throws ServletException {
		super.init();
	}
	
	public boolean leerEstadoCyclades(){
		ServicioGUDE sg = new ServicioGUDE();
		boolean lecturaSatisfactoria = true;
		try {
			captura = sg.ejecutarServicio("estadoCyclades","42","nada");
			if(debug) logs.debug("Informacion que proviene del servidor");
			if(debug) logs.debug(captura);
		} catch (SapeDataException e) {
			captura = "";
			if(debug) logs.error("Error en la llamada del comandoPc400\n"
					+"El servicio gude 42 no se pudo ejecutar");
			lecturaSatisfactoria = false;
			captura = "";
		}
		return lecturaSatisfactoria;
	}
	
	public void asignarEstados(HttpServletRequest req){
		ArrayList modems = new ArrayList();
		ArrayList canales = new ArrayList();
		StringTokenizer tk;
		int indice = 0;//Para contar hasta terminar los channels y saber cuando empezar
					   //con los modems
		ChannelCyclades ch;//Temporales, para la asignacion de la informacion y almacenamiento
		Modem md;//en los HashMaps
		
		tk = new StringTokenizer(captura,",");
		
		ec = new EstadoCyclades();
		if(tk.hasMoreTokens()){
			ec.setSincronizado(tk.nextToken());
			ec.setAlarmaRoja(tk.nextToken());
			ec.setAlarmaAzul(tk.nextToken());
			ec.setAlarmaAmarilla(tk.nextToken());
		}
		
		while(tk.hasMoreTokens()){
			if(indice < 30){
				ch = new ChannelCyclades();
				ch.setId(new Integer(indice + 1));
				ch.setEstado(tk.nextToken());
				ch.setTelefono(tk.nextToken());
				canales.add(indice, ch);
			}else{				
				md = new Modem();
				md.setId(new Integer(indice - 29));
				md.setEstado(tk.nextToken());
				modems.add(indice - 30, md);
			}
			indice++;
		}
		
		ec.setCanales(canales);
		ec.setModems(modems);
		
		req.setAttribute("estadoCyclades",ec);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res){
		doGet(req, res);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		boolean lecturaSatisfactoria = leerEstadoCyclades();
		if(lecturaSatisfactoria) {
			asignarEstados(req);
			redireccionarConPlantilla("estadoCyclades", req, res);
		} else {
			error("No se pudo obtener informacion del servidor", req, res);
		}
	}
}
