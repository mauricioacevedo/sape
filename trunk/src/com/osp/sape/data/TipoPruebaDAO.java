/*
 * Created on May 5, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.osp.sape.data;

import java.util.List;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.TipoPrueba;

public interface TipoPruebaDAO {

    public List getAllTipoPrueba() throws SapeDataException;
    public TipoPrueba getTipoPrueba(int id) throws SapeDataException;
    public List getTiposPrueba();
	public void actualizarTipoPrueba(TipoPrueba u) throws SapeDataException;
}
