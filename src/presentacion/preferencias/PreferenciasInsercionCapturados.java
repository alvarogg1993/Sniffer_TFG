package presentacion.preferencias;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import presentacion.Mediador;
import presentacion.comandos.CBAbrirFichero;
import presentacion.comandos.CBAceptar;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;
/** 
 * Clase PreferenciasInsercionCapturado. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class PreferenciasInsercionCapturados
  extends JDialog
{
  private static String title;
  private static String[] Dispositivos = { "Seleccione un dispositivo" };
  private static String[] Dispo;
  private static int rep = 0;
  private static String dispSel = "";
  Mediador mediador;
  Comando jBAbrirExportacion;
  Comando jBInsertar;
  Comando jBcancelar;
  private ButtonGroup buttonGroup1;
  private static JComboBox jComboBox1;
  private JComboBox jComboBox2;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JPanel jPanel1;
  private JRadioButton jRadioButton1;
  private JRadioButton jRadioButton2;
  private JRadioButton jRdet;
  private JRadioButton jRindet;
  private JRadioButton jRunicas;
  private static JTextField jTextField1;
  private JTextField jTextField2;
  private JTextField jTextField3;
  
  public PreferenciasInsercionCapturados()
  {
    super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Insercion de Paquetes Capturados", true);
    title = "Insercion";
    
    this.mediador = new Mediador();
    initComponents();
    ((CBAbrirFichero)this.jBAbrirExportacion).setVisible(true);
    ((CBAceptar)this.jBInsertar).setVisible(true);
    ((CBAceptar)this.jBcancelar).setVisible(true);
    setResizable(false);
    new CentrarVentana(this);
  }
  
  private void initComponents()
  {
    this.buttonGroup1 = new ButtonGroup();
    this.jPanel1 = new JPanel();
    jTextField1 = new JTextField();
    
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jRunicas = new JRadioButton();
    this.jRdet = new JRadioButton();
    this.jRindet = new JRadioButton();
    this.jTextField2 = new JTextField();
    this.jLabel3 = new JLabel();
    jComboBox1 = new JComboBox();
    this.jLabel4 = new JLabel();
    this.jRadioButton1 = new JRadioButton();
    this.jRadioButton2 = new JRadioButton();
    this.jTextField3 = new JTextField();
    this.jComboBox2 = new JComboBox();
    


    this.jBAbrirExportacion = new CBAbrirFichero(this.mediador, "Abrir fichero de Capturas Insercion...");
    this.jBInsertar = new CBAceptar(this.mediador, "Insertar Paquetes Capturados");
    this.jBcancelar = new CBAceptar(this.mediador, "Salir");
    
    setDefaultCloseOperation(2);
    setName("Form");
    
    this.jPanel1.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(153, 204, 255), 2, true), "", 0, 0, new Font("Tahoma", 0, 11), new Color(153, 204, 255)));
    this.jPanel1.setName("jPanel1");
    
    jTextField1.setName("jTextField1");
    


    this.jLabel1.setText("Exportación:");
    this.jLabel1.setName("jLabel1");
    
    this.jLabel2.setText("Nº Renvios:");
    this.jLabel2.setName("jLabel2");
    
    this.buttonGroup1.add(this.jRunicas);
    this.jRunicas.setText("Unico");
    this.jRunicas.setName("jRunicas");
    this.jRunicas.addFocusListener(new FocusAdapter()
    {
      public void focusGained(FocusEvent evt)
      {
        PreferenciasInsercionCapturados.this.jRunicasFocusGained(evt);
      }
    });
    this.buttonGroup1.add(this.jRdet);
    this.jRdet.setText("Repeticiones determinadas");
    this.jRdet.setName("jRdet");
    this.jRdet.addFocusListener(new FocusAdapter()
    {
      public void focusGained(FocusEvent evt)
      {
        PreferenciasInsercionCapturados.this.jRdetFocusGained(evt);
      }
    });
    this.buttonGroup1.add(this.jRindet);
    this.jRindet.setText("Repeticiones ilimitadas");
    this.jRindet.setName("jRindet");
    this.jRindet.addFocusListener(new FocusAdapter()
    {
      public void focusGained(FocusEvent evt)
      {
        PreferenciasInsercionCapturados.this.jRindetFocusGained(evt);
      }
    });
    this.jTextField2.setName("jTextField2");
    this.jTextField2.enable(false);
    this.jTextField2.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent evt)
      {
        PreferenciasInsercionCapturados.this.jTextField2FocusLost(evt);
      }
    });
    this.jLabel3.setText("Dispositivo:");
    this.jLabel3.setName("jLabel3");
    
    jComboBox1.setName("jComboBox1");
    


    this.jRadioButton1.setText("Manual");
    this.jRadioButton1.setName("jRadioButton1");
    
    this.jRadioButton2.setText("Capturado");
    this.jRadioButton2.setName("jRadioButton2");
    
    this.jTextField3.setName("jTextField3");
    
    this.jComboBox2.setName("jComboBox2");
    
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
      .addContainerGap()
      .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
      .addComponent(this.jLabel2, -1, 67, 32767)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(this.jRindet)
      .addGroup(jPanel1Layout.createSequentialGroup()
      .addComponent(this.jRdet)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(this.jTextField2, -2, 59, -2))
      .addComponent(this.jRunicas))
      .addContainerGap(323, 32767))
      .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
      .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
      .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
      .addComponent(this.jLabel3)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(jComboBox1, 0, 532, 32767))
      .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
      .addComponent(this.jLabel1, -2, 80, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(jTextField1, -2, 440, -2)
      .addGap(18, 18, 18)
      .addComponent((CBAbrirFichero)this.jBAbrirExportacion, -1, 68, 32767)))
      .addGap(19, 19, 19)))));
    
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(this.jLabel3)
      .addComponent(jComboBox1, -2, -1, -2))
      .addGap(49, 49, 49)
      .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(this.jLabel1)
      .addComponent(jTextField1, -2, -1, -2)
      .addComponent((CBAbrirFichero)this.jBAbrirExportacion, -2, 31, -2))
      .addGap(29, 29, 29)
      .addComponent(this.jRunicas)
      .addGap(14, 14, 14)
      .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(this.jLabel2)
      .addComponent(this.jRdet)
      .addComponent(this.jTextField2, -2, -1, -2))
      .addGap(18, 18, 18)
      .addComponent(this.jRindet)
      .addContainerGap(19, 32767)));
    





    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jPanel1, -2, -1, -2)
      .addContainerGap(-1, 32767))
      .addGroup(layout.createSequentialGroup()
      .addGap(121, 121, 121)
      .addComponent((CBAceptar)this.jBInsertar, -2, 141, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 182, 32767)
      .addComponent((CBAceptar)this.jBcancelar, -2, 141, -2)
      .addGap(161, 161, 161)));
    
    layout.setVerticalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jPanel1, -2, -1, -2)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
      .addGroup(layout.createSequentialGroup()
      .addGap(33, 33, 33)
      .addComponent((CBAceptar)this.jBInsertar, -1, 141, 32767))
      .addGroup(layout.createSequentialGroup()
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent((CBAceptar)this.jBcancelar, -2, 141, -2)))
      .addGap(32, 32, 32)));
    






    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jPanel1, -1, -1, 32767))
      .addGroup(layout.createSequentialGroup()
      .addGap(150, 150, 150)
      .addComponent((CBAceptar)this.jBInsertar, -2, 90, -2)
      .addGap(140, 140, 140)
      .addComponent((CBAceptar)this.jBcancelar, -2, 90, -2)))
      .addContainerGap()));
    
    layout.setVerticalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jPanel1, -2, -1, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
      .addComponent((CBAceptar)this.jBInsertar, -2, 40, -2)
      .addComponent((CBAceptar)this.jBcancelar, -1, 40, 32767))
      .addContainerGap()));
    
    jComboBox1.setModel(new DefaultComboBoxModel(Dispositivos));
    this.jRunicas.addActionListener(null);
    jComboBox1.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent evt)
      {
        PreferenciasInsercionCapturados.this.jComboBox1FocusLost(evt);
      }
    });
    pack();
  }
  
  public static void addDispositivo(String[] dispo, String[] des)
  {
    Dispo = new String[dispo.length + 1];
    Dispo[0] = "";
    for (int i = 0; i < dispo.length; i++)
    {
      String sAux = des[i].toString().trim();
      String name;

      if (sAux != "") {
        name = sAux;
      } else {
        name = dispo[i].toString().trim();
      }
      jComboBox1.addItem(name);
      Dispo[(i + 1)] = dispo[i];
    }
  }
  
  public static void setFicheroCapturas(String fruta)
  {
    jTextField1.setText(fruta);
  }
  
  private void jRunicasFocusGained(FocusEvent evt)
  {
    this.jTextField2.setText("");
    rep = 1;
  }
  
  private void jRdetFocusGained(FocusEvent evt)
  {
    this.jTextField2.enable(true);
  }
  
  private void jRindetFocusGained(FocusEvent evt)
  {
    this.jTextField2.setText("");
    rep = -1;
  }
  
  public static String getRuta()
  {
    return jTextField1.getText();
  }
  
  public static int getRepeticiones()
  {
    return rep;
  }
  
  public static String getDispositivo()
  {
    return dispSel;
  }
  
  private void jTextField2FocusLost(FocusEvent evt)
  {
    rep = Integer.valueOf(this.jTextField2.getText()).intValue();
  }
  
  public static int getEnvios()
  {
    return rep;
  }
  
  private void jComboBox1FocusLost(FocusEvent evt)
  {
    dispSel = String.valueOf(jComboBox1.getSelectedItem());
  }
}
