package presentacion.visualizarCaptura;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
/** 
 * Clase TableConexions. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class TableConexions
{
  private MiTablaModeloCx modelo;
  private JTable table;
  private Vector VectorConexionesTCP;
  private TableColumn column = null;
  protected int filaseleccionada = -1;
  private TablePane RTablePane;
  
  public TableConexions(TablePane RTablePane)
  {
    this.RTablePane = RTablePane;
    this.modelo = new MiTablaModeloCx();
    this.table = new JTable(this.modelo);
    this.table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent evt)
      {
        TableConexions.this.fileMouseClicked(evt);
      }
    });
    this.table.setOpaque(true);
    
    this.table.setBackground(new Color(0, 0, 0));
    this.table.setForeground(new Color(52, 255, 0));
  }
  
  private void fileMouseClicked(MouseEvent evt)
  {
    if ((evt.getModifiers() & 0x10) == 16) {
      getfila(this.table.getSelectedRow());
    }
    if ((evt.getModifiers() & 0x4) == 4) {
      new SelectColor(this.RTablePane).show();
    }
  }
  
  public void getfila(int fila)
  {
    this.filaseleccionada = fila;
    this.RTablePane.AplicarRender(fila);
  }
  
  public void DatosTablaConexion(String numeropaquete, String time, String ipsrc, String ipdest, String portsrc, String portdest, String paquetes)
  {
    this.modelo.addRow(numeropaquete, time, ipsrc, ipdest, portsrc, portdest, 
      paquetes);
  }
  
  public JTableHeader Cabezera()
  {
    return this.table.getTableHeader();
  }
  
  public JTable Tabla()
  {
    return this.table;
  }
  
  public void clearTable()
  {
    this.modelo.clearAll();
  }
}
