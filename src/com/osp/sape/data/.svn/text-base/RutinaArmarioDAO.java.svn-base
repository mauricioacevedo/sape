/*
 * Created on Jun 16, 2005
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.RutinaArmario;

/**
 * 
 * @author Andres Aristizabal
 */
public interface RutinaArmarioDAO {
    public List getAllRutinaArmario()throws SapeDataException;
    public List getTelefonosPorArmario(String armario, String estado)throws SapeDataException;
    public void eliminarRutinaArmario(int tel) throws SapeDataException;
    public void insertarRutinaArmario(RutinaArmario c) throws SapeDataException;
    public RutinaArmario getRutinaArmario(int tel) throws SapeDataException;
    public void actualizarRutinaArmario(RutinaArmario u)throws SapeDataException;
    public boolean eliminarAllArmario(String armario)throws SapeDataException;
    public List getRutinaArmario(long telefono)throws SapeDataException;
}
