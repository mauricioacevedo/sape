/*
 * Created on Jun 13, 2005
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.HoraPruebaCliente;

/**
 * 
 * @author Andres Aristizabal
 */
public interface HoraPruebaClienteDAO {
    public List getHorariosCliente()throws SapeDataException;
    public void actualizarHorarioPruebaClientes(HoraPruebaCliente hpc) throws SapeDataException;
    public List getListasHorarios() throws SapeDataException;
}
