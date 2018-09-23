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
import com.osp.sape.maestros.PruebaBasica;

/**
 * 
 * @author Andres Aristizabal
 */
public class PruebaBasicaDAOImpl extends HibernateObject implements PruebaBasicaDAO {

    
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

    public PruebaBasica getPruebaBasica(long id) throws SapeDataException {
    	PruebaBasica retorno = null;
        try {
            retorno = (PruebaBasica) cargarObjeto(PruebaBasica.class, new Long(id));
        } catch (HibernateException e) {
        	throw new SapeDataException(e);
        }
        return retorno;        
    }

    public List getAllPruebaBasica() throws SapeDataException {
        
    	Session session = null;
        List l = new ArrayList();
    	Exception exception = null;
    	try{
            session= getSession();
            l = session.find("from PruebaBasica p order by p.id");
            session.flush();
            session.close();
	    }catch(HibernateException e){
	        exception =e;
	    }finally{
        	try{
        		if(session != null) session.close();
        	}catch(HibernateException e){
        		exception=e;
        	}
        }
	    
	    if(exception != null) throw new SapeDataException(exception);
        return l;
    }
    
    public String getGrafica(long ticket) throws SapeDataException{
        if (debug) logs.debug("getGrafica: " + ticket);
        
        String ret=null;
        Session session = null;
        Exception exception = null;
        try{
            session= getSession();
            Statement st= session.connection().createStatement();
            
            st.execute("select * from prueba_extendida where id='"+ticket+"'");
            
            ResultSet rs=st.getResultSet();

            if (rs.next()) {
                ret= rs.getString(2);
            }
            session.close();
        }catch(HibernateException e){
            exception =e;
        } catch (SQLException e) {
        	exception =e;
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
            	exception =e;
            }
        }
        if(exception != null) throw new SapeDataException(exception);
        return ret;
    }
    
    public List getEstadisticoTelefonos(String tel, String fIni, String fFin) throws SapeDataException{
    	
        if (debug) logs.debug("getEstadisticoTelefonos: tel=" + tel+", fIni="+fIni+", fFin="+fFin );
        
        List total = new ArrayList(), tmp = null;
        Session session = null;
        Exception exception = null;
        Statement st = null;
        
        Transaction tx = null;
        try {
            session= getSession();
            st= session.connection().createStatement();
        	tx = session.beginTransaction();
        	st.execute("drop table tmpSAPE");
        	tx.commit();
        } catch (SQLException e) {
        	try {
				tx.rollback();
			} catch (HibernateException e1) {
				logs.error(e);
			}
        } catch (HibernateException e) {
        	logs.error(e);
        	exception = e;
        } finally {
        	try{
        		if(session != null) session.close();
        	}catch (HibernateException e) {
				exception = e;
			}
        }
        if(exception != null) throw new SapeDataException(exception);
 
        
        exception = null;
        
        try{
            
            String sqlTemp = "select p.idprueba_basica,e.fecha_inicial,p.codv,e.cliente,"+
            "e.estado into temp tmpSAPE from eventossape e,prueba_basica p where "+
            "e.ideventossape = p.idprueba_basica and e.telefono = '"+tel+"' "+
            "and e.fecha_inicial between '"+fIni+"' and '"+fFin+"'";
            
            if(logs.isDebugEnabled()) logs.debug("SQL TABLA TEMP: \n"+sqlTemp);
            
            st.execute(sqlTemp);
            
            String sql = "select count(*),p.codv,(select clasificacion from codigosver where codigover = p.codv) "+
            			" from tmpSAPE p group by p.codv order by p.codv ASC";
            
            /*String sql = "select count(*),p.codv,"+
            	"(select clasificacion from codigosver where codigover = p.codv)"+
            	" from eventossape e,prueba_basica p where e.ideventossape = p.idprueba_basica and "+
            	" e.telefono = '"+tel+"' and e.fecha_inicial between"+
            	" '"+fIni+" 00:00:00' and '"+fFin+" 23:59:59' group by p.codv order by p.codv ASC";*/
            
            if(logs.isDebugEnabled()) logs.debug("Lista de codsv: \n"+sql);
            
            ResultSet rs=st.executeQuery(sql);
            
            tmp = new ArrayList();
            int i = 0;
            String data[] = null;
            
            while(rs.next()){
            	
            	data = new String[3];
            	data[0] = rs.getString(1);
            	data[1] = rs.getString(2);
            	data[2] = rs.getString(3);
            	
            	tmp.add(i,data);
            	i++;
            }
            // en la primera pos. esta la relacion de codigos ver!!!!!
            total.add(0,tmp);
            
//            sql = " select count(*),p.estado from prueba_basica p, eventossape e"+
//            	" where e.ideventossape = p.idprueba_basica and e.telefono = '"+tel+"'"+
//            	" and  e.fecha_inicial between '"+fIni+" 00:00:00' and '"+fFin+" 23:59:59' "+
//            	" group by p.estado";
            
            sql = "select count(estado),estado from tmpSAPE group by estado";
            
            /*sql = " select count(estado), e.estado from eventossape e"+
        	" where e.telefono = '"+tel+"'"+
        	" and  e.fecha_inicial between '"+fIni+" 00:00:00' and '"+fFin+" 23:59:59' "+
        	" group by e.estado";*/
            
            if(logs.isDebugEnabled()) logs.debug("Relacion de estados: \n"+sql);
            
            rs=st.executeQuery(sql);
            //TODO falta asignar los valores a un vector y mandar al jsp estadisticoTelefonos.jsp
            
            tmp = new ArrayList();
            
            i = 0;
            while (rs.next()) {
            	
            	data = new String[2];
            	data[0] = rs.getString(1);//pocision 1: count.
            	String estado = rs.getString(2);//pocision 2: estado. 
            		//Reemplazo el estado por la palabra completa.
//            	System.out.println("ESTADO: " + estado);
            	if (estado.equals("E")) { 
            		estado = "EXITO";
            	} else if (estado.equals("F")) {
            		estado = "FALLO";
            	} else if (estado.equals("A")) {
            		estado = "ADVERTENCIA";
            	} else {
            		estado = "INESPERADO";	
            	}
            	data[1] = estado;
            	tmp.add(i,data);
            	i++;
            }
            total.add(1,tmp);
            
            
            sql = "select count(*),cliente,estado from tmpSAPE group by cliente,estado "+
            	  "order by cliente ASC";
            
            /*           
            sql = "select count(*),e.cliente,e.estado from eventossape e,prueba_basica p "+
            "where e.ideventossape = p.idprueba_basica and e.telefono = '"+tel+"' "+
            "and e.fecha_inicial between '"+fIni+" 00:00:00' and '"+fFin+" 23:59:59' "+
            "group by e.cliente,e.estado order by e.cliente ASC;";*/

            if(logs.isDebugEnabled()) logs.debug("Relacion de clientes: \n"+sql);
            rs=st.executeQuery(sql);
            
            tmp = new ArrayList();
            
            i = 0;
            while (rs.next()) {
            	
            	data = new String[3];
            	data[0] = rs.getString(1);//pocision 1: count.
            	data[1] = rs.getString(2);//pocision 2: cliente.
            	data[2] = rs.getString(3);//pocision 3: estado.
            	tmp.add(i,data);
            	i++;
            }
            total.add(2,tmp);
            
            sql = "select idprueba_basica,fecha_inicial,codv from tmpSAPE "+
            	  "order by fecha_inicial ASC";
            /*
            sql = "select p.idprueba_basica,e.fecha_inicial,p.codv from eventossape e,prueba_basica p "+
            "where e.ideventossape = p.idprueba_basica and e.telefono = '"+tel+"' "+
            "and e.fecha_inicial between '"+fIni+" 00:00:00' and '"+fFin+" 23:59:59' "+
            "order by e.fecha_inicial ASC";*/

            if(logs.isDebugEnabled()) logs.debug("Primera prueba y ultima: \n"+sql);
            rs=st.executeQuery(sql);
            
            tmp = new ArrayList();
            
            i = 0;
            Object data1[] = null; 
            while (rs.next()) {
            	
            	data1 = new Object[3];
            	data1[0] = rs.getString(1);//pocision 1: idPruebaBasica.
            	
            	data1[1] = rs.getString(3);//pocision 3: codv.
            	
            	data1[2] = rs.getTimestamp(2);//pocision 2: Fecha.
            	if(i == 0){
            		tmp.add(0,data1);
            		data1 = null;
            	}
            	i++;
            }
            
            if(i ==0){//no habian registros para este telefono.
                total.add(3,null);
            }else{
                tmp.add(1,data1);
                total.add(3,tmp);
            }
            

            
            session.close();
        }catch(HibernateException e){
            exception =e;
        } catch (SQLException e) {
        	exception =e;
        } finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
            	exception =e;
            }
        }
        if(exception != null) throw new SapeDataException(exception);
        return total;
    	
    }
    
    public List getEstadisticoPorCodv(String fIni, String fFin,String central,String tech) throws SapeDataException{
    	
    	double time1= System.currentTimeMillis();
    	
    	if(debug) logs.debug("getEstadisticoPorCodv: fIni=["+fIni+"], fFin=["+fFin+"], central=["+central+"]");
    	
		Exception exception = null;
		Session session = null;
        Statement st = null;
        Transaction tx = null;
        List total = new ArrayList();
        
        
        
        try {
			session = getSession();
			tx = session.beginTransaction();
			st = session.connection().createStatement();
						
			String sql = "select ideventossape as id into temp eventossapeTemporal"+
					" from eventossape where fecha_inicial between '"+fIni+"'"+
					" and '"+fFin+"'"+(central != null && !central.equals("")?" and central ='"+central+"'":"")+
					(tech != null && !tech.equals("")?" and site like '%"+tech+"%'":"");
			if(debug) logs.debug("sql: "+sql);
			st.execute(sql);
						
			sql = "select idprueba_basica, codv into temp prueba_basicatemp from prueba_basica where idprueba_basica between (select min(id) from eventossapeTemporal) and (select max(id) from eventossapeTemporal)";
			if(debug) logs.debug("sql: "+sql);
			st.execute(sql);
			
			sql = "select codv, (select clasificacion from codigosver where codigover = codv),count (*) from prueba_basicatemp, eventossapetemporal where idprueba_basica = id group by codv order by codv";
			if(debug) logs.debug("sql: "+sql);
			ResultSet rs = st.executeQuery(sql);
			
			String data[] = null;
			int i =0;
			
			while(rs.next()) {
				data = new String[3];
				data[0] = rs.getString(1);// codigover
				data[1] = rs.getString(2);//descripcion del codigover
				data[2] = rs.getString(3);// cantidad
				total.add(i,data);
				i++;
			}
			
			sql = "drop table eventossapeTemporal";
			if (debug) logs.debug("sql: " + sql);
    		st.execute(sql);
			
			sql = "drop table prueba_basicatemp";
			if (debug) logs.debug("sql: " + sql);
    		st.execute(sql);
			
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
            	exception =e;
            }
		}
        
		System.out.println("Demora: " + (System.currentTimeMillis() - time1));
		
		if(exception != null) throw new SapeDataException(exception);
        return total;
    }
    
    
    //TODO cambiar a eventosapeDAO//listo, trabajito pal puto!!!!!
