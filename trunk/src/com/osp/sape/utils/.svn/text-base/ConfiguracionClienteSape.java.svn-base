/**
 * Esta clase es la encargada de proporcionar las opciones de visibilidad
 * al Sistema SAPE, esto es, controlar las distintas partes que se van a 
 * desplegar del sistema a cada cliente
 */
package com.osp.sape.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.osp.sape.Exceptions.SapeAppException;

public class ConfiguracionClienteSape {

	private org.apache.log4j.Logger logs = org.apache.log4j.LogManager.getLogger(getClass());
	private boolean debug = logs.isDebugEnabled();
	private static ConfiguracionClienteSape _instancia = null;
	private Document configDoc;
	
	private static HashMap mapInvisibles;
	private String metodoValidacion;
	private int numeroRutinas=4;// Numero maximo de rutinas por cable o por armario que tendra
								// disponible el usuario. Por default son 4.
	
	private String serverInteractivas;
	private String puertoInteractivas;
	private String hostCyclades;
	/**
	 * En este hashmap iran incluidos los objetos correspondientes a cada tipo de validacion.
	 * Iran en una lista.
	 */
	private HashMap opcionesValidacion;
	
	/**
	 * Cantidad de digitos para los numeros telefonicos. Defecto 7
	 */
	private int cantDigitos = 7;
	
	/**
	 * El path donde se encuentran las carpetas con los archivos de configuracion para Indigos y Fast.
	 */
	private String rutaArchivosIndigoFast;
	
	public String getRutaArchivosIndigoFast() {
		return rutaArchivosIndigoFast;
	}

	public ConfiguracionClienteSape(String fileConfiguration) throws SapeAppException {
		
		if (logs.isInfoEnabled()) logs.info("fileConfiguration: " + fileConfiguration);
	
		SAXBuilder builder = new SAXBuilder();
		try {
		    configDoc = builder.build(fileConfiguration);
		} catch (JDOMException e) {
		    logs.error(e);
		    throw new SapeAppException(e.getMessage());
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				logs.warn("Archivo: " + fileConfiguration + " No Encontrado. Todas las opciones son visibles");
				mapInvisibles = new HashMap(0);
			}
		    logs.error(e);
		    throw new SapeAppException(e);
		}

		cargarArchivo();
		
	}
	
	public static ConfiguracionClienteSape getInstance() {
		if (_instancia == null) throw new RuntimeException("Falta inicializar la clase ConfiguracionClienteSape");
		return _instancia;
	}
	
	
	public static void inicializar(String configPath) throws SapeAppException {
		_instancia = new ConfiguracionClienteSape(configPath);
		
	}
	
	private void cargarArchivo() {
		if(debug)logs.debug("cargarArchivo["+configDoc.getBaseURI()+"]");
		
		long time = System.currentTimeMillis();
		Element root = configDoc.getRootElement();
		
			//Se cargan las opciones invisibles.
		List<Element> invisibles = root.getChild("visibilidad").getChildren("invisible");
		
		if(debug)logs.debug("iniciando opciones de visibilidad...");
		
		mapInvisibles = new HashMap(invisibles.size());
		for (Element item : invisibles) {
			mapInvisibles.put(item.getText(), new Boolean(true));
		}
		
		Element metodVal = root.getChild("autenticacion");
		
		metodoValidacion = metodVal.getAttributeValue("type");
		
		if(debug)logs.debug("Metodo de autenticacion de usuarios: ["+metodoValidacion+"]");
		
		if(debug)logs.debug("iniciando opciones de autenticacion...");
		
		opcionesValidacion = new HashMap();
		if(metodoValidacion.equals("ldap")){
			opcionesValidacion.put("ip",metodVal.getChildText("ip"));
			opcionesValidacion.put("usuarioLogin",metodVal.getChildText("usuarioLogin"));
			opcionesValidacion.put("passwordLogin",metodVal.getChildText("passwordLogin"));
		}
		
		Element maxRutinas = root.getChild("numero-max-rutinas");
		
		if(maxRutinas == null) return;
		
		try{
			numeroRutinas = Integer.parseInt(maxRutinas.getText());
		}catch(NumberFormatException e){
			logs.error("No se encontro una cantidad numerica valida. en <numero-max-rutinas>");
		}
		
		if(debug)logs.debug("Numero maximo de rutinas: "+numeroRutinas);
		
		Element tmp=null;
		// Esta variable se utiliza en contingenciaSiplexpro para determinar cuales son
		// las conexiones que estan por modem y cuales por red corporativa.
		tmp=root.getChild("host-cyclades");
		hostCyclades = (tmp!=null?tmp.getText():null);
		
		if(debug)logs.debug("Host Cyclades: "+hostCyclades);

		tmp=root.getChild("server-interactivas");
		serverInteractivas = (tmp!=null?tmp.getText():null);
		
		if(debug)logs.debug("Servidor Interactivas: "+serverInteractivas); 
		
		tmp=root.getChild("puerto-interactivas");
		puertoInteractivas = (tmp!=null?tmp.getText():null);
		
		if(debug)logs.debug("Puerto Interactivas: "+puertoInteractivas);
		
		tmp=root.getChild("cant-digitos");
		cantDigitos = Integer.parseInt(tmp.getText());
		
		if (logs.isInfoEnabled()) logs.debug("Cantidad de digitos para el ambiente: " + cantDigitos);
		
		tmp=root.getChild("ruta-archivos-indigo-fast");
		rutaArchivosIndigoFast=(tmp!=null?tmp.getText():null);
		
		if(debug)logs.debug("Ruta de Archivos de Indigos y Fast: "+rutaArchivosIndigoFast);
		
		logs.info("Demora: " + (System.currentTimeMillis()-time));
	}
	
	public String getMetodoValidacion(){
		return metodoValidacion;
	}
	
	public HashMap getOpcionesValidacion(){
		return opcionesValidacion;
	}
	
	/**
	 * Busca si una opcion es visible para el sistema.
	 * Si la opcion no se encuentra en el mapInvisibles retorna true
	 * @param opcion
	 * @return
	 */
	public static boolean isVisible(String opcion) {
//		System.out.println("isVisible: " + opcion);
		return (mapInvisibles.get(opcion) == null);
	}
	
	
	/**
	 * Este metodo administra las opciones de MantenimientoGeneral.jsp para que se muestren
	 * de manera dinamica y no se desorganicen.
	 * @return
	 */
	public static String getMenuTag(int linea){

		if(linea == 0){
			return "<tr>";
		} else if(linea == 1){
			return "";
		} else if(linea == 2){
			return "</tr><tr><td height=\"5%\">&nbsp;</td><td height=\"5%\">&nbsp;</td></tr><tr align=\"center\">";
		}
		return "";
		
	} 
	/**
	 * Metodo que retorna opciones de configuracion para la validacion de sus usuarios.
	 * Recordemos que se soporta la validacion normal del sape y validacion a traves de ldap.
	 * @param key
	 * @return
	 */
	public String getOpcionValidacion(String key){
		return (String)opcionesValidacion.get(key);
	}

	public int getNumeroRutinas() {
		return numeroRutinas;
	}

	public String getHostCyclades() {
		return hostCyclades;
	}

	public String getPuertoInteractivas() {
		return puertoInteractivas;
	}

	public String getServerInteractivas() {
		return serverInteractivas;
	}
	
	public int getCantDigitos() {
		return cantDigitos;
	}
}