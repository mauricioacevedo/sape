/*
 * Created on Apr 18, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.osp.sape.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.PruebaAtu;

public class PruebaAtuDAOImpl extends HibernateObject implements PruebaAtuDAO{
    private org.apache.log4j.Logger logs;
    
    public PruebaAtuDAOImpl() {
        logs = org.apache.log4j.Logger.getLogger(getClass());
    }

	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}

    public PruebaAtu getPruebaAtu (int id) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("getPruebaAtu: " + id);
    	Session session = null;
    	Exception exception = null;
    	Statement stm = null;
    	PruebaAtu retorno = null;
    	try {
			session = getSession();
			stm = session.connection().createStatement();
			String sql = "select * from siplexpro_atu where id = " + id;
			ResultSet rs = stm.executeQuery(sql);
			if (logs.isDebugEnabled()) logs.debug("Sql: " + sql);
			if (rs.next()) {
				retorno = new PruebaAtu();
				retorno.setId(id);
				retorno.setMaxdsbr(rs.getString("maxdsbr"));
				retorno.setMaxusbr(rs.getString("maxusbr"));
				retorno.setIntdsbr(rs.getString("intdsbr"));
				retorno.setIntusbr(rs.getString("intusbr"));
				retorno.setFdsbr(rs.getString("fdsbr"));
				retorno.setFusbr(rs.getString("fusbr"));
				retorno.setCapds(rs.getString("capds"));
				retorno.setCapus(rs.getString("capus"));
				retorno.setSnrmds(rs.getString("snrmds"));
				retorno.setSnrmus(rs.getString("snrmus"));
				retorno.setPwrds(rs.getString("pwrds"));
				retorno.setPwrus(rs.getString("pwrus"));
				retorno.setAttnds(rs.getString("attnds"));
				retorno.setAttnus(rs.getString("attnus"));
				retorno.setOpmode(rs.getString("opmode"));
				retorno.setState(rs.getString("state"));
				retorno.setCodv(rs.getString("codv"));
				retorno.setEstado(rs.getString("estado"));
			}
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
    
}