//    public List getEstadosCentral(String fechaIni, String fechaFin) throws SapeDataException {
//    	if (logs.isDebugEnabled()) logs.debug("getEstadosCentral");
//    	fechaIni = fechaIni + " 00:00:00";
//    	fechaFin = fechaFin + " 23:59:59";
//    	Exception exception = null;
//    	Session session = null;
//    	Transaction tx = null;
//    	List retorno = new java.util.ArrayList(10);
//		
//    	try {
//			session = getSession();
//			Statement stm = session.connection().createStatement();
//			tx = session.beginTransaction();
//			long tiempoInicial = System.currentTimeMillis();
//			String sql = "SELECT central, estado into temp eventossapetemp from eventossape where fecha_inicial between '" + fechaIni + "' and '" + fechaFin + "' ";
//			if (logs.isDebugEnabled()) logs.debug("Sql: " + sql);
//			stm.execute(sql);
//			sql = "create index i_central_eventosapetemp on eventossapetemp (central)";
//			if (logs.isDebugEnabled()) logs.debug("Sql: " + sql);
//			stm.execute(sql);
//			sql = "select central, " +
//					"(select count(estado) from eventossapetemp where central = e.central and estado = 'E') as exito, " +
//					"(select count(estado) from eventossapetemp where central = e.central and estado = 'F') as fallo, " +
//					"(select count(estado) from eventossapetemp where central = e.central and estado = 'A') as advertencia, " +
//					"(select count(estado) from eventossapetemp where central = e.central and estado not in ('E', 'F', 'A')) as inesperado " +
//					"from eventossapetemp e group by e.central order by e.central";
//			if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
//			ResultSet rs = stm.executeQuery(sql);
//			while (rs.next()) {
//				ViewEstadosCentral v = new ViewEstadosCentral(rs.getString("central"), 
//						rs.getInt("exito"), rs.getInt("advertencia"), rs.getInt("fallo"), 
//						rs.getInt("inesperado"));
//				retorno.add(v);
//			}
//			sql = "drop table eventossapetemp";
//			if (logs.isDebugEnabled()) logs.debug("Sql: " + sql);
//			stm.execute(sql);			
//			if (logs.isInfoEnabled()) logs.info("Demora: " + (System.currentTimeMillis() - tiempoInicial));
//			stm.close();
//			session.flush();
//		} catch (HibernateException e) {
//			logs.error(e);
//			exception = e;
//		} catch (SQLException e) {
//			logs.error(e);
//			exception = e;
//		} finally {
//            try {
//            	if (tx != null) tx.rollback();
//                if (session != null) session.close();
//            } catch (HibernateException e) {
//            	logs.error(e);
//            	exception =e;
//            }
//		}
//		if (exception != null) {
//			throw new SapeDataException(exception);
//		}
//    	return retorno;
//    }
//    
    //TODO hacer un DAO para esto.//listo
