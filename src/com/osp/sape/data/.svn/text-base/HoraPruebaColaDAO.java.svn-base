/*
 * Created on Jun 28, 2005
 */
package com.osp.sape.data;

import java.util.Date;
import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;

/**
 * 
 * @author Andres Aristizabal y Mauricio Acevedo
 */
public interface HoraPruebaColaDAO {
    
    public List getListaNombreColas() throws SapeDataException;
    
    /**
     * Recibe como parametro las 24 horas, el estado de los dias de la semana y la cola.
     * este metodo actualiza la informacion de la tabla hrprueba_cola, que es donde se encuentran los horarios
     * y de la tabla diaprueba_cola que es donde se almacena el estado por dia y si la prueba por esta cola esta habilitada o no
     * 
     * @return Retorna un String null si la operacion fue exitosa, de otra manera devuelve una
     * descripcion de la anomalia.
     * @throws SapeDataException
     */
    public String actualizarHorarios(
            String h00,String h01,String h02,String h03,String h04,String h05,String h06,
            String h07,String h08,String h09,String h10,String h11,String h12,String h13,
            String h14,String h15,String h16,String h17,String h18,String h19,String h20,
            String h21,String h22,String h23,String lunes,String martes,String miercoles,
            String jueves,String viernes, String sabado,String domingo,String cola, String usuario, Date fecha)throws SapeDataException;
    
    public void eliminarHorarioCola(String cola) throws SapeDataException;
    public List getListaAllHorarios() throws SapeDataException;
    
    /**
     * Metodo utilizado para actualizar la tabla diaprueba_cola cuando un usuario edita los
     * codigos de falla. De este modo se deja registro de la ultima persona toco la cola.
     * @param usuario
     * @param fecha
     * @throws SapeDataException
     */
    public void actualizarRastroCambio (String cola, String usuario, Date fecha) throws SapeDataException;
}
