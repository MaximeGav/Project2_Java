package domein;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beschikbaarheden")
public class Beschikbaarheid implements Serializable {
    
    //Attributen
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int beschikbaarheidsId;
    
    
    private int dag, tijdsvak;
    
    @OneToOne
    private Promotor promotor;

    public Beschikbaarheid() {
    }
    
    //Constructors

    public Beschikbaarheid(int dag, int tijdsvak) {
        this.dag = dag;
        this.tijdsvak = tijdsvak;
    }
    
    
    //Methodes
    
    
    //Getters & Setters
    public int getDag() {
        return dag;
    }

    public void setDag(int dag) {
        this.dag = dag;
    }

    public int getTijdsvak() {
        return tijdsvak;
    }

    public void setTijdsvak(int tijdsvak) {
        this.tijdsvak = tijdsvak;
    }

    public int getBeschikbaarheidsId() {
        return beschikbaarheidsId;
    }

    public void setBeschikbaarheidsId(int beschikbaarheidsId) {
        this.beschikbaarheidsId = beschikbaarheidsId;
    }
    
    
}
