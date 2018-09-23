/*
 * Created on Apr 5, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.maestros.siplexpro.ConfiguracionADSL;

public interface ConfiguracionADSLDAO {

	public List getConfiguracionADSL(TipoNodo t) throws SapeDataException;
	public ConfiguracionADSL getTipoConfiguracion(String conf);
	public void guardarConfigADSL(List values,String tipoConfig,TipoNodo t) throws SapeDataException;
}
