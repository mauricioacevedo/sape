/*
 * Created on Mar 16, 2005
 */
package com.osp.sape.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.UserSipe;
import com.osp.sape.utils.LdapHelper;

/** * @author Andres Aristizabal */
public class UsuarioDAOImpl extends HibernateObject implements UsuarioDAO {


	private LdapHelper ldapHelper;

	
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

	
    public void insertarUsuario(UserSipe u) throws SapeDataException {
    	if (debug) logs.debug("insertarUsuario: " + u);
    	try {
            insertarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public void actualizarUsuario(UserSipe u) throws SapeDataException {
    	if (debug) logs.debug("actualizarUsuario: " + u);
        try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarUsuario(UserSipe u) throws SapeDataException {
    	if (debug) logs.debug("eliminarUsuario: " + u);
        try {
            eliminarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarUsuario(int id) throws SapeDataException {
    	if (debug) logs.debug("eliminarUsuario: " + id);
    	try {
            eliminarObjeto(getUsuario(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public UserSipe getUsuario(int id) throws SapeDataException {
    	if (debug) logs.debug("getUsuario: " + id);
        UserSipe retorno = null;
        try {
            retorno = (UserSipe) cargarObjeto(UserSipe.class, new Integer(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getRegistros(String opcion,String valorOpcion,String regPorPagina,String offset,String orderBy ) throws SapeDataException{
        if (debug) logs.debug("getRegistros");
        Session session = null;
        Exception exception = null;
        List l ,total=new ArrayList();
        String whereSql = "",endSql="";
        
        //1. generamos la sentencia where para la busqueda, si los datos estan null decimos
        //	 que haga una busqueda completa
        if(opcion!=null&valorOpcion!=null&&!opcion.equals("")&&!valorOpcion.equals("")){
        	whereSql = " where u."+opcion+" = '"+valorOpcion+"' ";
        }
        //2. Genero el final de la sentencia
        endSql=" order by u."+orderBy+" limit "+regPorPagina+" offset "+offset;
        
        //3. junto todo en una sola sentencia
        String hsql = "from UserSipe u "+whereSql+endSql;
        String countHsql = "select count(u) from UserSipe u "+whereSql;
    	try{
	        session= getSession();
	        if(debug)logs.debug("[HSQL: "+hsql+" ]");
	        l = session.find(hsql);
	        total.add(0,l);
	        l = session.find(countHsql);
	        total.add(1,String.valueOf(l.get(0)));
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return total;

    }
    
    public List getAllUsuarios()throws SapeDataException{
        if (debug) logs.debug("getAllUsuarios");
        Session session = null;
        Exception exception = null;
        List l ;
    	try{
	        session= getSession();
	        l = session.find("from UserSipe u order by u.nick");
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            throw new SapeDataException(e);
        }finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				exception = e;
			}
    	}
        
        if(exception != null) throw new SapeDataException(exception);
        return l;

    }
    
    public UserSipe getUserByNick(String nick)throws SapeDataException {
    	if (debug) logs.debug("getUserByNick: " + nick);
    	UserSipe user = null;
    	Exception exception = null;
    	Session session = null;
    	
    	try{
    		session = getSession();
    		
    		List l = session.find("from UserSipe s where s.nick = '"+nick+"'");
    		
    		if(l != null && l.size() > 0)
    			user = (UserSipe)l.get(0);
    		
    	}catch(HibernateException e){
    		exception =e;
    	}finally {
    		try{
    			if(session != null) session.close();
    		}catch(HibernateException e){
    			exception = e;
    		}
    	}
    	if(exception != null) throw new SapeDataException(exception);
    	
    	return user;
    }
    
    
    
    public UserSipe validarUsuario(String nick,String password,String tipoValidacion,HashMap opcionesValidacion) throws SapeDataException{
    	if (debug) logs.debug("validarUsuario: " + nick+", metodoValidacion: "+tipoValidacion);
    	
    	UserSipe retorno = null;
    	
		nick = nick.replaceAll("'|\\\\'|\\\\", "");
		password = password.replaceAll("'|\\\\'|\\\\", "");
    	
    	if(tipoValidacion == null || tipoValidacion.equals("sape")) {
    			retorno= validarUsuarioSAPE(nick,password);
    	}else if(tipoValidacion.equals("ldap")) {
    		// TODO:pareciera ke cuando no encuentra un usuario lanza una exception.
    		//		se debe controlar eso y en vez de lanzar una exception (Cuando no encuentra un usuario)
    		//		devuelva el atributo user=null
    		retorno= validarUsuarioLDAP(nick, password, tipoValidacion, opcionesValidacion);
    	}
    	/*if(retorno == null){
    		throw new SapeDataException("El . Verifique los parametros.");
    	}*/
    	return retorno;
    }
    
    private UserSipe validarUsuarioLDAP(String nick,String password, String tipoValidacion, HashMap opcionesValidacion)throws SapeDataException{
    
    	if(ldapHelper==null){
    		ldapHelper = new LdapHelper((String)opcionesValidacion.get("ip"),
    				(String)opcionesValidacion.get("usuarioLogin"),
    				(String)opcionesValidacion.get("passwordLogin"));
    	}
    	// esta validacion devuelve null si el usuario no existe. Si existe, devuelve el
    	// nivel del usuario.
    	String rta=ldapHelper.validarUsuario(nick,password);
    	
    	UserSipe u =null;
    	if(rta!=null){
    		
    		String nivel = "1";
    		if(rta.indexOf("ADMINISTRADOR")!=-1){
    			nivel="3";
    		}else if(rta.indexOf("SUPERVISOR")!=-1){
    			nivel="2";
    		}else {
    			nivel="1";
    		}	
    		
    		u=new UserSipe();
            u.setNick(nick);
            u.setPasswd(password);
            u.setNivel(nivel);
            u.setLenguaje("espanol");
            u.setContacto("");
            u.setGrupo(new Integer(2));
            u.setNombre(nick);
            String estado = "S";
            u.setActivo(estado);
            u.setFechaAlta(new Date());
    	}
    	return u;
    }
    
    private UserSipe validarUsuarioSAPE(String nick, String password) throws SapeDataException {
    	
    	UserSipe user = null;
    	Exception exception = null;
    	Session session = null;
    	try{
    		session = getSession();
    		if (nick.length() > 15 || password.length() > 15) {
    			//TODO maldito hacker
    			return null;
    		}
    		
    		List l = session.find("from UserSipe s where s.nick = '" + nick + "' and s.passwd = '" + password + "' and s.activo = 'S'");
    		if (l.size() > 0) {
    			user = (UserSipe)l.get(0);
    		}
    	}catch(HibernateException e){
    		exception = e;
    		logs.error(e);
    	} finally {
    		try{
    			if(session != null) session.close();
    		}catch(HibernateException e){
    			logs.error(e);
    			exception = e;
    		}
    	}
    	if (exception != null){
    		if(exception.getCause() instanceof ObjectNotFoundException){
    			logs.warn("El usuario: "+nick+" no fue encontrado");
    			return null;
    		}else{
    			throw new SapeDataException(exception);
    		}
    	} 
    	return user;
    }

}
