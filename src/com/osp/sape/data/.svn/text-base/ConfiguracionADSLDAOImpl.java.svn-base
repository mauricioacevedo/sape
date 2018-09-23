/*
 * Created on Apr 5, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.osp.sape.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.maestros.siplexpro.CPE;
import com.osp.sape.maestros.siplexpro.DSLAM;
import com.osp.sape.maestros.siplexpro.IP;
import com.osp.sape.maestros.siplexpro.PING;
import com.osp.sape.maestros.siplexpro.ConfiguracionADSL;

public class ConfiguracionADSLDAOImpl extends HibernateObject implements ConfiguracionADSLDAO{
		
	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}
	
	
	@SuppressWarnings("unchecked")
	public List getConfiguracionADSL(TipoNodo t) throws SapeDataException{
		if(debug) logs.debug("getConfiguracionADSL( tipoNodo: "+t.getId()+" )");
		
		List l = new ArrayList();
		CPE cpe = null;
		DSLAM dslam = null;
		PING ping = null;
		IP ip = null;
		try {
			cpe = (CPE) cargarObjeto(CPE.class, new Integer(t.getId()));
		}catch(HibernateException e){
			if(!(e instanceof ObjectNotFoundException)){
				logs.error(e);
				throw new SapeDataException(e);
			}
		}
		try {
			dslam = (DSLAM) cargarObjeto(DSLAM.class, new Integer(t.getId()));
		}catch(HibernateException e){
			if(!(e instanceof ObjectNotFoundException)){
				logs.error(e);
				throw new SapeDataException(e);
			}
		}
		
		try{
			ping = (PING) cargarObjeto(PING.class, new Integer(t.getId()));
		}catch(HibernateException e){
			if(!(e instanceof ObjectNotFoundException)){
				logs.error(e);
				throw new SapeDataException(e);
			}
		}
		try{
			ip = (IP) cargarObjeto(IP.class, new Integer(t.getId()));
		} catch (HibernateException e) {
			if(!(e instanceof ObjectNotFoundException)){
				logs.error(e);
				throw new SapeDataException(e);
			}
		}
		
		if(cpe == null){
			cpe = new CPE();
			cpe.setIdCabeza(t.getId());
		}
		l.add(0,cpe);
		if(dslam == null)
			dslam = new DSLAM();
			dslam.setIdCabeza(t.getId());
		l.add(1,dslam);
		if(ping == null)
			ping = new PING();
			ping.setIdCabeza(t.getId());
		l.add(2,ping);
		if(ip ==null)
			ip = new IP();
			ip.setIdCabeza(t.getId());
		l.add(3,ip);
		
		return l;
	}
	
	public ConfiguracionADSL getTipoConfiguracion(String conf){
		boolean log = logs.isDebugEnabled();
		if(log) logs.debug("getTipoConfiguracion: conf: "+conf);
		if(conf.equals("CPE"))
			return new CPE();
		else if(conf.equals("DSLAM"))
			return new DSLAM();
		else if(conf.equals("PING"))
			return new PING();
		else if(conf.equals("IP"))
			return new IP();
		
		return null;
	}
	
	public void guardarConfigADSL(List values,String tipoConfig,TipoNodo t) throws SapeDataException{
		boolean log = logs.isDebugEnabled();
		if(log) logs.debug("guardarConfigADSL: values[List]:"+values.size()+" tipoConfig[String]:"+ tipoConfig+"t[TipoNodo]"+ t.getId());
		List l = getConfiguracionADSL(t);
		Exception exception = null;
		for(int i=0;i<l.size();i++){
			ConfiguracionADSL c = (ConfiguracionADSL)l.get(i);
			
			if(c.getNombreConfiguracion().equals(tipoConfig)){
				c.setValues(values);
				//c.setIdcabeza(t.getId());
				
				System.out.println("\n\n\n\nID="+t.getId()+" c.id="+c.getIdcabeza());
				
				try {
					if(c.getNombreConfiguracion().equals("CPE"))
						actualizarObjeto(((CPE)c));
					else if(c.getNombreConfiguracion().equals("DSLAM"))
						actualizarObjeto(((DSLAM)c));
					else if(c.getNombreConfiguracion().equals("PING"))
						actualizarObjeto(((PING)c));
					else if(c.getNombreConfiguracion().equals("IP"))
						actualizarObjeto(((IP)c));
					
				} catch (HibernateException e) {
					if(log) logs.debug("[WARN]: "+e.getMessage());
					try{
						
						if(c.getNombreConfiguracion().equals("CPE"))
							insertarObjeto(((CPE)c));
						else if(c.getNombreConfiguracion().equals("DSLAM"))
							insertarObjeto(((DSLAM)c));
						else if(c.getNombreConfiguracion().equals("PING"))
							insertarObjeto(((PING)c));
						else if(c.getNombreConfiguracion().equals("IP"))
							insertarObjeto(((IP)c));
						
						
					}catch(HibernateException e1){
						if(log) logs.debug("[ERROR]: "+e1.getMessage());
						exception = e1;
					}
				}
				break;
			} else {
				continue;
			}
		}
		
		if(exception != null) throw new SapeDataException(exception);
		
	}

}
