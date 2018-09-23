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
import com.osp.sape.maestros.rutinas.PruebaProgramada;

public class PruebaProgramadaDAOImpl extends HibernateObject implements PruebaProgramadaDAO {


    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }
	
    public void insertarPruebaProgramada(PruebaProgramada u) throws SapeDataException {
    	if (debug) logs.debug("insertarPruebaProgramada: " + u);
        try {
            insertarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public void actualizarPruebaProgramada(PruebaProgramada u) throws SapeDataException {
    	if (debug) logs.debug("actualizarPruebaProgramada: " + u);
        try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarPruebaProgramada(PruebaProgramada U) throws SapeDataException {
    	if (debug) logs.debug("eliminarPruebaProgramada: " + U);
    	try {
            eliminarObjeto(U);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarPruebaProgramada(int id) throws SapeDataException {
    	if (debug) logs.debug("eliminarPruebaProgramada: " + id);
    	try {

            eliminarObjeto(getPruebaProgramada(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        } catch (SapeDataException e) {
            throw new SapeDataException(e);
        }
    }

    public PruebaProgramada getPruebaProgramada(int id) throws SapeDataException {
    	if (debug) logs.debug("getPruebaProgramada: " + id);
    	PruebaProgramada retorno = null;
        try {
            retorno = (PruebaProgramada) cargarObjeto(PruebaProgramada.class, new Integer(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getAllPruebaProgramadas()throws SapeDataException{
    	if (debug) logs.debug("getAllPruebaProgramadas");     
        Session session= null;
        List l = new ArrayList();
    	Exception exception = null;
    	try{
	        session= getSession();
	        l = session.find("from PruebaProgramada u order by u.transaccion");
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            exception = e;
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

//    protected Configuration getConfiguration() throws MappingException {
//        if (configuration == null) {
//            configuration = new Configuration();
//            try {
//                configuration.configure();
//            } catch (HibernateException e) {
//                System.out.println("ERROR: " + e);
//            }
//        }
//        return configuration;
//    }
    
    
    public List getListaInicialPruebasProgramadas() throws SapeDataException{
        if(debug) logs.debug("getListaInicialPruebasProgramadas");
    	
    	Session session = null;
        List l = new ArrayList();
        Exception exception = null;
        
        try {
        	session= getSession();
        	l = session.find("select distinct p.tipoDePrueba from PruebaProgramada p");
//        	System.out.println("lista inicial tamano: "+l.size());
        } catch(HibernateException e){
        	exception = e;
        } finally{
    		try{
    			if(session != null)session.close();
    		}catch(HibernateException e){
    			exception = e;
    		}
        }
        if (exception != null) throw new SapeDataException(exception); 
        return l;
    }
    
    /**
     * Metodo que obtiene un conteo de todos los codigos ver de la tabla pruebaspp
     * recibe como parametro un codigover si este parametro es vacio o null retorna
     * todos los codigosver
     */
    public List getEstadisticoCodigosVer(String codv,String tipoPrueba, String regPorPagina, String offset) throws SapeDataException {
    	if (debug) logs.debug("getEstadisticoCodigosVer: " + codv + ", " + tipoPrueba+", "+regPorPagina+", "+offset);
    	
        Session session = null;
        List lista = new ArrayList(),total = new ArrayList();
        Exception exception = null;
        String sql = null, sqlCount=null;
        String endSQL = "";
        if(regPorPagina ==null||offset ==null){
        	endSQL="";
        }else{// agrego los elementos al final del sql para que realice bien la paginacion
        	endSQL=" limit "+regPorPagina+" offset "+offset;
        }
        
    	try{
    		session= getSession();
    		logs.debug("NUEVO CODIGO, ERROR EN OTRO LADO");
    		if ( codv == null || codv.equals("") ) {
    			
    			sql = "select count(*),ps.codigover from PruebaSPP ps "+
				"where ps.idpruebaprogramada IN (select p.transaccion from PruebaProgramada p where p.idRutina = '"+tipoPrueba+"') "+
				"group by ps.codigover";
   			
    		} else {
    			
    			sql = "from PruebaSPP ps " +
				"where ( ps.idpruebaprogramada IN (from PruebaProgramada p where p.idRutina = '"+tipoPrueba+"') "+
				"AND ( ps.codigover = '"+ codv +"')) order by ps.idpruebapp "+endSQL;
    			sqlCount ="select count(*) from PruebaSPP ps " +
				"where ( ps.idpruebaprogramada IN (from PruebaProgramada p where p.idRutina = '"+tipoPrueba+"') "+
				"AND ( ps.codigover = '"+ codv +"')) ";
    		}
    		if(debug) logs.debug("[HQL: "+sql+"]");
    		lista = session.find(sql);
    		total.add(0,lista);
    		lista = null;
    		
    		if(sqlCount!=null){
    			lista =session.find(sqlCount);
    			if(lista!=null&&lista.size()>0){
    				total.add((Integer)lista.get(0));
    			}
    		}
    		
    		//lista = getListaInicialPruebasProgramadas();
    		//total.add(1,lista);
    		session.flush();
    	}catch(HibernateException e){
    		exception = e;
    	}finally{
    		try{
    			if(session != null)session.close();
    		}catch(HibernateException e){
    			exception = e;
    		}
    	}

        if (exception != null) throw new SapeDataException(exception);
        
        return total;
    }

    public int eliminarEstadisticoCodigosVer(String tipoPrueba) throws SapeDataException {
    	if (debug) logs.debug("eliminarEstadisticoCodigosVer: " + tipoPrueba);
    	
        Session session = null;
       // List lista = new ArrayList(),total = new ArrayList();
        int deletedRows=0;
        Exception exception = null;
        String sql = null;
        //String endSQL = "";
        
    	try{
    		session= getSession();
    		logs.debug("NUEVO CODIGO, ERROR EN OTRO LADO");

    		
   			sql = "from PruebaSPP ps "+
				"where ps.idpruebaprogramada IN (select p.transaccion from PruebaProgramada p where p.idRutina = '"+tipoPrueba+"') ";
   			

    		if(debug) logs.debug("[HQL: "+sql+"]");
    		deletedRows = session.delete(sql);
    		if(debug) logs.debug("Elementos borrados de pruebaspp: "+deletedRows);
    		sql = "from PruebaProgramada p where p.idRutina = '"+tipoPrueba+"'";
    		int deletedPP=session.delete(sql);
    		if(debug) logs.debug(deletedPP	);
    		session.flush();
    	}catch(HibernateException e){
    		exception = e;
    	}finally{
    		try{
    			if(session != null)session.close();
    		}catch(HibernateException e){
    			exception = e;
    		}
    	}

        if (exception != null) throw new SapeDataException(exception);
        
        return deletedRows;
    }

    
    
    
    /**
     * Este metodo retorna una lista de PruebaSPP con todos los registros asociados a un tipo de prueba,
     * este ultimo se pasa por parametro junto con el rango de fechas y otra informacion
     * para cuestiones de paginacion.
     */
     
    public List getPruebasProgramadas(String tipoPrueba, String regPorPagina, String offset) throws SapeDataException{
    	if (debug) logs.debug("getPruebasProgramadas: " + tipoPrueba+", "+regPorPagina+", "+offset);
        Session session = null;
        List l = null,total = new ArrayList();
        Exception exception = null;
        String endSQL = "";
        if(regPorPagina ==null||offset ==null){
        	endSQL="";
        }else{// agrego los elementos al final del sql para que realice bien la paginacion
        	endSQL=" limit "+regPorPagina+" offset "+offset;
        }

        //total.add(0,new ArrayList());
    	try{
    		session= getSession();
    		String sql = "from PruebaSPP ps where ps.idpruebaprogramada IN "+
    		" (select p.transaccion from PruebaProgramada p where p.idRutina = '"+tipoPrueba+"') ";
    		
    		//String sql2="a.fechaFinal, b.distancia from EventoSape a, PruebaBasica b where a.id=b.id and a.id=";
    		
            if(tipoPrueba != null && !tipoPrueba.equals("")){
            	if(debug)logs.debug("[HQL: "+sql+" order by ps.idpruebapp "+endSQL+" ]");
    			l = session.find(sql+" order by ps.idpruebapp "+endSQL);
	    		total.add(0,l);
	    		l=null;
	    		l = session.find("select count(*) "+sql);
	    		
	    		if(l!=null&&l.size()>0){
	    			total.add(1,(Integer)l.get(0));
	    		}
	    		
            }
            
    		//l = null;
    		//l = getListaInicialPruebasProgramadas();
    		//total.add(1,l);
    	}catch(HibernateException e){
    		exception = e;
    	}finally{
    		try{
    			if(session != null)session.close();
    		}catch(HibernateException e){
    			exception = e;
    		}
    	}
        if (exception != null) throw new SapeDataException(exception);
        
        return total;
    }
    
    
    public int actualizarEstadosPruebasProgramadas(String prueba, String codv, String idrutina,String cliente) throws SapeDataException{
    	if (debug) logs.debug("actualizarEstadosPruebasProgramadas: " + prueba);
        Session session = null;
        Exception exception = null;
        int cuantos = -1, cuantosDelete=-1;
        Transaction tx = null;
        String tabla = "";
        
        if (prueba.startsWith("CA"))
        	tabla = "rutina_cable";
        else if (prueba.startsWith("AR"))
        	tabla = "rutina_armario";
        else if (prueba.startsWith("CL"))
        	tabla = "rutina_cliente";
        else if(prueba.equals("SE")){
        	tabla = null;// las rutinas por serie no tienen tabla de rutina asignada.
        }
        
        //total.add(0,new ArrayList());
    	try{
    		session= getSession();
    		String sql="";
    		if(tabla != null){// para que no haga este update debido a que las rutinas por serie no tienen esta tabla
    		
	    		sql = "update "+tabla+" set estatus= 'II' where telefono in "+
	    		//"(select telefono from eventossape where ideventossape in "+
	    		"(select telefono from pruebaspp where codigover = '"+codv+"' and idpruebaprogramada in "+
	    		"(select transaccion from pruebaprogramada where idrutina = '"+idrutina+"') ) "+
	    		(prueba.startsWith("CL")&&cliente!=null&&!cliente.equals("")?" and usuario='"+cliente+"'":"");
	    		
	    		logs.debug("ACTUALIZACION:\n\n"+sql);
	    		
	    		tx = session.beginTransaction();
	    		cuantos = session.connection().createStatement()
	    		.executeUpdate(sql);
    		}
    		sql = "delete from pruebaspp where telefono in "+
    		//"(select telefono from eventossape where ideventossape in "+
    		"(select telefono from pruebaspp where codigover = '"+codv+"' and idpruebaprogramada in "+
    		"(select transaccion from pruebaprogramada where idrutina = '"+idrutina+"'))";
    		
    		logs.debug("BORRADO:\n\n"+sql);
    		
    		cuantosDelete=session.connection().createStatement().executeUpdate(sql);
    		tx.commit();
    		if(debug) logs.debug("Resultado: Actualizados = ["+cuantos+"], Borrados = ["+cuantosDelete+"]");
    		//if(cuantos != cuantosDelete)
    		//	cuantos = -1;
    		
    		
    		
    		
    	}catch(HibernateException e){
    		exception = e;
    		try {
				tx.rollback();
			} catch (HibernateException e1) {
				exception = e;
			}
    	} catch (SQLException e) {
    		exception = e;
    		try {
				tx.rollback();
			} catch (HibernateException e1) {
				exception = e; 
			}
		}finally{
    		try{
    			if(session != null)session.close();
    		}catch(HibernateException e){
    			exception = e;
    		}
    	}
        if (exception != null) throw new SapeDataException(exception);
        
        return cuantos;
    }

    
    public List getEstadisticoMensualCalificacion(String fIni,String fFin) throws SapeDataException {
    	logs.debug("getEstadisticoMensualCalificacion: fIni="+fIni+", fFin="+fFin);
    	List total;
    	Session session=null;
    	Exception exception=null;
    	
    	total=new ArrayList();
    	
    	Object datosMes[];//arreglo de 3 posiciones: 1. mes, 2. calificados,3. total.
    	try {
			session=getSession();
			Statement st = session.connection().createStatement();
			logs.debug("ya obtuve la conexion");
			//1. query donde obtengo un timestamp por cada uno de los meses que tiene el sistema en rutinas
			String sql="select date_trunc('month',fechaini) as fecha_mes from pruebaprogramada pruebaprog1_ where (pruebaprog1_.fechaini between '"+fIni+"' and '"+fFin+"' ) group by 1 order by 1 DESC";			
			logs.debug("SQL1: "+sql);
			ResultSet rs=st.executeQuery(sql);
			
			List l = new ArrayList();
			while(rs.next()){
				//se guardan los meses ke la consulta comprende.
				l.add(rs.getDate(1));
			}
			
			int ii=0,size=l.size();
			
			while(ii<size){
				Date rta=(Date) l.get(ii);
				ii++;
				//logs.debug("Obtuve nun : "+rta);
				
				sql= "SELECT transaccion from pruebaprogramada where extract(month from fechaini) = extract(month from date '"+rta+"') and extract(year from fechaini) = extract(year from date '"+rta+"')";
				
				datosMes = new Object[6];
				datosMes[0]="0";// Mes de la consulta
				datosMes[1]="0";// Pares BPD
				datosMes[2]="0";// Pares NBPD
				datosMes[3]="0";// Pares RPD
				datosMes[4]="0";// Pares no probados
				datosMes[5]="0";// Total mes
				
				
				//2. esta consulta trae conteos de todos los registros que hay por cada uno de los meses consultados anteriormente
				
				
				String sql2= "SELECT count(*),calificaciondatos from pruebaspp where idpruebaprogramada in ("+sql+") group by calificaciondatos";
				logs.debug("SQL:"+sql2);
				
				//3. obtengo la cantidad de pruebas totales por el mes x
				ResultSet rs2=st.executeQuery(sql2);
				
				//logs.debug("despues del 2do query");
				
				int counterTotal =0,counterBuenos=0,counterMalos=0,counterNoPrueba=0,counterRegulares=0;
				
				while(rs2.next()!=false){
	
					String label=rs2.getString(2);
					if(label==null||label.equals("")||label.equals("N")){
						counterNoPrueba=rs2.getInt(1);
					}else if(label.indexOf("M")!=-1){
						counterMalos=rs2.getInt(1);
					}else if(label.indexOf("B")!=-1){
						counterBuenos=rs2.getInt(1);
					}else if(label.indexOf("R")!=-1){
						counterRegulares=rs2.getInt(1);
					}else{
						logs.debug("ENCONTRO UN ESTADO INCONSISTENTE");
						counterNoPrueba=rs2.getInt(1);
					}
					
				}

				counterTotal=counterBuenos+counterMalos+counterNoPrueba+counterRegulares;
				
				
				/*//4. obtengo los pares calificados de todas las pruebas por el mes x
				sql2+="  and calificaciondatos = 'B'";
				rs2=st.executeQuery(sql2);
				
				int counterCalificados =0; 
				if(rs2.next()==false)counterCalificados=0;
				else counterCalificados=rs2.getInt(1);
				*/
				//datosMes = new Object[3];
				datosMes[0]=rta;
				datosMes[1]=String.valueOf(counterBuenos);// Pares BPD
				datosMes[2]=String.valueOf(counterMalos);// Pares NBPD
				datosMes[3]=String.valueOf(counterRegulares);// Pares RPD
				datosMes[4]=String.valueOf(counterNoPrueba);// Pares no probados
				datosMes[5]=String.valueOf(counterTotal);// Total mes
				
				total.add(datosMes);
				
				//logs.debug("Calificados ("+counterCalificados+") total ("+counterTotal+") mes ("+rta+")");
			}
			logs.debug("TERMINO CONSULTA");
			//session.close();
		} catch (HibernateException e) {
			exception=e;
		} catch (SQLException e) {
			exception=e;
		}finally{
        	try{
        		if(session != null) session.close();
        	}catch (HibernateException e) {
				logs.error(e);
        		exception = e;
			}
		}
    	
		if(exception!=null)throw new SapeDataException(exception);
		
    	return total;
    }
    
    public List getRegistrosCalificacion(String filtro, String valorFiltro, String fIni, String fFin, String regPorPagina, String offset, String orderBy) throws SapeDataException {
    	if (debug) logs.debug("getRegistros: " + filtro + ", " +valorFiltro + ", " + fIni + ", " + fFin + ", " + 
				regPorPagina + ", " + offset + ", " + orderBy);
    	
        Session session = null;
        List l ,total = new ArrayList();
        Exception exception = null;

        //1. estableco, segun las variables de entrada, la condicion del where
        // el where por fechas va sobre la tabla pruebaprogramada
        String whereFechas = " p.fechaIni between '"+fIni+"' and '"+fFin+"' ";
        // el where del filtro va sobre la tabla pruebaspp
        String whereFiltro = "";
        String where="";
        if(filtro!=null&&valorFiltro!=null){
        	whereFiltro = " and ps."+filtro+" = '"+valorFiltro+"' ";
        }
        
        String endSQL =" order by ps."+orderBy+" limit "+regPorPagina+" offset "+offset; 
        
        //2. armo la consulta completa en hql.
		String sql = "from PruebaSPP ps where ps.idpruebaprogramada IN "+
		" (select p.transaccion from PruebaProgramada p where "+whereFechas+") "+whereFiltro;
		
		try {
			session = getSession();
			
			//3. obtengo el conteo de la cantidad de datos para esa consulta.
			
			String query = "select count(ps) "+sql;
			if(debug)logs.debug("[SQL1] "+query+"[/SQL1]");
			
			l=session.find(query);
			
			if(l!=null&&l.size()<=0)
				total.add(0,"0");
			else
				total.add(0,l.get(0).toString());
			
			//4. obtengo los registros de la consulta.
			
			
			query=sql+where+endSQL;
			
			if(debug)logs.debug("[SQL2]\n "+query+" \n[/SQL2]");
			l=session.find(query);
			total.add(1,l);
			
		} catch (HibernateException e) {
            logs.error(e);
        	exception = e;
		}finally{
        	try{
        		if(session != null) session.close();
        	}catch (HibernateException e) {
				logs.error(e);
        		exception = e;
			}
		}
		if(exception!=null) throw new SapeDataException(exception);
		
		return total;
    }
    
	public List getRegistros(String filtro, String fIni, String fFin, String regPorPagina, String offset, String orderBy) throws SapeDataException {
		if (debug) logs.debug("getRegistros: " + filtro + ", " + fIni + ", " + fFin + ", " + 
							regPorPagina + ", " + offset + ", " + orderBy);
        Session session = null;
        List l ,total = new ArrayList();
        Exception exception = null;
        
        //Dependiendo del parametro option se generara determinada sentencia where:
        String where = "";
        
        
        if(orderBy.equals(""))
        	orderBy = " order by e.transaccion DESC ";
        else 
        	orderBy = " order by e."+orderBy;
        //Controlar la cantidad de registros por pagina y la paginacion.
        if(regPorPagina.equals(""))
        	regPorPagina = "100";
        if(offset.equals(""))
        	offset = "0";
        if(filtro==null || fIni==null||fFin==null){
        	where = "";
        }else if(filtro.equals("") || fIni.equals("")||fFin.equals("")){
            //Es un pantallazo inicial o hay algun error en el envio de parametros.
            //Se le dira que traiga los ultimos 100 registros
        	where = "";
        }else if(filtro.equalsIgnoreCase("todos")){
            //se piden todos los telefonos entre el rango de fecha dado
        	if(fIni.equals("") || fFin.equals("")){
        		where="";
        	}where= "where date(e.fechaIni) between date('"+fIni+"') and date('"+fFin+"') ";
            
        }else  if(filtro != null && fIni != null && fFin != null){
        	if(!filtro.equals("") && !fIni.equals("") && !fFin.equals("")){
        		
        		where = "where  substr(e.tipoDePrueba, 1,2) = '"+filtro.toUpperCase()+"'  and date(e.fechaIni) between date('"+fIni+"') and date('"+fFin+"') ";
        	}
        }

        try {
            session= getSession();

            List l2 = null;

            l2 =session.find("select count(*) from PruebaProgramada e "+ where);
            
            Integer cantReg=(Integer)l2.get(0);
            total.add(0, cantReg); 
            
            String hql = "from PruebaProgramada e "+ where  + orderBy+ " limit " + regPorPagina + " offset " + offset;
            if(debug)logs.debug("Hql: " + hql);
            l = session.find(hql);
            total.add(1,l);
            session.flush();
        } catch (HibernateException e) {
            logs.error(e);
        	exception = e;
        }finally{
        	try{
        		if(session != null) session.close();
        	}catch (HibernateException e) {
				logs.error(e);
        		exception = e;
			}
        }
        if(exception != null)throw new SapeDataException(exception);
        return total;
	}

	public static void main(String argv[]){
		
		PruebaProgramadaDAOImpl p = new PruebaProgramadaDAOImpl();
		try {
			p.getEstadisticoMensualCalificacion("2005-01-01 00:00:00","2006-10-02 23:59:59");
		} catch (SapeDataException e) {
			e.printStackTrace();
		}
	}
	
}