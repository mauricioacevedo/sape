/*
 * Created on Jun 17, 2005
 */
package com.osp.sape.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.RutinaCable;

/**
 * 
 * @author Andres Aristizabal y Mauricio Acevedo
 */
public class RutinaCableDAOImpl extends HibernateObject implements RutinaCableDAO {

	private org.apache.log4j.Logger logs;
	
	public RutinaCableDAOImpl() {
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
//                logs.error("ERROR: " + e);
//            }
//        }
//        return configuration;
//    }

    
    public List getAllRutinaCable() throws SapeDataException {
        Exception e=null;
        List l = null;
        Session session = null;
        try{
            session= getSession();
            l = session.find("from RutinaCable u order by u.cable");
            session.flush();
            session.close();

            }catch(HibernateException e1){
                e = e1;
            }finally{
                try{
                    if(session !=null) session.close();
                }catch(HibernateException e1){
                    e=e1;
                }
            }
            if(e != null) throw new SapeDataException(e);
            return l;
    }

    
    public List getRutinaCable(String cable) throws SapeDataException{
    	if (debug) logs.debug("getRutinaCable: " + cable );
    	
	    Exception e = null;
	    List l = null;
	    Session session = null;
	    try {
	        session= getSession();
	        l = session.find("from RutinaCable u where u.cable = '"+cable+"'");
	        
	        session.flush();
	
	        }catch(HibernateException e1){
	        	logs.error(e1);
	            e = e1;
	        } finally {
	            try{
	            	if(session !=null) session.close();
	            }catch(HibernateException e1){
	            	logs.error(e1);
	                e=e1;
	            }
	        }
	        if(e != null) throw new SapeDataException(e);
	        return l;
    }
    
    public List getRutinaCable(long tel) throws SapeDataException{
    	if (debug) logs.debug("getRutinaCable: tel=" + tel );
    	
	    Exception e = null;
	    List l = null;
	    Session session = null;
	    try {
	        session= getSession();
	        l = session.find("from RutinaCable u where u.telefono = "+tel+"");
	        
	        session.flush();
	
	        }catch(HibernateException e1){
	        	logs.error(e1);
	            e = e1;
	        } finally {
	            try{
	            	if(session !=null) session.close();
	            }catch(HibernateException e1){
	            	logs.error(e1);
	                e=e1;
	            }
	        }
	        if(e != null) throw new SapeDataException(e);
	        return l;
    }
    
    
    public List getTelefonosPorCable(String cable, String estado) throws SapeDataException{
    	if (debug) logs.debug("getTelefonosPorCable: " + cable + ", " + estado);
    	
	    Exception e = null;
	    List l = null, total = new ArrayList();
	    Session session = null;
	    try {
	        session= getSession();
	        
	        //XXX este codigo es para corregir el error en las
	        //cifras entre los telefonos de rutina_cable y los
	        // telefonos que estan en pruebaprogramada.
	        
//	        Statement st = null;

//	        Transaction tx = null;
//        	try {
//        		tx = session.beginTransaction();
//				st = session.connection().createStatement();
//				st.executeUpdate("drop table yayaTemp;");
//				st.executeUpdate("drop table yayaTempTel;");
//				tx.commit();
//			} catch (SQLException e2) {
//				tx.rollback();
//			}
//	        try {
	        	//System.out.println("cable es: ["+cable+"]");
	        	//TODO validar si esto ya sobra.
//	        	st.execute("select transaccion into temp yayaTemp from pruebaprogramada where tipodeprueba='CA-"+cable+"';");
//				st.execute("select telefono into temp yayaTempTel from pruebaspp where idpruebaprogramada in (select * from yayaTemp);");
//				st.executeUpdate("update rutina_cable set estatus = 'II' where cable='"+cable+"' and telefono not in (select * from yayaTempTel);");
//                st.close();
//			} catch (SQLException e1) {
//				logs.error(e1);
//				e = e1;
//			}
	        l=session.find("from RutinaCable u where u.cable = '"+(cable.startsWith("directo_")?cable:cable.toUpperCase())+"'"+(estado != null && !estado.equals("")?" and u.estatus = '"+estado+"'":"")+" order by u.cable");
	        	                    
            total.add(0,l);
            l = null;
            
            l = session.find("select count(*),u.estatus from RutinaCable u where u.cable = '"+(cable.startsWith("directo_")?cable:cable.toUpperCase())+"' group by u.estatus"); 
            
            total.add(1,l);
	        
	        session.flush();
	
	        }catch(HibernateException e1){
	        	logs.error(e1);
	            e = e1;
	        } finally {
	            try{
	            	if(session !=null) session.close();
	            }catch(HibernateException e1){
	            	logs.error(e1);
	                e=e1;
	            }
	        }
	        if(e != null) throw new SapeDataException(e);
	        return total;
    }

    
    public void eliminarRutinaCable(int tel) throws SapeDataException {

        try {
            eliminarObjeto(getRutinaCable(tel));
      } catch (HibernateException e) {
          throw new SapeDataException(e);
      }
    }

    public void insertarRutinaCable(RutinaCable c) throws SapeDataException {
        try {
            insertarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public RutinaCable getRutinaCable(int tel) throws SapeDataException {
        RutinaCable retorno = null;
        try {
            retorno = (RutinaCable) cargarObjeto(RutinaCable.class, new Integer(tel));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public void actualizarRutinaCable(RutinaCable u) throws SapeDataException {
	    try {
	           actualizarObjeto(u);
	    } catch (HibernateException e) {
	        throw new SapeDataException(e);
	    }
    }
    
    public boolean eliminarAllCable(String cable)throws SapeDataException{

        Session session = null;
        Exception exception = null;
		Transaction tx = null;
        try{
            session= getSession();
            tx = session.beginTransaction();
            session.connection().createStatement().executeUpdate("delete from rutina_cable where cable='"+cable+"'");
            tx.commit();
            session.flush();
        }catch(HibernateException e1){
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
        if (exception != null) throw new SapeDataException(exception);
        return true;    }

}
