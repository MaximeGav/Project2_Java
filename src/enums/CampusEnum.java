package enums;

/**
 * Een enum klasse voor de verschillende campussen.
 * @author RootSoft
 */
public enum CampusEnum {
    SCHOONMEERSEN ("Schoonmeersen"),
    AALST ("AALST");
    
    private final String naam;
    
    CampusEnum(String naam) {
        this.naam = naam;
    }
    
}
