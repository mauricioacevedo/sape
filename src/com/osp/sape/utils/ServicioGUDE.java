package com.osp.sape.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.osp.sape.Exceptions.SapeDataException;

/**
 * Esta clase se encarga de gestionar todas las peticiones que se hagan
 * al servicio gude.
 * 
 * @author Mauricio
 *
 */
public class ServicioGUDE {

	private Logger logs;
	
	public static String SERVICIO_CONFIGURACION_ADSL = "41";
	public static String VERIFICAR_ALARMA_CABEZAS = "2";
	public static String SERVICIO_OPERACIONES_INDIGO_FAST = "43";
	
	public ServicioGUDE() {
		logs = LogManager.getLogger(getClass());
	}
	
	/**
	 * Esta clase es la encargada de ejecutar servicios a traves del ServicioGUDE.
	 * 
	 * @param usuario
	 * @param servicio
	 * @param params
	 * @return
	 * @throws SapeDataException
	 */
	public String ejecutarServicio(String usuario,String servicio,String params) throws SapeDataException{

		if(logs.isDebugEnabled()) logs.debug(" usuario=["+usuario+"], servicio=["+servicio+"], params=["+params+"]");
		
    	Process p = null;
		String linea = "";
    	String error = null;
		
    	try {
    		   		    		
    		p=Runtime.getRuntime().exec("/opt/gude/sqltcl/ServicioGUDE.tcl "+usuario+" "+servicio+" "+params);
    		BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		BufferedReader bfErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    		while(true){
    			
    			if(bfErr.ready()){
    				while(bfErr.ready())
    					error += "\n"+bfErr.readLine();
    				
    				error = "[ERROR]"+error;
    				logs.debug("ERROR: \n"+error);
    				break;
    			}
    			linea = bf.readLine();
    			System.out.println(linea);
    			if(linea.indexOf("%FIN")!= -1)
    				break;
    			if(linea.indexOf("%Respuesta") != -1){//la siguiente linea es la rta!!!
    				linea = bf.readLine();
    				//linea=linea.replace(',',' ');
    				break;
    			}
    		}
    		
    		if(linea.indexOf("TIMEOUT")!=-1 ||linea.indexOf("ERROR")!=-1 ){
    			throw new SapeDataException("Ocurrio un error ejecutando el servicio.SALIDA: "+linea);
    		}
    		
		} catch (IOException e) {
			throw new SapeDataException(e);
		}
		
		if(error != null) return error;
		else return linea;
		
	}

	
	/**
	 * Esta clase es la encargada de ejecutar servicios a traves del ServicioGUDE.
	 * Recibe como parametro tambien la variable outer que es para ubicar la salida del
	 * proceso!!!!!.
	 * @param usuario
	 * @param servicio
	 * @param params
	 * @param outer
	 * @return
	 * @throws SapeDataException
	 */
	public String ejecutarServicio(String usuario,String servicio,String params, PrintWriter outer) throws SapeDataException{

		if(logs.isDebugEnabled()) logs.debug(" usuario=["+usuario+"], servicio=["+servicio+"], params=["+params+"]");
		
    	Process p = null;
		String linea = "";
    	String error = null;
		
    	try {
    		   		    		
    		p=Runtime.getRuntime().exec("/opt/gude/sqltcl/ServicioGUDE.tcl "+usuario+" "+servicio+" "+params);
    		BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		BufferedReader bfErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    		
    		while(true){
    			
    			if(bfErr.ready()){
    				while(bfErr.ready())
    					error += "\n"+bfErr.readLine();
    				
    				error = "[ERROR]"+error;
    				logs.debug("ERROR: \n"+error);
    				break;
    			}
    			linea = bf.readLine();
    			
    			outer.println(linea);
    			
    			System.out.println(linea);
    			if(linea.indexOf("%FIN")!= -1)
    				break;
    			if(linea.indexOf("%Respuesta") != -1){//la siguiente linea es la rta!!!
    				linea = bf.readLine();
    				linea=linea.replace(',',' ');
    				break;
    			}
    		}	
		} catch (IOException e) {
			throw new SapeDataException(e);
		}
		
		if(error != null) return error;
		else return linea;
		
	}
	
