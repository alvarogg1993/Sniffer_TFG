// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustomTableCellRenderer.java

package presentacion.visualizarCaptura;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/** 
 * Clase CustomTableCellRenderer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
// Referenced classes of package presentacion.visualizarCaptura:
//            VisualizarCaptura, Conexion

public class CustomTableCellRenderer extends DefaultTableCellRenderer
{

    public CustomTableCellRenderer(Color color, VisualizarCaptura venpadre, int fila_seleccionada)
    {
        VectorConexiones = new Vector();
        Vectoraux = new Vector();
        this.venpadre = venpadre;
        this.color = color;
        int i = 0;
        int j = 0;
        conexiontcp = fila_seleccionada;
        if(conexiontcp != -1)
        {
            VectorConexiones = venpadre.getConexionesTCP();
            Conexion objetoConexion = (Conexion)VectorConexiones.elementAt(conexiontcp);
            Vectoraux = objetoConexion.getvector();
        }
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        int t = 0;
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cell.setBackground(Color.white);
        if(conexiontcp == -1)
            cell.setBackground(Color.white);
        else
            for(t = 0; t < Vectoraux.size(); t++)
            {
                Integer pospk = (Integer)Vectoraux.elementAt(t);
                if(row == pospk.intValue() - 1)
                    cell.setBackground(color);
            }

        if(isSelected)
            cell.setBackground(Color.LIGHT_GRAY);
        return cell;
    }

    public void filaconexion(int fila)
    {
        conexiontcp = fila;
    }

    private int conexiontcp;
    private Vector VectorConexiones;
    private Conexion objetoConexion;
    private Vector Vectoraux;
    protected Color color;
    public VisualizarCaptura venpadre;
}
