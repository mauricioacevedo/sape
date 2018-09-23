package com.osp.sape.data;

import net.sf.hibernate.HibernateException;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.SiplexproLI;

public class SiplexproLIDAOImpl extends HibernateObject implements
		SiplexproLIDAO {


	
	public SiplexproLIDAOImpl(){
		super();
	}
	
	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}
	
    public SiplexproLI getSiplexproLI(Long telefono) throws SapeDataException {
        if (debug) logs.debug("getSiplexproLI: "  + telefono);
        SiplexproLI retorno = null;
        try {
            retorno = (SiplexproLI) cargarObjeto(SiplexproLI.class, telefono);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;        
    }
    
	public void actualizarLI(SiplexproLI li) throws SapeDataException {
		if (debug) logs.debug("actualizarLI: " + li);
		try {
			actualizarObjeto(li);
		} catch (HibernateException e) {
			logs.error(e);
			insertarLI(li);
			//throw new SapeDataException(e);
		}
	}
	
	public void insertarLI(SiplexproLI li) throws SapeDataException {
		if (debug) logs.debug("insertarLI: " + li);
		try {
			insertarObjeto(li);
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
	}
	
	public void eliminarLI(Long telefono) throws SapeDataException {
		if (debug) logs.debug("eliminarLI: " + telefono);
		try {
			eliminarObjeto(getSiplexproLI(telefono));
		} catch (HibernateException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
	}


}
