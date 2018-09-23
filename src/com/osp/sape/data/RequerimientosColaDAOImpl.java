/*
 * Created on Jun 2, 2005
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
import com.osp.sape.reportes.RequerimientosCola;

/**
 * 
 * @author Andres Aristizabal
 */
public class RequerimientosColaDAOImpl extends HibernateObject implements RequerimientosColaDAO {

    
    private org.apache.log4j.Logger logs;
    
    public RequerimientosColaDAOImpl() {
        logs = org.apache.log4j.Logger.getLogger(getClass());
    }
    
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

    
    public boolean actualizarEstado(long oid, String estadoNuevo) throws SapeDataException {
    	if(logs.isDebugEnabled()) logs.debug("actualizarEstado: " + oid + ", " + estadoNuevo);
    	boolean retorno = false;
    	Exception exception = null;
    	Session session = null;
    	try {
    		session = getSession();
    		Statement stm = session.connection().createStatement();
    		String sql = "update requerimientoscola set estado = '" + estadoNuevo + "' where oid = " + oid;
    		if (logs.isDebugEnabled()) logs.debug("Sql: " + sql);
    		if (stm.executeUpdate(sql) == 1) retorno = true;
    		stm.close();
    		session.close();
    	} catch (HibernateException e) {
    		logs.error(e);
    		exception = e;
    	} catch (SQLException e) {
    		logs.error(e);
    		exception = e;
    	} finally {
    		if (session != null)
				try {
					session.close();
				} catch (HibernateException e) {
		    		logs.error(e);
		    		exception = e;
				}
    	}
    	if (exception != null) {
    		throw new SapeDataException(exception);
    	}
    	return retorno;
    }
    
//    protected Configuration getConfiguration() throws HibernateException {
//        if (configuration == null) {
//            configuration = new Configuration();
//            try {
//                configuration.configure();
//                logs = org.apache.log4j.Logger.getLogger(getClass());
//            } catch (HibernateException e) {
//                logs.error(e);
//            }
//        }
//        return configuration;
//    }


    
    public List getRegistrosTSTLI(String cola,String filtro, String valorFiltro,String fInicio,String fFin, String regPorPagina, String offset,String orderBy) throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getRegistrosTSTLI(COLAS): cola=["+cola+"], filtro=[" + filtro + "], valorFiltro=[" + valorFiltro + "], fInicio=[" + fInicio + "], fFin=[" + fFin + "], regPorPag=[" + regPorPagina + "], offset=[" + offset + "], orderBy=[" + orderBy+"]");
        
    	String where = " WHERE cola_id = '"+cola.toUpperCase()+"' and fecha BETWEEN '"+fInicio+" 00:00:00' AND '"+fFin+" 23:59:59'";
      	where += (filtro.equals("ninguno"))?"":" AND "+filtro+"='"+valorFiltro+"' ";
    	
      	
      	
