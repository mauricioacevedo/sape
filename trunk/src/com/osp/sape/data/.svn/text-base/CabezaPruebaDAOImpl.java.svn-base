/*
 * Created on Apr 2, 2005
 */
package com.osp.sape.data;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.CabezaPrueba;

/**
 * @author Andres Aristizabal
 */
public class CabezaPruebaDAOImpl extends HibernateObject implements
		CabezaPruebaDAO {

	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}

//	 protected Configuration getConfiguration() throws MappingException {
//	 if (configuration == null) {
//	 configuration = new Configuration();
//	 try {
//	 configuration.configure();
//	 } catch (HibernateException e) {
//	 System.out.println("ERROR: " + e);
//	 }
//	 }
//	 return configuration;
//	
//	 }

	public CabezaPrueba getCabezaPrueba(int id) throws SapeDataException {
		if (logs.isDebugEnabled())
			logs.debug("getCabezaPrueba: " + id);
		CabezaPrueba retorno = null;
		try {
			retorno = (CabezaPrueba) cargarObjeto(CabezaPrueba.class,
					new Integer(id));
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}
		return retorno;
	}

	public void insertarCabezaPrueba(CabezaPrueba c) throws SapeDataException {
		if (logs.isDebugEnabled())
			logs.debug("insertarCabezaPrueba: " + c);
		try {
			insertarObjeto(c);
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}
	}

	public void eliminarCabezaPrueba(int id) throws SapeDataException {
		if (logs.isDebugEnabled())
			logs.debug("eliminarCabezaPrueba: " + id);
		try {
			eliminarObjeto(getCabezaPrueba(id));
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}

	}

	public List getAllCabezaPrueba() throws SapeDataException {
		if (logs.isDebugEnabled())
			logs.debug("getAllCabezaPrueba");
		Session session = null;
		List l = null;
		Exception exception = null;
		try {
			session = getSession();
			l = session.find("from CabezaPrueba u order by u.id");
			session.flush();

		} catch (HibernateException e) {
			exception = e;
		} finally {
			try {
				if (session != null)
					session.close();
			} catch (HibernateException e1) {
				exception = e1;
			}
		}
		if (exception != null)
			throw new SapeDataException(exception);
		return l;
	}

	public void actualizarCabezaPrueba(CabezaPrueba u) throws SapeDataException {
		if (logs.isDebugEnabled())
			logs.debug("actualizarCabezaPrueba: " + u);
		try {
			actualizarObjeto(u);
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}
	}

}
