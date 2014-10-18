package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="lokaal")
public class Lokaal implements Serializable {

    //Attributen
    
    @Id
    private String naam;
    private int maxAantalPlaatsen;
    
    @ManyToOne
    private Campus campus;

    public Lokaal() {
        
    }
    
    //Constructors
    public Lokaal(String naam, int maxAantalPlaatsen, Campus campus) {
        this.naam = naam;
        this.maxAantalPlaatsen = maxAantalPlaatsen;
        this.campus = campus;
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

    public int getMaxAantalPlaatsen() {
        return maxAantalPlaatsen;
    }

    public void setMaxAantalPlaatsen(int maxAantalPlaatsen) {
        this.maxAantalPlaatsen = maxAantalPlaatsen;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    } 

}
