/*
 * Created on May 5, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.osp.sape.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.TipoPrueba;

public class TipoPruebaDAOImpl extends HibernateObject implements TipoPruebaDAO{ 

	//private org.apache.log4j.Logger logs;
	
	// TODO : esto si keda bien aca?
	private List tipos;
	
    // Annotation para que ignore warning debido a la asignacion de generics no controlada
    //@SuppressWarnings("unchecked")
	public TipoPruebaDAOImpl() {
		super();
		logs=org.apache.log4j.LogManager.getLogger(getClass());
		logs.debug("Iniciando Tipos de Pruebas SIPLEXPRO");
		try {
			// para inicializar la variable tipos.
			getAllTipoPrueba();
		} catch (SapeDataException e) {
			logs.error("ERROR AL INICIALIZAR LOS TIPOS DE PRUEBA SIPLEXPRO: " + e);
		}
	}
	
	protected HibernateConfiguration getHibernateConfiguration() {
		return HibernateConfigurationSape.getInstance();
	}
	
	public void actualizarTipoPrueba(TipoPrueba u) throws SapeDataException {
		if (logs.isDebugEnabled()) logs.debug("actualizarTipoPrueba: " + u);
		try {
			actualizarObjeto(u);
		} catch (HibernateException e) {
			throw new SapeDataException(e);
		}
	}
	
    public TipoPrueba getTipoPrueba(int id) throws SapeDataException {
        if (logs.isDebugEnabled()) logs.debug("getTipoPrueba: " + id);
        TipoPrueba retorno = null;
        try {
            retorno = (TipoPrueba) cargarObjeto(TipoPrueba.class, new Integer(id));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }
    
    
    public List getAllTipoPrueba() throws SapeDataException{
    	if (debug) logs.debug("getAllTipoPrueba");
        Exception e=null;
        List l = null;
        Session session = null;
        try {
            session= getSession();
            l = session.find("from TipoPrueba u where u.disponible='1' order by u.id ASC");
            session.flush();

            } catch(HibernateException e1){
            	e1.printStackTrace();
                e = e1;
            } finally{
                try{
                    if(session !=null) session.close();
                }catch(HibernateException e1){
                	e1.printStackTrace();
                	e=e1;
                }
            }
            if(e != null) throw new SapeDataException(e);
                       
            if(tipos == null){
                int size = l.size();
                String pruebas[] = new String[size];
                String desc[] = new String[size];
                for (int i=0;i < size;i++) {
                	TipoPrueba p = (TipoPrueba) l.get(i);
				
                	pruebas[i] = p.getTipo();
                	desc[i] = p.getDescripcion();
                }
                tipos = new ArrayList();
                tipos.add(0,pruebas);
                tipos.add(1,desc);
            }
            
            return l;
    }
    
    public List getTiposPrueba(){
    	if (debug) logs.debug("getTiposPrueba");
    	return tipos;
    }
}
