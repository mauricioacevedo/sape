package com.osp.sape.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.Categorias;

public class CategoriasDAOImpl extends HibernateObject implements CategoriasDAO {
	
	
    private org.apache.log4j.Logger logs;
    
    public CategoriasDAOImpl() {
        logs = org.apache.log4j.Logger.getLogger(getClass());
    }

	
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

	
	public void insertarCategorias(Categorias c) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("insertarCategorias: " + c);
		try {
			insertarObjeto(c);
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}
	}
	
	public void eliminarCategorias(Categorias c) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("eliminarCategorias: " + c);
		try {
			eliminarObjeto(c);
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}
	}
	
	public void eliminarCategorias(String id) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("eliminarCategorias: " + id);
		try {
			eliminarObjeto(getCategorias(id));
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}
		
	}
	
	public Categorias getCategorias(String id) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("getCategorias: " + id);
		Categorias retorno = null;
		try {
			retorno = (Categorias) cargarObjeto(Categorias.class, new Integer(id));
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}
		return retorno;        
	}
	
	public List getAllCategorias()throws SapeDataException{
		if (logs.isDebugEnabled()) logs.debug("getAllCategorias");
		List l  = new ArrayList();
		Session session = null;
		Exception exception = null;
		try{
			session= getSession();
			l = session.find("from Categorias u order by u.ticket");
			session.flush();
			session.close();
			
		}catch(HibernateException e){
			exception =e;
		}finally{
			try{
				if(session != null)session.close();
			}catch(HibernateException e){
				exception = e;
			}
		}
		if(exception != null) throw new SapeDataException(exception);
		return l;
	}    
	
//	protected Configuration getConfiguration() throws MappingException {
//		if (configuration == null) {
//			configuration = new Configuration();
//			try {
//				configuration.configure();
//			} catch (HibernateException e) {
//				System.out.println("ERROR: " + e);
//			}
//		}
//		return configuration;		
//	}
	
}