      	Exception exception = null;
      	List l = new ArrayList(),total = new ArrayList();
      	Session session = null;
        long counter = 0;
    	try{
            session= getSession();
            Statement stm = session.connection().createStatement();
            
            ResultSet rs = stm.executeQuery("select count(*) from requerimientoscola "+where);
            
            rs.next();
            counter = rs.getLong(1);
            
            total.add(0,new Long(counter));
            
            String sql = "select oid,* from requerimientoscola "+where+" order by "+orderBy+(regPorPagina == null || regPorPagina.equals("")||regPorPagina.equals("0")?"":" limit "+regPorPagina+" offset "+offset);
            if (logs.isDebugEnabled()) logs.debug("Sql: " + sql);
            rs = stm.executeQuery(sql);
            

            while(rs.next()) {
            	RequerimientosCola r = new RequerimientosCola();
            	
                r.setOid(rs.getLong("oid"));	
            	r.setArea_trabajo_id(rs.getString("area_trabajo_id"));
            	r.setArmario_id(rs.getString("armario_id"));
            	r.setCable(rs.getString("cable"));
            	r.setCaja_id(rs.getString("caja_id"));
            	r.setCodObservacion(rs.getString("codobserva"));
            	r.setColaEnruta(rs.getString("cola_enruta"));            	
            	r.setCola_id(rs.getString("cola_id"));
            	r.setEstado(rs.getString("estado"));
            	r.setFecha(rs.getTimestamp("fecha"));
            	r.setIdentificador(rs.getString("identificador"));
            	r.setPar_primario_id(rs.getString("par_primario_id"));
            	r.setPar_secundario_id(rs.getString("par_secundario_id"));
            	r.setSecuencia(rs.getString("secuencia"));
            	r.setStrip_id(rs.getString("strip_id"));
            	r.setSubzona_id(rs.getString("subzona_id"));
            	r.setTicketPrueba(rs.getLong("ticket_prueba"));
            	r.setTipo_nodo(rs.getString("tipo_nodo"));
            	r.setProducto(rs.getString("producto_id"));
            	r.setTipoCliente(rs.getString("tipo_cliente"));
            	l.add(r);
            	r = null;
            }
            total.add(1,l);
            
            
            session.flush();
    	}catch(HibernateException e1){
    		logs.error(e1);
    		exception = e1;
        } catch (SQLException e1) {
        	logs.error(e1);
        	exception = e1;
		} finally {
			try {
				if(session != null)session.close();
			} catch(HibernateException e1){
				logs.error(e1);
				exception = e1;
			}
		}
        if(exception != null)throw new SapeDataException(exception);       
        return total;
    }
    
    
    public String graficasInicialesTSTLI(String cola,int tipo,String filtro, String valorFiltro,String fIni,String fFin, String regPorPagina, String offset,String orderBy) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("graficasInicialesTSTLI(COLAS): "+cola+", " + tipo + ", " + filtro + ", " + valorFiltro + ", " + fIni + ", " + fFin + ", " + regPorPagina + ", " + offset + ", " + orderBy);
    	
      	Exception exception = null;
      	Session session = null;
        long counter = 0;
        String data = "";
        
    	try{
            session= getSession();
            
            
        	Transaction tx = null;
        	Statement stm = session.connection().createStatement();
    		try {
				tx = session.beginTransaction();
				stm.execute("drop table requerimientoscolaTemp");
				tx.commit();
			} catch (SQLException e) {
//				puede continuar, solo la tabla temp no estaba creada!!!!
//				este codigo es para deshacer la operacion anterior y que se 
//				desbloquee el hibernate!!!!
				tx.rollback();
			}
            
                        
            String query = null,queryCounter=null;
            String where = "where cola_id = '"+cola.toUpperCase()+"' and fecha between '"+fIni+" 00:00:00' and '"+fFin+" 23:59:59'";
          	where += (filtro == null || filtro.equals("ninguno"))?"":" AND "+filtro+"='"+valorFiltro+"' ";

            String sqlTemp = "select * into temp requerimientoscolaTemp from requerimientoscola "+where+" order by "+ orderBy;
          	
            if(tipo == 1){
            	data = "Grafica por Cola-Codigo, ";
            	
            	//query = "select count(*),cola,codobserva from requerimientoscola "+ where + " group by cola,codobserva order by cola, codobserva";
            	query = "select count(*),cola_enruta,codobserva from requerimientoscolaTemp group by cola_enruta,codobserva order by cola_enruta,codobserva";
            	queryCounter = "select count(*) from requerimientoscolaTemp";
            } else if (tipo == 2){
            	data = "Grafica por Cola, ";
            	query = "select count(*),cola_enruta from requerimientoscolaTemp group by cola_enruta order by cola_enruta";
            	queryCounter = "select count(*) from requerimientoscolaTemp" ;
            } else if (tipo == 3){
            	
            	if (offset == null || offset.equals("")) offset = "0";
            	data = "Grafica por Telefono, ";
            	query = "select count(*),identificador from requerimientoscolaTemp group by identificador having count(*) >= "+offset;
            	queryCounter = "select count(*) from requerimientoscolaTemp" ;
            }
            if (logs.isDebugEnabled()) logs.debug("[SQL: "+sqlTemp+"]");
            stm.execute(sqlTemp);
            
            
            if(logs.isDebugEnabled())logs.debug("[SQL: "+query+"]");
            
            ResultSet rs = stm.executeQuery(queryCounter);
            
            if(rs.next()) counter = rs.getLong(1);

            rs = null;
             
            if(tipo==1) data += counter+" registros.*Codigo*Valor*";
            else if (tipo==2) data += counter+" registros.*Cola*Valor*";
            else if (tipo==3) data += counter+" registros.*Telefono*Valor*";
            
                        
            rs = stm.executeQuery(query);
            int i = 0;
            
            if(tipo == 1){
            	while(rs.next()){
            		data += rs.getString(2)+"-"+rs.getString(3)+"*"+rs.getString(1)+"*";
            		i++;
            	}
            } else {
                while(rs.next()){
                	data += rs.getString(2)+"*"+rs.getString(1)+"*";
                	i++;
                }
            }
            session.flush();
    	} catch(HibernateException e1) {
        	logs.error(e1);
    		exception = e1;
        } catch (SQLException e1) {
        	logs.error(e1);
        	exception = e1;
		} finally {
			try{
				if(session != null) session.close();
			}catch(HibernateException e1){
				logs.error(e1);
				exception = e1;
			}
		}
        if(exception != null) throw new SapeDataException(exception);
		return data;
    }

    
    public List getIndicadoresTSTLI(String tipo, int rango) throws SapeDataException, HibernateException {
    	if (logs.isDebugEnabled()) logs.debug("getGraficaTSTLI: " + tipo + ", " + rango);
    	
        String data = "";
        Exception exception = null;
        List total = new ArrayList(),links = new ArrayList();
        long count = 0;
//        int contac = 0;
        
                
        Session session = null;
        try {
            session= getSession();
            Statement stm = session.connection().createStatement();
            
            Transaction tx = null;
            try{
            	tx = session.beginTransaction();
            	stm.execute("drop table requerimientoscola_temp;");
            	tx.commit();
            } catch(SQLException e){
            	tx.rollback();
            }
            
            if(tipo.equals("armario_id") && rango > 0 ) {
                stm.execute("select count(armario_id) as contador, armario_id into temp requerimientoscola_temp from requerimientoscola r where date(fecha) = CURRENT_DATE and cola_id = 'TSTLI' group by r.armario_id order by r.armario_id;");
                stm.execute("SELECT * from requerimientoscola_temp where contador >= "+rango+";");
                ResultSet rs = stm.getResultSet();
                
                if (rs == null) { session.close(); return null;}
                if (!rs.next()) { session.close(); return null;}
                

                do {
                	String counter = rs.getString(1);
                	String dat = rs.getString(2);
                    data += dat+"="+counter+"*"+counter+"*";
                    count += Integer.parseInt(counter);
                    
                    links.add(dat);
                    

                } while(rs.next());
                stm.execute("drop table requerimientoscola_temp;");
                session.close();
                
                                

                total.add(0,data);
                total.add(1,new Long(count));
                total.add(2,links);
                return total;
            }
            
            if(tipo.equals("cable") && rango > 0 ){
                
                stm.execute("select count(cable) as contador, cable into temp requerimientoscola_temp from requerimientoscola r where date(fecha) = CURRENT_DATE and cola_id = 'TSTLI' group by r.cable order by r.cable;");
                stm.execute("SELECT * from requerimientoscola_temp where contador >= "+rango+";");
                ResultSet rs = stm.getResultSet();
                
                if (rs == null) { session.close(); return null;}
                if (!rs.next()) { session.close(); return null;}

                do{
                	String counter = rs.getString(1);
                	String dat = rs.getString(2);
                	
                	data += dat+"="+counter+"*"+counter+"*";
                    count += Integer.parseInt(counter);
                  
                    links.add(dat);
                    
                }while(rs.next());
                
                stm.execute("drop table requerimientoscola_temp;");
                session.close();
                total.add(0,data);
                total.add(1,new Long(count));
                total.add(2,links);
                return total;
            }
            
            
            stm.execute("select count("+tipo+") as contador,"+tipo+" from requerimientoscola where date(fecha) = CURRENT_DATE and cola_id = 'TSTLI' group by "+tipo+" order by " + tipo);
            
            ResultSet rs = stm.getResultSet();
            
            if (rs == null) { session.close(); return null;}
            if (!rs.next()) { session.close(); return null;}
//            int j = 0;
            do{
            	String counter = rs.getString(1);
            	String dat = rs.getString(2);
            	
                data += dat+"="+counter+"*"+counter+"*";
                count += Integer.parseInt(counter);
                links.add(dat);
                
                //contac=(contac >= rs.getInt(1)?contac:rs.getInt(1));
                //j++;
            }while(rs.next());
            session.close();
            
            /*
            if (j > 100 ){
            	contac = (int)contac/2;
            	if(tipo.equals("armario_id") || tipo.equals("cable")){
            		List lista = getIndicadoresTSTLI(tipo,contac);
            		lista.add(2,String.valueOf(contac));
            		return lista;
            	}
            } else
            	contac = 0;*/
            
        } catch (SQLException e) {
            exception = e;
        }finally{
        	try{
        		if(session != null)session.close();
        	}catch(HibernateException e){
        		exception = e;
        	}
        }
//        if (logs.isDebugEnabled()) logs.debug("RESULTADO: " + data);
        if(exception != null) throw new SapeDataException(exception);
        total.add(0,data);
        total.add(1,new Long(count));
        total.add(2,links);
        return total;
            
    }
    
}
