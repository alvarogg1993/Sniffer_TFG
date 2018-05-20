package presentacion.preferencias;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import presentacion.Mediador;
import presentacion.comandos.CBAbrirFichero;
import presentacion.comandos.CBAceptar;
import presentacion.comandos.CBChequear;
import presentacion.comandos.CBGuardarFichero;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;
/** 
 * Clase PreferenciasDefinicion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class PreferenciasDefinicion
  extends JDialog
{
  private static String title;
  private static boolean tipo;
  private static String nomProtocolo = "";
  private static String rfc = "";
  private static int numCampos = 0;
  private static String camposClave = "";
  private static int nivel = 0;
  private static int numColumnas = 8;
  private static JTable jTable1;
  Mediador mediador;
  Comando jButton4;
  Comando jButton1;
  Comando jButton3;
  Comando jButton7;
  Comando jButton2;
  Comando jButton5;
  private static JComboBox jComboBox1;
  private static JComboBox jComboBox2;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private JLabel jLabel6;
  private JLabel jLabel7;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JScrollPane jScrollPane1;
  private static JTextField jTextField1;
  private static JTextField jTextField2;
  private static JTextField jTextField3;
  private static JTextField jTextField4;
  private static JTextField jTextField5;
  
  public PreferenciasDefinicion()
  {
    super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Definicion", true);
    title = "Inicio Definicion";
    tipo = true;
    this.mediador = new Mediador();
    initComponents();
    ((CBAbrirFichero)this.jButton4).setVisible(true);
    ((CBChequear)this.jButton3).setVisible(true);
    ((CBGuardarFichero)this.jButton7).setVisible(true);
    ((CBAceptar)this.jButton2).setVisible(true);
    setResizable(false);
    new CentrarVentana(this);
  }
  
  private void initComponents()
  {
    this.jPanel2 = new JPanel();
    jTextField2 = new JTextField();
    this.jLabel2 = new JLabel();
    this.jPanel3 = new JPanel();
    this.jScrollPane1 = new JScrollPane();
    jTable1 = new JTable();
    this.jLabel3 = new JLabel();
    jTextField3 = new JTextField();
    this.jLabel4 = new JLabel();
    jTextField4 = new JTextField();
    this.jLabel5 = new JLabel();
    jTextField5 = new JTextField();
    this.jLabel6 = new JLabel();
    this.jLabel1 = new JLabel();
    jTextField1 = new JTextField();
    this.jLabel7 = new JLabel();
    jComboBox1 = new JComboBox();
    jComboBox2 = new JComboBox();
    
    this.jButton4 = new CBAbrirFichero(this.mediador, "AbrirDefinicionProtocolo");
    this.jButton7 = new CBGuardarFichero(this.mediador, "GuardarProtocoloDefinido");
    this.jButton3 = new CBChequear(this.mediador, "ChequearDefinicionProtocolo");
    this.jButton2 = new CBAceptar(this.mediador, "Salir");
    this.jButton5 = new CBAceptar(this.mediador, "RefrescarTablaProtocolo");
    
    setDefaultCloseOperation(2);
    setTitle("Definicion de Protocolos");
    setName("Form");
    
    this.jPanel2.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(153, 204, 255), 2, true), "Protocolo/os generado/os :", 0, 0, new Font("Tahoma 11 Negrita", 1, 12)));
    
    this.jPanel2.setName("jPanel2");
    
    jTextField2.setName("jTextField2");
    
    this.jLabel2.setFont(new Font("Tahoma 12 Negrita 12 Negrita", 1, 12));
    this.jLabel2.setText("Dirección:");
    this.jLabel2.setName("jLabel2");
    
    GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jLabel2)
      .addGap(18, 18, 18)
      .addComponent(jTextField2, -1, 668, 32767)
      .addGap(18, 18, 18)
      .addComponent((CBAbrirFichero)this.jButton4, -2, 89, -2)
      .addContainerGap()));
    
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
      .addGap(28, 28, 28)
      .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(jTextField2, -2, -1, -2)
      .addComponent(this.jLabel2)
      .addComponent((CBAbrirFichero)this.jButton4, -2, 45, -2))
      .addContainerGap(35, 32767)));
    

    this.jPanel3.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(153, 204, 255), 2, true), "Campos del protocolo", 0, 0, new Font("Tahoma 11 Negrita", 1, 12)));
    this.jPanel3.setName("jPanel3");
    
    this.jScrollPane1.setName("jScrollPane1");
    
    jTable1.setModel(new DefaultTableModel(
      new Object[][] {
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8], 
      new Object[8] }, 
      
      new String[] {
      "ID Campo", "Nombre del Campo", "Tamanho del campo", "Valor por defecto", "Descripcion", "Tipo de Dato", "Campo Opcional", "Campo Relacionado" })
      {
        Class[] types = {
          Integer.class, String.class, Integer.class, String.class, String.class, String.class, String.class, String.class };
        
        public Class getColumnClass(int columnIndex)
        {
          return this.types[columnIndex];
        }
      });
    jTable1.setCellSelectionEnabled(true);
    jTable1.setName("jTable1");
    jTable1.setSelectionMode(0);
    jTable1.addFocusListener(new FocusAdapter()
    {
      public void focusGained(FocusEvent evt)
      {
        PreferenciasDefinicion.this.jTable1FocusGained(evt);
      }
    });
    this.jScrollPane1.setViewportView(jTable1);
    jTable1.getColumnModel().getSelectionModel().setSelectionMode(1);
    jTable1.getColumnModel().getColumn(0).setResizable(false);
    jTable1.getColumnModel().getColumn(1).setResizable(false);
    jTable1.getColumnModel().getColumn(2).setResizable(false);
    jTable1.getColumnModel().getColumn(3).setResizable(false);
    jTable1.getColumnModel().getColumn(4).setResizable(false);
    
    this.jLabel3.setText("RFC :");
    this.jLabel3.setName("jLabel3");
    
    jTextField3.setName("jTextField3");
    jTextField3.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent evt)
      {
        PreferenciasDefinicion.this.jTextField3FocusLost(evt);
      }
    });
    this.jLabel4.setText("Nombre Protocolo :");
    this.jLabel4.setName("jLabel4");
    
    jTextField4.setName("jTextField4");
    jTextField4.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent evt)
      {
        PreferenciasDefinicion.this.jTextField4FocusLost(evt);
      }
    });
    this.jLabel5.setText("Campos Clave :");
    this.jLabel5.setName("jLabel5");
    
    jTextField5.setName("jTextField5");
    jTextField5.addMouseListener(new MouseAdapter()
    {
      public void mouseEntered(MouseEvent evt)
      {
        PreferenciasDefinicion.this.jTextField5MouseEntered(evt);
      }
      
      public void mouseExited(MouseEvent evt)
      {
        PreferenciasDefinicion.this.jTextField5MouseExited(evt);
      }
    });
    jTextField5.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent evt)
      {
        PreferenciasDefinicion.this.jTextField5FocusLost(evt);
      }
    });
    this.jLabel6.setName("jLabel6");
    
    this.jLabel1.setText("Numero de Campos :");
    this.jLabel1.setName("jLabel1");
    
    this.jLabel7.setText("Nivel :");
    this.jLabel7.setName("jLabel7");
    
    jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
    jComboBox1.setName("jComboBox1");
    jComboBox1.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent evt)
      {
        PreferenciasDefinicion.this.jComboBox1FocusLost(evt);
      }
    });
    jComboBox2.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
    jComboBox2.setName("jComboBox2");
    jComboBox2.addFocusListener(new FocusAdapter()
    {
      public void focusLost(FocusEvent evt)
      {
        PreferenciasDefinicion.this.jComboBox2FocusLost(evt);
      }
    });
    GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
    this.jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jLabel3)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(jTextField3, -2, 51, -2)
      .addGap(26, 26, 26)
      .addComponent(this.jLabel4)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(jTextField4, -2, 76, -2)
      .addGap(34, 34, 34)
      .addComponent(this.jLabel7)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(jComboBox1, -2, 46, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 48, 32767)
      .addComponent(this.jLabel1)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(jComboBox2, -2, 45, -2)
      .addGap(42, 42, 42)
      .addComponent(this.jLabel5)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(jTextField5, -2, 112, -2)
      .addContainerGap())
      .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
      .addContainerGap(511, 32767)
      .addComponent(this.jLabel6)
      .addGap(357, 357, 357))
      .addGroup(jPanel3Layout.createSequentialGroup()
      .addComponent(this.jScrollPane1, -1, 858, 32767)
      .addContainerGap())
      .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
      .addContainerGap(127, 32767)
      .addComponent((CBAceptar)this.jButton5, -2, 110, -2)
      .addGap(90, 90, 90)
      .addComponent((CBChequear)this.jButton3, -2, 110, -2)
      .addGap(90, 90, 90)
      .addComponent((CBGuardarFichero)this.jButton7, -2, 110, -2)
      .addGap(90, 90, 90)
      .addComponent((CBAceptar)this.jButton2, -2, 110, -2)
      .addGap(90, 90, 90)));
    
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
      .addGap(26, 26, 26)
      .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
      .addComponent(this.jLabel3)
      .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent(jTextField3, -2, -1, -2)
      .addComponent(jTextField5, -2, -1, -2)
      .addComponent(this.jLabel5)
      .addComponent(this.jLabel1)
      .addComponent(jComboBox1, -2, -1, -2)
      .addComponent(this.jLabel7)
      .addComponent(jTextField4, -2, -1, -2)
      .addComponent(this.jLabel4)
      .addComponent(jComboBox2, -2, -1, -2)))
      .addGap(18, 18, 18)
      .addComponent(this.jLabel6)
      .addGap(18, 18, 18)
      .addComponent(this.jScrollPane1, -2, 199, -2)
      .addGap(53, 53, 53)
      .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
      .addComponent((CBGuardarFichero)this.jButton7, -2, 35, -2)
      .addComponent((CBChequear)this.jButton3, -2, 35, -2)
      .addComponent((CBAceptar)this.jButton2, -2, 35, -2)
      .addComponent((CBAceptar)this.jButton5, -2, 35, -2))
      .addContainerGap(25, 32767)));
    

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
      .addContainerGap()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
      .addComponent(this.jPanel3, GroupLayout.Alignment.LEADING, -1, -1, 32767)
      .addComponent(this.jPanel2, GroupLayout.Alignment.LEADING, -1, -1, 32767))
      .addContainerGap()));
    
    layout.setVerticalGroup(
      layout.createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
      .addContainerGap()
      .addComponent(this.jPanel2, -2, -1, -2)
      .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(this.jPanel3, -2, -1, -2)
      .addContainerGap(16, 32767)));
    




    JComboBox comboTipo = new JComboBox();
    comboTipo.addItem("Booleano");
    comboTipo.addItem("Numerico");
    comboTipo.addItem("Alfanumerico");
    TableCellEditor editor = new DefaultCellEditor(comboTipo);
    jTable1.getColumnModel().getColumn(5).setCellEditor(editor);
    
    JComboBox comboTamanho = new JComboBox();
    for (int i = 1; i <= 32; i++) {
      comboTamanho.addItem(String.valueOf(i));
    }
    TableCellEditor editor2 = new DefaultCellEditor(comboTamanho);
    jTable1.getColumnModel().getColumn(2).setCellEditor(editor2);
    
    JComboBox comboOpcional = new JComboBox();
    comboOpcional.addItem("Si");
    comboOpcional.addItem("No");
    TableCellEditor editor3 = new DefaultCellEditor(comboOpcional);
    jTable1.getColumnModel().getColumn(6).setCellEditor(editor3);
    pack();
  }
  
  private void jTable1FocusGained(FocusEvent evt)
  {
    if (numCampos > 0) {
      establecerID(numCampos);
    }
  }
  
  private void jTable1FocusLost(FocusEvent evt) {}
  
  private void jTextField5MouseEntered(MouseEvent evt)
  {
    this.jLabel6.setText("Separa los campos mediante '-' ej: campo-campo");
  }
  
  private void jTextField5MouseExited(MouseEvent evt)
  {
    this.jLabel6.setText(null);
  }
  
  private void jComboBox2FocusLost(FocusEvent evt)
  {
    numCampos = Integer.parseInt(jComboBox2.getSelectedItem().toString());
  }
  
  private void jComboBox1FocusLost(FocusEvent evt)
  {
    nivel = Integer.parseInt(jComboBox1.getSelectedItem().toString());
  }
  
  private void jTextField3FocusLost(FocusEvent evt)
  {
    rfc = jTextField3.getText();
  }
  
  private void jTextField4FocusLost(FocusEvent evt)
  {
    nomProtocolo = jTextField4.getText();
  }
  
  private void jTextField5FocusLost(FocusEvent evt)
  {
    camposClave = jTextField5.getText();
  }
  
  public static String getNombreProtocolo()
  {
    return nomProtocolo;
  }
  
  public static String getRFCProtocolo()
  {
    return rfc;
  }
  
  public static int getNivelProtocolo()
  {
    return nivel;
  }
  
  public static int getNumCamposProtocolo()
  {
    return numCampos;
  }
  
  public static String getCamposClave()
  {
    return camposClave;
  }
  
  public static int getColumnas()
  {
    return numColumnas;
  }
  
  public static Object getValorTabla(int fila, int columna)
  {
    return jTable1.getValueAt(fila, columna);
  }
  
  public static Object[][] getTabla()
  {
    Object[][] tabla = new Object[numCampos][numColumnas];
    for (int i = 0; i < numCampos; i++) {
      for (int j = 0; j < numColumnas; j++) {
        tabla[i][j] = getValorTabla(i, j);
      }
    }
    return tabla;
  }
  
  private void establecerID(int num)
  {
    for (int i = 0; i < num; i++) {
      jTable1.setValueAt(Integer.valueOf(i + 1), i, 0);
    }
  }
  
  public static void setNombreProtocolo(String nombre)
  {
    jTextField4.setText(nombre);
  }
  
  public static void setRFCProtocolo(String rfc)
  {
    jTextField3.setText(rfc);
  }
  
  public static void setCamposClaveProtocolo(String claves)
  {
    jTextField5.setText(claves);
  }
  
  public static void setNivelProtocolo(int nivel2)
  {
    jComboBox1.setSelectedIndex(nivel2 - 1);
    nivel = nivel2;
  }
  
  public static void setCamposProtocolo(int campos)
  {
    jComboBox2.setSelectedIndex(campos - 1);
    numCampos = campos;
  }
  
  public static void setTablaCampos(Object[][] tabla)
  {
    for (int i = 0; i < numCampos; i++) {
      for (int j = 0; j < numColumnas; j++) {
        if (tabla[i][j].equals("null")) {
          jTable1.setValueAt(" ", i, j);
        } else {
          jTable1.setValueAt(tabla[i][j], i, j);
        }
      }
    }
  }
  
  public static void refrescar()
  {
    jTextField3.requestFocus();
    jTextField4.requestFocus();
    jTextField5.requestFocus();
    jComboBox1.requestFocus();
    jComboBox2.requestFocus();
    jTextField3.setFocusable(true);
  }
  
  public static void setFicheroCapturas(String fruta)
  {
    jTextField2.setText(fruta);
  }
}
