package Mapeo;

import java.io.Serializable;
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
@Table(name = "vender")
public class Vender implements Serializable {
    // Variable que representa la columna id.
    @Id
    @JoinColumn(name = "id")
    private int id;
   
    // Variable que representa la columna alimentos.
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @JoinColumn(name = "idAlimento_fk")
    private Alimentos alimentos;
    
    // Variable que representa la columna puesto.
    @ManyToOne
    @JoinColumn(name = "idNombre_fk")
    private Puesto puesto;
    
    /* Constructor por default. */
    public Vender() {}
    
    /* Constructor de la clase Vender. */
    public Vender(int id, Alimentos alimentos, Puesto puesto){
        this.id = id;
        this.alimentos = alimentos;
        this.puesto = puesto;
    }
    
    /**
     * Metodo para obtener el valor de id.
     * @return El identificador del alimento.
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo para asignar un valor a id.
     * @param id El identificador a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Metodo para obtener el valor del objeto alimentos.
     * @return El alimento que venden
     */
    public Alimentos getAlimentos() {
        return alimentos;
    }
    
    /**
     * Metodo para asignar un objeto a alimentos.
     * @param alimentos El alimento a asignar.
     */
    public void setAlimentos(Alimentos alimentos) {
        this.alimentos = alimentos;
    }

    /**
     * Metodo para obtener el valor del objeto puesto.
     * @return El puesto que lo vende
     */
    public Puesto getPuesto() {
        return puesto;
    }
    
    /**
     * Metodo para asignar un objeto a puesto.
     * @param puesto El puesto a asignar.
     */
    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
}
