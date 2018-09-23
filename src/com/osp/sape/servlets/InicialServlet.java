package com.osp.sape.servlets;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import net.sf.hibernate.HibernateException;

import com.osp.sape.SapeConfiguration;
import com.osp.sape.Exceptions.SapeAppException;
import com.osp.sape.data.HibernateConfigurationSape;
import com.osp.sape.utils.ConfiguracionClienteSape;

public class InicialServlet extends HttpServlet {

	private org.apache.log4j.Logger logs;
	
	public void init() throws ServletException {
		super.init();
		logs=org.apache.log4j.LogManager.getLogger(getClass());
		
		boolean debug = logs.isDebugEnabled();
		boolean info = logs.isInfoEnabled();
		
		if(info) logs.info("Arranca InicialServlet");
		try {
			if(info) logs.debug("Inicializando SapeConfiguration");
			SapeConfiguration.inicializar(getServletContext().getRealPath("/WEB-INF/") + "/sape-config.xml");
			
			if(info) logs.info("Borrando Archivos Temporales de los reportes");
			File f = new File(getServletContext().getRealPath("/") + "/reportes/tmp/");
			if (f.isDirectory()) {
				if(debug) logs.debug("Borrando " + f.getAbsolutePath());
				File temporales[] = f.listFiles();
				for (int i = 0; i < temporales.length; i++) {
					if (debug) logs.debug("Se elimina: " + temporales[i].getAbsolutePath() + (temporales[i].delete() ? " OK" : " FALLO"));
				}
			} else { 
				if(debug) logs.warn(f.getAbsolutePath() + " NO es directorio.");
			}
			
			String cliente = SapeConfiguration.getInstance().getClienteSistema();
			if(info) logs.info("Inicializando Configuracion de Cliente: " + cliente);
			ConfiguracionClienteSape.inicializar(getServletContext().getRealPath("/WEB-INF/") + "/configuracionCliente-" + cliente + ".xml");
				//Configura la cantidad de digitos en el sistema.
			getServletContext().setAttribute("cantDigitosEntorno", new Integer(ConfiguracionClienteSape.getInstance().getCantDigitos()));
			if (info) logs.info("Inicializando la Base de Datos");
			net.sf.hibernate.Session s = HibernateConfigurationSape.getInstance().getSessionFactory().openSession();
			s.close();
						
		} catch (SapeAppException e) {
			throw new RuntimeException(e);
		} catch (HibernateException e) {
			throw new RuntimeException(e);
		}
	}
	
}
