package com.osp.sape.data;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.SiplexproLI;

public interface SiplexproLIDAO {
	public SiplexproLI getSiplexproLI(Long telefono) throws SapeDataException;
	public void actualizarLI(SiplexproLI li) throws SapeDataException;
	public void insertarLI(SiplexproLI li) throws SapeDataException;
	public void eliminarLI(Long telefono) throws SapeDataException;
}
