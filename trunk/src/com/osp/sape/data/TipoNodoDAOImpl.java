/*
 * Created on Apr 2, 2005
 */
package com.osp.sape.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.CPRSiplexPro;
import com.osp.sape.maestros.CabezaPrueba;
import com.osp.sape.maestros.ConfiguracionEWSD;
import com.osp.sape.maestros.SiplexproLI;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.maestros.siplexpro.ParMatrizSiplexPro;

/** * @author Andres Aristizabal */
public class TipoNodoDAOImpl extends HibernateObject implements TipoNodoDAO {

	
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

    
    public void eliminarTipoNodo(int id) throws SapeDataException {
    	if (debug) logs.debug("eliminarTipoNodo" + id);
    	try {
            eliminarObjeto(getTipoNodo(id));
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}
    }

    public TipoNodo getTipoNodo(int id)throws SapeDataException {
        if (debug) logs.debug("getTipoNodo: " + id);
    	TipoNodo retorno = null;
        try {
            retorno = (TipoNodo) cargarObjeto(TipoNodo.class, new Integer(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;            

    }

    
    public void insertarTipoNodo(TipoNodo c) throws SapeDataException{
    	if (debug) logs.debug("insertarTipoNodo: " + c);
    	try {
            insertarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }    
        
    }
    
    
    public void actualizarTipoNodo(TipoNodo u) throws SapeDataException {
    	if (debug) logs.debug("actualizarTipoNodo: " + u);
    	try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    
    
    public synchronized List getAllTipoNodo() throws SapeDataException {
        List listaTipoNodos = null;
        Session session = null;
        Exception exception = null;
        try{
            session= getSession();
            
            listaTipoNodos = session.find("from TipoNodo u order by u.tipoCabeza,u.estado,u.site ASC");

            session.flush();
            session.close();
        } catch(HibernateException e){
        	logs.error (e);
            exception = e;
        } finally {
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return listaTipoNodos;
    }
    
    
    public void cambiarMetodoConexionSiplexPRO(List tipoNodos, UserSipe user) throws SapeDataException{
    	if(debug) logs.debug("cambiarMetodoConexionSiplexPRO: tamano de lista de TipoNodo's: " + tipoNodos.size());
    	Session session = null;
    	Exception exception = null;
    	Connection co = null;
    	Transaction tx = null;
    	try {
			session = getSession();
			tx = session.beginTransaction();
			co = session.connection();
			Statement st = co.createStatement();
			
			for(int i=0;i<tipoNodos.size();i++){
				TipoNodo t = (TipoNodo)tipoNodos.get(i);
				st.execute("update tiponodo set ipcabeza = ipesclavo, ipesclavo = ipcabeza,"+
				" puertocabeza = puertoesclavo, puertoesclavo = puertocabeza,usuarioultimocambio = '"+user.getNick()+"' where idtiponodo = "+t.getId()+";");
			}
			tx.commit();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				logs.error(e);
				exception = e;
			}
		} catch (SQLException e) {
			logs.error(e);
			exception = e;
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				logs.error(e);
				exception = e;
			}
		}
		
		if(exception != null)throw new SapeDataException(exception);
    }
    
    public List getListaCabezasId()throws SapeDataException{

        Session session = null;
        ResultSet rs=null;
        Connection co = null;
        List listaCodigos = new ArrayList();
        Exception exception = null;
        
        try {
            session = getSession();
            co = session.connection();
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        
        try{
            
            rs=co.createStatement().executeQuery("select distinct(idtiponodo),site from tiponodo order by idtiponodo");
            
            while(rs.next()){
                
                String s1 = rs.getString(1);
                String s2 = rs.getString(2);
                
                TipoNodo t = new TipoNodo();
                t.setId(Integer.parseInt(s1));
                t.setSite(s2);
                listaCodigos.add(t);
            }
            
        }catch(SQLException err){
            logs.error(err);
        	exception = err;            
        }finally{
            try{
                session.close();
            }catch (HibernateException e) {
                logs.error(e);
            	exception = e;
            }
        }
        
        if(exception != null) throw new SapeDataException(exception);
        return listaCodigos;
    }

    
	public List getCprTipoNodo(TipoNodo cabeza) throws SapeDataException {
		if (debug) logs.debug("getCprTipoNodo: " + cabeza);
		List retorno = new ArrayList();
		Exception exception = null;
		Session session = null;
		try {
			session = getSession();
			retorno = session.find("from CPRSiplexPro c where c.tipoNodo.id = '" + cabeza.getId() + "' order by c.cpr, c.telefono");
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} finally {
            try{
                if (session != null) session.close();
            }catch (HibernateException e) {
                logs.error(e);
            	exception = e;
            }
		}
		if(exception != null) throw new SapeDataException(exception);
		return retorno;
	}

	public List getCprTipoNodoResumido(TipoNodo cabeza) throws SapeDataException {
		if (debug) logs.debug("getCprTipoNodoResumido: " + cabeza);
		List retorno = null;
		Exception exception = null;
		Session session = null;
		try {
			session = getSession();
			Connection c = session.connection();
			ResultSet rs = c.createStatement().executeQuery("select count(telefono) as telefono, central, cpr from cpr_siplexpro where idtiponodo = " + cabeza.getId() + " group by central, cpr");
			retorno = new ArrayList();
			while (rs.next()) {
				CPRSiplexPro cpr = new CPRSiplexPro();
				cpr.setCentral(rs.getString("central"));
				cpr.setCpr(rs.getInt("cpr"));
				cpr.setTelefono(rs.getInt("telefono"));
				cpr.setTipoNodo(cabeza);
				retorno.add(cpr);
			}
			rs.close();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} catch (SQLException e) {
			logs.error(e);
			exception = e;			
		} finally {
            try{
                session.close();
            }catch (HibernateException e) {
                logs.error(e);
            	exception = e;
            }
		}
		if(exception != null) throw new SapeDataException(exception);
		return retorno;
	}

	
	public CPRSiplexPro getCPR(long telefono) throws SapeDataException {
		if (debug) logs.debug("getCPR: " + telefono);
		CPRSiplexPro cpr = null;
		try {
			cpr = (CPRSiplexPro)cargarObjeto(CPRSiplexPro.class, new Long(telefono));
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
		return cpr;
	}

	
	public ConfiguracionEWSD getDLU(long telefono) throws SapeDataException {
		if (debug) logs.debug("getDLU: " + telefono);
		ConfiguracionEWSD dlu = null;
		try {
			dlu = (ConfiguracionEWSD)cargarObjeto(ConfiguracionEWSD.class, new Long(telefono));
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
		return dlu;
	}
	
	public List getDLUsTipoNodo(TipoNodo cabeza) throws SapeDataException {
		if (debug) logs.debug("getDLUsTipoNodo: " + cabeza);
		List retorno = new ArrayList();
		Exception exception = null;
		Session session = null;
		try {
			session = getSession();
			retorno = session.find("from ConfiguracionEWSD c where c.tipoNodo.id = '" + cabeza.getId() + "' order by c.dlu, c.telefono");
			session.flush();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} finally {
            try{
                if (session != null) session.close();
            } catch (HibernateException e) {
                logs.error(e);
            	exception = e;
            }
		}
		if(exception != null) throw new SapeDataException(exception);
		return retorno;
	}
	
	
	public List getDLUsTipoNodoResumido(TipoNodo cabeza) throws SapeDataException {
		if (debug) logs.debug("getDLUsTipoNodoResumido: " + cabeza);
		List retorno = null;
		Exception exception = null;
		Session session = null;
		String sql = "select count(telefono) as telefono, central, dlu from siplexpro_ewsd where idtiponodo = " + cabeza.getId() + " group by central, dlu";
		if (debug) logs.debug("sql: " + sql);
		try {
			session = getSession();
			Connection c = session.connection();			
			ResultSet rs = c.createStatement().executeQuery(sql);
			retorno = new ArrayList(1000);
			while (rs.next()) {
				ConfiguracionEWSD dlu = new ConfiguracionEWSD(new Long(rs.getLong("telefono")), rs.getString("central"), new Long(rs.getLong("dlu")), cabeza);
				retorno.add(dlu);
			}
			rs.close();
			session.flush();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} catch (SQLException e) {
			logs.error(e);
			exception = e;			
		} finally {
            try{
                if (session != null) session.close();
            }catch (HibernateException e) {
                logs.error(e);
            	exception = e;
            }
		}
		if(exception != null) throw new SapeDataException(exception);
		return retorno;
	}

	
	public SiplexproLI getLI(long telefono) throws SapeDataException {
		if (debug) logs.debug("getLI: " + telefono);
		SiplexproLI li = null;
		try {
			li = (SiplexproLI)cargarObjeto(SiplexproLI.class, new Long(telefono));
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
		return li;
	}
	
	public List getLIsTipoNodo(TipoNodo cabeza) throws SapeDataException {
		if (debug) logs.debug("getLIsTipoNodo: " + cabeza);
		List retorno = new ArrayList();
		Exception exception = null;
		Session session = null;
		String hql = "from SiplexproLI s where s.tipoNodo.id = '" + cabeza.getId() + "' order by s.li";
		if (debug) logs.debug("Hql: " + hql);
		try {
			session = getSession();
			retorno = session.find(hql);
			session.flush();
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} finally {
            try{
                if (session != null) session.close();
            }catch (HibernateException e) {
                logs.error(e);
            	exception = e;
            }
		}
		if(exception != null) throw new SapeDataException(exception);
		return retorno;
	}
	
	
	public void actualizarCPR(CPRSiplexPro cpr) throws SapeDataException {
		if (debug) logs.debug("actualizarCPR: " + cpr);
		try {
			actualizarObjeto(cpr);
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
	}


	
	public List getTipoNodosCentral(String central) throws SapeDataException {
		if (debug) logs.debug("getTipoNodosCentral: " + central);
		List retorno = null;
		Session session = null;
		Exception exception = null;
		try {
			session = getSession();
			retorno = session.find("from TipoNodo t where t.site like '%" + central + "%' order by t.site ASC");
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;
			}
		}
		if (exception != null) throw new SapeDataException(exception);
		return retorno;
	}

	
	public List getTipoNodosPorTecnologia(String tecnologia) throws SapeDataException {
		if (debug) logs.debug("getTipoNodosPorTecnologia: " + tecnologia);
		List retorno = null;
		Session session = null;
		Exception exception = null;
		try {
			session = getSession();
			retorno = session.find("from TipoNodo t where t.site like '%" + tecnologia + "%' order by t.site ASC");
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;
			}
		}
		if (exception != null) throw new SapeDataException(exception);
		return retorno;
	}

	public List getTipoNodosPorTecnologia(int tipoCabeza,String estado) throws SapeDataException {
		if (debug) logs.debug("getTipoNodosPorTecnologia: cabeza: " +tipoCabeza+", estado: "+estado );
		List retorno = null;
		Session session = null;
		Exception exception = null;
		CabezaPrueba c = new CabezaPrueba();
		c.setId(tipoCabeza);
		
		String endSql=(estado!=null&&!estado.equals("")?" and t.estado = '"+estado+"'":"");
		
		try {
			session = getSession();
			retorno = session.find("from TipoNodo t where t.tipoCabeza = " + tipoCabeza + endSql +" order by t.site ASC");
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;
			}
		}
		if (exception != null) throw new SapeDataException(exception);
		return retorno;
	}

	
	public void guardarPar(ParMatrizSiplexPro par) throws SapeDataException {
		if (debug) logs.debug("guardarPar: " + par);
		try {
				//si entra en la excepcion, inserto el telefono, si lo encuentra lo actualiza.
			try {
				getParMatriz(par.getPar());
				actualizarObjeto(par);
			} catch (SapeDataException e) {
				insertarObjeto(par);
			}
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}	
	}
	
	
	public List getTipoNodosCPR(CPRSiplexPro cpr) throws SapeDataException {
		if (debug) logs.debug("getTipoNodosCPR: " + cpr);
		return getTipoNodosCentral(cpr.getCentral());
	}
	
	
	public Map getTelefonosMatriz() throws SapeDataException {
		if (debug) logs.debug("getTelefonosMatriz");
		Exception exception = null;
		Map retorno = new java.util.HashMap(100);
		Session session = null;
		try {
			session = getSession();
			Statement stm = session.connection().createStatement();
			String sql = "select par, telefono from siplexpro_matriz";
			if (debug) logs.debug("Sql: " + sql);
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
					//al key le pongo p+par para que la jsp realmente lo encuentre
				retorno.put("p" + rs.getString("par"), rs.getString("telefono"));
			}
		} catch (SQLException e) {
			logs.error(e);
			exception = e;			
		} catch (HibernateException e) {
			logs.error(e);
			exception = e;
		} finally {
			try {
				session.close();
			} catch (HibernateException e) {
				logs.error(e);
				exception = e;
			}
		}
		if (exception != null) throw new SapeDataException(exception);			
		return retorno;
	}
	
	
	public ParMatrizSiplexPro getParMatriz(int par) throws SapeDataException {
        if (debug) logs.debug("getParMatriz: " + par);
        ParMatrizSiplexPro retorno = null;
        try {
            retorno = (ParMatrizSiplexPro) cargarObjeto(ParMatrizSiplexPro.class, new Integer(par));
        } catch (HibernateException e) {
        	logs.error(e);
            throw new SapeDataException(e);
        }
        return retorno;
	}
    
}
