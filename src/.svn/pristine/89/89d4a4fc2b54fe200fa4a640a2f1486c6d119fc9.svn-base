package domein;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Campus implements Serializable {

    //Attributen
    @Id
    private String naam;
    
    @OneToMany
    private List<Lokaal> lokalen;

    public Campus() {
    }

    //Constructors
    public Campus(String naam) {
        this.naam = naam;
    }    
    
    //Methodes
    public String toString() {
        return this.naam;
    }
    
    //Getters & Setters
    
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<Lokaal> getLokalen() {
        return lokalen;
    }

    public void setLokalen(List<Lokaal> lokalen) {
        this.lokalen = lokalen;
    }

}
