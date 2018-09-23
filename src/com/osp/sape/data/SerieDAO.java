/*
 * Created on Apr 3, 2005
 */
package com.osp.sape.data;

import java.util.List;
import java.util.Vector;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.Serie;

/**
 * 
 * @author Andres Aristizabal
 */
public interface SerieDAO {
    
    public void eliminarSerie(int id) throws SapeDataException;
    public void insertarSerie(Serie c) throws SapeDataException;
    public Serie getSerie(int id)throws SapeDataException; 
    public List getAllSerie() throws SapeDataException;
    public void actualizarSerie(Serie u) throws SapeDataException;
    public Serie buscarSerie(String telefono) throws SapeDataException;
    public Vector getUmbrales() throws SapeDataException;
    public List getListadoInicialRutinas()throws SapeDataException;
    public List getAllTipoCentrales() throws SapeDataException;
    public List getListasCentralesPorTecnologia() throws SapeDataException;
    public List getCentralesPorTecnologia(String tec)throws SapeDataException;
    //public List buscarSeriesPorCentral(String central) throws SapeDataException;
    public List getAllCentrales()throws SapeDataException;
    public List buscarSeriesPorCentralTecnologia(String central,String tec) throws SapeDataException;
}
