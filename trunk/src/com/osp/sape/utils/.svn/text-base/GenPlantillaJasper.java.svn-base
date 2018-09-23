/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Jul 15, 2006 - 1:05:35 PM
 */

package com.osp.sape.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.osp.sape.Exceptions.SapeAppException;

public class GenPlantillaJasper {

	private Logger logs; 
    
	private Document document;
	private String tituloReporte;
	private String campos[];
	//fConfigDefault: archivo donde esta la pantilla de muestra que utilizamos para generar las demas plantillas
	//fileTemp: referencia al archivo de la nueva plantilla.
	private File fConfigDefault,fileTemp;
	private boolean debug=true;
	public GenPlantillaJasper(String tituloReporte,String cams[],File file) throws SapeAppException {
		super();
		logs = Logger.getLogger(this.getClass());
		debug=logs.isDebugEnabled();
		this.tituloReporte=tituloReporte;
		campos=cams;
		fConfigDefault=file;
		
		logs.info("*** Comienza GenPlantillaJasper ***");
		
		if (debug) logs.debug("El archivo de configuracion es: " + fConfigDefault.getAbsolutePath());
		
		if (!fConfigDefault.exists()) {
			throw new SapeAppException("El archivo de configuracion " + fConfigDefault.getAbsolutePath() + "no Existe");
		}
		
		generarArchivo();
	}

	
	
	private void generarArchivo() throws SapeAppException {
		if (logs.isDebugEnabled()) logs.debug("generarArchivo");
		
		loadArchivoDefault();
	    
	    File dirTemp = new File(fConfigDefault.getParent());

	    if (logs.isDebugEnabled()) logs.debug("dir temporal: "+dirTemp.getAbsolutePath()+" directorio? "+dirTemp.isDirectory());
	    String nameTmp=tituloReporte.replace(' ','_');

		fileTemp=new File(dirTemp,nameTmp+".jrxml");
		try {
			fileTemp.createNewFile();
		} catch (IOException e) {
			logs.error(e);
		}
		
	    Element root = document.getRootElement();
//	    Element jasper=root.getChild("jasperReport");
	    
	    root.setAttribute("name",tituloReporte.replace(' ','_'));
	    // se multiplica por 130 porke hay ke guardar 30 de uno de los bordes
	    //root.setAttribute("pageWidth",String.valueOf(130*campos.length+(campos.length==1?30:0)));
	    root.setAttribute("pageWidth",String.valueOf(100*campos.length+60));
	    root.setAttribute("columnWidth","100");
	    Element reportElement = root.getChild("title").getChild("band").getChild("textField").getChild("reportElement");
	    
	    int x = 100*campos.length-100;
	    x =(int)x/2;
	    
	    reportElement.setAttribute("x",String.valueOf(x));
	    
	    // Genera dentro del XML las variables necesarias.
	    generarVariables(root);
	    
	    // Genera el xml para agregar los campos ke seran visibles y organizarlos.
	    actualizarCabeceras(root);
	    
	    //System.out.println("columns"+columnHeader.getChild("band").getChild("textField").getChildren().toString());
	    
	    try {
			guardarDocumento(fileTemp);
		} catch (IOException e) {
			logs.error(e);
			throw new SapeAppException(e);
		}
	    
		logs.info("Plantilla terminada con exito, file: ["+fileTemp.getAbsolutePath()+"]");
	}
	
