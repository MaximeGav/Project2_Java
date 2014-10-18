package domein;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Gast")
public class Gast extends Gebruiker implements Serializable {

        
    //Attributen
    
    //Constructor
    public Gast() {
        
    }
    
    //Constructors
    public Gast(String voornaam, String famnaam, String email, String wachtwoord) {
        super(voornaam, famnaam, email, wachtwoord);
    }
    
    public Gast(String voornaam, String famnaam, String email) {
        super(voornaam, famnaam, email, "nopass");
    }
    
    //Methodes
    
    
    //Getters & Setters
    
    
}
