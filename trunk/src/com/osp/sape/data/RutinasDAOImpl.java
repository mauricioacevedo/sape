/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Jun 29, 2006 - 4:09:48 PM
 */

package com.osp.sape.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.Rutinas;

public class RutinasDAOImpl extends HibernateObject implements RutinasDAO {

	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}

	
    public void insertarRutina(Rutinas u) throws SapeDataException {
    	if (debug) logs.debug("insertarRutina, Rutinas=" + u);
    	try {
            insertarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public void actualizarRutina(Rutinas u) throws SapeDataException {
    	if (debug) logs.debug("actualizarRutina, Rutinas=" + u);
        try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarRutina(Rutinas u) throws SapeDataException {
    	if (debug) logs.debug("eliminarRutina, Rutinas=" + u);
        try {
            eliminarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarRutina(int id) throws SapeDataException {
    	if (debug) logs.debug("eliminarRutina, id=" + id);
    	try {
            eliminarObjeto(getRutina(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public Rutinas getRutina(int id) throws SapeDataException {
    	if (debug) logs.debug("getRutina, id=" + id);
        Rutinas retorno = null;
        try {
            retorno = (Rutinas) cargarObjeto(Rutinas.class, new Integer(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getAllRutinasPorTipo(String tipo) throws SapeDataException{
        if (debug) logs.debug("getAllRutinasPorTipo, tipo="+tipo);
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from Rutinas u where u.tipo = '"+tipo+"' order by u.id");
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return l;

    }
    /**
     * 
     * Metodo para obtener los elementos de rutina por un tipo dado y dependiendo del 
     * boolean activos se extraen los ke estan o no habilitados.
     * 
     * @param tipo
     * @param user
     * @param activos
     * @return
     * @throws SapeDataException
     */
    public List getElementosActivos(String tipo,String user,boolean activos) throws SapeDataException{
        if (debug) logs.debug("getElementosActivos, tipo="+tipo+", user="+user+", activos="+activos);
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        String sql = "select u.valorTipo,u.id from Rutinas u where u.tipo='"+tipo+"' "+(activos==true?" and u.habilitado=true ":"")+
	        (user!=null && !user.equals("")?"and u.usuario='"+user+"' ":"")+" order by u.id";
	        
	        if(debug) logs.debug("[SQL:"+sql+" ]");
	        l = session.find(sql);
	        
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return l;
    }
    
    public List getElemento(String nombreElemento, boolean activo) throws SapeDataException{
        if (debug) logs.debug("getElemento, nombreElemento="+nombreElemento);
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        String sql = "from Rutinas u where u.valorTipo='"+nombreElemento+"' and u.habilitado = "+activo;
	        l = session.find(sql);
	        
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return l;    	
    }
    
    public List<Rutinas> getRutinas(String opcion,String valorOpcion,String fechaIni,String fechaFin,String regPorPagina,String offset,String orderBy) throws SapeDataException{
    	if (debug) logs.debug("getRutinas, opcion="+opcion+", fechaIni="+fechaIni+", fechaFin="+fechaFin+", regPorPagina="+regPorPagina+", offset="+offset+", orderBy="+orderBy);
    	List<Rutinas> registros = null;
    	Integer conteo = null;
    	List ret = new ArrayList();
    	Exception exception = null;
    	Session session = null;
    	
    	// para armar la consulta completa:
    	//1. condicion de fechas
    	String whereFecha = " u.fechaProgramada between '"+fechaIni+"' and '"+fechaFin+"' ";
    	//2. condicion para paginacion
    	String whereFinal = " order by u."+orderBy+" limit "+regPorPagina+" offset "+offset;
    	//3. condiciones adicionales
    	String whereQuery = "";
    	//4. variable donde kedara la consulta final
    	String where = "";
    	//5. verifico si el campo opcion es vacio o tiene valor.
    	if(opcion != null && !opcion.equals("")){
    		whereQuery = " and u.tipo='"+opcion+"' "+(valorOpcion!=null&&!valorOpcion.equals("")?" and u.valorTipo='"+valorOpcion+"' ":"");
    		//where = where2+" and "+where;
    	}

    	//6. armo el where final de esta consulta.
    	where = whereFecha+whereQuery+whereFinal;
    	
    	
    	try {
    		session = getSession();
    		//Statement stm = session.connection().createStatement();
    		String sql = "from Rutinas u where "+where;
    		
    		if (debug) logs.debug("[SQL: " + sql+" ]");
    		registros=(List<Rutinas>)session.find(sql);
    		
    		sql = "select count(u) from Rutinas u where "+whereFecha+whereQuery;
    		if (debug) logs.debug("[SQL2: " + sql+" ]");
    		List l=session.find(sql);
    		
    		//logs.debug("listaa: "+l.get(0).getClass().getCanonicalName());
    		conteo = (Integer) l.get(0);
    		
    		ret.add(0,registros);
    		ret.add(1,conteo);
    		//stm.close();
    		session.close();
    	} catch (HibernateException e) {
    		logs.error(e);
    		exception = e;
//    	} catch (SQLException e) {
//    		logs.error(e);
//    		exception = e;
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

    	return ret;
    	
    }
    
}
