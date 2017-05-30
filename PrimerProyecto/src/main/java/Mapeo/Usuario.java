package Mapeo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author diego
 * @version 2.0
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    // Variable que representa la columna correo_us.
    @Id
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @Column(name="correo_us")
    private String correo_us;
    
    // Variable que representa la columna administrador.
    @Column(name = "administrador")
    private String administrador;
    
    /* Constructor por default. */
    public Usuario() {}
    
    /* Constructor de la clase Usuario. */
    public Usuario(String correo, String administrador){
        this.correo_us = correo;
        this.administrador = administrador;
    }
    
    /**
     * Metodo para obtener el valor de correo_us.
     * @return El correo del usuario.
     */
    public String getCorreo_us() {
        return correo_us;
    }
    
    /**
     * Metodo para asignar un valor a correo_us.
     * @param correo_us El correo a asignar.
     */
    public void setCorreo_us(String correo_us) {
        this.correo_us = correo_us;
    }

    /**
     * Metodo para obtener el valor de administrador.
     * @return Nos dice si el usuario es un administrador o no.
     */
    public String getAdministrador() {
        return administrador;
    }

    /**
     * Metodo para asignar un valor a administrador.
     * @param administrador El valor a asignar.
     */
    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
}
