package Modelo;

import Mapeo.Vender;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author diego
 * @version 2.0
 */
public class VenderDAO {
    // Atributo para iniciar nueva sesion.
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Guarda lo que vende un puesto en la base de datos.
     * @param v Los alimentos que vende el puesto.
    */
    public void insert(Vender v){
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            session.persist(v);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){ tx.rollback(); }
            e.printStackTrace();
        }
        finally { session.close(); }
    }
    
    /**
     * Actualiza los alimentos que vende un puesto.
     * @param v Los alimentos a actualizar.
    */
    public void update(Vender v){
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            session.update(v);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){ tx.rollback(); }
            e.printStackTrace();
        }
        finally { session.close(); }
    }
    
    /**
     * Elimina un alimento que venda un puesto.
     * @param v El alimento a eliminar.
    */
    public void delete(Vender v){
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            session.delete(v);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){ tx.rollback(); }
            e.printStackTrace();
        }
        finally { session.close(); }
    }
    
    /**
     * Metodo para buscar un puesto en la base.
     * @param nombre El nombre del puesto.
     * @return Una lista de puestos.
     */
    public List<Vender> buscar(String nombre){
        List<Vender> vender = null;
        
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        try{
            
            tx = session.beginTransaction();
            String hql = "from Vender v where v.puesto.idNombre = :nombrePam";
            Query query = session.createQuery(hql);
            query.setParameter("nombrePam", nombre);
            vender = (List<Vender>)query.list();
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return vender;
    }
}
