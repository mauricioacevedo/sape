/*
 * Created on Apr 2, 2005
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.CabezaPrueba;

/** * @author Andres Aristizabal */
public interface CabezaPruebaDAO {

    public void eliminarCabezaPrueba(int id) throws SapeDataException;
    public void insertarCabezaPrueba(CabezaPrueba c) throws SapeDataException;
    public CabezaPrueba getCabezaPrueba(int id)throws SapeDataException; 
    public List getAllCabezaPrueba() throws SapeDataException;
    public void actualizarCabezaPrueba(CabezaPrueba u) throws SapeDataException;
}