	/**
	 * Para ejecutar el servicio gude con un arreglo de strings!!!
	 * 
	 * @param cmd
	 * @return
	 * @throws SapeDataException
	 */
	public String ejecutarServicio(String cmd[]) throws SapeDataException{

		String comandos[]= new String[cmd.length+1];
		comandos[0]="/opt/gude/sqltcl/ServicioGUDE.tcl";
		
		for(int i=0;i<cmd.length;i++){
			comandos[i+1]=cmd[i];
		}
		
    	Process p = null;
		String linea = "";
    	String error = null;
		
    	try {
    		   		    		
    		//p=Runtime.getRuntime().exec("/opt/gude/sqltcl/ServicioGUDE.tcl "+usuario+" "+servicio+" "+params);
    		    		
    		p=Runtime.getRuntime().exec(comandos);
    		BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		BufferedReader bfErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    		while(true){
    			
    			if(bfErr.ready()){
    				while(bfErr.ready())
    					error += "\n"+bfErr.readLine();
    				
    				error = "[ERROR]"+error;
    				logs.debug("ERROR: \n"+error);
    				break;
    			}
    			linea = bf.readLine();
    			if (logs.isDebugEnabled()) System.out.println(linea);
    			if(linea.indexOf("%FIN")!= -1)
    				break;
    			if(linea.indexOf("%Respuesta") != -1){//la siguiente linea es la rta!!!
    				linea = bf.readLine();
    				//linea=linea.replace(',',' ');
    				break;
    			}
    		}
    		
    		if(linea.indexOf("TIMEOUT")!=-1 ||linea.indexOf("ERROR")!=-1 ){
    			throw new SapeDataException("Ocurrio un error ejecutando el servicio.SALIDA: "+linea);
    		}
    		
		} catch (IOException e) {
			throw new SapeDataException(e);
		}
		
		if(error != null) return error;
		else return linea;
		
	}

	public String ejecutarComando(String cmd) throws SapeDataException{
		if(logs.isDebugEnabled()) logs.debug("COMANDO: "+cmd);
		
    	Process p = null;
		String linea = "";
    	String error = null;
		
    	try {
    		   		    		
    		p=Runtime.getRuntime().exec(cmd);
    		BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		BufferedReader bfErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    		
    		while(true){
    			
    			if(bfErr.ready()){
    				while(bfErr.ready())
    					error += "\n"+bfErr.readLine();
    				
    				error = "[ERROR]"+error;
    				logs.debug("ERROR: \n"+error);
    				break;
    			}
    			linea = bf.readLine();

    			System.out.println(linea);
    			if(linea == null)break;
    		}	
		} catch (IOException e) {
			throw new SapeDataException(e);
		}

		if(error != null) return error;
		else return linea;	
	}
	
	public String ejecutarServicio(String cmd[], PrintWriter outer) throws SapeDataException{

		String comandos[]= new String[cmd.length+1];
		comandos[0]="/opt/gude/sqltcl/ServicioGUDE.tcl";
		
		for(int i=0;i<cmd.length;i++){
			comandos[i+1]=cmd[i];
		}
		
    	Process p = null;
		String linea = "";
    	String error = null;
		
    	try {
    		   		    		
    		//p=Runtime.getRuntime().exec("/opt/gude/sqltcl/ServicioGUDE.tcl "+usuario+" "+servicio+" "+params);
    		    		
    		p=Runtime.getRuntime().exec(comandos);
    		BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		BufferedReader bfErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    		while(true){
    			
    			if(bfErr.ready()){
    				while(bfErr.ready())
    					error += "\n"+bfErr.readLine();
    				
    				error = "[ERROR]"+error;
    				logs.debug("ERROR: \n"+error);
    				break;
    			}
    			linea = bf.readLine();
    			if (logs.isDebugEnabled()) System.out.println(linea);
    			outer.println(linea);
    			outer.flush();
    			if(linea.indexOf("%FIN")!= -1)
    				break;
    			if(linea.indexOf("%Respuesta") != -1){//la siguiente linea es la rta!!!
    				linea = bf.readLine();
    				//linea=linea.replace(',',' ');
    				break;
    			}
    		}
    		
    		if(linea.indexOf("TIMEOUT")!=-1 ||linea.indexOf("ERROR")!=-1 ){
    			throw new SapeDataException("Ocurrio un error ejecutando el servicio.SALIDA: "+linea);
    		}
    		
		} catch (IOException e) {
			throw new SapeDataException(e);
		}
		
		if(error != null) return error;
		else return linea;
		
	}

	
}
