package domein;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Voorstel implements Serializable {

    //Attributen
    private String trefwoorden;
    
    @Id
    private String titel;
    private String probleemstelling;
    private String context;
    
    @OneToMany
    private List<Onderzoeksdomein> onderzoeksdomeinen;

    public Voorstel() {
    }

    //Constructor
    public Voorstel(String titel, String trefwoorden, String probleemstelling, String context, List<Onderzoeksdomein> onderzoeksdomeinen) {
        this.titel = titel;
        this.trefwoorden = trefwoorden;
        this.probleemstelling = probleemstelling;
        this.context = context;
        this.onderzoeksdomeinen = onderzoeksdomeinen;
    }

    //Getters & Setters
    public String getTrefwoorden() {
        return trefwoorden;
    }

    public void setTrefwoorden(String trefwoorden) {
        this.trefwoorden = trefwoorden;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getProbleemstelling() {
        return probleemstelling;
    }

    public void setProbleemstelling(String probleemstelling) {
        this.probleemstelling = probleemstelling;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Onderzoeksdomein> getOnderzoeksdomeinen() {
        return onderzoeksdomeinen;
    }

    public void setOnderzoeksdomeinen(List<Onderzoeksdomein> onderzoeksdomeinen) {
        this.onderzoeksdomeinen = onderzoeksdomeinen;
    }

}
