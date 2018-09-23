/*
 * Created on 14-feb-2005
 */
package com.osp.sape.data;

import java.io.Serializable;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.cfg.Configuration;

/**
 * Esta clase es una clase abstracta que representa los metodos mas comunes que se utilizan 
 * con hibernate, como es el metodo getSession, o las variables sessionFactori, configuration,
 * entre otras. Con esto pretendo simplificar el desarrollo de los DAOS.
 * @author Andres
 */
public abstract class HibernateObject {

	protected org.apache.log4j.Logger logs;
	protected boolean debug;
	
	protected Configuration configuration;

    /**
     * 
     * @uml.property name="sessionFactory"
     */
    protected SessionFactory sessionFactory;

//    private Session _session;

    protected HibernateObject() {
    	logs = org.apache.log4j.Logger.getLogger(getClass());
    	debug = logs.isDebugEnabled();
    }
    
    protected Configuration getConfiguration() throws HibernateException {
    	return getHibernateConfiguration().getConfiguration();
    }

    /**
     * Metodo que carga la configuracion de la base de datos.
     * Como se utiliza un solo sessionFactory para toda la aplicacion
     * la clase que implemente HibernateConfiguration maneja las instancias de
     * Configuration y de SessionFactory.
     * @return
     */
    protected abstract HibernateConfiguration getHibernateConfiguration();
    
    protected Session getSession() throws HibernateException {
//    	if (logs.isDebugEnabled()) logs.debug("getSession: " + _session);
//    	if (_session != null && logs.isDebugEnabled()) {
//    		logs.debug("isOpen: " + _session.isOpen() + " isConnected: " + _session.isConnected());
//    	}
//    	if (_session == null || !_session.isOpen() || !_session.isConnected()) {
//        	if (logs.isDebugEnabled()) logs.debug("creo una session");
//        	_session = getSessionFactory().openSession();
//		}
//		return _session;
    	return getSessionFactory().openSession();
	}


    protected SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            sessionFactory = getHibernateConfiguration().getSessionFactory();
//        	sessionFactory = HibernateConfiguration.getInstance().getSessionFactory();
//        	System.out.println("SessionFactory: " + sessionFactory);
        }
        return sessionFactory;
    }

 
    protected void insertarObjeto(Object o) throws HibernateException {
        HibernateException exception = null;
        
        Session session = getSession();
        Transaction tx = null;
        try {
		    tx = session.beginTransaction();

		    session.save(o);
			
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
		    exception = e;
		    if (tx != null) {
		        try {
		            tx.rollback();
		        } catch (HibernateException e1) {
		            exception = e1;
		        }
			}
		} finally {
            try {
            	 if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
		}
		if (exception != null) throw exception;
    }
    
    
    protected void actualizarObjeto(Object o) throws HibernateException {
        HibernateException exception = null;
        
        Session session = getSession();
	    Transaction tx = null;
	    try {
		    tx = session.beginTransaction();

		    session.update(o);
		    //session.refresh(o);
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
		    exception = e;
		    if (tx != null) {
			    try {
			    	tx.rollback();
		        } catch (HibernateException e1) {
		            exception = e1;
		        }
			}
		} finally {
            try {
            	 if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
		}
		if (exception != null) throw exception;
    }

    
    protected void eliminarObjeto(Object o) throws HibernateException {
        HibernateException exception = null;
        
        Session session = getSession();
        Transaction tx = null;
        try {
            
            tx = session.beginTransaction();
            
            session.delete(o);
            
            tx.commit();
            session.flush();
        } catch (HibernateException e) {
			exception = e;
            if (tx != null) {
		        try {
		            tx.rollback();
		        } catch (HibernateException e1) {
		            exception = e1;
		        }
			}
        } finally {
            try {
            	 if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
        }
        if (exception != null) throw exception;
    }
    
    /**
     * Efectua el metodo load de Hibernate.
     * @param theClass
     * @param id
     * @return
     */
    protected Object cargarObjeto(Class theClass, Serializable id) throws HibernateException {
        Object retorno = null;
        Session session = null;
        HibernateException exception = null;
        try {
            session = getSession();
            retorno =  session.load(theClass, id);
            session.flush();
        } catch (HibernateException e) {
            exception = e;
        } finally {
            try {
                 if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
        }
        if (exception != null) throw exception;
        return retorno;
    }
}
