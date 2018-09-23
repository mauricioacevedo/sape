/*
 * Created on Feb 28, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.gc.utils;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;

import com.gc.acceso.GestorServlet;

public class UploadServlet extends GestorServlet {

	public void init() throws ServletException {
		super.init();
		logs.debug("Hizo el init()");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException { 
		logs.debug("INICIANDO UploadServlet . . .");
	    String destino = null;
	    request.setAttribute("tipo","cerrar");
	    boolean isMultiPart = FileUpload.isMultipartContent(request);
		if (debug) logs.debug("MultiPart: " + isMultiPart);
		DiskFileUpload upload = new DiskFileUpload();
		/*
		 * TODO:hacer esto para que tambien se puedan montar varios archivos.
		 * 		para que es la vble redireccion????		
		 */
		//String redireccion = null;
		try {
	    
			//upload.setSizeMax(10000000);
			java.util.List items = upload.parseRequest(request);
		    
		    
		    
		    
		    if (debug) logs.debug("Numero de Elementos: "+items.size());
			java.util.Iterator itr = items.iterator();
			while(itr.hasNext()) {
			    FileItem item = (FileItem) itr.next();
			    if (item.isFormField() && item.getFieldName().equals("destino")) {
					destino = item.getString();
			    }
			}
			if (debug) logs.debug("el destino es: " + destino);

			itr = items.iterator();

			
			while(itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				
				if (debug) logs.debug("Campo: " + item.getFieldName() + ", valido: " + !item.isFormField());

				// Verifica si el item es un campo de formulario o un archivo montable.
				if(!item.isFormField()) {
					String nombreArchivo = item.getName();
					if (debug) logs.debug("NombreArchivo: "+nombreArchivo);
					
					if(nombreArchivo == null || nombreArchivo.equals("")){//error
						logs.debug("Archivo invalido. Nombre de archivo vacio");
						error("Archivo invalido. Nombre de archivo vacio",request,response);
						return;
					}
					
					
					nombreArchivo = nombreArchivo.replaceAll("\\\\", "/");
//					log (4, "nombreArchivo: " + nombreArchivo);
					int posicion = -1;
					if ((posicion = nombreArchivo.lastIndexOf("/")) != -1) {
//						log(4, "nombreArchivo: " + nombreArchivo + "pos: " + posicion);
						nombreArchivo = nombreArchivo.substring(posicion + 1);
					}
					
					
//						//si el destino termina en '/', pongo el mismo nombre al archivo.
//					if (destino.endsWith("/")) {
//						destino += nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));
//					}
					//acciones.getTemplate("temporalReportes")
					
					if (debug) logs.debug("se monta: " + nombreArchivo + ", tamano: " + item.getSize());
					
					//String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf('.'), nombreArchivo.length());
//					File savedFile = new File(getServletContext().getRealPath("/uploads/"),item.getName());
					
					File savedFile = new File(getServletContext().getRealPath("/")+acciones.getTemplate("temporalReportes") + nombreArchivo.toLowerCase());
					
					
					//File savedFile = new File("/tmp/" + nombreArchivo);
					
					if (debug) logs.debug("savedFile es: " + savedFile + ", item es: " + item);
					try {
						savedFile.createNewFile();
						item.write(savedFile);
						
						if (debug) logs.debug("Archivo montado con exito");
						
						
						//response.sendRedirect("/"+destino+"&archivo="+savedFile.getName().toLowerCase()+"#fin");
						//return;
						logs.debug("redireccion hacia: "+"/"+destino+"&archivo="+savedFile.getName().toLowerCase());
						//request.getRequestDispatcher("/"+destino+"&archivo="+savedFile.getName().toLowerCase()).forward(request,response);
						
						String file = savedFile.getName().toLowerCase();
						
						logs.debug("Nombre del archivo Final: "+file);
						response.sendRedirect("/sape/"+destino+"&archivo="+file);
						
						//TODO: verificar por ke esto esta en un ciclo.
						//		el 21-07-2006 se coloca un return para ke redireccione solo la primera vez.
						return;
					} catch (Exception e) {
					    logs.error(e);
					    error(e,request,response);
					    return;
					}
				}
			}
		} catch (FileUploadException e) {
			logs.error(e);
		    error(e,request,response);
		    return;
			
		}
		//logs.debug("Va a hacer la redireccion hacia: " + redireccion);
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		logs.debug("OJO, entro por metodo GET");
		doPost(request,response);
		return;
	}
}