	private void actualizarCabeceras(Element root){
	    
		// Primero se generan los campos de las cabeceras de columnas!!!
		Element columnHeader = root.getChild("columnHeader");
	    Element band = columnHeader.getChild("band");
	    
	    Element textfield =  band.getChild("textField");
	    Element textfield2 =  band.getChild("textField");
	    
	    for(int i=0;i<campos.length;i++){
		    Element report=textfield2.getChild("reportElement");
		    report.setAttribute("x",String.valueOf(i*100));
		    report.setAttribute("width","100");
		    report.setAttribute("key",campos[i]+"-key");
		    Element textFieldExpression = textfield2.getChild("textFieldExpression");
		    //System.out.println("TEXTO: "+textFieldExpression.getText());
		    //textFieldExpression.setText("<![CDATA[$P{"+campos[i]+"-param}]]>");
		    textFieldExpression.setText("$P{"+campos[i]+"-param}");
		    if(i!=0){
		    	band.addContent(textfield2);
		    }
		    textfield2 = (Element) textfield.clone();
	    }
	    
	    // Luego generamos los lugares donde va el contenido.
	    
		Element detail = root.getChild("detail");
	    band = detail.getChild("band");
	    
	    textfield =  band.getChild("textField");
	    textfield2 =  band.getChild("textField");
	    
	    for(int i=0;i<campos.length;i++){
		    Element report=textfield2.getChild("reportElement");
		    report.setAttribute("x",String.valueOf(i*100));
		    report.setAttribute("width","100");
		    report.setAttribute("key",campos[i]+"-key2");
		    Element textFieldExpression = textfield2.getChild("textFieldExpression");
		    //System.out.println("TEXTO: "+textFieldExpression.getText());
		    //textFieldExpression.setText("<![CDATA[$P{"+campos[i]+"-param}]]>");
		    textFieldExpression.setText("$F{"+campos[i]+"-field}");
		    if(i!=0){
		    	band.addContent(textfield2);
		    }
		    textfield2 = (Element) textfield.clone();
	    }
	}
	
	
	private void generarVariables(Element root){
	    // Asumimos que la plantilla no tiene un campo configurado.
	    // Primero los parametros para el titulo
	    int size = campos.length;
	    //Hago 2 ciclos para ke la plantilla quede un poco organizada.
	    
	    // parametros para el llenado del reporte.
	    Element fieldBase = new Element("field");
	    fieldBase.setAttribute("class","java.lang.String");
	    for(int i=0;i<size;i++){
	    	Element field = (Element) fieldBase.clone();
	    	field.setAttribute("name",campos[i]+"-field");
	    	root.addContent(13,field);// el indice es para acomodar la informacion en el lugar respectivo en la plantilla
	    }
	    
    	Element parameter = new Element("parameter");
    	parameter.setAttribute("isForPrompting","false");
    	parameter.setAttribute("class","java.lang.String");	    
	    for(int i=0;i<size;i++){
	    	Element param = (Element) parameter.clone();
	    	param.setAttribute("name",campos[i]+"-param");
	    	root.addContent(13,param);// el indice es para acomodar la informacion en el lugar respectivo en la plantilla
	    }
	    
	    
	}
	
	private void loadArchivoDefault() throws SapeAppException{
		if (logs.isDebugEnabled()) logs.debug("loadArchivoDefault");
		   SAXBuilder builder = new SAXBuilder();
			try {
				if (logs.isDebugEnabled()) logs.debug("fConfigDefault: " + fConfigDefault);
			    document = builder.build(fConfigDefault);
			} catch (JDOMException e) {
			    logs.error(e);
			    throw new SapeAppException(e.getMessage());
			} catch (IOException e) {
			    logs.error(e);
			    throw new SapeAppException(e.getMessage());
			}
	}
	
	public static void main(String[] args) {
		
		String titulo = "titulo del reporte";
		String names[]={"campo1","campo2","campo3"};
		//String names[]={"campo1"};
		File file = new File("/usr/java/tomcat/webapps/sape/reportes/plantillas/reporteador/reporteDefault.jrxml");
		
		try {
			new GenPlantillaJasper(titulo,names,file);
		} catch (SapeAppException e) {
			System.out.println("[ERROR] error: "+e);
		}

	}
	
	private void guardarDocumento(File destino) throws IOException {
		FileOutputStream out = new FileOutputStream(destino);
		XMLOutputter serializer = new XMLOutputter();
			//Con este format es para que el documento quede tabulado.
		serializer.setFormat(Format.getPrettyFormat());
		serializer.output(document, out);
		out.flush();	
		out.close();
	}


}