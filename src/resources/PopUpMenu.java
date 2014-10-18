package resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author RootSoft
 */
public class PopUpMenu extends JPopupMenu {
    
    //Attributes
    private final JMenuItem mnuPlannen;
    private final JMenuItem mnuBewerken;
    private final JMenuItem mnuJuryToevoegen;
    private final JMenuItem mnuBezoekersToevoegen;
    private final JMenuItem mnuVerwijderen;
    
    public PopUpMenu(ActionListener listener){
        
        //Adds the menu items to the popup
        mnuPlannen = new JMenuItem("Plannen");
        mnuPlannen.addActionListener(listener);
        add(mnuPlannen);
        
        mnuBewerken = new JMenuItem("Bewerken");
        mnuBewerken.addActionListener(listener);
        add(mnuBewerken);
        
        mnuVerwijderen = new JMenuItem("Verwijderen");
        mnuVerwijderen.addActionListener(listener);
        add(mnuVerwijderen);
        
        mnuJuryToevoegen = new JMenuItem("Juryleden toevoegen");
        mnuJuryToevoegen.addActionListener(listener);
        add(mnuJuryToevoegen);
        
        mnuBezoekersToevoegen = new JMenuItem("Bezoekers toevoegen");
        mnuBezoekersToevoegen.addActionListener(listener);
        add(mnuBezoekersToevoegen);

    }
    
}
