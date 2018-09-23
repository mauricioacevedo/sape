/*
 * Created on Jun 13, 2005
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.RutinaCliente;

/**
 * 
 * @author Andres Aristizabal
 */
public interface RutinaClienteDAO {
    
    public void eliminarRutinaCliente(long tel) throws SapeDataException;
    public void insertarRutinaCliente(RutinaCliente c) throws SapeDataException;
    public RutinaCliente getRutinaCliente(long tel)throws SapeDataException; 
    public List getAllRutinaCliente() throws SapeDataException;
    //public void actualizarRutinaCliente(RutinaCliente u) throws SapeDataException;
    public List getRutinasClientePorEstado(String cliente,String estado) throws SapeDataException;
    public int eliminarRangoRutinaCliente(String inicio, String fin,String cliente) throws SapeDataException;
    public List getRutinasPorCliente(String cliente,long telefono) throws SapeDataException;
    public void eliminarRutinaPorCliente(String cliente,long id) throws SapeDataException;
}
