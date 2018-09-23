/*
 * Created on Jul 1, 2005
 */
package com.osp.sape.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.reportes.ViewReportePruebas;

/**
 * 
 * @author Andres Aristizabal y Mauricio Acevedo
 */
public class ReportesDAOImpl extends HibernateObject implements ReportesDAO{

    private org.apache.log4j.Logger logs; 
    
    public ReportesDAOImpl() {
        logs = org.apache.log4j.Logger.getLogger(getClass());
    }
    
    
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

    
    /**
     * Este metodo recibe por para metro option, que es el valor por el cual se va  a hacer la
     * busqueda(codigover, telefono, usuario, tipo de prueba, ninguna).
     * Cuando la variable option pasa como ninguna significa que es una busque da de todos los
     * registros entre las fechas inicial y final nada mas.
     */
    public List getRegistros(String filtro, String valorFiltro, String usuario, String fIni, String fFin,String regPorPagina,String offset,String orderBy) throws SapeDataException{
        
    	boolean log = logs.isDebugEnabled();
    	
    	if (log) logs.debug("getRegistros: " + filtro + ", " + valorFiltro + ", " + fIni + ", " + fFin + ", " + regPorPagina + ", " + offset + ", " + orderBy);
//        long comienza = System.currentTimeMillis();
    	
        Session session = null;
        Transaction tx = null;
        Statement stm = null;

    	/** En l se guarda las lista de beans con cada vista
    	* total es una lista donde, aparte de la lista de vistas l, se guarda informacion
    	* como el numero de registros.
    	*/
        List l = new ArrayList();
        List total = new ArrayList();
        Exception exception = null;
        ResultSet rs = null;
        
        String whereFiltro = null;
        String whereFechas = null;
        	//este es para validar en el codv, porque el codv no sale de eventossape, 
        	//sino de pruebaBasica.
        String wherePruebaBasica = ""; 
    	
      	orderBy = " order by "+orderBy;
      	
        if (filtro.equals("reportePorSite")) {
        	wherePruebaBasica= " and e.site  = '"+valorFiltro+"'";
        	filtro = "";
        	valorFiltro = "todos";
        }
      	
      
        if(filtro != null && !filtro.equals("") && !filtro.equals("ninguno") && valorFiltro != null && !valorFiltro.equals("")){
        	if (filtro.equals("rangoTelefono")) {
        		whereFiltro = "e.telefono like '" + valorFiltro + "%'";
        	} else if (filtro.equals("codv")) {
        		wherePruebaBasica = " and codv = '" + valorFiltro + "'"; 
        	} else if (filtro.equals("")|| valorFiltro.equals("todos")){
        		whereFiltro = null;
        	} else {
        		
        		if(filtro.equals("codvCentral")){
        			// hay ke mandar el valorFiltro con un token donde va el codv y la central
        			// codv se busca en prueba_basica y la central en eventossape!!!
        			
        			StringTokenizer stt = new StringTokenizer(valorFiltro,"*");
        			String codvv = null;
        			String centr = null;
        			
        			if(stt.countTokens() == 2){
        				codvv=stt.nextToken();
        				centr=stt.nextToken();
        				
        				wherePruebaBasica = " and codv = '"+codvv+"'";
        				whereFiltro = " e.central = '"+centr+"' ";
        			}else{
        				filtro = "ninguno";
        			}
        			
        			
        		} else if (filtro.equals("tecnologia")){
        			valorFiltro = (valorFiltro.equals("FTX")?"INDIGO":valorFiltro);
        			whereFiltro = "site like '%" + valorFiltro + "%'";
        		}else{
        			if(valorFiltro.equals("EA"))// es una consulta por estado!!!
        				whereFiltro = " ( estado ='E' or estado ='A' ) ";
        			else
        				whereFiltro = filtro + "= '" + valorFiltro + "'";
        			}
        	}
        }
        
        if (fIni != null && !fIni.equals("") && fFin != null && !fFin.equals("")) {
        	whereFechas = "fecha_inicial between '"+fIni+" 00:00:00' and '"+fFin+" 23:59:59'";
        }
        
        	//aqui valido para concatenar la condiciones de filtro y de fechas.
        String whereDefinitivo = "";
        if (whereFechas != null) {
        	  whereDefinitivo = " WHERE " + whereFechas;
        }
        if (whereFiltro != null) {
        	if (whereFechas != null) {
             	whereDefinitivo += " AND " + whereFiltro;
        	} else {
        		whereDefinitivo = " WHERE " + whereFiltro;
        	}
        }
        
        if (filtro.equals("prueba")) {
        	whereDefinitivo = " WHERE e.ideventossape = "+valorFiltro+" ";
        	wherePruebaBasica = "";
        } 
 
        String sql = "";
        String tik="";
        try{
			session= getSession();
			tx = session.beginTransaction();
			stm = session.connection().createStatement();
        	
            sql = "SELECT e.ideventossape AS ticket, e.telefono, e.estado, e.tipoprueba, e.central,e.site, e.cliente AS usuario, e.fecha_inicial AS fecha, to_char(e.fecha_final - e.fecha_inicial, 'SS.MS'::text) AS duracion INTO TEMP eventossapeTemp FROM eventossape e " + whereDefinitivo+
            	(usuario != null && !usuario.equals("")?" and e.cliente = '"+usuario+"'":"");
            if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
            stm.execute(sql);

            
//            sql = "SELECT idprueba_basica, codv into temp prueba_basicaTemp from prueba_basica where idprueba_basica between (select min(ticket) from eventossapeTemp) and (select max(ticket) from eventossapetemp)";
            		//este nuevo sql tambien toma en cuenta las tablas siplexpro_atu y categorias.
            sql = "SELECT idprueba_basica, codv into temp prueba_basicatemp from prueba_basica where idprueba_basica between (select min(ticket) from eventossapeTemp) and (select max(ticket) from eventossapetemp) union select id, codv from siplexpro_atu where id between (select min(ticket) from eventossapeTemp) and (select max(ticket) from eventossapetemp) union select id, codv from siplexpro_ping where id between (select min(ticket) from eventossapeTemp) and (select max(ticket) from eventossapetemp) union select id, codv from siplexpro_estadisticasconexion where id between (select min(ticket) from eventossapetemp) and (select max(ticket) from eventossapetemp)";

            if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
            stm.execute(sql);
                        
            sql = "create INDEX i_ticket_eventossapetemp on eventossapeTemp (ticket)";
            if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
            stm.execute(sql);
            
//            sql = "SELECT idprueba_basica, codv into temp prueba_basicaTemp from prueba_basica where idprueba_basica between (select min(ticket) from eventossapeTemp) and (select max(ticket) from eventossapetemp)";
//            if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
//            stm.execute(sql);
            
            // SELECT e.*, codv,(select clasificacion from codigosver where codv=codigover) as descripcion from eventossapeTemp e, prueba_basicaTemp where ticket = idprueba_basica
            sql = "SELECT e.*, codv,(select clasificacion from codigosver where codv=codigover) as descripcion INTO TEMP view_reporte_pruebasTemp from eventossapeTemp e, prueba_basicaTemp where ticket = idprueba_basica" + wherePruebaBasica;
            if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
            stm.execute(sql);

            sql = "select count(*) from view_reporte_pruebasTemp";
            if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
            rs = stm.executeQuery(sql);
            rs.next();
            total.add(0,rs.getString(1));
            System.out.println("count: "+rs.getString(1));
            

            String limite = (regPorPagina!=null && offset != null && !regPorPagina.equals("")&&!offset.equals("")&&!offset.startsWith("exportar")?" limit " + regPorPagina + " offset " + offset:"");
            
            sql = "select * from view_reporte_pruebasTemp" + orderBy + limite;
        	if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
        	rs = stm.executeQuery(sql);

	            while (rs.next()) {
	            	ViewReportePruebas vista = new ViewReportePruebas();
	            	
	            	vista.setTicket(rs.getLong("ticket"));
	            	tik=String.valueOf(vista.getTicket());
	            	vista.setEstado(rs.getString("estado"));
	            	vista.setCentral(rs.getString("central"));
	            	vista.setCodigoVer(rs.getString("codv"));
	            	
	            	String dur=rs.getString("duracion");
	            	
	            	double duracion = 0;
	            	try{
	            		duracion=Double.parseDouble(dur);
	            	}catch(NumberFormatException e){
	            		logs.debug("Duracion del telefono "+vista.getTelefono()+" es invalida. Se deja en cero.");
	            		duracion=0;
	            	}
	            	
	            	vista.setDuracion(String.valueOf(duracion));
	            	vista.setFecha(new Date(rs.getTimestamp("fecha").getTime()));
	            	vista.setTelefono(rs.getString("telefono"));
	            	vista.setTipoPrueba(rs.getString("tipoprueba"));
	            	vista.setUsuario(rs.getString("usuario"));
	            	vista.setDescripcionCodv(rs.getString("descripcion"));
	            	l.add(vista);
	            }
	            System.out.println();
	            total.add(1,l);
                        
            sql = "drop table eventossapeTemp";
            if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
        	stm.execute(sql);
        	sql = "drop table prueba_basicaTemp";
        	if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
        	stm.execute(sql);
        	sql = "drop table view_reporte_pruebasTemp";
        	if (logs.isDebugEnabled()) logs.debug("sql: " + sql);
        	stm.execute(sql);
            
        	stm.close();
            session.flush();
            tx.commit();
        } catch (SQLException e) {
        	logs.error(e);
        	exception = e;
        	//logs.debug("TICKET: "+tik);
        } catch (HibernateException e) {
        	logs.error(e);
            exception = e;
            //logs.debug("TICKET: "+tik);
        }finally {
        	
        	/*if(offset.equals("exportar")){
        		logs.debug("se supone ke ya deberia estar afuera de aki!");
        		return total;
        	}*/
        	
        	try{
        		if (logs.isDebugEnabled()) logs.debug("Va a hacer el rollback");
        		if (tx != null) tx.rollback();
        		if (logs.isDebugEnabled()) logs.debug("Va a cerrar la session");	
        		if(session != null) session.close();
        	}catch (HibernateException e) {
        		logs.error("Haciendo el rollback y cerrando la session: " + e);
        		exception = e;
			}
        }
        
        if(exception != null)throw new SapeDataException(exception);
        return total;
    }
    
    
    public void consulta(){
    	String sqlSites="select distinct site from eventossape where fecha_inicial between '2008-03-28 00:00:00' and '2008-03-28 23:59:59'";
    	
    	   Session session = null;
           Transaction tx = null;
           Statement stm = null;
           ResultSet rs = null;
           Exception exception;
           
           try{
   			session= getSession();
   			tx = session.beginTransaction();
   			stm = session.connection().createStatement();
   			
   			rs=stm.executeQuery(sqlSites);
   			
   			List lista= new ArrayList();
   			
   			while(rs.next()){
   				String site=rs.getString(1);
   				lista.add(site);
   			}
   			
   			for(int i=0;i<lista.size();i++){
   				String site=(String) lista.get(i);
   				String sqlTelefono="select telefono from eventossape where site like '%"+site+"%' limit 5";
   				
   				rs=stm.executeQuery(sqlTelefono);
   			
   	   			while(rs.next()){
   	   				String telefono=rs.getString(1);
   	   				System.out.println(telefono+"\t"+site);
   	   			}
   				
   			}
   			
   			
   			
           } catch (SQLException e) {
           	logs.error(e);
           	exception = e;
           	//logs.debug("TICKET: "+tik);
           } catch (HibernateException e) {
           	logs.error(e);
               exception = e;
               //logs.debug("TICKET: "+tik);
           }
    }
    
    public static void main(String argv[]) throws SapeDataException{
    	ReportesDAOImpl r = new ReportesDAOImpl();
    	String filtro="estado";
    	String valorFiltro="F";
    	String usuario=null;
    	String fIni="2009-10-01";
    	String fFin="2009-10-19";
    	String regPorPagina="100";
    	String offset="0";
    	String orderBy=" ticket DESC";
    	r.getRegistros(filtro, valorFiltro, usuario, fIni, fFin,regPorPagina,offset,orderBy);
    	
    }
    
       
}
