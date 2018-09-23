package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.PruebaProgramada;

public interface PruebaProgramadaDAO {

    public void insertarPruebaProgramada(PruebaProgramada u) throws SapeDataException;
    public void actualizarPruebaProgramada(PruebaProgramada u) throws SapeDataException;
    public void eliminarPruebaProgramada(PruebaProgramada U) throws SapeDataException;
    public void eliminarPruebaProgramada(int id) throws SapeDataException;
    public PruebaProgramada getPruebaProgramada(int id) throws SapeDataException;
    public List getAllPruebaProgramadas()throws SapeDataException;
    public List getRegistros(String filtro, String fIni, String fFin,String regPorPagina,String offset,String orderBy) throws SapeDataException;
    public List getPruebasProgramadas(String tipoPrueba, String regPorPagina, String offset) throws SapeDataException;
    public List getListaInicialPruebasProgramadas() throws SapeDataException;
    public List getEstadisticoCodigosVer(String codv,String tipoPrueba, String regPorPagina, String offset) throws SapeDataException;
    public int actualizarEstadosPruebasProgramadas(String prueba, String codv, String idrutina,String cliente) throws SapeDataException;
    public List getRegistrosCalificacion(String filtro, String valorFiltro, String fIni, String fFin, String regPorPagina, String offset, String orderBy) throws SapeDataException;
    public List getEstadisticoMensualCalificacion(String fIni,String fFin) throws SapeDataException;
    public int eliminarEstadisticoCodigosVer(String tipoPrueba) throws SapeDataException;
}
