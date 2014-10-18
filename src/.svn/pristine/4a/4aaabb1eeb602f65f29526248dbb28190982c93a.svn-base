package domein;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author RootSoft
 */
@Entity
@Table(name = "gebruiker")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "Type", discriminatorType = DiscriminatorType.STRING)
public abstract class Gebruiker implements Serializable {

    //Attributen
    @Id
    private String email;
    
    private String voornaam, familienaam, wachtwoord, username;
    

    public Gebruiker() {
        
    }

    //Constructor  
    
    /**
    * Constructor van een gebruiker
    * @param voornaam van een gebruiker
    * @param familienaam van een gebruiker
    * @param email van een gebruiker
    * @param wachtwoord van een gebruiker
    */
    public Gebruiker(String voornaam, String familienaam, String email, String wachtwoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
    }
    
    /**
    * Constructor van een gebruiker
    * @param voornaam van een gebruiker
    * @param familienaam van een gebruiker
    * @param email van een gebruiker
    * @param wachtwoord van een gebruiker
     * @param username
    */
    public Gebruiker(String voornaam, String familienaam, String email, String wachtwoord, String username) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.username = username;
    }

    

    //Methoden
    /**
     * Methode om alle gegevens van een gebruiker weer te geven
     * @return Opsomming van de gegevens van de gebruiker
     */
    @Override
    public String toString() {
        return voornaam + " " + familienaam;
    }
          
    //Getters & Setters

    /**
     * De getter van de voornaam van de gebruiker
     * @return De voornaam van de gebruiker
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * De setter van de voornaam van de gebruiker
     * @param voornaam 
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * De getter van de familienaam van de gebruiker
     * @return De familienaam van de gebruiker
     */
    public String getFamilienaam() {
        return familienaam;
    }

    /**
     * De setter van de familienaam van de gebruiker
     * @param famnaam 
     */
    public void setFamilienaam(String famnaam) {
        this.familienaam = famnaam;
    }

    /**
     * De getter van het emailadres van de gebruiker
     * @return Het emailadres van de gebruiker
     */
    public String getEmail() {
        return email;
    }

    /**
     * De setter van het emailadres van de gebruiker
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * De getter van het wachtwoord van de gebruiker
     * @return Het wachtwoord van de gebruiker
     */
    public String getWachtwoord() {
        return wachtwoord;
    }

    /**
     * De setter van het wachtwoord van de gebruiker
     * @param wachtwoord 
     */
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
    
    /**
     * De getter van de username van de gebruiker
     * @return De username van de gebruiker
     */
    public String getUsername() {
        return username;
    }

    /**
     * De setter van de username van de gebruiker
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

}