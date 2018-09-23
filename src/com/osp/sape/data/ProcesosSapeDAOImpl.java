package com.osp.sape.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.ProcesosSape;

public class ProcesosSapeDAOImpl extends HibernateObject implements
		ProcesosSapeDAO {

	private Logger logs;
	
	public ProcesosSapeDAOImpl() {
		super();
		logs = LogManager.getLogger(getClass());
	}

    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationGestor.getInstance();
    }

	
    public void insertarProcesosSape(ProcesosSape u) throws SapeDataException {
        try {
            insertarObjeto(u);
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo insertarProcesosSape:\n"+e);
            throw new SapeDataException(e);
        }
    }

    public void actualizarProcesosSape(ProcesosSape u) throws SapeDataException {
        try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo actualizarProcesosSape:\n"+e);
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarProcesosSape(ProcesosSape U) throws SapeDataException {
        try {
            eliminarObjeto(U);
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo eliminarProcesosSape:\n"+e);
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarProcesosSape(int id) throws SapeDataException {
        try {
            eliminarObjeto(getProcesosSape(id));
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo eliminarProcesosSape(catch 1):\n"+e);
            throw new SapeDataException(e);
        } catch (SapeDataException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo eliminarProcesosSape(catch 2):\n"+e);
            throw new SapeDataException(e);
        }
    }

    public List getProcesosSape(String orderBy,String filtro, String valorFiltro) throws SapeDataException {

        Session session = null;
        Exception exception = null;
        List l ;
    	try{
    		String where = (filtro != null && !filtro.equals("") && !filtro.equals("ninguno")?" where lower(u."+filtro+") like lower('%"+valorFiltro+"%') ":"");
	        session= getSession();
	        l = session.find("from ProcesosSape u "+where+" order by u."+orderBy);
	        session.flush();
	        session.close();
        }catch(HibernateException e){
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo getProcesosSape(catch 1):\n"+e);
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				if(logs.isDebugEnabled()) logs.debug("ERROR en metodo getProcesosSape(catch 2):\n"+e);
				exception = e;
			}
    	}
        if(exception != null) throw new SapeDataException(exception);
        return l;
    }
    
    
    public ProcesosSape getProcesosSape(int id) throws SapeDataException {

    	ProcesosSape retorno = null;
        try {
            retorno = (ProcesosSape) cargarObjeto(ProcesosSape.class, new Integer(id));
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo getProcesosSape:\n"+e);
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getAllProcesosSape(String orderBy)throws SapeDataException{
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from ProcesosSape u order by u."+orderBy);
	        session.flush();
	        session.close();
        }catch(HibernateException e){
        	if(logs.isDebugEnabled()) logs.debug("ERROR en metodo getAllProcesosSape(catch 1):\n"+e);
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				if(logs.isDebugEnabled()) logs.debug("ERROR en metodo getAllProcesosSape(catch 2):\n"+e);
				exception = e;
			}
    	}
        if(exception != null) throw new SapeDataException(exception);
        return l;
    }
    
    public String ejecutarAccion(String id,String accion,String usuario) throws SapeDataException {
    	
    	Process p = null;
		String linea = "";
    	
		if(logs.isDebugEnabled()) logs.debug("Inicia ejecutarAccion:\n"+
				"id="+id+" accion="+accion+" usuario="+usuario);
		
    	try {
    		if (accion.equals("stopForce")) {
    			p=Runtime.getRuntime().exec("/opt/gude/sqltcl/ServicioGUDE.tcl "+usuario+" 1 stop "+id+" 1");
    			if(logs.isDebugEnabled()) logs.debug("SE EJECUTO:\n"+
    					"/opt/gude/sqltcl/ServicioGUDE.tcl "+usuario+" 1 stop "+id+" 1 2>&1");
    		} else {
    			p=Runtime.getRuntime().exec("/opt/gude/sqltcl/ServicioGUDE.tcl "+usuario+" 1 "+accion+" "+id+"");
    			if(logs.isDebugEnabled()) logs.debug("SE EJECUTO:\n"+
    					"/opt/gude/sqltcl/ServicioGUDE.tcl "+usuario+" 1 "+accion+" "+id+" 2>&1");
    		}
    		
    		BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()));
    		
    	
    		
    		BufferedReader bff = new BufferedReader(new InputStreamReader(p.getErrorStream()));
    		while(true){
    			
    			if(bff.ready())
    				logs.debug("ERROR: \n"+bff.readLine());
    			linea = bf.readLine();
    			if(debug)logs.debug(linea);
    			if(linea.indexOf("%FIN")!= -1)
    				break;
    			if(linea.indexOf("%Respuesta") != -1){//la siguiente linea es la rta!!!
    				linea = bf.readLine();
    				
    				linea=linea.replace(',',' ');
    				
    				break;
    			}
    		}
		} catch (IOException e) {
			throw new SapeDataException(e);
		}
		
		return linea;
    }
    
    /**
     * ./ServicioGUDE.tcl SAPE 1 status id_de_la_tabla
     * ./ServicioGUDE.tcl SAPE 1 stop  id_de_la_tabla 
     * ./ServicioGUDE.tcl SAPE 1 stop  id_de_la_tabla 1 //para hacer kill -9
     * ./ServicioGUDE.tcl SAPE 1 start id_de_la_tabla 
     */
}
