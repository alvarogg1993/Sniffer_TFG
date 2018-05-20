
package presentacion.ventanaMenuSniffer;

import java.io.PrintStream;
import javax.swing.*;

import presentacion.Mediador;
import presentacion.comandos.*;
/** 
 * Clase BarraMenu. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class BarraMenu extends JPanel
{


	public BarraMenu(Mediador med)
    {
        mediador = med;
        menuBar = new JMenuBar();
        menuBar.repaint();
        addMenus(menuBar);
        menuBar.repaint();
    }

    protected void addMenus(JMenuBar menuBar)
    {
        menuArchivo = new JMenu("Archivo");
        menuBar.add(menuArchivo);
        cJMAbrirFichero = new CJMAbrirFichero(mediador, "Abrir fichero de Capturas...");
        menuArchivo.add((CJMAbrirFichero)cJMAbrirFichero);
        cJMGuardarFicheroPcap = new CJMGuardarFichero(mediador, "Guardar fichero capturado...");
        menuArchivo.add((CJMGuardarFichero)cJMGuardarFicheroPcap);
        menuArchivo.addSeparator();
        menuExportar = new JMenu("Exportar");
        menuArchivo.add(menuExportar);
        cJMGuardarFicheroXML = new CJMGuardarFichero(mediador, "Captura a XML...");
        menuExportar.add((CJMGuardarFichero)cJMGuardarFicheroXML);
        cJMGuardarDesdeFicheroAXML = new CJMGuardarFichero(mediador, "... desde fichero a XML...");
        menuExportar.add((CJMGuardarFichero)cJMGuardarDesdeFicheroAXML);
        menuArchivo.addSeparator();
        cJMConfiguracion = new CJMConfiguracion(mediador);
        menuArchivo.add((CJMConfiguracion)cJMConfiguracion);
        menuArchivo.addSeparator();
        cJMSalir = new CJMSalir(mediador);
        menuArchivo.add((CJMSalir)cJMSalir);
        
        menuCaptura = new JMenu("Captura");
        menuBar.add(menuCaptura);
        cJMInicioCapture = new CJMInicioCapture(mediador);
        menuCaptura.add((CJMInicioCapture)cJMInicioCapture);
        cJMFinCapture = new CJMFinCapture(mediador);
        menuCaptura.add((CJMFinCapture)cJMFinCapture);
        menuCaptura.addSeparator();
        cJMInicioCaptureFromFile = new CJMInicioCaptureFromFile(mediador);
        menuCaptura.add((CJMInicioCaptureFromFile)cJMInicioCaptureFromFile);
      
        menuDefinicion = new JMenu("Definicion");
        menuBar.add(menuDefinicion);
        cJMInicioDefinicion = new CJMInicioDefinicion(mediador);
        menuDefinicion.add((CJMInicioDefinicion)cJMInicioDefinicion);
      
        menuInsercion = new JMenu("Insercion");
        menuBar.add(menuInsercion);
        cJMInicioInsertDef = new CJMInicioInsertDefinidos(mediador);
        menuInsercion.add((CJMInicioInsertDefinidos)cJMInicioInsertDef);
        cJMInicioInsertCap = new CJMInicioInsertCapturados(mediador);
        menuInsercion.add((CJMInicioInsertCapturados)cJMInicioInsertCap);
        
//        menuOperacion = new JMenu("Operaciones");
//        menuBar.add(menuOperacion);
//        cJMIntrusos = new CJMOperacionIntrusos(mediador);
//        menuOperacion.add((CJMOperacionIntrusos)cJMIntrusos);
                
        menuParametrizacion = new JMenu("Parametrizaci\363n");
        menuBar.add(menuParametrizacion);
        cJMPreferenciasCaptura = new CJMPreferenciasCaptura(mediador);
        menuParametrizacion.add((CJMPreferenciasCaptura)cJMPreferenciasCaptura);
        cJMPreferenciasExportar = new CJMPreferenciasExportar(mediador);
        menuParametrizacion.add((CJMPreferenciasExportar)cJMPreferenciasExportar);
        cJMPreferenciasFromFile = new CJMPreferenciasFromFile(mediador);
        menuParametrizacion.add((CJMPreferenciasFromFile)cJMPreferenciasFromFile);
        menuParametrizacion.addSeparator();
        cJMPreferenciasGenerar = new CJMPreferenciasGenerar(mediador);
        menuParametrizacion.add((CJMPreferenciasGenerar)cJMPreferenciasGenerar);
        menuAyuda = new JMenu("Ayuda");
        menuBar.add(menuAyuda);
        cJMAyudaContenidos = new CJMAyudaContenidos(mediador);
        menuAyuda.add((CJMAyudaContenidos)cJMAyudaContenidos);
        cJMAyudaAcercaDe = new CJMAyudaAcercaDe(mediador);
        menuAyuda.add((CJMAyudaAcercaDe)cJMAyudaAcercaDe);
        menuBar.show();
    }

    public JMenuBar getMenu()
    {
        return menuBar;
    }

    public static void EnabledComponenteBarraMenus(int posicion, int positem, int pos, boolean bEstado)
    {
        if(posicion < menuBar.getMenuCount())
        {
            if(positem < menuBar.getMenu(posicion).getItemCount())
            {
                JMenu aux = (JMenu)menuBar.getMenu(posicion).getMenuComponent(positem);
                if(pos < aux.getItemCount())
                {
                    aux.getItem(pos).setEnabled(bEstado);
                } else
                {
                    System.out.println("Te has salido del rango de componentes del sub-menu");
                    menuBar.repaint();
                }
            } else
            {
                System.out.println("Te has salido del rango de componentes del menu");
            }
        } else
        {
            System.out.println("Te has salido del rango de componentes de la barra de menus");
        }
    }

    public static void deshabilitarComponenteBarraMenus(int posicion, int positem)
    {
        if(posicion < menuBar.getMenuCount())
        {
            if(positem < menuBar.getMenu(posicion).getItemCount())
            {
                menuBar.getMenu(posicion).getItem(positem).setEnabled(false);
                menuBar.repaint();
            } else
            {
                System.out.println("Te has salido del rango de componentes del menu");
            }
        } else
        {
            System.out.println("Te has salido del rango de componentes de la barra de menus");
        }
    }

    public static void habilitarComponenteBarraMenus(int posicion, int positem)
    {
        if(posicion < menuBar.getMenuCount())
        {
            if(positem < menuBar.getMenu(posicion).getItemCount())
            {
                menuBar.getMenu(posicion).getItem(positem).setEnabled(true);
                menuBar.repaint();
            } else
            {
                System.out.println("Te has salido del rango de componentes del menu");
            }
        } else
        {
            System.out.println("Te has salido del rango de componentes de la barra de menus");
        }
    }

    public static void deshabilitarMenuBarraMenus(int posicion)
    {
        if(posicion < menuBar.getMenuCount())
        {
            menuBar.getMenu(posicion).setEnabled(false);
            menuBar.repaint();
        } else
        {
            System.out.println("Te has salido del rango de componentes de la barra de menus");
        }
    }

    public static void habilitarMenuBarraMenus(int posicion)
    {
        if(posicion < menuBar.getMenuCount())
        {
            menuBar.getMenu(posicion).setEnabled(true);
            menuBar.repaint();
        } else
        {
            System.out.println("Te has salido del rango de componentes de la barra de menus");
        }
    }

    JMenu menuArchivo;
    JMenu menuCaptura;
    JMenu menuDefinicion;
    JMenu menuInsercion;
    JMenu menuOperacion;
    JMenu menuParametrizacion;
    JMenu menuPreferecias;
    JMenu menuAyuda;
    JMenu menuExportar;
    JMenu menuPrefCaptura;
    JMenu menuPrefExportar;
    JMenu menuPrefFromFile;
    JMenu menuPrefGenerar;
    static JMenuBar menuBar;
    JMenuItem menuItem;
    Mediador mediador;
    Comando cJMConfiguracion;
    Comando cJMAbrirFichero;
    Comando cJMGuardarFicheroPcap;
    Comando cJMGuardarFicheroXML;
    Comando cJMGuardarDesdeFicheroAXML;
    Comando cJMSalir;
    Comando cJMInicioCapture;
    Comando cJMInicioInsertDef;
    Comando cJMInicioInsertCap;
    Comando cJMInicioDefinicion;
    Comando cJMFinCapture;
    Comando cJMFinInsert;
    Comando cJMInicioCaptureFromFile;
    Comando cJMPreferenciasCaptura;
    Comando cJMPreferenciasFromFile;
    Comando cJMPreferenciasGenerar;
    Comando cJMPreferenciasExportar;
    Comando cJMAyudaContenidos;
    Comando cJMAyudaAcercaDe;
    //Comando cJMIntrusos;
}
