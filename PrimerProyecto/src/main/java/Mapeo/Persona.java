
package Mapeo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author diego
 * @version 2.0
 */
@Entity
@Table(name = "persona")
public class Persona implements Serializable {
    // Variable que representa la columna correo.
    @Id
    @Column(name = "Correo")
    private String correo;
    
    // Variable que representa la columna nombre.
    @Column(name = "Nombres")
    private String nombre;
    
    // Variable que representa la columna apPaterno.
    @Column(name = "apPaterno")
    private String apPaterno;
    
    // Variable que representa la columna apMaterno.
    @Column(name = "apMaterno")
    private String apMaterno;
    
    // Variable que representa la columna contrasena.
    @Column(name = "Contrasena")
    private String contrasenia;
    
    /* Constructor por default. */
    public Persona(){}
    
    /* Constructor de la clase Persona */
    public Persona(String nombre, String apPaterno, String apMaterno, String correo, String contrasenia){
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }
    
    /**
     * Metodo que representa a un objeto Persona en forma de cadena.
     * @return La cadena que representa a la persona.
     */
    public String toString(){
        String persona = "nombre: " + nombre + "\n" + "apPaterno: " + apPaterno + "\n" + "apMaterno: " + apMaterno +
                "\n" + "Correo: " + correo + "\n" + "Contrasenia: " + contrasenia;
        return persona;
    }
    
    /**
     * Metodo para obtener el valor de correo.
     * @return El correo.
     */
    public String getCorreo() {
        return correo;
    }
    
    /**
     * Metodo para asignar un valor a correo.
     * @param correo El correo a asignar
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Metodo para obtener el valor de nombre
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Metodo para asignar un valor a nombre.
     * @param nombre El nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para obtener el valor de apPaterno.
     * @return El apellido paterno.
     */
    public String getApPaterno() {
        return apPaterno;
    }
    
    /**
     * Metodo para asignar un valor a apPaterno.
     * @param apPaterno El apellido paterno a asignar.
     */
    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }
    
    /**
     * Metodo para obtener el valor de apMaterno.
     * @return El apellido materno.
     */
    public String getApMaterno() {
        return apMaterno;
    }

    /**
     * Metodo para asignar un valor a apMaterno.
     * @param apMaterno El apellido materno a asignar.
     */
    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    /**
     * Metodo para obtener el valor de contrasenia.
     * @return La contrasenia.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Metodo para asignar un valor a contrasenia.
     * @param contrasenia La contrasenia a asignar.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
