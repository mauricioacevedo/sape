package com.osp.sape;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.osp.sape.Exceptions.SapeAppException;
//TODO esta clase esta en el package correcto?
// com.osp.sape.utils podria ser uno;
public class SapeConfiguration {

	private Logger logs;
	
	/** Documento XML que contendra toda la informacion especifica de configuracion */
	Document configDoc;
	
	private static SapeConfiguration _instancia = null;
	
	private String rutaServicios;
	private String urlServicios;
	private String rutaEvalResultados;
	private String rutaArchivoUmbrales;
	private String rutaCodigosFalla;
	private String clienteSistema;
	
	//ipGUDE y puertoGUDE son las variables que me guardan la direccion del socket para invocar a los servicios
	//que no tienen que ver con pruebas de Telefonos
	private String ipGUDE;
	private int puertoGUDE;
	
	private HashMap mapaServiciosGUDE = new HashMap(5);
	
	/**
	 * Carga toda la confguracion para el sape.
	 * @param configPath ruta del archivo de configuracion.
	 */
	public static void inicializar(String configPath) throws SapeAppException {
		
		File fConfiguracion = new File(configPath);

		if (!fConfiguracion.exists()) {
	       throw new SapeAppException("El archivo de configuracion " + fConfiguracion.getAbsolutePath() + "no Existe");
	   }
		
		_instancia = new SapeConfiguration(fConfiguracion);
		
	}
	
	private SapeConfiguration(File fConfiguracion) throws SapeAppException {
		logs = Logger.getLogger(this.getClass());
		if (logs.isInfoEnabled()) logs.info("*** Comienza ***");
		
		SAXBuilder builder = new SAXBuilder();
		try {
		    configDoc = builder.build(fConfiguracion);
		} catch (JDOMException e) {
		    logs.error(e);
		    throw new SapeAppException(e.getMessage());
		} catch (IOException e) {
		    logs.error(e);
		    throw new SapeAppException(e.getMessage());
		}

		cargarArchivo();
	}
	
	public static SapeConfiguration getInstance() {
		if (_instancia == null) throw new RuntimeException("Falta inicializar la clase");
		return _instancia;
	}
	
	private void cargarArchivo() {
		boolean log = logs.isDebugEnabled();
		if(log)logs.debug("cargarArchivo");
		Element root = configDoc.getRootElement();
		
		//cargo el archivo de servicios
		
		rutaServicios = root.getChild("ruta-servicios").getAttributeValue("value");
		if (log) logs.debug("carga rutaServicios: " + rutaServicios);
		
		//cargo el url donde se encuentra el archivo de configuracion.
		
		urlServicios = root.getChild("url-servicios").getAttributeValue("value");
		if (log) logs.debug("carga urlServicios: " + urlServicios);
		
		rutaEvalResultados = root.getChild("ruta-eval-resultados").getAttributeValue("value");
		if (log) logs.debug("carga rutaEvaluacionResultados: " + rutaEvalResultados);
		
		rutaArchivoUmbrales = root.getChild("ruta-gude-conf-tcl").getAttributeValue("value");
		if (log) logs.debug("carga rutaGudeConf: " + rutaArchivoUmbrales); 
		
		rutaCodigosFalla = root.getChild("ruta-codigos-falla").getAttributeValue("value");
		if (log) logs.debug("carga rutaCodigosFalla: " + rutaCodigosFalla); 
		
		// Se carga el cliente del sistema SAPE
		clienteSistema = root.getChild("cliente-sistema").getAttributeValue("value");
		if (log) logs.debug("Cliente del Sistema: " + clienteSistema);
		
				//cargo la ruta al GUDE
		Element eGude = root.getChild("servicio-gude");
		ipGUDE = eGude.getChildText("ip");
		try {
			puertoGUDE = Integer.parseInt(eGude.getChildText("puerto"));
		} catch (NumberFormatException e) {
			logs.error("El parametro ip en el tag servicio-gude es invalido");
		}
		if (log) logs.debug("carga servicioGude. IP=" + ipGUDE + ". Puerto: " + puertoGUDE);
		
			//Cargo los datos de los diferentes servicios
		Element eServicios = root.getChild("servicios-gude");
		for (Iterator iter = eServicios.getChildren().iterator(); iter.hasNext();) {
			Element servicio = (Element) iter.next();
			String nombre = servicio.getAttributeValue("name");
			String valor = servicio.getText();
			mapaServiciosGUDE.put(nombre, valor);
			if (log) logs.debug("carga Servicio: " + nombre + "= " + valor);
		}		
	}
	
	public String getRutaServicios() {
		return rutaServicios;
	}
	
	public String getIpGUDE() {
		return ipGUDE;
	}
	
	public int getPuertoGUDE() {
		return puertoGUDE;
	}
	
	public String getRutaEvalResultados(){
		return rutaEvalResultados;
	}

	public String getRutaArchivoUmbrales() {
		return rutaArchivoUmbrales;
	}
	
	public String getRutaCodigosFalla() {
		return rutaCodigosFalla;
	}
	/**
	 * Retorna el cliente del sistema SAPE guardado previamente en el 
	 * archivo de configuracion sape-config.xml
	 * @return
	 */
	public String getClienteSistema(){
		return clienteSistema;
	}
	
	/**
	 * Da el numero del servicio segun el nombre.
	 * Lee el tag servicios-gude
	 * @return
	 */
	public String getServicio(String nombre) {
		if (logs.isDebugEnabled()) logs.debug("getServicio: " + nombre);
		return (String)mapaServiciosGUDE.get(nombre);
	}

	public String getUrlServicios() {
		return urlServicios;
	}
	
	/**
	 * Retorna la ruta de acceso a la plantilla del reporte.
	 * Recibe como parametro el nombre de la pantalla a exportar.
	 */
}