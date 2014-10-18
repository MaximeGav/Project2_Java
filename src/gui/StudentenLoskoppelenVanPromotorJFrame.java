
package gui;

import domein.DomeinController;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;


public class StudentenLoskoppelenVanPromotorJFrame extends javax.swing.JFrame {

    private final DomeinController dc;
    private JLabel lblTekst;
    private JButton btnJa, btnNee;
    private final List<String> student;
    private final List<String> promotor;

    public StudentenLoskoppelenVanPromotorJFrame(DomeinController dc, List<String> student, List<String> promotor) {
        super();
        this.dc = dc;
        this.student = student;
        this.promotor = promotor;
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
            {
                JTextArea textArea = new JTextArea("Student " + student.get(0) + " " + student.get(1) + " is reeds toegewezen aan \npromotor " 
                        + promotor.get(0) + " " + promotor.get(1) + ". \nWenst U deze van elkaar los te koppelen?");
                textArea.setBounds(25,25,350,50);
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setOpaque(false);
                textArea.setBorder(BorderFactory.createEmptyBorder());
                getContentPane().add(textArea);
            }
            {
                btnJa = new JButton("Ja");
                btnJa.setBounds(25, 85, 50, 25);
                getContentPane().add(btnJa);
                btnJa.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        dc.verhoogAantalKeerJury(promotor.get(3), Integer.parseInt(promotor.get(4))-1);
                        dc.kenPromotorToe(student.get(4), "");

                        //JOptionPane.showMessageDialog(null, "Student heeft als promotor " + student.getPromotor());
                        //new GebruikersSelecterenJFrame(dc);
                    }    
                });
            }
            {
                btnNee = new JButton("Nee");
                btnNee.setBounds(85, 85, 60, 25);
                getContentPane().add(btnNee);
                btnNee.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }    
                });
            }
            
            pack();
            setSize(375, 160);

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
