/*
 * Created on 15-nov-2003
 */
package com.gc.utils;

import java.io.Serializable;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

/**
 * Esta clase se encarga de leer un header http y obtener los datos del browser 
 * y del sistema operativo del cliente. 
 * @author root
 *
 */
public class HeaderHttp implements Serializable {

	
	private org.apache.log4j.Logger logs;
	
		/*Varibles para los get */
	private String browser;
	private String browserVersion = "0.0";
	private String sistemaOperativo;
	private String sistemaOperativoVersion = "0.0";
	
		/*Browsers que soportara la clase*/ 
	public static final String BROWSER_KONKEROR = "Konkeror";
	public static final String BROWSER_MOZILLA = "Mozilla";
	public static final String BROWSER_IEXPLORER = "Internet Explorer";
	public static final String BROWSER_FIREFOX = "Firefox";
	public static final String BROWSER_OTRO = "Otro";
		/*Sistemas Operativos que Soporta la clase */
	public static final String SO_LINUX = "Linux";
	public static final String SO_WINDOWS = "Windows";
	public static final String SO_OTRO = "Otro";
	
		/* Palabras clave para buscar en el header*/
	private final String CONST_MOZILLA_KONKEROR = "Mozilla/5.0"; //Mozilla, konkeror, FIREFOX
	private final String CONST_IEXPLORER = "Mozilla/4.0";
	private final String CONST_WINDOWS = "windows";
	private final String CONST_LINUX = "linux";
	private final String CONST_FIREFOX = "Firefox";
	  
	
	public HeaderHttp(HttpServletRequest request) {
		this(request.getHeader("user-agent"));
	}
	
	public HeaderHttp(String agente) {
		logs = org.apache.log4j.Logger.getLogger(getClass());
		if (logs.isDebugEnabled()) logs.debug("agente: " + agente);
		
//		System.out.println("agente: '" + agente + "'");
		if (agente.startsWith(CONST_MOZILLA_KONKEROR)) {
			String tokens[] = getTokens(agente);
			System.out.println("tokens.length: " + tokens.length);
			if (tokens.length == 5) {  //Mozilla � Firefox
			    if (agente.indexOf(CONST_FIREFOX) != -1) 
			        browser = BROWSER_FIREFOX;
			    else
			        browser = BROWSER_MOZILLA;
				sistemaOperativo = getSistemaOperativo(tokens[2].toLowerCase());
				String version = tokens[4];
				browserVersion = version.substring(3);
			} else {
				browser = BROWSER_KONKEROR;
				sistemaOperativo = getSistemaOperativo(tokens[2].toLowerCase());
				String version = tokens[1];
				browserVersion = version.substring(10);
			}
		} else if  (agente.startsWith(CONST_IEXPLORER)) { 
			browser = BROWSER_IEXPLORER;
			String tokens[] = getTokens(agente);
			sistemaOperativo = getSistemaOperativo(tokens[2].toLowerCase());
			String version = tokens[1];
			browserVersion = version.substring(5);
		} else {
			browser = BROWSER_OTRO;
			sistemaOperativo = SO_OTRO;
		}
//		System.out.println("Browser: " + getBrowser() + " version: " + getBrowserVersion());
//		System.out.println("Sistema operativo: " + getSistemaOperativo());
		
	}
	
	public String getBrowser() {
		return browser;
	}
	
	public String getBrowserVersion() {
		return browserVersion;
	}
	
	public String getSistemaOperativo() {
		return sistemaOperativo;
	}
	
	public String getSistemaOperativoVersion() {
		return sistemaOperativoVersion;
	}
	
	private String getSistemaOperativo(String so) {
		String retorno = SO_OTRO;
		if (so.startsWith(CONST_WINDOWS)) {
			retorno = SO_WINDOWS;
		} else if (so.startsWith(CONST_LINUX)) {
			retorno = SO_LINUX;
		} 
		return retorno;	
	}
	
	private String[] getTokens(String agente) {
		agente = agente.substring(agente.indexOf('(') + 1, agente.indexOf(')'));
//		System.out.println("agente: '" + agente + "'");
		StringTokenizer token = new StringTokenizer(agente, ";");
		java.util.ArrayList listaTokens = new java.util.ArrayList(5);
		while (token.hasMoreElements()) {
			listaTokens.add(token.nextElement());
		}
		String retorno[] = new String[listaTokens.size()];
//		System.out.print("tokens: ");
		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = ((String)listaTokens.get(i)).trim();
//			System.out.print(retorno[i] + ", ");
		}
//		System.out.println("");
		return retorno;
	}
	
	public static void main(String[] args) {
		HeaderHttp h = null; 
//		h = new HeaderHttp("Mozilla/5.0 (X11; U; Linux i686; es-ES; rv:1.2.1) Gecko/20030225");
//		System.out.println("------------------------------------------------------");
//		System.out.println("------------------------------------------------------");
//		h = new Header("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
//		h = new Header("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.3) Gecko/20030312");
//		h = new HeaderHttp("Mozilla/4.78 [es] (X11; U; Linux 2.4.7-10 i686)"); ?????????????
//		h = new HeaderHttp("Mozilla/5.0 (compatible; Konqueror/2.2-11; Linux)");
		h = new HeaderHttp("Mozilla/5.0 (Windows; U; Windows NT 5.1; es-AR; rv:1.7.5) Gecko/20041108 Firefox/1.0");
		System.out.println("browser: " + h.getBrowser() + " version: " + h.getBrowserVersion());
		System.out.println("S.o.: " + h.getSistemaOperativo());
//		h = new HeaderHttp("Mozilla/5.0 (compatible; Konqueror/3.0.0-10; Linux)");
//		h = new HeaderHttp("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:0.9.9)Gecko/20020408");
		h = new HeaderHttp("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.5) Gecko/20031007");
		System.out.println("browser: " + h.getBrowser() + " version: " + h.getBrowserVersion());
		System.out.println("S.o.: " + h.getSistemaOperativo());
		//TODO este es nuevo y no lo he probado
	}	

}
