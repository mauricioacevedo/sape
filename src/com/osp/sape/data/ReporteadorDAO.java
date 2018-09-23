/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en Jul 15, 2006 - 5:53:49 PM
 */

package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.reportes.ReporteadorPlantilla;

public interface ReporteadorDAO {

	public void eliminarReporteadorPlantilla(int id) throws SapeDataException;
	public void eliminarReporteadorPlantilla(ReporteadorPlantilla r) throws SapeDataException;
	public List getAllReporteadorPlantillas() throws SapeDataException;
	public ReporteadorPlantilla getReporteadorPlantilla(int id) throws SapeDataException;
	public void insertarReporteadorPlantilla(ReporteadorPlantilla e) throws SapeDataException;
	public void actualizarReporteadorPlantilla(ReporteadorPlantilla u) throws SapeDataException;
	public List getReportes(String fechaHoy) throws SapeDataException;
    public String[] generarLabels(ReporteadorPlantilla r);
    public String[] generarNombres(ReporteadorPlantilla r,String sufix);
    public String enviarMailReporte(String usuario,String listaCorreos,String subject,String mensaje,String pathReporte);
}
