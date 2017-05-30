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
@Table(name = "alimentos")
public class Alimentos implements Serializable {
    // Variable que representa la columna idAlimento.
    @Id
    @Column(name = "idAlimento")
    private int idAlimento;
    
    // Variable que representa la columna nombreAlim.
    @Column(name = "nombreAlim")
    private String nombreAlim;

    /**
     * Metodo para obtener el valor de idAlimento.
     * @return El valor de idAlimento.
     */
    public int getIdAlimento() {
        return idAlimento;
    }

    /**
     * Asigna un valor a la variable idAlimento.
     * @param idAlimento nuevo valor para la variable.
     */
    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }
    
    /**
     * Metodo para obtener el valor de nombreAlim.
     * @return El valor de nombreAlim.
     */
    public String getNombreAlim() {
        return nombreAlim;
    }
    
    /**
     * Asigna un valor a la variable nombreAlim.
     * @param nombreAlim nuevo valor para la variable.
     */
    public void setNombreAlim(String nombreAlim) {
        this.nombreAlim = nombreAlim;
    }
}
