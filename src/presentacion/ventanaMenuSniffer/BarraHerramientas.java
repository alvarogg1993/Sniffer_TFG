
package presentacion.ventanaMenuSniffer;

import java.awt.Component;
import java.io.PrintStream;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import presentacion.Mediador;
import presentacion.comandos.*;
/** 
 * Clase BarraHerramientas. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class BarraHerramientas extends JPanel
{

    public BarraHerramientas(Mediador med)
    {
        mediador = med;
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        addButtons(toolBar);
    }

    protected void addButtons(JToolBar toolBar)
    {
        javax.swing.JButton button = null;
        cBIrInicioCapture = new CBInicioCapture(mediador);
        toolBar.add((CBInicioCapture)cBIrInicioCapture);
        cBAbrirFichero = new CBAbrirFichero(mediador, "Abrir fichero de Capturas...");
        toolBar.add((CBAbrirFichero)cBAbrirFichero);
        toolBar.addSeparator();
        cBGuardarFichero = new CBGuardarFichero(mediador, "Guardar fichero capturado...");
        toolBar.add((CBGuardarFichero)cBGuardarFichero);
        cBExportarXMLFichero = new CBGuardarFichero(mediador, "Exportar fichero a XML...");
        toolBar.add((CBGuardarFichero)cBExportarXMLFichero);
        toolBar.addSeparator();
        cBIrInicioDefinicion = new CBInicioDefinicion(mediador);
        toolBar.add((CBInicioDefinicion)cBIrInicioDefinicion);
        toolBar.addSeparator();
        cBIrInicioInsert = new CBInicioInsert(mediador);
        toolBar.add((CBInicioInsert)cBIrInicioInsert);
        cBIrInicioInsertCap = new CBInicioInsertCap(mediador);
        toolBar.add((CBInicioInsertCap)cBIrInicioInsertCap);
        toolBar.addSeparator();
        cBVentanaAyuda = new CBVentanaAyuda(mediador);
        toolBar.add((CBVentanaAyuda)cBVentanaAyuda);
        toolBar.addSeparator();
        cBConfiguracion = new CBConfiguracion(mediador);
        toolBar.add((CBConfiguracion)cBConfiguracion);
        toolBar.addSeparator();
    }

    public static JToolBar getBarraHerramientas()
    {
        return toolBar;
    }

    public static void deshabilitarComponenteBarraHerramientas(int posicion)
    {
        if(posicion < toolBar.getComponentCount())
        {
            toolBar.getComponentAtIndex(posicion).setEnabled(false);
            toolBar.repaint();
        } else
        {
            System.out.println("Te has salido del rango de componentes de la barra de herramientas");
        }
    }

    public static void habilitarComponenteBarraHerramientas(int posicion)
    {
        if(posicion < toolBar.getComponentCount())
        {
            toolBar.getComponentAtIndex(posicion).setEnabled(true);
            toolBar.repaint();
        } else
        {
            System.out.println("Te has salido del rango de componentes de la barra de herramientas");
        }
    }

    static JToolBar toolBar;
    Mediador mediador;
    Comando cBIrInicioCapture;
    Comando cBFinCapture;
    Comando cBIrInicioDefinicion;
    Comando cBIrInicioInsert;
    Comando cBIrInicioInsertCap;
    Comando cBIrFinInsert;
    Comando cBAbrirFichero;
    Comando cBGuardarFichero;
    Comando cBExportarXMLFichero;
    Comando cBVentanaAyuda;
    Comando cBConfiguracion;
    Comando cB_Prueba;
    Comando cB_Prueba2;
}
