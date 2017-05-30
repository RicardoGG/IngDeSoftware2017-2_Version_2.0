package Mapeo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author diego
 * @version 2.0
 */
@Entity
@Table(name = "calificar")
public class Calificar implements Serializable {
    // Variable que representa la columna Correo_fk.
    @Id
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @JoinColumn(name = "Correo_fk")
    private Persona persona;
    
    // Variable que representa la columna idNombre_fk.
    @Id
    @ManyToOne
    @JoinColumn(name = "idNombre_fk")
    private Puesto puesto;
    
    // Variable que representa la columna Comentario.
    @Column(name = "Comentario")
    private String comentario;
    
    /* Constructor por default. */
    public Calificar(){ }
    
    /* Constructor. */
    public Calificar(Persona persona, Puesto puesto, String comentario){
        this.persona = persona;
        this.puesto = puesto;
        this.comentario = comentario;
    }
    
    /**
     * Metodo para obtener el objeto Persona.
     * @return El objeto Persona.
     */
    public Persona getPersona() {
        return persona;
    }
    
    /**
     * Metodo para asignar una Persona a la variable persona.
     * @param persona El objeto Persona a ser asignado.
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    /**
     * Metodo para obtener el objeto Puesto.
     * @return El objeto Puesto.
     */
    public Puesto getPuesto() {
        return puesto;
    }

    /**
     * Metodo para asignar un Puesto a la variable puesto.
     * @param puesto El objeto Puesto a ser asignado.
     */
    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    
    /**
     * Metodo para obtener el comentario que dio la persona al puesto.
     * @return El comentario.
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Metodo que asigna un valor a comentario.
     * @param comentario el comentario a ser asignado.
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
