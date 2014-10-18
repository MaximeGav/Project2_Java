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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import domein.observers.PromotorDialog;
import domein.observers.PromotorObserver;
import java.awt.HeadlessException;
import java.util.Map;

/**
 * LIJN 220 aanpassen! code sandro
 * @author RootSoft
 */
public class PromotorToekennenPanel extends javax.swing.JPanel implements PromotorDialog {

    
    private final DomeinController dc;
    private final JList lijstStudenten, lijstPromotoren;
    private final DefaultListModel modelStudenten, modelPromotoren;
    private JLabel lblStudenten, lblPromotoren;
    private JButton btnSelecteerStudenten, btnSelecteerPromotoren, btnKoppelen, btnCancel;
    private JTextArea textAreaStudenten, textAreaPromotoren;
    private List<String> geselecteerdeStudenten;
    private final Set<PromotorObserver> observers;
    private final Map<String, String> studenten, promotoren;

    public PromotorToekennenPanel(DomeinController dc) {
        super();
        this.dc = dc;
        this.modelStudenten = new DefaultListModel();
        this.lijstStudenten = new JList(modelStudenten);
        this.modelPromotoren = new DefaultListModel();
        this.lijstPromotoren = new JList(modelPromotoren);
        this.lijstPromotoren.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.studenten = dc.geefStudenten();
        this.promotoren = dc.geefPromotoren();
        observers = new HashSet<>();
        
        //initComponents(); Methode die niet manueel aangepast kan worden in de code, enkel drag and drop
        initGUI(); //Eigen geschreven methode om een JFrame aan te maken
    }

