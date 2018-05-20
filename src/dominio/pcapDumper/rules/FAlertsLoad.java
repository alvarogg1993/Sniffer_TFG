
package dominio.pcapDumper.rules;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/** 
 * Clase FAlertsLoad. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package dominio.pcap.rules:
//            MiTablaModeloAlertsLoad

public class FAlertsLoad extends JFrame
{

    public FAlertsLoad()
    {
        super("AlertsLoad");
        column = null;
        modelo = new MiTablaModeloAlertsLoad();
        table = new JTable(modelo);
        table.setPreferredScrollableViewportSize(new Dimension(250, 50));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, "Center");
        pack();
    }

    public void DatosTablaAlertsLoad(String p_fichero_reglas)
    {
        modelo.addRow(p_fichero_reglas);
    }

    public JTableHeader Cabezera()
    {
        return table.getTableHeader();
    }

    public JTable Tabla()
    {
        return table;
    }

    private MiTablaModeloAlertsLoad modelo;
    private JTable table;
    private TableColumn column;
}
