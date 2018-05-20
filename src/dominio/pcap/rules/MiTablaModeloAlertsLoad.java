
package dominio.pcap.rules;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/** 
 * Clase MitablaModeloAlertsLoad. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
class MiTablaModeloAlertsLoad extends AbstractTableModel
{

    public MiTablaModeloAlertsLoad()
    {
        tableColumns = new Vector();
        tableData = new Vector();
        tableColumns.addElement(new String("Regla"));
    }

    public int getColumnCount()
    {
        return tableColumns.size();
    }

    public int getRowCount()
    {
        return tableData.size();
    }

    public String getColumnName(int col)
    {
        return (String)tableColumns.elementAt(col);
    }

    public Object getValueAt(int row, int col)
    {
        Vector rowVector = (Vector)tableData.elementAt(row);
        return rowVector.elementAt(col);
    }

    public Class getColumnClass(int c)
    {
        return getValueAt(0, c).getClass();
    }

    public void addRow(String fichero_reglas)
    {
        Vector newRow = new Vector();
        newRow.addElement(fichero_reglas);
        tableData.addElement(newRow);
    }

    Vector tableColumns;
    Vector tableData;
}
