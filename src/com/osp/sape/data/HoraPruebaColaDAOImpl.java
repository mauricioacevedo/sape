/*
 * Created on Jun 28, 2005
 */
package com.osp.sape.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.HorarioColaBean;


/**
 * 
 * @author Andres Aristizabal y Mauricio Acevedo
 */
public class HoraPruebaColaDAOImpl extends HibernateObject implements HoraPruebaColaDAO {
    
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

    
    public void eliminarHorarioCola(String cola) throws SapeDataException {
        Session session = null;
        Exception exception = null;
        Transaction tx = null;
            try {
                session = getSession();
                tx = session.beginTransaction();
                Statement st = session.connection().createStatement();

                String kuery ="UPDATE hrprueba_cola SET h13='0',h14='0',h15='0',h16='0',h17='0'"+
                ",h18='0',h19='0',h20='0',h21='0',h22='0',h23='0',h00='0',h01='0',h02='0',h03='0'"+
                ",h04='0',h05='0',h06='0',h07='0',h08='0',h09='0',h10='0',h11='0',h12='0' where cola='"+cola+"'";
                st.executeUpdate(kuery);
                
                String kuery2="UPDATE diaprueba_cola SET  d1='0',d2='0',d3='0', d4='0',d5='0' ,d6='0' ,d7='0', estado= 'D' where cola = '"+cola+"'";
                st.executeUpdate(kuery2);
                
                tx.commit();
                session.flush();
                session.close();
            } catch (Exception e) {
                exception = e;
            }finally{
                try {
                    if (session != null) session.close();
                } catch (HibernateException e) {
                    exception = e;
                }
            }
            
            if(exception != null) throw new SapeDataException(exception);
    }
    

    public String actualizarHorarios(String h00,String h01,String h02,String h03,String h04,String h05,String h06,
            String h07,String h08,String h09,String h10,String h11,String h12,String h13,
            String h14,String h15,String h16,String h17,String h18,String h19,String h20,
            String h21,String h22,String h23,String lunes,String martes,String miercoles,
            String jueves,String viernes, String sabado,String domingo,String cola, 
            String usuario, Date fecha) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("actualizarHorarios");
        
        Session session = null;
        Exception exception = null;
        Transaction tx = null;
        String retorno=null;
        
        try{
            
            String continuar = comprobarEstadoColas(cola);
            
            session = getSession();
            if(continuar == null){//actualizacion del horario y activacion de la cola
                
                tx = session.beginTransaction();
                Statement st=session.connection().createStatement();

                String kuery ="UPDATE hrprueba_cola SET h13='"+h13+"',h14='"+h14+"',h15='"+h15+"',h16='"+h16+"',h17='"+h17+
                "',h18='"+h18+"',h19='"+h19+"',h20='"+h20+"',h21='"+h21+"',h22='"+h22+"',h23='"+h23+"',h00='"+h00+"',h01='"+h01+"',h02='"+h02+"',h03='"+h03+
                "',h04='"+h04+"',h05='"+h05+"',h06='"+h06+"',h07='"+h07+"',h08='"+h08+"',h09='"+h09+"',h10='"+h10+"',h11='"+h11+"',h12='"+h12+"' where cola='"+cola+"'";
                if (debug) logs.debug("Sql: " + kuery);
                st.executeUpdate(kuery);
                
                String kuery2="UPDATE diaprueba_cola SET  d1='"+lunes+"' , d2='"+martes+"' ,  d3='"+miercoles+"' ,  d4='"+jueves+"' , d5='"+viernes+"' , d6='"+sabado+"' , d7='"+domingo+"', estado= 'A', usuario_cambio='" + usuario + "',  fecha_cambio='" + fecha + "' where cola = '"+cola+"'";
                if (debug) logs.debug("Sql: " + kuery2);
                st.executeUpdate(kuery2);
            
                tx.commit();
                session.flush();
            }else if(continuar.equalsIgnoreCase("ACTUALIZACION")){
                //YA HABIA UN HORARIO POR ESTA COLA Y SE VA A ACTUALIZAR.
                //System.out.println("ES UNA ACTUALIZACION");
                tx = session.beginTransaction();

                Statement st=session.connection().createStatement();
                
                String kuery ="UPDATE hrprueba_cola SET h13='"+h13+"', h14='"+h14+"', h15='"+h15+"', h16='"+h16+"', h17='"+h17+
                "', h18='"+h18+"', h19='"+h19+"', h20='"+h20+"', h21='"+h21+"', h22='"+h22+"', h23='"+h23+"', h00='"+h00+"', h01='"+h01+"', h02='"+h02+"', h03='"+h03+
                "', h04='"+h04+"', h05='"+h05+"', h06='"+h06+"', h07='"+h07+"', h08='"+h08+"', h09='"+h09+"', h10='"+h10+"', h11='"+h11+"', h12='"+h12+"' where cola='"+cola+"'";
                
                st.executeUpdate(kuery);

                String kuery2=	 "UPDATE diaprueba_cola SET  d1='"+lunes+"' , d2='"+martes+"' ,  d3='"+miercoles+"' ,  d4='"+jueves+"' , d5='"+viernes+"' , d6='"+sabado+"' , d7='"+domingo+"', estado= 'A', usuario_cambio='" + usuario + "',  fecha_cambio='" + fecha + "' where cola = '"+cola+"'";
                if (debug) logs.debug("Sql: " + kuery2);
                st.executeUpdate(kuery2);
                
                tx.commit();
                session.flush();
            }else{
                retorno = continuar;
            }
        }catch(HibernateException e1){
            logs.error(e1);
        	exception = e1;
        } catch (SQLException e) {
            logs.error(e);
        	exception = e;
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
        }
        if (exception != null) throw new SapeDataException(exception);
        
