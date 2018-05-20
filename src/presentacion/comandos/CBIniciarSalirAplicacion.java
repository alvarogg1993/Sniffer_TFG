package presentacion.comandos;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import presentacion.Mediador;
/** 
 * Clase CBIniciarSalirAplicacion. 
 * 
 * @author Jose Manuel Saiz, Carlos Mardones
 * @author jmsaizg@gmail.com,  
 * @version 1.2 
*/
public class CBIniciarSalirAplicacion
  extends JButton
  implements Comando
{
  private Mediador mediador;
  private String bnombre;
  
  public CBIniciarSalirAplicacion(Mediador mediador, String bnombre, ImageIcon icono)
  {
    super(bnombre, icono);
    this.bnombre = bnombre;
    setFont(new Font("Arial", 1, 16));
    setBorder(new BevelBorder(
      0));
    
    this.mediador = mediador;
    addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          CBIniciarSalirAplicacion.this.ejecutar();
        }
      }
    });
    addActionListener(mediador);
    requestFocus(true);
  }
  
  public CBIniciarSalirAplicacion(Mediador mediador, String bnombre)
  {
    super(bnombre);
    this.bnombre = bnombre;
    setFont(new Font("Arial", 1, 16));
    setBorder(new BevelBorder(
      0));
    
    this.mediador = mediador;
    addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          CBIniciarSalirAplicacion.this.ejecutar();
        }
      }
    });
    addActionListener(mediador);
    requestFocus(true);
  }
  
  public void ejecutar()
  {
    boolean salir = this.mediador.irAventana(this.bnombre);
    String barra = System.getProperty("file.separator");
    if (!salir) {
      this.mediador.cerrarVentana(this.mediador.getVentanaPresentacion());
    }
  }
}
