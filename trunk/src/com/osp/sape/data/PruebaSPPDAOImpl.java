package com.osp.sape.data;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.rutinas.PruebaSPP;


public class PruebaSPPDAOImpl extends HibernateObject implements PruebaSPPDAO {

    private org.apache.log4j.Logger logs;
	
	public PruebaSPPDAOImpl() {
		super();
		logs = org.apache.log4j.Logger.getLogger(getClass());
	}

    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

	
	public void insertarPruebaSPP(PruebaSPP u) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("insertarPruebaSPP: " + u);
        try {
            insertarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public void actualizarPruebaSPP(PruebaSPP u) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("actualizarPruebaSPP: " + u);
        try {
    
            actualizarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarPruebaSPP(PruebaSPP U) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("eliminarPruebaSPP: " + U);
    	try {
            eliminarObjeto(U);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    /**
     * Este metodo actualiza los registros de 3 tablas:
     * - pruebaspp
     * - pruebaprogramada
     * - rutina_cable o rutina_armario o rutina_cliente
     * Recibe como parametro la transaccion que se desea eliminar y el tipo de prueba.
     * Del tipo de prueba se verifica si la transaccion pertenece a un cable, armario o cliente
     * y con esto actualizamos la informacion de este en su respectiva tabla(rutina_cable,rutina_armario,rutina_cliente)
     */

    public void eliminarPruebaSPPPorTransaccion(int idPruebaProgramada,String tipoPrueba) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("eliminarPruebaSPPPorTransaccion: " + idPruebaProgramada + ", " + tipoPrueba);
    	Session session = null;
    	Exception exception = null;
    	Transaction tx = null;
    	
		try {
			session = getSession();
			tx = session.beginTransaction();
			
			Statement st = session.connection().createStatement();
			
			String tipoRutina = null;
			
			if(tipoPrueba.startsWith("CA"))
				tipoRutina = "cable";
			else if (tipoPrueba.startsWith("CL"))
				tipoRutina = "cliente";
			else if(tipoPrueba.startsWith("AR"))
				tipoRutina = "armario";
			else
				throw new SapeDataException("No existe el tipo de prueba '"+tipoPrueba+"'");
			
			int rows = -1;
			
			System.out.println("SQL1: \n\n"+"update rutina_"+tipoRutina+" set estatus = 'II' where telefono IN (select telefono from pruebaspp where idpruebaprogramada = "+idPruebaProgramada+")\n\n");
			rows=st.executeUpdate("update rutina_"+tipoRutina+" set estatus = 'II' where telefono IN (select telefono from pruebaspp where idpruebaprogramada = "+idPruebaProgramada+")");
			System.out.println("Elimino "+rows+" registros");
			System.out.println("SQL2:\n\n"+"DELETE FROM pruebaprogramada WHERE transaccion ="+idPruebaProgramada+"\n\n");
			rows = st.executeUpdate("DELETE FROM pruebaprogramada WHERE transaccion ="+idPruebaProgramada);
			System.out.println("Elimino "+rows+" registros");
			System.out.println("SQL3:\n\n"+"DELETE FROM pruebaspp WHERE idpruebaprogramada ="+idPruebaProgramada+"\n\n");
			rows=st.executeUpdate("DELETE FROM pruebaspp WHERE idpruebaprogramada ="+idPruebaProgramada);
			System.out.println("Elimino "+rows+" registros");
			
			
			tx.commit();
		} catch (HibernateException e) {
		    exception = e;
		    if (tx != null) {
		        try {
		            tx.rollback();
		        } catch (HibernateException e1) {
		            exception = e1;
		        }
			}
			
		} catch (SQLException e) {
			exception =e;
			
		    if (tx != null) {
		        try {
		            tx.rollback();
		        } catch (HibernateException e1) {
		            exception = e1;
		        }
			}
		} finally {
			if(session != null)
				try {
					session.close();
				} catch (HibernateException e) {
					exception =e;
				}
		}
		
		if(exception != null) throw new SapeDataException(exception);
		

    }
    
    public void eliminarPruebaSPP(int id) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("eliminarPruebaSPP: " + id);
        try {
            eliminarObjeto(getPruebaSPP(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        } catch (SapeDataException e) {
            throw new SapeDataException(e);
        }
    }

    public PruebaSPP getPruebaSPP(int id) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("getPruebaSPP: " + id);
    	PruebaSPP retorno = null;
        try {
            retorno = (PruebaSPP) cargarObjeto(PruebaSPP.class, new Integer(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getAllPruebaSPPs()throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getAllPruebaSPPs");        
        Session session= null;
        List l = new ArrayList();
    	Exception exception = null;
    	try{
	        session= getSession();
	        l = session.find("from PruebaSPP u order by u.transaccion_spp");
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            exception = e;
        }finally{
        	try{
        		if(session != null) session.close();
        	}catch(HibernateException e){
        		exception=e;
        	}
        }
        
        if(exception != null) throw new SapeDataException(exception);
        return l;
    }

//    protected Configuration getConfiguration() throws MappingException {
//        if (configuration == null) {
//            configuration = new Configuration();
//            try {
//                configuration.configure();
//            } catch (HibernateException e) {
//                System.out.println("ERROR: " + e);
//            }
//        }
//        return configuration;
//    }


	public List getRegistros(int prueba) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("getRegistros: " + prueba);
		Session session = null;
		Exception exception = null;
		List l = new ArrayList();
		try{
	        session= getSession();
	        l = session.find("from PruebaSPP u where u.idpruebaprogramada = "+prueba+" order by u.transaccion_spp");
	        session.flush();
	        session.close();
        }catch(HibernateException e){
            exception = e;
        }finally{
        	try{
        		if(session != null) session.close();
        	}catch (HibernateException e) {
				exception = e;
			}
        }
        
        if(exception != null) throw new SapeDataException(exception);
        return l;
	}

}
