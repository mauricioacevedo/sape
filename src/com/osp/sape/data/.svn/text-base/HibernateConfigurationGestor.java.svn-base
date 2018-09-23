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
public class HibernateConfigurationGestor implements HibernateConfiguration {

	
	private Configuration _configuration;
	private SessionFactory _sessionFactory;
	
	private static HibernateConfigurationGestor _instancia;
		
	public static HibernateConfiguration getInstance() {
		if (_instancia == null) {
			_instancia = new HibernateConfigurationGestor();
		}
		return _instancia;
	}
	
	public Configuration getConfiguration() throws HibernateException {
		if (_configuration == null) {
			_configuration = new Configuration();
			_configuration.configure("/hibernate2.cfg.xml");
		}
		return _configuration;
	}
	
	
	public SessionFactory getSessionFactory() throws HibernateException {
		if (_sessionFactory == null) {
			_sessionFactory = getConfiguration().buildSessionFactory();
		}
		return _sessionFactory;
	}
}
