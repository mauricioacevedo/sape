package com.osp.sape.data;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.ConfiguracionEWSD;

public interface ConfiguracionEWSDDAO {
	public ConfiguracionEWSD getConfiguracionEWSD(Integer telefono) throws SapeDataException;
	public void actualizarDLU(ConfiguracionEWSD dlu) throws SapeDataException;
	public void insertarDLU(ConfiguracionEWSD dlu) throws SapeDataException;
	public void eliminarDLU(Integer telefono) throws SapeDataException;
}
