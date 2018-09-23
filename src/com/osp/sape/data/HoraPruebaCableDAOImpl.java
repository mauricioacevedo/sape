/*
 * Created on Jun 16, 2005
 */
package com.osp.sape.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.utils.ConfiguracionClienteSape;

//TODO CAMBIAR todas las apariciones de la tabla hrprueba_cables_tmp
//A LA TABLA VERDADERA: hrprueba_cables

/**
 * 
 * @author Andres Aristizabal y Mauricio Acevedo
 */
public class HoraPruebaCableDAOImpl extends HibernateObject implements
        HoraPruebaCableDAO {

    private org.apache.log4j.Logger logs;
    
    public HoraPruebaCableDAOImpl(){
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

    public List getHorariosCable(String cable)throws SapeDataException{
        if (logs.isDebugEnabled()) logs.debug("getHorariosCables");
        List lt = new ArrayList();
        Session session = null;
        Exception exception = null;
        ResultSet rs = null;
        try{
            session= getSession();
            rs = session.connection().createStatement().executeQuery("select * from hrprueba_cables where cable= '"+cable+"'");
            int i = 1;
            if(rs.next() == false){
                for(int j=0;j<12;j++){
                    lt.add("0");
                }
                session.close();
                return lt;
            }
            while(i < 13){
                //la siguiente linea utiliza rs.getInt para kitar la basura ke le 
                //pone la base de datos a la informacion
                String hora = String.valueOf(rs.getInt(i+1));
                lt.add(hora);
                i++;
            }
            session.flush();
            session.close();
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
        return lt;
    }
    
    public String actualizarHorarioPruebaCables(String cable,String h19,String h20,String h21,String h22,String h23,String h00,String h01,String h02,String h03,String h04,String h05,String h06)
	throws SapeDataException {
        //XXX 24-06-05:
        //las tablas van a permitir solo 4 registros!!!
		if (logs.isDebugEnabled()) logs.debug("actualizarHorarioPruebaCables"); 
		Session session = null;
		Exception exception = null;
		Statement st = null;
		ResultSet rs = null;
		Transaction tx = null;
		int rta=-1;
		try{
			session= getSession();
			tx = session.beginTransaction();
			st=session.connection().createStatement();
			rs=st.executeQuery("select * from hrprueba_cables where cable ='"+cable.toUpperCase()+"'");
			
			if(rs.next()){
			    //SI ES TRUE YA HAY UN REGISTRO EN ESTA TABLA CON ESTE CABLE Y
			    //SE DEBE REEMPLAZAR CON EL KE SE TIENE POR PARAMETROS!!!
			    st.executeUpdate("delete from hrprueba_cables where cable ='"+cable.toUpperCase()+"'");
			}else{//MIRAMOS SI YA ESTAN LOS 4 REGISTROS QUE PERMITE LA TABLA!!!!

			    rs = st.executeQuery("select count(*) from hrprueba_cables");
			    if(rs.next() != false){
			        int kant = rs.getInt(1);
			        /**
			         * XXX: Se obtiene un dato desde el archivo de configuracion.
			         * Verificar si la forma de traer esta vable es correcto.
			         */
			        
			        int limiteRutinas = ConfiguracionClienteSape.getInstance().getNumeroRutinas();
			        
			        if(kant >= limiteRutinas){
			        	session.close();
			            return "No se puede actualizar el horario. Causa:<br> Limite de cables ("+limiteRutinas+") maximo.\nSe sugiere que retire un Cable de los "+limiteRutinas+" actuales.";
			        }
			    }
			}
			
			session.connection().createStatement().executeUpdate("INSERT INTO hrprueba_cables VALUES('"+cable+"','"+h19+"','"+h20+"','"+h21+"','"+h22+"','"+h23+"','"+h00+"','"+h01+"','"+h02+"','"+h03+"','"+h04+"','"+h05+"','"+h06+"')");
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
		return null;
	}

    public List getAllCables() throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getAllCables");
        List lt = new ArrayList();
        Session session = null;
        Exception exception = null;
        ResultSet rs = null;
        String cable=null;
        try{
            session= getSession();
            rs = session.connection().createStatement().executeQuery("select cable from hrprueba_cables");
            if(rs.next() == false){
            	session.close();
                return null;
            }
            do{
                cable = rs.getString(1);
                lt.add(cable);
            }while(rs.next());
            
            session.flush();
            session.close();
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
        return lt;
    }

    public boolean eliminarCable(String cable)throws SapeDataException{
        Session session = null;
        Exception exception = null;
		Transaction tx = null;
        try{
            session= getSession();
            tx = session.beginTransaction();
            session.connection().createStatement().executeUpdate("delete from hrprueba_cables where cable='"+cable+"'");
            tx.commit();
            session.flush();
            session.close();
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

    /* (non-Javadoc)
     * @see com.osp.sape.data.HoraPruebaCableDAO#existsCable(java.lang.String)
     */
    public boolean existsCable(String cable) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("existsCable");
        Session session = null;
        Exception exception = null;
        ResultSet rs = null;
        boolean ret = false;
        try{
            session= getSession();
            rs = session.connection().createStatement().executeQuery("select cable from hrprueba_cables where cable='"+cable+"'");
            ret= rs.next();
            session.flush();
            session.close();
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
        return ret;
    }
    
}
