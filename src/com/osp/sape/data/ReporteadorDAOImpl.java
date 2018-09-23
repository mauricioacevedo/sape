/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Jul 15, 2006 - 5:54:27 PM
 */

package com.osp.sape.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.reportes.ReporteadorPlantilla;
import com.osp.sape.utils.CustomDataSource;
import com.osp.sape.utils.ServicioGUDE;

public class ReporteadorDAOImpl extends HibernateObject implements
		ReporteadorDAO {

	public ReporteadorDAOImpl() {
		super();
	}

	@Override
	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}

    public void insertarReporteadorPlantilla(ReporteadorPlantilla u) throws SapeDataException {
    	if (debug) logs.debug("insertarReporteadorPlantilla: " + u);
    	try {
            insertarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public void actualizarReporteadorPlantilla(ReporteadorPlantilla u) throws SapeDataException {
    	if (debug) logs.debug("actualizarReporteadorPlantilla: " + u);
        try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarReporteadorPlantilla(ReporteadorPlantilla u) throws SapeDataException {
    	if (debug) logs.debug("eliminarReporteadorPlantilla: " + u);
        try {
            eliminarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarReporteadorPlantilla(int id) throws SapeDataException {
    	if (debug) logs.debug("eliminarReporteadorPlantilla: " + id);
    	try {
            eliminarObjeto(getReporteadorPlantilla(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public ReporteadorPlantilla getReporteadorPlantilla(int id) throws SapeDataException {
    	if (debug) logs.debug("getReporteadorPlantilla: " + id);
        ReporteadorPlantilla retorno = null;
        try {
            retorno = (ReporteadorPlantilla) cargarObjeto(ReporteadorPlantilla.class, new Integer(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    
    public List getAllReporteadorPlantillas()throws SapeDataException{
        if (debug) logs.debug("getAllReporteadorPlantillas");
        Session session = null;
        Exception exception = null;
        List l = null;
    	try{
	        session= getSession();
	        l = session.find("from ReporteadorPlantilla k");
	        session.flush();
        }catch(HibernateException e) {
        	logs.error(e);
            exception = e;
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return l;

    }
    
    //TODO Este metodo no deberia estar en un DAO.
    public String enviarMailReporte(String usuario,String listaCorreos,String subject,String mensaje,String pathReporte){
    	if (debug) logs.debug("enviarMailReporte: " + usuario + ", " + listaCorreos + ", " + subject + ", " + mensaje + ", " + pathReporte);
    	
    	ServicioGUDE s = new ServicioGUDE();
    	
    	String comandos[]=new String[7];
    	comandos[0]=usuario;
    	comandos[1]="14";
    	comandos[2]="correo";
    	comandos[3]=listaCorreos;
    	comandos[4]=mensaje;
    	comandos[5]=subject;
    	comandos[6]=pathReporte;
    	String rta = null;
    	try {
			rta=s.ejecutarServicio(comandos);
			if (debug) logs.debug("Respuesta: " + rta);
		} catch (SapeDataException e) {
			logs.error(e);
			rta = e.getLocalizedMessage();
		}
		return rta;
    }

    
    public static void main(String argv[]) throws SapeDataException{
    	ReporteadorDAOImpl r = new ReporteadorDAOImpl();
    	r.getReportes("2006-07-17 00:00:00");
    	//r.enviarMailReporte("macevedo","mauricio.acevedo@gmail.com,aaristizabal@osplaboratories.com","correo con attach","mensaje con attachment","/tmp/text3.txt");
    	//r.getAllReporteadorPlantillas();
    }
    
    public List getReportes(String fechaHoy) throws SapeDataException{
        if (debug) logs.debug("getAllReporteadorPlantillas fecha: ["+fechaHoy+"]");
        
        Session session = null;
        Exception exception = null;
        List listaReporteadores ,listaDataSources,total = new ArrayList();
    	try{
	        session= getSession();
	        // 1. Busco las rutinas que se van a enviar hoy
	        listaReporteadores = session.find("from ReporteadorPlantilla u where u.fechaEnvio = '"+fechaHoy+"'");
	        
	        if(listaReporteadores.size() < 1) return total;
	        
	        int size = listaReporteadores.size();
	        
	        listaDataSources = new ArrayList();
	        
	        for(int i=0;i<size;i++){
	        	CustomDataSource cc = consultar((ReporteadorPlantilla) listaReporteadores.get(i),getSession());
	        	listaDataSources.add(i,cc);
	        }
	        
	        session.flush();
	        session.close();
	        
	        total.add(0,listaReporteadores);
	        total.add(1,listaDataSources);
        }catch(HibernateException e) {
            logs.error(e);
        	exception = e;
        } catch (SQLException e) {
        	logs.error(e);
        	exception = e;
		}finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return total;

    }

    
    private CustomDataSource consultar(ReporteadorPlantilla r,Session session) throws SQLException, HibernateException{
    	CustomDataSource c = new CustomDataSource();
    	Connection conn = session.connection();
    	if (debug) logs.debug("Plantilla: " + r); 
    	
    	//1. limpio las tablas temporales.
    	String tablas[] = {"eventossapetemp","minmaxeventos","prueba_basicatemp","pruebaspptemp","pruebaprogramadatemp","rutinastemp"};
    	
		Statement stt=conn.createStatement();
		for(int k =0;k<tablas.length;k++){
			Transaction tx = null;
			try{
				tx=session.beginTransaction();
				stt.execute("drop table "+tablas[k]);
				tx.commit();
				logs.debug("Borrado exitoso de "+tablas[k]);
			}catch(SQLException e) {
//				logs.error("No se pudo borrar tabla: "+e.getMessage());
				tx.rollback();
			}
		}
    		
    	Object data[][] = null;
    	String names[] = generarNombres(r,"-field");
    	conn.setAutoCommit(false);
    	
        CallableStatement cstm = conn.prepareCall("{ ? = call reporteador(?, ?, ?, ?, ?, ?) }");
    	//CallableStatement cstm = conn.prepareCall("{ ? = call reporteador(?, ?, null, null, null) }");
        cstm.registerOutParameter(1, Types.OTHER);
        cstm.setString(2, r.getDesdeFecha()+" 00:00:00");
        cstm.setString(3, r.getHastaFecha()+" 23:59:59");
        cstm.setString(4, (r.getValorCondicion()==null||r.getValorCondicion().equals("")?null:r.getColCondicion()));
        cstm.setString(5, (r.getValorCondicion()==null||r.getValorCondicion().equals("")?null:r.getTipoCondicion()));
        cstm.setString(6, (r.getValorCondicion()==null||r.getValorCondicion().equals("")?null:r.getValorCondicion()));
        cstm.setString(7, r.getOrderBy());
        if (debug) logs.debug("A punto de ejecutar query");
        long tiempo = System.currentTimeMillis();
        cstm.execute();
        if (debug) logs.debug("Consulata ejecutada. Demora: " + (System.currentTimeMillis() - tiempo));
        ResultSet rs = (ResultSet) cstm.getObject(1);
        boolean bool=rs.next();
        
        if(bool == false){ //no habian registros para esta consulta
        	logs.warn("No hay registros para la consulta.");
        	data = new Object[0][0];
        	c = new CustomDataSource(data,names);
        	return c;
        }
        
        System.out.println("ultimo? "+rs.last());
        int conteo = rs.getRow();
        rs.beforeFirst();
        names =generarNombres(r,"");
        data = new Object[conteo][names.length];
        int i =0;
        
        while (rs.next()){
            
        	for(int j=0;j<names.length;j++){
        		data[i][j]=rs.getString(names[j]);
        	}
        	i++;
        	
        	//System.out.println (rs.getString(1)  + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4));
        }
        //tx.commit();
    	//rs.close();
        c = new CustomDataSource(data,generarNombres(r,"-field"));
    	return c;
    }
    
    /**
     * Obtiene un arreglo de Strings con los nombres de reporte r mas un sufijo.  
     * 
     * @param r
     * @param prefix
     * @return
     */
    public String[] generarNombres(ReporteadorPlantilla r,String sufix){
    	
    	String names = r.getCampos();
    	    	
    	StringTokenizer stt = new StringTokenizer(names,",");
    	String na[] = new String[stt.countTokens()];
    	
    	int i=0;
    	
    	while(stt.hasMoreTokens()){
    		na[i]=stt.nextToken()+sufix;
    		i++;
    	}
    	
    	return na;
    }
    /**
     * Obtiene un arreglo de string con los labels del reporte r
     * 
     * @param r
     * @return
     */
    public String[] generarLabels(ReporteadorPlantilla r){
    	
      	String labels = r.getEtiquetas();
    	
    	StringTokenizer stt = new StringTokenizer(labels,",");
    	String na[] = new String[stt.countTokens()];
    	
    	int i=0;
    	
    	while(stt.hasMoreTokens()){
    		na[i]=stt.nextToken();
    		i++;
    	}
    	
    	return na;
    }
}