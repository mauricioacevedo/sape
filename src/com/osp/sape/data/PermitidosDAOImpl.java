/*
 * Created on Apr 3, 2005
 */
package com.osp.sape.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.Permitidos;

/**
 * 
 * @author Andres Aristizabal
 */
public class PermitidosDAOImpl extends HibernateObject implements PermitidosDAO {
	

    private org.apache.log4j.Logger logs; 
    
	public PermitidosDAOImpl() {
		super();
		logs = org.apache.log4j.Logger.getLogger(getClass());
	}
	
	protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationGestor.getInstance();
    }

	
//    protected Configuration getConfiguration() throws MappingException {
//        if (configuration == null) {
//            configuration = new Configuration();
//            try {
//                configuration.configure("/hibernate2.cfg.xml");
//            } catch (HibernateException e) {
//                System.out.println("ERROR: " + e);
//            }
//        }
//        return configuration;
//    }

    public void eliminarPermitidos(String id) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("eliminarPermitidos: " + id);
        try {
            eliminarObjeto(getPermitidos(id));
      } catch (HibernateException e) {
          throw new SapeDataException(e);
      }

    }

    public void insertarPermitidos(Permitidos c) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("insertarPermitidos: " + c);
        try {
            insertarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }    

    }


    public Permitidos getPermitidos(String id) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("getPermitidos: " + id);
        Permitidos retorno = null;
        try {
            retorno = (Permitidos) cargarObjeto(Permitidos.class, id);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }


    public List getAllPermitidos() throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("getAllPermitidos");
    	Session session= null;
    	List l = new ArrayList();
    	Exception exception = null;
    	try{
            session= getSession();
            l = session.find("from Permitidos per order by per.ip");
            session.flush();
            session.close();
    	}catch(HibernateException e){
    		exception = e;
        }finally{
        	try{
        		if(session != null) session.close();
        	}catch(HibernateException e){
        		exception=e;
        	}
        }
    	
    	if(exception != null)throw new SapeDataException(exception);
        return l;   
    }


    public void actualizarPermitidos(Permitidos u) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("actualizarPermitidos: " + u);
        try {
            actualizarObjeto(u);
	     } catch (HibernateException e) {
	         throw new SapeDataException(e);
	     }
    }
    
}
