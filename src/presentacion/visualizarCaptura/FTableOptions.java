package presentacion.visualizarCaptura;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import presentacion.propiedadesVentana.CentrarVentana;
/** 
 * Clase FTableOptions. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class FTableOptions
  extends JFrame
{
  private boolean numeropaquete;
  private boolean time;
  private boolean macsource;
  private boolean macdest;
  private boolean frame;
  private boolean protocol;
  private boolean ipsrc;
  private boolean ipdest;
  private boolean portsrc;
  private boolean portdest;
  private boolean seq;
  private boolean ack;
  private boolean length;
  private boolean size;
  public TablePane venpadre;
  private JButton jButton1;
  private JButton jButton2;
  private JCheckBox jCheckBox1;
  private JCheckBox jCheckBox10;
  private JCheckBox jCheckBox11;
  private JCheckBox jCheckBox12;
  private JCheckBox jCheckBox13;
  private JCheckBox jCheckBox14;
  private JCheckBox jCheckBox2;
  private JCheckBox jCheckBox3;
  private JCheckBox jCheckBox4;
  private JCheckBox jCheckBox5;
  private JCheckBox jCheckBox6;
  private JCheckBox jCheckBox7;
  private JCheckBox jCheckBox8;
  private JCheckBox jCheckBox9;
  private JPanel jPanel1;
  private JPanel jPanel2;
  
  public FTableOptions(TablePane venpadre)
  {
    this.venpadre = venpadre;
    initComponents();
    new CentrarVentana(this);
  }
  
  private void initComponents()
  {
    this.jCheckBox1 = new JCheckBox();
    this.jCheckBox2 = new JCheckBox();
    this.jCheckBox3 = new JCheckBox();
    this.jCheckBox4 = new JCheckBox();
    this.jCheckBox5 = new JCheckBox();
    this.jCheckBox6 = new JCheckBox();
    this.jCheckBox7 = new JCheckBox();
    this.jCheckBox8 = new JCheckBox();
    this.jCheckBox9 = new JCheckBox();
    this.jCheckBox10 = new JCheckBox();
    this.jCheckBox11 = new JCheckBox();
    this.jCheckBox14 = new JCheckBox();
    this.jCheckBox13 = new JCheckBox();
    this.jCheckBox12 = new JCheckBox();
    this.jPanel1 = new JPanel();
    this.jPanel2 = new JPanel();
    this.jButton1 = new JButton();
    this.jButton2 = new JButton();
    
    this.numeropaquete = this.venpadre.getNumeropaquete();
    this.time = this.venpadre.getTime();
    this.macsource = this.venpadre.getMacsource();
    this.macdest = this.venpadre.getMacdest();
    this.frame = this.venpadre.getFrame();
    this.protocol = this.venpadre.getProtocol();
    this.ipsrc = this.venpadre.getIpsrc();
    this.ipdest = this.venpadre.getIpdest();
    this.portsrc = this.venpadre.getPortsrc();
    this.portdest = this.venpadre.getPortdest();
    this.seq = this.venpadre.getSeq();
    this.ack = this.venpadre.getAck();
    this.length = this.venpadre.getLength();
    this.size = this.venpadre.getSize();
    
    getContentPane().setLayout(new GridLayout(8, 2));
    
    setDefaultCloseOperation(2);
    setTitle("Table Options");
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        FTableOptions.this.exitForm(evt);
      }
    });
    this.jCheckBox1.setSelected(this.numeropaquete);
    this.jCheckBox1.setText("nº");
    this.jCheckBox1.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox1ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox1);
    
    this.jCheckBox2.setSelected(this.ipdest);
    this.jCheckBox2.setText("Add IP dest");
    this.jCheckBox2.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox2ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox2);
    
    this.jCheckBox3.setSelected(this.time);
    this.jCheckBox3.setText("Time");
    this.jCheckBox3.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox3ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox3);
    
    this.jCheckBox4.setSelected(this.portsrc);
    this.jCheckBox4.setText("Port Src");
    this.jCheckBox4.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox4ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox4);
    
    this.jCheckBox5.setSelected(this.macsource);
    this.jCheckBox5.setText("MAC Source Adress");
    this.jCheckBox5.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox5ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox5);
    
    this.jCheckBox6.setSelected(this.portdest);
    this.jCheckBox6.setText("Port dest");
    this.jCheckBox6.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox6ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox6);
    
    this.jCheckBox7.setSelected(this.macdest);
    this.jCheckBox7.setText("MAC Dest Adress");
    this.jCheckBox7.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox7ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox7);
    
    this.jCheckBox8.setSelected(this.seq);
    this.jCheckBox8.setText("SEQ");
    this.jCheckBox8.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox8ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox8);
    
    this.jCheckBox9.setSelected(this.frame);
    this.jCheckBox9.setText("Frame");
    this.jCheckBox9.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox9ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox9);
    
    this.jCheckBox10.setSelected(this.ack);
    this.jCheckBox10.setText("ACK");
    this.jCheckBox10.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox10ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox10);
    
    this.jCheckBox11.setSelected(this.protocol);
    this.jCheckBox11.setText("Protocol");
    this.jCheckBox11.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox11ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox11);
    
    this.jCheckBox12.setSelected(this.size);
    this.jCheckBox12.setText("Size");
    this.jCheckBox12.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox12ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox12);
    
    this.jCheckBox13.setSelected(this.ipsrc);
    this.jCheckBox13.setText("Add IP scr");
    this.jCheckBox13.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox13ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox13);
    

    this.jCheckBox14.setSelected(this.length);
    this.jCheckBox14.setText("Header Length");
    this.jCheckBox14.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent evt)
      {
        FTableOptions.this.jCheckBox14ItemStateChanged(evt);
      }
    });
    getContentPane().add(this.jCheckBox14);
    
    getContentPane().add(this.jPanel1);
    
    this.jButton1.setText("Aceptar");
    this.jButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        FTableOptions.this.jButton1ActionPerformed(evt);
      }
    });
    this.jPanel2.add(this.jButton1);
    
    this.jButton2.setText("Cancelar");
    this.jButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        FTableOptions.this.jButton2ActionPerformed(evt);
      }
    });
    this.jPanel2.add(this.jButton2);
    
    getContentPane().add(this.jPanel2);
    
    pack();
  }
  
  private void jButton2ActionPerformed(ActionEvent evt)
  {
    dispose();
  }
  
  private void jButton1ActionPerformed(ActionEvent evt)
  {
    opciones();
    dispose();
  }
  
  private void jCheckBox1ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.numeropaquete = false;
    } else {
      this.numeropaquete = true;
    }
  }
  
  private void jCheckBox2ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.ipdest = false;
    } else {
      this.ipdest = true;
    }
  }
  
  private void jCheckBox3ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.time = false;
    } else {
      this.time = true;
    }
  }
  
  private void jCheckBox4ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.portsrc = false;
    } else {
      this.portsrc = true;
    }
  }
  
  private void jCheckBox5ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.macsource = false;
    } else {
      this.macsource = true;
    }
  }
  
  private void jCheckBox6ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.portdest = false;
    } else {
      this.portdest = true;
    }
  }
  
  private void jCheckBox7ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.macdest = false;
    } else {
      this.macdest = true;
    }
  }
  
  private void jCheckBox8ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.seq = false;
    } else {
      this.seq = true;
    }
  }
  
  private void jCheckBox9ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.frame = false;
    } else {
      this.frame = true;
    }
  }
  
  private void jCheckBox10ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.ack = false;
    } else {
      this.ack = true;
    }
  }
  
  private void jCheckBox11ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.protocol = false;
    } else {
      this.protocol = true;
    }
  }
  
  private void jCheckBox12ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.size = false;
    } else {
      this.size = true;
    }
  }
  
  private void jCheckBox13ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.ipsrc = false;
    } else {
      this.ipsrc = true;
    }
  }
  
  private void jCheckBox14ItemStateChanged(ItemEvent evt)
  {
    if (evt.getStateChange() == 2) {
      this.length = false;
    } else {
      this.length = true;
    }
  }
  
  public void opciones()
  {
    this.venpadre.setColumnas(this.numeropaquete, this.time, this.macsource, this.macdest, 
      this.frame, this.protocol, this.ipsrc, this.ipdest, this.portsrc, this.portdest, this.seq, this.ack, 
      this.length, this.size);
    this.venpadre.viewColumns();
  }
  
  private void exitForm(WindowEvent evt)
  {
    dispose();
  }
}
