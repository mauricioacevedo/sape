/*
 * Created on Mar 15, 2005
 */
package com.osp.sape.data;



import java.util.HashMap;
import java.util.List;

import com.osp.sape.maestros.UserSipe;


import com.osp.sape.Exceptions.SapeDataException;



/** * @author Andres Aristizabal */
public interface UsuarioDAO {

    public void insertarUsuario(UserSipe u) throws SapeDataException;
    
    public void actualizarUsuario(UserSipe u) throws SapeDataException;
    
    public void eliminarUsuario(UserSipe U) throws SapeDataException;

    public void eliminarUsuario(int id) throws SapeDataException;

    public UserSipe getUsuario(int id) throws SapeDataException;
    
    public List getAllUsuarios()throws SapeDataException;
    
    public UserSipe validarUsuario(String nick,String password,String tipoValidacion,HashMap opcionesValidacion) throws SapeDataException;
    
    public UserSipe getUserByNick(String nick)throws SapeDataException;

    public List getRegistros(String opcion,String valorOpcion,String regPorPagina,String offset,String orderBy ) throws SapeDataException;
}
