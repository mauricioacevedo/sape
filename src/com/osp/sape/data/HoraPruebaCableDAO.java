/*
 * Created on Jun 16, 2005
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;

/**
 * 
 * @author Andres Aristizabal y Mauricio Acevedo
 */
public interface HoraPruebaCableDAO {
    public List getHorariosCable(String cable)throws SapeDataException;
    public String actualizarHorarioPruebaCables(String cable,String h19,String h20,String h21,String h22,String h23,String h00,String h01,String h02,String h03,String h04,String h05,String h06)
	throws SapeDataException;
    public List getAllCables() throws SapeDataException;
    public boolean eliminarCable(String cable)throws SapeDataException;
    public boolean existsCable(String cable)throws SapeDataException;
}
