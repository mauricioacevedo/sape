/*
 * Created on Mar 16, 2005
 */
package com.osp.sape.data;



import java.util.List;

import com.osp.sape.maestros.CodigoVer;



import com.osp.sape.Exceptions.SapeDataException;


/** * @author Andres Aristizabal */
public interface CodigosVerDAO {

    void insertarCodigoVer(CodigoVer c) throws SapeDataException;

    void eliminarCodigoVer(CodigoVer c) throws SapeDataException;

    void eliminarCodigoVer(String id) throws SapeDataException;

    public CodigoVer getCodigoVer(String id) throws SapeDataException;
    
    public List getAllCodigosVer() throws SapeDataException;
    
    public List getCodigoVerNoETB() throws SapeDataException;
    
    public void actualizarCodigoVer(CodigoVer codv) throws SapeDataException;
}
