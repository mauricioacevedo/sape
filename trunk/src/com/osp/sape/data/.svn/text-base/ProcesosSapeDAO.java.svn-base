package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.ProcesosSape;

public interface ProcesosSapeDAO {

    public void insertarProcesosSape(ProcesosSape u) throws SapeDataException;
    public void actualizarProcesosSape(ProcesosSape u) throws SapeDataException;
    public void eliminarProcesosSape(ProcesosSape U) throws SapeDataException;
    public void eliminarProcesosSape(int id) throws SapeDataException;
    public ProcesosSape getProcesosSape(int id) throws SapeDataException;
    public List getAllProcesosSape(String orderBy)throws SapeDataException;
    public String ejecutarAccion(String id,String accion,String usuario) throws SapeDataException;
    public List getProcesosSape(String orderBy,String filtro, String valorFiltro) throws SapeDataException;
}
