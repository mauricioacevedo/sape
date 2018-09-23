package com.osp.sape.data;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.IndigoEpm;

public class IndigoEpmDAOImpl extends HibernateObject implements IndigoEpmDAO {

	@Override
	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}
	
    public void insertarIndigo(IndigoEpm u) throws SapeDataException {
    	if (debug) logs.debug("insertarIndigo: " + u);
    	try {
            insertarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public void actualizarIndigo(IndigoEpm u) throws SapeDataException {
    	if (debug) logs.debug("actualizarIndigo: " + u);
        try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarIndigo(IndigoEpm u) throws SapeDataException {
    	if (debug) logs.debug("eliminarIndigo: " + u);
        try {
            eliminarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarIndigo(long id) throws SapeDataException {
    	if (debug) logs.debug("eliminarIndigo: " + id);
    	try {
            eliminarObjeto(getIndigo(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public IndigoEpm getIndigo(long id) throws SapeDataException {
    	if (debug) logs.debug("getIndigo: " + id);
        IndigoEpm retorno = null;
        try {
            retorno = (IndigoEpm) cargarObjeto(IndigoEpm.class, new Long(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getAllIndigo()throws SapeDataException{
        if (debug) logs.debug("getAllIndigo");
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from IndigoEpm u order by u.numeroIndigo ASC");
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return l;

    }

    public List getIndigoPorNumero(String numeroIndigo)throws SapeDataException{
        if (debug) logs.debug("getIndigoPorNumero");
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from IndigoEpm u where u.numeroIndigo = '"+numeroIndigo+"'");
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return l;

    }
}
