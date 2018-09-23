/*
 * Created on Jun 16, 2005
 */
package com.osp.sape.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.RutinaArmario;

/**
 * 
 * @author Andres Aristizabal
 */
public class RutinaArmarioDAOImpl extends HibernateObject implements
        RutinaArmarioDAO {
    private org.apache.log4j.Logger logs;
    
    public RutinaArmarioDAOImpl(){
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
    
    public List getAllRutinaArmario() throws SapeDataException{
        Exception e=null;
        List l = null;
        Session session = null;
        try {
            session= getSession();
            l = session.find("from RutinaArmario u order by u.armario");
            session.flush();

            } catch(HibernateException e1){
                e = e1;
            } finally{
                try{
                    if(session !=null) session.close();
                }catch(HibernateException e1){
                    e=e1;
                }
            }
            if(e != null) throw new SapeDataException(e);
            return l;
    } 
    
    
    public List getTelefonosPorArmario(String armario, String estado)throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getTelefonosPorArmario: " + armario + ", " + estado);
    	
        Exception e=null;
        List l = null,total=new ArrayList();
        Session session = null;
        try{
            session= getSession();
            
	        //XXX este codigo es para corregir el error en las
	        //cifras entre los telefonos de rutina_cable y los
	        // telefonos que estan en pruebaprogramada.
	        
//	        Statement st = null;
//
//	        Transaction tx = null;
//        	try {
//        		tx = session.beginTransaction();
//				st = session.connection().createStatement();
//				st.executeUpdate("drop table yayaTemp;");
//				st.executeUpdate("drop table yayaTempTel;");
//				tx.commit();
//			} catch (SQLException e2) {
////				System.out.println("Exception[mientras se borraba tabla yayaTemp o yayaTempTel]: "+e2.toString());
//				tx.rollback();
//			}
//	        
//	        try {
//				st.execute("select transaccion into temp yayaTemp from pruebaprogramada where tipodeprueba='AR-"+armario+"';");
//				st.execute("select telefono into temp yayaTempTel from pruebaspp where idpruebaprogramada in (select * from yayaTemp);");
//				st.executeUpdate("update rutina_armario set estatus = 'II' where armario='"+armario+"' and telefono not in (select * from yayaTempTel);");				
//			} catch (SQLException e1) {
//				e = e1;
//			}

            l=session.find("from RutinaArmario u where u.armario = '"+armario.toUpperCase()+"'"+(estado != null && !estado.equals("")?" and u.estatus = '"+estado+"'":"")+" order by u.armario");
            total.add(0,l);
            l = null;

            l = session.find("select count(*),u.estatus from RutinaArmario u where u.armario = '"+armario.toUpperCase()+"' group by u.estatus"); 
            
            total.add(1,l);

            session.flush();

            }catch(HibernateException e1) {
            	logs.error(e1);
                e = e1;
            }finally{
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

    public List getRutinaArmario(long telefono)throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getTelefonosPorArmario: " +telefono);
    	
        Exception e=null;
        List l = null;
        Session session = null;
        try{
            session= getSession();
            

            l=session.find("from RutinaArmario u where u.telefono = "+telefono);
            
            session.flush();

            }catch(HibernateException e1) {
            	logs.error(e1);
                e = e1;
            }finally{
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

    
    
    public void eliminarRutinaArmario(int tel) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("eliminarRutinaArmario: " + tel);
        try {
            eliminarObjeto(getRutinaArmario(tel));
      } catch (HibernateException e) {
          throw new SapeDataException(e);
      }
    }


    public void insertarRutinaArmario(RutinaArmario c) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("insertarRutinaArmario: " + c);
        try {
            insertarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }    
    }


    public RutinaArmario getRutinaArmario(int tel) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getRutinaArmario: " + tel);
        RutinaArmario retorno = null;
        try {
            retorno = (RutinaArmario) cargarObjeto(RutinaArmario.class, new Integer(tel));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }
    
    public void actualizarRutinaArmario(RutinaArmario u)throws SapeDataException{
	    if (logs.isDebugEnabled()) logs.debug("actualizarRutinaArmario: " + u);
	    try {
	           actualizarObjeto(u);
	    } catch (HibernateException e) {
	        throw new SapeDataException(e);
	    }
    }
    
    
    public boolean eliminarAllArmario(String armario)throws SapeDataException{
        //BORRA TODAS LAS TUPLAS DE ESTA TABLA QUE SEAN DEL ARMARIO armario
        Session session = null;
        Exception exception = null;
		Transaction tx = null;
        try{
            session= getSession();
            tx = session.beginTransaction();
            int reg=session.connection().createStatement().executeUpdate("delete from rutina_armario where armario='"+armario+"'");
            System.out.println("Cantidad de registros eliminados: "+reg);
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
        return true;    
    }
}
