package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.PruebaSPP;

public interface PruebaSPPDAO {
	public List getRegistros(int prueba) throws SapeDataException;
	
	
	
	public void insertarPruebaSPP(PruebaSPP u) throws SapeDataException;

    public void actualizarPruebaSPP(PruebaSPP u) throws SapeDataException;
    
    public void eliminarPruebaSPP(PruebaSPP U) throws SapeDataException ;

    public void eliminarPruebaSPP(int id) throws SapeDataException;

    public PruebaSPP getPruebaSPP(int id) throws SapeDataException;
    
    public List getAllPruebaSPPs()throws SapeDataException;
    
    public void eliminarPruebaSPPPorTransaccion(int idPruebaProgramada, String tipoPrueba) throws SapeDataException;
        
}
