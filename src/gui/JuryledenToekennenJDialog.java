package gui;

import domein.DomeinController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import domein.observers.PlanningDialog;
import domein.observers.PlanningObserver;
import java.util.Map;

public class JuryledenToekennenJDialog extends javax.swing.JDialog implements PlanningDialog {

    private final DomeinController dc;
    private final Map<String, String> promotoren;
    private final List<String> geselecteerdePresentatie;
    private final Set<PlanningObserver> observers;
    private final String studentMail, promotorEmail;

    public JuryledenToekennenJDialog(final DomeinController dc, List<String> geselecteerdePresentatie, String studentMail) {
        initComponents();

        this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/small_icon.png")).getImage());
        getContentPane().setLayout(null);
        setVisible(true);
        setResizable(true);
        setTitle("Bachelorproefapplicatie HoGent");

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // Move the window
        setLocation(x, y);

        this.dc = dc;
        this.geselecteerdePresentatie = geselecteerdePresentatie;
        this.studentMail = studentMail;
        List<String> student = dc.geefStudentByEmail(studentMail);
        this.promotorEmail = student.get(3);
        List<String> promotor = dc.geefPromotorByEmail(promotorEmail);
        
        lblNaamStudent.setText(student.get(0) + " " + student.get(1));
        promotoren = dc.geefPromotoren();
        String jurylidNaam = promotor.get(0) + " " + promotor.get(1);
        lblJurylidNaam.setText(jurylidNaam);
        setStudentenCombobox(cmbJurylid2);
        
        observers = new HashSet<>();
    }

    private void setStudentenCombobox(JComboBox combobox) {
        combobox.removeAllItems();
        for (Map.Entry<String, String> p : promotoren.entrySet()) {
            
            if (!studentMail.equals(p.getKey())) {
                combobox.addItem(p.getValue());
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNaamStudent = new javax.swing.JTextField();
        lblPresentatieVan = new javax.swing.JLabel();
        lblJurylid1 = new javax.swing.JLabel();
        lblJurylid2 = new javax.swing.JLabel();
        btnVoegJurylidToe = new javax.swing.JButton();
        cmbJurylid2 = new javax.swing.JComboBox();
        lblNaamStudent = new javax.swing.JLabel();
        lblJurylidNaam = new javax.swing.JLabel();

        txtNaamStudent.setText("txtNaamStudent");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblPresentatieVan.setText("Presentatie van :");

        lblJurylid1.setText("Jurylid 1 :");

        lblJurylid2.setText("Jurylid 2 :");

        btnVoegJurylidToe.setText("Voeg Juryleden toe");
        btnVoegJurylidToe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoegJurylidToeActionPerformed(evt);
            }
        });

        cmbJurylid2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblNaamStudent.setText("jLabel1");

        lblJurylidNaam.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJurylid2)
                            .addComponent(lblJurylid1)
                            .addComponent(lblPresentatieVan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNaamStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblJurylidNaam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbJurylid2, 0, 160, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(120, Short.MAX_VALUE)
                        .addComponent(btnVoegJurylidToe)
                        .addGap(104, 104, 104)))
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPresentatieVan)
                    .addComponent(lblNaamStudent))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJurylid1)
                    .addComponent(lblJurylidNaam))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbJurylid2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJurylid2))
                .addGap(45, 45, 45)
                .addComponent(btnVoegJurylidToe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoegJurylidToeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoegJurylidToeActionPerformed

        String juryLid = (String) cmbJurylid2.getSelectedItem();
        String juryLidEmail = "";

        for (Map.Entry<String, String> p : promotoren.entrySet()) {
            if (juryLid.equals(p.getValue())) {
                juryLidEmail = p.getKey();
            }
        }

        dc.kenJuryLedenToe(studentMail, juryLidEmail);
        notifyObservers();
        this.dispose();
        //new PlanningOpstellenJFrame(dc);
    }//GEN-LAST:event_btnVoegJurylidToeActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoegJurylidToe;
    private javax.swing.JComboBox cmbJurylid2;
    private javax.swing.JLabel lblJurylid1;
    private javax.swing.JLabel lblJurylid2;
    private javax.swing.JLabel lblJurylidNaam;
    private javax.swing.JLabel lblNaamStudent;
    private javax.swing.JLabel lblPresentatieVan;
    private javax.swing.JTextField txtNaamStudent;
    // End of variables declaration//GEN-END:variables

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