//    public PruebaAtu getPruebaAtu (int id) throws SapeDataException {
//    	Session session = null;
//    	Exception exception = null;
//    	Statement stm = null;
//    	PruebaAtu retorno = null;
//    	try {
//			session = getSession();
//			stm = session.connection().createStatement();
//			ResultSet rs = stm.executeQuery("select * from siplexpro_atu where id = " + id);
//			if (rs.next()) {
//				retorno = new PruebaAtu();
//				retorno.setId(id);
//				retorno.setMaxdsbr(rs.getString("maxdsbr"));
//				retorno.setMaxusbr(rs.getString("maxusbr"));
//				retorno.setIntdsbr(rs.getString("intdsbr"));
//				retorno.setIntusbr(rs.getString("intusbr"));
//				retorno.setFdsbr(rs.getString("fdsbr"));
//				retorno.setFusbr(rs.getString("fusbr"));
//				retorno.setCapds(rs.getString("capds"));
//				retorno.setCapus(rs.getString("capus"));
//				retorno.setSnrmds(rs.getString("snrmds"));
//				retorno.setSnrmus(rs.getString("snrmus"));
//				retorno.setPwrds(rs.getString("pwrds"));
//				retorno.setPwrus(rs.getString("pwrus"));
//				retorno.setAttnds(rs.getString("attnds"));
//				retorno.setAttnus(rs.getString("attnus"));
//				retorno.setOpmode(rs.getString("opmode"));
//				retorno.setState(rs.getString("state"));
//				retorno.setCodv(rs.getString("codv"));
//				retorno.setEstado(rs.getString("estado"));
//			}
//			stm.close();
//			session.flush();
//		} catch (HibernateException e) {
//			logs.error(e);
//			exception = e;
//		} catch (SQLException e) {
//			logs.error(e);
//			exception = e;
//		} finally {
//           try {
//                if (session != null) session.close();
//            } catch (HibernateException e) {
//            	logs.error(e);
//            	exception =e;
//            }
//		}
//		if (exception != null) {
//			throw new SapeDataException(exception);
//		}
//	   	return retorno;    	
//    }
    
}
