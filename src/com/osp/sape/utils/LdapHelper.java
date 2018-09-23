/*
 * 
 * @author Develop Team XpLoRa
 * Clase generada en May 24, 2006 - 12:20:05 PM
 */

package com.osp.sape.utils;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.osp.sape.Exceptions.SapeDataException;

/**
 * Clase que contiene configuracion especifica a ldap.
 * Este bean esta instanciado en la clase UsuarioDAOImpl y aparte de
 * dar algunos parametros de configuracion tambien proporciona una
 * conexion persistente al servidor ldap.
 * 
 * @author Develop Team XpLoRa
 */
public class LdapHelper {

    private int ldapPort = LDAPConnection.DEFAULT_PORT;
    private int ldapVersion = LDAPConnection.LDAP_V3;
	
    private String userLogin;
    private String passwordLogin;
    private String ipServer;
    
    private org.apache.log4j.Logger logs;
    private boolean debug;
    
    private LDAPConnection connection;
	public LdapHelper(String ip, String usuario,String password) {
		
		logs = org.apache.log4j.Logger.getLogger(getClass());
		debug = logs.isDebugEnabled();
		
		userLogin = usuario;
		passwordLogin = password;
		ipServer=ip;
//		try {
//			doConnect();
//		} catch (LDAPException e) {
//			logs.error(e);
//			throw new SapeDataException(e);
//		}
	}
	
	public String validarUsuario(String nick, String password) throws SapeDataException{
		if (debug) logs.debug("validarUsuario: " + nick);
		try {
			doConnect();
		} catch (LDAPException e) {
			logs.error(e);
			throw new SapeDataException(e);
		}
		
		String objectDN = "cn="+nick+",ou=Empleados,o=etb";
		
		LDAPAttribute attr = new LDAPAttribute("userPassword", password);
		
		String retorno = null;
		
		try {
			if(connection.compare( objectDN, attr )){
				String[] attri = {"groupMembership"};
	            
	            LDAPEntry entry = connection.read( objectDN, attri );
	            
	            retorno = entry.getAttribute("groupMembership").toString();
			} else {
				logs.warn("Password Invalido");
			}
			connection.disconnect();
		} catch (LDAPException e) {
			if (e.getResultCode() != LDAPException.NO_SUCH_OBJECT) {
				logs.error(e);
				throw new SapeDataException("No se puede validar usuario.\n[ERROR]: "+e);
			}
		}
		return retorno;
	}
	
	
	private void doConnect() throws LDAPException {
		if (debug) logs.debug("doConnect");
		if (connection == null) connection = new LDAPConnection();
		if (connection.isConnected())  connection.disconnect();
		connection.connect(ipServer,ldapPort);
		connection.bind( ldapVersion, userLogin, passwordLogin.getBytes());
	}

}
