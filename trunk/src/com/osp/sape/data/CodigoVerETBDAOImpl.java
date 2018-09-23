/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en May 25, 2006 - 11:11:37 AM
 */

package com.osp.sape.data;

import java.util.ArrayList;
import java.util.List;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.CodigoVerETB;

public class CodigoVerETBDAOImpl extends HibernateObject implements CodigoVerETBDAO {

	public CodigoVerETBDAOImpl() {
		super();
		logs = org.apache.log4j.Logger.getLogger(getClass());
	}

	@Override
	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}
	
    public void insertarCodvETB(CodigoVerETB c) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("insertarCodigoVerETB: " + c);
        try {
            insertarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }        

    }

     public void eliminarCodvETB(CodigoVerETB c) throws SapeDataException {
    	 if (logs.isDebugEnabled()) logs.debug("eliminarCodigoVerETB: " + c);
        try {
            eliminarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

     public void eliminarCodvETB(String id) throws SapeDataException {
    	 if (logs.isDebugEnabled()) logs.debug("eliminarCodigoVerETB: " + id);
        try {
              eliminarObjeto(getCodvETB(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }

    }
     
     public void actualizarObjetoETB(CodigoVerETB id) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("actualizarObjetoETB: " + id);
		Transaction tx = null;
		Session session = null;
		Exception exception = null;
		try {
			session=getSession();
			tx = session.beginTransaction();
		    actualizarObjeto(id);
		    tx.commit();		      
		} catch (HibernateException e) {
			logs.error(e);
			try {
				tx.rollback();
				tx=session.beginTransaction();
				insertarObjeto(id);
				tx.commit();
				exception=null;
			} catch (HibernateException e2) {
				logs.error (e2);
				exception = e2;
			}
		} finally {
			try {
				if (session != null) session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;
			}
		}
		
		if(exception != null) throw new SapeDataException(exception);

    }
     

    public CodigoVerETB getCodvETB(String id) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getCodigoVerETB: "  + id);
        CodigoVerETB retorno = null;
        try {
            retorno = (CodigoVerETB) cargarObjeto(CodigoVerETB.class, id);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;        
    }

    public List<CodigoVerETB> getAllCodvETB()throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getAllCodigosVer");
    	List l  = new ArrayList();
    	Session session = null;
    	Exception exception = null;
    	try{
	        session= getSession();
	        l = session.find("from CodigoVerETB u order by u.codvETB");
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

}
