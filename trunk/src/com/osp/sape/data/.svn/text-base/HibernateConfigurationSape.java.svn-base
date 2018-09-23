package com.osp.sape.data;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;


/**
 * Clase que maneja una unica instancia de los objetos
 * Configuration y SessionFactory para toda la aplicacion.
 * @author Andres Aristizabal
 *
 */
public class HibernateConfigurationSape implements HibernateConfiguration {

	private org.apache.log4j.Logger logs;   
	
	private Configuration _configuration;
	private SessionFactory _sessionFactory;
	
	private static HibernateConfigurationSape _instancia;
		
	private HibernateConfigurationSape() {
		logs = org.apache.log4j.LogManager.getLogger(getClass());
		if (logs.isDebugEnabled()) logs.debug("Instanciando la Clase");
	}
	
	public static HibernateConfiguration getInstance() {
		if (_instancia == null) {
			_instancia = new HibernateConfigurationSape();
		}
		return _instancia;
	}
	
	public Configuration getConfiguration() throws HibernateException {
		if (_configuration == null) {

			if (logs.isDebugEnabled()) logs.debug("Instanciando la Configuracion");
//          //XXX ESTA ES LA FORMA DE UTILIZAR MULTIPLES BD EN HIBERNATE...
//          configuration.configure("/hibernate2.cfg.xml");
			_configuration = new Configuration();
			_configuration.configure();
		}

		return _configuration;
	}
	
	
	public SessionFactory getSessionFactory() throws HibernateException {
		if (_sessionFactory == null) {
			if (logs.isDebugEnabled()) logs.debug("Instanciando El SessionFactory");
			_sessionFactory = getConfiguration().buildSessionFactory();
		}
		return _sessionFactory;
	}
}
