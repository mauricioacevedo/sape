package com.osp.sape.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ajaristi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MostrarRequest extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Enumeration e;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "co"));
		out.println("<div align=\"center\"><strong>Datos Entrados el: " + 
					f.format(new Date()) + "</strong></div>");
		out.println("<p>&nbsp;</p>");
		out.println("<b>ATRIBUTOS DEL CONTEXTO</B><br>");
		ServletContext contexto = getServletContext();
		e = contexto.getAttributeNames();
		while (e.hasMoreElements()) {
			String atributo = (String) e.nextElement();
			out.println(atributo + ": " + contexto.getAttribute(atributo) + "<br>");
		}
		contexto = null;
		out.println("<p>&nbsp;</p>");
		out.println("<b>DATOS DEL REQUEST</B><BR>");
		e = req.getParameterNames();
		while (e.hasMoreElements()) {
			String parametro = (String)e.nextElement();
			String[] parametros = req.getParameterValues(parametro);
			for (int i = 0; i < parametros.length; i++) {
				out.println(parametro + ": " + parametros[i] + "<br>");	
			}			
		}
		out.println("<p>&nbsp;</p>");
		out.println("<b>DATOS DE LA CABECERA</B><BR>");
		e = req.getHeaderNames();
		while (e.hasMoreElements()) {
			String header = (String)e.nextElement();
			out.println(header + ": " + req.getHeader(header) + "<br>");	
		}
		out.println("<p>&nbsp;</p>");
		out.println("LOCALE " + req.getLocale());
		out.println("<p>&nbsp;</p>");

	}
	
	

}
