/*
 * Created on 16-abr-2005
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

/**
 * Clase que manejara la configuracion de los servicios de EPM
 * @author Andres
 */
public class ConfiguracionServicios{

	private Logger logs; 
    
	private ArrayList listaAlarmas;
	private ArrayList listaColas;
	private Document document;
	private File fConfiguracion;
	
	/**
	 * 
	 * @param fConfiguracion La ruta al documento en XML con los datos de la 
	 * configuraci�n de los servicios.
	 */
	public ConfiguracionServicios(File fConfiguracion) throws SapeAppException {
		logs = Logger.getLogger(this.getClass());
		
		logs.info("*** Comienza ***");
	   this.fConfiguracion = fConfiguracion;
	   if (logs.isDebugEnabled()) logs.debug("El archivo de configuracion es: " + fConfiguracion.getAbsolutePath());
	   
	   if (!fConfiguracion.exists()) {
	       throw new SapeAppException("El archivo de configuracion " + fConfiguracion.getAbsolutePath() + "no Existe");
	   }
	   
	   actualizarListas();
	   // ESTE METODO ACTUALIZA LA INFORMACION QUE HAY EN CADA LISTA!!!!!!!!
	}
	
	public String getServicioAlarmaValue(String medio, String hijo){
// para obtener la informacion para la clase servicioAlarma
// se pasa por parametro el medio (medio-beeper, medio-correo,etc) y el valor solicitado!	    
	    Element root = document.getRootElement();
	    Element sa = root.getChild("servicioAlarma");
	    Element med = sa.getChild(medio);
	    
	    if(med == null)
	        return null;
	    
	    String val = med.getChildText(hijo);
//	    System.out.println("valor medio="+med);
	    
	    if(val == null)
	        return null;
	    else if(val.equals(""))
	        return null;
	    
        return val;
	}

