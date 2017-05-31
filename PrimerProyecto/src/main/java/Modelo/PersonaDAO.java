package Modelo;

import Mapeo.Persona;
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
public class PersonaDAO {
    // Atributo para iniciar nueva sesion.
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Guarda una persona en la base de datos.
     * @param p La persona a agregar.
    */
    public void insert(Persona p){
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            session.persist(p);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){ tx.rollback(); }
            e.printStackTrace();
        }
        finally { session.close(); }
    }
    
    /**
     * Actualiza a una persona en la base de datos.
     * @param p La persona a actualizar.
    */
    public void update(Persona p){
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            session.update(p);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){ tx.rollback(); }
            e.printStackTrace();
        }
        finally { session.close(); }
    }
    
    /**
     * Elimina una persona de la base de datos.
     * @param p La persona a eliminar.
    */
    public void delete(Persona p){
        Session session = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            
            session.delete(p);
            tx.commit();
        }
        catch(Exception e){
            if(tx != null){ tx.rollback(); }
            e.printStackTrace();
        }
        finally { session.close(); }
    }
    
    /**
     * Metodo para buscar una persona por su correo.
     * @param correo El correo de la persona.
     * @return La persona si es que esta registrada en la base.
     */
    public Persona getPersona_correo(String correo) {
        Persona per = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = " FROM Persona WHERE correo = :correoParam";
            Query query = session.createQuery(hql);
            query.setParameter("correoParam", correo);
            per = (Persona)query.uniqueResult();
            tx.commit();
        }
        catch (Exception e) {
           if (tx!=null){
               tx.rollback();
           }
           e.printStackTrace();
        }finally {
           session.close();
        }
        return per;
    }
    
    /**
     * Metodo para buscar a una persona por su correo y contrasenia.
     * @param correo El correo de la persona.
     * @param con La contrasenia de la persona.
     * @return La persona si es que esta registrada en la base.
     */
    public Persona getPersona(String correo, String con) {
        Persona per = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = " FROM Persona WHERE correo = :correoParam";
            Query query = session.createQuery(hql);
            query.setParameter("correoParam", correo);
            per = (Persona)query.uniqueResult();
            tx.commit();
        }
        catch (Exception e) {
           if (tx!=null){ 
               tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
           session.close();
        }
        return per;
    }
    
    /**
     * Metodo para buscar una persona por su correo.
     * @param correo El correo de la persona.
     * @return La persona, si es que esta registrada en la base.
     */
    public Persona usuario_registrado(String correo){
        Persona person = null;
        
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        try{
            
            tx = session.beginTransaction();
            String hql = " from Persona where correo = :correo_persona";
            Query query = session.createQuery(hql);
            query.setParameter("correo_persona", correo);
            person = (Persona)query.uniqueResult();
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return person;
    }
    
    /**
     * Metodo para obtener todas las personas registradas.
     * @return Una lista de Personas.
     */
    public List<Persona> personas(){
        List<Persona> personas = null;
        
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        
        try{
            
            tx = session.beginTransaction();
            String hql = " from Persona";
            Query query = session.createQuery(hql);
            personas = (List<Persona>)query.list();
            tx.commit();
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return personas;
    }
}
