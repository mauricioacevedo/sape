package com.osp.sape.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.osp.sape.Exceptions.SapeDataException;
import com.osp.sape.maestros.Armarios;

public class DistanciaArmariosDAOImpl extends HibernateObject implements DistanciaArmariosDAO{

	//TODO poner logs
	public DistanciaArmariosDAOImpl() {}

    protected HibernateConfiguration getHibernateConfiguration() {
    	return HibernateConfigurationSape.getInstance();
    }

	
//	protected Configuration getConfiguration() throws MappingException {
//        if (configuration == null) {
//            configuration = new Configuration();
//            try {
//                configuration.configure();
//            } catch (HibernateException e) {
//                System.out.println("ERROR: " + e);
//            }
//        }
//        return configuration;
//	}
	
    public List getAllDistanciaArmario() throws SapeDataException {
        
        Session session= null;
        Exception exception = null;
        List l = new ArrayList();
        
    	try{
            session= getSession();
            l = session.find("from Armarios p order by p.armario");
            session.flush();
            session.close();
            
            }catch(HibernateException e){
                exception = e;
            }finally{
            	try{
            		if(session != null)session.close();
            	}catch(HibernateException e){
            		exception = e;
            	}
            }
            if(exception != null)throw new SapeDataException(exception);
            
            return l;
    }

    public Armarios getDistanciaArmario(String id) throws SapeDataException {
        Armarios retorno = null;
        try {
            retorno = (Armarios) cargarObjeto(Armarios.class, id);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }
        return retorno;
    }

    public List getDistanciaArmarioLike(String id) throws SapeDataException {
        Session session= null;
        Exception exception = null;
        List l = new ArrayList();
        
    	try{
            session= getSession();
            
            l = session.find("from Armarios p where p.armario like '"+id+"%' order by p.armario");
            
            
            session.flush();
            session.close();
            
            } catch(HibernateException e) {
                exception = e;
            } finally {
            	try{
            		if(session != null)session.close();
            	}catch(HibernateException e){
            		exception = e;
            	}
            }
            if(exception != null)throw new SapeDataException(exception);
            
            return l;

    }
    
    public void eliminarDistanciaArmario(String armario) throws SapeDataException {
        // TODO falta control de log
        try {
              eliminarObjeto(getDistanciaArmario(armario));
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }

    }
    
    public void insertarDistanciaArmario(Armarios c) throws SapeDataException {
        // TODO pendiente generar log
        try {
            insertarObjeto(c);
        } catch (HibernateException e) {
            throw new SapeDataException(e);
        }        

    }
    
    public List getDistanciaArmario(String orderBy,String regXpag,String offset) throws SapeDataException{
        Session session= null;
        Exception exception = null;
        List l = null, total = new ArrayList();
        
    	try{
            session= getSession();

            l = session.find("select count(*) from Armarios");
                        
            total.add(0,l.get(0).toString());

            l = null;
            
            l = session.find("from Armarios p order by p."+orderBy+" limit "+regXpag+" offset "+offset);
            
            total.add(1,l);
            session.flush();
            session.close();
            
            }catch(HibernateException e){
                exception = e;
            } finally{
            	try{
            		if(session != null)session.close();
            	}catch(HibernateException e){
            		exception = e;
            	}
            }
            if(exception != null)throw new SapeDataException(exception);
            
            return total;
    	
    }
    
    //NO BORRAR, puede servir despues para cuando halla ke actualizar armarios.
    public void inserts(){
		
    	Session session = null;
    	Transaction tx=null;
    	try {
			RandomAccessFile ram = new RandomAccessFile("/root/armariosNuevos.txt","rw");
			int i = 0;
			
			session=getSession();
			tx=session.beginTransaction();
			while(true){
				i++;
				String line=ram.readLine();
				Statement st = session.connection().createStatement();
				if(line == null)
					break;
				
				StringTokenizer stt = new StringTokenizer(line,";");
				
				if(stt.countTokens() != 2){
					System.out.println("OUCH!!");
					continue;
				}
				
				String armario=stt.nextToken();
				String distancia=String.valueOf(Double.parseDouble(stt.nextToken()));
				
				armario=(armario.length()==5?armario:"0"+armario);
				
				//1. debo preguntar si el armario existe:
				
				String sql = "select * from armarios where armario = '"+armario+"'";
				ResultSet rs=st.executeQuery(sql);
				
				if(rs.next()){//El registro existe, actualizacion!
					sql = "update armarios set distancia='"+distancia+"' where armario = '"+armario+"'";
					int rta=st.executeUpdate(sql);
					logs.debug("armario "+armario+" registros actualizados: "+rta);
				}else{//el registro no existe, insert!!!!!
					sql = "INSERT INTO armarios VALUES ('"+armario+"',"+"'"+distancia+"')";
					st.execute(sql);
					logs.debug("armario "+armario+" Insertado!!!!");
				}
			}
			tx.commit();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (HibernateException e1) {
				e1.printStackTrace();
			}
		}finally{
			if (session!=null)
				try {
					session.close();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
		} 
    }
    
    public static void main(String argv[]){
    	DistanciaArmariosDAOImpl d = new DistanciaArmariosDAOImpl();
    	d.inserts();
    }

}
