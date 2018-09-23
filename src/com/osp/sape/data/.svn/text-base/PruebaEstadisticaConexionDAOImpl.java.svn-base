package com.osp.sape.data;

import net.sf.hibernate.HibernateException;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.siplexpro.PruebaEstadisticasConexion;

public class PruebaEstadisticaConexionDAOImpl extends HibernateObject implements PruebaEstadisticaConexionDAO {

	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}
	
	
	public PruebaEstadisticasConexion getPruebaEstadistica(int id) throws SapeDataException {
		try {
			return (PruebaEstadisticasConexion) cargarObjeto(PruebaEstadisticasConexion.class, new Integer(id));	
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
	}
	
}
