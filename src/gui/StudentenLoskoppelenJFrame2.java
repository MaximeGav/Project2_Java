package gui;

import domein.DomeinController;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class StudentenLoskoppelenJFrame2 extends javax.swing.JFrame {
    
    private final DomeinController dc;
    private JLabel lblTekst;
    private JButton btnLoskoppelen;
    private final DefaultListModel modelStudenten;
    private final JList lijstStudenten;
    private List<String> geselecteerdeStudenten;
    private final String geselecteerdePromotor;

    public StudentenLoskoppelenJFrame2(DomeinController dc, String geselecteerdePromotor) {
        super();
        this.dc = dc;
        this.modelStudenten = new DefaultListModel();
        this.lijstStudenten = new JList(modelStudenten);
        this.geselecteerdePromotor = geselecteerdePromotor;
        //initComponents(); Methode die niet manueel aangepast kan worden in de code, enkel drag and drop
        initGUI(); //Eigen geschreven methode om een JFrame aan te maken
    }

    private void initGUI() {
        try {
            this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/small_icon.png")).getImage());
            getContentPane().setLayout(null);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);
            setResizable(true);
            setTitle("Bachelorproefapplicatie HoGent");
            List<String> promotor = dc.geefPromotorByEmail(geselecteerdePromotor);
            {
                JTextArea textArea = new JTextArea("Promotor " + promotor.get(0) + " " +
                        promotor.get(1)+ " heeft volgende studenten:");
                textArea.setBounds(25,25,325,25);
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setOpaque(false);
                textArea.setBorder(BorderFactory.createEmptyBorder());
                getContentPane().add(textArea);
            }
            {
                JScrollPane pane = new JScrollPane(lijstStudenten);
                pane.setBounds(25, 50, 300, 100);
                getContentPane().add(pane);
            }
            
            for (String s : geefStudentenVanPromotor()) {
                modelStudenten.addElement(s);
            }
            
            geselecteerdeStudenten = lijstStudenten.getSelectedValuesList();
            System.out.println("k: " + geselecteerdeStudenten);
            {
                btnLoskoppelen = new JButton("Loskoppelen");
                btnLoskoppelen.setBounds(25, 160, 125, 25);
                getContentPane().add(btnLoskoppelen);
                btnLoskoppelen.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (String s : geselecteerdeStudenten) {
                            System.out.println(s);
                            Map<String,String> studenten = dc.geefStudenten();
                            for (Map.Entry<String, String> student : studenten.entrySet()) {
                                String naam = student.getKey();
                                String mail = student.getValue();
                                if (s.equals(naam)) {
                                    System.out.println(naam);
                                    dc.koppelLos(mail);
                                    List<String> promotor = dc.geefPromotorByEmail(geselecteerdePromotor);
                                    int aantalKeerJury = Integer.parseInt(promotor.get(4));
                                    dc.verhoogAantalKeerJury(geselecteerdePromotor, aantalKeerJury-1);
                                }
                            }
                        }
                         dispose();
                    }
                });
            }

            pack();
            setSize(375, 250);

            // Get the size of the screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            // Determine the new location of the window
            int w = getSize().width;
            int h = getSize().height;
            int x = (dim.width-w)/2;
            int y = (dim.height-h)/2;

            // Move the window
            setLocation(x, y);
        } catch (HeadlessException e) {
        }

    }
    
    /**
     * Geeft een lijst van studenten (email) terug van 
     * @param promotorEmail
     * @return 
     */
    private List<String> geefStudentenVanPromotor() {
        List<String> studenten = new ArrayList<>();
        for (Map.Entry<String, String> s : dc.geefStudenten().entrySet()) {
            List<String> student = dc.geefStudentByEmail(s.getValue());
            String sNaam = student.get(0) + " " + student.get(1);
            String sEmail = student.get(4);
            String pEmail = student.get(3);
            if (!pEmail.equals("")) {
                if (pEmail.equals(geselecteerdePromotor)) {
                    studenten.add(sNaam);
                }
            }
        }
        return studenten;
    }

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
