/*
 * Created on Jun 13, 2005
 */
package com.osp.sape.data;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.HoraPruebaCliente;

/**
 * 
 * @author Andres Aristizabal
 */
public class HoraPruebaClienteDAOImpl extends HibernateObject implements
        HoraPruebaClienteDAO {

    private org.apache.log4j.Logger logs;
    
    public HoraPruebaClienteDAOImpl() {

        logs = org.apache.log4j.Logger.getLogger(getClass());
    }
        
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }


//    protected Configuration getConfiguration() throws MappingException {
//        if (configuration == null) {
//            configuration = new Configuration();
//            try {
//                configuration.configure();
//            } catch (HibernateException e) {
//                logs.error(e);
//            }
//        }
//        return configuration;
//    }
    
    public List getHorariosCliente()throws SapeDataException{
        if (logs.isDebugEnabled()) logs.debug("getHorariosCliente");
        List l = null;
        Session session = null;
        Exception exception = null;
        try{
            session= getSession();
            
            l=session.find("from HoraPruebaCliente u");
            session.flush();
            session.close();
        }catch(HibernateException e1){
            exception = e1;
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
        }
        if (exception != null) throw new SapeDataException(exception);
        return l;
    }
    
    public void actualizarHorarioPruebaClientes(HoraPruebaCliente hpc) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("actualizarHorarioPruebaClientes"); 

        Session session = null;
        Exception exception = null;
        Statement st = null;
        Transaction tx = null;
        int rta=-1;
        try{
            session= getSession();
            tx = session.beginTransaction();
            st=session.connection().createStatement();
            st.executeUpdate("delete from hrprueba_clientes");
            session.connection().createStatement().executeUpdate("INSERT INTO hrprueba_clientes VALUES("+hpc.toString()+")");
            tx.commit();
            
            session.flush();
            session.close();
        }catch(HibernateException e1){

            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e2) {
                    exception = e2;
                }
            }
            exception = e1;
        } catch (SQLException e) {
            exception = e;
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
        }
        if (exception != null) throw new SapeDataException(exception + " valor de Retorno: '"+rta+"'");

    }
    

    public List getListasHorarios() throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getListasHorarios");
        
        Session session = null;
        Exception exception = null;
    	List l = null;
        try {
			session = getSession();
			
			l=session.find("from HoraPruebaCliente e");
		} catch (HibernateException e) {
			logs.error(e);
			exception =e;
		} finally {
			
			try {
				if(session != null) session.close();
			}catch(HibernateException e){
				logs.error(e);
				exception =e;
			}
		}
		
		if(exception != null) throw new SapeDataException(exception);
		
		HoraPruebaCliente h = (HoraPruebaCliente)l.get(0);
		
		return h.getListsByType();
		
    }
    
    
}
