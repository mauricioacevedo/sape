/*
 * Created on 16-abr-2005
 */
package com.osp.sape.servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.osp.sape.Exceptions.SapeAppException;
import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.utils.ManejoUmbrales;

/**
 * Clase que controlara las sencuencias dentro del archivo de configuracion que se utiliza para
 * la evaluacion de resultados. Este archivo es un xml(evaluacionResultados.xml) y se asocia
 * tambien con un jsp(evaluacionResultados.jsp). El manejo de estos elementos estara a cargo
 * del servlet(RecogerParametrosTCLServlet).
 * @author Andres
 */
public class EvaluacionResultados {

	private Logger logs; 
	private File fConfiguracion;
	private List listaSecuencias;
	/**
	 * 
	 * @param fConfiguracion La ruta al documento en XML con los datos de la 
	 * configuracion.
	 */
	public EvaluacionResultados(File fConfiguracion) throws SapeAppException {
		logs = Logger.getLogger(this.getClass());
		
		logs.info("*** Comienza EvaluacionResultados ***");
		this.fConfiguracion = fConfiguracion;
		if (logs.isDebugEnabled()) logs.debug("El archivo de configuracion es: " + fConfiguracion.getAbsolutePath());
		
		if (!fConfiguracion.exists()) {
			throw new SapeAppException("El archivo de configuracion " + fConfiguracion.getAbsolutePath() + "no Existe");
	   	}
		
		
		cargarSecuencias();
	}

	
	/**
	 * Metodo utilizado para cargar en memoria la informacion del archivo de parametros
	 * 'evaluacionResultados.tcl'.
	 *
	 */
	private void cargarSecuencias(){
		
		int k = 6; // para controlar el decremento o incremento de parametros (codigos ver)
				   // recuerde cambiar la variable tambien en el jsp.
		try {
			RandomAccessFile raf = new RandomAccessFile(fConfiguracion,"rw");
			listaSecuencias = new ArrayList();
			while(true){
				
				String linea = raf.readLine();
				if(linea == null){ // si termino y no encontro el comentario el archivo esta mal!!!
					if(logs.isDebugEnabled()) logs.debug("[ERROR] No se encontro posicion inicial para la lectura de las condiciones por codv.");
					return;
				}
				if (linea.indexOf("Condiciones por codv") != -1) {
					// Se ubico al principio de las 25 condiciones por codv.
					logs.info("Me ubique al principio de las condiciones por codv");
					break;
				}
			}
			
			//StringTokenizer stt = null;
			for (int i = 0;i<28+k;i++){
				
				String line = raf.readLine();
				
				String cola = getToken(line,1," ");
				String codv = cola.substring(cola.length()-2);
				cola = getToken(line,2," ");
				// ahora paso a la siguiente linea que es donde esta el codigo!
				line = raf.readLine();
				
				String codigo = getToken(line,2," "); 
				
		    	Secuencia secuencia = null;
		    	
	    	   	secuencia = new Secuencia(codv,cola,codigo, i);
		    	listaSecuencias.add(i,secuencia);
		    	
		    	raf.readLine();//para ke ubike el cursor en el siguiente par de valores!
			}
			String line = raf.readLine();
			
			// Si todo salio bien, en estos momentos en line esta el comentario ke ubica el
			// archivo en las condiciones de cerrar!!!!
			
			if(line.indexOf("Condicion de cerrar") == -1){
				logs.error("No se pudo ubicar sobre las condiciones de cerrar");
				return;
			} else {
				logs.info("Me ubique al principio de las condiciones de cerrar");
			}
			
			line = raf.readLine();
			String clienteRepetido = getToken(line,2," ");
			
			line = raf.readLine();
			String siCola = getToken(line,2," ");
			
			line = raf.readLine();
			
			String siCodigo = getToken(line,2," ");
			
			String noCodigo = null;
			String noUsuario = null;
		    
			Secuencia secc = new Secuencia(clienteRepetido,siCola,siCodigo,noCodigo,noUsuario,28+k);			
			listaSecuencias.add(28+k,secc); // en la posicion 28+k queda guardada la secuencia de CERRAR
			
			raf.readLine();
			line =raf.readLine();
			
			// A esta altura debemos estar sobre la condicion de Usuario FENIX !!!!
			
			if(line.indexOf("Usuario FENIX") == -1){
				logs.error("No se pudo ubicar sobre la condicion de Usuario FENIX");
				return;
			} else {
				logs.info("Me ubique al principio de la condicion de Usuario FENIX!");
			}
			
			line = raf.readLine();
			
			String userFenix = getToken(line,2," ");
			listaSecuencias.add(29+k,userFenix); // pos 29+k, usuario de FENIX!!!!
			
			raf.readLine();
			
			// Me ubico sobre las variables de Condicion de error
			
			line = raf.readLine();
			
			if(line.indexOf("Condicion de error") == -1){
				logs.error("No se pudo ubicar sobre la Condicion de error");
				return;
			} else {
				logs.info("Me ubique al principio de la Condicion de error!");
			}
			
			line = raf.readLine();
			String cola = getToken(line,2," ");
			
			line = raf.readLine();
			String codigo = getToken(line,2," ");
			
			secc = new Secuencia(null,cola,codigo,30);
		    
			listaSecuencias.add(30+k,secc); // pos 30+k, Condicion de error!!!!!
			raf.readLine();
			
			// Me ubico sobre las variables de la cola GRANC!!!!
			
			line = raf.readLine();
			
			if(line.indexOf("Condiciones para GRANC") == -1){
				logs.error("No se pudo ubicar sobre las Condiciones para GRANC");
				return;
			} else {
				logs.info("Me ubique al principio de las Condiciones para GRANC!");
			}
			
			line = raf.readLine();
			String colaDia = getToken(line,2," ");
			
			line = raf.readLine();
			String codigoDia = getToken(line,2," ");

			line = raf.readLine();
			String horaGrancNoche = getToken(line,2," ");
			
			line = raf.readLine();
			String colaNoche = getToken(line,2," ");
			
			line = raf.readLine();
			String codigoNoche = getToken(line,2," ");

			
			secc = new Secuencia(31+k,colaDia, codigoDia, colaNoche, codigoNoche, horaGrancNoche);
			
			listaSecuencias.add(31+k,secc); // pos 31+k, Condiciones de la cola GRANC!!!!
			raf.readLine();

			// Me ubico sobre las condiciones de pruebas ND!
			
			line = raf.readLine();
			
			if(line.indexOf("Condiciones ND") == -1){
				logs.error("No se pudo ubicar sobre las Condiciones para pruebas ND");
				return;
			} else {
				logs.info("Me ubique al principio de las Condiciones para pruebas ND");
			}

			line = raf.readLine();
			String colaND = getToken(line,2," ");
			
			line = raf.readLine();
			String codigoND = getToken(line,2," ");

			
			secc = new Secuencia(null,colaND,codigoND,32+k);
			
			listaSecuencias.add(32+k,secc); // pos 32+k, Condiciones de pruebas ND!

			
		} catch (FileNotFoundException e) {
			if(logs.isDebugEnabled())logs.debug("[ERROR]: "+e.toString());
			return;
		} catch (IOException e) {
			e.printStackTrace();
			if(logs.isDebugEnabled())logs.debug("[ERROR]: "+e.toString()); 
		}
	}
	
