/*
 * Created on Apr 2, 2005
 */
package com.osp.sape.data;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.CPRSiplexPro;
import com.osp.sape.maestros.ConfiguracionEWSD;
import com.osp.sape.maestros.SiplexproLI;
import com.osp.sape.maestros.TipoNodo;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.maestros.siplexpro.ParMatrizSiplexPro;

import java.util.List;
import java.util.Map;

/** * @author Andres Aristizabal */
public interface TipoNodoDAO {

	/**
	 * 
	 * @param id
	 * @throws SapeDataException
	 */
    public void eliminarTipoNodo(int id) throws SapeDataException;
    
    /**
     * 
     * @param c
     * @throws SapeDataException
     */
    public void insertarTipoNodo(TipoNodo c) throws SapeDataException;
    
    /**
     * 
     * @param id
     * @return
     * @throws SapeDataException
     */
    public TipoNodo getTipoNodo(int id)throws SapeDataException;
    
    /**
     * 
     * @return
     * @throws SapeDataException
     */
    public List getAllTipoNodo() throws SapeDataException;
    /**
     * 
     * @param u
     * @throws SapeDataException
     */
    public void actualizarTipoNodo(TipoNodo u) throws SapeDataException;
    
    /**
     * Metodo que retorna una lista de objetos TipoNodo, estos objetos solo tienen
     * 2 valores validos: el Site( o nombre ) y el codigo.
     * @return
     * @throws SapeDataException
     */
    public List getListaCabezasId()throws SapeDataException;
    
    /**
     * Retorna toda la lista de los telefonos asociados a una Cabeza.
     * @param cabeza
     * @return
     * @throws SapeDataException
     * @see com.osp.sape.maestros.CPRSiplexPro
     */
    public List getCprTipoNodo(TipoNodo cabeza) throws SapeDataException;
    
    /**
     * Retorna la lista de los cprs asociados a una cabeza, con el total de telefonos que manejan
     * @param cabeza
     * @return
     * @throws SapeDataException
     * @see com.osp.sape.maestros.CPRSiplexPro
     */
    public List getCprTipoNodoResumido(TipoNodo cabeza) throws SapeDataException;
    /**
     * 
     * @param telefono
     * @return
     * @throws SapeDataException
     */
	public CPRSiplexPro getCPR(long telefono) throws SapeDataException;
	/**
	 * 
	 * @param cpr
	 * @throws SapeDataException
	 */
	public void actualizarCPR(CPRSiplexPro cpr) throws SapeDataException;
	
	/**
	 * Busca las cabezas que puedan estar asociadas a un CPR, por ejemplo si un CPR pertenece
	 * a la central NUT2, buscar todos los tipos de nodos que en su nombre este NUT2 (like '%NUT2%')
	 * @param cpr
	 * @return
	 */
	public List getTipoNodosCPR (CPRSiplexPro cpr) throws SapeDataException;
	/**
	 * 
	 * @param central
	 * @return
	 * @throws SapeDataException
	 */
	public List getTipoNodosCentral(String central) throws SapeDataException;
    /**
     * 
     * @param tecnologia
     * @return
     * @throws SapeDataException
     */
	public List getTipoNodosPorTecnologia(String tecnologia) throws SapeDataException;
	
	/**
	 * Metodo para cambiar el metodo de conexion a las cabezas SiplexPRO (modem o red corporativa).
	 * Este metodo cambia la informacion los campos cabeza y esclavo!!!
	 * Recibe la lista de tipos de nodo y el usuario que solicita el cambio.
	 * 
	 * @param tipoNodos
	 * @param user
	 * @throws SapeDataException
	 */
    public void cambiarMetodoConexionSiplexPRO(List tipoNodos, UserSipe user) throws SapeDataException;
    
    public Map getTelefonosMatriz() throws SapeDataException;
    
    public ParMatrizSiplexPro getParMatriz(int par) throws SapeDataException;
    
    public void guardarPar(ParMatrizSiplexPro par) throws SapeDataException;
    
	public ConfiguracionEWSD getDLU(long telefono) throws SapeDataException;
	
	public SiplexproLI getLI(long telefono) throws SapeDataException;
	
    public List getLIsTipoNodo(TipoNodo cabeza) throws SapeDataException;
    
    public List getDLUsTipoNodo(TipoNodo cabeza) throws SapeDataException;
    
    public List getDLUsTipoNodoResumido(TipoNodo cabeza) throws SapeDataException;
    
	public List getTipoNodosPorTecnologia(int tipoCabeza,String estado) throws SapeDataException;
}
