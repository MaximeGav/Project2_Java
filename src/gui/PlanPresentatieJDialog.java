package gui;

import domein.DomeinController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import resources.DateTimeFactory;
import domein.observers.PlanningDialog;
import domein.observers.PlanningObserver;
import javax.swing.JOptionPane;

public class PlanPresentatieJDialog extends javax.swing.JDialog implements PlanningDialog {

    private final DomeinController dc;
    private Map<String, String> studentenMap;
    private final int dag, tijdsvak;
    private final String campusNaam, lokaalNaam;
    private final List<String> promotor;
    private final String promotorEmail;
    private final Set<PlanningObserver> observers;
    //private String s;

    public PlanPresentatieJDialog(final DomeinController dc, String pEmail, String cNaam, String lNaam, int dag, int tijdsvak) {
        //super(parent, modal);
        initComponents();

        this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("images/small_icon.png")).getImage());
        getContentPane().setLayout(null);
        setResizable(true);
        setTitle("Bachelorproefapplicatie HoGent");

        this.dc = dc;
        this.dag = dag;
        
        txtDatum.setText(DateTimeFactory.geefDag(dag));
        txtDatum.setEditable(false);
        this.tijdsvak = tijdsvak;
        txtBegin.setText(DateTimeFactory.geefTijdsvak(tijdsvak));
        txtBegin.setEditable(false);
        txtEind.setText(DateTimeFactory.geefTijdsvak(tijdsvak+1));
        txtEind.setEditable(false);
        this.campusNaam = cNaam;
        txtCampus.setText(cNaam);
        txtCampus.setEditable(false);
        this.lokaalNaam = lNaam;
        txtLokaal.setText(lNaam);
        txtLokaal.setEditable(false);
        this.promotorEmail = pEmail;
        promotor = dc.geefPromotorByEmail(pEmail);
        txtPromotor.setText(promotor.get(0) + " " + promotor.get(1));
        txtPromotor.setEditable(false);


        setStudentenCombobox(cmbStudenten);

        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // Move the window
        setLocation(x, y);
        setVisible(true);
        
        observers = new HashSet<>();
    }

    private void setStudentenCombobox(JComboBox combobox) {
        //Enkel de studenten van de promotor
        combobox.removeAllItems();
        studentenMap = dc.geefStudenten();
        for (Map.Entry<String, String> s : studentenMap.entrySet()) {
            List<String> student = dc.geefStudentByEmail(s.getValue());
            String pEmail = student.get(3);
            if (!pEmail.equals("")) {
                if (pEmail.equals(promotorEmail)) {
                    if (dc.geefPresentatieByStudentEmail(s.getValue())==null) {
                    combobox.addItem(s.getKey());
                    }
                    
                }
            }
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

        planPresentatie = new javax.swing.JButton();
        txtDatum = new javax.swing.JTextField();
        lblDatum = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBegin = new javax.swing.JTextField();
        txtLokaal = new javax.swing.JTextField();
        lblBegin = new javax.swing.JLabel();
        txtCampus = new javax.swing.JTextField();
        lblCampus = new javax.swing.JLabel();
        cmbStudenten = new javax.swing.JComboBox();
        lblEind = new javax.swing.JLabel();
        txtEind = new javax.swing.JTextField();
        lblLokaal = new javax.swing.JLabel();
        lblPromotor = new javax.swing.JLabel();
        txtPromotor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        planPresentatie.setText("Opslaan");
        planPresentatie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planPresentatieActionPerformed(evt);
            }
        });

        lblDatum.setText("Datum:");
        lblDatum.setToolTipText("");

        jLabel3.setText("Student:");

        lblBegin.setText("Beginstijdstip:");
        lblBegin.setToolTipText("");

        lblCampus.setText("Campus:");
        lblCampus.setToolTipText("");

        cmbStudenten.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblEind.setText("Eindstijdstip:");
        lblEind.setToolTipText("");

        lblLokaal.setText("Lokaal:");
        lblLokaal.setToolTipText("");

        lblPromotor.setText("Promotor:");
        lblPromotor.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(planPresentatie)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(29, 29, 29)
                        .addComponent(cmbStudenten, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEind)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(lblPromotor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPromotor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCampus)
                                .addComponent(lblLokaal))
                            .addGap(29, 29, 29)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtLokaal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtEind, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblBegin)
                                .addComponent(lblDatum))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDatum, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                .addComponent(txtBegin)))))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatum)
                    .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBegin)
                    .addComponent(txtBegin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEind)
                    .addComponent(txtEind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLokaal)
                    .addComponent(txtLokaal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCampus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCampus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPromotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPromotor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbStudenten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(planPresentatie)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void planPresentatieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planPresentatieActionPerformed

        String student = (String) cmbStudenten.getSelectedItem();
        String studentMail = "";
        try{
            for (Map.Entry<String, String> s : dc.geefStudenten().entrySet()) {
                if (student.equals(s.getKey())) {
                    studentMail = s.getValue();
                }
            }

            dc.planPresentatie(dag, tijdsvak, lokaalNaam, campusNaam, studentMail);
        }catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "Er is geen student geselecteerd. Gelieve een student te kiezen");
        }
        
        //Notify the GUI to update.
        notifyObservers();
        this.dispose();
    }//GEN-LAST:event_planPresentatieActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlanPresentatieJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbStudenten;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblBegin;
    private javax.swing.JLabel lblCampus;
    private javax.swing.JLabel lblDatum;
    private javax.swing.JLabel lblEind;
    private javax.swing.JLabel lblLokaal;
    private javax.swing.JLabel lblPromotor;
    private javax.swing.JButton planPresentatie;
    private javax.swing.JTextField txtBegin;
    private javax.swing.JTextField txtCampus;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JTextField txtEind;
    private javax.swing.JTextField txtLokaal;
    private javax.swing.JTextField txtPromotor;
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
