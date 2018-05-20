
package presentacion.visualizarCaptura;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
/** 
 * Clase MiTablaModelo. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
class MiTablaModelo extends AbstractTableModel
{

   
	public MiTablaModelo()
    {
        tableColumns = new Vector();
        tableData = new Vector();
        tableColumns.addElement(new String("Num"));
        tableColumns.addElement(new String("Time"));
        tableColumns.addElement(new String("Size"));
        tableColumns.addElement(new String("MAC Src"));
        tableColumns.addElement(new String("MAC Dest"));
        tableColumns.addElement(new String("Frame"));
        tableColumns.addElement(new String("Protocol"));
        tableColumns.addElement(new String("IP Src"));
        tableColumns.addElement(new String("IP Dest"));
        tableColumns.addElement(new String("Port Src"));
        tableColumns.addElement(new String("Port Dest"));
        tableColumns.addElement(new String("SEQ"));
        tableColumns.addElement(new String("ACK"));
        tableColumns.addElement(new String("Length"));
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

    public void newVector()
    {
        newRow = new Vector();
    }

    public void addRow(String macsource, String macdest, String frame, String protocol, String ipsrc, String ipdest, String portsrc, String portdest, String seq, String ack, String length)
    {
        newRow.addElement(macsource);
        newRow.addElement(macdest);
        newRow.addElement(frame);
        newRow.addElement(protocol);
        newRow.addElement(ipsrc);
        newRow.addElement(ipdest);
        newRow.addElement(portsrc);
        newRow.addElement(portdest);
        newRow.addElement(seq);
        newRow.addElement(ack);
        newRow.addElement(length);
    }

    public void addTimeRow(String numeropaquete, String time, String size)
    {
        newRow.addElement(numeropaquete);
        newRow.addElement(time);
        newRow.addElement(size);
    }

    public void addPacket()
    {
        tableData.addElement(newRow);
         }
        
        public void clearAll()
    {
        tableData.clear();
    }

    Vector tableColumns;
    Vector tableData;
    public Vector newRow;
}
