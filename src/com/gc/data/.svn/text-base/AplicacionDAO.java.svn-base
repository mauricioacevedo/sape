/*
 * Created on 13-ene-2004
 */
package com.gc.data;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.auditoria.Visita;

/**
 * @author usuario
 */
public interface AplicacionDAO {

	
	public enum TipoRastros { LOGIN_FALLIDO, NUEVO, ELIMINACION, ACTUALIZACION, CONSULTA };
	
	/**
	 * Da la lista de paginas mas visitadas en el sitio. 
	 * Como pueden ser muchas, se le debe dar la cantidad a mostrar.
	 * 
	 * @param cantidad. la cantidad de registros a recuperar 
	 * @return un HasTable con las parejas pagina, visitas.  
	 */
	public Hashtable getEstadisticoPaginas(int cantidad);
	public Hashtable getEstadisticoVisitasHoras();
	public Hashtable getEstadisticoVisitasDias();
	public Hashtable getEstadisticoVisitasMeses();
	public Hashtable getEstadisticoVisitasAnos();
	public Hashtable getEstadisticoBrowsers();
	public Hashtable getEstadisticoSO();
	public void registrarVisita(Visita v);
	public void registrarSalida (Visita v);
	public void registrarActualizacion(Date fecha, String usuario, Object viejo, Object nuevo);
	public void registrarEliminacion (Date fecha, String usuario, Object eliminado);
	public void registrarInsersion (Date fecha, String usuario, Object nuevo);
	public void registrarLoginFallido(Date fecha, String usuario, String clave, String ip);
	public List buscarVisita(String fIni, String fFin,String user) throws SapeDataException;
	public List buscarRastro(String fIni, String fFin,String user,String tipo)  throws SapeDataException;
	
	/** Elimina rastros segun el rango de fechas y devuelve la cantidad de registros que elimino */
	public int eliminarRastros (String desdeFecha, String hastaFecha) throws SapeDataException;
	
	/** Elimina visitas segun el rango de fechas y devuelve la cantidad de registros que elimino */
	public int eliminarVisitas (String desdeFecha, String hastaFecha) throws SapeDataException;
	
	/** 
	 * Inserta los registros que estan Pendientes en rastrossape.
	 *
	 */
	public void finalizarPendientes();
	
}	
