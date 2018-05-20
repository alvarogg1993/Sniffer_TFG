
package presentacion.visualizarCaptura;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
/** 
 * Clase MiTablaModeloCx. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
class MiTablaModeloCx extends AbstractTableModel
{

    public MiTablaModeloCx()
    {
        tableColumns = new Vector();
        tableData = new Vector();
        tableColumns.addElement(new String("Num Conexi\363n"));
        tableColumns.addElement(new String("Tiempo Inicio"));
        tableColumns.addElement(new String("IP Origen"));
        tableColumns.addElement(new String("IP Destino"));
        tableColumns.addElement(new String("Puerto Origen"));
        tableColumns.addElement(new String("Puerto Destino"));
        tableColumns.addElement(new String("N\372mero Paquetes"));
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

    public void addRow(String numeropaquete, String time, String ipsrc, String ipdest, String portsrc, String portdest, String paquetes)
    {
        Vector newRow = new Vector();
        newRow.addElement(numeropaquete);
        newRow.addElement(time);
        newRow.addElement(ipsrc);
        newRow.addElement(ipdest);
        newRow.addElement(portsrc);
        newRow.addElement(portdest);
        newRow.addElement(paquetes);
        tableData.addElement(newRow);
    }

    public void clearAll()
    {
        tableData.clear();
    }

    Vector tableColumns;
    Vector tableData;
}
