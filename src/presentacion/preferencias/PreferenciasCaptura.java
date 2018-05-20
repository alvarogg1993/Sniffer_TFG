package presentacion.preferencias;

import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.GroupLayout.ParallelGroup;
import org.jdesktop.layout.GroupLayout.SequentialGroup;
import org.jnetpcap.PcapIf;

import presentacion.Mediador;
import presentacion.comandos.CBAbrirFichero;
import presentacion.comandos.CBAceptar;
import presentacion.comandos.CBFiltroAyuda;
import presentacion.comandos.CBGuardarFichero;
import presentacion.comandos.Comando;
import presentacion.propiedadesVentana.CentrarVentana;
/** 
 * Clase PreferenciasCaptura. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class PreferenciasCaptura
  extends JDialog
{
  private String title;
  private String[] Protocolos = { "", "TCP", "UDP", "ICMP" };
  private String[] Puertos = { "", "80", "20", "21", "23", "22", "25", "110" };
  private String[] Capacidad = { "kilobyte(s)", "megabyte(s)", "gigabyte(s)" };
  private String[] Tiempos = { "segundo(s)", "minuto(s)", "hora(s)", "dia(s)" };
  private boolean tipo;
  private static String[] Dispositivos = { "Seleccione un dispositivo" };
  private static String[] Dispo;
  Comando jButton1;
  Comando jButton2;
  Comando jButton3;
  Comando jButton5;
  Comando jButton6;
  Mediador mediador;
  private static JButton jButton4;
  private static JCheckBox jCheckBox1;
  private static JCheckBox jCheckBox12; //Captura Sincronizada 
  private static JCheckBox jCheckBox10;
  private static JCheckBox jCheckBox2;
  private static JCheckBox jCheckBox3;
  private static JCheckBox jCheckBox4;
  private static JCheckBox jCheckBox5;
  private static JCheckBox jCheckBox6; //Pila de 
  private static JCheckBox jCheckBox11; 	// XML Circular 
  private static JCheckBox jCheckBox7;
  private static JCheckBox jCheckBox8;
  private static JCheckBox jCheckBox9;
  private static JComboBox jCBAux;
  private static JComboBox jComboBox1;
  private static JComboBox jComboBox2;
  private static JComboBox jComboBox3;
  private static JComboBox jComboBox4;
  private static JComboBox jComboBox5;
  private static JComboBox jComboBox6;
  private static JComboBox jComboBox7;
  private static JLabel jLabel1;
  private static JLabel jLabel2;
  private static JLabel jLabel3;
  private static JLabel jLabel4;
  private static JLabel jLabel5;
  private static JLabel jLabel6;
  private static JLabel jLabel7;
  private static JLabel jLabel8;
  private static JLabel jLabel9;
  private static JPanel jPanel1;
  private static JPanel jPanel2;
  private static JPanel jPanel3;
  private static JPanel jPanel4;
  private static JPanel jPanel5;
  private static JPanel jPanel6;
  private static JPanel jPanel7;
  private static JPanel jPanel8;
  private static JPanel jPanel9;
  private static JTextField jTextField1;
  private static JTextField jTextField10;
  private static JTextField jTextField2;
  private static JTextField jTextField3;
  private static JTextField jTextField4;
  private static JTextField jTextField5;
  private static JTextField jTextField6;
  private static JTextField jTextField7;
  private static JTextField jTextField8;
  private static JTextField jTextField9;
private static int i;
  
  public PreferenciasCaptura()
  {
    super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Captura", true);
    this.title = "Inicio Captura";
    this.tipo = true;
    this.mediador = new Mediador();
    
    initComponents();
    ((CBFiltroAyuda)this.jButton1).setEnabled(false);
    ((CBGuardarFichero)this.jButton5).setVisible(false);
    ((CBAbrirFichero)this.jButton6).setVisible(false);
    setResizable(false);
    new CentrarVentana(this);
  }
  
  public PreferenciasCaptura(boolean tipo, Mediador med)
  {
    super(presentacion.ventanaMenuSniffer.MenuSniffer.getFrames()[0], "Captura", true);
    this.tipo = tipo;
    if (tipo) {
      this.title = "Inicio Captura";
    } else {
      this.title = "Preferencias de Captura";
    }
    this.mediador = med;
    initComponents();
    ((CBFiltroAyuda)this.jButton1).setEnabled(false);
    if (!tipo)
    {
      jButton4.setVisible(false);
    }
    else
    {
      ((CBGuardarFichero)this.jButton5).setVisible(false);
      ((CBAbrirFichero)this.jButton6).setVisible(false);
    }
    setResizable(false);
    new CentrarVentana(this);
  }
  
  private void initComponents()
  {
    jPanel3 = new JPanel();
    jLabel9 = new JLabel();
    jPanel2 = new JPanel();
    jPanel1 = new JPanel();
    jPanel6 = new JPanel();
    jLabel2 = new JLabel();
    jTextField1 = new JTextField();
    jLabel3 = new JLabel();
    jComboBox2 = new JComboBox();
    jLabel4 = new JLabel();
    jComboBox3 = new JComboBox();
    jPanel7 = new JPanel();
    jCheckBox2 = new JCheckBox();
    jTextField2 = new JTextField();
    
    jLabel1 = new JLabel();
    jComboBox1 = new JComboBox();
    jCheckBox1 = new JCheckBox();
    jCheckBox12 = new JCheckBox(); //Captura Sincronizada
    jPanel4 = new JPanel();
    jLabel5 = new JLabel();
    jTextField3 = new JTextField();
    
    jCheckBox3 = new JCheckBox();
    jPanel8 = new JPanel();
    jPanel9 = new JPanel();
    jCheckBox4 = new JCheckBox();
    jTextField4 = new JTextField();
    jComboBox4 = new JComboBox();
    jCheckBox5 = new JCheckBox();
    jTextField5 = new JTextField();
    jComboBox5 = new JComboBox();
    jLabel6 = new JLabel();
    jTextField6 = new JTextField();
    jCheckBox6 = new JCheckBox();
    jCheckBox11 = new JCheckBox();
    jTextField7 = new JTextField();
    jLabel7 = new JLabel();
    jCheckBox7 = new JCheckBox();
    jPanel5 = new JPanel();
    jCheckBox8 = new JCheckBox();
    jCheckBox9 = new JCheckBox();
    jCheckBox10 = new JCheckBox();
    jTextField8 = new JTextField();
    jTextField9 = new JTextField();
    jTextField10 = new JTextField();
    jLabel8 = new JLabel();
    jComboBox6 = new JComboBox();
    jComboBox7 = new JComboBox();
    
    jButton4 = new JButton();
    


    setDefaultCloseOperation(2);
    setTitle(this.title);
    jPanel3.setBorder(BorderFactory.createEtchedBorder());
    jLabel9.setText("Visualiza mensajes de ayuda de esta pantalla");
    
    GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(1)
    		.add(jPanel3Layout.createSequentialGroup().addContainerGap().add(jLabel9, -1, 490, 32767).addContainerGap()));
    jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(1).add(jLabel9));
    getContentPane().add(jPanel3, "South");
    
    jPanel1.setBorder(
      BorderFactory.createTitledBorder("Captura"));
    jPanel6.setBorder(BorderFactory.createTitledBorder(null, 
      "Filtro", 
      0, 
      0, 
      new Font("Times New Roman", 0, 11)));
    jLabel2.setText("Host:");
    
    jTextField1.setText("");
    
    jLabel3.setText("Protocolo:");
    
    jComboBox2.setModel(new DefaultComboBoxModel(this.Protocolos));
    
    jLabel4.setText("Puerto:");
    
    jComboBox3.setModel(new DefaultComboBoxModel(this.Puertos));
    
    jCheckBox2.setText("Avanzado:");
    jCheckBox2.setBorder(BorderFactory.createEmptyBorder(0, 0, 
      0, 0));
    jCheckBox2.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox2.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasCaptura.this.jCheckBox2ItemStateChanged(evt);
      }
    });
    jTextField2.setEnabled(false);
    
    this.jButton1 = new CBFiltroAyuda(this.mediador);
    
    GroupLayout jPanel7Layout = new GroupLayout(
      jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(1).add(
      jPanel7Layout.createSequentialGroup().addContainerGap().add(jCheckBox2).addPreferredGap(
      0).add(jTextField2,-1, 275, 32767).add(27, 27, 27).add(
      (CBFiltroAyuda)this.jButton1).addContainerGap()));
    
    jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(1).add(
      jPanel7Layout.createParallelGroup(3).add(jCheckBox2).add(jTextField2,-2,-1,-2).add((CBFiltroAyuda)this.jButton1)));
    
    GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout
      .setHorizontalGroup(jPanel6Layout
      .createParallelGroup(1).add(jPanel6Layout
      .createSequentialGroup()
      .add(jPanel6Layout
      .createParallelGroup(1)
      .add(jPanel6Layout
      .createSequentialGroup()
      .addContainerGap()
      .add(jLabel2).addPreferredGap(0).add(jTextField1,-2,99,-2).add(14,14,14)
      .add(jLabel3).addPreferredGap(0).add(jComboBox2,-2,100,-2).addPreferredGap(0,22,32767)
      .add(jLabel4).addPreferredGap(0).add(jComboBox3,-2,100,-2)).add(jPanel7,-1,-1,32767))
      .addContainerGap()));
    
    jPanel6Layout
      .setVerticalGroup(jPanel6Layout
      .createParallelGroup(1).add(jPanel6Layout
      .createSequentialGroup()
      .add(jPanel6Layout
      .createParallelGroup(3)
      .add(jTextField1,-2,-1,-2).add(jLabel2).add(jComboBox3,-2,-1,-2).add(jLabel4).add(jComboBox2,-2,-1,-2)
      .add(jLabel3)).addPreferredGap(0,10, 32767).add(jPanel7,-2,-1,-2)
      .addContainerGap()));
    
    jLabel1.setText("Dispositivo:");
    
    jComboBox1.setModel(new DefaultComboBoxModel(Dispositivos));
    
    jCheckBox1.setSelected(true);
    jCheckBox1.setEnabled(false);
    jCheckBox1.setText("Captutar paquetes en modo promíscuo");
    jCheckBox1.setBorder(BorderFactory.createEmptyBorder(0, 0, 
      0, 0));
    jCheckBox1.setMargin(new Insets(0, 0, 0, 0));
    
    
    jCheckBox12.setSelected(false);
    jCheckBox12.setEnabled(true);
    jCheckBox12.setText("Captutar con fichero sincronizado. ");
    jCheckBox12.setBorder(BorderFactory.createEmptyBorder(0, 0, 
      0, 0));
    jCheckBox12.setMargin(new Insets(0, 0, 0, 0));
    
    GroupLayout jPanel1Layout = new GroupLayout(
      jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout
      .setHorizontalGroup(jPanel1Layout
      .createParallelGroup(1)
      .add(jPanel1Layout
      .createSequentialGroup()
      .add(jPanel1Layout
      .createParallelGroup(1)
      .add(jPanel1Layout
      .createSequentialGroup()
      .addContainerGap()
      .add(jPanel1Layout
      .createParallelGroup(1)
      .add(jCheckBox1,-2,253,-2).add(jCheckBox12,-2,253,-2).add(jPanel1Layout
      .createSequentialGroup()
      .add(jLabel1).addPreferredGap(0).add(jComboBox1,0,399,32767)))).add(jPanel6,-1,-1,32767))
      .addContainerGap()));
    
    jPanel1Layout
      .setVerticalGroup(jPanel1Layout.createParallelGroup(1)
    		  .add(jPanel1Layout.createSequentialGroup()
    				  .addContainerGap()
    				  .add(jPanel1Layout.createParallelGroup(3)
    						  .add(jLabel1).add(jComboBox1,-2,-1,-2)).add(15, 15, 15).add(jCheckBox1).add(jCheckBox12)
    						  .addPreferredGap(0,17, 32767).add(jPanel6,-2,-1,-2)));
    
    //JPanel4 contiene las opciones referentes a los ficheros capturados
    
    jPanel4.setBorder(
      BorderFactory.createTitledBorder("Fichero/os Capturado/os"));
    jLabel5.setText("Fichero:");
    
    this.jButton2 = new CBGuardarFichero(this.mediador, "Browse...");
    
    jCheckBox3.setText("Múltiples ficheros");
    jCheckBox3.setBorder(BorderFactory.createEmptyBorder(0, 0, 
      0, 0));
    jCheckBox3.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox3.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasCaptura.this.jCheckBox3ItemStateChanged(evt);
      }
    });
    jCheckBox4.setText("Próximo fichero cada");
    jCheckBox4.setBorder(BorderFactory.createEmptyBorder(0, 0, 
      0, 0));
    jCheckBox4.setEnabled(false);
    jCheckBox4.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox4.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasCaptura.this.jCheckBox4ItemStateChanged(evt);
      }
    });
    jTextField4.setEnabled(false);
    
    jComboBox4.setModel(new DefaultComboBoxModel(this.Capacidad));
    jComboBox4.setEnabled(false);
    
    jCheckBox5.setText("Próximo fichero cada");
    jCheckBox5.setBorder(BorderFactory.createEmptyBorder(0, 0, 
      0, 0));
    jCheckBox5.setEnabled(false);
    jCheckBox5.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox5.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasCaptura.this.jCheckBox5ItemStateChanged(evt);
      }
    });
    jTextField5.setEnabled(false);
    
    jComboBox5.setModel(new DefaultComboBoxModel(this.Tiempos));
    jComboBox5.setEnabled(false);
    
    jLabel6.setText("ficheros");
    jLabel6.setEnabled(false);
    
    jTextField6.setEnabled(false);
    
    jCheckBox6.setText("Pila de");
    jCheckBox6.setBorder(BorderFactory.createEmptyBorder(0, 0, 
      0, 0));
    jCheckBox6.setEnabled(false);
    jCheckBox6.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox6.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasCaptura.this.jCheckBox6ItemStateChanged(evt);
      }
    });
    
    jCheckBox11.setText("XML Circular");
    jCheckBox11.setBorder(BorderFactory.createEmptyBorder(0, 0, 
      0, 0));
    jCheckBox11.setEnabled(false);
    jCheckBox11.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox11.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
       // PreferenciasCaptura.this.jCheckBox11ItemStateChanged(evt);
      }
    });
    
    
    
    
    jTextField7.setEnabled(false);
    
    jLabel7.setText("ficheros");
    jLabel7.setEnabled(false);
    
    jCheckBox7.setText("Stop despu\u00e9s de");
    jCheckBox7.setBorder(BorderFactory.createEmptyBorder(0, 0, 
      0, 0));
    jCheckBox7.setEnabled(false);
    jCheckBox7.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox7.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasCaptura.this.jCheckBox7ItemStateChanged(evt);
      }
    });
    
    
    
    //JPanel9
    GroupLayout jPanel9Layout = new GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout
      .setHorizontalGroup(jPanel9Layout.createParallelGroup(1)
    		  .add(jPanel9Layout.createSequentialGroup()
    				  .addContainerGap()
    				  .add(jPanel9Layout.createParallelGroup(1)
    						  .add(jCheckBox7)
    						  .add(jCheckBox6)
    						  .add(jCheckBox5)
    						  .add(jCheckBox4)
    						  )
    					.add(10, 10, 10)
    					.add(jPanel9Layout.createParallelGroup(1)
    							.add(jTextField6, -1, 65, 32767)
    							.add(jTextField7, -1, 65, 32767)
    							.add(jTextField5, -1, 65, 32767)
    							.add(jTextField4, -2, 65, -2)
    						)
    					.addPreferredGap(0)
    					.add(jPanel9Layout.createParallelGroup(1)
    							.add(jLabel7)
    							.add(jLabel6)
    							//.add(jCheckBox11)
    							.add(jPanel9Layout.createParallelGroup(1, false)
    									.add(jComboBox5, 0, -1, 32767)
    									.add(jComboBox4, 0, 86, 32767)
    							)
    						)
    						.add(25, 25, 25)

    						.add(jPanel9Layout.createParallelGroup(1)
    							.add(jCheckBox11)
    							)
    						.add(40, 40, 40)
    				)
    		);
    
    jPanel9Layout
      .setVerticalGroup(jPanel9Layout.createParallelGroup(1)
    		  .add(jPanel9Layout.createSequentialGroup()
    				  .add(jPanel9Layout.createParallelGroup(3)
    						  .add(jComboBox4, -2, -1, -2)
    						  .add(jCheckBox4)
    						  .add(jTextField4, -2, -1, -2)
    					)
    					.addPreferredGap(0)
    					.add(jPanel9Layout.createParallelGroup(3)
    							.add(jCheckBox5)
    							.add(jComboBox5, -2, -1, -2)
    							.add(jTextField5, -2, -1, -2)
    					)
    					.addPreferredGap(0)
    					.add(jPanel9Layout.createParallelGroup(3)
    							.add(jCheckBox6)
    							.add(jCheckBox11)
    							.add(jLabel6)
    							.add(jTextField6, -2, -1, -2)
    						)
    					.addPreferredGap(0, -1, 32767)
    					.add(jPanel9Layout.createParallelGroup(3)
    							.add(jCheckBox7)
    							.add(jLabel7)
    							.add(jTextField7, -2, -1, -2)
    						)
    					
    				)
    			);
    
    
    //JPanel8
    GroupLayout jPanel8Layout = new GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout
    .setHorizontalGroup(jPanel8Layout.createParallelGroup(1)
    		.add(jPanel8Layout.createSequentialGroup()
    				.add(45, 45, 45)
    				.add(jPanel9, -2, -1, -2)
    				.addContainerGap(94, 32767)
    			)
    		);
    jPanel8Layout
    .setVerticalGroup(jPanel8Layout.createParallelGroup(1)
    		.add(jPanel9, -2, -1, -2)
    		);
    
    GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout
      .setHorizontalGroup(jPanel4Layout.createParallelGroup(1)
    		  .add(jPanel4Layout.createSequentialGroup()
    				  .addContainerGap()
    				  .add(jPanel4Layout.createParallelGroup(1, false)
    						  .add(jPanel4Layout.createSequentialGroup()
    								  .add(jLabel5)
    								  .addPreferredGap(0)
    								  .add(jTextField3, -2, 390, -2)
    								  .addPreferredGap(0)
    								  .add((CBGuardarFichero)this.jButton2)
    							)
    							.add(jCheckBox3)
    							.add(jPanel8, -1, -1, 32767)
    						)
    						.addContainerGap(-1, 32767)
    				)
    		);
    
    jPanel4Layout
    .setVerticalGroup(jPanel4Layout.createParallelGroup(1)
    		.add(jPanel4Layout.createSequentialGroup()
    				.add(jPanel4Layout.createParallelGroup(3)
    						.add(jTextField3, -2, -1, -2)
    						.add(jLabel5)
    						.add((CBGuardarFichero)this.jButton2)
    				)
    				.add(10, 10, 10)
    				.add(jCheckBox3)
    				.addPreferredGap(0)
    				.add(jPanel8, -2, -1, -2)
    				.addContainerGap(-1, 32767)
    			)
    		);
    
    
    //JPanel5 hace referencia a las preferencias para parar la captura de datos
    jPanel5.setBorder(
      BorderFactory.createTitledBorder("Parar Captura"));
    jCheckBox8.setText("despu\u00e9s de ...");
    jCheckBox8.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jCheckBox8.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox8.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasCaptura.this.jCheckBox8ItemStateChanged(evt);
      }
    });
    jCheckBox9.setText("despu\u00e9s de ...");
    jCheckBox9.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jCheckBox9.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox9.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasCaptura.this.jCheckBox9ItemStateChanged(evt);
      }
    });
    jCheckBox10.setText("despu\u00e9s de ...");
    jCheckBox10.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jCheckBox10.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox10.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        PreferenciasCaptura.this.jCheckBox10ItemStateChanged(evt);
      }
    });
    jTextField8.setEnabled(false);
    
    jTextField9.setEnabled(false);
    
    jTextField10.setEnabled(false);
    
    jLabel8.setText("paquete/es");
    jLabel8.setEnabled(false);
    
    jComboBox6.setModel(new DefaultComboBoxModel(this.Capacidad));
    jComboBox6.setEnabled(false);
    
    jComboBox7.setModel(new DefaultComboBoxModel(this.Tiempos));
    jComboBox7.setEnabled(false);
    
    GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout
      .setHorizontalGroup(jPanel5Layout.createParallelGroup(1)
    		  .add(jPanel5Layout.createSequentialGroup()
    				  .addContainerGap()
    				  .add(jPanel5Layout.createParallelGroup(1)
    						  .add(jCheckBox8)
    						  .add(jCheckBox9)
    						  .add(jCheckBox10)
    					)
    					.add(20, 20, 20)
    					.add(jPanel5Layout.createParallelGroup(1, false)
    							.add(jTextField8, -1, 65, 32767)
    							.add(jTextField9)
    							.add(jTextField10)
    						)
    						.addPreferredGap(0)
    						.add(jPanel5Layout.createParallelGroup(1)
    								.add(jLabel8)
    								.add(jComboBox6, 0, 95, 32767)
    								.add(jComboBox7, 0, 95, 32767)
    							)
    							.addContainerGap()
    				)
    	);
    jPanel5Layout
      .setVerticalGroup(jPanel5Layout.createParallelGroup(1)
    		  .add(jPanel5Layout.createSequentialGroup()
    				  .add(jPanel5Layout.createParallelGroup(3)
    						  .add(jCheckBox8)
    						  .add(jLabel8)
    						  .add(jTextField8, -2, -1, -2)
    					)
    					.addPreferredGap(0)
    					.add(jPanel5Layout.createParallelGroup(3)
    							.add(jCheckBox9)
    							.add(jTextField9, -2, -1, -2)
    							.add(jComboBox6, -2, -1, -2)
    					)
    					.addPreferredGap(0)
    					.add(jPanel5Layout.createParallelGroup(3)
    							.add(jCheckBox10)
    							.add(jTextField10, -2, -1, -2)
    							.add(jComboBox7, -2, -1, -2)
    						)
    						.addContainerGap(-1, 32767)
    			)
    	);
    
    if (this.tipo) {
      this.jButton3 = new CBAceptar(this.mediador, "AceptarInicioCaptura");
    } else {
      this.jButton3 = new CBAceptar(this.mediador, "Salir");
    }
    jButton4.setText("Cancelar");
    jButton4.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PreferenciasCaptura.this.jButton4ActionPerformed(evt);
      }
    });
    this.jButton5 = new CBGuardarFichero(this.mediador, "GuardarInicioCapturaXML");
    
    this.jButton6 = new CBAbrirFichero(this.mediador, "BrowseInicioCaptura");
    
    GroupLayout jPanel2Layout = new GroupLayout(
      jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout
      .setHorizontalGroup(jPanel2Layout
      .createParallelGroup(1).add(jPanel2Layout.createSequentialGroup().addContainerGap().add(
      jPanel2Layout.createParallelGroup(1).add(2,jPanel2Layout.createSequentialGroup().add(jPanel5,-2,-1,-2)
      .addPreferredGap(0,47,32767)
      .add(jPanel2Layout
      .createParallelGroup(2,false)
      .add((CBAbrirFichero)this.jButton6,-1,-1,32767)
      .add(jButton4,-1,-1,32767))
      .addPreferredGap(0).add(jPanel2Layout.createParallelGroup(1)
      .add((CBGuardarFichero)this.jButton5).add((CBAceptar)this.jButton3)))
      .add(jPanel1,0,-1,32767).add(jPanel4,-1,-1,32767))
      .addContainerGap()));
    
   jPanel2Layout.setVerticalGroup(jPanel2Layout
      .createParallelGroup(1).add(jPanel2Layout.createSequentialGroup()
      .addContainerGap()
      .add(jPanel1,-2,-1,-2).addPreferredGap(0).add(jPanel4,-2,-1,-2).add(jPanel2Layout
      .createParallelGroup(1)
      .add(jPanel2Layout.createSequentialGroup().add(16,16,16).add(jPanel2Layout
      .createParallelGroup(3)
      .add((CBGuardarFichero)this.jButton5).add((CBAbrirFichero)this.jButton6)).addPreferredGap(0,60,32767).add(jPanel2Layout
      .createParallelGroup(3)
      .add((CBAceptar)this.jButton3).add(jButton4)).addContainerGap(22,32767)).add(jPanel2Layout
      .createSequentialGroup()
      .addPreferredGap(0).add(jPanel5,-2,-1,-2)
      .addContainerGap()))));
    getContentPane().add(jPanel2, "Center");
    
    pack();
  }
  
  private void jCheckBox2ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2)
    {
      jTextField2.setEnabled(false);
      ((CBFiltroAyuda)this.jButton1).setEnabled(false);
      jLabel2.setEnabled(true);
      jLabel3.setEnabled(true);
      jLabel4.setEnabled(true);
      jTextField1.setEnabled(true);
      jComboBox2.setEnabled(true);
      jComboBox3.setEnabled(true);
    }
    else
    {
      jTextField2.setEnabled(true);
      ((CBFiltroAyuda)this.jButton1).setEnabled(true);
      jLabel2.setEnabled(false);
      jLabel3.setEnabled(false);
      jLabel4.setEnabled(false);
      jTextField1.setEnabled(false);
      jComboBox2.setEnabled(false);
      jComboBox3.setEnabled(false);
    }
  }
  
  private void jButton4ActionPerformed(ActionEvent evt)
  {
    dispose();
  }
  
  private void jCheckBox10ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2)
    {
      jCheckBox10ItemState(false);
    }
    else
    {
      jCheckBox10ItemState(true);
      jCheckBox9.setSelected(false);
      jCheckBox9ItemState(false);
    }
  }
  
  private void jCheckBox9ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2)
    {
      jCheckBox9ItemState(false);
    }
    else
    {
      jCheckBox9ItemState(true);
      jCheckBox10.setSelected(false);
      jCheckBox10ItemState(false);
    }
  }
  
  private void jCheckBox8ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      jCheckBox8ItemState(false);
    } else {
      jCheckBox8ItemState(true);
    }
  }
  
  private void jCheckBox7ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      jCheckBox7ItemState(false);
    } else {
      jCheckBox7ItemState(true);
    }
  }
  
  private void jCheckBox6ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      jCheckBox6ItemState(false);
    } else {
      jCheckBox6ItemState(true);
    }
  }
  
  private void jCheckBox5ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2)
    {
      jCheckBox5ItemState(false);
      jCheckBox4.setSelected(true);
      jCheckBox4ItemState(true);
    }
    else
    {
      jCheckBox5ItemState(true);
      jCheckBox4.setSelected(false);
      jCheckBox4ItemState(false);
    }
  }
  
  private void jCheckBox4ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2)
    {
      jCheckBox4ItemState(false);
      jCheckBox5.setSelected(true);
      jCheckBox5ItemState(true);
    }
    else
    {
      jCheckBox4ItemState(true);
      jCheckBox5.setSelected(false);
      jCheckBox5ItemState(false);
    }
  }
  
  private void jCheckBox3ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      jCheckBox3ItemState(false);
    } else {
      jCheckBox3ItemState(true);
    }
  }
  
  private void jCheckBox3ItemState(boolean auxVar)
  {
    jCheckBox4.setEnabled(auxVar);
    jCheckBox5.setEnabled(auxVar);
    jCheckBox6.setEnabled(auxVar);
    jCheckBox7.setEnabled(auxVar);
    if (auxVar)
    {
      jCheckBox4ItemState(jCheckBox4.isSelected());
      jCheckBox5ItemState(jCheckBox5.isSelected());
      jCheckBox6ItemState(jCheckBox6.isSelected());
      jCheckBox7ItemState(jCheckBox7.isSelected());
      jCheckBox9.setEnabled(false);
      jCheckBox9ItemState(false);
      jCheckBox10.setEnabled(false);
      jCheckBox10ItemState(false);
    }
    else
    {
      jCheckBox4ItemState(false);
      jCheckBox5ItemState(false);
      jCheckBox6ItemState(false);
      jCheckBox7ItemState(false);
      jCheckBox9ItemState(false);
      jCheckBox10ItemState(false);
      jCheckBox9.setEnabled(true);
      jCheckBox9ItemState(jCheckBox9.isSelected());
      jCheckBox10.setEnabled(true);
      jCheckBox10ItemState(jCheckBox10.isSelected());
    }
  }
  
  private void jCheckBox4ItemState(boolean auxVar)
  {
    jTextField4.setEnabled(auxVar);
    
    jComboBox4.setEnabled(auxVar);
  }
  
  private void jCheckBox5ItemState(boolean auxVar)
  {
    jTextField5.setEnabled(auxVar);
    
    jComboBox5.setEnabled(auxVar);
  }
  
  private void jCheckBox6ItemState(boolean auxVar)
  {
    jTextField6.setEnabled(auxVar);
    
    jLabel6.setEnabled(auxVar);
    
    jCheckBox11.setEnabled(auxVar);
  }
  
  private void jCheckBox7ItemState(boolean auxVar)
  {
    jTextField7.setEnabled(auxVar);
    
    jLabel7.setEnabled(auxVar);
  }
  
  private void jCheckBox8ItemState(boolean auxVar)
  {
    jTextField8.setEnabled(auxVar);
    
    jLabel8.setEnabled(auxVar);
  }
  
  private void jCheckBox9ItemState(boolean auxVar)
  {
    jTextField9.setEnabled(auxVar);
    
    jComboBox6.setEnabled(auxVar);
  }
  
  private void jCheckBox10ItemState(boolean auxVar)
  {
    jTextField10.setEnabled(auxVar);
    
    jComboBox7.setEnabled(auxVar);
  }
  
  public static void addDispositivo(String[] aux)
  {
    String sAux = "";
    Dispo = new String[aux.length + 1];
    Dispo[0] = "";
    for (int i = 0; i < aux.length; i++)
    {
      sAux = aux[i].toString();
      int dotPlace = sAux.indexOf(" ");
      String name2;
      
      if (dotPlace >= 0)
      {
        String name = sAux.substring(0, dotPlace);
        name2 = sAux.substring(dotPlace + 1);
      }
      else
      {
        name2 = sAux;
      }
      jComboBox1.addItem(name2);
      Dispo[(i + 1)] = aux[i];
    }
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
  
  public static void setDispositivo(String list)
  {
	  
	  
	  
	  
	  
	  
	  
  list = list.toString().trim();
for (i = 0; i < Dispo.length; i++)
    {
      String ooo = Dispo[i].toString().trim();
      if (list.contains(ooo)) {
        jComboBox1.setSelectedIndex(i);
      }
    }
  }
  
  public static String getDispositivo()
  {
    return 
      Dispo[jComboBox1.getSelectedIndex()] + "\n " + jComboBox1.getSelectedItem();
  }
  
  public static void setPromiscuo(boolean aux)
  {
    jCheckBox1.setSelected(aux);
  }
  
  public static boolean getPromiscuo()
  {
    return jCheckBox1.isSelected();
  }
  
  public static void setHost(String aux)
  {
    jTextField1.setText(aux);
  }
  
  public static String getHost()
  {
    return jTextField1.getText();
  }
  
  public static void setProtocolo(String aux)
  {
    for (int i = 0; i < jComboBox2.getItemCount(); i++) {
      if (jComboBox2.getItemAt(i).equals(aux)) {
        jComboBox2.setSelectedIndex(i);
      }
    }
  }
  
  public static String getProtocolo()
  {
    return (String)jComboBox2.getSelectedItem();
  }
  
  public static void setPuerto(String aux)
  {
    for (int i = 0; i < jComboBox3.getItemCount(); i++) {
      if (jComboBox3.getItemAt(i).equals(aux)) {
        jComboBox3.setSelectedIndex(i);
      }
    }
  }
  
  public static String getPuerto()
  {
    return (String)jComboBox3.getSelectedItem();
  }
  
  public static void setAvanzado(boolean aux)
  {
    jCheckBox2.setSelected(aux);
  }
  
  public static boolean getAvanzado()
  {
    return jCheckBox2.isSelected();
  }
  
  public static void setAvanzado(String aux)
  {
    jTextField2.setText(aux);
  }
  
  public static String getAvanzadoDato()
  {
    return jTextField2.getText();
  }
  
  public static void setFichero(String aux)
  {
    jTextField3.setText(aux);
  }
  
  public static String getFichero()
  {
    return jTextField3.getText();
  }
  
  public static void setMultiplesFicheros(boolean aux)
  {
    if (!aux) {
      jCheckBox3.setSelected(true);
    }
    jCheckBox3.setSelected(aux);
  }
  
  public static boolean getMultiplesFicheros()
  {
    return jCheckBox3.isSelected();
  }
  
  public static void setProximoEspacio(boolean aux)
  {
    jCheckBox4.setSelected(aux);
  }
  
  public static boolean getProximoEspacio()
  {
    return jCheckBox4.isSelected();
  }
  
  public static void setProximoEspacio(String aux)
  {
    jTextField4.setText(aux);
  }
  
  public static String getProximoEspacioDato()
  {
    return jTextField4.getText();
  }
  
  public static void setProximoEspacioTipo(String aux)
  {
    for (int i = 0; i < jComboBox4.getItemCount(); i++)
    {
      String ooo = jComboBox4.getItemAt(i).toString();
      if (jComboBox4.getItemAt(i).toString().contains(aux.toLowerCase())) {
        jComboBox4.setSelectedIndex(i);
      }
    }
  }
  
  public static String getProximoEspacioTipo()
  {
    return (String)jComboBox4.getSelectedItem();
  }
  
  public static void setProximoTiempo(boolean aux)
  {
    jCheckBox5.setSelected(aux);
  }
  
  public static boolean getProximoTiempo()
  {
    return jCheckBox5.isSelected();
  }
  
  public static void setProximoTiempo(String aux)
  {
    jTextField5.setText(aux);
  }
  
  public static String getProximoTiempoDato()
  {
    return jTextField5.getText();
  }
  
  public static void setProximoTiempoTipo(String aux)
  {
    for (int i = 0; i < jComboBox5.getItemCount(); i++)
    {
      String ooo = jComboBox5.getItemAt(i).toString();
      if (jComboBox5.getItemAt(i).toString().contains(aux.toLowerCase())) {
        jComboBox5.setSelectedIndex(i);
      }
    }
  }
  
  public static String getProximoTiempoTipo()
  {
    return (String)jComboBox5.getSelectedItem();
  }
  
  public static void setPila(boolean aux)
  {
    jCheckBox6.setSelected(aux);
  }
  
  public static boolean getPilaXML()
  {
    return jCheckBox11.isSelected();
  }
  
  public static void setPilaXML(boolean aux)
  {
    jCheckBox11.setSelected(aux);
  }
  
  public static boolean getNFSsync()
  {
    return jCheckBox12.isSelected();
  }
  
  public static void setNFSsync(boolean aux)
  {
    jCheckBox12.setSelected(aux);
  }
  
  
  public static boolean getPila()
  {
    return jCheckBox6.isSelected();
  }  
  
  public static void setPila(String aux)
  {
    jTextField6.setText(aux);
  }
  
  public static String getPilaDato()
  {
    return jTextField6.getText();
  }
  
  public static void setStop(boolean aux)
  {
    jCheckBox7.setSelected(aux);
  }
  
  public static boolean getStop()
  {
    return jCheckBox7.isSelected();
  }
  
  public static void setStop(String aux)
  {
    jTextField7.setText(aux);
  }
  
  public static String getStopDato()
  {
    return jTextField7.getText();
  }
  
  public static void setDespuesPaquetes(boolean aux)
  {
    jCheckBox8.setSelected(aux);
  }
  
  public static boolean getDespuesPaquetes()
  {
    return jCheckBox8.isSelected();
  }
  
  public static void setDespuesPaquetes(String aux)
  {
    jTextField8.setText(aux);
  }
  
  public static String getDespuesPaquetesDato()
  {
    return jTextField8.getText();
  }
  
  public static void setDespuesEspacio(boolean aux)
  {
    jCheckBox9.setSelected(aux);
  }
  
  public static boolean getDespuesEspacio()
  {
    return jCheckBox9.isSelected();
  }
  
  public static void setDespuesEspacio(String aux)
  {
    jTextField9.setText(aux);
  }
  
  public static String getDespuesEspacioDato()
  {
    return jTextField9.getText();
  }
  
  public static void setDespuesEspacioTipo(String aux)
  {
    for (int i = 0; i < jComboBox6.getItemCount(); i++)
    {
      String ooo = jComboBox6.getItemAt(i).toString();
      if (jComboBox6.getItemAt(i).toString().contains(aux.toLowerCase())) {
        jComboBox6.setSelectedIndex(i);
      }
    }
  }
  
  public static String getDespuesEspacioTipo()
  {
    return (String)jComboBox6.getSelectedItem();
  }
  
  public static void setDespuesTiempo(boolean aux)
  {
    jCheckBox10.setSelected(aux);
  }
  
  public static boolean getDespuesTiempo()
  {
    return jCheckBox10.isSelected();
  }
  
  public static void getDespuesTiempo(String aux)
  {
    jTextField10.setText(aux);
  }
  
  public static void setDespuesTiempo(String aux)
  {
    jTextField10.setText(aux);
  }
  
  public static String getDespuesTiempoDato()
  {
    return jTextField10.getText();
  }
  
  public static void setDespuesTiempoTipo(String aux)
  {
    for (int i = 0; i < jComboBox7.getItemCount(); i++)
    {
      String ooo = jComboBox7.getItemAt(i).toString();
      if (jComboBox7.getItemAt(i).toString().contains(aux.toLowerCase())) {
        jComboBox7.setSelectedIndex(i);
      }
    }
  }
  
  public static String getDespuesTiempoTipo()
  {
    return (String)jComboBox7.getSelectedItem();
  }
  
  public void rep()
  {
    repaint();
  }
}