	/**
	 * SE ACTUALIZA LA LISTA DE COLAS Y DE ALARMAS QUE HAY EN MEMORIA.
	 * DESDE EL ARCHIVO de configuracion
	 * @throws SapeAppException
	 */
	private void actualizarListas() throws SapeAppException{
		   SAXBuilder builder = new SAXBuilder();
			try {
			    document = builder.build(fConfiguracion);
			    Element root = document.getRootElement();
			    cargarColas(root.getChild("colas"));
			    cargarAlarmas(root.getChild("alarmas-pendientes"));
			} catch (JDOMException e) {
			    logs.error(e);
			    throw new SapeAppException(e.getMessage());
			} catch (IOException e) {
			    logs.error(e);
			    throw new SapeAppException(e.getMessage());
			}
	}
	
	
	private void cargarAlarmas(Element alarm) throws SapeAppException {
	    List l = alarm.getChildren();
	    listaAlarmas = new ArrayList(l.size());
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
            
	        Element alarma = (Element) iter.next();
            
	        String nombreCola = alarma.getChildText("cola");
            
	        int limite = -20;

	        try {
                limite=Integer.parseInt(alarma.getChildText("limite"));
            } catch (NumberFormatException e) {
                throw new SapeAppException(e.getMessage());
            }
            
            String avisar = alarma.getChildText("avisar");
            String mensaje = alarma.getChildText("mensaje");
            
            
            Element tmp=alarma.getChild("medio");
            
            
            String tipo=tmp.getAttributeValue("tipo");
            String valorMedio = tmp.getChildText("valor");
            
            
            if(tipo == null || valorMedio == null)
                throw new SapeAppException("El medio de envio esta mal formado.");
            if(tipo.equals("") || valorMedio.equals(""))
                throw new SapeAppException("El medio de envio esta mal formado.");
            
            //en tipo esta el tipo de medio para la alarma: beeper o correo
            //en valorMedio esta o la direccion de correo electronico o el codigo del beeper
//            System.out.println("EL TIPO DE ESTA ALARMA ES: "+tipo+" el valor es: "+valorMedio);
            
            MedioInformar md = null;
            if(tipo.equals("correo")){
                md = new MedioCorreo(valorMedio);
            }else if( tipo.equals("beeper")){
                md = new MedioBeeper(null,valorMedio);
            /// el primer parametro es el tel... como se va a enviar el beeper directamente
            ///desde la aplicacion no se necesita este parametro. en valorMedio esta el 
            ///codigo del beeper
            }else if(tipo.equals("smsOla")){
                md = new MedioSmsOLA(valorMedio);
            }
            
	        int recordar = -20;
	        try {
                recordar=Integer.parseInt(alarma.getChildText("recordar"));
            } catch (NumberFormatException e) {
                throw new SapeAppException(e.getMessage());
            }
            Alarma al = new Alarma(nombreCola,limite,avisar,mensaje,md,recordar);
            
            listaAlarmas.add(al);
            
        }
	}
	
	
	private void cargarColas(Element colas) {
	    List l = colas.getChildren();
	    listaColas = new ArrayList(l.size());
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
            Element cola = (Element) iter.next();
            String nombre = cola.getChildText("nombre");
            String descripcion = cola.getChildText("descripcion");
            if (descripcion == null) descripcion = "";
            listaColas.add(new Cola(nombre, descripcion));
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
	
	public ArrayList getAllColas() throws SapeAppException {
	    actualizarListas();
	    return listaColas;
	}
	
	public void adicionarCola(Cola c) throws SapeAppException {
	    
	    Cola prueb = getCola(c.getNombre());
	    
	    if(prueb != null){
	        throw new SapeAppException("La cola: \""+c.getNombre()+"\" ya existe!!!\nPor favor utilice otro nombre para la cola.");
	    }	    
	    
	    
	    Element nodoColas = document.getRootElement().getChild("colas");
	    if (nodoColas == null) 
	        throw new SapeAppException("El Documento no tiene el tag Colas");
	    
	    Element cola = new Element("cola");
	    Element nombre = new Element("nombre");
	    if (c.getNombre() == null || c.getNombre().equals("")) { 
	        throw new SapeAppException("El nombre de la cola es invalido");
	    } else {
	        nombre.setText(c.getNombre());
        }
	    cola.addContent(nombre);

	    if (c.getDescripcion() != null && !c.getDescripcion().equals(""))	{
	        Element descripcion = new Element("descripcion");
	        descripcion.setText(c.getDescripcion());
	        cola.addContent(descripcion);
	    }	    
	    
	    nodoColas.addContent(cola);
	    ///////SE ESTA GUARDANDO HACIENDO LA SUPOSICION DE QUE NODOCOLAS GUARDA REFERENCIA
	    try {//EN MEMORIA CON EL DOCUMENTO.
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error guardando el Docuemnto", e);
            throw new SapeAppException("Error guardando el Documento XML");
        }
        actualizarListas();
	}
	
	public void eliminarCola(String nombre) throws SapeAppException {
        
	    try {
            eliminarAlarma(nombre);
        } catch (SapeAppException e1) {
           //Para controlar cuando no tiene alarma!!!!!!!
            
        }	    
	    Element nodoColas = document.getRootElement().getChild("colas");
	    List l = nodoColas.getChildren();
	    int i=0;
	    for (i=0;i<l.size();i++) {
	        Element c=(Element) l.get(i);
	        //System.out.println("nombre("+i+")"+c.getChildText("nombre"));
            if(c.getChildText("nombre").equals(nombre)){
//                System.out.println("AKI ESTA VA A BORRAR LA COLA!!!");
                l.remove(i);
                i=-20;
                break;
            }
        }
	    if(i != -20){//NO EXISTIA ESTA COLA EN EL ARCHIVO!!!!!!!!
	        throw new SapeAppException("No existe la cola con nombre: "+nombre);
	    }
	    
	    //nodoColas.setAttributes(l);
	    try {
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error mientras se borraba la cola: "+nombre, e);
            throw new SapeAppException("Error mientras se borraba la cola: "+nombre);
        }
        actualizarListas();
	}
	
	public Cola getCola(String nombre) throws SapeAppException {
	    
	    List l=getAllColas();
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
            Cola element = (Cola) iter.next();
            if(element.getNombre().equals(nombre)){
                return element;
            }
            
        }
	    return null;
	}
	
	public ArrayList getAllAlarmas() throws SapeAppException { 
	    actualizarListas();
	    return listaAlarmas;
	}
	
	public Alarma getAlarma(String cola) throws SapeAppException {
	    List l=getAllAlarmas();
	    for (Iterator iter = l.iterator(); iter.hasNext();) {
            Alarma element = (Alarma) iter.next();
            if(element.getNombreCola().equals(cola)){
                return element;
            }
            
        }
	    return null;
	}
	
	public void adicionarAlarma(Alarma a) throws SapeAppException {

	    Cola col=getCola(a.getNombreCola());
	    Alarma ali = getAlarma(a.getNombreCola());
	    if(col == null){
	        // INTENTAN GUARDAR UNA ALARMA CON UNA COLA QUE NO EXISTE.
	        throw new SapeAppException("Esta intentando generar una alarma para una cola que no existe!.");
	    }
	    if(ali != null){
//	      SI ENTRA YA HAY UNA ALARMA ASOCIADA A ESTA COLA!!!!!!
	        throw new SapeAppException("Ya hay una alarma asociada a la cola: "+a.getNombreCola());  
	    }
	    
	    Element nodoListas = document.getRootElement().getChild("alarmas-pendientes");
	    
	    if (nodoListas == null) 
	        throw new SapeAppException("El Documento no tiene el tag Colas");
	    
	    if(a.getMedio() == null || a.getLimite()==0 || a.getMensaje() == null || a.getNombreCola()==null || a.getRecordar() == 0 ){
	       
//	        System.out.println("EL ERROR DEBE SER:");
//	        System.out.println(a.getMedio() +" "+ a.getLimite()+" "+ a.getMensaje() +" "+  a.getNombreCola()+" "+ a.getRecordar());
	        
	        throw new SapeAppException("La informacion de la alarma no esta completa.");
	    }
	    Element alarma = new Element("alarma");
	    Element cola = new Element("cola");
	    Element limite = new Element("limite");
	    Element avisar = new Element("avisar"); 
	    Element mensaje = new Element("mensaje");
	    Element medio = new Element("medio");
	    	Element valor=new Element("valor");
	    Element recordar = new Element("recordar");
	   
	    
	    cola.setText(a.getNombreCola());
	    limite.setText(String.valueOf(a.getLimite()));//ESTABA SACANDO ERROR DE
	    avisar.setText(a.getAvisar());//////////////////CASTING.
	    mensaje.setText(a.getMensaje());
	    
	    MedioInformar mi = a.getMedio();
	    
	    if (mi instanceof MedioCorreo){
	        
	        MedioCorreo mc = (MedioCorreo)mi;
	        medio.setAttribute("tipo", mc.getMedio());
	        
	        System.out.println("KEDO CON EL MEDIO: "+mc.getMedio());
	        
	        valor.setText(mc.getDireccion());
	    }else if(mi instanceof MedioSmsOLA){
	        
	        MedioSmsOLA ms = (MedioSmsOLA)mi;
	        medio.setAttribute("tipo", ms.getMedio());
	        valor.setText(ms.getTelefono());
	        System.out.println("entro como smsOla");
	    }else{
	        
	        MedioBeeper mc = (MedioBeeper)mi;
	        medio.setAttribute("tipo", mc.getMedio());
	        valor.setText(mc.getCodigo());
	        System.out.println("entro por el ultimo else "+mc.getMedio());
	    }
        medio.addContent(valor);	    
	    
        recordar.setText(String.valueOf(a.getRecordar()));
        
        
	    alarma.addContent(cola);
	    
	    
	    
	    alarma.addContent(limite);
	    alarma.addContent(avisar);
	    alarma.addContent(mensaje);
	    alarma.addContent(medio);
	    alarma.addContent(recordar);
	    nodoListas.addContent(alarma);
//	    System.out.println();
	    
//	    System.out.println(document.getRootElement().getChild("colas").getChildren());
//	    System.out.println(document.getRootElement().getChild("alarmas-pendientes").getChildren());
//	    
//	    System.out.println("nodolistas es ="+nodoListas.getChildren().size());
	    
	    ///////SE ESTA GUARDANDO HACIENDO LA SUPOSICION DE QUE NODOCOLAS GUARDA REFERENCIA
	    try {//EN MEMORIA CON EL DOCUMENTO.
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error guardando el Docuemnto", e);
            throw new SapeAppException("Error guardando el Documento XML");
        }
        //actualizarListas();
        
	}
	
	public void eliminarAlarma(String cola) throws SapeAppException {
	    Element nodoAlarmas = document.getRootElement().getChild("alarmas-pendientes");
	    List l = nodoAlarmas.getChildren();
	    int i=0;
//	   
	    for (i=0;i<l.size();i++) {
	        Element c=(Element) l.get(i);
	        //System.out.println("nombre("+i+")"+c.getChildText("nombre"));
            if(c.getChildText("cola").equals(cola)){
                l.remove(i);
                i=-20;
                break;
            }
        }
	    
	    if(i != -20){//NO EXISTIA ESTA COLA EN EL ARCHIVO!!!!!!!!
	        Cola cc = getCola(cola);
	        if(cc == null)
	            throw new SapeAppException("No existe la cola con nombre: "+cola);
	        else 
	            throw new SapeAppException("No se ha generado ninguna alarma para la cola: "+cola);
	    }
	    
	    //nodoColas.setAttributes(l);
	    try {
            guardarDocumento(document, fConfiguracion);
        } catch (IOException e) {
            logs.error("Error mientras se borraba la alarma de la cola: "+cola, e);
            throw new SapeAppException("Error mientras se borraba la alarma de la cola: "+cola);
        }
        actualizarListas();
	}
	
}
