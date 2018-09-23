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
import com.osp.sape.maestros.SiplexPROAutotest;

public class SiplexPROAutotestDAOImpl extends HibernateObject implements
		SiplexPROAutotestDAO {

    private org.apache.log4j.Logger logs;
	
	public SiplexPROAutotestDAOImpl() {
		super();
		logs = org.apache.log4j.Logger.getLogger(getClass());
	}
	
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }


//	protected Configuration getConfiguration() throws MappingException {
//        if (configuration == null) {
//            configuration = new Configuration();
//            try {
//                configuration.configure();
//            } catch (HibernateException e) {
//                if(logs.isDebugEnabled())logs.debug(e);
//            }
//        }
//        return configuration;
//	}
	
    public void insertarSiplexPROAutotest(SiplexPROAutotest u) throws SapeDataException {
        try {

            insertarObjeto(u);
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled())logs.debug(e);
            throw new SapeDataException(e);
        }
    }

    public void actualizarSiplexPROAutotest(SiplexPROAutotest u) throws SapeDataException {
        try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled())logs.debug(e);
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarSiplexPROAutotest(SiplexPROAutotest u) throws SapeDataException {
        try {
            eliminarObjeto(u);
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled())logs.debug(e);
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarSiplexPROAutotest(int id) throws SapeDataException {
        try {

            eliminarObjeto(getSiplexPROAutotest(id));
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled())logs.debug(e);
            throw new SapeDataException(e);
        } catch (SapeDataException e) {
        	if(logs.isDebugEnabled())logs.debug(e);
            throw new SapeDataException(e);
        }
    }

    public SiplexPROAutotest getSiplexPROAutotest(int id) throws SapeDataException {
        SiplexPROAutotest retorno = null;
        try {
            retorno = (SiplexPROAutotest) cargarObjeto(SiplexPROAutotest.class, new Integer(id));
        } catch (HibernateException e) {
        	if(logs.isDebugEnabled())logs.debug(e);
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getAllSiplexPROAutotests()throws SapeDataException{
        
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from SiplexPROAutotest u order by u.id");
	        session.flush();
	        session.close();
        }catch(HibernateException e){
        	if(logs.isDebugEnabled())logs.debug(e);
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				if(logs.isDebugEnabled())logs.debug(e);
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return l;
    }
    
    public List getRegistros(String filtro, String valorFiltro,String fIni,String fFin,String regPorPagina,String offset,String orderBy) throws SapeDataException {
    	
        Session session = null;
        List l = new ArrayList(),total = new ArrayList();
        Exception exception = null;
        ResultSet rs = null;
    	Statement st = null;
    	
    	
    	String whereFechas=" a.fecha between '"+fIni+"' and '"+fFin+"' ";
    	String whereFiltro = " and "+filtro+" = '"+valorFiltro+"' ";
    	String whereSites = " and a.idtiponodo = b.idtiponodo ";
        try {
			session = getSession();
			st = session.connection().createStatement();
			Transaction tx = null;
            try {
            	tx = session.beginTransaction();
            	st.execute("drop table autoTestTmp");
            	st.execute("drop table autoTestTmpSite");
            	tx.commit();
            } catch (SQLException e) {
            	tx.rollback();
            }
			
            
            String sql = "select b.site into temp autoTestTmpSite from siplexpro_autotest a, tiponodo b where "+whereFechas+
			whereSites;
            
            st.execute(sql);
            
            sql = "select a.*,b.site into temp autoTestTmp from siplexpro_autotest a, tiponodo b where "+whereFechas+
			(filtro != null && !filtro.equals("")?whereFiltro:"")+whereSites;            
            if(logs.isDebugEnabled()) logs.debug("SQL:\n\n"+sql);
            
            st.execute(sql);
            
			sql = "select count(*) from autoTestTmp";
			if(logs.isDebugEnabled()) logs.debug("SQL:\n\n"+sql);
			rs = st.executeQuery(sql);
			
			if(rs.next()){
				total.add(0,rs.getString(1));
			} else {
				total.add(0,"0");
			}
			
			sql = "select *  from autoTestTmp "+
				" order by "+orderBy+" limit "+regPorPagina+" offset "+offset;
			
			if(logs.isDebugEnabled()) logs.debug("SQL:\n\n"+sql);
			
			rs = st.executeQuery(sql);
			
			int i = 0;
			while(rs.next()) {
				SiplexPROAutotest p = new SiplexPROAutotest();
				p.setId(new Integer(rs.getString(1)));
				p.setIdtiponodo(new Integer(rs.getString(2)));
				p.setFecha(rs.getTimestamp(3));
				
				p.setProcesador(rs.getString(4));
				p.setModulo_pruebas(rs.getString(5));
				p.setEthernet(rs.getString(6));
				p.setModem(rs.getString(7));
				p.setFlash_mem(rs.getString(8));
				p.setTarjeta_expansion(rs.getString(9));
				p.setControlador_troncal(rs.getString(10));
				p.setObservacion(rs.getString(11));
				p.setUltimoUsuario(rs.getString(12));
				p.setSite(rs.getString("site"));
				l.add(i,p);
				i++;
			}
			
			total.add(1,l);
			
			
			
			sql = "select distinct site from autoTestTmpSite";
			
			rs = st.executeQuery(sql);
			
			List ll = new ArrayList();
			
			i = 0;
			while(rs.next()) {
				ll.add(i,rs.getString(1));
				i++;
			}
			total.add(2,ll);
			
		} catch (HibernateException e) {
			if(logs.isDebugEnabled()) logs.debug(e);
			exception = e;
		} catch (SQLException e) {
			if(logs.isDebugEnabled()) logs.debug(e);
			exception = e;
		} finally {
			try{
				if (session != null) session.close();
			}catch(HibernateException e) {
				exception = e;
			}
		}
    	
		if(exception != null) throw new SapeDataException(exception);
		
    	return total;
    }
}