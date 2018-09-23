package com.osp.sape.data;

import net.sf.hibernate.HibernateException;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.ConfiguracionEWSD;

public class ConfiguracionEWSDDAOImpl extends HibernateObject implements
		ConfiguracionEWSDDAO {

	@Override
	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}
	
    public ConfiguracionEWSD getConfiguracionEWSD(Integer telefono) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getConfiguracionEWSD: "  + telefono);
        ConfiguracionEWSD retorno = null;
        try {
            retorno = (ConfiguracionEWSD) cargarObjeto(ConfiguracionEWSD.class, telefono);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;        
    }
    
	public void actualizarDLU(ConfiguracionEWSD dlu) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("actualizarDLU: " + dlu);
		try {
			actualizarObjeto(dlu);
		} catch (HibernateException e) {
			logs.error(e);
			insertarDLU(dlu);
			//throw new SapeDataException(e);
		}
	}
	
	public void insertarDLU(ConfiguracionEWSD dlu) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("insertarDLU: " + dlu);
		try {
			insertarObjeto(dlu);
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
	}
	
	public void eliminarDLU(Integer telefono) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("eliminarDLU: " + telefono);
		try {
			eliminarObjeto(getConfiguracionEWSD(telefono));
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
	}


}
