package gui;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RootSoft
 */
public class PlanningTableModel extends AbstractTableModel implements TableModelListener{
    
    //Attributes
    private static final String[] DAGEN = {"Uur", "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag"};
    private Object[][] data;
    
    //Constructors
    public PlanningTableModel() {
       addTableModelListener(this);
    }
    
    public PlanningTableModel(Object[][] data) {
       addTableModelListener(this);
       this.data = data;
       
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return DAGEN.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    
    //Callbacks
    
    @Override
    public void tableChanged(TableModelEvent e) {
        
    }
    
}
