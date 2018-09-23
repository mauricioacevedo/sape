/*
 * Created on Apr 16, 2005
 */
package com.osp.sape.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.ServicioIndicadoresOSS;

/**
 * 
 * @author Andres Aristizabal
 */
public class ServicioIndicadoresOSSDAOImpl extends HibernateObject 
									implements ServicioIndicadoresOSSDAO {

	private org.apache.log4j.Logger logs;
	
	public ServicioIndicadoresOSSDAOImpl() {
		super();
		logs = org.apache.log4j.Logger.getLogger(getClass());
	}
	
    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationGestor.getInstance();
    }
    
    public void insertarServicioIndicadoresOSS(ServicioIndicadoresOSS u) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("insertarServicioIndicadoresOSS: " + u);
    	try {
            insertarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    public void actualizarServicioIndicadoresOSS(ServicioIndicadoresOSS u) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("actualizarServicioIndicadoresOSS: " + u);
    	try {
            actualizarObjeto(u);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }
    
    
    public void eliminarServicioIndicadoresOSS(ServicioIndicadoresOSS U) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("eliminarServicioIndicadoresOSS: " + U);
    	try {
            eliminarObjeto(U);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
    }

    
    public void eliminarServicioIndicadoresOSS(int id) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("eliminarServicioIndicadoresOSS: " + id);
    	try {
            eliminarObjeto(getServicioIndicadoresOSS(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        } catch (SapeDataException e) {
            throw new SapeDataException(e);
        }
    }

    public ServicioIndicadoresOSS getServicioIndicadoresOSS(int id) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("getServicioIndicadoresOSS: " + id);
        ServicioIndicadoresOSS retorno = null;
        try {
            retorno = (ServicioIndicadoresOSS) cargarObjeto(ServicioIndicadoresOSS.class, new Integer(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getAllServicioIndicadoresOSS()throws SapeDataException{
    	if (logs.isDebugEnabled()) logs.debug("getAllServicioIndicadoresOSS");
    	Session session= null;
        List l = new ArrayList();
    	Exception exception = null;
    	
    	try{
	        session= getSession();
	        l = session.find("from ServicioIndicadoresOSS s order by s.id");
	        session.flush();
        }catch(HibernateException e){
            exception = e;
        }finally {
            try {
                if (session != null) session.close();
            } catch (HibernateException e) {
                exception = e;
            }
        }
        
        if(exception != null) throw new SapeDataException(exception);
        return l;
    }
    
    
    public ServicioIndicadoresOSS getServicio(String cola, String estado) throws SapeDataException {
    	if (logs.isDebugEnabled()) logs.debug("getServicio: " + cola + ", " + estado);
    	List l=null;
        ServicioIndicadoresOSS retorno = null;
        Exception exception = null;
        
        Session session = null;
        try {
	            session= getSession();
	            
	            l = session.find("from ServicioIndicadoresOSS s where s.estadoOSS = '" + estado + "' and s.fechaCarga= (select max(s2.fechaCarga) from ServicioIndicadoresOSS s2 where s2.cola = '" + cola + "' and s2.estadoOSS = '" + estado + "')");
	
//	            if(l == null){
//	                System.out.println("NO ENCONTRO NINGUN REGISTRO PARA LA COLA "+cola+" con estado "+estado+" lista nula");
//	                return null;
//	            }
	            if(l.size()==0) {
//	                System.out.println("NO ENCONTRO NINGUN REGISTRO PARA LA COLA "+cola+" con estado "+estado);
	                session.close();
	                return null;
	            } else {
	                retorno = (ServicioIndicadoresOSS)l.get(0);
	            }
	            
//	            l=null;
//	            l = session.find("select max(s.fechaCarga) from ServicioIndicadoresOSS s");
//	            Date da=(Date)l.get(0);
//	            Date da2=ret.getFechaCarga();
//	            
//	            if(da.getMonth() == da2.getMonth()&&da.getDay() == da2.getDay()){
//	                if(da.getHours()==da2.getHours()){
//	// VALIDO QUE ESTEN EN LA MISMA FECHA Y MISMA HORA!!!!
//	                    int date=da.getMinutes() -da2.getMinutes();
//	                    System.out.println("cola:"+ret.getCola()+" estado:"+estado+" dif="+date);
//	                    // OBTENGO LA DIFERENCIA DE MINUTOS, ESTANDO EN LA MISMA HORA
//	                    if(date < 5){// SI ESA DIFERENCIA ES MENOR A 5 ES UN DATO VALIDO
//	                        return ret;// Y SE PUEDE RETORNAR
//	                    }
//	                    
//	                }
//	            }
//	            System.out.println("NO SE ENTRO AL IF");
//	            // SI NO SE CUMPLE, SE SETEA LA FECHA CON LA MAXIMA Y LA CANTIDAD EN CERO
//	            ret.setCantidad(0);
//	            ret.setFechaCarga(da);
//	            System.out.println("A PUNTO DE CERRAR FLUJO");
//	            //session.flush();
//	            System.out.println("A PUNTO DE CERRAR EL 2DO FLUJO");
	            session.close();
	            //System.out.println(l.get(0).getClass().getName()+" EL STRING "+l.toString());
    	} catch (HibernateException e) {
            exception = e;
    	}finally{
			try {
				if(session != null) session.close();
			} catch (HibernateException e) {
				exception = e;
			}
    	}
    	
    	if(exception != null) throw new SapeDataException(exception);
        return retorno;
    }
    
//    public static void main(String af[]){
//        ServicioIndicadoresOSSDAO ss = DAOFactoryImpl.getInstance().getServicioIndicadoresOSSDAO();
//        try {
//            ss.getServicio("REPLI","PENDI");
//        } catch (SapeDataException e) {
//            e.printStackTrace();
//        }
//    }
}
