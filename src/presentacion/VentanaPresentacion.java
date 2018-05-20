package presentacion;

import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;
import presentacion.comandos.CBIniciarSalirAplicacion;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase VentanaPresentacion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class VentanaPresentacion
  extends JFrame
{
  Mediador mediador;
  private Comando comandoIniciarSalirAplicacion;
  private Comando jButton1;
  private JButton jButton2;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  
  public VentanaPresentacion()
  {
    super("Presentación Sniffer III");
    initBottons();
    initComponents();
    new CentrarVentana(this);
    
    addWindowListener(new AdaptadorVentana());
    
    setIconImage(Toolkit.getDefaultToolkit().getImage(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "sniffer.gif"));
    show();
  }
  
  public VentanaPresentacion(boolean aux)
  {
    this.mediador = new Mediador();
    this.mediador.irAventana("Sniffer");
    this.mediador.PrefLeerPreferencias();
    this.mediador.PropLeerProperties();
    this.mediador.cargardirectorios();
  }
  
  private void initBottons()
  {
    this.mediador = new Mediador();
    ImageIcon IconoNuevo = new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "sniffer.gif");
    this.jButton1 = new CBIniciarSalirAplicacion(this.mediador, "Sniffer", IconoNuevo);
  }
  
  private void initComponents()
  {
    this.jPanel1 = new JPanel();
    this.jPanel2 = new JPanel();
    this.jButton2 = new JButton();
    this.jPanel3 = new JPanel();
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jLabel3 = new JLabel();
    this.jLabel4 = new JLabel();
    
    this.jPanel1.setBorder(BorderFactory.createEtchedBorder(0));
    this.jButton2.setIcon(new ImageIcon(FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "escudo1.gif"));
    this.jButton2.setBorderPainted(false);
    this.jButton2.setContentAreaFilled(false);
    this.jButton2.setFocusPainted(false);
    this.jButton2.setFocusable(false);
    
    GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(1).add(this.jButton2, -2, 74, -2));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(this.jButton2, -1, -1, 32767).addContainerGap()));
    
    this.jLabel1.setFont(new Font("Tahoma", 1, 36));
    this.jLabel1.setHorizontalAlignment(0);
    this.jLabel1.setText("Sniffer III");
    
    this.jLabel2.setFont(new Font("Times New Roman", 0, 12));
    this.jLabel2.setHorizontalAlignment(0);
    this.jLabel2.setText("Leonardo García");
    
    this.jLabel3.setFont(new Font("Times New Roman", 0, 12));
    this.jLabel3.setHorizontalAlignment(0);
    this.jLabel3.setText("Jose-Ramón Gutiérrez");
    
    this.jLabel4.setHorizontalAlignment(2);
    this.jLabel4.setText("Versión 2.0");
    
    GroupLayout jPanel3Layout = new GroupLayout( this.jPanel3);
    this.jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(1).add(jPanel3Layout.createSequentialGroup().addContainerGap().add(jPanel3Layout.createParallelGroup(1).add(jPanel3Layout.createSequentialGroup().add(jPanel3Layout.createParallelGroup(1).add(this.jLabel1, -1, -1,32767).add(2,this.jLabel2,-1,150,32767).add( this.jLabel3,-1,150,32767)).addContainerGap()).add( 2, this.jLabel4))));
    jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(1).add(jPanel3Layout.createSequentialGroup().addContainerGap().add(this.jLabel1).add(14, 14, 14).add(this.jLabel2).addPreferredGap(0).add(this.jLabel3).addPreferredGap(0, 14, 32767).add(this.jLabel4)));
    
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(this.jPanel2,-2,-1,-2).addPreferredGap(0,24,32767).add(this.jPanel3,-2,-1,-2).addContainerGap()));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jPanel1Layout.createParallelGroup(1).add(this.jPanel2,-2,-1,-2).add(jPanel1Layout.createSequentialGroup().add(11,11,11).add(this.jPanel3,-1,-1,32767))).addContainerGap()));
    GroupLayout layout = new GroupLayout(getContentPane());
    
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1,-2,-1,-2).addContainerGap(-1,32767)).add(2,layout.createSequentialGroup().addContainerGap(201, 32767).add((CBIniciarSalirAplicacion)this.jButton1).addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(this.jPanel1,-2, 154,-2).add(14,14,14).add((CBIniciarSalirAplicacion)this.jButton1).addContainerGap(-1,32767)));
    pack();
  }
  
  private class AdaptadorVentana
    extends WindowAdapter
  {
    private AdaptadorVentana() {}
    
    public void windowClosing(WindowEvent e)
    {
      System.exit(1);
    }
  }
}
