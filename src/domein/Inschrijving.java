package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inschrijvingen")
public class Inschrijving implements Serializable {
    
    //Attributen
    @Id
    private int id;
    
    @ManyToOne
    private Presentatie presentatie;
    private boolean isGoedgekeurd;
    
    @OneToOne(optional = false)
    private Gast gast;

    public Inschrijving() {
    }

    //Constructors
    public Inschrijving(int id, Presentatie presentatie, Gast gast, boolean isGoedgekeurd) {
        this.id = id;
        this.presentatie = presentatie;
        this.gast = gast;
        this.isGoedgekeurd = isGoedgekeurd;
    }
    
    //Methodes
    

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsGoedgekeurd() {
        return isGoedgekeurd;
    }

    public void setIsGoedgekeurd(boolean isGoedgekeurd) {
        this.isGoedgekeurd = isGoedgekeurd;
    }

    public Presentatie getPresentatie() {
        return presentatie;
    }

    public void setPresentatie(Presentatie presentatie) {
        this.presentatie = presentatie;
    }

    public Gast getGast() {
        return gast;
    }

    public void setGast(Gast gast) {
        this.gast = gast;
    }
    
    
}
