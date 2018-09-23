/*
 * Created on Apr 14, 2005
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.EventoSape;
import com.osp.sape.maestros.UserSipe;

/**
 * 
 * @author Andres Aristizabal
 */
public interface EventoSapeDAO {
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SapeDataException
	 */
    public EventoSape getEventoSape(long id) throws SapeDataException;
	/**
	 * 
	 * @param fIni
	 * @param fFin
	 * @return
	 * @throws SapeDataException
	 */
    public List getEfectividadPrueba(String fIni, String fFin) throws SapeDataException;
    
    //TODO: BORRAR!!!!!
    public List getEfectividadPrueba2(String fIni, String fFin) throws SapeDataException;
    
    /**
     * Carga todos los eventos de un usuario en una fecha determinada.
     * @param usuario
     * @param fIni
     * @param fFin
     * @return
     * @throws SapeDataException
     */
    public List getEventosUsuario(UserSipe usuario, String fIni, String fFin) throws SapeDataException;
    /**
     * 
     * @param fIni
     * @param fFin
     * @param usuario
     * @return
     * @throws SapeDataException
     */
	public List getPruebasPorUsuarios(String fIni, String fFin, String usuario,String orderBy) throws SapeDataException;
	/**
	 * 
	 * @param usuario
	 * @param fIni
	 * @param fFin
	 * @return
	 * @throws SapeDataException
	 */
	public String getInfoPorUsuario(String usuario, String fIni, String fFin) throws SapeDataException;
	/**
	 * 
	 * @param fIni
	 * @param fFin
	 * @return
	 * @throws SapeDataException
	 */
	public List estadisticoPorHora(String fIni,String fFin) throws SapeDataException;
	
    /**
     * Busca los eventos por central para los indicadores de detalles por central.
     * @param central
     * @param fIni
     * @param fFin
     * @return Retorna una lista de dos posiciones, una con la lista del repote y la otra con la cantidad total de eventos. 
     * @throws SapeDataException
     */
	public List getDetallesCentral(String central, String fIni, String fFin) throws SapeDataException;
	
	/**
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param filtro Null para que no haga el where
	 * @param valorFiltro null para que no haga el where
	 * @param regXPagina
	 * @param offset
	 * @param order
	 * @return
	 * @throws SapeDataException
	 */
	public List getPrimeraPruebaTelefono (String fechaInicial, String fechaFinal, String filtro, String valorFiltro, int regXPagina, int offset, String order) throws SapeDataException;
	/**
	 * 
	 * @param fechaInicial
	 * @param fechaFinal
	 * @return
	 * @throws SapeDataException
	 */
    public String getGraficaPrimeraPrueba(String fechaInicial, String fechaFinal)throws SapeDataException;
    /**
     * 
     * @param fechaIni
     * @param fechaFin
     * @return
     * @throws SapeDataException
     */
    public List getEstadosCentral(String fechaIni, String fechaFin) throws SapeDataException;
    
    /**
     * 
     * @param fechaIni
     * @param fechaFin
     * @return
     * @throws SapeDataException
     */
    public List getEstadisticoTecnologia(String fechaIni,String fechaFin) throws SapeDataException;
    
}
