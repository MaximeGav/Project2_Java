package gui;

import domein.Beschikbaarheid;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import persistentie.DAOFactory;
import persistentie.dao.BeschikbaarheidDAO;
import enums.DAO;

public class CustomRenderer extends DefaultTableCellRenderer {

    private String promotorEmail = "";

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        c.setBackground(table.getBackground());

        if (!promotorEmail.equals("")) {
            for (Beschikbaarheid b : findBeschikbaarhedenByPromotorEmail()) {
                if (row == b.getTijdsvak() && column == b.getDag()) {
                    c.setBackground(Color.GREEN);
                }
            }
        }

        return c;
    }

    public String getPromotorEmail() {
        return promotorEmail;
    }

    public void setPromotorEmail(String pEmail) {
        this.promotorEmail = pEmail;
    }

    public List<Beschikbaarheid> findBeschikbaarhedenByPromotorEmail() {
        DAOFactory.initFactory();
        BeschikbaarheidDAO bDAO = (BeschikbaarheidDAO) DAOFactory.getDAO(DAO.BESCHIKBAARHEID);
        return bDAO.findBeschikbaarhedenByPromotorEmail(promotorEmail);
    }
}
