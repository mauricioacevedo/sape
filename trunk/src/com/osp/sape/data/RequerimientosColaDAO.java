/*
 * Created on Jun 2, 2005
 */
package com.osp.sape.data;

import java.util.List;

import net.sf.hibernate.HibernateException;

import com.osp.sape.Exceptions.SapeDataException;

/**
 * 
 * @author Andres Aristizabal
 */
public interface RequerimientosColaDAO {
    //30-09-2005 esto lo comente porque no se estan utilizando. Borrar si no es necesario.
//    /**
//     * Busca todos los requerimientos que hay en la tabla requerimientoscola segun el parametro fecha.
//     * @param fecha
//     * @return
//     * @throws SQLException
//     * @throws HibernateException
//     * @throws SapeDataException
//     */
//    public List getRequerimientosTSTLI(String fecha) throws SapeDataException;
    
//    public List getAllRequerimientosCola() throws SapeDataException ;
    
    /**
     * Metodo que retorna registros de la tabla requerimientoscola restringiendolos por
     *  rango de fecha y por el filtro y por la cola, si el filtro es vacio se hace una seleccion de
     *  registros solo por fecha.
     *  
     *  @return Retorna una lista que contiene:@
     *  	- En la pocision cero, la totalidad de registros para esta consulta.
     *  	- En la pocision uno , la lista de los registros con limit=regPorPagina y offset=offset.
     */
    public List getRegistrosTSTLI(String cola, String filtro, String valorFiltro,String fInicio,String fFin, String regPorPagina, String offset,String orderBy) throws SapeDataException;
    
    /**
     * Grafica utilizada en los indicadores.
     * @param tipo. El tipo de grafica: por armario, por cable, por subzona, etc.
     * @param rango
     * @return
     * @throws SapeDataException
     * @throws HibernateException
     */
    public List getIndicadoresTSTLI(String tipo, int rango) throws SapeDataException, HibernateException;
    
    public String graficasInicialesTSTLI(String cola,int tipo,String filtro, String valorFiltro,String fIni,String fFin, String regPorPagina, String offset,String orderBy) throws SapeDataException;
    
    /**
     * Cambia el estado de un telefono en una cola determinada.
     * @param oid
     * @param estadoNuevo (II,ET,NI-usuario)
     * @return
     * @throws SapeDataException
     */
    public boolean actualizarEstado(long oid, String estadoNuevo) throws SapeDataException;
}
