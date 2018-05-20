package presentacion.preferencias;

import dominio.pcap.Insercion;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import presentacion.Mediador;
import presentacion.propiedadesVentana.CentrarVentana;
import servicioAccesoDatos.FachadaFicheroDirectorios;
/** 
 * Clase PreferenciasVisualizarInsercion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class PreferenciasVisualizarInsercion
  extends JDialog
{
  private static String title;
  private int tot;
  static Mediador mediador;
  public static Insercion insert = null;
  private JButton jBSalir;
  private JButton jButton1;
  private JLabel jLabel1;
  private static JLabel jlbImabgen;
  private static JLabel jlbNumIns;
  
  public PreferenciasVisualizarInsercion(String titulo, int total, Insercion ins)
  {
    super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Insercion", true);
    title = titulo;
    this.tot = total;
    insert = ins;
    initComponents();
    setResizable(false);
    new CentrarVentana(this);
  }
  
  private void initComponents()
  {
    this.jLabel1 = new JLabel();
    jlbNumIns = new JLabel();
    this.jButton1 = new JButton();
    jlbImabgen = new JLabel();
    this.jBSalir = new JButton();
    
    setDefaultCloseOperation(2);
    setName("Form");
    
    this.jLabel1.setText("Insertados :");
    this.jLabel1.setName("jLabel1");
    
    jlbNumIns.setName("jlbNumIns");
    
    this.jButton1.setText("Parar");
    this.jButton1.setName("jButton1");
//    this.jButton1.addMouseListener(new MouseAdapter()
//    {
//      public void mouseClicked(MouseEvent evt)
//      {
//        PreferenciasVisualizarInsercion.this.jButton1MouseClicked(evt);
//      }
//    });
    this.jButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PreferenciasVisualizarInsercion.this.jButton1ActionPerformed(evt);
      }
    });
    jlbImabgen.setName("jlbImabgen");
    
    this.jBSalir.setText("Salir");
    this.jBSalir.setName("jBSalir");
    this.jBSalir.setOpaque(false);
    this.jBSalir.setRequestFocusEnabled(false);
    this.jBSalir.setRolloverEnabled(false);
    this.jBSalir.setVerifyInputWhenFocusTarget(false);
    this.jBSalir.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent evt)
      {
        PreferenciasVisualizarInsercion.this.jBSalirMouseClicked(evt);
      }
    });
    this.jBSalir.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PreferenciasVisualizarInsercion.this.jBSalirActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jLabel1)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(jlbNumIns, -2, 65, -2))
      .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addComponent(this.jButton1, -2, 87, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 76, 32767)
      .addComponent(this.jBSalir, -2, 85, -2)))
      .addContainerGap())
      .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
      .addContainerGap(25, 32767)
      .addComponent(jlbImabgen, -2, 238, -2)
      .addGap(23, 23, 23)));
    
    layout.setVerticalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(jlbImabgen, -2, 72, -2)
      .addGap(18, 18, 18)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jLabel1)
      .addComponent(jlbNumIns, -2, 17, -2))
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jButton1, -1, 40, 32767)
      .addComponent(this.jBSalir, -2, 40, -2))
      .addContainerGap()));
    

    pack();
//    this.jButton1.addActionListener(new ActionListener()
//    {
//      public void actionPerformed(ActionEvent evt)
//      {
//        PreferenciasVisualizarInsercion.this.jButton1ActionPerformed(evt);
//      }
//    });
//    this.jButton1.addMouseListener(new MouseAdapter()
//    {
//      public void mouseClicked(MouseEvent evt)
//      {
//        PreferenciasVisualizarInsercion.this.jButton1MouseClicked(evt);
//      }
//    });
    jlbImabgen.setIcon(new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "transfer.gif"));
    
    pack();
  }
  
  public static void iniciarInsercion()
  {
	  
	  Insercion.continuar=true;
    if (title.equals("Insercion Paquetes Capturados")) {
      insert.start();
    }
    if (title.equals("Insertar Paquetes Definidos"))
    {
      System.out.println("entra en insertar paquetes");
      

      insert.start();
    }
  }
  
//  public static void detenerInsercion()
//  {
//    insert.stop();
//  }
  
  public static void setCont(int num)
  {
    jlbNumIns.setText(String.valueOf(num));
  }
  
  private void jButton1ActionPerformed(ActionEvent evt)
  {
	 Insercion.setContinuar();
	 
	 if (title.equals("Insertar Paquetes Definidos")) {
	      insert.stop();
	    }
	 //
	 cambiarParada();
  }
  
//  private void jButton1MouseClicked(MouseEvent evt)
//  {
//    insert.stop();
//    detenerInsercion();
//  }
  
  public static void cambiarParada()
  {
    jlbImabgen.setIcon(new ImageIcon(
      FachadaFicheroDirectorios.getdirectorioData("DIR_IMAGES") + 
      System.getProperty("file.separator") + "fin.gif"));
  }
  
  private void jBSalirMouseClicked(MouseEvent evt)
  {
    dispose();
  }
  
  private void jBSalirActionPerformed(ActionEvent evt)
  {
    dispose();
  }
}
