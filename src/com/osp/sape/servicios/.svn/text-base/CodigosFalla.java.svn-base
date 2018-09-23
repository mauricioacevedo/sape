/*
 * Created on Mar 18, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.osp.sape.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.osp.sape.Exceptions.SapeAppException;
import com.osp.sape.maestros.CodigoFalla;
import com.osp.sape.maestros.CodigoFallaCola;
import com.osp.sape.maestros.NaturalezaReclamo;

/**
 * 
 * @author developTeam
 *
 * Clase encargada del manejo de los codigos de falla de la opcion rutinas.
 * Gestiona el archivo codigosFalla.xml.
 * 
 */
public class CodigosFalla {
	
	private Logger logs; 
	private File fConfiguracion;
	private List listaCodigos,listaColas,listaNaturalezas;
	private Document document;
	
	/**
	 * 
	 * @param fConfiguracion La ruta al documento en XML con los datos de la 
	 * configuracion.
	 */
	public CodigosFalla(File fConfiguracion) throws SapeAppException {
	
		logs = Logger.getLogger(this.getClass());
		
		if (logs.isInfoEnabled()) logs.info("[START] Codigos de Falla [/START]");
		this.fConfiguracion = fConfiguracion;
		if (logs.isDebugEnabled()) logs.debug("El archivo de configuracion es: " + fConfiguracion.getAbsolutePath());
		
		if (!fConfiguracion.exists()) {
			throw new SapeAppException("El archivo de configuracion " + fConfiguracion.getAbsolutePath() + " no Existe");
	   	}
		
		updateDocument();
	}
	/**
	 * Metodo utilizado para extraer del elemento root del documento la lista de codigos!!!!
	 * @param root un objeto de tipo ELement.
	 */
	private void cargarCodigos(Element root){
	    List l = root.getChildren();
	    listaCodigos = new ArrayList(l.size());
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
            Element codigo = (Element) iter.next();
            String fallaId = codigo.getChildText("fallaId");
            String nombre = codigo.getChildText("nombre");
            
            if (fallaId == null) fallaId = "";
            if (nombre == null) nombre = "";
            listaCodigos.add(new CodigoFalla( fallaId, nombre));
        }
		
	}
	
	/**
	 * Metodo para cargar en memoria las naturalezas de reclamo que se pueden asociar
	 * a las colas.
	 * @param root
	 */
	private void cargarNaturalezas(Element root){
	    List l = root.getChildren();
	    listaNaturalezas = new ArrayList<NaturalezaReclamo>(l.size());
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
            Element naturaleza = (Element) iter.next();
            String fallaId = naturaleza.getChildText("fallaId");
            String nombre = naturaleza.getChildText("nombre");
            
            if(fallaId==null && nombre==null){
            	continue;
            }
            
            if (fallaId == null) fallaId = "";
            if (nombre == null) nombre = "";

            listaNaturalezas.add(new NaturalezaReclamo(fallaId, nombre));            
        }
		
	}

	/**
	 * Metodo que carga las colas y sus respectivos codigos de falla del archivo xml!!!
	 * @param root
	 */
	private void cargarColas(Element root){

	    List l = root.getChildren();
	    listaColas = new ArrayList(l.size());
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
            Element colaCodigo = (Element) iter.next();
            
            String cola = colaCodigo.getAttributeValue("value");
            String fallaId = null;
            
            List lista = colaCodigo.getChildren("codigo");
            List listaCodigosPorCola = new ArrayList();
            List listaNaturalezasPorCola =  new ArrayList();
            for(Iterator iter2 = lista.iterator(); iter2.hasNext();){
            	Element c = (Element) iter2.next();
                fallaId = c.getText();               
                
                if (fallaId == null || fallaId.equals(""))
                	continue;
                listaCodigosPorCola.add(fallaId);
            }
            
            lista = colaCodigo.getChildren("naturaleza");
            
            for(Iterator iter2 = lista.iterator(); iter2.hasNext();){
            	Element c = (Element) iter2.next();
                fallaId = c.getText();               
                
                if (fallaId == null || fallaId.equals(""))
                	continue;
                listaNaturalezasPorCola.add(fallaId);
            }
            
            
            CodigoFallaCola cfc = new CodigoFallaCola(listaCodigosPorCola,cola,listaNaturalezasPorCola);
            
            listaColas.add(cfc);
        }
	    
	}
	
	private void updateDocument() throws SapeAppException {
		   SAXBuilder builder = new SAXBuilder();
			try {
			    document = builder.build(fConfiguracion);
			    Element root = document.getRootElement();
			    cargarCodigos(root.getChild("codigosFalla"));
			    cargarNaturalezas(root.getChild("naturalezasReclamo"));
			    cargarColas(root.getChild("colas"));
			} catch (JDOMException e) {
			    logs.error(e);
			    throw new SapeAppException(e.getMessage());
			} catch (IOException e) {
			    logs.error(e);
			    throw new SapeAppException(e.getMessage());
			}	
	}
	
	private void guardarDocumento(Document documento, File destino) throws IOException {
		FileOutputStream out = new FileOutputStream(destino);
		XMLOutputter serializer = new XMLOutputter();
			//Con este format es para que el documento quede tabulado.
		serializer.setFormat(Format.getPrettyFormat());
		serializer.output(documento, out);
		out.flush();	
		out.close();
	}

	public void adicionarCodigoFallaCola(CodigoFallaCola c) throws SapeAppException {
	    
	    CodigoFallaCola prueb = getCodigoFallaCola(c.getCola());
	    
	    if(prueb != null){
	        throw new SapeAppException("La cola: \""+c.getCola()+"\" ya tiene codigos de falla asignados.\nPor favor utilice otra Cola.");
	    }	    
	    Element cola = new Element("cola");
	    Element codigo = new Element("codigo");
	    Element naturaleza = new Element("naturaleza");
	    //Element nombre = new Element("nombre");
	    cola.setAttribute("value",c.getCola());
	    if (c.getCola() == null || c.getCola().equals("")) { 
	        throw new SapeAppException("La cola es invalida.");
	    } else {
	    	List listaCola = c.getFallasId();
	    	List listaNaturaleza = c.getNaturalezasId();
	    	int size = listaCola.size();
	    	for(int i=0;i<size;i++){
	    		codigo = new Element("codigo");
	    		codigo.setText((String)listaCola.get(i));
	    		cola.addContent(codigo);
	    	}
	    	
	    	size = listaNaturaleza.size();
	    	
	    	for(int i=0;i<size;i++){
	    		naturaleza = new Element("naturaleza");
	    		naturaleza.setText((String)listaNaturaleza.get(i));
	    		cola.addContent(naturaleza);
	    	}
	    	
        }
	    
	    //codigo.addContent(fallaId);
	    //codigo.addContent(nombre);
	    
	    Element root = document.getRootElement().getChild("colas");
	    root.addContent(cola);

	    try {
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error guardando el Docuemnto", e);
            throw new SapeAppException("Error guardando el Documento XML");
        }
        updateDocument();
	}

	public void adicionarNaturalezaReclamo(NaturalezaReclamo c) throws SapeAppException {
	    
		NaturalezaReclamo prueb = getNaturalezaReclamo(c.getFallaId());
	    
	    if(prueb != null){
	        throw new SapeAppException("La naturaleza: \""+c.getNombre()+"\" ya existe con id: \""+c.getFallaId()+"\" \nPor favor utilice otro id para la naturaleza.");
	    }	    
	    Element naturaleza = new Element("naturaleza");
	    Element fallaId = new Element("fallaId");
	    Element nombre = new Element("nombre");
	    
	    if (c.getFallaId() == null || c.getFallaId().equals("")) { 
	        throw new SapeAppException("El id de la naturaleza es invalido.");
	    } else {
	        nombre.setText(c.getNombre());
	        fallaId.setText(c.getFallaId());
        }
	    
	    naturaleza.addContent(fallaId);
	    naturaleza.addContent(nombre);
	    
	    Element root = document.getRootElement().getChild("naturalezasReclamo");
	    root.addContent(naturaleza);

	    try {
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error guardando el Docuemnto", e);
            throw new SapeAppException("Error guardando el Documento XML");
        }
        updateDocument();
	}

	
	public void adicionarCodigoFalla(CodigoFalla c) throws SapeAppException {
	    
	    CodigoFalla prueb = getCodigoFalla(c.getFallaId());
	    
	    if(prueb != null){
	        throw new SapeAppException("El codigo: \""+c.getNombre()+"\" ya existe con id: \""+c.getFallaId()+"\" \nPor favor utilice otro id para el codigo de falla.");
	    }	    
	    Element codigo = new Element("codigo");
	    Element fallaId = new Element("fallaId");
	    Element nombre = new Element("nombre");
	    
	    if (c.getFallaId() == null || c.getFallaId().equals("")) { 
	        throw new SapeAppException("El id del codigo de falla es invalido.");
	    } else {
	        nombre.setText(c.getNombre());
	        fallaId.setText(c.getFallaId());
        }
	    
	    codigo.addContent(fallaId);
	    codigo.addContent(nombre);
	    
	    Element root = document.getRootElement().getChild("codigosFalla");
	    root.addContent(codigo);

	    try {
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error guardando el Docuemnto", e);
            throw new SapeAppException("Error guardando el Documento XML");
        }
        updateDocument();
	}

	public void eliminarCodigoFallaCola(String cola) throws SapeAppException {
	    if(logs.isDebugEnabled()) logs.debug("eliminarCodigoFallaCOla: cola="+cola);
	    Element root = document.getRootElement().getChild("colas");
	    List l = root.getChildren();
	    int i=0;
	    for (i=0;i<l.size();i++) {
	        Element c=(Element) l.get(i);
	        //System.out.println("value("+i+")="+c.getAttributeValue("value"));
            if(c.getAttributeValue("value").equals(cola)){
            	// Encontro el dato a borrar!!!
                l.remove(i);
                i=-20;
                break;
            }
        }
	    if(i != -20){//NO EXISTIA ESTA cola EN EL ARCHIVO!!!!!!!!
	        throw new SapeAppException("No existe la cola : "+cola);
	    }
	    
	    try {
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error mientras se borraba la cola: "+cola, e);
            throw new SapeAppException("Error mientras se borraba la cola: "+cola);
        }
        updateDocument();
	}

	/**
	 * Elimina un codigo de falla del sistema.
	 * 
	 * @param fallaId
	 * @throws SapeAppException
	 */
	
	public void eliminarCodigoFalla(String fallaId) throws SapeAppException {
    		
	    Element root = document.getRootElement().getChild("codigosFalla");
	    List l = root.getChildren();
	    
	    int i=0;
	    for (i=0;i<l.size();i++) {
	        Element c=(Element) l.get(i);
	        //System.out.println("nombre("+i+")"+c.getChildText("nombre"));
            if(c.getChildText("fallaId").equals(fallaId)){
            	// Encontro el dato a borrar!!!
                l.remove(i);
                i=-20;
                break;
            }
        }
	    if(i != -20){//NO EXISTIA ESTE codigo EN EL ARCHIVO!!!!!!!!
	        throw new SapeAppException("No existe el codigo con id: "+fallaId);
	    }
	    
	    
	    try {
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error mientras se borraba el codigo con id: "+fallaId, e);
            throw new SapeAppException("Error mientras se borraba el codigo con id: "+fallaId);
        }
        updateDocument();
	}

	/**
	 * Elimina una naturaleza.
	 * 
	 * @param fallaId
	 * @throws SapeAppException
	 */
	
	public void eliminarNaturaleza(String fallaId) throws SapeAppException {
		
	    Element root = document.getRootElement().getChild("naturalezasReclamo");
	    List l = root.getChildren();
	    
	    int i=0;
	    for (i=0;i<l.size();i++) {
	        Element c=(Element) l.get(i);
            if(c.getChildText("fallaId").equals(fallaId)){
            	// Encontro el dato a borrar!!!
                l.remove(i);
                i=-20;
                break;
            }
        }
	    if(i != -20){//NO EXISTIA ESTE codigo EN EL ARCHIVO!!!!!!!!
	        throw new SapeAppException("No existe la naturaleza con id: "+fallaId);
	    }
	    
	    
	    try {
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error mientras se borraba la naturaleza con id: "+fallaId, e);
            throw new SapeAppException("Error mientras se borraba la naturaleza con id: "+fallaId);
        }
        updateDocument();
	}

	
	public CodigoFallaCola getCodigoFallaCola(String cola) throws SapeAppException {
	    
	    List l=getAllCodigosFallaCola();
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
            CodigoFallaCola element = (CodigoFallaCola) iter.next();
            
            //System.out.println("lista de colascodigo: "+element.getFallasId().size());
            
            if(element.getCola().equals(cola)){
                return element;
            }
            
        }
	    return null;
	}
	
	
	public CodigoFalla getCodigoFalla(String fallaId) throws SapeAppException {
	    
	    List l=getAllCodigosFalla();
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
            CodigoFalla element = (CodigoFalla) iter.next();
            if(element.getFallaId().equals(fallaId)){
                return element;
            }
            
        }
	    return null;
	}

	public NaturalezaReclamo getNaturalezaReclamo(String fallaId) throws SapeAppException {
	    
	    List l=getAllNaturalezasReclamo();
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
	    	NaturalezaReclamo element = (NaturalezaReclamo) iter.next();
            if(element.getFallaId().equals(fallaId)){
                return element;
            }
            
        }
	    return null;
	}

	public List getAllNaturalezasReclamo() throws SapeAppException {
	    updateDocument();
	    return listaNaturalezas;
	}
	
	public List getAllCodigosFallaCola() throws SapeAppException {
	    updateDocument();
	    return listaColas;
	}
	
	public List getAllCodigosFalla() throws SapeAppException {
	    updateDocument();
	    return listaCodigos;
	}
}
