package Modelo;

import Mapeo.Calificar;
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
public class CalificarDAO {
    // Atributo para iniciar nueva sesion.
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Guarda un comentario en la base de datos.
     * @param c El comentario a agregar.
    */
    public void insert(Calificar c){
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            session.persist(c);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){ tx.rollback(); }
            e.printStackTrace();
        }
        finally { session.close(); }
    }
    
    /**
     * Elimina un comentario de la base de datos.
     * @param c El comentario a eliminar.
    */
    public void delete(Calificar c){
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            session.delete(c);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){ tx.rollback(); }
            e.printStackTrace();
        }
        finally { session.close(); }
    }
    
    /**
     * Actualiza un comentario en la base de datos.
     * @param c El comentario a editar.
     */
    public void update(Calificar c){
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            session.update(c);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){ tx.rollback(); }
            e.printStackTrace();
        }
        finally { session.close(); }
    }
    
   /**
    * Busca un puesto.
    * @param nombre El nombre del puesto.
    * @return Los comentarios del puesto.
    */
    public Calificar buscar(String nombre){
        Calificar cal = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        try{
            
            tx = session.beginTransaction();
            String hql = "from Puesto p where p.puesto.idNombre = :nombrePuesto";
            Query query = session.createQuery(hql);
            query.setParameter("nombrePuesto", nombre);
            cal = (Calificar)query.uniqueResult();
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return cal;
    }
    
    /**
     * Devuelve la lista de comentarios de un puesto.
     * @param nombre El nombre del puesto.
     * @return La lista de comentarios.
     */
    
    public List<Calificar> list_comentarios(String nombre){
        List<Calificar> comentarios = null;

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try{

            tx = session.beginTransaction();
            String hql = "from Calificar c where c.puesto.idNombre = :puesto";
            Query query = session.createQuery(hql);
            query.setParameter("puesto", nombre);
            comentarios = (List<Calificar>)query.list();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return comentarios;
    }
    
    /**
     * Metodo para buscar los comentarios hechos por un usuario dado su email.
     * @param email El email del usuario.
     * @return El comentario.
     */
    public List<Calificar> buscar_comentario(String email){
        List<Calificar> cal = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        try{
            
            tx = session.beginTransaction();
            String hql = "from Calificar c where c.persona.correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", email);
            cal = (List<Calificar>)query.list();
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return cal;
    }
    
    /**
     * Metodo para buscar todos los comentarios hechos.
     * @return Los comentarios.
     */
    public List<Calificar> comentarios(){
        List<Calificar> comentarios = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        try{
            
            tx = session.beginTransaction();
            String hql = "from Calificar";
            Query query = session.createQuery(hql);
            comentarios = (List<Calificar>)query.list();
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return comentarios;
    }
    
    /**
     * Metodo para buscar un comentario hecho por un usuario dado su email y un puesto.
     * @param email El email del usuario.
     * @param puesto El nombre del puesto.
     * @return El comentario.
     */
    public Calificar buscar_comentario2(String email, String puesto){
        Calificar cal = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        try{
            
            tx = session.beginTransaction();
            String hql = "from Calificar c where c.persona.correo = :correo and c.puesto.idNombre = :puesto";
            Query query = session.createQuery(hql);
            query.setParameter("correo", email);
            query.setParameter("puesto", puesto);
            cal = (Calificar)query.uniqueResult();
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return cal;
    }
}
