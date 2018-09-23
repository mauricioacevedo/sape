/*
 * Created on Mar 16, 2005
 */
package com.osp.sape.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.CodigoVer;

/** * @author Andres Aristizabal */
public class CodigosVerDAOImpl extends HibernateObject implements CodigosVerDAO {

    //private org.apache.log4j.Logger logs;
	
    public CodigosVerDAOImpl() {   
		super();
		logs = org.apache.log4j.Logger.getLogger(getClass());
    }

    
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

    
    public void insertarCodigoVer(CodigoVer c) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("insertarCodigoVer: " + c);
        try {
            insertarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }        

    }

     public void eliminarCodigoVer(CodigoVer c) throws SapeDataException {
    	 if (logs.isDebugEnabled()) logs.debug("eliminarCodigoVer: " + c);
        try {
            eliminarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

     public void eliminarCodigoVer(String id) throws SapeDataException {
    	 if (logs.isDebugEnabled()) logs.debug("eliminarCodigoVer: " + id);
        try {
              eliminarObjeto(getCodigoVer(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }

    }
     
     public void actualizarCodigoVer(CodigoVer codv) throws SapeDataException {
    	 if (logs.isDebugEnabled()) logs.debug("actualizarCodigoVer: " + codv);
        try {
              actualizarObjeto(codv);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }

    }
     

    public CodigoVer getCodigoVer(String id) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getCodigoVer: "  + id);
        CodigoVer retorno = null;
        try {
            retorno = (CodigoVer) cargarObjeto(CodigoVer.class, id);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;        
    }

    public List getAllCodigosVer()throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getAllCodigosVer");
    	List l  = new ArrayList();
    	Session session = null;
    	Exception exception = null;
    	try{
	        session= getSession();
	        l = session.find("from CodigoVer u order by u.codigoVer");
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
    
    public List getCodigoVerNoETB() throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getCodigoVerNoETB");
    	List l  = new ArrayList();
    	Session session = null;
    	Exception exception = null;
    	try{
	        session= getSession();
	        l = session.find("from CodigoVer u where u.codigoVer not in (select c.codvSAPE from CodigoVerETB c) order by u.codigoVer");
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
