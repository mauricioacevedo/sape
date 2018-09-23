/*
 * Created on Jun 15, 2005
 */
package com.osp.sape.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.Cable;

/**
 * 
 * @author Andres Aristizabal
 */
public class CableDAOImpl extends HibernateObject implements CableDAO {

    private org.apache.log4j.Logger logs; 
    
	public CableDAOImpl() {
		super();
		logs = org.apache.log4j.Logger.getLogger(getClass());
	}
	
	
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

    
    public void insertarCable(Cable c) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("insertarCable: " + c);
        try {
            insertarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }        

    }

    public Cable getCable(String id) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("getCable: " + id);
        Cable retorno = null;
        try {
            retorno = (Cable) cargarObjeto(Cable.class, id);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;        
    }

    
    
    
//    protected Configuration getConfiguration() throws MappingException {
//        if (configuration == null) {
//            configuration = new Configuration();
//            try {
//                configuration.configure();
//            } catch (HibernateException e) {
//                System.out.println("ERROR: " + e);
//            }
//        }
//        return configuration;
//    }
    
    public List getListadoCablesPorCentral(String central)throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getListadoCablesPorCentral: " + central);
    	Exception err=null;
        List l = new ArrayList();
        Session session= null;
        
        try{
            session= getSession();
            
            ResultSet rs=session.connection().createStatement().executeQuery("select cable from cables where central = '"+central+"'");
            int i=0;
            while(rs.next()){    
                l.add(i,rs.getString(1));
                i++;
            }
            
//            System.out.println("el tamano de la lista: "+l.size());
            session.flush();
            session.close();
            }catch(HibernateException e){
                err = e;
            } catch (SQLException e) {
                err=e;
            }finally{
                try {
                if(session != null) session.close();
                    } catch (HibernateException e1) {
                        err = e1;
                    }
            }
            
            if(err != null) throw new SapeDataException(err);
            
            return l;
    }

    
    public List getListadoArmariosPorCentral(String central)throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getListadoArmariosPorCentral: " + central);
    	Exception err=null;
        List l = new ArrayList();
        Session session= null;
        
        try{
            session= getSession();
                        
            ResultSet rs=session.connection().createStatement().executeQuery("select distinct(armario) from cables where central = '"+central+"'");
            int i=0;
            while(rs.next()){
                l.add(i,rs.getString(1));
                i++;
            }

//            System.out.println("el tamano de la lista: "+l.size());
            session.flush();
            session.close();
            }catch(HibernateException e){
                err = e;
            } catch (SQLException e) {
                err=e;
            }finally{
                try {
                if(session != null) session.close();
                    } catch (HibernateException e1) {
                        err = e1;
                    }
                
            }
            
            if(err != null) throw new SapeDataException(err);
            return l;
    }

}
