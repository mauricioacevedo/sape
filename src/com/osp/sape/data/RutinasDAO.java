/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Jun 29, 2006 - 4:09:19 PM
 */

package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.Rutinas;

public interface RutinasDAO {
	
    public void insertarRutina(Rutinas u) throws SapeDataException;
    public void actualizarRutina(Rutinas u) throws SapeDataException;
    public void eliminarRutina(Rutinas u) throws SapeDataException;
    public void eliminarRutina(int id) throws SapeDataException;
    public Rutinas getRutina(int id) throws SapeDataException;
    public List getAllRutinasPorTipo(String tipo) throws SapeDataException;
    public List getElementosActivos(String tipo,String user,boolean activos) throws SapeDataException;
    public List getElemento(String nombreElemento, boolean activo) throws SapeDataException;
    public List<Rutinas> getRutinas(String opcion,String valorOpcion,String fechaIni,String fechaFin,String regPorPagina,String offset,String orderBy) throws SapeDataException;
}
