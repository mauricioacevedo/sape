/*
 * Created on Jun 17, 2005
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.RutinaCable;

/**
 * 
 * @author Andres Aristizabal y Mauricio Acevedo
 */
public interface RutinaCableDAO {
    
        public List getAllRutinaCable()throws SapeDataException;
        public List getTelefonosPorCable(String Cable, String estado)throws SapeDataException;
        public void eliminarRutinaCable(int tel) throws SapeDataException;
        public void insertarRutinaCable(RutinaCable c) throws SapeDataException;
        public RutinaCable getRutinaCable(int tel) throws SapeDataException;
        public void actualizarRutinaCable(RutinaCable u)throws SapeDataException;
        public boolean eliminarAllCable(String cable)throws SapeDataException;
        public List getRutinaCable(String cable) throws SapeDataException;
        public List getRutinaCable(long tel) throws SapeDataException;
}