	public Object getSecuencia(int number){
		cargarSecuencias();
				
		return listaSecuencias.get(number);
		
	}
	

	public List getAllSecuencias() {
	    cargarSecuencias();
	    return listaSecuencias;
	}
	/**
	 * Este metodo actualiza la informacion de el archivo evaluacionResultados.tcl.
	 * Recibe una lista(List) de objetos Secuencia donde se encuentra la informacion de
	 * cada una de las secuencias del jsp evaluacionResultados.jsp.
	 * 
	 * @param secuencias
	 * @throws IOException
	 * @throws SapeDataException 
	 */
	public void actualizarSecuencias(List secuencias) throws IOException, SapeDataException {
	    	

		int k = 6;
		
		File fileTemp,fileOld,fileNow;
		fileTemp = new File(fConfiguracion.getParent()+"/evaluacionResultados.tcl-swp");
		fileOld =  new File(fConfiguracion.getParent()+"/evaluacionResultados.tcl-old");

		RandomAccessFile raf = new RandomAccessFile(fileTemp,"rw");
		RandomAccessFile ref = new RandomAccessFile(fConfiguracion,"rw");

		String line = null;
		
		while(true){
			line = ref.readLine();
			if(line == null) {				
				throw new SapeDataException("[ERROR]No encontro el comentario de Condiciones por codv.\nNo fue posible la ubicacion en el archivo.\n Linea: ["+line+"]");
			}
			
			if(line.indexOf("Condiciones por codv") == -1){
				raf.write((line+"\r\n").getBytes());
				continue;
			} else { // encontro el principio de las propiedades por codv!!!
				raf.write((line+"\r\n").getBytes());
				break;
			}
		}
		
		
	    for (int i=0;i<28+k;i++) {
	    	
	    	Secuencia sec = (Secuencia)secuencias.get(i);
	    	
	    	String cola = sec.getCola();
	    	String codigo = sec.getCodigo();
	    	String codv = sec.getCodv();
	    	
	    	raf.write(("set COLA_COND"+codv+" "+cola+";\r\n").getBytes());
	    	raf.write(("set COD_COND"+codv+" "+codigo+";\r\n").getBytes());
	    	raf.write(("\r\n").getBytes());
	    	ref.readLine();ref.readLine();ref.readLine();
	    
	    }
	    
	    line = ref.readLine();
	    
	    if(line.indexOf("Condicion de cerrar") == -1){
	    	throw new SapeDataException("[ERROR]No encontro el comentario de Condicion de cerrar.\nNo fue posible la ubicacion en el archivo.\n Linea: ["+line+"]");
	    }
	    
	    raf.write((line+"\r\n").getBytes());
	    
	    Secuencia sec = (Secuencia) secuencias.get(28+k);
	    
	    raf.write(("set REPETIDO_CERRAR "+sec.getClienteRepetido()+";\r\n").getBytes());
	    raf.write(("set COLA_NO_CERRAR "+sec.getSiCola()+";\r\n").getBytes());
	    raf.write(("set CODIGO_NO_CERRAR "+sec.getSiCodigo()+";\r\n").getBytes());
	    raf.write(("\r\n").getBytes());
	    ref.readLine();ref.readLine();ref.readLine();ref.readLine();

	    line = ref.readLine();
	    if(line.indexOf("Usuario FENIX") == -1){
	    	throw new SapeDataException("[ERROR]No encontro el comentario de Usuario FENIX.\nNo fue posible la ubicacion en el archivo.\n Linea: ["+line+"]");
	    }
	    String usuarioFenix = (String) secuencias.get(29+k);
	    raf.write((line+"\r\n").getBytes());
	    raf.write(("set USUARIO_FENIX "+usuarioFenix+";\r\n").getBytes());
	    raf.write(("\r\n").getBytes());
	    ref.readLine();ref.readLine();
	    
	    line = ref.readLine();
	    
	    if(line.indexOf("Condicion de error") == -1){
	    	throw new SapeDataException("[ERROR]No encontro el comentario de Usuario FENIX.\nNo fue posible la ubicacion en el archivo.\n Linea: ["+line+"]");
	    }
	    sec = (Secuencia) secuencias.get(30+k);
	    raf.write((line+"\r\n").getBytes());
	    raf.write(("set COLA_NOPRUEBA "+sec.getCola()+";\r\n").getBytes());
	    raf.write(("set COD_NOPRUEBA "+sec.getCodigo()+";\r\n").getBytes());
	    raf.write(("\r\n").getBytes());
	    ref.readLine();ref.readLine();ref.readLine();

	    line = ref.readLine();
	    
	    if(line.indexOf("Condiciones para GRANC") == -1){
	    	throw new SapeDataException("[ERROR]No encontro el comentario de Condiciones para GRANC.\nNo fue posible la ubicacion en el archivo.\n Linea: ["+line+"]");
	    }
	    sec = (Secuencia) secuencias.get(31+k);
	    raf.write((line+"\r\n").getBytes());
	    raf.write(("set COLA_GRANCDIA "+sec.getColaDia()+";\r\n").getBytes());
	    raf.write(("set COD_GRANCDIA "+sec.getCodigoDia()+";\r\n").getBytes());
	    raf.write(("set HORA_GRANCNOCHE "+sec.getHoraGrancNoche()+";\r\n").getBytes());
	    raf.write(("set COLA_GRANCNOCHE "+sec.getColaNoche()+";\r\n").getBytes());
	    raf.write(("set COD_GRANCNOCHE "+sec.getCodigoNoche()+";\r\n").getBytes());
	    raf.write(("\r\n").getBytes());
	    ref.readLine();ref.readLine();ref.readLine();ref.readLine();ref.readLine();ref.readLine();
	    
	    line = ref.readLine();
	    
	    if(line.indexOf("Condiciones ND") == -1){
	    	throw new SapeDataException("[ERROR]No encontro el comentario de Condiciones ND.\nNo fue posible la ubicacion en el archivo.\n Linea: ["+line+"]");
	    }
	    sec = (Secuencia) secuencias.get(32+k);
	    raf.write((line+"\r\n").getBytes());
	    raf.write(("set COLA_ND "+sec.getCola()+";\r\n").getBytes());
	    raf.write(("set COD_ND "+sec.getCodigo()+";\r\n").getBytes());
	    raf.write(("\r\n").getBytes());
	    raf.close();
		ref.close();
		
		boolean bol=fConfiguracion.renameTo(fileOld);

		if (bol == false) {
			throw new SapeDataException("No fue posible crear el archivo : '"+fConfiguracion.getName()+"'");
		} else {
			
			fileNow = new File(fConfiguracion.getParent()+"/evaluacionResultados.tcl");
			
			bol= fileTemp.renameTo(fileNow);
			
			if(bol == false){
				throw new SapeDataException("No fue posible crear el archivo : '"+fileTemp.getName()+"'");
			}
		}
	    
		ManejoUmbrales.actualizarArchivo("/opt/gude/sqltcl/evaluacionResultados.tcl","/opt/gude/sqltcl/");
	    
	    /*
	    secuencia.addContent(tmp);
	    
	    root.addContent(secuencia);
	    
	    try {
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error guardando el Documento", e);
            throw new SapeAppException("Error guardando el Documento XML");
        }
	    */
	    
		///////SE ESTA GUARDANDO HACIENDO LA SUPOSICION DE QUE NODOCOLAS GUARDA REFERENCIA
//	    try {//EN MEMORIA CON EL DOCUMENTO.
//            guardarDocumento(document, fConfiguracion);
//        } catch (IOException e) {
//            logs.error("Error guardando el Docuemnto", e);
//            throw new SapeAppException("Error guardando el Documento XML");
//        }
      
	}
	
	/*
    public static void main(String[] args) throws SapeAppException {
 
        EvaluacionResultados c = null;
        try {
        	c = new EvaluacionResultados(new File("/home/ajaristi/workspace/sape/sapeWeb/WEB-INF/evaluacionResultados.xml"));
        	//c = new EvaluacionResultados(new File(SapeConfiguration.getInstance().getRutaEvalResultados()));
        	c.cargarSecuencias();
        } catch (SapeAppException e) {
            System.out.println("ERROR: " + e);
        }
    }*/
    

    private String getToken(String source, int tok, String delim){
    	
    	StringTokenizer stt = new StringTokenizer(source,delim);
    	
    	if (stt.countTokens() <= tok){
    		System.out.println("[ERROR] los tokens no coinciden!!!");
    		return null;
    	}
    	
    	int i = 0;
    	while(stt.hasMoreTokens()){
    		String token = stt.nextToken();
    		if(i == tok){
    			token = token.replaceAll(";","");
    			return token;
    		}
    		
    		i++;
    	}
    	return null;
    }
    
}

