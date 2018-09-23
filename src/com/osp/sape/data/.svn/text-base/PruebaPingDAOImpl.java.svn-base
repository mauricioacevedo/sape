package com.osp.sape.data;

import net.sf.hibernate.HibernateException;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.siplexpro.PruebaPing;

public class PruebaPingDAOImpl extends HibernateObject implements PruebaPingDAO {

	
	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}
	
	
	public PruebaPing getPruebaPing(int id) throws SapeDataException {
		if (debug) logs.debug("getPruebaPing: "  + id);
		try {
			return (PruebaPing) cargarObjeto(PruebaPing.class, new Integer(id));
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
	}
	
}