    private void initGUI() {
        try {
            setLayout(null);
            setVisible(true);
            

            {
                lblStudenten = new JLabel("Selecteer de studenten");
                add(lblStudenten);
                lblStudenten.setBounds(35, 25, 300, 25);
            }
            {
                JScrollPane pane = new JScrollPane(lijstStudenten);

                pane.setBounds(35, 50, 300, 155);
                add(pane);
            }
            {
                btnSelecteerStudenten = new JButton("Selecteer");
                btnSelecteerStudenten.setBounds(35, 225, 100, 25);
                add(btnSelecteerStudenten);
                btnSelecteerStudenten.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        geselecteerdeStudenten = lijstStudenten.getSelectedValuesList();
                        if (geselecteerdeStudenten.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "U selecteerde geen student(en)");
                        } else {
                            for (String sNaam : geselecteerdeStudenten) {
                                String sEmail = studenten.get(sNaam);
                                List<String> student = dc.geefStudentByEmail(sEmail);
                                String pEmail = student.get(3);
                                if (!pEmail.equals("")) {
                                    List<String> promotor = dc.geefPromotorByEmail(pEmail);
                                    new StudentenLoskoppelenVanPromotorJFrame(dc, student, promotor);
                                }
                            }

                            toonLinkerDeel(e);
                        }
                    }
                });
            }
            {
                JSeparator x = new JSeparator(SwingConstants.VERTICAL);
                x.setBounds(360, 25, 5, 290);
                add(x);
            }
            
            for (Map.Entry<String, String> student : dc.geefStudenten().entrySet()) {
                modelStudenten.addElement(student.getKey());
            }
            
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

        } catch (HeadlessException e) {
            
        }

    }

    private void toonLinkerDeel(ActionEvent e) {
        setSize(750, 365);
        lijstStudenten.setEnabled(false);
        btnSelecteerStudenten.setEnabled(false);
        {
            String begin = "U selecteerde volgende studenten:\n\n";
            textAreaStudenten = new JTextArea(begin);
            textAreaStudenten.setEditable(false);
            //textArea.setPreferredSize(new Dimension(25, 200));
            textAreaStudenten.setBounds(25, 200, 300, 100);

            for (String s : geselecteerdeStudenten) {
                textAreaStudenten.append(s + "\n");
            }

            add(textAreaStudenten);
        }
        {
            JScrollPane scrollPane = new JScrollPane(textAreaStudenten, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
           scrollPane.setBounds(35, 275, 300, 155);
            add(scrollPane);
        }

        /**
         * PROMOTOR GEDEELTE
         */
        {
            lblPromotoren = new JLabel("Selecteer de promotor");
            add(lblPromotoren);
            lblPromotoren.setBounds(400, 25, 300, 25);
        }
        {
            JScrollPane pane = new JScrollPane(lijstPromotoren);
            pane.setBounds(400,  50, 300, 155);
            add(pane);
        }
        {
            btnSelecteerPromotoren = new JButton("Selecteer");
            btnSelecteerPromotoren.setBounds(400, 225,  100, 25);
            add(btnSelecteerPromotoren);
            btnSelecteerPromotoren.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                     
                    String geselecteerdePromotor = (String) lijstPromotoren.getSelectedValue();
                    if (geselecteerdePromotor == null) {
                        JOptionPane.showMessageDialog(null, "U selecteerde geen promotor");
                    } else {
                        for (String s : geselecteerdeStudenten) {
                            String sEmail = studenten.get(s);
                            List<String> student = dc.geefStudentByEmail(sEmail);
                            String pEmail = student.get(3);
                            if (!pEmail.equals("")) {
                                List<String> promotor = dc.geefPromotorByEmail(pEmail);
                                new StudentenLoskoppelenVanPromotorJFrame(dc, student, promotor);
                            } else {
                                toonRechterdeel(e, geselecteerdePromotor);
                            }
                        }
                        lijstPromotoren.setEnabled(false);
                    }
                }
            });
        }
        {
             btnCancel = new JButton("Cancel");
            btnCancel.setBounds(530, 225,  100, 25);
            add(btnCancel);
            btnCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                     removeAll();
                    revalidate();
                    repaint();
                    initGUI();
                    
                    notifyObservers();
                    lijstStudenten.setEnabled(true);
                    btnSelecteerStudenten.setEnabled(true);
                    geselecteerdeStudenten.clear();
                    lijstPromotoren.setEnabled(true);
                }
                   
        });
        }
        for (Map.Entry<String, String> promotor : dc.geefPromotoren().entrySet()) {
            modelPromotoren.addElement(promotor.getValue()); 
        }
    }

    private void toonRechterdeel(ActionEvent e, final String pNaam) {
        {
            String begin = "U selecteerde volgende promotor:\n\n";
            textAreaPromotoren = new JTextArea(begin);
            textAreaPromotoren.setEditable(false);
            //textAreaPromotoren.setPreferredSize(new Dimension(25, 200));
             textAreaPromotoren.setBounds(400, 200, 300, 65);
            //promotoren.get
            textAreaPromotoren.append(pNaam);

            add(textAreaPromotoren);
        }
        {
            JScrollPane scrollPane2 = new JScrollPane(textAreaPromotoren);
            scrollPane2.setBounds(400, 275, 300, 65);
            add(scrollPane2);
        }
        {
            btnKoppelen = new JButton("Opslaan");
            btnKoppelen.setBounds(400, 375, 100, 25);
            add(btnKoppelen);
            btnKoppelen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int aantalKeerJury = 0;//geselecteerdePromotor.getAantalKeerJury();
                    String pEmail ="";
                    for (String s : geselecteerdeStudenten) {
                        if (aantalKeerJury < 6) {
                            String sEmail = studenten.get(s);
                            List<String> student = dc.geefStudentByEmail(sEmail);
                            String sNaam = student.get(0) + " " + student.get(1);
                            
                            for (Map.Entry<String, String> entry : promotoren.entrySet()) {
                                String email = entry.getKey();
                                String naam = entry.getValue();
                                if (naam.equals(pNaam)) {
                                    pEmail = email;
                                    break;
                                }
                            }

                            dc.kenPromotorToe(sEmail, pEmail);
                            aantalKeerJury++;
                            dc.verhoogAantalKeerJury(pEmail, aantalKeerJury);
                            JOptionPane.showMessageDialog(null, "Student " + sNaam + " werd gekoppeld aan "
                                    + pNaam, "Koppeling student en promotor geslaagd", JOptionPane.INFORMATION_MESSAGE);
                            
                        } else { //Promotor heeft het max aantal studenten bereikt
                            new StudentenLoskoppelenJFrame(dc, pEmail);
                        }
                    }

                    removeAll();
                    revalidate();
                    repaint();
                    initGUI();
                    
                    notifyObservers();
                    lijstStudenten.setEnabled(true);
                    btnSelecteerStudenten.setEnabled(true);
                    geselecteerdeStudenten.clear();
                    lijstPromotoren.setEnabled(true);
                }
            });
        }
        //pack();
        setSize(750, 365);
    }
    
    
    @Override
    public void addObserver(PromotorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(PromotorObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PromotorObserver observer : observers) {
            observer.updatePromotor();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 433, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
