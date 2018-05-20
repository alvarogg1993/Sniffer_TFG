package presentacion.visualizarCaptura;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
/** 
 * Clase SelectColor. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class SelectColor
  extends JFrame
{
  protected Color newColor;
  private TablePane RTablePane;
  private JButton jButton1;
  private JButton jButton2;
  private JColorChooser jColorChooser1;
  
  public SelectColor(TablePane RTablePane)
  {
    super("Seleccion de Color");
    this.RTablePane = RTablePane;
    initComponents();
  }
  
  private void initComponents()
  {
    this.jColorChooser1 = new JColorChooser();
    this.jButton1 = new JButton();
    this.jButton2 = new JButton();
    
    getContentPane().setLayout(
      new AbsoluteLayout());
    
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        SelectColor.this.exitForm(evt);
      }
    });
    this.jColorChooser1.getSelectionModel().addChangeListener(
      new ChangeListener()
      {
        public void stateChanged(ChangeEvent evt)
        {
          SelectColor.this.newColor = SelectColor.this.jColorChooser1.getColor();
        }
      });
    getContentPane().add(
      this.jColorChooser1, 
      new AbsoluteConstraints(0, 0, 410, 
      270));
    
    this.jButton1.setText("Aceptar");
    this.jButton1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        SelectColor.this.jButton1ActionPerformed(evt);
      }
    });
    getContentPane().add(
      this.jButton1, 
      new AbsoluteConstraints(140, 280, -1, 
      -1));
    
    this.jButton2.setText("Cancelar");
    this.jButton2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        SelectColor.this.jButton2ActionPerformed(evt);
      }
    });
    getContentPane().add(
      this.jButton2, 
      new AbsoluteConstraints(210, 280, -1, 
      -1));
    
    pack();
  }
  
  private void jButton2ActionPerformed(ActionEvent evt)
  {
    dispose();
  }
  
  private void jButton1ActionPerformed(ActionEvent evt)
  {
    this.RTablePane.obtenercolor(this.newColor);
    
    dispose();
  }
  
  private void exitForm(WindowEvent evt)
  {
    dispose();
  }
}
