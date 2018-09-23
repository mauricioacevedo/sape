package com.osp.sape.data;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.CalificacionEPM;

public class CalificacionEPMDAOImpl extends HibernateObject implements
		CalificacionEPMDAO {

	private Logger logs;
	
	public CalificacionEPMDAOImpl() {
		super();
		logs = LogManager.getLogger(getClass());
	}

    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationGestor.getInstance();
    }

	
    public void insertarCalificacionEpm(CalificacionEPM u) throws SapeDataException {
        try {
            insertarObjeto(u);
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo insertarCalificacionEPM:\n"+e);
            throw new SapeDataException(e);
        }
    }

    public void actualizarCalificacionEpm(CalificacionEPM u) throws SapeDataException {
        try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo actualizarCalificacionEPM:\n"+e);
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarCalificacionEpm(CalificacionEPM U) throws SapeDataException {
        try {
            eliminarObjeto(U);
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo eliminarCalificacionEPM:\n"+e);
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarCalificacionEpm(long id) throws SapeDataException {
        try {
            eliminarObjeto(getCalificacionEpm(id));
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo eliminarCalificacionEPM(catch 1):\n"+e);
            throw new SapeDataException(e);
        } catch (SapeDataException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo eliminarCalificacionEPM(catch 2):\n"+e);
            throw new SapeDataException(e);
        }
    }

     public CalificacionEPM getCalificacionEpm(long id) throws SapeDataException {

    	CalificacionEPM retorno = null;
        try {
            retorno = (CalificacionEPM) cargarObjeto(CalificacionEPM.class, new Long(id));
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo getCalificacionEPM:\n"+e);
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getAllCalificacionEpm(String orderBy)throws SapeDataException{
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from CalificacionEPM u order by u."+orderBy);
	        session.flush();
	        session.close();
        }catch(HibernateException e){
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo getAllCalificacionEPM(catch 1):\n"+e);
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				if(logs.isDebugEnabled()) logs.debug("ERROR en metodo getAllCalificacionEPM(catch 2):\n"+e);
				exception = e;
			}
    	}
        if(exception != null) throw new SapeDataException(exception);
        return l;
    }
}
