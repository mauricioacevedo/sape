/*
 * Created on Jan 2, 2006
 *
 */
package com.osp.sape.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.osp.sape.Exceptions.SapeDataException;

/** 
 * Clase encargada de leer un archivo en tcl que contiene todas las variables de configuracion del sistema
 * ademas tambien guarda estas en caso de ser modificadas. 
 * La ruta del archivo.tcl se encuentra en el archivo sape-config.xml 
 */ 
public class ManejoUmbrales {

	private Logger logs;
	private File archivoConfiguracion;
	private Properties fields;
	private List namesProperties;
	/**
	 * @param args
	 */
	
	public ManejoUmbrales(File f){
		
		archivoConfiguracion = f;
		if (!archivoConfiguracion.exists()) {
			if(logs.isDebugEnabled()) logs.debug("El archivo de Configuracion "+f.getName()+" No existe!");
			return;
		}
		
		initProperties();

	}
	
	private void initProperties(){
		
		Exception exception = null;
		
		try {
			RandomAccessFile raf = new RandomAccessFile(archivoConfiguracion,"rw");
			Properties p = new Properties();
			namesProperties = new ArrayList();
			int i = 0;
			while(true){
				
				String line = raf.readLine();
				if(line == null) break;
				if(line.endsWith(";") && !line.startsWith("#")){ // encontro una propiedad
					
					StringTokenizer st = new StringTokenizer(line," ");
					//System.out.println("Cantidad de elementos de frase: "+st.countTokens());
					/*
					 * Una linea se compone de :
					 * 1. Comando 'set'
					 * 2. Nombre de la Propiedad
					 * 3. Valor de la propiedad, este valor no tebe contener espacios!
					 * 
					 * Sabiendo esto, la linea TIENE QUE TENER 3 tokens!
					 */
					
					if (st.countTokens() != 3)
						continue;
					st.nextToken();		// con este me deshago del SET
					String name = st.nextToken();
					String value = st.nextToken();
					
					value = value.replace(';',' ');
					value = value.replace('"',' ');
					value = value.replaceAll(" ","");
					
					p.setProperty(name,value);
					namesProperties.add(i,name);
					i++;
				} else {
					if(line.startsWith("#.")){
						
						String name = "comentario"+i;
						String value = line.substring(2);
						p.setProperty(name,value);
						namesProperties.add(i,name);
						i++;
					}
				}
			}
			
			fields = null;
			fields = p;
			
						
		} catch (FileNotFoundException e) {
			exception =e;
		} catch (IOException e) {
			exception =e;
		}
		
		if ( exception != null ) {
			if(logs.isDebugEnabled()) logs.debug(exception);
		}
	}
	
	public String getValue(String key) {
		
		return fields.getProperty(key);
	}
	
	public List getNameFields(){
			
		return namesProperties;
	}
	
	public Properties getProperties() {
		return fields;
	}
	
	/**
	 * Este metodo actualiza todas las propiedades del archivo gude.conf.tcl
	 * @param p
	 * @throws SapeDataException
	 */
	public void setProperties(Properties p) throws SapeDataException{		
		Exception exception = null;
		File fileTemp,fileOld,fileNow;
		try {
			fileTemp = new File(archivoConfiguracion.getParent()+"/gude.conf.tcl-swp");
			fileOld = new File(archivoConfiguracion.getParent()+"/gude.conf.tcl-old");

			RandomAccessFile raf = new RandomAccessFile(fileTemp,"rw");
			RandomAccessFile ref = new RandomAccessFile(archivoConfiguracion,"rw");
			
			
			while(true){
				
				String line = ref.readLine();
				if(line == null) { System.out.println("OJO ke salio con un break!"); break;}
				if(line.endsWith(";") && !line.startsWith("#")){ // encontro una propiedad
					
					StringTokenizer st = new StringTokenizer(line," ");
					
					if (st.countTokens() != 3)
						continue;
					st.nextToken();		// con este me deshago del SET
					
					String name = st.nextToken();
					String value = p.getProperty(name);
					
					raf.write(("set "+name+" \""+value+"\";\r\n").getBytes());
					
				} else {
					raf.write((line+"\r\n").getBytes());
				}
			}
			raf.close();
			ref.close();
			
			boolean bol=archivoConfiguracion.renameTo(fileOld);

			if (bol == false) {
				exception = new SapeDataException("No fue posible crear el archivo : '"+archivoConfiguracion.getName()+"'");
			} else {
				
				fileNow = new File(archivoConfiguracion.getParent()+"/gude.conf.tcl");
				
				bol= fileTemp.renameTo(fileNow);
				
				if(bol == false)
					exception = new SapeDataException("No fue posible crear el archivo : '"+fileTemp.getName()+"'");
			}
			
			// Ahora hago la actualizacion del archivo en los demas ekipos!!!

			actualizarArchivo("/opt/gude/sqltcl/gude.conf.tcl","/opt/gude/sqltcl/");
			/*
			Process process = null;
			String linea = "";
	    	String error = null;
	    	
	    	process=Runtime.getRuntime().exec("/opt/gude/sqltcl/sincronizarGudeConf.tcl /opt/gude/sqltcl/gude.conf.tcl /opt/gude/sqltcl/");
	    	
	    	BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
    		BufferedReader bfErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
    		while(true){
    			
    			if(bfErr.ready()){
    				while(bfErr.ready())
    					error += "\n"+bfErr.readLine();
    				
    				error = "[ERROR]"+error;
    				logs.debug("ERROR: \n"+error);
    				exception = new SapeDataException(error);
    				break;
    			}
    			linea = bf.readLine();
    			if(linea == null) break;
    			System.out.println(linea);
    		
    		}*/
		} catch (FileNotFoundException e) {
			exception = e;
		} catch (IOException e) {
			exception = e;
		}
		if(exception != null) throw new SapeDataException(exception);
	}
	
	
	public static void main(String[] args) {
		
		new ManejoUmbrales(new File("/opt/gude/sqltcl/gude.conf.tcl"));
	}

	public static String actualizarArchivo(String archivo,String destiny)throws SapeDataException{
		
		Process process = null;
		String linea = "";
    	String error = null;
    	    	
    	try{
    	
    	process=Runtime.getRuntime().exec("/opt/gude/sqltcl/sincronizarGudeConf.tcl "+archivo+" "+destiny+"");
    	
    	BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
		BufferedReader bfErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		
		while(true){
			
			if(bfErr.ready()){
				while(bfErr.ready())
					error += "\n"+bfErr.readLine();
				
				error = "[ERROR]"+error;
				//logs.debug("ERROR: \n"+error);
				System.out.println("[ERROR]"+error);
				throw new SapeDataException(error);
			}
			linea = bf.readLine();
			if(linea == null) break;
			System.out.println(linea);
		}
		
    	}catch(IOException e){
    		throw new SapeDataException(e);
    	}
		return "[OK]";
	}
}
