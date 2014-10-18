package resources;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RootSoft
 */
public class DateTimeFactory {
   
    public static String geefDag(int dag) {
        switch (dag) {
            case 1 : return "Maandag";
            case 2 : return "Dinsdag";
            case 3 : return "Woensdag";
            case 4 : return "Donderdag";
            case 5 : return "Vrijdag";
            case 6 : return "Zaterdag";
            case 7 : return "Zondag";
        }
        
        return "Geen dag";
    }
    
    public static String geefTijdsvak(int tijdsvak) {
        List<String> begintijdstippen = new ArrayList<String>();
        int currentHour = 8;
        boolean halfHour=false;
        for(int i=0;i<18;i++) {
            String time, endTime;
            time = (halfHour ? currentHour : currentHour--) + "u" + (halfHour ? "30" : "00");
            begintijdstippen.add(time);
            currentHour++;
            halfHour = !halfHour;
        }
        return begintijdstippen.get(tijdsvak);
        
    }
    
 }


