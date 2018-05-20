package presentacion;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;
import presentacion.propiedadesVentana.CentrarVentana;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase VentanaAcercaDe. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class VentanaAcercaDe
  extends JDialog
{
  private JButton jButton1;
  private JButton jButton2;
  private JLabel jLabel1;
  private JLabel jLabel10;
  private JLabel jLabel11;
  private JLabel jLabel12;
  private JLabel jLabel13;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JLabel jLabel8;
  private JLabel jLabel9;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JPanel jPanel4;
  
  public VentanaAcercaDe()
  {
    super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Acerca de Sniffer III", true);
    initComponents();
    setSize(328, 400);
    setResizable(false);
    
    new CentrarVentana(this);
    addWindowListener(new AdaptadorVentana());
    
    show();
  }
  
  private void initComponents()
  {
    this.jButton1 = new JButton();
    this.jPanel1 = new JPanel();
    this.jPanel2 = new JPanel();
    this.jButton2 = new JButton();
    this.jPanel3 = new JPanel();
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jLabel3 = new JLabel();
    this.jLabel4 = new JLabel();
    this.jPanel4 = new JPanel();
    this.jLabel5 = new JLabel();
    this.jLabel6 = new JLabel();
    this.jLabel7 = new JLabel();
    this.jLabel8 = new JLabel();
    this.jLabel9 = new JLabel();
    this.jLabel10 = new JLabel();
    this.jLabel11 = new JLabel();
    this.jLabel12 = new JLabel();
    this.jLabel13 = new JLabel();
    
    Runtime runtime = Runtime.getRuntime();
    runtime.gc();
    long totalMemory = runtime.totalMemory();
    long usedMemory = (totalMemory - runtime.freeMemory()) / 1024L;
    long maxMemory = runtime.maxMemory() / 1024L;
    
    setDefaultCloseOperation(2);
    setAlwaysOnTop(true);
    setResizable(false);
    this.jButton1.setIcon(new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + 
      "sniffer.gif"));
    this.jButton1.setHorizontalTextPosition(0);
    this.jButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        VentanaAcercaDe.this.dispose();
      }
    });
    this.jPanel1.setBorder(
      BorderFactory.createEtchedBorder(0));
    this.jButton2.setIcon(new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + 
      "escudo1.gif"));
    this.jButton2.setBorderPainted(false);
    this.jButton2.setContentAreaFilled(false);
    this.jButton2.setFocusPainted(false);
    this.jButton2.setFocusable(false);
    
    GroupLayout jPanel2Layout = new GroupLayout(
      this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
      1).add(this.jButton2, 
      -2, 74, 
      -2));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
      1).add(
      jPanel2Layout.createSequentialGroup().addContainerGap().add(
      this.jButton2, -1, 
      -1, 
      32767).addContainerGap()));
    
    this.jLabel1.setFont(new Font("Tahoma", 1, 36));
    this.jLabel1.setHorizontalAlignment(0);
    this.jLabel1.setText("Sniffer III");
    
    this.jLabel2.setFont(new Font("Times New Roman", 0, 12));
    this.jLabel2.setHorizontalAlignment(0);
    this.jLabel2.setText("para la creación de un");
    
    this.jLabel3.setFont(new Font("Times New Roman", 0, 12));
    this.jLabel3.setHorizontalAlignment(0);
    this.jLabel3.setText("conjunto de datos");
    
    this.jLabel4.setHorizontalAlignment(2);
    this.jLabel4.setText("Versión 1.7");
    
    GroupLayout jPanel3Layout = new GroupLayout(
      this.jPanel3);
    this.jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout
      .setHorizontalGroup(jPanel3Layout
      .createParallelGroup(
      1)
      .add(
      jPanel3Layout
      .createSequentialGroup()
      .addContainerGap()
      .add(
      jPanel3Layout
      .createParallelGroup(
      1)
      .add(
      jPanel3Layout
      .createSequentialGroup()
      .add(
      jPanel3Layout
      .createParallelGroup(
      1)
      .add(
      this.jLabel1, 
      -1, 
      -1, 
      32767)
      .add(
      2, 
      this.jLabel2, 
      -1, 
      150, 
      32767)
      .add(
      this.jLabel3, 
      -1, 
      150, 
      32767))
      .addContainerGap())
      .add(
      2, 
      this.jLabel4))));
    jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
      1).add(
      jPanel3Layout.createSequentialGroup().addContainerGap().add(
      this.jLabel1).add(14, 14, 14).add(this.jLabel2).addPreferredGap(
      0).add(this.jLabel3)
      .addPreferredGap(
      0, 14, 
      32767).add(this.jLabel4)));
    
    GroupLayout jPanel1Layout = new GroupLayout(
      this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
      1).add(
      jPanel1Layout.createSequentialGroup().addContainerGap().add(
      this.jPanel2, 
      -2, 
      -1, 
      -2)
      .addPreferredGap(
      0, 24, 
      32767).add(this.jPanel3, 
      -2, 
      -1, 
      -2)
      .addContainerGap()));
    jPanel1Layout
      .setVerticalGroup(jPanel1Layout
      .createParallelGroup(
      1)
      .add(
      jPanel1Layout
      .createSequentialGroup()
      .add(
      jPanel1Layout
      .createParallelGroup(
      1)
      .add(
      this.jPanel2, 
      -2, 
      -1, 
      -2)
      .add(
      jPanel1Layout
      .createSequentialGroup()
      .add(
      11, 
      11, 
      11)
      .add(
      this.jPanel3, 
      -1, 
      -1, 
      32767)))
      .addContainerGap()));
    
    this.jPanel4.setBorder(
      BorderFactory.createLineBorder(new Color(0, 0, 0)));
    this.jLabel5.setText("Tutor: ");
    
    this.jLabel6.setText("Jose Manuel Sáiz");
    
    this.jLabel7.setText("Autores:");
    
    this.jLabel8.setText("Carlos Mardones Muga");
    
    this.jLabel9.setText("");
    
    GroupLayout jPanel4Layout = new GroupLayout(
      this.jPanel4);
    this.jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout
      .setHorizontalGroup(jPanel4Layout
      .createParallelGroup(
      1)
      .add(
      jPanel4Layout
      .createSequentialGroup()
      .addContainerGap()
      .add(
      jPanel4Layout
      .createParallelGroup(
      1)
      .add(this.jLabel7)
      .add(
      jPanel4Layout
      .createSequentialGroup()
      .add(
      this.jLabel5)
      .add(
      jPanel4Layout
      .createParallelGroup(
      1)
      .add(
      jPanel4Layout
      .createSequentialGroup()
      .add(
      19, 
      19, 
      19)
      .add(
      jPanel4Layout
      .createParallelGroup(
      1)
      .add(
      this.jLabel8)
      .add(
      this.jLabel9)))
      .add(
      jPanel4Layout
      .createSequentialGroup()
      .add(
      29, 
      29, 
      29)
      .add(
      this.jLabel6)))))
      .addContainerGap(106, 32767)));
    jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
      1).add(
      jPanel4Layout.createSequentialGroup().addContainerGap().add(
      jPanel4Layout.createParallelGroup(
      3).add(
      this.jLabel5).add(this.jLabel6)).addPreferredGap(
      0).add(this.jLabel7)
      .addPreferredGap(
      0).add(
      this.jLabel8).addPreferredGap(
      0).add(
      this.jLabel9).addContainerGap(
      -1, 
      32767)));
    
    this.jLabel10.setText("Memoria Usada:");
    
    this.jLabel11.setText("Memoria Máxima:");
    
    this.jLabel12.setText(usedMemory + " Kb");
    
    this.jLabel13.setText(maxMemory + " Kb");
    
    GroupLayout layout = new GroupLayout(
      getContentPane());
    getContentPane().setLayout(layout);
    layout
      .setHorizontalGroup(layout
      .createParallelGroup(
      1)
      .add(
      layout
      .createSequentialGroup()
      .add(19, 19, 19)
      .add(
      layout
      .createParallelGroup(
      2)
      .add(
      1, 
      this.jPanel1, 
      -1, 
      -1, 
      32767)
      .add(
      1, 
      this.jPanel4, 
      -1, 
      -1, 
      32767)
      .add(
      layout
      .createSequentialGroup()
      .add(
      layout
      .createParallelGroup(
      1)
      .add(
      layout
      .createSequentialGroup()
      .add(
      this.jLabel11)
      .addPreferredGap(
      0)
      .add(
      this.jLabel13, 
      -2, 
      56, 
      -2))
      .add(
      layout
      .createSequentialGroup()
      .add(
      this.jLabel10)
      .addPreferredGap(
      0)
      .add(
      this.jLabel12, 
      -2, 
      70, 
      -2)))
      .addPreferredGap(
      0, 
      59, 
      32767)
      .add(
      this.jButton1)
      .add(
      15, 
      15, 
      15)))
      .add(363, 363, 363)));
    layout
      .setVerticalGroup(layout
      .createParallelGroup(
      1)
      .add(
      layout
      .createSequentialGroup()
      .addContainerGap()
      .add(
      this.jPanel1, 
      -2, 
      154, 
      -2)
      .addPreferredGap(
      0)
      .add(
      this.jPanel4, 
      -2, 
      -1, 
      -2)
      .add(16, 16, 16)
      .add(
      layout
      .createParallelGroup(
      1)
      .add(
      layout
      .createSequentialGroup()
      .add(
      layout
      .createParallelGroup(
      3)
      .add(
      this.jLabel10)
      .add(
      this.jLabel12, 
      -2, 
      22, 
      -2))
      .addPreferredGap(
      0)
      .add(
      layout
      .createParallelGroup(
      3)
      .add(
      this.jLabel11)
      .add(
      this.jLabel13, 
      -2, 
      20, 
      -2)))
      .add(this.jButton1))
      .addContainerGap(138, 32767)));
    pack();
  }
  
  private class AdaptadorVentana
    extends WindowAdapter
  {
    private AdaptadorVentana() {}
    
    public void windowClosing(WindowEvent e)
    {
      VentanaAcercaDe.this.dispose();
    }
  }
  
  private class OyenteCerrar
    implements ActionListener
  {
    private OyenteCerrar() {}
    
    public void actionPerformed(ActionEvent e)
    {
      VentanaAcercaDe.this.dispose();
    }
  }
}