        return retorno;
    }
    
    public void actualizarRastroCambio (String cola, String usuario, Date fecha) throws SapeDataException {
    	if (debug) logs.debug("actualizarRastroCambio");
    	
    	Exception exception = null;
        Session session = null;
        Transaction tx = null;
        try {
        	session = getSession();
        	tx = session.beginTransaction();
        	Statement stm = session.connection().createStatement();

        	String sql =	 "UPDATE diaprueba_cola SET usuario_cambio='" + usuario + "', fecha_cambio='" + fecha + "' where cola = '"+cola+"'";
            if (debug) logs.debug("Sql: " + sql);

        	stm.execute(sql);
        	
        	tx.commit(); 
        	session.flush();
        } catch (SQLException e) {
        	logs.error(e);
        	try {
				tx.rollback();
			} catch (HibernateException e1) {
				logs.error(e1);
			}
        	exception = e;
        } catch (HibernateException e) {
        	logs.error(e);
        	exception = e;
        	try {
				tx.rollback();
			} catch (HibernateException e1) {
				logs.error(e1);
			}
        } finally {
			try {
				if (session != null) session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;
			}
        }
    	if (exception != null) throw new SapeDataException(exception);
    }

    
    /**
     * Este metodo devuelve una lista de objetos HorarioColaBean, estos objetos
     * contienen informacion especial para la  ejecucion del jsp inicioColas.jsp
     */
    public List getListaAllHorarios() throws SapeDataException{
        if (logs.isDebugEnabled()) logs.debug("getListaAllHorarios");
        List lt = new ArrayList(),lTotal = new ArrayList(), listaDias;
        Session session = null;
        Exception exception = null;
        ResultSet rs = null,rs2=null;
        try{
            session= getSession();
            rs = session.connection().createStatement().executeQuery("select * from hrprueba_cola order by cola ASC");
            rs2 = session.connection().createStatement().executeQuery("select * from diaprueba_cola order by cola ASC");
            
            if(rs.next() == false) {
                logs.error("No se puede obtener informacion de inicializacion desde la Base de datos.");
                session.close();
                return null;
            }
            rs2.next();
            do{
                HorarioColaBean hcb = new HorarioColaBean();
            	String col =rs.getString(1);
                
               for(int k=0;k<24;k++) {
                   
                   String h="";
                   if(k<10)
                       h="0";
                   else
                       h="";
                   //System.out.println("operacion= rs.getInt("+"h"+h+String.valueOf(k)+")");
                   int s = rs.getInt("h"+h+String.valueOf(k));
                   if(s == 0)
                       lt.add("name=c"+String.valueOf(k));
                   else
                       lt.add("name=c"+String.valueOf(k)+" checked=\"true\" ");
               }
               hcb.setListaHorarios(lt);
               lt = new ArrayList();
               listaDias=new ArrayList();
               String days[] ={"lunes","martes","miercoles","jueves","viernes","sabado","domingo"};
               String colspan[] ={"3","3","4","3","3","4","4",};
               for(int k2=0;k2<7;k2++){
                   
                   int dia = rs2.getInt("d"+String.valueOf(k2+1));
                   if (dia == 1)
                       listaDias.add("<td colspan=\""+colspan[k2]+"\" align=\"center\"><input type=\"checkbox\" name=\""+days[k2]+"\" checked=\"true\" >"+days[k2]+"</td>");
                   else
                       listaDias.add("<td colspan=\""+colspan[k2]+"\" align=\"center\"><input type=\"checkbox\" name=\""+days[k2]+"\">"+days[k2]+"</td>");
               }
               hcb.setCola(col);
               hcb.setUsuarioCambio(rs2.getString("usuario_cambio"));
               hcb.setFechaCambio(rs2.getTimestamp("fecha_cambio"));
               String estado = rs2.getString("estado");
               if(estado.indexOf("A") != -1){
                   hcb.setTitulo(col + " <font color = \"#ff5900\"> ACTIVA </font>");
               }else{
                   hcb.setTitulo(col);
               }
               
               hcb.setListaDias(listaDias);
               lTotal.add(hcb);
               rs2.next();   
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
        return lTotal;
    }
    
    public List getListaNombreColas() throws SapeDataException{
        
        if (logs.isDebugEnabled()) logs.debug("getListaNombreColas");
        List lt = new ArrayList();
        Session session = null;
        Exception exception = null;
        ResultSet rs = null;
        try{
            session= getSession();
            rs = session.connection().createStatement().executeQuery("select cola from hrprueba_cola order by cola ASC");
            if(rs.next() == false){
            	session.close();
                return null;
            }
            do{
                lt.add(rs.getString(1));
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
    
    /**
     * Este metodo se encarga de verificar el estado de las colas y sus horarios
     * La idea es que no se prueben mas de 3 colas,esto es, no hayan mas de 3 colas haciendo pruebas.
     * @param cola
     * @return Retorna null si la cola tiene permiso para acceder a el horario solicitado.\n
     * De otra manera retorna la descripcion del por que no se puede hacer la insercion.
     */
    private String comprobarEstadoColas(String cola)
    {
        
        Session session=null;
        ResultSet rs = null;

        Exception exception = null;
		try{
		    session=getSession();
		    rs = session.connection().createStatement().executeQuery("select count(estado) from diaprueba_cola where estado='A'");
		    
		    if(rs.next()==false){//NINGUNA COLA ESTA ACTIVA, SE PUEDE INSERTAR EL HORARIO
		        session.close();
		        return null;
		    }
		    
		    int cantidad = rs.getInt(1);
		    
		    if(cantidad <= 2){//NO HAY PROBLEMA, SE PUEDE HACER LA INSERCION DE ESTE HORARIO!!!!!!!
		        session.close();
		        return null;
		    }
		    
		    //si llego hasta aca se asume ke hay minimo 3 colas programadas!!!!!
		    rs = session.connection().createStatement().executeQuery("select cola from diaprueba_cola where estado='A'");
		    
		    while(rs.next()){
		        String colaBase = rs.getString(1);
		        
		        if(cola.indexOf(colaBase) != -1){//LA COLA SE VA A ACTUALIZAR
		            session.close();
		            return "ACTUALIZACION";
		        }
		    }
		    session.close();

		} catch (HibernateException e) {

		    exception = e;
		}catch(SQLException err){
			exception = err;
		}finally{
        	try{
        		if(session != null) session.close();
        	}catch(HibernateException e){
        		exception=e;
        	}
        }
		
		if(exception != null)return exception.toString();
	    //SI LLEGO HASTA ACA ES PORQUE NO SE PUEDE INSERTAR EL HORARIO SOLICITADO.		
	    return "No es posible especificar este horario para la cola "+cola+
	    " debido a que ya hay 3 colas en pruebas.";
    }
}
