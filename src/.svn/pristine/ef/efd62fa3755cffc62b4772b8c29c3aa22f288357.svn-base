package gui;

import domein.DomeinController;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;


public class StudentenLoskoppelenJFrame extends javax.swing.JFrame {

    private final DomeinController dc;
    private JLabel lblTekst;
    private JButton btnJa, btnNee;
    private final String geselecteerdePromotor;

    public StudentenLoskoppelenJFrame(DomeinController dc, String geselecteerdePromotor) {
        super();
        this.dc = dc;
        this.geselecteerdePromotor = geselecteerdePromotor;
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
                /*lblTekst = new JLabel("De promotor heeft zijn maximum \naantal studenten bereikt. Wenst U enkele studenten los te koppelen?");
                getContentPane().add(lblTekst);
                lblTekst.setBounds(25, 25, 300, 25);*/
                JTextArea textArea = new JTextArea("De promotor heeft zijn maximum aantal studenten bereikt. Wenst U enkele studenten los te koppelen?");
                textArea.setBounds(25,25,325,40);
                textArea.setEditable(false);
                textArea.setLineWrap(true);
                textArea.setOpaque(false);
                textArea.setBorder(BorderFactory.createEmptyBorder());
                getContentPane().add(textArea);
            }
            {
                btnJa = new JButton("Ja");
                btnJa.setBounds(25, 75, 50, 25);
                getContentPane().add(btnJa);
                btnJa.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new StudentenLoskoppelenJFrame2(dc, geselecteerdePromotor);
                    }    
                });
            }
            {
                btnNee = new JButton("Nee");
                btnNee.setBounds(85, 75, 60, 25);
                getContentPane().add(btnNee);
                btnNee.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        //keer terug naar het venster om studenten aan een promotor te koppelen
                    }    
                });
            }
            
            pack();
            setSize(375, 150);

            // Get the size of the screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            // Determine the new location of the window
            int w = getSize().width;
            int h = getSize().height;
            int x = (dim.width-w)/2;
            int y = (dim.height-h)/2;

            // Move the window
            setLocation(x, y);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
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
