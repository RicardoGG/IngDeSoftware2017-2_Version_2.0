package Mapeo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @version 2.0
 * @author diego
 */
@Entity
@Table(name = "puesto")
public class Puesto implements Serializable {
    // Variable que representa la columna idNombre.
    @Id
    @Column(name = "idNombre")
    private String idNombre;
    
    // Variable que representa la columna ubicacion.
    @Column(name = "Ubicacion")
    private String ubicacion;
    
    // Variable que representa la columna calificacion.
    @Column(name = "Calificacion")
    private int calificacion;
    
    /* Constructor por default. */
    public Puesto() {}
    
    /* Constructor de la clase Puesto, recibe todos los parametros. */
    public Puesto(String idnombre, String ubicacion, int calificacion){
        this.idNombre = idnombre;
        this.ubicacion = ubicacion;
        this.calificacion = calificacion;
    }
    
    /* Constructor de la clase Puesto, recibe solo el nombre del puesto. */
    public Puesto(String idnombre){
        this.idNombre = idnombre;
        this.ubicacion = "Desconocido";
        this.calificacion = 0;
    }

    /**
     * Metodo para obtener el valor de idNombre.
     * @return El nombre del puesto.
     */
    public String getIdNombre() {
        return idNombre;
    }
    
    /**
     * Metodo para asignar un valor a idNombre.
     * @param idNombre El nombre a asignar.
     */
    public void setIdNombre(String idNombre) {
        this.idNombre = idNombre;
    }
    
    /**
     * Metodo para obtener el valor de ubicacion.
     * @return La ubicacion.
     */
    public String getUbicacion() {
        return ubicacion;
    }
    
     /**
     * Metodo para obtener la url de la imagen de la ubicación
     * @return La ubicacion.
     */
    public String getUrl() {
        String s = "https://maps.googleapis.com/maps/api/staticmap?center="+ ubicacion +"&zoom=17&size=600x225&maptype=roadmap&key=AIzaSyC2OSZJg00YFXIfATR7QgXAu0wmA78Q2p0&markers=color:blue%7Clabel:P%7C"+ ubicacion+"&markers=size:tiny";
        return s;
    }
    
    /**
     * Metodo para asignar un valor a ubicacion.
     * @param ubicacion La ubicacion a asignar.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Metodo para obtener el valor de calificacion.
     * @return La calificacion
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * Metodo para asignar un valor a calificacion.
     * @param calificacion La calificacion a asignar.
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
    
    public String getLat(String ubicacion){
        String [] u = ubicacion.split(",");
        String lat = u[0];
        return lat;
    }
    
    public String getLon(String ubicacion){
        String [] u = ubicacion.split(",");
        String lon = u[1];
        return lon;
    }
}
