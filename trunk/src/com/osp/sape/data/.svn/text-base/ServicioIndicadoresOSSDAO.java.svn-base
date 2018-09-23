/*
 * Created on Apr 16, 2005
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.ServicioIndicadoresOSS;


/**
 * 
 * @author Andres Aristizabal
 */
public interface ServicioIndicadoresOSSDAO {
    
    public void insertarServicioIndicadoresOSS(ServicioIndicadoresOSS u) throws SapeDataException;
    
    public void actualizarServicioIndicadoresOSS(ServicioIndicadoresOSS u) throws SapeDataException;
    
    public void eliminarServicioIndicadoresOSS(ServicioIndicadoresOSS U) throws SapeDataException;

    public void eliminarServicioIndicadoresOSS(int id) throws SapeDataException;

    public ServicioIndicadoresOSS getServicioIndicadoresOSS(int id) throws SapeDataException;
    
    public List getAllServicioIndicadoresOSS()throws SapeDataException;

    /**
     * 
     * @param cola
     * @param estado PENDI o CUMPL
     * @return
     * @throws SapeDataException
     */
    public ServicioIndicadoresOSS getServicio(String cola,String estado) throws SapeDataException;
}
