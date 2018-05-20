package presentacion.ventanaMenuSniffer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import presentacion.Mediador;
import presentacion.VentanaSalir;
import presentacion.visualizarCaptura.VisualizarCaptura;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase MenuSniffer. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class MenuSniffer
  extends JFrame
{
  JPanel statusPanel;
  static JLabel statusMsg1;
  static JLabel statusMsg2;
  VisualizarCaptura VC;
  Mediador mediador;
  public int WinX;
  public int WinY;
  public int WinWidth;
  public int WinHeight;
  
  public MenuSniffer(Mediador med)
  {
    super("Sniffer III");
    setVisible(false);
    this.mediador = med;
    setDefaultCloseOperation(0);
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent e)
      {
        new VentanaSalir(MenuSniffer.this.mediador);
        System.out.println("Cerro Correctamente");
      }
      
      public void windowClosed(WindowEvent e) {}
      
      public void windowDeactivated(WindowEvent e) {}
      
      public void windowActivated(WindowEvent e) {}
      
      public void windowIconified(WindowEvent e) {}
      
      public void windowDeiconified(WindowEvent e) {}
    });
    setVisible(false);

    setIconImage(Toolkit.getDefaultToolkit().getImage(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") +  "sniffer.gif"));
    Container contentPane = getContentPane();
    
    show();
    
    BarraMenu menus = new BarraMenu(this.mediador);
    setJMenuBar(menus.getMenu());
    


    BarraHerramientas barraHerramientas = new BarraHerramientas(this.mediador);
    
    contentPane.add(BarraHerramientas.getBarraHerramientas(), 
      "North");
    setContentPane(contentPane);
    
    med.deshabilitarBHelemento(3);
    med.deshabilitarBHelemento(4);
    med.deshabilitarComponenteBarraMenus(1, 1);
    med.deshabilitarComponenteBarraMenus(0, 1);
    
    med.EnabledComponenteBarraMenus(0, 3, 0, false);
    
    this.statusPanel = new JPanel();
    this.statusPanel.setLayout(new BorderLayout());
    

    statusMsg1 = new JLabel("Estado: ");
    statusMsg1.setFont(new Font("Times-Roman", 0, 12));
    
    statusMsg2 = new JLabel("Sniffer-III iniciado");
    statusMsg2.setFont(new Font("Times-Roman", 0, 12));
    this.statusPanel.add(statusMsg1, "Before");
    this.statusPanel.add(statusMsg2, "Center");
    this.statusPanel.setBorder(new EtchedBorder(
      0));
    
    getContentPane().add(this.statusPanel, "South");
    
    this.VC = new VisualizarCaptura(this.mediador);
    contentPane.add(this.VC.getPanel(), "Center");
    
    this.mediador.leerProperties(this);
  }
  
  public void runVisualizacionCaptura()
  {
    this.VC.run();
  }
  
  public void abrirFichero(String file)
  {
    this.VC.setFile(file);
    runVisualizacionCaptura();
  }
  
  public static void setStatusPanel(String estado)
  {
    statusMsg2.setText(estado);
  }
  
  public void recargarDatos()
  {
    this.VC.refreshCapture();
  }
  
  public String getColumPosition()
  {
    return this.VC.getColumnPosition();
  }
  
  public void clearScreen()
  {
    this.VC.resetGraficos();
  }
}
