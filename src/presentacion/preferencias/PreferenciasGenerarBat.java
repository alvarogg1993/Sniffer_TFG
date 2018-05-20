package presentacion.preferencias;

import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import javax.accessibility.AccessibleContext;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;
import presentacion.Mediador;
import presentacion.comandos.CBAbrirFichero;
import presentacion.comandos.CBAceptar;
import presentacion.comandos.CBGuardarFichero;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;
/** 
 * Clase PreferenciasGenerarBat. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class PreferenciasGenerarBat
  extends JDialog
{
  Mediador mediador;
  private Comando jButton1;
  private Comando jButton2;
  private Comando jButton3;
  private static ButtonGroup buttonGroup1;
  private static ButtonGroup buttonGroup2;
  private ButtonGroup buttonGroup3;
  private JRadioButton jRadioButtonUnix;
  private JRadioButton jRadioButtonWindows;
  private JButton jButtonCancelar;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JPanel jPanel4;
  private JRadioButton jRadioButtonDefUsuario;
  private JRadioButton jRadioButtonExport;
  private JRadioButton jRadioButtonFromFile;
  private static JRadioButton jRadioButtonPorDefecto;
  private JRadioButton jRadioButtonScan;
  private JScrollPane jScrollPane1;
  private JTextArea jTextArea1;
  private static JTextField jTextField1;
  private static JTextField jTextField2;
  private static JTextField jTextFieldMegas;
  
  public PreferenciasGenerarBat()
  {
    super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Generar Script", true);
    this.mediador = new Mediador();
    initComponents();
    setResizable(false);
    new CentrarVentana(this);
    setVisible(true);
  }
  
  public PreferenciasGenerarBat(Mediador med)
  {
    super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Generar Script", true);
    this.mediador = med;
    initComponents();
    setResizable(false);
    new CentrarVentana(this);
    setVisible(true);
  }
  
  private void initComponents()
  {
    buttonGroup1 = new ButtonGroup();
    this.jScrollPane1 = new JScrollPane();
    this.jTextArea1 = new JTextArea();
    buttonGroup2 = new ButtonGroup();
    this.buttonGroup3 = new ButtonGroup();
    this.jPanel1 = new JPanel();
    this.jRadioButtonScan = new JRadioButton();
    this.jRadioButtonExport = new JRadioButton();
    this.jRadioButtonFromFile = new JRadioButton();
    this.jPanel2 = new JPanel();
    this.jLabel1 = new JLabel();
    jTextField1 = new JTextField();
    
    this.jButton1 = new CBAbrirFichero(this.mediador, "GenerarBatPreferencias");
    this.jLabel2 = new JLabel();
    jTextField2 = new JTextField();
    
    this.jButton2 = new CBGuardarFichero(this.mediador, "GenerarBatBat");
    this.jButtonCancelar = new JButton();
    this.jPanel3 = new JPanel();
    this.jPanel4 = new JPanel();
    jRadioButtonPorDefecto = new JRadioButton();
    this.jRadioButtonDefUsuario = new JRadioButton();
    jTextFieldMegas = new JTextField();
    this.jLabel3 = new JLabel();
    
    this.jButton3 = new CBAceptar(this.mediador, "GenerarScript");
    
    this.jRadioButtonWindows = new JRadioButton();
    this.jRadioButtonUnix = new JRadioButton();
    
    this.jTextArea1.setColumns(20);
    this.jTextArea1.setRows(5);
    this.jScrollPane1.setViewportView(this.jTextArea1);
    
    setDefaultCloseOperation(2);
    this.jPanel1.setBorder(BorderFactory.createTitledBorder("Tipo"));
    buttonGroup1.add(this.jRadioButtonScan);
    this.jRadioButtonScan.setMnemonic('c');
    this.jRadioButtonScan.setSelected(true);
    this.jRadioButtonScan.setText("captura");
    this.jRadioButtonScan.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.jRadioButtonScan.setMargin(new Insets(0, 0, 0, 0));
    this.jRadioButtonScan.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasGenerarBat.this.jRadioButtonScanItemStateChanged(evt);
      }
    });
    buttonGroup1.add(this.jRadioButtonExport);
    this.jRadioButtonExport.setMnemonic('e');
    this.jRadioButtonExport.setText("exporta");
    this.jRadioButtonExport.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    
    this.jRadioButtonExport.setMargin(new Insets(0, 0, 0, 0));
    this.jRadioButtonExport.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasGenerarBat.this.jRadioButtonExportItemStateChanged(evt);
      }
    });
    buttonGroup1.add(this.jRadioButtonFromFile);
    this.jRadioButtonFromFile.setMnemonic('d');
    this.jRadioButtonFromFile.setText("desde fichero");
    this.jRadioButtonFromFile.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    
    this.jRadioButtonFromFile.setMargin(new Insets(0, 0, 0, 0));
    
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(1)
      .add(jPanel1Layout.createSequentialGroup()
      .add(26, 26, 26)
      .add(jPanel1Layout.createParallelGroup(1)
      .add(this.jRadioButtonFromFile)
      .add(this.jRadioButtonExport)
      .add(this.jRadioButtonScan))
      .addContainerGap(36, 32767)));
    
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(1)
      .add(jPanel1Layout.createSequentialGroup()
      .addContainerGap()
      .add(this.jRadioButtonScan)
      .addPreferredGap(0)
      .add(this.jRadioButtonExport)
      .addPreferredGap(0)
      .add(this.jRadioButtonFromFile)
      .addContainerGap(-1, 32767)));
    

    this.jPanel2.setBorder(BorderFactory.createTitledBorder("Datos"));
    this.jLabel1.setText("Preferencias: ");
    



    this.jLabel2.setText("Script:");
    


    GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(1)
      .add(jPanel2Layout.createSequentialGroup()
      .addContainerGap()
      .add(jPanel2Layout.createParallelGroup(1)
      .add(this.jLabel2)
      .add(this.jLabel1))
      .addPreferredGap(0)
      .add(jPanel2Layout.createParallelGroup(1, false)
      .add(jTextField1, -2, 165, -2)
      .add(jTextField2))
      .addPreferredGap(0)
      .add(jPanel2Layout.createParallelGroup(1)
      .add((CBGuardarFichero)this.jButton2)
      .add((CBAbrirFichero)this.jButton1))));
    
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(1)
      .add(jPanel2Layout.createSequentialGroup()
      .addContainerGap()
      .add(jPanel2Layout.createParallelGroup(3)
      .add(this.jLabel1)
      .add((CBAbrirFichero)this.jButton1)
      .add(jTextField1, -2, -1, -2))
      .addPreferredGap(0, 19, 32767)
      .add(jPanel2Layout.createParallelGroup(3)
      .add(this.jLabel2)
      .add(jTextField2, -2, -1, -2)
      .add((CBGuardarFichero)this.jButton2))
      .add(20, 20, 20)));
    

    this.jPanel2.getAccessibleContext().setAccessibleName("Datos");
    





    this.jButtonCancelar.setText("Cancelar");
    this.jButtonCancelar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PreferenciasGenerarBat.this.jButtonCancelarActionPerformed(evt);
      }
    });
    this.jPanel3.setBorder(BorderFactory.createTitledBorder("Memoria MVJ"));
    this.buttonGroup3.add(jRadioButtonPorDefecto);
    jRadioButtonPorDefecto.setMnemonic('p');
    jRadioButtonPorDefecto.setSelected(true);
    jRadioButtonPorDefecto.setText("... por defecto");
    jRadioButtonPorDefecto.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jRadioButtonPorDefecto.setMargin(new Insets(0, 0, 0, 0));
    
    this.buttonGroup3.add(this.jRadioButtonDefUsuario);
    this.jRadioButtonDefUsuario.setMnemonic('f');
    this.jRadioButtonDefUsuario.setText("... definido usuario");
    this.jRadioButtonDefUsuario.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.jRadioButtonDefUsuario.setMargin(new Insets(0, 0, 0, 0));
    this.jRadioButtonDefUsuario.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasGenerarBat.this.jRadioButtonDefUsuarioItemStateChanged(evt);
      }
    });
    jTextFieldMegas.setText("200");
    jTextFieldMegas.setEnabled(false);
    
    this.jLabel3.setText("megas");
    this.jLabel3.setEnabled(false);
    
    GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
    this.jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(1)
      .add(jPanel3Layout.createSequentialGroup()
      .add(28, 28, 28)
      .add(jPanel3Layout.createParallelGroup(1)
      .add(jRadioButtonPorDefecto)
      .add(jPanel3Layout.createSequentialGroup()
      .add(17, 17, 17)
      .add(jTextFieldMegas, -2, 39, -2)
      .addPreferredGap(0)
      .add(this.jLabel3))
      .add(this.jRadioButtonDefUsuario))
      .addContainerGap(53, 32767)));
    
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(1)
      .add(jPanel3Layout.createSequentialGroup()
      .addContainerGap()
      .add(jRadioButtonPorDefecto)
      .addPreferredGap(0)
      .add(this.jRadioButtonDefUsuario)
      .addPreferredGap(0)
      .add(jPanel3Layout.createParallelGroup(2)
      .add(this.jLabel3)
      .add(jTextFieldMegas, -2, -1, -2))
      .addContainerGap(7, 32767)));
    



    this.jPanel4.setBorder(BorderFactory.createTitledBorder("S.O."));
    buttonGroup2.add(this.jRadioButtonWindows);
    this.jRadioButtonWindows.setMnemonic('w');
    this.jRadioButtonWindows.setSelected(true);
    this.jRadioButtonWindows.setText("Windows");
    this.jRadioButtonWindows.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.jRadioButtonWindows.setMargin(new Insets(0, 0, 0, 0));
    this.jRadioButtonWindows.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt) {}
    });
    buttonGroup2.add(this.jRadioButtonUnix);
    this.jRadioButtonUnix.setMnemonic('u');
    this.jRadioButtonUnix.setText("Unix");
    this.jRadioButtonUnix.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    this.jRadioButtonUnix.setMargin(new Insets(0, 0, 0, 0));
    this.jRadioButtonUnix.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasGenerarBat.this.jRadioButtonUnixItemStateChanged(evt);
      }
    });
    GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
    this.jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(1)
      .add(jPanel4Layout.createSequentialGroup()
      .add(26, 26, 26)
      .add(jPanel4Layout.createParallelGroup(1)
      .add(this.jRadioButtonUnix)
      .add(this.jRadioButtonWindows))
      .addContainerGap(32, 32767)));
    
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(1)
      .add(jPanel4Layout.createSequentialGroup()
      .add(this.jRadioButtonWindows)
      .addPreferredGap(0)
      .add(this.jRadioButtonUnix)
      .addContainerGap(-1, 32767)));
    
    this.jPanel4.getAccessibleContext().setAccessibleName("S.O.");
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(1)
      .add(layout.createSequentialGroup()
      .addContainerGap()
      .add(layout.createParallelGroup(1)
      .add(this.jPanel2, -1, -1, 32767)
      .add(layout.createSequentialGroup()
      .add(layout.createParallelGroup(2, false)
      .add(this.jPanel4, -1, -1, 32767)
      .add(this.jPanel1, -1, -1, 32767))
      .add(layout.createParallelGroup(1)
      .add(layout.createSequentialGroup()
      .addPreferredGap(0)
      .add(this.jPanel3, -1, -1, 32767))
      .add(2, layout.createSequentialGroup()
      .add(18, 18, 18)
      .add(this.jButtonCancelar)
      .addPreferredGap(0)
      .add((CBAceptar)this.jButton3)))))
      .addContainerGap()));
    
    layout.setVerticalGroup(
      layout.createParallelGroup(1)
      .add(layout.createSequentialGroup()
      .addContainerGap()
      .add(this.jPanel2, -2, -1, -2)
      .addPreferredGap(0)
      .add(layout.createParallelGroup(1)
      .add(this.jPanel3, 0, 109, 32767)
      .add(this.jPanel1, -2, -1, -2))
      .add(layout.createParallelGroup(1)
      .add(layout.createSequentialGroup()
      .addPreferredGap(0, 56, 32767)
      .add(layout.createParallelGroup(3)
      .add((CBAceptar)this.jButton3)
      .add(this.jButtonCancelar))
      .add(19, 19, 19))
      .add(layout.createSequentialGroup()
      .add(10, 10, 10)
      .add(this.jPanel4, -2, -1, -2)
      .addContainerGap()))));
    


















































































































    pack();
  }
  
  private void jButton3ActionPerformed(ActionEvent evt)
  {
    dispose();
  }
  
  private void jRadioButtonDefUsuarioItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 1)
    {
      jTextFieldMegas.setEnabled(true);
      this.jLabel3.setEnabled(true);
    }
    else
    {
      jTextFieldMegas.setEnabled(false);
      this.jLabel3.setEnabled(false);
    }
  }
  
  private void jRadioButtonUnixItemStateChanged(ItemEvent evt) {}
  
  private void jRadioButtonWindowsActionPerformed(ItemEvent evt) {}
  
  private void jRadioButtonScanItemStateChanged(ItemEvent evt) {}
  
  private void jRadioButtonExportItemStateChanged(ItemEvent evt) {}
  
  private void jButtonCancelarActionPerformed(ActionEvent evt)
  {
    dispose();
  }
  
  public static void setFicheroPref(String aux)
  {
    jTextField1.setText(aux);
  }
  
  public static String getFicheroPref()
  {
    return jTextField1.getText();
  }
  
  public static void setFicheroBat(String aux)
  {
    jTextField2.setText(aux);
  }
  
  public static String getFicheroBat()
  {
    return jTextField2.getText();
  }
  
  public static String getTipo()
  {
    String aux = "";
    for (Enumeration e = buttonGroup1.getElements(); e.hasMoreElements();)
    {
      JRadioButton b = (JRadioButton)e.nextElement();
      if (b.getModel() == buttonGroup1.getSelection()) {
        aux = b.getText();
      }
    }
    return aux;
  }
  
  public static String getSO()
  {
    String aux = "";
    for (Enumeration e = buttonGroup2.getElements(); e.hasMoreElements();)
    {
      JRadioButton b = (JRadioButton)e.nextElement();
      if (b.getModel() == buttonGroup2.getSelection()) {
        aux = b.getText();
      }
    }
    return aux;
  }
  
  public static String getMvM()
  {
    String aux;

    if (jRadioButtonPorDefecto.isSelected()) {
      aux = "-1";
    } else {
      aux = jTextFieldMegas.getText();
    }
    return aux;
  }
}
