package domein;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
@DiscriminatorValue("BPCoordinator")
public class BPCoordinator extends Gebruiker implements Serializable {

    @OneToOne
    private Planning planning;

    public BPCoordinator() {
    }
    
    //Constructor
    
    /**
     * Constructor van een BPCoördinator
     *
     * @param voornaam van de BPCoördinator
     * @param famnaam van de BPCoördinator
     * @param email van de BPCoördinator
     * @param wachtwoord van de BPCoördinator
     */
    public BPCoordinator(String voornaam, String famnaam, String email, String wachtwoord) {
        super(voornaam, famnaam, email, wachtwoord);
    }

    //Methoden
    public void startEditingPlanning() {
        if (planning == null) {
            planning = new Planning();
        }
        planning.setIsZichtbaar(Boolean.FALSE);
    }

    public void endEditingPlanning() {
        planning.setIsZichtbaar(Boolean.TRUE);
    }
    
    public void maakPlanningZichtbaar(Calendar eindDatum) {
        if (planning != null) {
            planning.maakPlanningZichtbaar(eindDatum);
        }
    }

    public Planning geefPlanning() {
        if (planning == null) {
            return new Planning();
        }
        return planning;
    }

    /**
     * Methode om alle gegevens van een BPCoördinator weer te geven
     *
     * @return Opsomming van de gegevens van de BPCoördinator
     */
    @Override
    public String toString() {
        return super.toString() + ", is een BPCoördinator";
    }

}
