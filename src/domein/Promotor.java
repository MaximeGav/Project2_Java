package domein;

import java.io.Serializable;
import java.util.*;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue("Promotor")
public class Promotor extends Gebruiker implements Serializable {

    //Attributen
    @OneToMany
    private List<Student> studenten;
    
    @ManyToMany
    private List<Beschikbaarheid> beschikbaarheden;
    private int aantalKeerJury;
    private int aantalKeerJuryExtern;
    private int maxAantalStudenten;

    public Promotor() {
        
    }

    //Constructor
    /**
     * Constructor van een promotor
     *
     * @param voornaam van een promotor
     * @param famnaam van een promotor
     * @param email van een promotor
     * @param wachtwoord van een promotor
     * @param aantalJury
     * @param aantalExterneJury
     */
    public Promotor(String voornaam, String famnaam, String email, String wachtwoord, int aantalJury, int aantalExterneJury) {
        super(voornaam, famnaam, email, wachtwoord);
        studenten = new ArrayList<>();
        this.aantalKeerJury = aantalJury;
        this.aantalKeerJuryExtern = aantalExterneJury;
    }
    
    public Promotor(String voornaam, String famnaam, String email, String wachtwoord, int aantalJury, int aantalExterneJury, int maxAantalStudenten) {
        super(voornaam, famnaam, email, wachtwoord);
        studenten = new ArrayList<>();
        this.aantalKeerJury = aantalJury;
        this.aantalKeerJuryExtern = aantalExterneJury;
        this.maxAantalStudenten = maxAantalStudenten;
    }

    /*public Promotor(int id, String voornaam, String famnaam, String email, String wachtwoord, int aantalJury, int aantalExterneJury) {
     super(id, voornaam, famnaam, email, wachtwoord);
     studenten = new ArrayList<>();
     this.aantalKeerJury = aantalJury;
     this.aantalKeerJuryExtern = aantalExterneJury;
     }*/
    //Methoden
    /**
     * Methode om studenten toe te wijzen aan een promotor
     *
     * @param lijst van toe te wijzen studenten
     */
    public void voegStudentenToe(List<Student> lijst) {
        for (Student s : lijst) {
            studenten.add(s);
        }
    }

    public void addStudent(Student s) {
        studenten.add(s);
    }

    public void deleteStudent(Student s) {
        studenten.remove(s);
    }

    /**
     * Methode om alle gegevens van een promotor weer te geven
     *
     * @return Opsomming van de gegevens van de promotor
     */
    @Override
    public String toString() {
        return super.toString();
    }

    //Getters & Setters
    /**
     * De getter van de toegewezen lijst studenten van de promotor
     *
     * @return Lijst van aan de promotor toegewezen studenten
     */
    public List<Student> getStudenten() {
        if (studenten == null) {
            studenten = new ArrayList<>();
        }
        return studenten;
    }

    /**
     * De setter van de toegewezen lijst studenten van de promotor
     *
     * @param studenten lijst van studenten
     */
    public void setStudenten(List<Student> studenten) {
        this.studenten = studenten;
    }

    public List<Beschikbaarheid> getBeschikbaarheden() {
        return beschikbaarheden;
    }

    public void setBeschikbaarheden(List<Beschikbaarheid> beschikbaarheden) {
        this.beschikbaarheden = beschikbaarheden;
    }

    public int getAantalKeerJury() {
        return aantalKeerJury;
    }

    public void setAantalKeerJury(int aantalKeerJury) {
        this.aantalKeerJury = aantalKeerJury;
    }

    public int getAantalKeerJuryExtern() {
        return aantalKeerJuryExtern;
    }

    public void setAantalKeerJuryExtern(int aantalKeerJuryExtern) {
        this.aantalKeerJuryExtern = aantalKeerJuryExtern;
    }
    
    public int getMaxAantalStudenten() {
        return maxAantalStudenten;
    }

    public void setMaxAantalStudenten(int maxAantalStudenten) {
        this.maxAantalStudenten = maxAantalStudenten;
    }

}
