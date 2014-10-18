package gui;

import domein.DomeinController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import domein.observers.PlanningDialog;
import domein.observers.PlanningObserver;
import java.awt.HeadlessException;
import java.util.Map;

public class BezoekersToevoegenJFrame extends javax.swing.JFrame implements PlanningDialog {

    private final DomeinController dc;
    private final JList lijstGasten, lijstPresentaties;
    private final DefaultListModel modelGasten, modelPresentaties;
    private JLabel lblGasten, lblPresentaties;
    private JButton btnSelecteerGast, btnInschrijven;
    private String geselecteerdeGast;
    private final List<String> geselecteerdePresentatie;
    private List<String> geselecteerdePresentaties;
    private Set<PlanningObserver> observers;
    private final String sEmail;
    private String gEmail;

    public BezoekersToevoegenJFrame(DomeinController dc, List<String> presentatie) {
        super();
        this.dc = dc;
        this.modelGasten = new DefaultListModel();
        this.lijstGasten = new JList(modelGasten);
        this.lijstGasten.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.modelPresentaties = new DefaultListModel();
        this.lijstPresentaties = new JList(modelPresentaties);
        this.lijstPresentaties.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.geselecteerdePresentatie = presentatie;
        sEmail = presentatie.get(2);
        initGUI(); //Eigen geschreven methode om een JFrame aan te maken
    }

    private void initGUI() {
        try {
            this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/small_icon.png")).getImage());
            getContentPane().setLayout(null);
            setVisible(true);
            setResizable(true);
            setTitle("Bachelorproefapplicatie HoGent");
            

            {
                lblGasten = new JLabel("Selecteer een gast");
                getContentPane().add(lblGasten);
                lblGasten.setBounds(25, 25, 300, 25);
            }
            {
                JScrollPane pane = new JScrollPane(lijstGasten);
                pane.setBounds(25, 50, 300, 100);
                getContentPane().add(pane);
            }
            {
                btnSelecteerGast = new JButton("Selecteer");
                btnSelecteerGast.setBounds(25, 160, 100, 25);
                getContentPane().add(btnSelecteerGast);
                btnSelecteerGast.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        geselecteerdeGast = (String) lijstGasten.getSelectedValue();
                        if (geselecteerdeGast == null) {
                            JOptionPane.showMessageDialog(null, "U selecteerde geen gast");
                        } else {
                            toonLinkerDeel(e);
                        }
                    }
                });
            }
            {
                JSeparator x = new JSeparator(SwingConstants.VERTICAL);
                x.setBounds(360, 25, 5, 150);
                getContentPane().add(x);
            }
            
            for (Map.Entry<String, List<String>> i : dc.geefInschrijvingenByPresentatieId(sEmail).entrySet()) {
                List<String> inschrijving = i.getValue();
                String gastEmail = inschrijving.get(1);
                boolean isGoedgekeurd = Boolean.getBoolean(inschrijving.get(3));
                if (!gastEmail.equals("")) {
                    if (!isGoedgekeurd){
                        gEmail = gastEmail;
                        List<String> g = dc.geefGastByEmail(gastEmail);
                        String naam = g.get(0) + " " + g.get(1);
                        modelGasten.addElement(naam);
                    }
                }
            }

            pack();
            setSize(375, 250);

            // Get the size of the screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            // Determine the new location of the window
            int w = getSize().width;
            int h = getSize().height;
            int x = (dim.width - w) / 2;
            int y = (dim.height - h) / 2;

            // Move the window
            setLocation(x, y);
            observers = new HashSet<>();
        } catch (HeadlessException e) {
        }

    }

    private void toonLinkerDeel(ActionEvent e) {
        setSize(750, 250);
        lijstGasten.setEnabled(false);
        btnSelecteerGast.setEnabled(false);
        {
            lblPresentaties = new JLabel("Selecteer de presentaties");
            getContentPane().add(lblPresentaties);
            lblPresentaties.setBounds(400, 25, 300, 25);
        }
        {
            JScrollPane pane = new JScrollPane(lijstPresentaties);
            pane.setBounds(400, 50, 300, 100);
            getContentPane().add(pane);
        }
        {
            btnInschrijven = new JButton("Inschrijven");
            btnInschrijven.setBounds(400, 160, 100, 25);
            getContentPane().add(btnInschrijven);
            btnInschrijven.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    geselecteerdePresentaties = lijstPresentaties.getSelectedValuesList();
                    if (geselecteerdePresentaties.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "U selecteerde geen presentaties");
                    }
                    else {
                        
                        for (String s : geselecteerdePresentaties) {
                            dc.inschrijvingGoedkeuren(sEmail,gEmail);
                            dispose();
                        }
                    }
                }
            });
        }

        for (Map.Entry<String, List<String>> i : dc.geefInschrijvingenByGastEmail(gEmail).entrySet()) {
            List<String> inschrijving = i.getValue();
            boolean isGoedgekeurd = Boolean.getBoolean(inschrijving.get(3));
            if (!isGoedgekeurd) {
                List<String> student = dc.geefStudentByEmail(sEmail);
                modelPresentaties.addElement(student.get(0) + " " + student.get(1));
            }
        }
        
        // Get the size of the screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            // Determine the new location of the window
            int w = getSize().width;
            int h = getSize().height;
            int x = (dim.width - w) / 2;
            int y = (dim.height - h) / 2;

            // Move the window
            setLocation(x, y);
    }
    
    
    @Override
    public void addObserver(PlanningObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PlanningObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PlanningObserver observer : observers) {
            observer.updatePlanning();
        }
    }

}

/*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
