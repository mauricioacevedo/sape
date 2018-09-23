/*
 * Created on Jun 13, 2005
 */
package com.osp.sape.data;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.RutinaCliente;

/**
 * 
 * @author Andres Aristizabal
 */
public class RutinaClienteDAOImpl extends HibernateObject implements
        RutinaClienteDAO {

    private org.apache.log4j.Logger logs;
    
    public RutinaClienteDAOImpl(){
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


    public int eliminarRangoRutinaCliente(String inicio, String fin,String cliente) throws SapeDataException{
    	if (debug) logs.debug("eliminarRangoRutinaCliente: " + inicio+", "+fin+", "+cliente);
    	
        Session session = null;
        Exception exception = null;
        Statement st = null;
        Transaction tx = null;
        int rows = -20;
        try {
			session = getSession();
			st = session.connection().createStatement();
			tx = session.beginTransaction();
			rows=st.executeUpdate("delete from rutina_cliente where telefono between "+inicio+" and "+fin+" and usuario = '"+cliente+"'");
			tx.commit();
		} catch (HibernateException e) {
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				if(logs.isDebugEnabled())logs.debug("ERROR: "+e1.getMessage());
			}
			exception =e;
		} catch (SQLException e) {
			exception =e;
		} finally {
			try {
				if(session != null) session.close();
			}catch(HibernateException e) {
				exception =e;
			}
		}
		if(exception != null) throw new SapeDataException(exception);
    	return rows;
    }
    
    public void eliminarRutinaCliente(long id) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("eliminarRutinaCliente: " + id);
        
        Session session = null;
        Exception exception = null;
        Statement st = null;
        Transaction tx = null;
        try {
			session = getSession();
			st = session.connection().createStatement();
			tx = session.beginTransaction();
			st.executeUpdate("delete from rutina_cliente where telefono = '"+id+"'");
			tx.commit();
		} catch (HibernateException e) {
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				if(logs.isDebugEnabled())logs.debug("ERROR: "+e1.getMessage());
			}
			exception =e;
		} catch (SQLException e) {
			exception =e;
		} finally {
			try {
				if(session != null) session.close();
			}catch(HibernateException e) {
				exception =e;
			}
		}
		if(exception != null) throw new SapeDataException(exception);
    }

    public void eliminarRutinaPorCliente(String cliente,long id) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("eliminarRutinaCliente: cliente="+cliente+", telefono=" + id);
        
        Session session = null;
        Exception exception = null;
        Statement st = null;
        Transaction tx = null;
        try {
			session = getSession();
			st = session.connection().createStatement();
			tx = session.beginTransaction();
			st.executeUpdate("delete from rutina_cliente where telefono = '"+id+"' and usuario='"+cliente+"'");
			tx.commit();
		} catch (HibernateException e) {
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				if(logs.isDebugEnabled())logs.debug("ERROR: "+e1.getMessage());
			}
			exception =e;
		} catch (SQLException e) {
			exception =e;
		} finally {
			try {
				if(session != null) session.close();
			}catch(HibernateException e) {
				exception =e;
			}
		}
		if(exception != null) throw new SapeDataException(exception);
    }


    public void insertarRutinaCliente(RutinaCliente c) throws SapeDataException {
//		  Cambiado el 27-07-2006, debido a que el oid no se puede insertar.
//        if (debug) logs.debug("insertarRutinaCliente: " + c);
//        try {
//            insertarObjeto(c);
//        } catch (HibernateException e) {
//            throw new SapeDataException(e);
//        }
    	
        //List l = null;
        Session session = null;
        Exception exception = null;
        Transaction tx = null;
        try{
            session= getSession();
            tx=session.beginTransaction();
            String sql = "insert into rutina_cliente (telefono, nombre, estatus, usuario)"+
            " values ('"+c.getTelefono()+"','"+c.getNombre()+"','"+c.getEstatus()+"','"+c.getUsuario()+"')";
            
            Statement st = session.connection().createStatement();
            
            if(debug)logs.debug("[SQL(JDBC): "+sql+"]");
            st.executeUpdate(sql);
            tx.commit();
            //l = session.find(sql);
            
            session.flush();
        }catch(HibernateException e){
            exception = e;
        } catch (SQLException e) {
        	exception = e;
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				logs.error("IMPOSIBLE hacer el rollback!!!");
				exception = e;
			}
		} finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
        }
        if (exception != null) throw new SapeDataException(exception);    	
    }

    
    public List getRutinasPorCliente(String cliente,long telefono) throws SapeDataException{
        List l = null;
        Session session = null;
        HibernateException exception = null;
        try{
            session= getSession();
            
            String sql = "from RutinaCliente u where u.usuario = '"+cliente+"' "+(telefono != -1 ?" and u.telefono = "+telefono+"":"")+" order by u.telefono";
            
            if(debug)logs.debug("[SQL: "+sql+"]");
            
            l = session.find(sql);

            session.flush();
        }catch(HibernateException e){
            exception = e;
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
    

    public RutinaCliente getRutinaCliente(long id) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getRutinaCliente: " + id);
        RutinaCliente retorno = null;
        try {
            retorno = (RutinaCliente) cargarObjeto(RutinaCliente.class, new Long(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getRutinasClientePorEstado(String cliente,String estado) throws SapeDataException {
    	
        if (debug) logs.debug("getRutinasClientePorEstado: " + estado);
        List l = null,total=new ArrayList();
        Session session = null;
        HibernateException exception = null;
                
        try{
            session= getSession();
            l = session.find("from RutinaCliente u where u.usuario = '"+cliente+"' "+(estado != null && !estado.equals("")?" and u.estatus = '"+estado+"'":"")+" order by u.telefono");
            
            total.add(0,l);
            l = null;
            
            l = session.find("select count(*),u.estatus from RutinaCliente u where u.usuario = '"+cliente+"' group by u.estatus");             
            total.add(1,l);

            session.flush();
        }catch(HibernateException e){
            exception = e;
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
        }
        if (exception != null) throw new SapeDataException(exception);
        return total;
    }

    
    public List getAllRutinaCliente() throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getAllRutinaCliente");
        List lt = null;
        Session session = null;
        HibernateException exception = null;
        try{
            session= getSession();
            lt = session.find("from RutinaCliente u order by u.telefono");
            session.flush();
        }catch(HibernateException e){
            exception = e;
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
        }
        if (exception != null) throw new SapeDataException(exception);
        return lt;
    }

// comentariado el 27-07-2006.
//    public void actualizarRutinaCliente(RutinaCliente c)throws SapeDataException{
//	    if (logs.isDebugEnabled()) logs.debug("actualizarRutinaCliente: " + u);
//	    try {
//	           actualizarObjeto(u);
//	    } catch (HibernateException e) {
//	        throw new SapeDataException(e);
//	    }
//    }
//    	
//        	
//    }

    	
    	
    
}
