/*
 * Created on Apr 14, 2005
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
import com.osp.sape.indicadores.ViewCodvCentral;
import com.osp.sape.indicadores.ViewEstadosCentral;
import com.osp.sape.indicadores.ViewIndicadoresTecnologia;
import com.osp.sape.indicadores.ViewPrimeraPruebaTelefonos;
import com.osp.sape.maestros.EventoSape;
import com.osp.sape.maestros.UserSipe;

/**
 * @author Andres Aristizabal
 */
public class EventoSapeDAOImpl extends HibernateObject implements EventoSapeDAO {

    private org.apache.log4j.Logger logs = org.apache.log4j.Logger.getLogger(getClass()); 
    private boolean debug = logs.isDebugEnabled();
	public EventoSapeDAOImpl() {
		super();
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


    public EventoSape getEventoSape(long id) throws SapeDataException {
    	if (debug) logs.debug("getEventoSape: " + id);
    	
        EventoSape retorno = null;
        try {
            retorno = (EventoSape) cargarObjeto(EventoSape.class, new Long(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }
 
	
    public List getDetallesCentral(String central, String fechaIni, String fechaFin) throws SapeDataException {
    	
    	if (debug) logs.debug("getDetallesCentral: " + central + ", " + fechaIni + ", " + fechaFin);
    	
    	Exception exception = null;
    	fechaIni = fechaIni + " 00:00:00";
    	fechaFin = fechaFin + " 23:59:59";
    	List retorno = new java.util.ArrayList(2);
    	List detalles = new java.util.ArrayList(10);
    	int totalEventos = 0;
    	Session session = null;
    	Transaction tx = null;
		
    	try {
			session = getSession();
			tx = session.beginTransaction();
			
			Statement stm = session.connection().createStatement();
			long tiempoInicial = System.currentTimeMillis();
			String sql = "SELECT ideventossape, date(fecha_inicial) as fechainicial, central, site into temp eventossapetemp from eventossape e where fecha_inicial between '" + fechaIni + "' and '" + fechaFin + "' and e.central = '" + central + "'";
			
			if (debug) logs.debug("Sql: " + sql);
			stm.execute(sql);
			
			sql = "SELECT idprueba_basica, codv into temp prueba_basicatemp from prueba_basica where idprueba_basica between (select min(ideventossape) from eventossapetemp) and (select max(ideventossape) from eventossapetemp)";
			if (logs.isDebugEnabled()) logs.debug("Sql: " + sql);
			stm.execute(sql);
			
			sql = "SELECT e.fechainicial, e.central, e.site, p.codv, count(e.ideventossape) as cantidad from eventossapetemp e left outer join prueba_basicatemp as p on (e.ideventossape = p.idprueba_basica) group by e.fechainicial, e.central, e.site, p.codv order by e.fechainicial, e.central, e.site, p.codv";
			if (debug) logs.debug("Sql: " + sql);
			
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int cant = rs.getInt("cantidad");
				totalEventos += cant;
				ViewCodvCentral v = new ViewCodvCentral(rs.getString("central"), 
						rs.getString("site"), rs.getString("codv"),
						rs.getDate("fechaInicial"),	cant);
				detalles.add(v);
			}

    		sql = "drop TABLE eventossapetemp";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
			
			sql = "drop TABLE prueba_basicatemp";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
			
			if (debug) logs.info("Demora: " + (System.currentTimeMillis() - tiempoInicial));
			stm.close();
			session.flush();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} catch (SQLException e) {
			logs.error(e);
			exception = e;
		} finally {
            try {
            	if (debug) logs.debug("Va a hacer el rollback");
            	if (tx != null) tx.rollback();
            	if (debug) logs.debug("Va a cerrar la session");
            	if (session != null) session.close();
            } catch (HibernateException e) {
            	logs.warn("ERROR cerrando la sesion");
            	logs.error(e);
            	exception =e;
            }
		}
		if (exception != null) {
			throw new SapeDataException(exception);
		}
		retorno.add(detalles);
		retorno.add(new Integer(totalEventos));
    	return retorno;
    }
    
    
    public List getEfectividadPrueba(String fIni, String fFin) throws SapeDataException {
    	if (debug) logs.debug("getEfectividadPrueba: " +  fIni + ", " + fFin);
    	
    	List total = new ArrayList();
    	Exception exception = null;
    	Session session = null;
    	Statement st = null;
    	ResultSet rs = null;
    	
    	try {
    		session = getSession();
			st = session.connection().createStatement();
			
			String sql = "select site,sum(total),sum(exito),sum(fallo),sum(advertencia),sum(inesperado),to_char(avg(promedio), 'SS.MS') "+
				"from efectividad where fecha between '"+fIni+"' and '"+fFin+"' group by site order by site";
			
			if(debug) logs.debug("[SQL: "+sql+"]");
			
			List listaCentro = new ArrayList();
			
			int i = 0;
			rs = st.executeQuery(sql);
			
			
			while(rs.next()) {
				String tmpTot = rs.getString(2);
				String tmpE = rs.getString(3);
				String tmpF = rs.getString(4);
				String tmpA = rs.getString(5); 
				String tmpN = rs.getString(6);
				String tmpSite = rs.getString(1);
				String tmpProm = rs.getString(7);
				if (tmpProm == null) {
					tmpProm = "00.000";
				}

				String datos[] ={tmpTot,tmpE,tmpF,tmpA,tmpN,tmpSite,tmpProm};
				listaCentro.add(i,datos);
				datos = null;
				i++;
			}
			
			sql = "select sum(total) as total,sum(exito) as exito,sum(fallo) as fallo,"+
				  " sum(advertencia) as advertencia,sum(inesperado) as inesperados,"+
				  " to_char(avg(promedio), 'SS.MS') as promedio from efectividad where fecha between"+
				  " '"+fIni+"' and '"+fFin+"'";
			
			if(debug) logs.debug("[SQL: "+sql+"]");
			
			
			int stateA=0,stateE=0,stateF=0,stateN=0, cantTotal=0;
			String promTotal="0";
			
			rs = st.executeQuery(sql);
			rs.next();
			
			cantTotal = rs.getInt(1);
			stateE = rs.getInt(2);
			stateF = rs.getInt(3);
			stateA = rs.getInt(4);
			stateN = rs.getInt(5);
			
			promTotal = rs.getString(6);
			
			total.add(0,String.valueOf(cantTotal));
            total.add(1,String.valueOf(stateA));
            total.add(2,String.valueOf(stateE));
            total.add(3,String.valueOf(stateF));
            total.add(4,String.valueOf(stateN));
            
            //cambiar este objeto, no se hace nada con el ni en el servlet ni en el
            //jsp
            
            total.add(5,"no hace nada. CORREGIR");
            total.add(6,promTotal);
            total.add(7,listaCentro);
			
			
		} catch (HibernateException e) {
			exception = e;
		} catch (SQLException e) {
			exception = e;
		}
		
    	if(exception != null) throw new SapeDataException(exception);
    	
    	return total;
		
    }
    
    ///////////////////////////////////////////////
    //TODO:borrar el metodo:
    
    public List getEfectividadPrueba2(String fIni, String fFin) throws SapeDataException {
    	if (debug) logs.debug("getEfectividadPrueba2: " +  fIni + ", " + fFin);
    	
    	List total = new ArrayList();
    	Exception exception = null;
    	Session session = null;
    	Statement st = null;
    	ResultSet rs = null;
    	
    	try {
    		session = getSession();
			st = session.connection().createStatement();

			String sql = "select site,sum(total),sum(exito),sum(fallo),sum(advertencia),sum(inesperado),to_char(avg(promedio), 'SS.MS') "+
				"from efectividad2 where fecha between '"+fIni+"' and '"+fFin+"' group by site order by site";
			
			if(debug) logs.debug("[SQL: "+sql+"]");
			
			List listaCentro = new ArrayList();
			
			int i = 0;
			rs = st.executeQuery(sql);
			
			
			while(rs.next()) {
				String tmpTot = rs.getString(2);
				String tmpE = rs.getString(3);
				String tmpF = rs.getString(4);
				String tmpA = rs.getString(5); 
				String tmpN = rs.getString(6);
				String tmpSite = rs.getString(1);
				String tmpProm = rs.getString(7);
				if (tmpProm == null) {
					tmpProm = "00.000";
				} 
						
				String datos[] ={tmpTot,tmpE,tmpF,tmpA,tmpN,tmpSite,tmpProm};
				listaCentro.add(i,datos);
				datos = null;
				i++;
			}
			
			sql = "select sum(total) as total,sum(exito) as exito,sum(fallo) as fallo,"+
				  " sum(advertencia) as advertencia,sum(inesperado) as inesperados,"+
				  " to_char(avg(promedio), 'SS.MS') as promedio from efectividad2 where fecha between"+
				  " '"+fIni+"' and '"+fFin+"'";
			
			if(debug) logs.debug("[SQL: "+sql+"]");
			
			
			int stateA=0,stateE=0,stateF=0,stateN=0, cantTotal=0;
			String promTotal="0";
			
			rs = st.executeQuery(sql);
			rs.next();
			
			cantTotal = rs.getInt(1);
			stateE = rs.getInt(2);
			stateF = rs.getInt(3);
			stateA = rs.getInt(4);
			stateN = rs.getInt(5);
			
			promTotal = rs.getString(6);
			
			total.add(0,String.valueOf(cantTotal));
            total.add(1,String.valueOf(stateA));
            total.add(2,String.valueOf(stateE));
            total.add(3,String.valueOf(stateF));
            total.add(4,String.valueOf(stateN));
            
            //cambiar este objeto, no se hace nada con el ni en el servlet ni en el
            //jsp
            
            total.add(5,"no hace nada. CORREGIR");
            total.add(6,promTotal);
            total.add(7,listaCentro);
			
			
		} catch (HibernateException e) {
			exception = e;
		} catch (SQLException e) {
			exception = e;
		}
		
    	if(exception != null) throw new SapeDataException(exception);
    	
    	return total;
    }
    
    
    
    /////////////////////////////////////////////////
    
    public List getEventosUsuario(UserSipe usuario, String fIni, String fFin) throws SapeDataException {
    	if (debug) logs.debug("getEventosUsuario: " + usuario + ", " + fIni + ", " + fFin);
    	
    	Exception exception = null;
    	List retorno = null;
    	Session session = null;
    	
    	
    	try {
			session = getSession();
			
			String hql = "from EventoSape e where e.cliente= '" + usuario.getNick() + 
					"' and e.fechaInicial between '" + fIni + " 00:00:00' and '" + fFin + " 23:59:59"
					+ "' order by e.fechaInicial DESC";
			
			if (debug) logs.debug("HQL: " + hql);
			
			retorno = session.find(hql);
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} finally {
			try {
				if (session != null) session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;				
			}
		}
		if (exception != null) throw new SapeDataException(exception);
		return retorno;
    }
    
    public String getGraficaPrimeraPrueba(String fechaInicial, String fechaFinal)throws SapeDataException {
    	if (debug) logs.debug("getGraficaPrimeraPrueba: " + fechaInicial + ", " + fechaFinal);
    	
    	fechaInicial = fechaInicial + " 00:00:00";
    	fechaFinal = fechaFinal + " 23:59:59";
		String datos = "";
    	
    	Exception exception = null;
    	Session session = null;
    	Transaction tx = null;
    	try {
    		session = getSession();
    		tx = session.beginTransaction();
    		Statement stm = session.connection().createStatement();
    		long tiempoInicial = System.currentTimeMillis();
    		String sql = "SELECT min(ideventossape) as primero, count(ideventossape) as cantidad, telefono into temp eventossapegrouptemp from eventossape e where fecha_inicial between '" + fechaInicial + "' and '" + fechaFinal + "' group by e.telefono";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		sql = "select idprueba_basica, codv into temp prueba_basicatemp from prueba_basica where idprueba_basica between (select min(primero) from eventossapegrouptemp) and (select max(primero) from eventossapegrouptemp)";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		sql = "select t.primero, t.telefono, t.cantidad, e.tipoprueba, p.codv, e.site, e.cliente, e.estado, e.fecha_inicial, to_char(e.fecha_final - e.fecha_inicial, 'SS.MS') as duracion into temp primerostemp from eventossapegrouptemp t inner join eventossape as e on (t.primero = e.ideventossape) left join prueba_basicatemp as p on (t.primero = p.idprueba_basica) where e.fecha_inicial between '" + fechaInicial + "' and '" + fechaFinal + "'";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		
    		sql = "select count(*),codv from primerostemp group by codv";
    		if (debug) logs.debug("sql: " + sql);
    		ResultSet rs = stm.executeQuery(sql);
    		
    		while (rs.next()) {

    			datos += rs.getString(2)+"*"+rs.getString(1)+"*";
    		}
    			//total de registros
    		Integer totalRegistros = new Integer(0);
    		sql = "select count(*) from primerostemp ";
    		if (debug) logs.debug("sql: " + sql);
    		rs = stm.executeQuery(sql);
    		if (rs.next()) totalRegistros = new Integer(rs.getInt(1));

    		datos = "Grafica por Codigos Ver, "+totalRegistros.toString()+
    		" registros.*Codigo*Valor*"+datos;
    		
    		sql = "drop TABLE eventossapegrouptemp";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		sql = "drop TABLE prueba_basicatemp";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		sql = "drop TABLE primerostemp";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);

    		if (debug) logs.info("Demora: " + (System.currentTimeMillis() - tiempoInicial));
			stm.close();
			session.flush();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} catch (SQLException e) {
			logs.error(e);
			exception = e;
		} finally {
			try {
				if (debug) logs.debug("Va a hacer el rollback");
        		if (tx != null) tx.rollback();
        		if (debug) logs.debug("Va a cerrar la session");	
        		if(session != null) session.close();
        	} catch (HibernateException e) {
				logs.error(e);
				exception = e;				
			}
		} 
		if (exception != null) throw new SapeDataException(exception);
		
		//if(logs.isDebugEnabled()) logs.debug("[DATOS: \n"+datos+" ]");
		return datos;    	
    	
    }
    
    
	public List getPrimeraPruebaTelefono (String fechaInicial, String fechaFinal, String filtro, String valorFiltro, int regXPagina, int offset, String order) throws SapeDataException {
    	if (debug) logs.debug("getPrimeraPruebaTelefono: " + fechaInicial + ", " + fechaFinal + ", " + regXPagina + ", " + offset + ", " + order);
    	
    	fechaInicial = fechaInicial + " 00:00:00";
    	fechaFinal = fechaFinal + " 23:59:59";
    	String where = "";
    	if (filtro != null && valorFiltro != null && !filtro.equals("") && !valorFiltro.equals("")) 
    		where = "WHERE " + filtro + "= '" + valorFiltro + "'";
    	if (order == null) order = "";
    	else order = "order by " + order;  
    	
    	Exception exception = null;
    	List retorno = new ArrayList(2);
    	Session session = null;
    	Transaction tx = null;
    	Statement stm = null;
    	        	    	
    	try {
    		session = getSession();
    		stm = session.connection().createStatement();
    		tx = session.beginTransaction();
    		long tiempoInicial = System.currentTimeMillis();
    		String sql = "SELECT min(ideventossape) as primero, count(ideventossape) as cantidad, telefono into temp eventossapegrouptemp from eventossape e where fecha_inicial between '" + fechaInicial + "' and '" + fechaFinal + "' group by e.telefono";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		sql = "select idprueba_basica, codv into temp prueba_basicatemp from prueba_basica where idprueba_basica between (select min(primero) from eventossapegrouptemp) and (select max(primero) from eventossapegrouptemp)";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		sql = "select t.primero, t.telefono, t.cantidad, e.tipoprueba, p.codv, e.site, e.cliente, e.estado, e.fecha_inicial, to_char(e.fecha_final - e.fecha_inicial, 'SS.MS') as duracion into temp primerostemp from eventossapegrouptemp t inner join eventossape as e on (t.primero = e.ideventossape) left join prueba_basicatemp as p on (t.primero = p.idprueba_basica) where e.fecha_inicial between '" + fechaInicial + "' and '" + fechaFinal + "'";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		
    		String paginacion = null;
    		if(offset <= -1){// indica que no se quiere paginar el reporte
    			paginacion = "";
    		}else{
    			paginacion = " limit " + regXPagina + " offset " + offset;
    		}
    		
    		
    		sql = "select * from primerostemp " + where + " " + order + paginacion;
    		if (debug) logs.debug("sql: " + sql);
    		ResultSet rs = stm.executeQuery(sql);
    		List listaVista = new ArrayList(regXPagina);
    		while (rs.next()) {
    			ViewPrimeraPruebaTelefonos v = new ViewPrimeraPruebaTelefonos(rs.getInt("primero"), 
    					rs.getString("telefono"), rs.getInt("cantidad"), rs.getString("tipoprueba"),
    					rs.getString("codv"), rs.getString("site"), rs.getString("cliente"), 
    					rs.getString("estado"), rs.getTimestamp("fecha_inicial"), rs.getString("duracion"));
    			listaVista.add(v);
    		}
    			//total de registros
    		Integer totalRegistros = new Integer(0);
    		sql = "select count(*) from primerostemp " + where;
    		if (debug) logs.debug("sql: " + sql);
    		rs = stm.executeQuery(sql);
    		if (rs.next()) totalRegistros = new Integer(rs.getInt(1));
    		retorno.add(listaVista);
    		retorno.add(totalRegistros);
    		sql = "drop TABLE eventossapegrouptemp";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		sql = "drop TABLE prueba_basicatemp";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);
    		sql = "drop TABLE primerostemp";
    		if (debug) logs.debug("sql: " + sql);
    		stm.execute(sql);

    		if (debug) logs.info("Demora: " + (System.currentTimeMillis() - tiempoInicial));
			stm.close();
			session.flush();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} catch (SQLException e) {
			logs.error(e);
			exception = e;
		} finally {
			try {
				if (debug) logs.debug("Va a hacer el rollback");
        		if (tx != null) tx.rollback();
        		if (debug) logs.debug("Va a cerrar la session");	
        		if(session != null) session.close();
			} catch (HibernateException e) {
				logs.error("Haciendo el rollback y cerrando la session: " + e);
				exception = e;				
			}
		} 
		if (exception != null) throw new SapeDataException(exception);
		return retorno;
    }
	
    
	public List getPruebasPorUsuarios(String fIni, String fFin, String usuario,String orderBy) throws SapeDataException {
		if (debug) logs.debug("getPruebasPorUsuarios");
		
        Session session = null;
        List total = new ArrayList();
        Exception exception = null;
        Statement st = null;
        Transaction tx = null;
        try {
        	session = getSession();
			st = session.connection().createStatement();        	
        	try {

				tx = session.beginTransaction();
				st.execute("drop table eventosSapeFechaTemp");
				tx.commit();
			} catch (SQLException e) {
				tx.rollback();
			}
        	
			String sql = "select * into temp eventosSapeFechaTemp from eventossape where date(fecha_inicial) between date('"+fIni+"') and date('"+fFin+"')"+
						(usuario != null && !usuario.equals("") ?" and cliente = '"+usuario+"'":"");
			
			if (debug) logs.debug("TABLA TEMP: \n"+sql);

			st.execute(sql);
			
			
			sql = 	"select cliente,"+
					"(select count(*) from eventosSapeFechaTemp where estado = 'E' and cliente = e.cliente),"+
					"(select count(*) from eventosSapeFechaTemp where estado = 'F' and cliente = e.cliente),"+
					"(select count(*) from eventosSapeFechaTemp where estado = 'A' and cliente = e.cliente),"+
					"(select count(*) from eventosSapeFechaTemp where estado = 'N' and cliente = e.cliente),"+
					"(select count(*) from eventosSapeFechaTemp where cliente = e.cliente)"+
					" from eventosSapeFechaTemp e group by e.cliente order by e."+orderBy;
			
			if (debug) logs.debug("INFORMACION: \n"+sql);
			
			ResultSet rs=st.executeQuery(sql);
			
			
			String rta[] = null;
//			int i = 0;
			while(rs.next()) {
				
				rta = new String[6];
				
				rta[0] = rs.getString(1);
				rta[1] = rs.getString(2);
				rta[2] = rs.getString(3);
				rta[3] = rs.getString(4);
				rta[4] = rs.getString(5);
				rta[5] = rs.getString(6);
				
//				total.add(i,rta);
				total.add(rta);
//				i++;
			}
        	
        } catch (HibernateException e) {
        	exception =e;
        } catch (SQLException e) {
        	exception =e;
		} finally {
        	try{
        		if(session != null) session.close();
        	}catch(HibernateException e){
        		exception =e;
        	}
        }
        if(exception != null) throw new SapeDataException(exception);
        
        return total;
		
	}
	
	public String getInfoPorUsuario(String usuario, String fIni, String fFin) throws SapeDataException {
	
        Session session = null;
        Exception exception = null;
        Statement st = null;
        String datos = null;
        try {
			session = getSession();
			
			st = session.connection().createStatement();
			
			
			String sql =
				"select cliente, extract (hour from fecha_inicial) as hora, count(extract (hour from fecha_inicial)) "+
				"from eventossape where cliente = '"+usuario+"' and date(fecha_inicial) between date('"+fIni+"') and date('"+fFin+"') group by cliente,"+
				"extract(hour from fecha_inicial) order by cliente, hora";
			
			if(debug) logs.debug("PROMEDIO DE USUARIOS:\n\n"+sql);
			
			ResultSet rs = st.executeQuery(sql);
			
			datos = "Grafica de Pruebas. Usuario "+usuario+"*Hora*Pruebas*";
			
			//String data[] = null;
			while (rs.next()) {
				
				//data = new String[3];
				//data[0] = rs.getString(1);
				datos += rs.getString(2)+"*"+rs.getString(3)+"*";
				//data[2] = rs.getString(3);
				
				//total.add(i,data);
				//i++;
			}
			
		} catch (HibernateException e) {
			exception =e;
			
		} catch (SQLException e) {
			exception =e;

		}  finally {
        	try{
        		if(session != null) session.close();
        	}catch(HibernateException e){
        		exception =e;
        	}
        }
        if(exception != null) throw new SapeDataException(exception);
		
        return datos;
	}
	
	
	public List estadisticoPorHora(String fIni,String fFin) throws SapeDataException {
		
		if (debug) logs.debug("estadisticoPorHora: fIni =["+fIni+"], fFin=["+fFin+"]");
		// select e.hora,(select total from tmp where hora = e.hora and estado = 'E'),(select total from tmp where hora = e.hora and estado = 'F'),(select total from tmp where hora = e.hora and estado = 'A'),(select total from tmp where hora = e.hora and estado not in ('A','E','F')) from tmp e group by hora order by hora;
		
		// select count(*) as total,extract(hour from fecha_inicial) as hora,estado into temp tmp from eventossape e group by 2,estado order by 2 ASC;
		
		Exception exception = null;
		Session session = null;
        Statement st = null;
        Transaction tx = null;
        List total = new ArrayList();
        
        try {
			session = getSession();
			
			st = session.connection().createStatement();
			try {
				tx = session.beginTransaction();
				st.execute("drop table estadisticoHoraTemp");
				tx.commit();
			} catch (SQLException e) {
				//no habia tabla temporal. puede continuar con la query!
				tx.rollback();
			}
			
			String sql = "select count(*) as total,extract(hour from fecha_inicial) as hora,estado "+
					"into temp estadisticoHoraTemp from eventossape e "+
					//"where date(e.fecha_inicial) between date('"+fIni+"') and date('"+fFin+"') "+
					"where e.fecha_inicial between '"+fIni+"' and '"+fFin+"' "+
					"group by 2,estado order by 2 ASC";
			
			
			if (debug) logs.debug("Generando tabla temporal: \n"+sql);
			
			st.execute(sql);
			
			sql = "select e.hora,"+
			"(select total from estadisticoHoraTemp where hora = e.hora and estado = 'E'),"+
			"(select total from estadisticoHoraTemp where hora = e.hora and estado = 'F'),"+
			"(select total from estadisticoHoraTemp where hora = e.hora and estado = 'A'),"+
			"(select total from estadisticoHoraTemp where hora = e.hora and estado not in ('A','E','F')) "+
			"from estadisticoHoraTemp e group by hora order by hora";
			
			if (debug) logs.debug("Query de horas: \n"+sql);
			
			ResultSet rs=st.executeQuery(sql);
			
			String data[] = null;
			
			int i = 0;
			while (rs.next()) {
				
				data = new String[5];
				data[0] = rs.getString(1);
				data[1] = rs.getString(2);
				data[2] = rs.getString(3);
				data[3] = rs.getString(4);
				data[4] = rs.getString(5);
				
				total.add(i,data);
				i++;
			}
			
		} catch (HibernateException e) {
			exception =e;
		} catch (SQLException e) {
			exception =e;
		}  finally {
        	try{
        		if(session != null) session.close();
        	}catch(HibernateException e){
        		exception =e;
        	}
        }
        if(exception != null) throw new SapeDataException(exception);
        
		return total;
	}
	
    public List getEstadosCentral(String fechaIni, String fechaFin) throws SapeDataException {
    	///boolean log = logs.isDebugEnabled(); 
    	
    	if (debug) logs.debug("getEstadosCentral");
    	fechaIni = fechaIni + " 00:00:00";
    	fechaFin = fechaFin + " 23:59:59";
    	Exception exception = null;
    	Session session = null;
    	Transaction tx = null;
    	List retorno = new java.util.ArrayList(10);
		
    	try {
			session = getSession();
			Statement stm = session.connection().createStatement();
			tx = session.beginTransaction();
			long tiempoInicial = System.currentTimeMillis();
			String sql = "SELECT central, estado into temp eventossapetemp from eventossape where fecha_inicial between '" + fechaIni + "' and '" + fechaFin + "' ";
			if (debug) logs.debug("Sql: " + sql);
			stm.execute(sql);
			sql = "create index i_central_eventosapetemp on eventossapetemp (central)";
			if (debug) logs.debug("Sql: " + sql);
			stm.execute(sql);
			sql = "select central, " +
					"(select count(estado) from eventossapetemp where central = e.central and estado = 'E') as exito, " +
					"(select count(estado) from eventossapetemp where central = e.central and estado = 'F') as fallo, " +
					"(select count(estado) from eventossapetemp where central = e.central and estado = 'A') as advertencia, " +
					"(select count(estado) from eventossapetemp where central = e.central and estado not in ('E', 'F', 'A')) as inesperado " +
					"from eventossapetemp e group by e.central order by e.central";
			if (debug) logs.debug("sql: " + sql);
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				ViewEstadosCentral v = new ViewEstadosCentral(rs.getString("central"), 
						rs.getInt("exito"), rs.getInt("advertencia"), rs.getInt("fallo"), 
						rs.getInt("inesperado"));
				retorno.add(v);
			}
			sql = "drop table eventossapetemp";
			if (debug) logs.debug("Sql: " + sql);
			stm.execute(sql);			
			if (debug) logs.info("Demora: " + (System.currentTimeMillis() - tiempoInicial));
			stm.close();
			session.flush();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} catch (SQLException e) {
			logs.error(e);
			exception = e;
		} finally {
            try {
            	if (tx != null) tx.rollback();
                if (session != null) session.close();
            } catch (HibernateException e) {
            	logs.error(e);
            	exception =e;
            }
		}
		if (exception != null) {
			throw new SapeDataException(exception);
		}
    	return retorno;
    }
    
    
    public List getEstadisticoTecnologia(String fechaIni,String fechaFin) throws SapeDataException {
    	if(debug) logs.debug("getEstadisticoTecnologia");
    	//////////////////////////////////////////////////////////////////////
    	
    	fechaIni = fechaIni + " 00:00:00";
    	fechaFin = fechaFin + " 23:59:59";
    	Exception exception = null;
    	Session session = null;
    	Transaction tx = null;
    	List<ViewIndicadoresTecnologia> retorno = null;
		
    	try{
    		
			session = getSession();
			Statement stm = session.connection().createStatement();
			tx = session.beginTransaction();
			long tiempoInicial = System.currentTimeMillis();
			String sql = "SELECT tipocabeza, e.estado, c.nombre into temp eventossapetemp "+
			"from eventossape e, tiponodo t, cabezaprueba c where fecha_inicial between "+
			"'"+fechaIni+"' and '"+fechaFin+"' and e.site = t.site and t.tipocabeza = c.idcabeza";
			if (debug) logs.debug("Sql: " + sql);
			stm.execute(sql);
			//sql = "create index i_central_eventosapetemp on eventossapetemp (central)";
			//if (debug) logs.debug("Sql: " + sql);
			//stm.execute(sql);
			sql = "SELECT tipocabeza, nombre, "+
					"(select count(estado) from eventossapetemp where tipocabeza = e.tipocabeza and estado = 'E') as exito," +
					"(select count(estado) from eventossapetemp where tipocabeza = e.tipocabeza and estado = 'F') as fallo," +
					"(select count(estado) from eventossapetemp where tipocabeza = e.tipocabeza and estado = 'A') as advertencia," +
					"(select count(estado) from eventossapetemp where tipocabeza = e.tipocabeza and estado not in ('E', 'F', 'A')) as inesperado "+
					"from eventossapetemp e group by tipocabeza, nombre";

			if (debug) logs.debug("sql: " + sql);
			ResultSet rs = stm.executeQuery(sql);
			
			retorno = new ArrayList();
			
			while (rs.next()) {
				ViewIndicadoresTecnologia v = new ViewIndicadoresTecnologia(rs.getString("tipocabeza"),
						rs.getString("nombre"),	rs.getString("exito"), rs.getString("fallo"), rs.getString("advertencia"), 
						rs.getString("inesperado"));
				retorno.add(v);
			}
			sql = "drop table eventossapetemp";
			if (debug) logs.debug("Sql: " + sql);
			stm.execute(sql);			
			if (debug) logs.info("Demora: " + (System.currentTimeMillis() - tiempoInicial));
			stm.close();
			session.flush();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} catch (SQLException e) {
			logs.error(e);
			exception = e;
		} finally {
            try {
            	if (tx != null) tx.rollback();
                if (session != null) session.close();
            } catch (HibernateException e) {
            	logs.error(e);
            	exception =e;
            }
		}
		if (exception != null) {
			throw new SapeDataException(exception);
		}
    	return retorno;
    	
    	/////////////////////////////////////////////////////////////////////
    	
    }
    
} //Fin de la clase
