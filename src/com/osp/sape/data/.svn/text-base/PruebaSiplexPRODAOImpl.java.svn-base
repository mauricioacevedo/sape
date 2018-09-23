/*
 * Created on Apr 14, 2005
 */
package com.osp.sape.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.PruebaBasica;
import com.osp.sape.maestros.PruebaSiplexPRO;

/**
 * 
 * @author Andres Aristizabal
 */
public class PruebaSiplexPRODAOImpl extends PruebaBasicaDAOImpl {

    public PruebaBasica getPruebaBasica(long id) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("getPruebaBasica: " + id);
    	PruebaSiplexPRO retorno = null;
        try {
            retorno = (PruebaSiplexPRO) cargarObjeto(PruebaSiplexPRO.class, new Long(id));
        } catch (HibernateException e) {
        	logs.error(e);
        	throw new SapeDataException(e);
        }
        return retorno;   
    }

    public List getAllPruebaBasica() throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getAllPruebaBasica");
    	Session session = null;
        List l = new ArrayList();
    	Exception exception = null;
    	try{
            session= getSession();
            l = session.find("from PruebaSiplexPRO p order by p.id");
            session.flush();
	    }catch(HibernateException e){
	        logs.error(e);
	    	exception =e;
	    }finally{
        	try{
        		if(session != null) session.close();
        	}catch(HibernateException e) {
        		logs.error(e);
        		exception=e;
        	}
        }
	    
	    if(exception != null) throw new SapeDataException(exception);
        return l;
    }
}
