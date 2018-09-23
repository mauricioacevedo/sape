/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Aug 11, 2006 - 12:18:51 PM
 */

package com.osp.sape.data;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.FastEpm;

public class FastEpmDAOImpl extends HibernateObject implements FastEpmDAO{

	@Override
	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}
	
    public void insertarFast(FastEpm u) throws SapeDataException {
    	if (debug) logs.debug("insertarFast: " + u);
    	try {
            insertarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public void actualizarFast(FastEpm u) throws SapeDataException {
    	if (debug) logs.debug("actualizarFast: " + u);
        try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarFast(FastEpm u) throws SapeDataException {
    	if (debug) logs.debug("eliminarFast: " + u);
        try {
            eliminarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarFast(String id) throws SapeDataException {
    	if (debug) logs.debug("eliminarFast: " + id);
    	try {
            eliminarObjeto(getFast(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public FastEpm getFast(String id) throws SapeDataException {
    	if (debug) logs.debug("getFast: " + id);
        FastEpm retorno = null;
        try {
            retorno = (FastEpm) cargarObjeto(FastEpm.class, id);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public FastEpm getFastPorTelefono(String telefono) throws SapeDataException{
        if (debug) logs.debug("getFastPorTelefono: "+telefono);
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from FastEpm u where u.telefonoFast ='"+telefono+"'");
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
        if(l==null||l.size()<=0)
        	return null;
        else
        	return (FastEpm) l.get(0);
    	
    }
    
    /**
     * Se hace mostrar en orden de central
     * */
    public List getAllFast()throws SapeDataException{
        if (debug) logs.debug("getAllFast");
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from FastEpm u order by u.numeroFast ASC");
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

    public List getFastPorNumero(int numeroFast)throws SapeDataException{
        if (debug) logs.debug("getFastPorNumero");
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from FastEpm u where u.numeroFast = "+numeroFast);
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

    /**
     * 17-oct-2006
     * John David Gutierrez
     * Permite Obtener todos los fast de una determinada entral
     * */
    public List getFastPorCentral(String central) throws SapeDataException {
		if (debug) logs.debug("getFastPorCentral");
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session = getSession();
	        l = session.find("from FastEpm u where u.central = '"+central+"'");
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
